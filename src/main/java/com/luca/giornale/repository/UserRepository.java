package com.luca.giornale.repository;

import com.luca.giornale.entities.User;
import com.luca.giornale.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
   User findByEmail (String email);
   @Query("SELECT u FROM User u WHERE u.email = :email")
   User findByUserEmail(@Param("email") String email);
   User findByRole(Role role);
}
