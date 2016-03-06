package za.co.polygon;

import static za.co.polygon.mapper.Mapper.fromBankAccountCommandModel;
import static za.co.polygon.mapper.Mapper.fromClaimRequestCommandModel;
import static za.co.polygon.mapper.Mapper.fromClientCommandModel;
import static za.co.polygon.mapper.Mapper.fromContactCommandModel;
import static za.co.polygon.mapper.Mapper.fromEndorsePolicyCommandModel;
import static za.co.polygon.mapper.Mapper.fromHistoryCommandModel;
import static za.co.polygon.mapper.Mapper.fromLocationOptionsCommandModel;
import static za.co.polygon.mapper.Mapper.fromPolicyCreationCommandModel;
import static za.co.polygon.mapper.Mapper.fromPolicyRequestTypeCommandModel;
import static za.co.polygon.mapper.Mapper.fromQuotationRequestCommandModel;
import static za.co.polygon.mapper.Mapper.fromQuotationUpdateCommandModel;
import static za.co.polygon.mapper.Mapper.toApprovePolicyCommandModel;
import static za.co.polygon.mapper.Mapper.toBrokerQueryModel;
import static za.co.polygon.mapper.Mapper.toClaimQuestionnaireQueryModel;
import static za.co.polygon.mapper.Mapper.toClaimRequest;
import static za.co.polygon.mapper.Mapper.toClaimRequestQueryModel;
import static za.co.polygon.mapper.Mapper.toClaimTypeQueryModel;
import static za.co.polygon.mapper.Mapper.toClientCommandModel;
import static za.co.polygon.mapper.Mapper.toClientQueryModel;
import static za.co.polygon.mapper.Mapper.toEndorsementQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyRequest;
import static za.co.polygon.mapper.Mapper.toPolicyRequestQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyRequestTypeQueryModel;
import static za.co.polygon.mapper.Mapper.toPolicyUpdateCommandModel;
import static za.co.polygon.mapper.Mapper.toProductQueryModel;
import static za.co.polygon.mapper.Mapper.toQuestionnaireQueryModel;
import static za.co.polygon.mapper.Mapper.toQuotationQueryModel;
import static za.co.polygon.mapper.Mapper.toQuotationRequest;
import static za.co.polygon.mapper.Mapper.toQuotationRequestQueryModel;
import static za.co.polygon.mapper.Mapper.toReleaseFormCommandModel;
import static za.co.polygon.mapper.Mapper.toRequestQuestionnaireQueryModel;
import static za.co.polygon.mapper.Mapper.toRequestTypeQueryModel;
import static za.co.polygon.mapper.Mapper.toSelectedQuotationQueryModel;
import static za.co.polygon.mapper.Mapper.toSubAgentQueryModel;
import static za.co.polygon.mapper.Mapper.toUserQueryModel;

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
import za.co.polygon.domain.Answer;
import za.co.polygon.domain.BankAccount;
import za.co.polygon.domain.Broker;
import za.co.polygon.domain.Carrier;
import za.co.polygon.domain.ClaimAnswer;
import za.co.polygon.domain.ClaimQuestionnaire;
import za.co.polygon.domain.ClaimRequest;
import za.co.polygon.domain.ClaimType;
import za.co.polygon.domain.Client;
import za.co.polygon.domain.Contact;
import za.co.polygon.domain.Endorsement;
import za.co.polygon.domain.History;
import za.co.polygon.domain.LocationOption;
import za.co.polygon.domain.Policy;
import za.co.polygon.domain.PolicyRequest;
import za.co.polygon.domain.PolicyRequestType;
import za.co.polygon.domain.Product;
import za.co.polygon.domain.Questionnaire;
import za.co.polygon.domain.Quotation;
import za.co.polygon.domain.QuotationOption;
import za.co.polygon.domain.QuotationRequest;
import za.co.polygon.domain.ReleaseForm;
import za.co.polygon.domain.RequestAnswer;
import za.co.polygon.domain.RequestQuestionnaire;
import za.co.polygon.domain.RequestType;
import za.co.polygon.domain.SubAgent;
import za.co.polygon.domain.Underwriter;
import static za.co.polygon.mapper.Mapper.fromCarrierCommandModel;
import static za.co.polygon.mapper.Mapper.toCarrierQueryModel;
import za.co.polygon.model.BrokerQueryModel;
import za.co.polygon.model.CarrierCommandModel;
import za.co.polygon.model.CarrierQueryModel;
import za.co.polygon.model.ClaimQuestionnaireQuery;
import za.co.polygon.model.ClaimRequestCommandModel;
import za.co.polygon.model.ClaimRequestQueryModel;
import za.co.polygon.model.ClaimTypeQueryModel;
import za.co.polygon.model.ClientQueryModel;
import za.co.polygon.model.EndorsementQueryModel;
import za.co.polygon.model.PolicyCreationCommandModel;
import za.co.polygon.model.PolicyQueryModel;
import za.co.polygon.model.PolicyRequestCommandModel;
import za.co.polygon.model.PolicyRequestQueryModel;
import za.co.polygon.model.PolicyRequestTypeCommandModel;
import za.co.polygon.model.PolicyRequestTypeQueryModel;
import za.co.polygon.model.ProductQueryModel;
import za.co.polygon.model.QuestionnaireQuery;
import za.co.polygon.model.QuotationCommandModel;
import za.co.polygon.model.QuotationQueryModel;
import za.co.polygon.model.QuotationRequestCommandModel;
import za.co.polygon.model.QuotationRequestCommandModel.LocationOptions;
import za.co.polygon.model.QuotationRequestQueryModel;
import za.co.polygon.model.ReleaseFormCommandModel;
import za.co.polygon.model.RequestQuestionnaireQueryModel;
import za.co.polygon.model.RequestTypeQueryModel;
import za.co.polygon.model.SelectedQuotationQueryModel;
import za.co.polygon.model.SubAgentQueryModel;
import za.co.polygon.model.UserQueryModel;
import za.co.polygon.repository.BankAccountRepository;
import za.co.polygon.repository.BrokerRepository;
import za.co.polygon.repository.CarrierRepository;
import za.co.polygon.repository.ClaimQuestionnaireRepository;
import za.co.polygon.repository.ClaimRequestQuestionnaireRepository;
import za.co.polygon.repository.ClaimRequestRepository;
import za.co.polygon.repository.ClaimTypeRepository;
import za.co.polygon.repository.ClientRepository;
import za.co.polygon.repository.ContactRepository;
import za.co.polygon.repository.EndorsementRepository;
import za.co.polygon.repository.HistoryRepository;
import za.co.polygon.repository.LocationOptionRepository;
import za.co.polygon.repository.PolicyRepository;
import za.co.polygon.repository.PolicyRequestRepository;
import za.co.polygon.repository.PolicyRequestTypeRepository;
import za.co.polygon.repository.ProductRepository;
import za.co.polygon.repository.QuestionnaireRepository;
import za.co.polygon.repository.QuotationOptionRepository;
import za.co.polygon.repository.QuotationRepository;
import za.co.polygon.repository.QuotationRequestQuestionnaireRepository;
import za.co.polygon.repository.QuotationRequestRepository;
import za.co.polygon.repository.ReleaseFormRepository;
import za.co.polygon.repository.RequestAnswersRepository;
import za.co.polygon.repository.RequestTypeRepository;
import za.co.polygon.repository.SubAgentRepository;
import za.co.polygon.repository.UnderwriterRepository;
import za.co.polygon.repository.UserRepository;
import za.co.polygon.service.DocumentService;
import za.co.polygon.service.NotificationService;

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
        List<za.co.polygon.domain.User> users = userRepository.findAll();
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
        notificationService.sendNotificationForApprovalToBroker(updatePolicy,"polygon.broker@gmail.com");
        notificationService.sendNotificationForApprovalToUnderwritter(updatePolicy,"polygon.underwriter@gmail.com");
        return toPolicyQueryModel(updatePolicy);
    }
    
    @RequestMapping(value = "api/policy-request-approval/{reference}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public String requestPolicyApproval(@PathVariable("reference") String reference) {
        log.info("Sending email notification for policy request approval");
        Policy policy = policyRepository.findByReference(reference);
//        notificationService.sendNotificationForRequestPolicyApproval(policy, "gerhard@polygongroup.co.za");
        notificationService.sendNotificationForRequestPolicyApproval(policy, "polygon.testing@gmail.com");
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
        return documentService.policyScheduleReportPDF(policy);

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
        Quotation quotation = quotationRepository.findByQuotationRequest(quotationRequest);

        return documentService.generateQuotationPDF(quotation);

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
    public void createReleaseForm(@RequestBody ReleaseFormCommandModel releaseFormCommandModel){
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

        byte[] data = reportService.generateQuotationPDF(quotationRepository.findByQuotationRequest(quotationRequest));
        notificationService.sendNotificationForAcceptQuotationRequest(quotationRequest, data);

        log.info("Quotation Created Successfully !!!");
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

//        notificationService.sendNotificationForNewPolicyRequest(policyRequest, file, "eric@polygongroup.co.za ", "Polygon UnderwriterAdmin");
        notificationService.sendNotificationForNewPolicyRequest(policyRequest, file, "polygon.underwriter@gmail.com", "Polygon UnderwriterAdmin");
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
    public void acceptClaimRequest(@PathVariable("claimNumber") String claimNumber) {
        log.info("Claim accepted");
        ClaimRequest claimRequest = claimRequestRepository.findByClaimNumber(claimNumber);
        claimRequest.setStatus("Accepted");
        notificationService.sendNotificationForAcceptedClaimRequest(claimRequest);
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

        notificationService.sendNotificationForNewClaimRequest(claimRequest, "claims@polygongroup.co.za", "Polygon Claims Department");

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
        notificationService.sendNotificationForNewGenericPolicyRequest(savePolicyRequestType, "polygon.underwriter@gmail.com");
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
