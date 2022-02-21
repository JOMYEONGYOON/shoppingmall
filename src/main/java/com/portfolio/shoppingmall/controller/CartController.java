package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Controller
@RequiredArgsConstructor
public class CartController {

    private final ProductRepository productRepo;

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
            if(cart.containsKey(id)) {
                size = cart.size();
            }else{
                size += value.getQuantity();
            }

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
            model.addAttribute("cart", null);
        }
        Map<Integer, Cart> cart = (Map<Integer, Cart>) session.getAttribute("cart");
        Map<String , List<Object>> test1 = new HashMap<>();
        List<Object> cartList = new ArrayList<>();
        Map<String, Object> cart2 = new HashMap<>();
        for (Cart value : cart.values()) {
            test1.put("name", new ArrayList<Object>());
            test1.get("name").add(value.getName());

            test1.put("id", new ArrayList<Object>());
            test1.get("id").add(value.getId());

            test1.put("quantity", new ArrayList<Object>());
            test1.get("quantity").add(value.getQuantity());

//            cartList.add();
//            cartList.add();
//            cartList.add(value.getPrice());
//            cartList.add(value.getQuantity());
//            test1.put(value.getId(),cartList);
//            cart2.put("name",value.getName());
//            cart2.put("id", value.getId());
//            cart2.put("price", value.getPrice());
//            cart2.put("quantity", value.getQuantity());
        }
        log.info("test1={}",test1);
//

//        Map<Integer, Cart> cart2 = new HashMap<>();
//        cart2.put(1,cart.get());
        Iterator<Integer> keys = cart.keySet().iterator();
        while (keys.hasNext()){
            int key = keys.next();
            System.out.println("KEY : " + key); // Key2 , Key1, Key4, Key3, Key5
             }

        model.addAttribute("cart", cart);
        model.addAttribute("cartList", cartList);
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
