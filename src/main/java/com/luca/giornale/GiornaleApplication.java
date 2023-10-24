package com.luca.giornale;

import com.luca.giornale.entities.User;
import com.luca.giornale.enums.Role;
import com.luca.giornale.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication

public class GiornaleApplication implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(GiornaleApplication.class, args);
    }
public void run(String... args){
    User adminAcc= userRepository.findByRole(Role.ADMIN);
    if(null==adminAcc){
        User user= new User();
        user.setEmail("admin@gmail.com");
        user.setFirstName("luca");
        user.setLastName("forma");
        user.setRole(Role.ADMIN);
        user.setPassword(new BCryptPasswordEncoder().encode("admin"));
    userRepository.save(user);
    }
}

}
