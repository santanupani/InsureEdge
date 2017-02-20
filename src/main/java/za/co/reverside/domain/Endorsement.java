package za.co.reverside.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "endorsements")
public class Endorsement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "policy_id")
    private Policy policy;
    
    @Temporal(TemporalType.DATE)
    @Column(name = "endorsement_date")
    private Date endorsementDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "inception_date")
    private Date inceptionDate;

    @Temporal(TemporalType.DATE)
    @Column(name = "renewal_date")
    private Date renewalDate;
    
    @Column(name = "product_name")
    private String productName;

    @Column(name = "status")
    private String status;

	@Column(name = "frequency")
    private String frequency;

    @Column(name = "sasria_frequency")
    private String sasriaFrequency;

    @Column(name = "device")
    private String device;

    @Column(name = "sum_insured")
    private double sumInsured;
    
    @Column(name = "max_sum_insured")
    private double maximumSumInsured;
    
    @Column(name = "broker_commission")
    private double brokerCommission;
    
    @Column(name = "uma_fee")
    private double umaFee;
    
    @Column(name = "underwriter_commission")
    private double underwriterCommission;
    
    @Column(name = "policy_fee")
    private double policyFee;
    
    @Column(name = "initial_fee")
    private double initialFee;
    
    @Column(name = "premium")
    private double premium;
    
    @Column(name = "sasria_premium")
    private double sasriaPremium;
    
    @Column(name = "schedule_attaching")
    private String scheduleAttaching;
    
    @Column(name = "type_of_cover")
    private String typeOfCover;
    
    @Column(name = "subject_matter")
    private String subjectMatter;
    
   @Column(name = "excess_structure")
    private String excessSturcture;
   
   @Column(name = "special_condition")
    private String specialCondition;
   
   @Column(name = "conveyances")
    private String convenyances;

   @Column(name = "geographical_duration")
    private String geographicalDuration;

    @Column(name = "notes")
    private String notes;
   
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	
    public Date getInceptionDate() {
        return inceptionDate;
    }

    public void setInceptionDate(Date inceptionDate) {
        this.inceptionDate = inceptionDate;
    }

    public Date getRenewalDate() {
        return renewalDate;
    }

    public void setRenewalDate(Date renewalDate) {
        this.renewalDate = renewalDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public String getSasriaFrequency() {
        return sasriaFrequency;
    }

    public void setSasriaFrequency(String sasriaFrequency) {
        this.sasriaFrequency = sasriaFrequency;
    }

    public String getDevice() {
        return device;
    }

    public void setDevice(String device) {
        this.device = device;
    }
    

    public double getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(double sumInsured) {
        this.sumInsured = sumInsured;
    }

    public double getMaximumSumInsured() {
        return maximumSumInsured;
    }

    public void setMaximumSumInsured(double maximumSumInsured) {
        this.maximumSumInsured = maximumSumInsured;
    }

    public double getBrokerCommission() {
        return brokerCommission;
    }

    public void setBrokerCommission(double brokerCommission) {
        this.brokerCommission = brokerCommission;
    }

    public double getSasriaPremium() {
        return sasriaPremium;
    }

    public void setSasriaPremium(double sasriaPremium) {
        this.sasriaPremium = sasriaPremium;
    }

    public String getScheduleAttaching() {
        return scheduleAttaching;
    }

    public void setScheduleAttaching(String scheduleAttaching) {
        this.scheduleAttaching = scheduleAttaching;
    }

    public String getTypeOfCover() {
        return typeOfCover;
    }

    public void setTypeOfCover(String typeOfCover) {
        this.typeOfCover = typeOfCover;
    }

    public String getSubjectMatter() {
        return subjectMatter;
    }

    public void setSubjectMatter(String subjectMatter) {
        this.subjectMatter = subjectMatter;
    }

    public String getExcessSturcture() {
        return excessSturcture;
    }

    public void setExcessSturcture(String excessSturcture) {
        this.excessSturcture = excessSturcture;
    }

    public String getSpecialCondition() {
        return specialCondition;
    }

    public void setSpecialCondition(String specialCondition) {
        this.specialCondition = specialCondition;
    }

    public String getConvenyances() {
        return convenyances;
    }

    public void setConvenyances(String convenyances) {
        this.convenyances = convenyances;
    }

    public String getGeographicalDuration() {
        return geographicalDuration;
    }

    public void setGeographicalDuration(String geographicalDuration) {
        this.geographicalDuration = geographicalDuration;
    }
    
    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

	public double getUmaFee() {
		return umaFee;
	}

	public void setUmaFee(double umaFee) {
		this.umaFee = umaFee;
	}

	public double getUnderwriterCommission() {
		return underwriterCommission;
	}

	public void setUnderwriterCommission(double underwriterCommission) {
		this.underwriterCommission = underwriterCommission;
	}

	public double getPolicyFee() {
		return policyFee;
	}

	public void setPolicyFee(double policyFee) {
		this.policyFee = policyFee;
	}

	public double getInitialFee() {
		return initialFee;
	}

	public void setInitialFee(double initialFee) {
		this.initialFee = initialFee;
	}

	public double getPremium() {
		return premium;
	}

	public void setPremium(double premium) {
		this.premium = premium;
	}

	public Policy getPolicy() {
		return policy;
	}

	public void setPolicy(Policy policy) {
		this.policy = policy;
	}

	public Date getEndorsementDate() {
		return endorsementDate;
	}

	public void setEndorsementDate(Date endorsementDate) {
		this.endorsementDate = endorsementDate;
	}
    
}
