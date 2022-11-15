package com.example.finalproject_clinic.service;

import com.example.finalproject_clinic.exceptions.ResourceNotFoundException;
import com.example.finalproject_clinic.model.AddressDto;
import com.example.finalproject_clinic.service.impl.AddressService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AddressServiceTest {

    @Autowired
    private AddressService addressService;

    private AddressDto addressDto;


    @BeforeEach
    public void setUp() {
        addressDto = new AddressDto("Calle Siempreviva", "1235", "Springfield", "Spring");

    }

    @Test
    public void createAddressTest() throws ResourceNotFoundException {
       AddressDto addressDto = addressService.save(this.addressDto);
        assert (addressDto.getId() != null);
    }

    @Test
    public void updateAddressTest() throws ResourceNotFoundException {
        AddressDto addressDto = addressService.save(this.addressDto);
        addressDto.setStreet("Calle Nueva");
        AddressDto addressDtoUpdated = addressService.update(addressDto);
        assertNotNull(addressDtoUpdated);
        assertEquals(addressDtoUpdated.getStreet(), "Calle Nueva");
    }

    @Test
    public void getAllAddressTest() throws ResourceNotFoundException {
        addressService.save(this.addressDto);
        assertNotNull(addressService.findAll());
    }

    @Test
    public void deleteAddressTest() throws ResourceNotFoundException {
        AddressDto addressDto = addressService.save(this.addressDto);
        addressService.deleteById(addressDto.getId());
        assertThrows(ResourceNotFoundException.class, () -> addressService.findById(addressDto.getId()));
    }

}
