package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.Carrier;

@Repository
public interface CarrierRepository extends JpaRepository<Carrier, Long> {
    
    public  Carrier findByDescription(String description);

}
