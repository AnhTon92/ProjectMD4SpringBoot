package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAllByUserId(Long userId);
}
