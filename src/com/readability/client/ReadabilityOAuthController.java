package com.readability.client;

import static com.readability.client.ReadabilityConstants.PREFIX;
import static com.readability.client.ReadabilityConstants.authorizationUrlBase;
import static com.readability.client.ReadabilityConstants.callbackUrl;
import static com.readability.client.ReadabilityConstants.consumerKey;
import static com.readability.client.ReadabilityConstants.consumerSecret;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.Token;
import org.scribe.model.Verifier;
import org.scribe.oauth.OAuthService;

/**
 * 
 * @author yongboy
 * @time 2012-7-18
 * @version 1.0
 */
@WebServlet(urlPatterns = "/readability")
public class ReadabilityOAuthController extends HttpServlet {
	private static final long serialVersionUID = 3L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		if (request.getSession().getAttribute(PREFIX) != null) {
			response.sendRedirect("readability/choose");
			return;
		}

		HttpSession session = request.getSession();
		String requestToken = (String) session.getAttribute(PREFIX
				+ "requestToken");
		String requestTokenSecret = (String) session.getAttribute(PREFIX
				+ "requestTokenSecret");

		String action = request.getParameter("action");

		OAuthService service = ReadabilityService.getService();
		if (service == null) {
			String thisUrl = request.getRequestURL().toString();
			String cbUrl = thisUrl.substring(0, thisUrl.lastIndexOf('/') + 1)
					+ callbackUrl;
			service = new ServiceBuilder()
					.provider(com.readability.client.ReadabilityApi.class)
					.apiKey(consumerKey).apiSecret(consumerSecret)
					.callback(cbUrl).build();

			ReadabilityService.setReadabilityService(service);
		}

		Token scribeRequestToken = service.getRequestToken();
		requestToken = scribeRequestToken.getToken();
		session.setAttribute(PREFIX + "requestToken", requestToken);
		session.setAttribute(PREFIX + "requestTokenSecret",
				scribeRequestToken.getSecret());

		if (action == null || action.equals("")) {
			String authorizationUrl = authorizationUrlBase + "?oauth_token="
					+ requestToken;
			response.sendRedirect(authorizationUrl);
			return;
		}

		requestToken = request.getParameter("oauth_token");
		String verifier = request.getParameter("oauth_verifier");

		scribeRequestToken = new Token(requestToken, requestTokenSecret);
		Verifier scribeVerifier = new Verifier(verifier);
		Token token = service
				.getAccessToken(scribeRequestToken, scribeVerifier);

		session.removeAttribute(PREFIX + "requestToken");
		session.removeAttribute(PREFIX + "requestTokenSecret");

		session.setAttribute(PREFIX, token);

		response.sendRedirect("index.jsp");
	}
}