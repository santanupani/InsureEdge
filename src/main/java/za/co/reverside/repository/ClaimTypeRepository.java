
package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.ClaimType;

public interface ClaimTypeRepository extends JpaRepository<ClaimType, Long> {
   
}
