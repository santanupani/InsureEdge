package za.co.reverside.domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "request_types")
public class RequestType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @Column(name = "request_type")
    private String requestType;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestType")
    private List<PolicyRequestType> policyRequestTypes;

    @JsonIgnore
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestType")
    private List<RequestQuestionnaire> requestQuestionnaire;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
	public String getRequestType() {
		return requestType;
	}

	public void setRequestType(String requestType) {
		this.requestType = requestType;
	}

	public List<RequestQuestionnaire> getRequestQuestionnaire() {
		return requestQuestionnaire;
	}

	public void setRequestQuestionnaire(List<RequestQuestionnaire> requestQuestionnaire) {
		this.requestQuestionnaire = requestQuestionnaire;
	}

	public List<PolicyRequestType> getPolicyRequestTypes() {
		return policyRequestTypes;
	}

	public void setPolicyRequestTypes(List<PolicyRequestType> policyRequestTypes) {
		this.policyRequestTypes = policyRequestTypes;
	}
    
}
