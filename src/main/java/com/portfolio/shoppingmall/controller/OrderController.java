package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.service.MemberService;
import com.portfolio.shoppingmall.service.ProductService;
import com.siot.IamportRestClient.Iamport;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

@Controller
@Slf4j
public class OrderController {

    @Autowired
    private MemberService memberService;

//    @Autowired
//    private ProductService productService;

    private IamportClient api;

    public OrderController(){
        this.api = new IamportClient("6685630644289929","bf21670bef0bfc7e541818e6dcaec606e8d69ad65c1191e478a902b0658a63f7859ae9134aa59680");
    }



    @PostMapping("/order/service")
    @ResponseBody
    public HashMap<String, String> orderService(@RequestParam HashMap<String, String> map){
        log.info("map.keySet()={}",map.keySet());
        log.info("cart2[id]={}",map.get("cart2[id]"));
        String idValue = map.get("cart2[id]");
        String id = map.get("allData["+idValue+"][id]");
        String quantity = map.get("allData["+idValue+"][quantity]");
        log.info("id={}",id);
        log.info("quantity={}",quantity);
        List<String> list = new ArrayList<>();
        return map;
    }

    @GetMapping("/order")
    public String order(Model model, Authentication authentication, HttpSession httpSession , @RequestParam HashMap<String, String> map){

        String name = authentication.getName();
        Member member = memberService.findByEmail(name);
//        Optional<Product> byId = productService.findById();
        model.addAttribute("orderMember",member);
        return "/order/orderForm";
    }

    @GetMapping("/popup")
    public String popup(Model model, Authentication authentication, HttpSession httpSession , @RequestParam HashMap<String, String> map){

        return "/order/addresspopup";
    }



    @ResponseBody
    @RequestMapping(value="/verifyIamport/{imp_uid}")
    public IamportResponse<Payment> paymentByImpUid(
            Model model
            , @RequestBody HashMap<String, String> map
            , Locale locale
            , HttpSession session
            , HttpServletRequest request
            , @PathVariable(value= "imp_uid") String imp_uid) throws IamportResponseException, IOException
    {
        log.info("map={}",map);
        System.out.println("------------------");
        return api.paymentByImpUid(imp_uid);
    }


}
