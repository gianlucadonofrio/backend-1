package com.example.finalproject_clinic.service.impl;

import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.AddressDto;
import com.example.finalproject_clinic.persistence.entity.Address;
import com.example.finalproject_clinic.persistence.repository.IAddressRepository;
import com.example.finalproject_clinic.service.IAddressService;
import com.sun.istack.NotNull;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressService implements IAddressService {

    @Autowired
    private IAddressRepository addressRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public AddressDto findById(@NotNull Long id) throws ResourceNotFoundException {
        if (addressRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Address not found");
        }
        Address address = addressRepository.getById(id);
        return mapToDto(address);
    }

    @Override
    public AddressDto save(AddressDto addressDto) throws ResourceNotFoundException {
        if (addressDto == null) {
            throw new ResourceNotFoundException("Address not created");
        }
        Address address = mapToEntity(addressDto);
        return mapToDto(addressRepository.save(address));
    }

    @Override
    public void deleteById(@NotNull Long id) throws ResourceNotFoundException {
        if (addressRepository.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Address not found with id: " + id);
        }
        addressRepository.deleteById(id);

    }

    @Override
    public AddressDto update( AddressDto addressDto) throws ResourceNotFoundException {
        if (addressRepository.findById(addressDto.getId()).isEmpty()) {
            throw new ResourceNotFoundException("Address not found with ID: " + addressDto.getId());
        }
        Address address = mapToEntity(addressDto);
        Address addressSaved = addressRepository.save(address);
        return mapToDto(addressSaved);
    }

    @Override
    public List<AddressDto> findAll() throws ResourceNotFoundException {
        if (addressRepository.findAll().isEmpty()) {
            throw new ResourceNotFoundException("Addresses not found");
        }
        List<Address> addressesList = addressRepository.findAll();
        return addressesList.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    //---- Mapper ----//
    private AddressDto mapToDto(Address address) {
        return modelMapper.map(address, AddressDto.class);
    }

    private Address mapToEntity(AddressDto addressDto) {
        return modelMapper.map(addressDto, Address.class);
    }


}
