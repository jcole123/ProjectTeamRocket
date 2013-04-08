package edu.gatech.oad.rocket.findmythings.server.api;

import com.google.inject.Singleton;
import edu.gatech.oad.rocket.findmythings.server.util.Messages;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Singleton
public class ForgotEndpoint extends RegisterEndpoint {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3131265945592171117L;

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		try {
			String email = WebUtils.getCleanParam(request, getUsernameParam());

			if (!emailIsValid(email)) {
				sendError(request, response, Messages.Register.BAD_EMAIL_ADDRESS);
				return;
			}

			if (!memberExistsWithEmail(email)) {
				sendError(request, response, Messages.Register.NO_SUCH_MEMBER);
				return;
			}

			mailAuthenticationTokenSendOK(request, response, email, true);
		} catch (Exception e) {
			sendError(request, response, Messages.Register.INVALID_DATA);
		}
	}

}
