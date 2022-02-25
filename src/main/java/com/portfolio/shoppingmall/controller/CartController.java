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
        Map<String, Object> cart2 = new HashMap<>();
        ArrayList<Integer> list = new ArrayList<>();
     try {

         for (Cart value : cart.values()) {
             for (int i=0;i< cart.size();i++){
                 list.add(i,value.getId());
                 cart2.put("id",value.getId());
             }



             if(cart2.containsKey("id")){

             }
         }

     } catch(NullPointerException e){
         cart2.put("id","empty");
     }
        model.addAttribute("cart", cart);
        model.addAttribute("cart2", cart2);
        model.addAttribute("list", list);
        model.addAttribute("notCartViewPage", true);
//        Map<Integer, Cart> cart2 = new HashMap<>();
//        cart2.put(1,cart.get());

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
