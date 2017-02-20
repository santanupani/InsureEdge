package za.co.reverside.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import net.sf.jasperreports.engine.JRException;
import za.co.reverside.domain.Broker;
import za.co.reverside.domain.ClaimRequest;
import za.co.reverside.domain.Notification;
import za.co.reverside.domain.Policy;
import za.co.reverside.domain.PolicyRequest;
import za.co.reverside.domain.PolicyRequestType;
import za.co.reverside.domain.QuotationRequest;
import za.co.reverside.domain.ReleaseForm;
import za.co.reverside.repository.MessageRepository;

@Service
public class NotificationService {

	@Autowired
	private MessageRepository messageRepository;
	
	@Autowired
	private DocumentService documentService;
	
	private Notification notification;

	@Value("${reverside.application.hostname}")
	private String hostname;

	@Value("${reverside.application.port}")
	private String port;

	public void sendNotificationForNewQuotationRequest(QuotationRequest quotationRequest, Broker broker) {
		String to = broker.getEmail();
//                String cc = "obertvdw@gmail.com";
//                String cc = "binod.sethi@gmail.com";
		String subject = "New Quotation Request";

		String message = String.format(
				"Ref : %s" + "<br>"
						+ "Click the link below to view quotation request : " + "<br>"
						+ "http://%s:%s/reverside/broker.html#/quotation-requests/%s" + "<br>"
                                                + "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Reverside Underwritting" /*+ "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Reverside Underwritting" + "<br>"
                                                +"The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Reverside Underwritting," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Reverside Underwriting is a authorised Financial Service Provider." + "<br>"
                                                + "<br>"*/,    
						quotationRequest.getReference(),
						hostname,
						port,
						quotationRequest.getReference());
		Notification notification = new Notification(to, /*c,*/ subject, message);
		messageRepository.publish(notification, "q.notification");
	}
        
        public void sendNotificationForOldQuotationRequest(QuotationRequest quotationRequest, Broker broker) {
		String to = broker.getEmail();
//                String cc = "obertvdw@gmail.com";
//                String cc = "binod.sethi@gmail.com";
		String subject = "New Quotation Request";

		String message = String.format(
				"Ref : %s" + "<br>"
						+ "Click the link below to view Updated quotation request : " + "<br>"
						+ "http://%s:%s/reverside/broker.html#/requotation-requests/%s" + "<br>"
                                                + "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Reverside Underwriting" /*+ "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>"*/,    
						quotationRequest.getReference(),
						hostname,
						port,
						quotationRequest.getReference());
		Notification notification = new Notification(to, /*c,*/ subject, message);
		messageRepository.publish(notification, "q.notification");
	}

	public void sendNotificationForRejectQuotationRequest(QuotationRequest quotationRequest, String reason) {
		String to = quotationRequest.getApplicantEmail();
//                String cc = "obertvdw@gmail.com";
//             String cc = "binod.sethi@gmail.com";
		String subject = "Quotation Request Rejected";
		String message = String.format(
				"Dear %s ," +"<br>"
						+ "<br>"
						+"Your request for quotation Ref : %s has been rejected for the following reason(s)" + "<br>"
						+ "<br>"
						+"Reason(s):" + "<br>"
						+ "%s" + "<br>"
						+ "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Reverside Underwriting" /*+ "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>"
                                                + "<br>"*/,
						quotationRequest.getApplicantName(),
						quotationRequest.getReference(),
						reason);
		Notification notification = new Notification(to, /*c,*/ subject, message);
		getMessageRepository().publish(notification, "q.notification");
	}
	public void sendNotificationForRejectPolicyRequest(PolicyRequest policyRequest, String reason) {
		String to = policyRequest.getQuotation().getQuotationRequest().getApplicantEmail();
//                String cc = "obertvdw@gmail.com";
 //             String cc = "binod.sethi@gmail.com";
		String subject = "Policy Request Rejected";
		String message = String.format(
				"Dear %s ," +"<br>"
						+ "<br>"
						+"Your policy request for quotation Ref : %s has been rejected for the following reason(s)" + "<br>"
						+ "<br>"
						+"Reason(s):" + "<br>"
						+ "%s" + "<br>"
						+ "<br>"
						+ "<br>"+ "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Reverside Underwriting" /*+ "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>"*/,
						policyRequest.getQuotation().getQuotationRequest().getApplicantName(),
						policyRequest.getQuotation().getQuotationRequest().getReference(),
						reason);
		Notification notification = new Notification(to, /*c,*/ subject, message);
		getMessageRepository().publish(notification, "q.notification");
	}

