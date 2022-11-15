package com.example.finalproject_clinic.service;

import com.example.finalproject_clinic.exceptions.BadRequestException;
import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.AddressDto;
import com.example.finalproject_clinic.model.AppointmentDto;
import com.example.finalproject_clinic.model.DentistDto;
import com.example.finalproject_clinic.model.PatientDto;
import com.example.finalproject_clinic.service.impl.AppointmentService;
import com.example.finalproject_clinic.service.impl.DentistService;
import com.example.finalproject_clinic.service.impl.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class AppointmentServiceTest {

    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DentistService dentistService;

    private AppointmentDto appointmentDto;
    private PatientDto patientDto;
    private DentistDto dentistDto;

    @BeforeEach
    public void setUp() {
        AddressDto addressDto = new AddressDto("Calle de la Calle", "1235", "12345", "123456789"); // create address
        dentistDto = new DentistDto("Juan", "Perez", "AE060CA"); // create dentist
        patientDto = new PatientDto("Juan", "Perez", "123456789", LocalDate.now(), addressDto); // create patient
    }

    @Test
    public void createAppointmentTest() throws ResourceNotFoundException, BadRequestException {
        PatientDto patientDto = patientService.save(this.patientDto);
        DentistDto dentistDto = dentistService.save(this.dentistDto);
        appointmentDto = new AppointmentDto(LocalDate.now(), patientDto, dentistDto);
        appointmentService.save(appointmentDto);
        assertNotNull(appointmentDto);
    }

    @Test
    public void deleteAppointmentTest() throws ResourceNotFoundException, BadRequestException {
        DentistDto dentistDto = dentistService.save(this.dentistDto); // save dentist
        PatientDto patientDto = patientService.save(this.patientDto); // save patient
        appointmentDto = new AppointmentDto(LocalDate.now(), patientDto, dentistDto); // create appointment
        AppointmentDto appointmentDto = appointmentService.save(this.appointmentDto); // save appointment
        appointmentService.deleteById(appointmentDto.getId()); // delete appointment
        assertThrows(ResourceNotFoundException.class, () -> appointmentService.findById(appointmentDto.getId())); // check if appointment is deleted
    }

    @Test
    public void updateAppointmentTest() throws ResourceNotFoundException, BadRequestException {
        DentistDto dentistDto = dentistService.save(this.dentistDto); // save dentist
        PatientDto patientDto = patientService.save(this.patientDto); // save patient
        AppointmentDto appointmentDto = new AppointmentDto(LocalDate.now(), patientDto, dentistDto); // create appointment
        AppointmentDto updatedAppointmentDto = appointmentService.save(appointmentDto); // save appointment
        updatedAppointmentDto.setDateAppointment(LocalDate.of(2020, 1, 1));  // update appointment date
        assertNotEquals(appointmentDto.getDateAppointment(), updatedAppointmentDto.getDateAppointment()); // compare dates before and after update
    }

}
