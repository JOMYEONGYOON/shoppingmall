package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.Address;

import java.util.List;
import java.util.Optional;

public interface AddressService {

    List<Address> findByMember_id(Long id);

    void save(Address address);

    Optional<Address> findById(Long id);

    void delete(Long id);

    List<Address> pickAddress(Long id);


}
