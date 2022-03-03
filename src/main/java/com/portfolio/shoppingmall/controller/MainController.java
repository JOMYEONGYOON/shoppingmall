package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.dto.ItemsDto;
import com.portfolio.shoppingmall.dto.ProductDto;
import com.portfolio.shoppingmall.repository.ProductRepository;
import com.portfolio.shoppingmall.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemsService itemsService;
    private final ProductRepository productRepository;


    @GetMapping("/")
    public String home(Model model, HttpServletRequest request){
        List<Product> itemsList = productRepository.findAll();
        model.addAttribute("itemsList",itemsList);
        return "test/testindex";
    }


    @GetMapping("/items/view/detail/{id}")
    public String detail(@PathVariable int id , Model model){
        // data
        Product productDto = productRepository.getById(id);
        model.addAttribute("product", productDto);


        return "item";
    }

    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword") String keyword, Model model){
        List<ProductDto> itemsDtos = itemsService.searchItem(keyword);
        log.info("itemsDtos={}",itemsDtos);
        log.info("keyword={}",keyword);
        model.addAttribute("itemsList",itemsDtos);

        return "myitems";
    }

}
