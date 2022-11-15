package com.example.finalproject_clinic.service;

import com.example.finalproject_clinic.exceptions.BadRequestException;
import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;

import java.util.List;

public interface ICRUDService <T> {
    T findById(Long id) throws ResourceNotFoundException;
    T save ( T t ) throws ResourceNotFoundException, BadRequestException;
    void deleteById(Long id) throws ResourceNotFoundException;
    T update(T t) throws ResourceNotFoundException;
    List<T> findAll() throws ResourceNotFoundException;
}


