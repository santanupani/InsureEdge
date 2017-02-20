package za.co.reverside.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.Endorsement;

public interface EndorsementRepository extends JpaRepository<Endorsement, Long> {
	public List<Endorsement> findByPolicyId(Long policyId);
}
