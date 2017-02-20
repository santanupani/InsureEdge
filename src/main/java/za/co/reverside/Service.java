package za.co.reverside;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.DocumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;

import net.sf.jasperreports.engine.JRException;
import static za.co.reverside.mapper.Mapper.fromBankAccountCommandModel;
import static za.co.reverside.mapper.Mapper.fromCarrierCommandModel;
import static za.co.reverside.mapper.Mapper.fromClaimRequestCommandModel;
import static za.co.reverside.mapper.Mapper.fromClientCommandModel;
import static za.co.reverside.mapper.Mapper.fromContactCommandModel;
import static za.co.reverside.mapper.Mapper.fromEndorsePolicyCommandModel;
import static za.co.reverside.mapper.Mapper.fromHistoryCommandModel;
import static za.co.reverside.mapper.Mapper.fromLocationOptionsCommandModel;
import static za.co.reverside.mapper.Mapper.fromPolicyCreationCommandModel;
import static za.co.reverside.mapper.Mapper.fromPolicyRequestTypeCommandModel;
import static za.co.reverside.mapper.Mapper.fromQuotationRequestCommandModel;
import static za.co.reverside.mapper.Mapper.fromQuotationUpdateCommandModel;
import static za.co.reverside.mapper.Mapper.toApprovePolicyCommandModel;
import static za.co.reverside.mapper.Mapper.toBrokerQueryModel;
import static za.co.reverside.mapper.Mapper.toCarrierQueryModel;
import static za.co.reverside.mapper.Mapper.toClaimQuestionnaireQueryModel;
import static za.co.reverside.mapper.Mapper.toClaimRequest;
import static za.co.reverside.mapper.Mapper.toClaimRequestQueryModel;
import static za.co.reverside.mapper.Mapper.toClaimTypeQueryModel;
import static za.co.reverside.mapper.Mapper.toClientCommandModel;
import static za.co.reverside.mapper.Mapper.toClientQueryModel;
import static za.co.reverside.mapper.Mapper.toEndorsementQueryModel;
import static za.co.reverside.mapper.Mapper.toPolicyQueryModel;
import static za.co.reverside.mapper.Mapper.toPolicyRequest;
import static za.co.reverside.mapper.Mapper.toPolicyRequestQueryModel;
import static za.co.reverside.mapper.Mapper.toPolicyRequestTypeQueryModel;
import static za.co.reverside.mapper.Mapper.toPolicyUpdateCommandModel;
import static za.co.reverside.mapper.Mapper.toProductQueryModel;
import static za.co.reverside.mapper.Mapper.toQuestionnaireQueryModel;
import static za.co.reverside.mapper.Mapper.toQuotationQueryModel;
import static za.co.reverside.mapper.Mapper.toQuotationRequest;
import static za.co.reverside.mapper.Mapper.toQuotationRequestAuto;
import static za.co.reverside.mapper.Mapper.toQuotationRequestQueryModel;
import static za.co.reverside.mapper.Mapper.toReleaseFormCommandModel;
import static za.co.reverside.mapper.Mapper.toReleaseFormQueryModel;
import static za.co.reverside.mapper.Mapper.toRequestQuestionnaireQueryModel;
import static za.co.reverside.mapper.Mapper.toRequestTypeQueryModel;
import static za.co.reverside.mapper.Mapper.toSelectedQuotationQueryModel;
import static za.co.reverside.mapper.Mapper.toSubAgentQueryModel;
import static za.co.reverside.mapper.Mapper.toUserQueryModel;
import za.co.reverside.model.BrokerQueryModel;
import za.co.reverside.model.CarrierCommandModel;
import za.co.reverside.model.CarrierQueryModel;
import za.co.reverside.model.ClaimQuestionnaireQuery;
import za.co.reverside.model.ClaimRequestCommandModel;
import za.co.reverside.model.ClaimRequestQueryModel;
import za.co.reverside.model.ClaimTypeQueryModel;
import za.co.reverside.model.ClientQueryModel;
import za.co.reverside.model.EndorsementQueryModel;
import za.co.reverside.model.PolicyCreationCommandModel;
import za.co.reverside.model.PolicyQueryModel;
import za.co.reverside.model.PolicyRequestCommandModel;
import za.co.reverside.model.PolicyRequestQueryModel;
import za.co.reverside.model.PolicyRequestTypeCommandModel;
import za.co.reverside.model.PolicyRequestTypeQueryModel;
import za.co.reverside.model.ProductQueryModel;
import za.co.reverside.model.QuestionnaireQuery;
import za.co.reverside.model.QuotationCommandModel;
import za.co.reverside.model.QuotationQueryModel;
import za.co.reverside.model.QuotationRequestCommandModel;
import za.co.reverside.model.QuotationRequestCommandModel.LocationOptions;
import za.co.reverside.model.QuotationRequestQueryModel;
import za.co.reverside.model.ReleaseFormCommandModel;
import za.co.reverside.model.ReleaseFormQueryModel;
import za.co.reverside.model.RequestQuestionnaireQueryModel;
import za.co.reverside.model.RequestTypeQueryModel;
import za.co.reverside.model.SelectedQuotationQueryModel;
import za.co.reverside.model.SubAgentQueryModel;
import za.co.reverside.model.UserQueryModel;
import za.co.reverside.domain.Answer;
import za.co.reverside.domain.BankAccount;
import za.co.reverside.domain.Broker;
import za.co.reverside.domain.Carrier;
import za.co.reverside.domain.ClaimAnswer;
import za.co.reverside.domain.ClaimQuestionnaire;
import za.co.reverside.domain.ClaimRequest;
import za.co.reverside.domain.ClaimType;
import za.co.reverside.domain.Client;
import za.co.reverside.domain.Contact;
import za.co.reverside.domain.Endorsement;
import za.co.reverside.domain.History;
import za.co.reverside.domain.LocationOption;
import za.co.reverside.domain.Policy;
import za.co.reverside.domain.PolicyRequest;
import za.co.reverside.domain.PolicyRequestType;
import za.co.reverside.domain.Product;
import za.co.reverside.domain.Questionnaire;
import za.co.reverside.domain.Quotation;
import za.co.reverside.domain.QuotationOption;
import za.co.reverside.domain.QuotationRequest;
import za.co.reverside.domain.ReleaseForm;
import za.co.reverside.domain.RequestAnswer;
import za.co.reverside.domain.RequestQuestionnaire;
import za.co.reverside.domain.RequestType;
import za.co.reverside.domain.SubAgent;
import za.co.reverside.domain.Underwriter;
import za.co.reverside.repository.BankAccountRepository;
import za.co.reverside.repository.BrokerRepository;
import za.co.reverside.repository.CarrierRepository;
import za.co.reverside.repository.ClaimQuestionnaireRepository;
import za.co.reverside.repository.ClaimRequestQuestionnaireRepository;
import za.co.reverside.repository.ClaimRequestRepository;
import za.co.reverside.repository.ClaimTypeRepository;
import za.co.reverside.repository.ClientRepository;
import za.co.reverside.repository.ContactRepository;
import za.co.reverside.repository.EndorsementRepository;
import za.co.reverside.repository.HistoryRepository;
import za.co.reverside.repository.LocationOptionRepository;
import za.co.reverside.repository.PolicyRepository;
import za.co.reverside.repository.PolicyRequestRepository;
import za.co.reverside.repository.PolicyRequestTypeRepository;
import za.co.reverside.repository.ProductRepository;
import za.co.reverside.repository.QuestionnaireRepository;
import za.co.reverside.repository.QuotationOptionRepository;
import za.co.reverside.repository.QuotationRepository;
import za.co.reverside.repository.QuotationRequestQuestionnaireRepository;
import za.co.reverside.repository.QuotationRequestRepository;
import za.co.reverside.repository.ReleaseFormRepository;
import za.co.reverside.repository.RequestAnswersRepository;
import za.co.reverside.repository.RequestTypeRepository;
import za.co.reverside.repository.SubAgentRepository;
import za.co.reverside.repository.UnderwriterRepository;
import za.co.reverside.repository.UserRepository;
import za.co.reverside.service.DocumentService;
import za.co.reverside.service.NotificationService;

