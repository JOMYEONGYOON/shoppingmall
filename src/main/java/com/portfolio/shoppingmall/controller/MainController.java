package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.Items;
import com.portfolio.shoppingmall.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class MainController {

    private final ItemsService itemsService;


    @RequestMapping("/")
    public String home(Model model){
        List<Items> itemsList = itemsService.getItemList();
        model.addAttribute("itemsList",itemsList);
        return "myitems";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/join")
    public String join(){
        return "join";
    }

    @GetMapping("/items/view/detail/{id}")
    public String detail(@PathVariable Long id , Model model){
        log.info("id ={}",id);

        return "item";
    }
}
