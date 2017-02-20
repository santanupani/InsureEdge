package za.co.reverside.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "policy_requests")
public class PolicyRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    

    @Column(name = "company_reg_number")
    private String companyRegNumber;

    @Column(name = "vat_reg_number")
    private String vatRegNumber;

    @Column(name = "telephone_number")
    private String telephoneNumber;

    @Column(name = "fax_number")
    private String faxNumber;

    @Column(name = "street_address")
    private String streetAddress;

    @Column(name = "suburb")
    private String suburb;

    @Column(name = "postal_code")
    private String postalCode;

    @Column(name = "designation")
    private String designation;

    @Column(name = "buisness_desc")
    private String buisnessDesc;

   

    @Column(name = "account_holder")
    private String accountHolder;

    @Column(name = "account_name")
    private String accountName;

    @Column(name = "bank_name")
    private String bankName;

    @Column(name = "account_number")
    private String accountNumber;

    @Column(name = "branch_code")
    private String branchCode;

    @Column(name = "acc_type")
    private String accType;

    @Column(name = "debit_order_date")
    private String debitOrderDate;
    
    @Column(name = "bank_statement")
    private byte[] bankStatement;
    
    @Column(name = "status")
    private String status;
    
    @ManyToOne
    @JoinColumn(name = "quotation_id")
    private Quotation quotation;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    
    public String getCompanyRegNumber() {
        return companyRegNumber;
    }

    public void setCompanyRegNumber(String companyRegNumber) {
        this.companyRegNumber = companyRegNumber;
    }

    public String getVatRegNumber() {
        return vatRegNumber;
    }

    public void setVatRegNumber(String vatRegNumber) {
        this.vatRegNumber = vatRegNumber;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getBuisnessDesc() {
        return buisnessDesc;
    }

    public void setBuisnessDesc(String buisnessDesc) {
        this.buisnessDesc = buisnessDesc;
    }

    public String getAccountHolder() {
        return accountHolder;
    }

    public void setAccountHolder(String accountHolder) {
        this.accountHolder = accountHolder;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getAccType() {
        return accType;
    }

    public void setAccType(String accType) {
        this.accType = accType;
    }

    public String getDebitOrderDate() {
        return debitOrderDate;
    }

    public void setDebitOrderDate(String debitOrderDate) {
        this.debitOrderDate = debitOrderDate;
    }

    public byte[] getBankStatement() {
        return bankStatement;
    }

    public void setBankStatement(byte[] bankStatement) {
        this.bankStatement = bankStatement;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    


}
