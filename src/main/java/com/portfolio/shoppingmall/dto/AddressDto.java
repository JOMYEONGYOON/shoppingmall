package com.portfolio.shoppingmall.dto;

import com.portfolio.shoppingmall.domain.Member;
import lombok.Data;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
public class AddressDto {
    private Long id;

    private Member member;

    private String recipient;

    private String phone;

    private String address;

    private String detailedAddress;

    private String zipcode;

    private boolean selection;

}
