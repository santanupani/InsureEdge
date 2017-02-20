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
@Table(name = "histories")
public class History {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quotation_request_id")
    private QuotationRequest quotationRequest;

    @Column(name = "event_date")
    private String eventDate;

    @Column(name = "loss_value")
    private double lossValue;

    @Column(name = "type_of_loss")
    private String typeOfLoss;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public QuotationRequest getQuotationRequest() {
        return quotationRequest;
    }

    public void setQuotationRequest(QuotationRequest quotationRequest) {
        this.quotationRequest = quotationRequest;
    }

    public String getTypeOfLoss() {
        return typeOfLoss;
    }

    public void setTypeOfLoss(String typeOfLoss) {
        this.typeOfLoss = typeOfLoss;
    }

    public double getLossValue() {
        return lossValue;
    }

    public void setLossValue(double lossValue) {
        this.lossValue = lossValue;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

}
