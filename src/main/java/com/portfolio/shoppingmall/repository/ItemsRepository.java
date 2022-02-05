package com.portfolio.shoppingmall.repository;

import com.portfolio.shoppingmall.domain.item.Items;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemsRepository extends JpaRepository<Items, Long> {

    /**
     * 메뉴 아이디로 상품 목록 조회
     * @param
     * @return
     */
    List<Items> findAllByCategoryId(Long categoryId);


}
