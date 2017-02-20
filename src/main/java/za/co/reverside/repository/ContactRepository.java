package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.Contact;


public interface ContactRepository extends JpaRepository<Contact, Long>{
    
}