	public void sendNotificationForAcceptQuotationRequest(QuotationRequest quotationRequest,byte[] data){
		String to = quotationRequest.getApplicantEmail();
//                String cc = "obertvdw@gmail.com";
//             String cc = "binod.sethi@gmail.com";
		String subject = "Quotation Request Accepted";
		String message = String.format(
				"Dear %s ," +"<br>"
						+ "<br>"
						+"Your request for quotation Ref : %s has been accepted" + "<br>"
						+ "<br>"
						+"Please find the attachment to view your quotation" + "<br>"
						+ "<br>"
						+ "Please click the link below to apply for a policy" + " <br>"
						+ "http://%s:%s/reverside/client.html#/quotations/%s " + " <br>"
						+ "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Reverside Underwriting" /*+ "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>"*/,   
						quotationRequest.getApplicantName(),
						quotationRequest.getReference(),
						hostname,
						port,
						quotationRequest.getReference());
		String filename = quotationRequest.getApplicantName() + "_quotation.pdf";

		Notification notification = new Notification(to, /*c,*/ subject, message, data, filename);
		getMessageRepository().publish(notification, "q.notification");
	}


	public void sendNotificationForNewPolicyRequest(PolicyRequest policyRequest, MultipartFile file, String toUnderwriterEmail,String underWriterName) throws IOException {
		String to = toUnderwriterEmail;
//                String cc = "obertvdw@gmail.com";
//                String cc = "binod.sethi@gmail.com";
		String subject = "New Policy Request";
		String message = String.format(
				"Dear " +underWriterName+ ",<br><br>"
						+ "You have a new Policy Request for Ref. :  %s\nClick the link below to view policy request details: " + "<br>"
						+ "http://%s:%s/reverside/underwritter.html#/policy-requests/%s<br>"
                                                + "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Reverside Underwriting" /*+ "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>"*/,
						policyRequest.getQuotation().getQuotationRequest().getReference(),
						hostname,
						port,
						policyRequest.getQuotation().getQuotationRequest().getReference());
		setNotification(new Notification(to, /*cc,*/ subject, message, file.getBytes(), "bankstatement.pdf"));
		//        		try {
		//        			MailRepository mailRepository = new MailRepository();
		//        			mailRepository.send(notification);
		//        		} catch (Exception e) {
		//        			e.printStackTrace();
		//        		}
		getMessageRepository().publish(getNotification(), "q.notification");     
	}

	
	public void sendNotificationForRequestPolicyApproval(Policy policy, String managerEmail){

		String subject = "Policy Approval Request";
//                String cc = "obertvdw@gmail.com";
 //               String cc = "binod.sethi@gmail.com";
		String message = String.format("Dear Manager,<br> <br>"
				+ "You have a new policy approval request." + "<br>"
				+"<br>Policy Approval request Ref No : %s\n\n click the link below to view approval request" + "<br>"
				+ "http://%s:%s/reverside/manager.html#/policy/%s/approval"
                                + "Trust you find the above in order." + "<br>" 
                                + "Kind Regards," + "<br>"
                                + "On behalf of Reverside Underwriting" /*+ "<br>"
                                + "<br>" 
                                + "<br>"
                                + "<img src=\"cid:image\">" + "<br><br>"
                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                + "<br>"*/,
				policy.getReference(),
				hostname,
				port,
				policy.getReference());

		setNotification(new Notification(managerEmail, /*cc,*/ subject, message));
		getMessageRepository().publish(getNotification(), "q.notification");  
	}
	
	public void sendNotificationForApprovalToUnderwritter(Policy policy,String underwritterEmail) {
            
//		String cc = "obertvdw@gmail.com";
//                String cc = "binod.sethi@gmail.com";
		String subject = "Policy "+policy.getReference()+" Approved.";

		String message = String.format("Dear Underwriter,<br> <br>"
				+ "This is to notify you that policy request for temporary policy ref: %s" + 
				" has been approved and the policy is currently active.<br><br> click the link below to view approved policy" + "<br>"
				+ "http://%s:%s/reverside/underwritter.html#/policy/%s<br>"
                                + "Trust you find the above in order." + "<br>" 
                                + "Kind Regards," + "<br>"
                                + "On behalf of Reverside Underwriting" /*+ "<br>"
                                + "<br>" 
                                + "<br>"
                                + "<img src=\"cid:image\">" + "<br><br>"
                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                + "<br>"*/,
				policy.getReference(),
				hostname,
				port,
				policy.getReference());
		Notification notification = new Notification(underwritterEmail, /*cc,*/ subject, message);
		messageRepository.publish(notification, "q.notification");
	}
	
