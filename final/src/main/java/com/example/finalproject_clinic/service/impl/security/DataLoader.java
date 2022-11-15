package com.example.finalproject_clinic.service.impl.security;

import com.example.finalproject_clinic.exceptions.BadRequestException;
import com.example.finalproject_clinic.persistence.entity.security.RolesUser;
import com.example.finalproject_clinic.persistence.entity.security.User;
import com.example.finalproject_clinic.persistence.repository.security.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private final IUserRepository userRepository;

    @Autowired
    public DataLoader(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws BadRequestException {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userRepository.save(new User("Gian", "admin", "admin", passwordEncoder.encode("admin"), RolesUser.ADMIN));
        userRepository.save(new User("Gian", "user", "user", passwordEncoder.encode("user"), RolesUser.USER));

    }
}
