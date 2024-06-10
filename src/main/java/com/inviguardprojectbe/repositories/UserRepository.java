package com.inviguardprojectbe.repositories;


import com.inviguardprojectbe.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {


   //Optional<User> findById(Long id);

    List<User> findAllByEmail(String email);
    User findOneByEmail(String email);

}
