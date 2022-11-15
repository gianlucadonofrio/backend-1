package com.example.finalproject_clinic.persistence.repository.security;

import com.example.finalproject_clinic.persistence.entity.security.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String email);

}
