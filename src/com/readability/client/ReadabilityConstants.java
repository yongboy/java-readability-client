package com.readability.client;

/**
 * 
 * @author yongboy
 * 
 */
public final class ReadabilityConstants {
	public static final String PREFIX = "readability_";
	
	protected static final String consumerKey = "your key here";
	protected static final String consumerSecret = "your secret here";

	protected static final String urlBase = "https://www.readability.com/api/rest/v1";

	protected static final String requestTokenUrl = urlBase + "/oauth/request_token/";
	protected static final String accessTokenUrl = urlBase + "/oauth/access_token/";
	protected static final String authorizationUrlBase = urlBase
			+ "/oauth/authorize/";

	protected static final String callbackUrl = "readability?action=return";
}
