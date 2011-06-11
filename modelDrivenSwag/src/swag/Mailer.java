package swag;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.*;

public class Mailer {
	
	private static final String SMTP_HOST_NAME = "myserver.smtphost.com";
	private static final String SMTP_AUTH_USER = "myusername";
	private static final String SMTP_AUTH_PWD  = "mypwd";
	private static final boolean SMTP_AUTH_SET = true;
	
	public void postMail( String recipients[ ], String subject, String message , String from) throws MessagingException
	{

	    boolean debug = false;
	    
		//Set the host smtp address
		Properties props = new Properties();
		props.put("mail.smtp.host", SMTP_HOST_NAME);
		props.put("mail.smtp.auth", SMTP_AUTH_SET);

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