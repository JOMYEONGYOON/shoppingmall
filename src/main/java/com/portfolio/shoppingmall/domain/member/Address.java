package com.portfolio.shoppingmall.domain.member;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    private String recipient;

    private String phone;

    private String address;

    private String detailedAddress;

    private String zipcode;

    private boolean selection;

//    @Builder
//    public Address(Member member, String recipient, String phone, String address, String detailedAddress) {
//        this.member = member;
//        this.recipient = recipient;
//        this.phone = phone;
//        this.address = address;
//        this.detailedAddress = detailedAddress;
//    }



}
