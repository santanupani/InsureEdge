
package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import za.co.reverside.domain.Broker;

@Repository
public interface BrokerRepository extends JpaRepository<Broker, Long> {
    
}
