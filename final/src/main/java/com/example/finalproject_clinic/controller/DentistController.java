package com.example.finalproject_clinic.controller;

import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.DentistDto;
import com.example.finalproject_clinic.service.impl.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentists")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @GetMapping
    public ResponseEntity getAllDentists() throws ResourceNotFoundException {
        List<DentistDto> dentistDtoList = dentistService.findAll();
        return ResponseEntity.ok(dentistDtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity getDentist(@PathVariable Long id) throws ResourceNotFoundException {
        DentistDto dentist = dentistService.findById(id);
        return new ResponseEntity<>(dentist, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity createDentist(@RequestBody DentistDto dentistDto) throws ResourceNotFoundException {
        dentistService.save(dentistDto);
        return ResponseEntity.ok(HttpStatus.CREATED);

    }

    @PutMapping("/{id}")
    public ResponseEntity updateDentist(@RequestBody DentistDto dentistDto, @PathVariable Long id) throws ResourceNotFoundException {
        dentistService.update(dentistDto);
        return ResponseEntity.ok("Patient updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteDentist(@PathVariable Long id) throws ResourceNotFoundException {
        dentistService.deleteById(id);
        return ResponseEntity.ok("Dentist deleted");
    }
}
