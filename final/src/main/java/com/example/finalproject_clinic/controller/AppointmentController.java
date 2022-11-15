package com.example.finalproject_clinic.controller;

import com.example.finalproject_clinic.exceptions.BadRequestException;
import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.AppointmentDto;
import com.example.finalproject_clinic.service.impl.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping
    public ResponseEntity getAllAppointments() throws ResourceNotFoundException {
        List<AppointmentDto> appointmentDtoList = appointmentService.findAll();
        return ResponseEntity.ok(appointmentDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getAppointmentById(@PathVariable Long id) throws ResourceNotFoundException {
        AppointmentDto appointmentDto = appointmentService.findById(id);
        return new ResponseEntity<>(appointmentDto, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createAppointment(@RequestBody AppointmentDto appointmentDto) throws ResourceNotFoundException, BadRequestException {
        appointmentService.save(appointmentDto);
        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAppointment(@PathVariable Long id) throws ResourceNotFoundException {
        appointmentService.deleteById(id);
        return ResponseEntity.ok("Appointment deleted");
    }

    @PutMapping("/{id}")
    public ResponseEntity updateAppointmen(@RequestBody AppointmentDto appointmentDto) throws ResourceNotFoundException {
        appointmentService.update(appointmentDto);
        return ResponseEntity.ok("Appointment updated");
    }
}





