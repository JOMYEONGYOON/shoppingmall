package com.portfolio.shoppingmall.test;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TestMemberTest {

    @Autowired
    EntityManager em;

    @Autowired
    ProductRepository productRepository;

    @Test
    public void testEntity() throws Exception {
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(1), LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        List<Product> allByUpdatedAtBetween = productRepository.findAllByUpdatedAtBetween(start, end);
        for (Product product : allByUpdatedAtBetween) {
            System.out.println("product = " + product);
        }
    }

}