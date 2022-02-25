package com.portfolio.shoppingmall.service.impl;

import com.portfolio.shoppingmall.domain.member.Address;
import com.portfolio.shoppingmall.repository.AddressRepository;
import com.portfolio.shoppingmall.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    public List<Address> findByMember_id(Long id) {
        return addressRepository.findByMember_id(id);
    }
}
