package za.co.reverside.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "request_answer_types")
public class RequestAnswerType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "request_answer_type")
    private String requestAnswerType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

	public String getRequestAnswerType() {
		return requestAnswerType;
	}

	public void setRequestAnswerType(String requestAnswerType) {
		this.requestAnswerType = requestAnswerType;
	}
    
}
