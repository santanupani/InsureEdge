package za.co.reverside.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.Product;
import za.co.reverside.domain.Questionnaire;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Long> {
	
	public List<Questionnaire> findByProduct(Product product);

}