@RestController
public class Service {

    private static final Logger log = LoggerFactory.getLogger(Service.class);

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CarrierRepository carrierRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private LocationOptionRepository locationOptionRepository;

    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private BrokerRepository brokerRepository;

    @Autowired
    private QuotationRequestRepository quotationRequestRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private QuotationRequestQuestionnaireRepository quotationRequestQuestionnaireRepository;

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private QuotationRepository quotationRepository;

    @Autowired
    private QuotationOptionRepository quotationOptionRepository;

    @Autowired
    private PolicyRequestRepository policyRequestRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private PolicyRepository policyRepository;

    @Autowired
    private RequestTypeRepository requestTypeRepository;

    @Autowired
    private PolicyRequestTypeRepository policyRequestTypeRepository;

    @Autowired
    private DocumentService reportService;

    @Autowired
    private UnderwriterRepository underwriterRepository;

    @Autowired
    private SubAgentRepository subAgentRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ClaimTypeRepository claimTypeRepository;

    @Autowired
    private ClaimQuestionnaireRepository claimQuestionnaireRepository;

    @Autowired
    private ClaimRequestRepository claimRequestRepository;
    
    @Autowired	
    private ReleaseFormRepository releaseFormRepository;

    @Autowired
    private ClaimRequestQuestionnaireRepository claimRequestQuestionnaireRepository;

    @Autowired
    private RequestAnswersRepository requestAnswersRepository;
    
    @Autowired
    private EndorsementRepository endorsementRepository;

    @Autowired
    private DocumentService documentService;

    @RequestMapping(value = "api/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserQueryModel> findAllUsers() {
        log.info("find user");
        List<za.co.reverside.domain.User> users = userRepository.findAll();
        List<UserQueryModel> result = toUserQueryModel(users);
        log.info("found user, size:{}", result.size());
        log.info("this service to get all users");
        return result;
    }

