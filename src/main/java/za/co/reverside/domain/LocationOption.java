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
@Table(name = "location_options")
public class LocationOption {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "quotation_request_id")
    private QuotationRequest quotationRequest;

    @Column(name = "commodity")
    private String commodity;

    @Column(name = "distance")
    private String distance;

    @Column(name = "from_location")
    private String fromLocation;

    @Column(name = "to_location")
    private String toLocation;

    @Column(name = "max_limit")
    private double limit;

    @Column(name = "static_limit")
    private double staticLimit;

    @Column(name = "sabs_category")
    private String sabsCategory;

    @Column(name = "professional_carriers")
    private String professionalCarriers;

    @Column(name = "duration")
    private String duration;

    @Column(name = "static_max_amount")
    private String staticMaxAmount;

    @Column(name = "total_value")
    private String totalValue;
    
    @Column(name = "transit_total_value")
    private String transitTotalValue;

    @Column(name = "total_value_static")
    private String totalvaluestatic;

    @Column(name = "is_first_loss_cover")
    private boolean isFirstLossCover;

    @Column(name = "is_goods_moved")
    private boolean isGoodsMoved;
    
    @Column(name = "is_goods_moved_static")
    private boolean isGoodsMovedStatic;

    @Column(name = "is_service_carrier")
    private boolean isServiceCarrier;

    @Column(name = "carrier_name")
    private String carrierName;

    @Column(name = "specific_carrier")
    private String specificCarrier;

    @Column(name = "goods_description")
    private String goodsDescription;

    @Column(name = "is_store_vault")
    private boolean isStoreVault;

    @Column(name = "is_concrete_secured")
    private boolean isConcreteSecured;

    @Column(name = "is_seismic_detector")
    private boolean isSeismicDetector;

    @Column(name = "is_cctv")
    private boolean isCctv;

    @Column(name = "is_alarmed")
    private boolean isAlarmed;

    @Column(name = "storage_type")
    private String storageType;

    @Column(name = "vault_address")
    private String vaultAddress;

    @Column(name = "other_secure_means")
    private String otherSecureMeans;

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

    public String getCommodity() {
        return commodity;
    }

    public void setCommodity(String commodity) {
        this.commodity = commodity;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getFromLocation() {
        return fromLocation;
    }

    public void setFromLocation(String fromLocation) {
        this.fromLocation = fromLocation;
    }

    public String getToLocation() {
        return toLocation;
    }

    public void setToLocation(String toLocation) {
        this.toLocation = toLocation;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    public double getStaticLimit() {
        return staticLimit;
    }

    public void setStaticLimit(double staticLimit) {
        this.staticLimit = staticLimit;
    }

    public String getSabsCategory() {
        return sabsCategory;
    }

    public void setSabsCategory(String sabsCategory) {
        this.sabsCategory = sabsCategory;
    }

    public String getProfessionalCarriers() {
        return professionalCarriers;
    }

    public void setProfessionalCarriers(String professionalCarriers) {
        this.professionalCarriers = professionalCarriers;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStaticMaxAmount() {
        return staticMaxAmount;
    }

    public void setStaticMaxAmount(String staticMaxAmount) {
        this.staticMaxAmount = staticMaxAmount;
    }

    public String getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(String totalValue) {
        this.totalValue = totalValue;
    }

    public String getTotalvaluestatic() {
        return totalvaluestatic;
    }

    public void setTotalvaluestatic(String totalvaluestatic) {
        this.totalvaluestatic = totalvaluestatic;
    }

    public boolean isFirstLossCover() {
        return isFirstLossCover;
    }

    public void setFirstLossCover(boolean isFirstLossCover) {
        this.isFirstLossCover = isFirstLossCover;
    }

    public boolean isServiceCarrier() {
        return isServiceCarrier;
    }

    public void setServiceCarrier(boolean isServiceCarrier) {
        this.isServiceCarrier = isServiceCarrier;
    }

    public String getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(String carrierName) {
        this.carrierName = carrierName;
    }

    public String getSpecificCarrier() {
        return specificCarrier;
    }

    public void setSpecificCarrier(String specificCarrier) {
        this.specificCarrier = specificCarrier;
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription;
    }

    public boolean isStoreVault() {
        return isStoreVault;
    }

    public void setStoreVault(boolean isStoreVault) {
        this.isStoreVault = isStoreVault;
    }

    public boolean isConcreteSecured() {
        return isConcreteSecured;
    }

    public void setConcreteSecured(boolean isConcreteSecured) {
        this.isConcreteSecured = isConcreteSecured;
    }

    public boolean isSeismicDetector() {
        return isSeismicDetector;
    }

    public void setSeismicDetector(boolean isSeismicDetector) {
        this.isSeismicDetector = isSeismicDetector;
    }

    public boolean isCctv() {
        return isCctv;
    }

    public void setCctv(boolean isCctv) {
        this.isCctv = isCctv;
    }

    public boolean isAlarmed() {
        return isAlarmed;
    }

    public void setAlarmed(boolean isAlarmed) {
        this.isAlarmed = isAlarmed;
    }

    public String getStorageType() {
        return storageType;
    }

    public void setStorageType(String storageType) {
        this.storageType = storageType;
    }

    public String getVaultAddress() {
        return vaultAddress;
    }

    public void setVaultAddress(String vaultAddress) {
        this.vaultAddress = vaultAddress;
    }

    public String getOtherSecureMeans() {
        return otherSecureMeans;
    }

    public void setOtherSecureMeans(String otherSecureMeans) {
        this.otherSecureMeans = otherSecureMeans;
    }

    public boolean isGoodsMoved() {
        return isGoodsMoved;
    }

    public void setGoodsMoved(boolean isGoodsMoved) {
        this.isGoodsMoved = isGoodsMoved;
    }

    public String getTransitTotalValue() {
        return transitTotalValue;
    }

    public void setTransitTotalValue(String transitTotalValue) {
        this.transitTotalValue = transitTotalValue;
    }

    public boolean isIsFirstLossCover() {
        return isFirstLossCover;
    }

    public void setIsFirstLossCover(boolean isFirstLossCover) {
        this.isFirstLossCover = isFirstLossCover;
    }

    public boolean isIsGoodsMoved() {
        return isGoodsMoved;
    }

    public void setIsGoodsMoved(boolean isGoodsMoved) {
        this.isGoodsMoved = isGoodsMoved;
    }

    public boolean isIsServiceCarrier() {
        return isServiceCarrier;
    }

    public void setIsServiceCarrier(boolean isServiceCarrier) {
        this.isServiceCarrier = isServiceCarrier;
    }

    public boolean isIsStoreVault() {
        return isStoreVault;
    }

    public void setIsStoreVault(boolean isStoreVault) {
        this.isStoreVault = isStoreVault;
    }

    public boolean isIsConcreteSecured() {
        return isConcreteSecured;
    }

    public void setIsConcreteSecured(boolean isConcreteSecured) {
        this.isConcreteSecured = isConcreteSecured;
    }

    public boolean isIsSeismicDetector() {
        return isSeismicDetector;
    }

    public void setIsSeismicDetector(boolean isSeismicDetector) {
        this.isSeismicDetector = isSeismicDetector;
    }

    public boolean isIsCctv() {
        return isCctv;
    }

    public void setIsCctv(boolean isCctv) {
        this.isCctv = isCctv;
    }

    public boolean isIsAlarmed() {
        return isAlarmed;
    }

    public void setIsAlarmed(boolean isAlarmed) {
        this.isAlarmed = isAlarmed;
    }

    public boolean isIsGoodsMovedStatic() {
        return isGoodsMovedStatic;
    }

    public void setIsGoodsMovedStatic(boolean isGoodsMovedStatic) {
        this.isGoodsMovedStatic = isGoodsMovedStatic;
    }
    

}
