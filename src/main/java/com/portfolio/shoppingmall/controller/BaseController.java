package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.cart.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.Map;
@Slf4j
@ControllerAdvice
public class BaseController {


    @ModelAttribute
    public void sharedData(Model model, HttpSession session, Principal principal) {
        if (principal != null) {
            model.addAttribute("principal", principal.getName());
        }

//        List<Page> pages = pageRepo.findAllByOrderBySortingAsc();
//        List<Category> categories = categoryRepo.findAllByOrderBySortingAsc();

        boolean cartActive = false;
        if (session.getAttribute("cart") != null) {
            Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
            int size = 0;
            double total = 0;
        log.info("cart={}",cart);

//            for (Cart value : cart.values()) {
////                if(value.getId() == ) {
////                    size = 1;
////                    total = 0;
////                }
////                else{
//                    size += value.getQuantity();
//                    total += value.getQuantity() * Integer.parseInt(value.getPrice());
////                }
//
//            }

            for (Cart value : cart.values()) {
                if(cart.containsKey(value.getId())) {
                    size = 1;
                }else{
                    size += value.getQuantity();
                }

                total += value.getQuantity() * Double.parseDouble(value.getPrice());
            }


            model.addAttribute("csize", size);
            model.addAttribute("ctotal", total);

            cartActive = true;
        }
//
//        model.addAttribute("cpages", pages);
//        model.addAttribute("ccategories", categories);
        model.addAttribute("cartActive", cartActive);
    }
}
