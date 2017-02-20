package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.History;


public interface HistoryRepository extends JpaRepository<History, Long>{
    
}
