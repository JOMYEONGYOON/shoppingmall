package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.dto.ItemsDto;
import com.portfolio.shoppingmall.repository.ItemsRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;
    private final ModelMapper modelMapper;
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

    /**
     * 상품 상세 조회
     * @param itemId
     * @return
     */
    public ItemsDto getProduct(Long itemId){
        ItemsDto itemsDto = modelMapper.map(itemsRepository.findById(itemId).get(), ItemsDto.class);
//        productDto.setDiscountPrice(productDto.getPrice() * (100 - productDto.getDiscount())/100);
        return itemsDto;
    }

}
