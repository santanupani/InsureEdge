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
@Table(name = "questionnaires")
public class Questionnaire {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    Product product;

    @Column(name = "sequence_number")
    private Long sequenceNumber;

    @Column(name = "question")
    private String question;

    @ManyToOne
    @JoinColumn(name = "answer_type_id")
    private AnswerType answertype;
   
    @OneToMany(mappedBy = "questionnaire")
    private List<AnswerValue> answerValues;

    @Column(name = "depends_on")
    private Long dependsOn;

    @Column(name = "on_answer")
    private String onAnswer;
    
    @Column(name = "is_required")
    private Boolean isRequired;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public AnswerType getAnswertype() {
        return answertype;
    }

    public void setAnswertype(AnswerType answertype) {
        this.answertype = answertype;
    }

    public Long getDependsOn() {
        return dependsOn;
    }

    public void setDependsOn(Long dependsOn) {
        this.dependsOn = dependsOn;
    }

    public String getOnAnswer() {
        return onAnswer;
    }

    public void setOnAnswer(String onAnswer) {
        this.onAnswer = onAnswer;
    }

    public List<AnswerValue> getAnswerValues() {
        return answerValues;
    }

    public void setAnswerValues(List<AnswerValue> answerValues) {
        this.answerValues = answerValues;
    }

    public Boolean getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(Boolean isRequired) {
        this.isRequired = isRequired;
    }

    
    
    

}
