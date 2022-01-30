package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.Address;
import com.portfolio.shoppingmall.domain.Member;
import com.portfolio.shoppingmall.domain.Order;
import com.portfolio.shoppingmall.domain.OrderStatus;
import com.portfolio.shoppingmall.domain.item.Book;
import com.portfolio.shoppingmall.domain.item.Item;
import com.portfolio.shoppingmall.exception.NotEnoughStockException;
import com.portfolio.shoppingmall.repository.OrderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository OrderRepository;


    @Test
    public void 상품_주문() throws Exception {
        //given
        Member member = getMember();
        Book book = getBook("시골 jpa", 10000, 10);

        int orderCount = 2;
        //when
        Long orderId = orderService.order(member.getId(), book.getId(), 2);
        Order getOrder = OrderRepository.findOne(orderId);
        //then

        Assertions.assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.ORDER);
        Assertions.assertThat(getOrder.getOrderItems().size()).isEqualTo(1);
        Assertions.assertThat(10000 * orderCount).isEqualTo(getOrder.getTotalPrice());
        Assertions.assertThat(8).isEqualTo(book.getStockQuantity());


    }



    @Test
    public void 주문취소() throws Exception {
        Member member = getMember();
        Book item =  getBook("시골 jpa",10000, 10);

        int orderCount = 2;
        Long orderId = orderService.order(member.getId(), item.getId(), orderCount);

        orderService.cancelOrder(orderId);
        Order getOrder = OrderRepository.findOne(orderId);
        Assertions.assertThat(getOrder.getStatus()).isEqualTo(OrderStatus.CANCEL);
        Assertions.assertThat(10).isEqualTo(item.getStockQuantity());
    }

    @Test
    public void 상품주문_재고수량초과() throws Exception {
        //given

        //then
        try{
            Member member = getMember();
            Item item = getBook("시골 jpa",10000, 10);
            int orderCount = 10;
            //when
            orderService.order(member.getId(), item.getId(), orderCount);
        }catch (NotEnoughStockException e) {
            e.printStackTrace();
        }
//        fail("재고 수량 부족 예외가 발행해야 한다.");


    }

    private Book getBook(String name, int price , int stockQuantity) {
        Book book = new Book();
        book.setName(name);
        book.setPrice(price);
        book.setStockQuantity(stockQuantity);
        em.persist(book);
        return book;
    }

    private Member getMember() {
        Member member = new Member();
        member.setName("회원1");
        member.setAddress(new Address("서울", "강가", "123-123"));
        em.persist(member);
        return member;
    }

}