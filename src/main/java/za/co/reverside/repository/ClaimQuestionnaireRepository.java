package za.co.reverside.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.ClaimQuestionnaire;
import za.co.reverside.domain.ClaimType;


public interface ClaimQuestionnaireRepository extends JpaRepository<ClaimQuestionnaire, Long> {
 
    public List<ClaimQuestionnaire> findByClaimType(ClaimType claimType);
}
