package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.Commission;

public interface CommissionRepository extends JpaRepository<Commission,Long>{

}
