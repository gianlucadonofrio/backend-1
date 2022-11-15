package com.example.finalproject_clinic.persistence.repository;

import com.example.finalproject_clinic.persistence.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface IAddressRepository extends JpaRepository<Address, Long> {
    @Query("SELECT a FROM Address a WHERE a.street = ?1 ")
    Optional<Address> findByAddress(String street);
}
