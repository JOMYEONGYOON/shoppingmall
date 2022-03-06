package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.dto.ProductDto;
import com.portfolio.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/")
public class MainController {

    private final ProductService productService;
    private final ModelMapper modelMapper;

    @GetMapping
    public String home(Model model){
        List<ProductDto> productDtos = productService.findAll().stream().map(list -> modelMapper.map(list, ProductDto.class))
                .collect(Collectors.toList());
        model.addAttribute("productList",productDtos);
        return "index";
    }


//    @GetMapping("/items/view/detail/{id}")
//    public String detail(@PathVariable int id , Model model){
//        // data
//        Product productDto = productService.getById(id);
//        model.addAttribute("product", productDto);
//
//
//        return "item";
//    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
//        List<ProductDto> itemsDtos = itemsService.searchItem(keyword);
//        model.addAttribute("itemsList",itemsDtos);

        return "myitems";
    }

}
