package com.portfolio.shoppingmall.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.domain.item.Items;
import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.CartDto;
import com.portfolio.shoppingmall.repository.ProductRepository;
import com.portfolio.shoppingmall.security.SessionConst;
import com.portfolio.shoppingmall.service.CartService;
import com.portfolio.shoppingmall.service.ItemsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {

    private final ProductRepository productRepo;

//    @GetMapping("/cart")
//    public String cart(Model model, HttpServletRequest request){
//        List<Items> cart = cartService.findCart();
//        model.addAttribute("cartlist",cart);
//        return "cart/cart";
//    }

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

            for (Cart value : cart.values()) {
                size += value.getQuantity();
                total += value.getQuantity() * Integer.parseInt(value.getPrice());
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


    @GetMapping("cart/add/{id}")
    public String add(@PathVariable int id, HttpSession session, Model model, @RequestParam(value = "cartPage", required = false) String cartPage) {
        Product product = productRepo.getById(id);
        if (session.getAttribute("cart") == null) {
            Map<Integer, Cart> cart = new HashMap<>();
            cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage()));
            session.setAttribute("cart", cart);
        } else {
            Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
            if (cart.containsKey(id)) {
                int qty = cart.get(id).getQuantity();
                cart.put(id, new Cart(id, product.getName(), product.getPrice(), ++qty, product.getImage()));
            } else {
                cart.put(id, new Cart(id, product.getName(), product.getPrice(), 1, product.getImage()));
                session.setAttribute("cart", cart);
            }
        }

        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        int size = 0;
        double total = 0;
        for (Cart value : cart.values()) {
            size += value.getQuantity();
            total += value.getQuantity() * Double.parseDouble(value.getPrice());
        }

        model.addAttribute("csize", size);
        model.addAttribute("ctotal", total);
        if (cartPage != null) {
            return "redirect:/cart";
        }

        return "cart/cartPartial";
    }

    @GetMapping("/cart")
    public String view(HttpSession session, Model model) {
        if (session.getAttribute("cart") == null) {
            return "redirect:/cart";
        }
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        model.addAttribute("cart", cart);
        model.addAttribute("notCartViewPage", true);
        return "cart/cart";
    }


    @GetMapping("/cart/subtract/{id}")
    public String subtract(@PathVariable int id, HttpSession session, Model model, HttpServletRequest httpServletRequest) {
        Product product = productRepo.getById(id);
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");

        int qty = cart.get(id).getQuantity();
        if (qty == 1) {
            cart.remove(id);
            if (cart.size() == 0) {
                session.removeAttribute("cart");
            }
        } else {
            cart.put(id, new Cart(id, product.getName(), product.getPrice(), --qty, product.getImage()));
        }

        String refererLink = httpServletRequest.getHeader("referer");
        return "redirect:" + refererLink;
    }

    @GetMapping("/cart/remove/{id}")
    public String remove(@PathVariable int id, HttpSession session, Model model, HttpServletRequest httpServletRequest) {

        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        cart.remove(id);
        if (cart.size() == 0) {
            session.removeAttribute("cart");
        }

        String refererLink = httpServletRequest.getHeader("referer");
        return "redirect:" + refererLink;
    }

    @GetMapping("/clear")
    public String clear(HttpSession session, HttpServletRequest httpServletRequest) {
        session.removeAttribute("cart");

        String refererLink = httpServletRequest.getHeader("referer");
        return "redirect:" + refererLink;
    }

    


}
