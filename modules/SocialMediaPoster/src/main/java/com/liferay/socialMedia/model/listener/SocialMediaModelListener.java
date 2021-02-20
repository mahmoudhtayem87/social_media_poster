package com.liferay.socialMedia.model.listener;

import com.liferay.dynamic.data.mapping.model.DDMStructure;
import com.liferay.dynamic.data.mapping.service.DDMStructureLocalServiceUtil;
import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalService;
import com.liferay.portal.configuration.metatype.bnd.util.ConfigurableUtil;
import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.service.CompanyLocalService;
import com.liferay.portal.kernel.service.GroupLocalService;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.portal.kernel.xml.*;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Modified;
import org.osgi.service.component.annotations.Reference;
import java.util.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

@Component(
        immediate = true,
        service = ModelListener.class,
        configurationPid = SocialMediaModelListenerConfiguration.PID
)
public class SocialMediaModelListener
        extends BaseModelListener<JournalArticle> {


  private static final Log _log = LogFactoryUtil.getLog(
          SocialMediaModelListener.class
  );
  @Reference
  JournalArticleLocalService _journalArticleLocalService;
  @Reference
  GroupLocalService _groupLocalService;
  @Reference
  CompanyLocalService _companyLocalService;
  @Reference
  PortalUtil _portalUtil;
  
  
  private volatile SocialMediaModelListenerConfiguration _config;
  public static String encode(String url) {
    return url.replace(" ", "%20");
  }
  private String getFieldValue(JournalArticle journalArticleObject, String FieldName) throws DocumentException {
    Document document = SAXReaderUtil.read(journalArticleObject.getContentByLocale(Locale.getDefault().toString()));
    Node fieldNode = document.selectSingleNode("/root/dynamic-element[@name='" + FieldName + "']/dynamic-content");
    String value = fieldNode.getText();
    return value;
  }
  private String FacebookPostURLBuilder(String Subject, String PostLink) {
    return "https://graph.facebook.com/" + _config.facebookPageID() + "/feed";
  }
  private void PostToFaceBook(JournalArticle Article) throws PortalException {
    String Subject = Article.getTitle();
    String articleTitle = Article.getUrlTitle();
    StringBuilder friendlyURLBuilder = new StringBuilder();
    String PostLink = friendlyURLBuilder.append(_config.postLinkBaseAddress()).append(articleTitle).toString();
    Client client = ClientBuilder.newClient();
    WebTarget target = client.
            target(FacebookPostURLBuilder(Subject, PostLink));
    Response response = target.queryParam("access_token", _config.facebookPageAccessToken())
            .queryParam("message", encode(Subject))
            .queryParam("link", PostLink)
            .request()
            .post(null);
    _log.error
            (
                    new StringBuilder()
                            .append("Facebook Post Created with ID: ")
                            .append(response.readEntity(String.class))
                            .toString()
            );
  }
  private void post(JournalArticle Article) throws DocumentException {
    try {
      if (!_config.postToFacebookFieldID().isEmpty()) {
        Boolean postToFacebookFieldID = Boolean.parseBoolean(getFieldValue(Article, _config.postToFacebookFieldID()));
        if (postToFacebookFieldID) {
          PostToFaceBook(Article);
          updateValue(Article, _config.postToFacebookFieldID(), "false");
        }
      } else if (_config.postToFacebookFieldID().isEmpty() && _config.facebookActive()) {
        PostToFaceBook(Article);
      }
    } catch (DocumentException | PortalException ex) {
      _log.error("Error at post function", ex);
    }
  }
  @Override
  public void onAfterCreate(JournalArticle Article)
          throws ModelListenerException {
    if (Article.isDraft())
      return;
    try {
      //if module is not active, kill the process at this line
      if (!_config.activePosting() ||
              Article.getDDMStructure().getStructureId() != Long.valueOf(_config.webContentStructureID()))
        return;
      if (_config.publishingApprovalRequired() == true) {
        if (Article.getStatus() == 0) {
          // publishing approval is required
          post(Article);
        }
      } else {
        // publishing approval is not required
        post(Article);
      }

    } catch (DocumentException e) {
      _log.error("error at first level" + e.getMessage());
    }
  }
  @Override
  public void onAfterUpdate(JournalArticle Article)
          throws ModelListenerException {
    if (Article.isDraft())
      return;
    try {
      //if module is not active, kill the process at this line
      if (!_config.activePosting() ||
              Article.getDDMStructure().getStructureId() != Long.valueOf(_config.webContentStructureID()))
        return;
      if (_config.publishingApprovalRequired() == true) {
        if (Article.getStatus() == 0) {
          // publishing approval is required
          post(Article);
        }
      } else {
        // publishing approval is not required
        post(Article);
      }

    } catch (DocumentException e) {
      _log.error("error at first level" + e.getMessage());
    }
  }
  private void updateValue(JournalArticle article, String fieldname, String newValue) throws PortalException, DocumentException {
	    ServiceContext serviceContext = new ServiceContext();
	    serviceContext.setCompanyId(article.getCompanyId());
	    serviceContext.setScopeGroupId(article.getGroupId());
	    SAXReader reader = SAXReaderUtil.getSAXReader();
	    Document document = reader.read(article.getContentByLocale(Locale.getDefault().toString()));
	    try {
	        XPath xPath = SAXReaderUtil.createXPath(
	                "//dynamic-element[@name='" + fieldname
	                        + "']/dynamic-content[@language-id='" + Locale.getDefault().toString() + "']");
	        Element n = (Element) xPath.selectSingleNode(document);
	        _log.debug("current value: " + n.getText());
	        n.setText(newValue);
	        _journalArticleLocalService.updateArticle(article.getUserId(),
	                article.getGroupId(),
	                article.getFolderId(),
	                article.getArticleId(),
	                article.getVersion(),
	                article.getTitleMap(), article.getDescriptionMap(),
	                document.asXML(),
	                article.getLayoutUuid(),
	                serviceContext);
	    } catch (Exception e) {
	        _log.error(e);
	    }
	}
  
  
  @Activate
  @Modified
  public void activate(Map<String, String> properties) {
    try {
      _log.error(
              "activating Social Media Model Listener"
      );
      _config =
              ConfigurableUtil.createConfigurable(
                      SocialMediaModelListenerConfiguration.class,
                      properties
              );
    } catch (Exception e) {
      _log.error(
              "error while activating Social Media Model Listener, please provide a valid configurations"
      );
    }
  }
}
