package com.portfolio.shoppingmall.service;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.dto.ItemsDto;
import com.portfolio.shoppingmall.dto.ProductDto;
import com.portfolio.shoppingmall.repository.ItemsRepository;
import com.portfolio.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ItemsService {

    private final ItemsRepository itemsRepository;
    private final ProductRepository productRepository;
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

    @Transactional
    public List<ProductDto> searchItem(String keyword){
        List<Product> items = productRepository.findByNameContaining(keyword);
        List<ProductDto> itemsDtos = new ArrayList<>();

        if(items.isEmpty()) return itemsDtos;

        for(Product items1 : items){
            itemsDtos.add(this.convertEntityToDto(items1));
        }

        return itemsDtos;
    }


        private ProductDto convertEntityToDto(Product items){
            return ProductDto.builder()
                    .id(items.getId())
                    .name(items.getName())
                    .price(items.getPrice())
                    .description(items.getDescription())
                    .image(items.getImage())
                    .categoryId(items.getCategoryId())
                    .build();
        }

}