	public void sendNotificationForApprovalToBroker(Policy policy, String brokerEmail) throws IOException, JRException {
            
//		String cc = "obertvdw@gmail.com";
//               String cc = "binod.sethi@gmail.com";
		String subject = "Policy Schedule for Client "+policy.getClient().getClientName();
		String message = String.format("Dear Broker,<br> <br>"
						+ "This is to notify you that policy request for client: %s"+
						" has been created. Attached please find the Policy Schedule for the client."
						+ /*"Please note that the client can view the Policy Details by clicking on the link below" + "<br>"
						+ "http://%s:%s/reverside/client-admin.html#/clients/%s<br>"
                                                +*/ "<br>Kind Regards,"+ "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Reverside Underwriting" /*+ "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>"*/,
						policy.getClient().getClientName()
						/*hostname,
						port,
						policy.getClient().getId()*/);
		
		if(policy.getProductName().equalsIgnoreCase("Auto Insurance")){
		setNotification(new Notification(brokerEmail, /*cc,*/ subject, message, documentService.autoPolicyScheduleReportPDF(policy), 
				"Policy Schedule for - "+policy.getClient().getClientName()+"-"+policy.getReference()+".pdf"));
		}else{
			setNotification(new Notification(brokerEmail, /*cc,*/ subject, message, documentService.policyScheduleReportPDF(policy), 
					"Policy Schedule for - "+policy.getClient().getClientName()+"-"+policy.getReference()+".pdf"));
		}
		getMessageRepository().publish(getNotification(), "q.notification");     
	}

	
	public void sendNotificationForApprovalToCustomer(Policy policy, String custEmail) throws IOException, JRException {
       //String to = quotationRequest.getApplicantEmail(); 
       
//		String cc = "obertvdw@gmail.com";
//               String cc = "binod.sethi@gmail.com";
		String subject = "Policy Schedule for Client "+policy.getClient().getClientName();
		String message = String.format("Dear %s,<br> <br>"
						+ "This is to notify you that your policy request has been created."
						+ " Attached please find the Policy Schedule with re:%s"
						+ "Please note that you can view the Policy Details by clicking on the link below" + "<br>"
						+ "http://%s:%s/reverside/client-admin.html#/clients/%s<br>"
                                                + "<br>Kind Regards,"+ "Trust you find the above in order." + "<br>" 
                                                + "Kind Regards," + "<br>"
                                                + "On behalf of Reverside Underwriting" /*+ "<br>"
                                                + "<br>" 
                                                + "<br>"
                                                + "<img src=\"cid:image\">" + "<br><br>"
                                                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                                                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                                                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                                                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                                                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                                                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                                                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                                                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                                                + "<br>"*/,
						policy.getClient().getClientName(),
						policy.getReference(),
						hostname,
						port,
						policy.getClient().getId());
		if(policy.getProductName().equalsIgnoreCase("Auto Insurance")){
		setNotification(new Notification(custEmail, /*cc,*/ subject, message, documentService.autoPolicyScheduleReportPDF(policy), 
				"Policy Schedule for - "+policy.getClient().getClientName()+"-"+policy.getReference()+".pdf"));
		}else{
		setNotification(new Notification(custEmail, /*cc,*/ subject, message, documentService.policyScheduleReportPDF(policy), 
				"Policy Schedule for - "+policy.getClient().getClientName()+"-"+policy.getReference()+".pdf"));	
		}
		getMessageRepository().publish(getNotification(), "q.notification");     
	}

