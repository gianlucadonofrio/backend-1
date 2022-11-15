package com.example.finalproject_clinic.service;

import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.AddressDto;
import com.example.finalproject_clinic.model.PatientDto;
import com.example.finalproject_clinic.service.impl.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class PatientServiceTest {

    @Autowired
    private PatientService patientService;

    private PatientDto patientDto;

    @BeforeEach
    public void setUp() {
        AddressDto addressDto = new AddressDto("Calle de la Calle","1235","12345","123456789");
        patientDto = new PatientDto("Juan", "Perez", "123456789", LocalDate.now(),addressDto);
    }

    @Test
    public void savePatientTest() throws ResourceNotFoundException {
      PatientDto patientDto = patientService.save(this.patientDto);
        assertNotNull(patientDto);
    }

    @Test
    public void updatePatientTest() throws ResourceNotFoundException {
        PatientDto patientDto = patientService.save(this.patientDto);
        patientDto.setName("Juanito");
        patientDto = patientService.update(patientDto);
        assertNotNull(patientDto);
        assertEquals("Juanito", patientDto.getName());
    }

    @Test
    public void deletePatientTest() throws ResourceNotFoundException {
        PatientDto patientDto = patientService.save(this.patientDto);
        patientService.deleteById(patientDto.getId());
        assertThrows(ResourceNotFoundException.class, () -> patientService.findById(patientDto.getId()));
    }

    @Test
    public void getAllPatientsTest() throws ResourceNotFoundException {
        patientService.save(this.patientDto);
        assertNotNull(patientService.findAll());
    }
}


