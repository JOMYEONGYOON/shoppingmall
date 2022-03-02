package com.portfolio.shoppingmall.repository;

import com.portfolio.shoppingmall.domain.member.Address;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {


    List<Address> findByMember_id(@Param(value = "member_id") Long member_id);


    @Query("select a from Address a where a.member.id = :id")
    List<Address> pickAddress(@Param("id") Long id);


}
