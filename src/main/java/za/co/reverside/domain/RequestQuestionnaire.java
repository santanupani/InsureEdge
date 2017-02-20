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
@Table(name = "request_questionnaires")
public class RequestQuestionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "request_type_id")
    RequestType requestType;

    @Column(name = "question")
    private String question;
    
    @Column(name = "is_required")
    private Boolean isRequired;
    
    @Column(name = "sequence_number")
    private Long sequenceNumber;

     @ManyToOne
    @JoinColumn(name = "request_answer_type_id")
    private RequestAnswerType requestAnswerType;
   
    @OneToMany(mappedBy = "requestQuestionnaire")
    private List<RequestAnswerValue> requestAnswerValue;
    
    
    
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

	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public RequestAnswerType getRequestAnswerType() {
		return requestAnswerType;
	}

	public void setRequestAnswerType(RequestAnswerType requestAnswerType) {
		this.requestAnswerType = requestAnswerType;
	}

	public List<RequestAnswerValue> getRequestAnswerValue() {
		return requestAnswerValue;
	}

	public void setRequestAnswerValue(List<RequestAnswerValue> requestAnswerValue) {
		this.requestAnswerValue = requestAnswerValue;
	}
    
}
