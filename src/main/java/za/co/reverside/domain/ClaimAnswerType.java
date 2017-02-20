package za.co.reverside.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "claim_answer_types")
public class ClaimAnswerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "claim_answer_type")
    private String claimAnswerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClaimAnswerType() {
        return claimAnswerType;
    }

    public void setClaimAnswerType(String claimAnswerType) {
        this.claimAnswerType = claimAnswerType;
    }
    

}
