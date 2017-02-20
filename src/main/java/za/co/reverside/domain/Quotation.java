
package za.co.reverside.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
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

@Entity
@Table(name = "quotations")
public class Quotation {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "quotation_request_id")
    private QuotationRequest quotationRequest;
    
    @Column(name = "created_date")
    private Date createdDate;
    
    @Column(name = "expired_date")
    private Date expiredDate;
    
    @Column(name = "note")
    private String note;
    
    @Column(name = "cover")
    private String cover;
    
    @Column(name = "premium")
    private String premium;

    @OneToMany(mappedBy = "quotation", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<QuotationOption> quotationOptions;

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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpired(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
    public String getCover(){
    	return cover;
    }
    
    public void setCover(String cover){
    	this.cover = cover;
    }
    
    public String getPremium(){
    	return premium;
    }
    
    public void setPremium(String premium){
    	this.premium = premium;
    }
    
    public List<QuotationOption> getQuotationOptions() {
        if(quotationOptions==null) quotationOptions = new ArrayList<QuotationOption>();
        return quotationOptions;
    }

    public void setQuotationOptions(List<QuotationOption> quotationOptions) {
        this.quotationOptions = quotationOptions;
    }
}
