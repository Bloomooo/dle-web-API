package com.backendkiss.backendkiss.repository;

import com.backendkiss.backendkiss.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepository extends JpaRepository<User, Integer> {

    boolean existsByUsername(String username);

    boolean existsByMail(String mail);

    User findByUsername(@Param("username") String username);

    User findByMail(@Param("mail") String mail);

}
