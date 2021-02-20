package com.liferay.socialMedia.model.listener;

import aQute.bnd.annotation.metatype.Meta;

@Meta.OCD(
        id = SocialMediaModelListenerConfiguration.PID,
        localization = "content/Language",
        name = "social-media-listener-config-name"
)
interface SocialMediaModelListenerConfiguration {

    @Meta.AD(
            name = "social-media-posting-active",
            description = "enable or disable the social media posting",
            required = false,
            deflt = "false"
    )
    public boolean activePosting();
    @Meta.AD(
            name = "publishing-approval-required",
            description = "indicate whether an approval workflow is required to post to social media or not",
            required = false,
            deflt = "false"
    )
    public boolean publishingApprovalRequired();
    @Meta.AD(
            name = "facebook-active",
            description = "enable or disable the social media posting on facebook",
            required = false,
            deflt = "false"
    )
    public boolean facebookActive();
    @Meta.AD(
            name = "Web-Content-Structure-ID",
            description = "Web content strcutre to be monitored, once the content article is approved, will be posted to social media",
            required = false,
            deflt = "0"
    )
    public String webContentStructureID();

    @Meta.AD(
            name = "post-link-base-address",
            description = "display page base address, the address should starts at http-> ends with /-/, where it will be concatenated with the article Title URL",
            required = false,
            deflt = "0"
    )
    public String postLinkBaseAddress();

    @Meta.AD(
            name = "post-to-facebook-field-id",
            description = "create a boolean field in your structure to enable or disable facebook posting for the current article, if this field left empty, it means post all to facebook",
            required = false,
            deflt = "id0123"
    )
    public String postToFacebookFieldID();

    @Meta.AD(
            name = "social-media-posts-ids-field-id",
            description = "create a string field in your structure to store social media posts ids for the current article",
            required = true,
            deflt = "id0123"
    )
    public String SocialMediaPostsIDFieldID();

    @Meta.AD(
            name = "facebook-page-access-token",
            description = "Page access token is required to allow liferay to post feed on your business page",
            required = true,
            deflt = "0"
    )
    public String facebookPageAccessToken();
    @Meta.AD(
            name = "facebook-page-id",
            description = "Page ID is required to allow liferay to post feed on your business page",
            required = true,
            deflt = "0"
    )
    public String facebookPageID();


    public static final String PID = "com.liferay.socialMedia.model.listener.SocialMediaModelListenerConfiguration";

}
