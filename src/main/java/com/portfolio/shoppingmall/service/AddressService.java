package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.member.Address;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AddressService {

    List<Address> findByMember_id(Long id);

}
