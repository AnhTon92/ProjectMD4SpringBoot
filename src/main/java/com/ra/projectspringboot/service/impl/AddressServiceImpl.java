package com.ra.projectspringboot.service.impl;

import com.ra.projectspringboot.dto.request.AddressRequest;
import com.ra.projectspringboot.exception.CustomException;
import com.ra.projectspringboot.model.entity.Address;
import com.ra.projectspringboot.repository.IAddressRepository;
import com.ra.projectspringboot.service.IAddressService;
import com.ra.projectspringboot.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements IAddressService {
    private final IAddressRepository addressRepository;
    private final IUserService userService;

    @Override
    public List<Address> getAllAddress() {
        return addressRepository.findAllByUserId(userService.getCurrentUser().getId());
    }

    @Override
    public Address getAddressById(Long addressId) throws CustomException {
        return addressRepository.findById(addressId).orElseThrow(() -> new CustomException("address not found", HttpStatus.NOT_FOUND));
    }

    @Override
    public Address addNewAddress(AddressRequest addressRequest) throws CustomException {
        Address address = Address.builder()
                .user(userService.findUserById(userService.getCurrentUser().getId()))
                .fullAddress(addressRequest.getFullAddress())
                .receiveName(addressRequest.getReceiveName())
                .phone(addressRequest.getPhone())
                .build();
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddressById(Long addressId) throws CustomException {
        if (addressRepository.existsById(addressId)) {
            addressRepository.deleteById(addressId);
        }
        throw new CustomException("address not found", HttpStatus.NOT_FOUND);
    }
}
