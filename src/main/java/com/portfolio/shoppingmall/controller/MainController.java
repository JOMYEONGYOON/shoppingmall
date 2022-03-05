package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ProductService productService;


    @GetMapping("/")
    public String home(Model model){
        LocalDateTime start = LocalDateTime.of(LocalDate.now().minusDays(7), LocalTime.of(0,0,0));
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(23,59,59));
        log.info("start={}",start);
        log.info("end={}",end);

        List<Product> latestProducts = productService.findAllByCreatedAtBetween(start,end);
        log.info("latestProducts={}",latestProducts);
        model.addAttribute("latestProducts",latestProducts);
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
