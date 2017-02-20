package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.RequestType;

public interface RequestTypeRepository extends JpaRepository<RequestType, Long> {

}
