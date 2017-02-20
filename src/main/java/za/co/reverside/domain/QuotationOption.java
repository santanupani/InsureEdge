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
@Table(name = "quotation_options")
public class QuotationOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quotation_id")
    private Quotation quotation;

    @Column(name = "location")
    private String location;

    
    @Column(name = "limits")
    private String limit;

    @Column(name = "commodity")
    private String commodity;

    @Column(name = "cover")
    private String cover;

    @Column(name = "duration")
    private String duration;

    @Column(name = "excess")
    private String excess;

    @Column(name = "premium")
    private String premium;

    @Column(name = "cross_pavement")
    private String pavements;

    @Column(name = "static_limit")
    private String staticLimit;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Quotation getQuotation() {
        return quotation;
    }

    public void setQuotation(Quotation quotation) {
        this.quotation = quotation;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getExcess() {
        return excess;
    }

    public void setExcess(String excess) {
        this.excess = excess;
    }

    public String getPremium() {
        return premium;
    }

    public void setPremium(String premium) {
        this.premium = premium;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPavements() {
        return pavements;
    }

    public void setPavements(String pavements) {
        this.pavements = pavements;
    }

    public String getStaticLimit() {
        return staticLimit;
    }

    public void setStaticLimit(String staticLimit) {
        this.staticLimit = staticLimit;
    }

}
