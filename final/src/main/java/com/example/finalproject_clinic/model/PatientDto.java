package com.example.finalproject_clinic.model;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PatientDto {

    private Long id;
    private String name;
    private String lastName;
    private String dni;
    private LocalDate admissionDate;
    private AddressDto address;

    public PatientDto(String name, String lastName, String dni, LocalDate admissionDate, AddressDto address) {
        this.name = name;
        this.lastName = lastName;
        this.dni = dni;
        this.admissionDate = admissionDate;
        this.address = address;
    }
}