    public void sendNotificationForDeclineClaimRequest(ClaimRequest claimRequest, String reason) {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
//        String cc = "obertvdw@gmail.com";
//        String cc = "binod.sethi@gmail.com";
        String subject = "Claim Request Declined";
        String message = String.format(
                "Dear %s ," + "<br>"
                + "<br>"
                + "Your request for Claim No : %s has been rejected for the following reason(s)" + "<br>"
                + "<br>"
                + "Reason(s):" + "<br>"
                + "%s" + "<br>"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Reverside Underwriting" /*+ "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br><br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>"*/,
                claimRequest.getPolicy().getClient().getClientName(),
                claimRequest.getClaimNumber(),
                reason);
        Notification notification = new Notification(to, /*cc,*/ subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }


    public void sendNotificationForRequestApprovalForClaimRequest(ClaimRequest claimRequest) {
        String to = "broker@reverside.co.za";
//        String cc = "obertvdw@gmail.com";
//        String cc = "binod.sethi@gmail.com"; 
        String subject = "Claim Request Provisionally Approval by Claim Admin";
        String message = String.format(
                "Dear Broker ," + "<br>"
                + "<br>"
                + "Claim request for Claim No : %s has been provisionally approved by Claim Admin" + "<br>"
                + "<br>"
                + "Please click the link below to view the claim approved by Claim's Department" + " <br>"
                + "<br>"       
                + "http://%s:%s/reverside/broker.html#/claimRequests/%s " + " <br>"
                + "<br>"
                /*+ "Susan Atto"*/
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Reverside Underwriting" /*+ "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>"*/,
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, /*cc,*/ subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }
    public void sendNotificationForRequestingPendingDocuments(ClaimRequest claimRequest) {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
        
//        String cc = "obertvdw@gmail.com";
//        String cc = "binod.sethi@gmail.com";
        String subject = "Claim Documents Pending";
        String message = String.format(
                "Dear %s ," + "<br>"
                + "<br>"
                + "Please upload claim supporting documents for Claim Number : %s , so that your claim can be processed" + "<br>"
                + "<br>"
                + "Please click the link below to finish your claim" + " <br>"
                + "http://%s:%s/reverside/client-admin.html#/claim-requests/%s/claim " + " <br>"
                + "<br>"
                /*+ "Susan Atto"*/
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Reverside Underwriting" /*+ "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>"*/,
                claimRequest.getPolicy().getClient().getClientName(),
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, /*cc,*/ subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }

    public void sendNotificationForApproveClaimRequest(ClaimRequest claimRequest) {
        //String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
        String to = "manager@reverside.co.za";
//        String cc = "obertvdw@gmail.com";
//        String cc = "binod.sethi@gmail.com";
        String subject = "Claim Request Approved by Broker";
        String message = String.format(
                "Dear Manager ," + "<br>"
                + "<br>"
                + "Claim request for Claim No : %s has been approved by Broker" + "<br>"
                + "<br>"
                + "Please click the link below to accept the approved claim" + " <br>"
                + "http://%s:%s/reverside/manager.html#/claimRequests/%s " + " <br>"                
                + "<br>"
                /*+ "Hentie Synder"*/
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Reverside Underwriting" /*+ "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>"*/,
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, /*cc,*/ subject, message);
        getMessageRepository().publish(notification, "q.notification");
    }
    
    public void sendNotificationForAcceptedClaimRequestCustomer(ClaimRequest claimRequest, ReleaseForm releaseForm) throws IOException, JRException {
        String to = claimRequest.getPolicy().getClient().getContacts().getEmail();
//        String cc = "obertvdw@gmail.com";
//        String cc = "binod.sethi@gmail.com";
        String subject = "Claim Request Approved by Manager";
        String message = String.format(
                "Dear %s ," + "<br>"
               + "<br>"
                + "Your claim request for Claim No : %s has been approved by Manager" + "<br>"
                + "<br>"
                + "Please click the link below to see details" + " <br>"
                + "http://%s:%s/reverside/client-admin.html#/clients/%s " + " <br>"                 
                + "<br>"
               /* + "Hentie Synder"*/
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Reverside Underwriting" /*+ "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>"*/,
                claimRequest.getPolicy().getClient().getClientName(),
                claimRequest.getClaimNumber(),                
                hostname,
                port,
                claimRequest.getPolicy().getClient().getId());
        Notification notification = new Notification(to, /*cc,*/ subject, message, documentService.generateReleaseFormPDF(releaseForm),
        		"Claim Release Form for - "+claimRequest.getPolicy().getClient().getClientName()+"-"+releaseForm.getClaimNumber()+".pdf");
        getMessageRepository().publish(notification, "q.notification");
    }
    
    public void sendNotificationForAcceptedClaimRequestBroker(ClaimRequest claimRequest, ReleaseForm releaseForm) throws IOException, JRException {
        String to = "broker@reverside.co.za";
        String subject = "Claim Request Accepted by Manager for "+claimRequest.getPolicy().getClient().getClientName();
        String message = String.format(
                "Dear Broker ," + "<br>"
               + "<br>"
                + "Claim request for Claim No : %s has been accepted by Manager" + "<br>"
                + "<br>"
                + "Please find the claim release form" + " <br>"                                
                + "<br>"             
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Reverside Underwriting",
                
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, /*cc,*/ subject, message, documentService.generateReleaseFormPDF(releaseForm),
        		"Claim Release Form for - "+claimRequest.getPolicy().getClient().getClientName()+"-"+releaseForm.getClaimNumber()+".pdf");
        getMessageRepository().publish(notification, "q.notification");
    }

    
    public void sendNotificationForAcceptedClaimRequestClaimAdmin(ClaimRequest claimRequest, ReleaseForm releaseForm) throws IOException, JRException {
        String to = "claim@reverside.co.za";
        String subject = "Claim Request Accepted by Manager for "+claimRequest.getPolicy().getClient().getClientName();
        String message = String.format(
                "Dear Claim Admin ," + "<br>"
               + "<br>"
                + "Claim request for Claim No : %s has been accepted by Manager" + "<br>"
                + "<br>"
                + "Please find the claim release form" + " <br>"                                
                + "<br>"             
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Reverside Underwriting",
                
                claimRequest.getClaimNumber());
        Notification notification = new Notification(to, /*cc,*/ subject, message, documentService.generateReleaseFormPDF(releaseForm),
        		"Claim Release Form for - "+claimRequest.getPolicy().getClient().getClientName()+"-"+releaseForm.getClaimNumber()+".pdf");
        getMessageRepository().publish(notification, "q.notification");
    }
    

    public void sendNotificationForNewClaimRequest(ClaimRequest claimRequest, String claimsEmail, String claimsName) {

        String to = claimsEmail;
//        String cc = "obertvdw@gmail.com";
//        String cc = "binod.sethi@gmail.com";
        String subject = "New Claim Request";
        String message = String.format("Dear " + claimsName + ",<br> <br>"
                + "You have a new Claim Request For Claim No : %s\n click the link below to view claim request" + "<br>"
                + "http://%s:%s/reverside/claim-admin.html#/claim-requests/%s<br>"
                + "<br>"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Reverside Underwriting" /*+ "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>"*/,
                claimRequest.getClaimNumber(),
                hostname,
                port,
                claimRequest.getClaimNumber());

        setNotification(new Notification(to, /*cc,*/ subject, message));
        getMessageRepository().publish(getNotification(), "q.notification");
    }

    public void sendNotificationForNewGenericPolicyRequest(PolicyRequestType policyRequestType, String claimsEmail) {

        String to = claimsEmail;
//        String cc = "obertvdw@gmail.com";
//        String cc = "binod.sethi@gmail.com";
        String subject = "New Cancellation Policy Request";
        String message = String.format("Dear Underwriter,<br> <br>"
                + "You have a new POLICY " + policyRequestType.getRequestType().getRequestType()
                + " REQUEST For Policy [" + policyRequestType.getPolicy().getReference() + "]. <br> Policy request type Ref No : %s<br><br> click the link below to view claim request for Cancellation" + "<br>"
                + "http://%s:%s/reverside/claim-admin.html#/claim-requests/%s<br>"
                + "<br>"
                + "Trust you find the above in order." + "<br>" 
                + "Kind Regards," + "<br>"
                + "On behalf of Reverside Underwriting" /*+ "<br>"
                + "<br>" 
                + "<br>"
                + "<img src=\"cid:image\">" + "<br>"
                +"NOTE: This e-mail message and all attachments thereto contain confidential information intended for a specific addressee and purpose." + "<br>"
                + " If you are not the addressee (a) you may not disclose, copy, distribute or take any action based on the contents hereof; (b) kindly inform the sender" + "<br>"
                +"immediately and destroy all copies thereof. Any copying, publication or disclosure of this message, or part thereof, in any form whatsoever, "+ "<br>"
                + "without the sender's express written consent, is prohibited. No opinion expressed or implied by the sender necessarily constitutes the opinion of Polygon" + "<br>"
                +"Underwriting Agency (Pty) Ltd. The views and/or representations contained in this e-mail and/or attachment(s), whether expressed or implied, "+ "<br>"
                + "are those of the sender only, unless the sender expressly states them to be the views and/or representations of Polygon Underwriting Agency (Pty) Ltd," + "<br>"
                +"who the sender shall state to represent. This message does not constitute a guarantee or proof of the facts mentioned therein." + "<br>"
                + " Polygon Underwriting Agency (Pty) Ltd is a authorised Financial Service Provider." + "<br>"
                + "<br>"*/,
                policyRequestType.getReference(),
                hostname,
                port,
                policyRequestType.getReference());

        setNotification(new Notification(to, /*cc,*/ subject, message));
        getMessageRepository().publish(getNotification(), "q.notification");
    }

    public MessageRepository getMessageRepository() {
        return messageRepository;
    }

    public void setMessageRepository(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }
}
