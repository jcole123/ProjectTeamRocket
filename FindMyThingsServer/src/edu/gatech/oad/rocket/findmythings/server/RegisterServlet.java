package edu.gatech.oad.rocket.findmythings.server;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

import javax.inject.Singleton;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import edu.gatech.oad.rocket.findmythings.server.util.Messages;
import edu.gatech.oad.rocket.findmythings.server.util.Parameters;

@Singleton
public class RegisterServlet extends RegisterEndpoint {
	static final Logger LOGGER = Logger.getLogger(RegisterServlet.class.getName());
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -7032016092040490069L;

	@Override
	protected void sendError(HttpServletRequest request, HttpServletResponse response, Messages.Register message) {
		request.setAttribute(Parameters.FAILURE_REASON, message.toString());
		try {
			doGet(request, response);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	protected void sendOK(HttpServletRequest request, HttpServletResponse response) {
		try {
			writeDocument(response, "registerSubmitted.ftl", getParameterMap(request));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		writeDocument(response, getDefaultTemplateURI(request), getParameterMap(request));
	}

	@Override
	protected void addParametersToMap(HttpServletRequest request, Map<String, Object> params) {
		super.addParametersToMap(request, params);
		
		Object failureReason = request.getAttribute(Parameters.FAILURE_REASON);
		if (failureReason != null) {
			params.put(Parameters.FAILURE_REASON, failureReason);
		}

		params.put(Parameters.FORGOTPASSWORD, false);
	}
}
