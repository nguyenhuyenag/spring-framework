package oauth2.service;

import oauth2.model.AccessToken;

public interface OAuth2Service {

    AccessToken getAccessToken(String clientId, String clientSecret, String redirectUri, String code, String state);
}
