package com.example.finalproject_clinic.persistence.repository;

import com.example.finalproject_clinic.persistence.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface IAppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("SELECT a FROM Appointment a WHERE a.dateAppointment = ?1")
    Optional<Appointment> findByDateAppointment(String dateAppointment);


}

