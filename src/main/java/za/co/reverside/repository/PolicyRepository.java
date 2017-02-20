package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.Policy;

public interface PolicyRepository extends JpaRepository<Policy,Long>{
	public Policy findByReference(String Reference);

}
