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
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "claim_requests")
public class ClaimRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;

    @ManyToOne
    @JoinColumn(name = "claim_type_id")
    private ClaimType claimType;

    @OneToMany(mappedBy = "claimRequest", fetch = FetchType.EAGER)
    private List<ClaimAnswer> claimAnswers;
    
    @Column(name = "claim_number")
    private String claimNumber;

    @Column(name = "status")
    private String status;

    @Column(name = "investigation_report")
    private byte[] investigationReport;

    @Column(name = "confirmation_amount")
    private byte[] comfirmationAmount;

    @Column(name = "proof_of_pickup")
    private byte[] proofOfPickup;

    @Column(name = "case_number")
    private byte[] caseNumber;

    @Column(name = "amount_banked")
    private byte[] amountBanked;

    @Column(name = "trans_track_document")
    private byte[] transTrackDocument;

    @Column(name = "quote")
    private byte[] quote;

    @Column(name = "report")
    private byte[] report;

    @Column(name = "affidavit")
    private byte[] affidavit;

    @Column(name = "photo1")
    private byte[] photo1;

    @Column(name = "photo2")
    private byte[] photo2;

    @Column(name = "photo3")
    private byte[] photo3;

    @Column(name = "photo4")
    private byte[] photo4;

    @Column(name = "investigation_report_c")
    private String investigationReportC;

 
    @Column(name = "confirmation_amount_c")
    private String comfirmationAmountC;

    @Column(name = "proof_of_pickup_c")
    private String proofOfPickupC;

    @Column(name = "case_number_c")
    private String caseNumberC;

    @Column(name = "amount_banked_c")
    private String amountBankedC;

    @Column(name = "trans_track_document_c")
    private String transTrackDocumentC;

    @Column(name = "quote_c")
    private String quoteC;

    @Column(name = "report_c")
    private String reportC;

    @Column(name = "affidavit_c")
    private String affidavitC;

    @Column(name = "photo1_c")
    private String photo1C;

    @Column(name = "photo2_c")
    private String photo2C;

    @Column(name = "photo3_c")
    private String photo3C;

    @Column(name = "photo4_c")
    private String photo4C;

    @Column(name = "create_date")
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Policy getPolicy() {
        return policy;
    }

    public void setPolicy(Policy policy) {
        this.policy = policy;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public byte[] getInvestigationReport() {
        return investigationReport;
    }

    public void setInvestigationReport(byte[] investigationReport) {
        this.investigationReport = investigationReport;
    }

    public byte[] getComfirmationAmount() {
        return comfirmationAmount;
    }

    public void setComfirmationAmount(byte[] comfirmationAmount) {
        this.comfirmationAmount = comfirmationAmount;
    }

    public byte[] getProofOfPickup() {
        return proofOfPickup;
    }

    public void setProofOfPickup(byte[] proofOfPickup) {
        this.proofOfPickup = proofOfPickup;
    }

    public byte[] getCaseNumber() {
        return caseNumber;
    }

    public void setCaseNumber(byte[] caseNumber) {
        this.caseNumber = caseNumber;
    }

    public byte[] getAmountBanked() {
        return amountBanked;
    }

    public void setAmountBanked(byte[] amountBanked) {
        this.amountBanked = amountBanked;
    }

    public byte[] getTransTrackDocument() {
        return transTrackDocument;
    }

    public void setTransTrackDocument(byte[] transTrackDocument) {
        this.transTrackDocument = transTrackDocument;
    }

    public byte[] getQuote() {
        return quote;
    }

    public void setQuote(byte[] quote) {
        this.quote = quote;
    }

    public byte[] getReport() {
        return report;
    }

    public void setReport(byte[] report) {
        this.report = report;
    }

    public byte[] getAffidavit() {
        return affidavit;
    }

    public void setAffidavit(byte[] affidavit) {
        this.affidavit = affidavit;
    }

    public byte[] getPhoto1() {
        return photo1;
    }

    public void setPhoto1(byte[] photo1) {
        this.photo1 = photo1;
    }

    public byte[] getPhoto2() {
        return photo2;
    }

    public void setPhoto2(byte[] photo2) {
        this.photo2 = photo2;
    }

    public byte[] getPhoto3() {
        return photo3;
    }

    public void setPhoto3(byte[] photo3) {
        this.photo3 = photo3;
    }

    public byte[] getPhoto4() {
        return photo4;
    }

    public void setPhoto4(byte[] photo4) {
        this.photo4 = photo4;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public ClaimType getClaimType() {
        return claimType;
    }

    public void setClaimType(ClaimType claimType) {
        this.claimType = claimType;
    }

    public List<ClaimAnswer> getClaimAnswer() {
        if (claimAnswers == null) {
            return new ArrayList<ClaimAnswer>();
        } else {
            return claimAnswers;
        }
    }

    public void setClaimAnswer(List<ClaimAnswer> claimRequestQuestionnaire) {
        this.claimAnswers = claimRequestQuestionnaire;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ClaimAnswer> getClaimAnswers() {
        return claimAnswers;
    }

    public void setClaimAnswers(List<ClaimAnswer> claimAnswers) {
        this.claimAnswers = claimAnswers;
    }

    public String getInvestigationReportC() {
        return investigationReportC;
    }

    public void setInvestigationReportC(String investigationReportC) {
        this.investigationReportC = investigationReportC;
    }

    public String getComfirmationAmountC() {
        return comfirmationAmountC;
    }

    public void setComfirmationAmountC(String comfirmationAmountC) {
        this.comfirmationAmountC = comfirmationAmountC;
    }

    public String getProofOfPickupC() {
        return proofOfPickupC;
    }

    public void setProofOfPickupC(String proofOfPickupC) {
        this.proofOfPickupC = proofOfPickupC;
    }

    public String getCaseNumberC() {
        return caseNumberC;
    }

    public void setCaseNumberC(String caseNumberC) {
        this.caseNumberC = caseNumberC;
    }

    public String getAmountBankedC() {
        return amountBankedC;
    }

    public void setAmountBankedC(String amountBankedC) {
        this.amountBankedC = amountBankedC;
    }

    public String getTransTrackDocumentC() {
        return transTrackDocumentC;
    }

    public void setTransTrackDocumentC(String transTrackDocumentC) {
        this.transTrackDocumentC = transTrackDocumentC;
    }

    public String getQuoteC() {
        return quoteC;
    }

    public void setQuoteC(String quoteC) {
        this.quoteC = quoteC;
    }

    public String getReportC() {
        return reportC;
    }

    public void setReportC(String reportC) {
        this.reportC = reportC;
    }

    public String getAffidavitC() {
        return affidavitC;
    }

    public void setAffidavitC(String affidavitC) {
        this.affidavitC = affidavitC;
    }

    public String getPhoto1C() {
        return photo1C;
    }

    public void setPhoto1C(String photo1C) {
        this.photo1C = photo1C;
    }

    public String getPhoto2C() {
        return photo2C;
    }

    public void setPhoto2C(String photo2C) {
        this.photo2C = photo2C;
    }

    public String getPhoto3C() {
        return photo3C;
    }

    public void setPhoto3C(String photo3C) {
        this.photo3C = photo3C;
    }

    public String getPhoto4C() {
        return photo4C;
    }

    public void setPhoto4C(String photo4C) {
        this.photo4C = photo4C;
    }
    
}
