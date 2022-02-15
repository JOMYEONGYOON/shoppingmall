package com.portfolio.shoppingmall.repository;

import com.portfolio.shoppingmall.domain.item.Items;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityManager;
import java.util.List;

@RequiredArgsConstructor
public class ItemRepositoryImpl implements ItemRepositoryCustom{

    private final EntityManager em;

//    @Override
//    public List<Items> findByNameContaining(String keyword) {
//        return em.createQuery("select i from Items i");
//    }
}
