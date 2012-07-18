package com.readability.client;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

/**
 * @author yongboy
 * @time 2012-7-10
 * @version 1.0
 */
public class ReadabilityApi extends DefaultApi10a {
	public String getRequestTokenEndpoint() {
		return ReadabilityConstants.requestTokenUrl;
	}

	public String getAccessTokenEndpoint() {
		return ReadabilityConstants.accessTokenUrl;
	}

	public String getAuthorizationUrl(Token requestToken) {
		return String.format(ReadabilityConstants.authorizationUrlBase
				+ "?oauth_token=%s", new Object[] { requestToken.getToken() });
	}
}
