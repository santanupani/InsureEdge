
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
@Table(name = "request_answer_values")
public class RequestAnswerValue {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    
    @ManyToOne
    @JoinColumn(name = "request_questionnaire_id")
    private  RequestQuestionnaire requestQuestionnaire;
    
    @Column(name = "request_answer_value")
    private String requestAnswerValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public RequestQuestionnaire getRequestQuestionnaire() {
		return requestQuestionnaire;
	}

	public void setRequestQuestionnaire(RequestQuestionnaire requestQuestionnaire) {
		this.requestQuestionnaire = requestQuestionnaire;
	}

	public String getRequestAnswerValue() {
		return requestAnswerValue;
	}

	public void setRequestAnswerValue(String requestAnswerValue) {
		this.requestAnswerValue = requestAnswerValue;
	}   
    
}
