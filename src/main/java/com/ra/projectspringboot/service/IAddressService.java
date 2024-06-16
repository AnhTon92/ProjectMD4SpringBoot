package com.ra.projectspringboot.service;

import com.ra.projectspringboot.dto.request.AddressRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Address;

import java.util.List;

public interface IAddressService {
    List<Address> getAllAddress();

    Address getAddressById(Long addressId) throws CustomException;

    Address addNewAddress(AddressRequest addressRequest) throws CustomException;

    void deleteAddressById(Long addressId) throws CustomException;

}
