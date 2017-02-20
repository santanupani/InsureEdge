
package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.ClaimRequest;


public interface ClaimRequestRepository extends JpaRepository<ClaimRequest, Long>{
    // public QuotationRequest findByReference(String reference);
    
    public  ClaimRequest findByClaimNumber(String claimNumber);
}
