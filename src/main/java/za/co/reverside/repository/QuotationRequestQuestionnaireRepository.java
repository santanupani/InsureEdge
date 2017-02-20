
package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.Answer;


@Repository
public interface QuotationRequestQuestionnaireRepository extends JpaRepository<Answer, Long> {
       
}
