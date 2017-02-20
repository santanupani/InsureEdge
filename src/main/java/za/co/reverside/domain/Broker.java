
package za.co.reverside.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "brokers")
public class Broker {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @OneToMany(mappedBy="broker",fetch=FetchType.EAGER)
    private List<SubAgent> subAgents;
    
    @Column(name = "code")
    private String code;
    @Column(name = "name")
    private String name;
    @Column(name = "email")
    private String email;

    public Long getId() {
        return id;
    }

    public List<SubAgent> getSubAgents() {
		return subAgents;
	}



	public void setSubAgents(List<SubAgent> subAgents) {
		this.subAgents = subAgents;
	}



	public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    
    
}
