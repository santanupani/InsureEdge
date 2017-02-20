
package za.co.reverside.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.model.QuotationRequestCommandModel;
import za.co.reverside.Service;
import za.co.reverside.domain.LocationOption;

@Repository
public interface LocationOptionRepository extends JpaRepository<LocationOption, Long> {

    public LocationOption findById(Long id);
    
}
