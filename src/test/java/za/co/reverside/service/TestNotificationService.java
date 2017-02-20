package za.co.reverside.service;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import za.co.reverside.domain.PolicyRequest;
import za.co.reverside.domain.Quotation;
import za.co.reverside.domain.QuotationOption;
import za.co.reverside.domain.QuotationRequest;
import za.co.reverside.repository.MessageRepository;
import za.co.reverside.service.NotificationService;

@RunWith(MockitoJUnitRunner.class)
public class TestNotificationService {
	
	//Test email notification for policy request
    @Test
    public void testSendPolicyRequestNotification(){
    	String underwriterToEmailAddress= "lkonaite@gmail.com";
    	String underwriterName = "Underwriter Name";
    	
    	QuotationOption selectedOption = new QuotationOption();
    	selectedOption.setId(1l);
    	selectedOption.setLocation("Randburg");
    	selectedOption.setCommodity("Cash and Valuables");
    	selectedOption.setCover("Cash");
    	selectedOption.setExcess("2000.00");
    	
    	List<QuotationOption> quotationOptions = new ArrayList<QuotationOption>();
    	quotationOptions.add(selectedOption);
    	
    	QuotationRequest quotationRequest = new QuotationRequest();
    	quotationRequest.setReference("5a3444b9-849a-473a-b662-65d06a11c117");
    	quotationRequest.setApplicantName("Client Requesting Policy");
    	quotationRequest.setCompanyName("My Company to Run");
    	
    	Quotation quotation = new Quotation();
    	quotation.setId(1l);
    	quotation.setQuotationOptions(quotationOptions);
    	quotation.setQuotationRequest(quotationRequest);
    	
    	PolicyRequest policyRequest = new PolicyRequest();
    	policyRequest.setAccountNumber("123123");
    	policyRequest.setAccountHolder("My Company to Run");
    	policyRequest.setQuotation(quotation); // Set new policy's quotation that requested the quotation
    	
   
    	NotificationService notificationService = new NotificationService();
    	
    	MessageRepository messageRepository = mock(MessageRepository.class);
    	
    	notificationService.setMessageRepository(messageRepository);
    	Assert.assertNotNull(notificationService.getMessageRepository());
    	
    	//notificationService.sendNotificationForNewPolicyRequest(policyRequest, underwriterToEmailAddress,underwriterName);
    	
    	//verify(messageRepository,times(1)).publish(notificationService.getNotification(), "q.notification");
    	
    }

}
