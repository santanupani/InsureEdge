
package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.QuotationOption;

@Repository
public interface QuotationOptionRepository extends JpaRepository<QuotationOption, Long> { 

}
