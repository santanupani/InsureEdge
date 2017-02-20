package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.ClaimRequest;
import za.co.reverside.domain.ReleaseForm;

@Repository
public interface ReleaseFormRepository extends JpaRepository<ReleaseForm, Long>{
    
    public ClaimRequest findByClaimRequest(ClaimRequest claimRequest);

    public ReleaseForm findByClaimNumber(String claimNumber);
    
}
