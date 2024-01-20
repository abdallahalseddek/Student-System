package com.student.system;

import com.student.system.security.entity.User;
import com.student.system.security.enums.Role;
import com.student.system.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;
    public static void main(String[] args) {
        SpringApplication.run(StudentApplication.class, args);
    }

   public void run(String... args){
       User adminAccount = userRepository.findByRole(Role.ADMIN);
       if (adminAccount == null){
           User user = new User();
           user.setEmail("admin@domain.com");
           user.setFirstname("admin");
           user.setPassword("admin");
           user.setPassword("admin");
           user.setRole(Role.ADMIN);
           userRepository.save(user);
       }
   }
}
