package com.frontierwholesales.core.workflow;


import java.util.List;

import javax.jcr.RepositoryException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Service;
import org.apache.felix.scr.annotations.Properties;
import org.apache.felix.scr.annotations.Property;
import org.apache.felix.scr.annotations.Reference;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.commons.osgi.PropertiesUtil;
import org.osgi.framework.Constants;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.HistoryItem;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.day.cq.mailer.MessageGateway;
import com.day.cq.mailer.MessageGatewayService;

import org.apache.commons.mail.Email;
import org.apache.commons.mail.SimpleEmail;

//This is a component so it can provide or consume services
@Component
@Service
@Properties({
  @Property(name = Constants.SERVICE_DESCRIPTION, value = "Frontier Publication Request Email Step."),
  @Property(name = Constants.SERVICE_VENDOR, value = "Adobe"),
  @Property(name = "process.label", value = "Frontier Publication Request Email Step.") })
public class CustomStepForApproval implements WorkflowProcess {

  protected final Logger log = LoggerFactory.getLogger(this.getClass());

  @Reference
 private MessageGatewayService messageGatewayService;

  public void execute(WorkItem workitem, WorkflowSession wfsession,
   MetaDataMap metaDataMap) throws WorkflowException {
  
  ResourceResolver resolver = wfsession.adaptTo(ResourceResolver.class);
  UserManager userManager = resolver.adaptTo(UserManager.class);
  
  
  Workflow workflow = workitem.getWorkflow();
  String payload = (String) workitem.getWorkflowData().getPayload();
  String initiator = workitem.getWorkflow().getInitiator();
  
  Authorizable authorizable = null;
  String userEmail = null;
  try {
   authorizable = userManager.getAuthorizable(initiator);
  } catch (RepositoryException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  }
        try {
    userEmail = PropertiesUtil.toString(authorizable.getProperty("profile/email"), "");
  } catch (RepositoryException e1) {
   // TODO Auto-generated catch block
   e1.printStackTrace();
  }

   String temp = null;
  List<HistoryItem> list = wfsession.getHistory(workflow);
  
   for(int index = list.size()-1; index >=1; index--){
    HistoryItem previous = list.get(index);
    temp = (String) previous.getWorkItem().getMetaDataMap().get("name");
   }
   
  try {

    MessageGateway<Email> messageGateway;

    Email email = new SimpleEmail();

    String emailToRecipients = userEmail;
   //String emailCcRecipients = "abc@gmail.com";

    email.addTo(emailToRecipients);
   //email.addCc(emailCcRecipients);
   email.setSubject("Frontier Wholesale Page Publication Request");
   email.setFrom("adamyocum@gmail.com");
   
   if(temp .equals("approve")){
    email.setMsg("This message is to inform you that the Frontier Wholesale content has been approved and activated which path is = "+ payload);
   }else{
    email.setMsg("This message is to inform you that the Frontier Wholesale content has been Rejected Please modify it which path is = "+ payload);
   }

    // Inject a MessageGateway Service and send the message
   messageGateway = messageGatewayService.getGateway(Email.class);

    // Check the logs to see that messageGateway is not null
   messageGateway.send((Email) email);
  }

   catch (Exception e) {
   e.printStackTrace();
  }
 }

}