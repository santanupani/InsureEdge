package za.co.reverside.domain;

import java.util.ArrayList;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "quotation_requests")
public class QuotationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "reference")
    private String reference;

    @Column(name = "applicant_name")
    private String applicantName;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "applicant_email")
    private String applicantEmail;

    @ManyToOne
    @JoinColumn(name = "broker_id")
    private Broker broker;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @OneToMany(mappedBy = "quotationRequest", fetch = FetchType.EAGER)
    private List<LocationOption> locationOptions;

    @OneToMany(mappedBy = "quotationRequest", fetch = FetchType.EAGER)
    private List<History> history;

    @Column(name = "create_date")
    private Date createDate;

    @Column(name = "status")
    private String status;
    
    @Column(name = "accident_info")
    private String accidentInfo;
    
    @Column(name = "agreed_flag")
    private String agreedFlag;
    
    @Column(name = "class_of_use")
    private String classOfuse;
    
    @Column(name = "commodity")
    private String commodity;
    
    @Column(name = "cover_amount")
    private String coverAmount;
    
    @Column(name = "cover_type")
    private String coverType;
    
    @Column(name = "make_model")
    private String makeModel;
    
    @Column(name = "overnight_parking")
    private String overnightParking;
    
    @Column(name = "pensioner_status")
    private String pensionerStatus;
    
    @Column(name = "regd_number")
    private String regdNumber;
    
    @Column(name = "year_of_manufacture")
    private String yearOfManufacture;    
    
    
    @Column(name = "policy_inception_date")
    private String policyInceptionDate;
    
    @Column(name = "policy_end_date")
    private String policyEndDate;
   
    @Column(name = "mode_of_coverage")
    private String modeOfCoverage;
    
    @Column(name = "sasria")
    private String sasria;
    
    @Column(name = "insured_status")
    private String insuredStatus;
    
    
    

    @OneToMany(mappedBy = "quotationRequest", fetch = FetchType.EAGER)
    private List<Answer> answers;

    @OneToMany(mappedBy = "quotationRequest", fetch = FetchType.EAGER)
    private List<Quotation> quotation;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public Broker getBroker() {
        return broker;
    }

    public void setBroker(Broker broker) {
        this.broker = broker;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createdDate) {
        this.createDate = createdDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getAccidentInfo(){
    	return accidentInfo;
    	
    }
    
    public void setAccidentInfo(String accidentInfo){
    	this.accidentInfo = accidentInfo;
    	
    }
    
    public String getAgreedFlag(){
    	return agreedFlag;
    	
    }

    public void setAgreedFlag(String agreedFlag){
    	this.agreedFlag  = agreedFlag;
    	
    }
    
    public String getClassOfuse(){
    	return classOfuse;
    	
    }
    
    public void setClassOfuse(String classOfuse){
    	this.classOfuse = classOfuse;
    	
    }
    
    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }
    
    public String getCoverAmount(){
    	return coverAmount;
    }
    
    public void setCoverAmount(String coverAmount){
    	this.coverAmount = coverAmount;
    }
    
    public String getCoverType(){
    	return coverType;
    }
    
    public void setCoverType(String coverType){
    	
    	this.coverType = coverType;
    }
    
    public String getMakeModel(){
    	return makeModel;
    }
    
    public void setMakeModel(String makeModel){
    	this.makeModel = makeModel;
    	
    }
    
    public String getOvernightParking(){
    	return overnightParking;
    }
    
    public void setOvernightParking(String overnightParking){
    	this.overnightParking = overnightParking;
    }
    
    public String getPensionerStatus(){
    	return pensionerStatus;
    }
    
    public void setPensionerStatus(String pensionerStatus){
    	this.pensionerStatus = pensionerStatus;
    }
    
    public String getRegdNumber(){
    	return regdNumber;
    }
    
    public void setRegdNumber(String regdNumber){
    	this.regdNumber = regdNumber;
    }
    
    public String getYearOfManufacture(){
    	return yearOfManufacture;
    }
    
    public void setYearOfManufacture(String yearOfManufacture){
    	this.yearOfManufacture = yearOfManufacture;
    } 
    
    public String getPolicyInceptionDate(){
    	return policyInceptionDate;
    }
    
    public void setPolicyInceptionDate(String policyInceptionDate){
    	this.policyInceptionDate = policyInceptionDate;
    }
    
    public String getPolicyEndDate(){
    	return policyEndDate;
    }
    
    public void setPolicyEndDate(String policyEndDate){
    	this.policyEndDate = policyEndDate;
    }   
   
    
    public String getModeOfCoverage(){
    	return modeOfCoverage;
    }
    
    public void setModeOfCoverage(String modeOfCoverage){
    	this.modeOfCoverage = modeOfCoverage;
    }
    
    public String getSasria(){
    	return sasria;
    }
    
    public void setSasria(String sasria){
    	this.sasria = sasria;
    }
    
    public String getInsuredStatus(){
    	return insuredStatus;
    }
    
    public void setInsuredStatus( String insuredStatus){
    	this.insuredStatus = insuredStatus;
    }
    
    
    public List<Answer> getAnswers() {
        if (answers == null) {
            return new ArrayList<Answer>();
        } else {
            return answers;
        }
    }

    public void setAnswers(List<Answer> quotationRequestQuestionnaire) {
        this.answers = quotationRequestQuestionnaire;
    }

    public List<Quotation> getQuotation() {
        return quotation;
    }

    public void setQuotation(List<Quotation> quotation) {
        this.quotation = quotation;
    }

    public List<LocationOption> getLocationOptions() {
        return locationOptions;
    }

    public void setLocationOptions(List<LocationOption> locationOptions) {
        this.locationOptions = locationOptions;
    }

    public List<History> getHistory() {
        return history;
    }

    public void setHistory(List<History> history) {
        this.history = history;
    }
    

}
