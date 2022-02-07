package com.portfolio.shoppingmall;

import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.MemberDto;
import com.portfolio.shoppingmall.repository.CartItemRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)

public class ShoppingcartTest {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private EntityManager em;

    @Test
    public void testAdd() throws Exception {
        //given
        Items items = em.find(Items.class, 4L);
        Member member = em.find(Member.class, 2L);
        Cart cart = new Cart();
        cart.setMember(member);
        cart.setItems(items);
        cart.setQuantity(1);

        Cart savecart = cartItemRepository.save(cart);
        Long savecartId = savecart.getId();
        System.out.println(savecartId);
        assertTrue(savecart.getId() >= 1L);
        
    }

}
