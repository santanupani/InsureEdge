
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
@Table(name = "claim_answer_values")
public class ClaimAnswerValue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "claim_questionnaire_id")
    private  ClaimQuestionnaire claimQuestionnaire;
    
    @Column(name = "claim_answer_value")
    private String claimAnswerValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ClaimQuestionnaire getClaimQuestionnaire() {
        return claimQuestionnaire;
    }

    public void setClaimQuestionnaire(ClaimQuestionnaire claimQuestionnaire) {
        this.claimQuestionnaire = claimQuestionnaire;
    }

    public String getClaimAnswerValue() {
        return claimAnswerValue;
    }

    public void setClaimAnswerValue(String claimAnswerValue) {
        this.claimAnswerValue = claimAnswerValue;
    }


    
    
    
}
