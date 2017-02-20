package za.co.reverside.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "release_form")
public class ReleaseForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToOne
    @JoinColumn(name = "claim_request_id")
    private ClaimRequest claimRequest;

    @Column(name = "claim_number")
    private String claimNumber;
    
    @Column(name = "policy_number")
    private String policyNumber;
    
    @Column(name = "insured")
    private String insured;
    
    @Column(name = "amount_claim")
    private double amountClaim;
    
    @Column(name = "less_excess")
    private double lessExcess;
    
    @Column(name = "total_payeble")
    private double totalPayeble;
    
    @Column(name = "good_description")
    private String goodDescription;
    
    @Column(name = "loss_date")
    private String lossDate;
    
    @Column(name = "loss_description")
    private String lossDescription;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClaimRequest getClaimRequest() {
        return claimRequest;
    }

    public void setClaimRequest(ClaimRequest claimRequest) {
        this.claimRequest = claimRequest;
    }

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }
 
    public String getInsured() {
        return insured;
    }

    public void setInsured(String insured) {
        this.insured = insured;
    }

    public double getAmountClaim() {
        return amountClaim;
    }

    public void setAmountClaim(double amountClaim) {
        this.amountClaim = amountClaim;
    }

    public double getLessExcess() {
        return lessExcess;
    }

    public void setLessExcess(double lessExcess) {
        this.lessExcess = lessExcess;
    }

    public double getTotalPayeble() {
        return totalPayeble;
    }

    public void setTotalPayeble(double totalPayeble) {
        this.totalPayeble = totalPayeble;
    }

    public String getGoodDescription() {
        return goodDescription;
    }

    public void setGoodDescription(String goodDescription) {
        this.goodDescription = goodDescription;
    }

    public String getLossDate() {
        return lossDate;
    }

    public void setLossDate(String lossDate) {
        this.lossDate = lossDate;
    }

    public String getLossDescription() {
        return lossDescription;
    }

    public void setLossDescription(String lossDescription) {
        this.lossDescription = lossDescription;
    }
    
    


}
