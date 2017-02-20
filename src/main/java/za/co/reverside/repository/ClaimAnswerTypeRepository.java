package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.ClaimAnswerType;

public interface ClaimAnswerTypeRepository extends JpaRepository<ClaimAnswerType, Long> {
    
}
