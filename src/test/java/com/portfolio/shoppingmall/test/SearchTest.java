package com.portfolio.shoppingmall.test;

import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.search.SearchCondition;
import com.portfolio.shoppingmall.service.ItemsService;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;

@SpringBootTest
@Transactional

public class SearchTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory queryFactory;

    @Autowired
    ItemsService itemsService;



}
