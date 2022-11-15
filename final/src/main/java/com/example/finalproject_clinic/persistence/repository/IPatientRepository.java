package com.example.finalproject_clinic.persistence.repository;

import com.example.finalproject_clinic.persistence.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IPatientRepository extends JpaRepository<Patient, Long> {

    @Query("SELECT p FROM Patient p WHERE p.dni = ?1")
    Optional<Patient> findByDni(String dni);

    @Query("SELECT p FROM Patient p WHERE p.name = ?1")
    Optional<Patient> findByName(String name);
}
