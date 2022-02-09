package com.portfolio.shoppingmall.repository;

import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.CartDto;
import com.portfolio.shoppingmall.dto.MemberDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartItemRepository extends JpaRepository<Cart,Long> {

    @Query("select i from Items i join i.itemsList")
    List<Items> findCart();

    @Query("select c from Cart c where c.item_id = : item_id")
    Items findByItemId(@Param("item_id") Long item_id);



}
