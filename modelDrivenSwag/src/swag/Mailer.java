package swag;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;
import java.lang.Boolean;

public class Mailer {
	
	private static final String SMTP_HOST_NAME = "smtp.gmail.com";
	private static final String SMTP_HOST_PORT = "465";
	private static final String SMTP_AUTH_USER = "p.raich@aon.at";
	private static final String SMTP_AUTH_PWD  = "whattheduck";
	private static final boolean SMTP_AUTH_SET = true;
	private static final boolean SMTP_TLS_SET = true;

	
	public void postMail( String recipients[ ], String subject, String message , String from) throws MessagingException
	{

	    boolean debug = false;
	    
		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", new Boolean(SMTP_AUTH_SET).toString());
		props.put("mail.smtp.user", SMTP_AUTH_USER);
		props.put("mail.smtp.port", SMTP_HOST_PORT);
		props.put("mail.smtp.starttls.enable",SMTP_TLS_SET);
		//props.put("mail.smtp.debug", "true");
		props.put("mail.smtp.socketFactory.port", SMTP_HOST_PORT);
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.socketFactory.fallback", "false");

		Session session = null;
	    if (SMTP_AUTH_SET) {
	    	Authenticator auth = new SMTPAuthenticator();
	    	session = Session.getInstance(props, auth);
	    } else {
	    	session = Session.getInstance(props);
		}

		session.setDebug(debug);

	    // create a message
	    Message msg = new MimeMessage(session);

	    // set the from and to address
	    InternetAddress addressFrom = new InternetAddress(from);
	    msg.setFrom(addressFrom);

	    InternetAddress[] addressTo = new InternetAddress[recipients.length];
	    for (int i = 0; i < recipients.length; i++)
	    {
	        addressTo[i] = new InternetAddress(recipients[i]);
	    }
	    msg.setRecipients(Message.RecipientType.TO, addressTo);


	    // Setting the Subject and Content Type
	    msg.setSubject(subject);
	    msg.setContent(message, "text/plain");
	    Transport.send(msg);
	}
	
	/**
	* SimpleAuthenticator is used to do simple authentication
	* when the SMTP server requires it.
	*/
	private class SMTPAuthenticator extends javax.mail.Authenticator
	{

		public PasswordAuthentication getPasswordAuthentication()
	    {
	        String username = SMTP_AUTH_USER;
	        String password = SMTP_AUTH_PWD;
	        return new PasswordAuthentication(username, password);
	    }
	}
}