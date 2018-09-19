package com.frontierwholesales.core.magento.services.servlets;

import java.io.IOException;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frontierwholesales.core.magento.services.EmailConnector;

@SuppressWarnings("serial")

@Component(immediate = true,service=Servlet.class,
property={
        Constants.SERVICE_DESCRIPTION + "=FrontierWholesales Email Contact Servlet",
        "sling.servlet.methods=" + HttpConstants.METHOD_GET,
       "sling.servlet.selectors=data",
       "sling.servlet.paths=/services/contact",
        "sling.servlet.extensions=html"      
        
})

public class FrontierWholesalesEmailContactServlet extends SlingAllMethodsServlet{

	private static final Logger log = LoggerFactory.getLogger(FrontierWholesalesEmailContactServlet.class);
	private EmailConnector emailConnector = new EmailConnector();
	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		log.debug("doGet Method Start");
		String name = request.getParameter("name");
		
		String fromEmail = request.getParameter("email");
		
		String phoneNo = request.getParameter("phoneno");
		
		String subject = request.getParameter("subject");
		
		String message = request.getParameter("message");
		
		Properties properties = System.getProperties();
		String toEmail = emailConnector.getToAddress();
		properties.setProperty("mail.smtp.host", emailConnector.getHostName());
	    properties.setProperty("mail.smtp.user",emailConnector.getSmtpUser());
	    properties.setProperty("mail.smtp.port", emailConnector.getSmtpPort());
	    properties.setProperty("mail.smtp.auth", "true");
	    properties.setProperty("mail.imap.ssl.enable", "true");
	    properties.put("mail.smtp.starttls.enable", "true");
	    
	    Authenticator auth = new Authenticator() {
			//override the getPasswordAuthentication method
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailConnector.getSmtpUser(), emailConnector.getPassword());
			}
		};
	    
	    Session session = Session.getInstance(properties,auth);
	    try {
			sendEmail(session,name,fromEmail,toEmail,subject,message,phoneNo);
			
			response.getOutputStream().print("Success");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.debug("error "+e.getMessage());
			response.getOutputStream().print("Error "+e.getMessage());
		}
	    log.debug("doGet Method end");
	}
	
	private static void sendEmail(Session session, String name,String fromEmail,String toEmail, String subject, String body,String phone) throws Exception{
			MimeMessage msg = new MimeMessage(session);
			log.debug("sendEmail Method Start");
	      //set message headers
	      msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	      msg.addHeader("format", "flowed");
	      msg.addHeader("Content-Transfer-Encoding", "8bit");
	      log.debug("From email is "+fromEmail);
	      msg.setFrom(new InternetAddress(fromEmail, name));

	      msg.setReplyTo(InternetAddress.parse(toEmail, false));

	      msg.setSubject(subject, "UTF-8");

	      msg.setContent(constructBodyMessage(name,fromEmail,phone,subject,body), "text/HTML; charset=UTF-8");

	      msg.setSentDate(new Date());

	      msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
	      log.debug("Message is ready");
	      Transport.send(msg);  
	      log.debug("sendEmail Method End");

	}
	
	private static String constructBodyMessage(String name,String email,String phone,String reason,String msg) {
		StringBuilder builder = new StringBuilder();
		builder.append("<p>Name: ").append(name).append("</p>");
		builder.append("<p>Email: ").append(email).append("</p>");
		if(phone !=null && !phone.equals("")) {
		builder.append("<p>Phone Number: ").append(phone).append("</p>");
		}
		builder.append("<p>Reason: ").append(reason).append("</p>");
		builder.append("<p>Message: ").append(msg).append("</p>");
		return builder.toString();
		
	}
}
