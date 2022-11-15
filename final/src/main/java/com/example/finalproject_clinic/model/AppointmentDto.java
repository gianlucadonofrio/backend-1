package com.example.finalproject_clinic.model;

import com.example.finalproject_clinic.persistence.entity.Dentist;
import com.example.finalproject_clinic.persistence.entity.Patient;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class AppointmentDto {

    private Long id;
    private LocalDate dateAppointment;
    private PatientDto patient;
    private DentistDto dentist;

    public AppointmentDto(LocalDate dateAppointment, PatientDto patient, DentistDto dentist) {
        this.dateAppointment = dateAppointment;
        this.patient = patient;
        this.dentist = dentist;
    }

}
