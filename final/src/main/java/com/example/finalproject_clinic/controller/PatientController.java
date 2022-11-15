package com.example.finalproject_clinic.controller;

import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.PatientDto;
import com.example.finalproject_clinic.service.impl.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/{id}")
    public ResponseEntity getPatient(@PathVariable Long id) throws ResourceNotFoundException {
        PatientDto patientDtoFound = patientService.findById(id);
        return new ResponseEntity<>(patientDtoFound, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createPatient(@RequestBody PatientDto patientDto) throws ResourceNotFoundException {
        patientDto.setAdmissionDate(LocalDate.now());
        patientService.save(patientDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity getAllPatients() throws ResourceNotFoundException {
        List<PatientDto> patientDtoList = patientService.findAll();
        return ResponseEntity.ok(patientDtoList);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletePatient(@PathVariable Long id) throws ResourceNotFoundException {
        patientService.deleteById(id);
        return ResponseEntity.ok("Patient deleted");

    }

    @PutMapping("/{id}")
    public ResponseEntity updatePatient( @RequestBody PatientDto patientDto) throws ResourceNotFoundException {
        patientService.update(patientDto);
        return ResponseEntity.ok("Patient updated");
    }

}
