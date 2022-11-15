package com.example.finalproject_clinic.service.impl;

import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.PatientDto;
import com.example.finalproject_clinic.persistence.entity.Patient;
import com.example.finalproject_clinic.persistence.repository.IPatientRepository;
import com.example.finalproject_clinic.service.IPatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientService implements IPatientService {

    @Autowired
    private IPatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PatientDto findById(Long id) throws ResourceNotFoundException {
        if (patientRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        Patient patient = patientRepository.getById(id);
        return mapToDto(patient);
    }

    @Override
    public PatientDto save(PatientDto patientDto) throws ResourceNotFoundException {
        if (patientDto == null) {
            throw new ResourceNotFoundException("Patient not created");
        }
        Patient patient = mapToEntity(patientDto);
        Patient patientSaved = patientRepository.save(patient);
        return mapToDto(patientSaved);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (patientRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Patient not found with id: " + id);
        }
        patientRepository.deleteById(id);
    }

    @Override
    public PatientDto update(PatientDto patientDto) throws ResourceNotFoundException {
        if (patientRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Patient not found with id: " + patientDto.getId());
        }
        Patient patient = mapToEntity(patientDto);
        Patient patientUpdated = patientRepository.save(patient);
        return mapToDto(patientUpdated);
    }

    @Override
    public List<PatientDto> findAll() throws ResourceNotFoundException {
        if (patientRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Patients not found");
        }
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    //---- Mapper ----//
    private PatientDto mapToDto(Patient patient) {
        return modelMapper.map(patient, PatientDto.class);
    }

    private Patient mapToEntity(PatientDto patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }
}
