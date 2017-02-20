
package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.QuotationRequest;

@Repository
public interface QuotationRequestRepository extends JpaRepository<QuotationRequest, Long> {
    
	public QuotationRequest findByReference(String reference);
}
