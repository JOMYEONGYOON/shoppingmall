package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.dto.ProductDto;
import com.portfolio.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
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
    public String home(Model model, @PageableDefault(size = 4, sort = "id", direction = Sort.Direction.DESC) Pageable pageable, @RequestParam HashMap<String, String> map){

        Page<ProductDto> pagingList =null;
        String optionValue = map.get("optionValue");
        log.info("optionValue={}",optionValue);

        if(optionValue == null) {
            Page<Product> list = productService.findAll(pageable);
            pagingList = list.map(pageList -> new ProductDto(
                    pageList.getId(), pageList.getName(), pageList.getImage(), pageList.getPrice()
            ));
        }else if (optionValue.equals("1")){
            Page<Product> list = productService.findAllByOrderByPriceDesc(pageable);
            pagingList = list.map(pageOrder -> new ProductDto(pageOrder.getId(), pageOrder.getName(), pageOrder.getImage(), pageOrder.getPrice()));
        }

        int nowPage = pagingList.getPageable().getPageNumber()+1;
        int startPage = Math.max(nowPage -4 , 1);
        int endPage = Math.min(nowPage +5 , pagingList.getTotalPages());


        model.addAttribute("productList",pagingList);
        model.addAttribute("nowPage",nowPage);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);
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
