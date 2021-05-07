# Social Media Poster

A Module listener which has been created to demonstrate how to automate social media posting for a content article, once the article is created or modified, a new post on FaceBook page will be created, the implementation is open to be extended to include linked, twitter, or any other social media network.

## Facebook developer account
Learn how to setup a [FB developer account](docs/facebook.md)

## How to obtain long live page access token (valid for 60 days)
1. First obtain Short Lived User access token using https://developers.facebook.com/tools/explorer

1. Obtain Long Lived User Access token (valid for 60 days)
1. 

curl -i -X GET "https://graph.facebook.com/oauth/access_token?grant_type=fb_exchange_token&client_id=2615708165394466&client_secret=eca4ee72be948406e14112fec686231a&fb_exchange_token=EAAlKZBQQI0CIBAMBsPAmrVbuqthtEQ89BcMSn0ZAIjEvnnbkCDWyJaaTOINmjGEQo2aHrBjdhwjLdKV7v9Yi0blX0NngAHnOPjgZBgZCJGBGTWMoGR7yxgNhQg0qcsNcDHEPEBJTUZA3e45sLYqOtkMe56j8CYin5A6XxOZAZBk0n2VjRT4aVxzMGQdpzHRptanRXmZBiJkEQzZC8ZCtGv2WfL"

EAAlKZBQQI0CIBAMBsPAmrVbuqthtEQ89BcMSn0ZAIjEvnnbkCDWyJaaTOINmjGEQo2aHrBjdhwjLdKV7v9Yi0blX0NngAHnOPjgZBgZCJGBGTWMoGR7yxgNhQg0qcsNcDHEPEBJTUZA3e45sLYqOtkMe56j8CYin5A6XxOZAZBk0n2VjRT4aVxzMGQdpzHRptanRXmZBiJkEQzZC8ZCtGv2WfL


ACCESS TOKEN: EAAlKZBQQI0CIBAH6ltn0ACDMcvlsu3qP3ym7GcwJQZCeYLk8EjHkT86t6tQIh4apwu3uZCZBZAtpgKNUi2aT4nUcViisGB025AuMtrPV3ZBrKJzJkPSxSQeMZBFYzPTuj8k5fXfSe30Wj5f6F4W8heE8em6wE4V7c1Y2dB0CH3dQgN5Bs6ZCiqfZB



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
