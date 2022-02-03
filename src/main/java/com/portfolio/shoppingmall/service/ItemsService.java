package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.Items;
import com.portfolio.shoppingmall.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;

    /**
     * 메뉴 아이디로 상품 목록 조회
     * @param categoryId
     * @return
     */
    public List<Items> getProductList(Long categoryId){
        return itemsRepository.findAllByCategoryId(categoryId);
    }

    public List<Items> getItemList(){
        return itemsRepository.findAll();
    }

}
