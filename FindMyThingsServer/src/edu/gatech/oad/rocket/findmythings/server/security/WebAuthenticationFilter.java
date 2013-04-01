package edu.gatech.oad.rocket.findmythings.server.security;

import java.util.Collection;
import java.util.Map;
import java.util.logging.Logger;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

import com.google.common.collect.Maps;

import edu.gatech.oad.rocket.findmythings.server.util.Messages;
import edu.gatech.oad.rocket.findmythings.server.util.Parameters;

public class WebAuthenticationFilter extends FormAuthenticationFilter {

	static final Logger LOGGER = Logger.getLogger(WebAuthenticationFilter.class.getName());

	public WebAuthenticationFilter() {
		super();
		setUsernameParam(Parameters.USERNAME);
		setPasswordParam(Parameters.PASSWORD);
		setRememberMeParam(Parameters.REMEMBER_ME);
	}

	@Override
	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		request.setAttribute(Parameters.FAILURE_REASON, Messages.Login.getMessage(ae));
	}

	@Override
	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        AuthenticationToken token = createToken(request, response);
        Subject subject = SecurityUtils.getSubject();
		Session originalSession = subject.getSession();

        Map<Object, Object> attributes = Maps.newLinkedHashMap();
        Collection<Object> keys = originalSession.getAttributeKeys();
        for(Object key : keys) {
            Object value = originalSession.getAttribute(key);
            if (value != null) {
                attributes.put(key, value);
            }
        }
        originalSession.stop();

        try {
		subject.login(token);

		Session newSession = subject.getSession();
            for(Object key : attributes.keySet() ) {
                newSession.setAttribute(key, attributes.get(key));
            }

            LOGGER.fine("Creating a new instance of DatastoreRealm");

            return onLoginSuccess(token, subject, request, response);
        } catch (AuthenticationException e) {
            LOGGER.fine("Failed log in.");
            setFailureAttribute(request, e);
		return onLoginFailure(token, e, request, response);
        }
	}

}