package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.AnswerType;

@Repository
public interface AnswerTypeRepository extends JpaRepository<AnswerType, Long> {

}
