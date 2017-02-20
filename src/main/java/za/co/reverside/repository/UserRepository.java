package za.co.reverside.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import za.co.reverside.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
      
      public User  findByUserNameAndPassword(String userName, String password);
      
      public User findByUserName(String userName);
      
      public User findByRole(String role);
}


