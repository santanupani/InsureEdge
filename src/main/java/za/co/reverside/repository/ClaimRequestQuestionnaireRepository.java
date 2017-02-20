package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.ClaimAnswer;


public interface ClaimRequestQuestionnaireRepository extends JpaRepository<ClaimAnswer, Long>{
    
}
