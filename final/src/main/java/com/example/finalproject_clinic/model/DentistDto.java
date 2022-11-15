package com.example.finalproject_clinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DentistDto {
    private Long id;
    private String name;
    private String lastName;
    private String enrollment;

    public DentistDto(String name, String lastName, String enrollment) {
        this.name = name;
        this.lastName = lastName;
        this.enrollment = enrollment;
    }

}

