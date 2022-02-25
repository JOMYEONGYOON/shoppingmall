package com.portfolio.shoppingmall.repository;

import com.portfolio.shoppingmall.domain.member.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {

    List<Address> findByMember_id(@Param(value = "member_id") Long member_id);

}
