package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.AnswerValue;

@Repository
public interface AnswerValueRepository extends JpaRepository<AnswerValue, Long> {

}
