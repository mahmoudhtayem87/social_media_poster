# Social Media Poster

A Module listener which has been created to demonstrate how to automate social media posting for a content article, once the article is created or modified, a new post on FaceBook page will be created, the implementation is open to be extended to include linked, twitter, or any other social media network.

## Facebook developer account
Learn how to setup a [FB developer account](docs/facebook.md)

## Configurations

-	social-media-posts-ids-field-id: create a string field in your structure to store social media posts ids for the current article
-	facebook-page-access-token: Page access token is required to allow liferay to post feed on your business page
-	facebook-page-id: Page ID is required to allow liferay to post feed on your business page
-	social-media-posting-active: enable or disable the social media posting
-	publishing-approval-required: indicate whether an approval workflow is required to post to social media or not
-	facebook-active: enable or disable the social media posting on Facebook
-	Web-Content-Structure-ID: Web content structure to be monitored for social media posting
-	post-link-base-address: display page base address, the address should starts at http-> ends with /-/, where it will be concatenated with the article Title URL
-	post-to-facebook-field-id: create a Boolean field in your structure to enable or disable Facebook posting for the current article, if this field left empty, it means post all to Facebook

## Screenshots
[![Screen-Shot-2021-03-10-at-1-36-51-PM.png](https://i.postimg.cc/s1dgPqrM/Screen-Shot-2021-03-10-at-1-36-51-PM.png)](https://postimg.cc/RJdm4Pqm)
[![Screen-Shot-2021-03-10-at-1-37-41-PM.png](https://i.postimg.cc/Hk8f1nb1/Screen-Shot-2021-03-10-at-1-37-41-PM.png)](https://postimg.cc/VJ17jY7K)
[![Screen-Shot-2021-03-10-at-1-41-02-PM.png](https://i.postimg.cc/FRTPLX0x/Screen-Shot-2021-03-10-at-1-41-02-PM.png)](https://postimg.cc/0MJGT3W6)
