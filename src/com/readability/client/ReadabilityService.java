package com.readability.client;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import com.google.gson.Gson;

/**
 * @author yongboy
 * @time 2012-7-10
 * @version 1.0
 */
public class ReadabilityService {
	private static OAuthService service;

	protected static OAuthService getService() {
		return service;
	}

	protected static void setReadabilityService(OAuthService service) {
		ReadabilityService.service = service;
	}

	public static String doGet(String url, Token token) {
		OAuthRequest req = new OAuthRequest(Verb.GET,
				ReadabilityConstants.urlBase + url);
		getService().signRequest(token, req);
		Response res = req.send();
		return res.getBody();
	}

	public static String doGet(String url, HttpServletRequest request) {
		OAuthRequest req = new OAuthRequest(Verb.GET,
				ReadabilityConstants.urlBase + url);
		Token token = (Token) request.getSession().getAttribute(
				ReadabilityConstants.PREFIX);
		getService().signRequest(token, req);
		Response res = req.send();
		return res.getBody();
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> doGetMap(String url,
			HttpServletRequest request) {
		Gson gson = new Gson();
		return gson.fromJson(doGet(url, request), Map.class);
	}

	@SuppressWarnings("unchecked")
	public static Map<String, Object> doGetMap(String url, Token token) {
		Gson gson = new Gson();
		return gson.fromJson(doGet(url, token), Map.class);
	}
}