    @RequestMapping(value = "api/products", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ProductQueryModel> findAllProducts() {
        log.info("find all products");
        List<Product> products = productRepository.findAll();
        log.info("found all products - size:{}", products.size());
        return toProductQueryModel(products);
    }

    @RequestMapping(value = "api/policy-requests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PolicyRequestQueryModel> getAllPolicyRequests() {
        log.info("Find all Policy Requests");
        List<PolicyRequest> policyRequests = policyRequestRepository.findAll();
        List<PolicyRequestQueryModel> policyRequestQueryModel = toPolicyRequestQueryModel(policyRequests);

        return policyRequestQueryModel;
    }

    /*The all the policies*/
    @RequestMapping(value = "api/policies", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PolicyQueryModel> getPolicies() {
        log.info("Find all policies");
        List<Policy> policy = policyRepository.findAll();
        List<PolicyQueryModel> policies = toPolicyQueryModel(policy);
        log.info("Number of policies returned: " + policies.size());
        return policies;
    }

    @Transactional
    @RequestMapping(value = "api/policy", method = RequestMethod.POST)
    public String createPolicy(@RequestBody PolicyCreationCommandModel policyCreationCommandModel) throws IOException, ParseException, JRException {
    	
        Underwriter underwriter = underwriterRepository.findOne(policyCreationCommandModel.getUnderwriterId());
        SubAgent subAgent = subAgentRepository.findOne(policyCreationCommandModel.getSubAgentId());
        BankAccount bankAccount = bankAccountRepository.save(fromBankAccountCommandModel(policyCreationCommandModel));
        Contact contact = contactRepository.save(fromContactCommandModel(policyCreationCommandModel));
        Client client = clientRepository.save(fromClientCommandModel(policyCreationCommandModel, contact, bankAccount));
        int lastPolicyNumber = policyRepository.findAll().size();
        Policy policy = policyRepository.save(fromPolicyCreationCommandModel(policyCreationCommandModel, client, subAgent, underwriter, contact, bankAccount, lastPolicyNumber));
        
        
        log.info("Before saving: "+policyCreationCommandModel.getProductName()+ " : "+policyCreationCommandModel.getMakeModel());
        log.info("After Saving: "+policy.getProductName()+ " : "+policy.getMakeModel());
        
        return policy.getReference();
    }

    @Transactional
    @RequestMapping(value = "api/policy-update/{reference}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PolicyQueryModel updatePolicy(@PathVariable("reference") String reference, @RequestBody PolicyQueryModel policyQueryModel) throws ParseException {
        log.info("Updating Policy with reference: " + reference);
        Policy policy = policyRepository.findByReference(reference);
        SubAgent subAgent = subAgentRepository.findOne(policyQueryModel.getSubAgent().getId());
        log.info("Product Name: " + policy.getProductName());
        Endorsement endorsement = endorsementRepository.save(fromEndorsePolicyCommandModel(policyQueryModel,policy));
        log.info("Saved endorsement: "+endorsement.getId()+ "\t"+endorsement.getEndorsementDate());
        Policy updatePolicy = policyRepository.save(toPolicyUpdateCommandModel(policyQueryModel, policy, subAgent));
        return toPolicyQueryModel(updatePolicy);
    }
    
    @Transactional
    @RequestMapping(value = "api/policy/{reference}/approval", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public PolicyQueryModel approvePolicy(@PathVariable("reference") String reference, @RequestBody PolicyQueryModel PolicyQueryModel) throws ParseException, IOException, JRException {
        log.info("Updating Policy with reference: " + reference);
        Policy policy = policyRepository.findByReference(reference);
        SubAgent subAgent = subAgentRepository.findOne(PolicyQueryModel.getSubAgent().getId());
        log.info("Product Name: " + policy.getProductName());
        Policy updatePolicy = policyRepository.save(toApprovePolicyCommandModel(PolicyQueryModel, policy, subAgent));

        
//        notificationService.sendNotificationForApprovalToBroker(updatePolicy,"quotes@polygongroup.co.za");
//        notificationService.sendNotificationForApprovalToUnderwritter(updatePolicy,"eric@polygongroup.co.za ");
        notificationService.sendNotificationForApprovalToBroker(updatePolicy,"broker@reverside.co.za");
        notificationService.sendNotificationForApprovalToUnderwritter(updatePolicy,"underwritter@reverside.co.za");
        notificationService.sendNotificationForApprovalToCustomer(updatePolicy, updatePolicy.getClient().getContact().getEmail());
        return toPolicyQueryModel(updatePolicy);
    }
    
    @RequestMapping(value = "api/policy-request-approval/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String requestPolicyApproval(@PathVariable("reference") String reference) {
        log.info("Sending email notification for policy request approval");
        Policy policy = policyRepository.findByReference(reference);
//        notificationService.sendNotificationForRequestPolicyApproval(policy, "gerhard@polygongroup.co.za");
        notificationService.sendNotificationForRequestPolicyApproval(policy, "manager@reverside.co.za");
        return "Approval Request sent successfully";
    }
    
    @RequestMapping(value = "api/endorsements/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<EndorsementQueryModel> getPolicyEndorsements(@PathVariable("reference") String reference) {
        Policy policy = policyRepository.findByReference(reference);
        List<Endorsement> endorsementList = policy.getEndorsements();
        return toEndorsementQueryModel(endorsementList,policy);
    }

    @RequestMapping(value = "api/sub-agents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<SubAgentQueryModel> getSubAgents() {
        log.info("Find all sub agents");
        List<SubAgent> subAgent = subAgentRepository.findAll();
        List<SubAgentQueryModel> subAgents = toSubAgentQueryModel(subAgent);
        return subAgents;
    }

    @Transactional
    @RequestMapping(value = "api/client/{clientId}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
    public void updateClient(@PathVariable("clientId") Long clientId, @RequestBody ClientQueryModel clientQueryModel) throws ParseException {

        Client client = clientRepository.findOne(clientId);

        clientRepository.save(toClientCommandModel(clientQueryModel, client));
    }

    @RequestMapping(value = "api/policy-schedules/{reference}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[] generatePolicySchedule(@PathVariable("reference") String reference) throws JRException, IOException {
        Policy policy = policyRepository.findByReference(reference);
        
        if(policy.getProductName().equalsIgnoreCase("Auto Insurance")){
        	
        	log.info("I am in auto Insurance");
        	
        	log.info("Make Model Name: "+policy.getMakeModel());
        	
        	return documentService.autoPolicyScheduleReportPDF(policy);
        	
        }else{
        	log.info("I am in non auto Insurance");
        return documentService.policyScheduleReportPDF(policy);
        	}

    }

    @RequestMapping(value = "api/policy-ref", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String getPolicyCounts() {
        log.info("Find all policy for reference");
        int lastPolicyNumber = policyRepository.findAll().size();
        LocalDateTime now = LocalDateTime.now();;
        return (now.getYear() + "-" + now.getMonthValue() + "" + String.format("%02d", lastPolicyNumber)).toString();
    }

    @RequestMapping(value = "api/quotation-request-pdf/{reference}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[] viewQuotationPDF(@PathVariable("reference") String reference) throws JRException, IOException {
    	
        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);          
       
        Long productID = quotationRequest.getProduct().getId();
        log.info("Product ID: "+productID);
        
        if(productID !=5){
        	Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);        
        	log.info("Quotation ref details :"+quotation);        
        	return documentService.generateQuotationPDF(quotation);
        	
        
        } else{
        	Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);        
        	log.info("Quotation ref details :"+quotationRequest);
        	return documentService.generateQuotationPDFAuto(quotation);
        }  


    }

    @RequestMapping(value = "api/release-form-pdf/{claimNumber}", method = RequestMethod.GET, produces = "application/pdf")
    public byte[] viewReleaseFormPDF(@PathVariable("claimNumber") String claimNumber) throws JRException, IOException {
        ReleaseForm releaseForm = releaseFormRepository.findByClaimNumber(claimNumber);
        
        log.info("Claim Number:"+releaseForm);
        
        return documentService.generateReleaseFormPDF(releaseForm);

    }

    @RequestMapping(value = "api/products/{productId}/questionnaires", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuestionnaireQuery> findQuestionnaires(@PathVariable("productId") Long productId) {
        log.info("find questionnaires for product - productId:{}", productId);
        List<Questionnaire> questionnaires = new ArrayList<Questionnaire>();
        Product product = productRepository.findOne(productId);
        if (product != null) {
            questionnaires = questionnaireRepository.findByProduct(product);
        }
        log.info("found questionnaires for product - productId:{}, size:{}", productId, questionnaires.size());
        List<QuestionnaireQuery> r = toQuestionnaireQueryModel(questionnaires);
        log.info("mapping done....");
        return r;
    }

    @RequestMapping(value = "api/brokers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<BrokerQueryModel> findAllBrokers() {
        log.info("find all brokers");
        List<Broker> brokers = brokerRepository.findAll();
        log.info("found all products - size:{}", brokers.size());
        return toBrokerQueryModel(brokers);
    }
    
    @RequestMapping(value = "api/professionalCarriers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CarrierQueryModel> findAllCarriers() {
        log.info("find all Professional Va;luable cariers");
        List<Carrier> carriers = carrierRepository.findAll();
        log.info("found all carriers - size:{}", carriers.size());
        return toCarrierQueryModel(carriers);
    }

    @Transactional
    @RequestMapping(value = "api/quotation-requests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
    public String createQuotationRequest(@RequestBody QuotationRequestCommandModel quotationRequestCommandModel) {
        Broker broker = brokerRepository.findOne(quotationRequestCommandModel.getBrokerId());

        Product product = productRepository.findOne(quotationRequestCommandModel.getProductId());
        
        

        QuotationRequest quotationRequest = toQuotationRequest(quotationRequestCommandModel, broker, product, quotationRequestRepository.findAll().size());
        quotationRequest = quotationRequestRepository.save(quotationRequest);
        List<Answer> quotationRequestQuestionnaires = fromQuotationRequestCommandModel(quotationRequestCommandModel, quotationRequest);
        quotationRequestQuestionnaireRepository.save(quotationRequestQuestionnaires);
        List<LocationOption> locationOptions = fromLocationOptionsCommandModel(quotationRequestCommandModel, quotationRequest);
        locationOptionRepository.save(locationOptions);
        if (quotationRequestCommandModel.getHistories() != null) {
            List<History> histories = fromHistoryCommandModel(quotationRequestCommandModel, quotationRequest);
            historyRepository.save(histories);
        }
        notificationService.sendNotificationForNewQuotationRequest(quotationRequest, broker);
        log.info("Quotation Request Created. reference : {} ", quotationRequest.getReference());
        
        return quotationRequest.getReference();
    }
    
    @Transactional
    @RequestMapping(value = "api/quotation-requests/auto", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
    public String createQuotationRequestAuto(@RequestBody QuotationRequestCommandModel quotationRequestCommandModel){
    	
    	log.info("Auto Make Model: " + quotationRequestCommandModel.getMakeModel());
    	
    	Broker broker = brokerRepository.findOne(quotationRequestCommandModel.getBrokerId());
    	log.info("AutoBroker ID :" + broker);
    	
    	
    	
    	Product product = productRepository.findOne(quotationRequestCommandModel.getProductId());    	
    	log.info("AutoProduct ID :" + product);
    	
    	QuotationRequest quotationRequest = toQuotationRequestAuto(quotationRequestCommandModel, broker, product, quotationRequestRepository.findAll().size());
    	quotationRequest = quotationRequestRepository.save(quotationRequest);  	
    	
    	notificationService.sendNotificationForNewQuotationRequest(quotationRequest, broker);
    	log.info("Quotation Request Created Successfully !");
    	return quotationRequest.getReference();
    	
    }
    

    

	@RequestMapping(value = "api/quotation-requests/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuotationRequestQueryModel getQuotationRequest(@PathVariable("reference") String reference) throws ParseException {
        log.info("find all the questions and answers inserted for a product");
        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
        log.info("find all the questions and answers inserted for a product using the reference");
        return toQuotationRequestQueryModel(quotationRequest);
    }

    @Transactional
    @RequestMapping(value = "api/quotation-requests/{reference}/reject", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
    public void rejectQuotationRequest(@PathVariable("reference") String reference, @RequestBody Map<String, String> reason) {
        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
        quotationRequest.setStatus("REJECTED");
        notificationService.sendNotificationForRejectQuotationRequest(quotationRequest, reason.get("reason"));
        quotationRequestRepository.save(quotationRequest);
        log.info("New status :" + quotationRequest.getStatus());
    }

    @Transactional
    @RequestMapping(value = "api/quotations", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewQuotation(@RequestBody QuotationCommandModel quotationCommandModel) throws DocumentException, FileNotFoundException, IOException, JRException {
        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(quotationCommandModel.getReference());
        quotationRequest.setStatus("ACCEPTED");
        Quotation quotation = fromQuotationRequestCommandModel(quotationCommandModel, quotationRequest);
        quotation = quotationRepository.save(quotation);
        log.info("Quotation Command size: " + quotation.getQuotationOptions().size());
        log.info("Quotation Created Successfully !!!");

    }
    
    @Transactional
    @RequestMapping(value = "api/professionalCarriers", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createNewCarrier(@RequestBody CarrierCommandModel carrierCommandModel) throws Exception{
        Carrier carrier = fromCarrierCommandModel(carrierCommandModel);
        if(carrierRepository.findByDescription(carrierCommandModel.getDescription()) != null) {
            throw new Exception("duplicate description found");
        }
        Carrier result =  carrierRepository.save(carrier);
    }
    
    @Transactional
    @RequestMapping(value = "api/releaseForm", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createReleaseForm(@RequestBody ReleaseFormCommandModel releaseFormCommandModel) throws DocumentException, FileNotFoundException, IOException, JRException {
       
    	 log.info("Release Form totalPayable amount!!!"+ releaseFormCommandModel.getTotalPayeble());
    	ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(releaseFormCommandModel.getClaimNumber());
        claimRequest.setStatus("ACCEPTED");        
        ReleaseForm releaseForm = toReleaseFormCommandModel(releaseFormCommandModel, claimRequest);
        releaseFormRepository.save(releaseForm);
        log.info("Release Form Created Successfully !!!");

    }
    
   

    @Transactional
    @RequestMapping(value = "api/quotation-update/{reference}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateQuotation(@PathVariable("reference") String reference, @RequestBody QuotationCommandModel quotationCommandModel) throws ParseException {
        log.info("Updating Quotation with reference: " + reference);

        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(quotationCommandModel.getReference());
        Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);
        List<QuotationOption> quotationOptions = fromQuotationUpdateCommandModel(quotationCommandModel, quotation);
        quotationOptionRepository.save(quotationOptions);
        log.info("QuotationOptions updated successfully : " + quotationOptions.size());

    }

    @RequestMapping(value = "api/quotation-submit/{reference}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void submitNewQuotation(@PathVariable("reference") String reference) throws DocumentException, FileNotFoundException, IOException, JRException {
        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);

        Long productId = quotationRequest.getProduct().getId();
        
        if(productId !=5){
        byte[] data = reportService.generateQuotationPDF(quotationRepository.findByQuotationRequest(quotationRequest));
        notificationService.sendNotificationForAcceptQuotationRequest(quotationRequest, data);

        log.info("Quotation Created Successfully !!!");
        } else{
    	
        byte[] data = reportService.generateQuotationPDFAuto(quotationRepository.findByQuotationRequest(quotationRequest));
        notificationService.sendNotificationForAcceptQuotationRequest(quotationRequest, data);
        }
        
    }
    
    
    @RequestMapping(value = "api/requotation-submit/{reference}", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void submitExitingQuotation(@PathVariable("reference") String reference, @RequestBody List<LocationOptions> locationOptions) throws IOException{
        
        for(int i=0; i< locationOptions.size();i++) {
             LocationOption locationOption = locationOptionRepository.findById(locationOptions.get(i).getId());
             locationOption.setLimit(locationOptions.get(i).getLimit());
             locationOption = locationOptionRepository.save(locationOption);

        }
        
        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
        Broker broker = quotationRequest.getBroker();
        quotationRequest.setStatus("APPLIED");
        quotationRequest = quotationRequestRepository.save(quotationRequest);
        notificationService.sendNotificationForOldQuotationRequest(quotationRequest,broker);
   
    }

    @RequestMapping(value = "api/quotations/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public QuotationQueryModel getQuotation(@PathVariable("reference") String reference) {

        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);

        if (quotationRequest.getStatus().equals("ACCEPTED")) {

            Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);

            log.info("Number of quotation options for this quotation: " + quotationRepository.count());
            log.info("Quotation request size: " + quotation.getQuotationOptions().size());

            log.info("find all the quotation details inserted for a product using the reference");
            return toQuotationQueryModel(quotation);

        }
        throw new RuntimeException("Quotation has not been Accepted yet");
    }

    @RequestMapping(value = "api/quotations/{reference}/{quotationOptionId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public SelectedQuotationQueryModel getQuotationSelected(@PathVariable("reference") String reference, @PathVariable("quotationOptionId") String optionId) {

        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
        QuotationOption quotationOption = quotationOptionRepository.findOne(Long.parseLong(optionId));

        if (quotationRequest.getStatus().equals("ACCEPTED")) {

            Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);

            log.info("Number of quotation options for this quotation: " + quotationRepository.count());
            log.info("Quotation request size: " + quotation.getQuotationOptions().size());

            log.info("find all the quotation details inserted for a product using the reference");
            return toSelectedQuotationQueryModel(quotation, quotationOption);

        }
        throw new RuntimeException("Quotation has not been Accepted yet");
    }

    @Transactional
    @RequestMapping(value = "api/policy-requests", method = RequestMethod.POST)
    public void createPolicyRequest(@RequestPart(value = "policyRequest") PolicyRequestCommandModel policyRequestCommandModel, @RequestPart(value = "file") MultipartFile file) throws IOException {

        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(policyRequestCommandModel.getReference());

        Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);

        PolicyRequest policyRequest = toPolicyRequest(policyRequestCommandModel, quotation);
        policyRequest.setBankStatement(file.getBytes());
        policyRequest = policyRequestRepository.save(policyRequest);

        log.info("saved all the values");
        log.info("Policy Request Object :" + policyRequest.toString());

        //notificationService.sendNotificationForNewPolicyRequest(policyRequest, file, "eric@polygongroup.co.za ", "Polygon UnderwriterAdmin");
        notificationService.sendNotificationForNewPolicyRequest(policyRequest, file, "underwritter@reverside.co.za", "Reverside UnderwriterAdmin");
    }

    @RequestMapping(value = "api/policy-requests/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PolicyRequestQueryModel getPolicyRequest(@PathVariable("reference") String reference) {

        QuotationRequest quotationtRequest = quotationRequestRepository.findByReference(reference);
        Quotation quotation = quotationRepository.findByQuotationRequest(quotationtRequest);
        log.info("Quotation object: " + quotation.toString());
        PolicyRequest policyRequest = policyRequestRepository.findByQuotation(quotation);
        log.info("PolicyRequest object: " + policyRequest.toString());

        return toPolicyRequestQueryModel(policyRequest);

    }

    @Transactional
    @RequestMapping(value = "api/policy-requests/{reference}/reject", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
    public void rejectPolicyRequest(@PathVariable("reference") String reference, @RequestBody Map<String, String> reason) {
        QuotationRequest quotationRequest = quotationRequestRepository.findByReference(reference);
        Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);
        PolicyRequest policyRequest = policyRequestRepository.findByQuotation(quotation);
        policyRequest.setStatus("REJECTED");
        notificationService.sendNotificationForRejectPolicyRequest(policyRequest, reason.get("reason"));
        policyRequestRepository.save(policyRequest);
        log.info("New status :" + policyRequest.getStatus());
        log.info("Policy Request Rejected succes");
    }

    @RequestMapping(value = "api/quotations", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuotationQueryModel> findAllQuotations() {
        log.info("find all quotations");
        List<Quotation> quotation = quotationRepository.findAll();
        log.info("found all quotations - size:{}", quotation.size());
        return toQuotationQueryModel(quotation);
    }

    @RequestMapping(value = "api/quotation-requests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<QuotationRequestQueryModel> findAllQuotationRequest() {
        log.info("find all quotations");
        List<QuotationRequest> quotationRequests = quotationRequestRepository.findAll();
        log.info("found all quotations - size:{}", quotationRequests.size());
        return toQuotationRequestQueryModel(quotationRequests);
    }

    @RequestMapping(value = "api/clients/{clientId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ClientQueryModel getClient(@PathVariable("clientId") Long clientId) {

        Client client = clientRepository.findOne(clientId);
        log.info("client details");
        return toClientQueryModel(client);
    }

    @RequestMapping(value = "api/clients", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClientQueryModel> findAllClients() {
        log.info("Find all Clients");
        List<Client> client = clientRepository.findAll();
        log.info("Found all Cleints - size : ", client.size());
        return toClientQueryModel(client);
    }

    /*The get service to return the policy details per specific policy ID*/
    @RequestMapping(value = "api/policy/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PolicyQueryModel getPolicy(@PathVariable("reference") String reference) {
        log.info("Find the details of specific policy");
        Policy policy = policyRepository.findByReference(reference);

        return toPolicyQueryModel(policy);
    }

    @RequestMapping(value = "api/claim-types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClaimTypeQueryModel> findAllClaimTypes() {
        log.info("find all claim Types");
        List<ClaimType> claimType = claimTypeRepository.findAll();
        log.info("Found all claim types");

        return toClaimTypeQueryModel(claimType);
    }

    @RequestMapping(value = "api/claim-types/{claimTypeId}/claim-questionnaires", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClaimQuestionnaireQuery> findClaimQuestionnaires(@PathVariable("claimTypeId") Long claimTypeId) {

        log.info("Find all Claim questionaire for a claim type");
        List<ClaimQuestionnaire> claimQuestionnaire = new ArrayList<ClaimQuestionnaire>();

        ClaimType claimType = claimTypeRepository.findOne(claimTypeId);

        if (claimType != null) {
            claimQuestionnaire = claimQuestionnaireRepository.findByClaimType(claimType);
        }
        log.info("found all the questionnaires for claim type");
        List<ClaimQuestionnaireQuery> r = toClaimQuestionnaireQueryModel(claimQuestionnaire);

        return r;
    }

    @RequestMapping(value = "api/claim-requests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<ClaimRequestQueryModel> getAllClaimRequests() {
        log.info("Find all claim Requests");
        List<ClaimQuestionnaire> claimQuestionnaires = claimQuestionnaireRepository.findAll();
        List<ClaimRequest> claimRequest = claimRequestRepository.findAll();
        List<ClaimRequestQueryModel> claimRequestQueryModels = toClaimRequestQueryModel(claimRequest, claimQuestionnaires);
        log.info("found all claim requests");
        return claimRequestQueryModels;
    }
    
    @Transactional
    @RequestMapping(value = "api/getReleaseFormData/{claimNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ReleaseFormQueryModel getReleaseFormData(@PathVariable("claimNumber") String claimNumber) {
    	log.info("Release Form totalPayable amount!!!"+ claimNumber);
    	ReleaseForm releaseForm= releaseFormRepository.findByClaimNumber(claimNumber);
    	log.info("Release Form Created Successfully !!!");
    	return toReleaseFormQueryModel(releaseForm);
    }

    @RequestMapping(value = "api/claim-requests/{claimNumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ClaimRequestQueryModel getClaimRequest(@PathVariable("claimNumber") String claimNumber) {
        log.info("find claim");
        List<ClaimQuestionnaire> claimQuestionnaires = claimQuestionnaireRepository.findAll();
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
        log.info("found claim by claim number");
        return toClaimRequestQueryModel(claimRequest, claimQuestionnaires);
    }

    @Transactional
    @RequestMapping(value = "api/claim-requests/{claimNumber}/decline", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE, produces = "text/html")
    public void declineClaimRequest(@PathVariable("claimNumber") String claimNumber, @RequestBody Map<String, String> reason) {
        log.info("Declining Claim");
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
        claimRequest.setStatus("DECLINED");
        notificationService.sendNotificationForDeclineClaimRequest(claimRequest, reason.get("reason"));
        claimRequestRepository.save(claimRequest);
        log.info("New status :" + claimRequest.getStatus());
    }
    
    @Transactional
    @RequestMapping(value = "api/claim-requests/{claimNumber}/request-approval", method = RequestMethod.PUT)
    public void provisionallyApproveClaimRequest(@PathVariable("claimNumber") String claimNumber) {
        log.info("Request approval");
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
        claimRequest.setStatus("ApprovalRequest");
        notificationService.sendNotificationForRequestApprovalForClaimRequest(claimRequest);
        claimRequestRepository.save(claimRequest);
        log.info("New status :" + claimRequest.getStatus());
    }
    
    @Transactional
    @RequestMapping(value = "api/claim-requests/{claimNumber}/pendingDocuments", method = RequestMethod.PUT)
    public void requestForPendingDocuments(@PathVariable("claimNumber") String claimNumber) {
        log.info("request for pending documents");
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
        claimRequest.setStatus("Pending Documents Requested");
        notificationService.sendNotificationForRequestingPendingDocuments(claimRequest);
        claimRequestRepository.save(claimRequest);
        log.info("New status :" + claimRequest.getStatus());
    }
    
    @Transactional
    @RequestMapping(value = "api/claim-requests/{claimNumber}/approve", method = RequestMethod.PUT)
    public void approveClaimRequest(@PathVariable("claimNumber") String claimNumber) {
        log.info("Claim Approved");
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
        claimRequest.setStatus("Approved");
        notificationService.sendNotificationForApproveClaimRequest(claimRequest);
        claimRequestRepository.save(claimRequest);
        log.info("New status :" + claimRequest.getStatus());
    }
    
    @Transactional
    @RequestMapping(value = "api/claim-requests/{claimNumber}/accept", method = RequestMethod.PUT)
    public void acceptClaimRequest(@PathVariable("claimNumber") String claimNumber) throws ParseException, IOException, JRException {
        log.info("Claim accepted");
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
        claimRequest.setStatus("Accepted");
        ReleaseForm releaseForm = releaseFormRepository.findByClaimNumber(claimNumber);
        notificationService.sendNotificationForAcceptedClaimRequestCustomer(claimRequest, releaseForm);
        notificationService.sendNotificationForAcceptedClaimRequestBroker(claimRequest, releaseForm);
       notificationService.sendNotificationForAcceptedClaimRequestClaimAdmin(claimRequest, releaseForm);
        claimRequestRepository.save(claimRequest);
        log.info("New status :" + claimRequest.getStatus());
    }

    @Transactional
    @RequestMapping(value = "api/claim-requests/{claimNumber}/update", method = RequestMethod.PUT)
    public void createClaimRequest(@PathVariable("claimNumber") String claimNumber, @RequestPart(value = "investigationReport", required = false) MultipartFile investigationReport, @RequestPart(value = "comfirmationAmount", required = false) MultipartFile comfirmationAmount,
            @RequestPart(value = "proofOfPickup", required = false) MultipartFile proofOfPickup, @RequestPart(value = "transTrackDocument", required = false) MultipartFile transTrackDocument, @RequestPart(value = "quote", required = false) MultipartFile quote,
            @RequestPart(value = "report", required = false) MultipartFile report, @RequestPart(value = "affidavit", required = false) MultipartFile affidavit,
            @RequestPart(value = "photo1", required = false) MultipartFile photo1, @RequestPart(value = "photo2", required = false) MultipartFile photo2,
            @RequestPart(value = "photo3", required = false) MultipartFile photo3, @RequestPart(value = "photo4", required = false) MultipartFile photo4,
            @RequestPart(value = "amountBanked", required = false) MultipartFile amountBanked, @RequestPart(value = "proofOfPayment", required = false) MultipartFile proofOfPayment) throws IOException {

        log.info("updating Claim");
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
        claimRequest.setStatus("APPLIED");

        if (affidavit != null) {
            claimRequest.setAffidavit(affidavit.getBytes());
            claimRequest.setAffidavitC(affidavit.getContentType());
        }
        if (report != null) {
            claimRequest.setReport(report.getBytes());
            claimRequest.setReportC(report.getContentType());
        }
        if (proofOfPickup != null) {
            claimRequest.setProofOfPickup(proofOfPickup.getBytes());
            claimRequest.setProofOfPickupC(proofOfPickup.getContentType());
        }
        if (transTrackDocument != null) {
            claimRequest.setTransTrackDocument(transTrackDocument.getBytes());
            claimRequest.setTransTrackDocumentC(transTrackDocument.getContentType());
        }
        if (amountBanked != null) {
            claimRequest.setAmountBanked(amountBanked.getBytes());
            claimRequest.setAmountBankedC(amountBanked.getContentType());
        }
        if (comfirmationAmount != null) {
            claimRequest.setComfirmationAmount(comfirmationAmount.getBytes());
            claimRequest.setComfirmationAmountC(comfirmationAmount.getContentType());
        }
        if (investigationReport != null) {
            claimRequest.setInvestigationReport(investigationReport.getBytes());
            claimRequest.setInvestigationReportC(investigationReport.getContentType());
        }
        if (photo1 != null) {
            claimRequest.setPhoto1(photo1.getBytes());
            claimRequest.setPhoto1C(photo1.getContentType());
        }
        if (photo2 != null) {
            claimRequest.setPhoto2(photo2.getBytes());
            claimRequest.setPhoto2C(photo2.getContentType());
        }
        if (photo3 != null) {
            claimRequest.setPhoto3(photo3.getBytes());
            claimRequest.setPhoto3C(photo3.getContentType());
        }
        if (photo4 != null) {
            claimRequest.setPhoto4(photo4.getBytes());
            claimRequest.setPhoto4C(photo4.getContentType());
        }

    }

    @Transactional
    @RequestMapping(value = "api/claim-requests", method = RequestMethod.POST)
    public String createClaimRequest(@RequestPart(value = "claimRequest") ClaimRequestCommandModel claimRequestCommandModel, @RequestPart(value = "investigationReport", required = false) MultipartFile investigationReport, @RequestPart(value = "comfirmationAmount", required = false) MultipartFile comfirmationAmount,
            @RequestPart(value = "proofOfPickup", required = false) MultipartFile proofOfPickup, @RequestPart(value = "transTrackDocument", required = false) MultipartFile transTrackDocument, @RequestPart(value = "quote", required = false) MultipartFile quote,
            @RequestPart(value = "report", required = false) MultipartFile report, @RequestPart(value = "affidavit", required = false) MultipartFile affidavit,
            @RequestPart(value = "photo1", required = false) MultipartFile photo1, @RequestPart(value = "photo2", required = false) MultipartFile photo2,
            @RequestPart(value = "photo3", required = false) MultipartFile photo3, @RequestPart(value = "photo4", required = false) MultipartFile photo4,
            @RequestPart(value = "amountBanked", required = false) MultipartFile amountBanked, @RequestPart(value = "proofOfPayment", required = false) MultipartFile proofOfPayment) throws IOException {

        Policy policy = policyRepository.findByReference(claimRequestCommandModel.getReference());

        ClaimType claimType = claimTypeRepository.findOne(claimRequestCommandModel.getClaimTypeId());
        List<ClaimQuestionnaire> claimQuestionnaires = claimQuestionnaireRepository.findAll();
        int size = claimRequestRepository.findAll().size();
        ClaimRequest claimRequest = toClaimRequest(claimRequestCommandModel, policy, claimType, investigationReport, comfirmationAmount, proofOfPickup, transTrackDocument, quote, report, affidavit, photo1, photo2, photo3, photo4, amountBanked, proofOfPayment, claimQuestionnaires, size);

        claimRequest = claimRequestRepository.save(claimRequest);

        List<ClaimAnswer> claimRequestQuestionnaires = fromClaimRequestCommandModel(claimRequestCommandModel, claimRequest);

        claimRequestQuestionnaireRepository.save(claimRequestQuestionnaires);

        notificationService.sendNotificationForNewClaimRequest(claimRequest, "claim@reverside.co.za", "Reverside Claims Department");

        return claimRequest.getClaimNumber();
    }

    @RequestMapping(value = "api/request-types", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestTypeQueryModel> findAllRequestTypes() {
        log.info("find all request Types");
        List<RequestType> requestType = requestTypeRepository.findAll();
        log.info("Found all request types");

        return toRequestTypeQueryModel(requestType);
    }
    

    @RequestMapping(value = "api/request-type/{requestTypeId}/request-questionnaire", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<RequestQuestionnaireQueryModel> findRequestTypeQuestionnaires(@PathVariable("requestTypeId") Long requestTypeId) {

        log.info("Find all questionaire for a request type");
        RequestType requestType = requestTypeRepository.findOne(requestTypeId);
        List<RequestQuestionnaire> requestQuestionnaire = requestType.getRequestQuestionnaire();

        return toRequestQuestionnaireQueryModel(requestQuestionnaire);
    }
    

    @Transactional
    @RequestMapping(value = "api/generic-policy-requests", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public String createGenericPolicyRequest(@RequestBody PolicyRequestTypeCommandModel policyRequestTypeCommandModel) throws ParseException {
        int genericPolicyRequests = policyRequestTypeRepository.findAll().size();
        Policy policy = policyRepository.findByReference(policyRequestTypeCommandModel.getPolicyNo());
        RequestType requestType = requestTypeRepository.findOne(policyRequestTypeCommandModel.getRequestTypeId());
        PolicyRequestType policyRequestType = fromPolicyRequestTypeCommandModel(policyRequestTypeCommandModel, policy, requestType, genericPolicyRequests);
        PolicyRequestType savePolicyRequestType = policyRequestTypeRepository.save(policyRequestType);

        List<RequestAnswer> requestAnswers = fromPolicyRequestTypeCommandModel(policyRequestTypeCommandModel, policyRequestType);

        requestAnswersRepository.save(requestAnswers);

//        notificationService.sendNotificationForNewGenericPolicyRequest(savePolicyRequestType, "eric@polygongroup.co.za ");
        notificationService.sendNotificationForNewGenericPolicyRequest(savePolicyRequestType, "underwritter@reverside.co.za");
        log.info("Generic policy request created Successfully !!!");
        return savePolicyRequestType.getReference();
    }

    @RequestMapping(value = "api/generic-policy-request/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public PolicyRequestTypeQueryModel getGenericPolicyRequest(@PathVariable("reference") String reference) {
        log.info("Find the details of specific generic policy");
        PolicyRequestType policyRequestType = policyRequestTypeRepository.findByReference(reference);

        return toPolicyRequestTypeQueryModel(policyRequestType);
    }
    
    @RequestMapping(value = "api/generic-policy-requests", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<PolicyRequestTypeQueryModel> getGenericPolicyRequests() {
        log.info("Find all Generic Requests");
        List<PolicyRequestType> policyRequestTypes = policyRequestTypeRepository.findAll();
        
        return toPolicyRequestTypeQueryModel(policyRequestTypes);
    }


    @Bean
    public MultipartResolver multipartResolver() {
        return new StandardServletMultipartResolver() {
            @Override
            public boolean isMultipart(HttpServletRequest request) {
                String method = request.getMethod().toLowerCase();
                if (!Arrays.asList("put", "post").contains(method)) {
                    return false;
                }
                String contentType = request.getContentType();
                return (contentType != null && contentType.toLowerCase().startsWith("multipart/"));
            }
        };
    }

}
