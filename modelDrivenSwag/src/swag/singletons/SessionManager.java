package swag.singletons;

import java.util.HashMap;
import java.util.UUID;

import javax.ejb.Singleton;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import swag.rest.session.UserSession;

@Singleton
public class SessionManager {
	
	public static final String COOKIE_NAME = "sessionId";
	private HashMap<UUID, UserSession> sessions = new HashMap<UUID, UserSession>();
	
	public UserSession createSession() {
		UserSession session = new UserSession();
		sessions.put(session.getSessionId(), session);
		return session;
	}
	
	public UserSession getSession(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		UUID sessionId = null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals(COOKIE_NAME)) {
				sessionId = UUID.fromString(cookie.getValue());
				break;
			}
		}
		
		if (!sessions.containsKey(sessionId)) return null;
		return sessions.get(sessionId);
	}
}
