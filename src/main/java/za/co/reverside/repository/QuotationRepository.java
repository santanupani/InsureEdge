
package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.Quotation;
import za.co.reverside.domain.QuotationRequest;

@Repository
public interface QuotationRepository extends JpaRepository<Quotation, Long> { 
    
    public Quotation findByQuotationRequest(QuotationRequest quotationRequest);
}
