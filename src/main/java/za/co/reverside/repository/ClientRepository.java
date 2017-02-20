package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.Client;

public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
