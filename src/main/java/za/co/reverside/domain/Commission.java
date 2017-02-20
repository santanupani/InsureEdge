package za.co.reverside.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name = "commissions")
public class Commission {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@OneToOne
	@JoinColumn(name = "underwriter_id")
    private Underwriter underwriter;
	
	@OneToOne
	@JoinColumn(name = "broker_id")
    private Broker broker;
	
	@Column(name = "inital_admission_fee")
    private Date inital_admission_fee;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Underwriter getUnderwriter() {
		return underwriter;
	}

	public void setUnderwriter(Underwriter underwriter) {
		this.underwriter = underwriter;
	}

	public Broker getBroker() {
		return broker;
	}

	public void setBroker(Broker broker) {
		this.broker = broker;
	}

	public Date getInital_admission_fee() {
		return inital_admission_fee;
	}

	public void setInital_admission_fee(Date inital_admission_fee) {
		this.inital_admission_fee = inital_admission_fee;
	}

}
