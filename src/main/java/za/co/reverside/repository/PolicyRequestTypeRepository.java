package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.PolicyRequestType;

public interface PolicyRequestTypeRepository extends JpaRepository<PolicyRequestType, Long> {
	public PolicyRequestType findByReference(String reference);
}
