
package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.BankAccount;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
    
}
