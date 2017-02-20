package za.co.reverside.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "claim_questionnaires")
public class ClaimQuestionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "claim_type_id")
    ClaimType claimType;

    @Column(name = "question")
    private String question;
    
    @Column(name = "is_required")
    private Boolean isRequired;
    
    @Column(name = "sequence_number")
    private Long sequenceNumber;

     @ManyToOne
    @JoinColumn(name = "claim_answer_type_id")
    private ClaimAnswerType claimAnswerType;
   
    @OneToMany(mappedBy = "claimQuestionnaire")
    private List<ClaimAnswerValue> claimAnswerValue;
    
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
    

    public ClaimAnswerType getClaimAnswerType() {
        return claimAnswerType;
    }

    public void setClaimAnswerType(ClaimAnswerType claimAnswerType) {
        this.claimAnswerType = claimAnswerType;
    }

    public List<ClaimAnswerValue> getClaimAnswerValue() {
        return claimAnswerValue;
    }

    public void setClaimAnswerValue(List<ClaimAnswerValue> claimAnswerValue) {
        this.claimAnswerValue = claimAnswerValue;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public ClaimType getClaimType() {
        return claimType;
    }

    public void setClaimType(ClaimType claimType) {
        this.claimType = claimType;
    }



}
