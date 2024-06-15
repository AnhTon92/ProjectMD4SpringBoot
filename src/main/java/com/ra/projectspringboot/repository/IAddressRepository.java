package com.ra.projectspringboot.repository;

import com.ra.projectspringboot.model.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAddressRepository extends JpaRepository<Address,Long> {
}
