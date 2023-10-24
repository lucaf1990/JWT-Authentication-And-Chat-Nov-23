package com.luca.giornale.repository;

import com.luca.giornale.entities.User;
import com.luca.giornale.enums.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long>  {
   UserDetails findByEmail (String email);
   User findByRole(Role role);
}
