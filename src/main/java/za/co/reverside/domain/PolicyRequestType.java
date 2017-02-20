package za.co.reverside.domain;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "policy_request_type")
public class PolicyRequestType {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name="policy_id")
    private Policy policy;
    
    @ManyToOne
    @JoinColumn(name = "request_type_id")
    private RequestType requestType;
    
    @OneToMany(mappedBy = "policyRequestType", fetch = FetchType.EAGER)
	private List<RequestAnswer> requestAnswers;
    
    @Column(name = "status_from")
    private String statusFrom;
    
    @Column(name = "reference")
    private String reference;
    
    @Column(name = "status")
    private String status;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "effective_date")
    private Date effectiveDate;

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

	
	public RequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(RequestType requestType) {
		this.requestType = requestType;
	}

	public String getStatusFrom() {
		return statusFrom;
	}

	public void setStatusFrom(String statusFrom) {
		this.statusFrom = statusFrom;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getEffectiveDate() {
		return effectiveDate;
	}

	public void setEffectiveDate(Date effectiveDate) {
		this.effectiveDate = effectiveDate;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<RequestAnswer> getRequestAnswers() {
		return requestAnswers;
	}

	public void setRequestAnswers(List<RequestAnswer> requestAnswers) {
		this.requestAnswers = requestAnswers;
	}
    
}
