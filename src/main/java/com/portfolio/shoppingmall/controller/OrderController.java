package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.service.MemberService;
import com.siot.IamportRestClient.Iamport;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Locale;

@Controller
@Slf4j
public class OrderController {

    @Autowired
    private MemberService memberService;

    private IamportClient api;

    public OrderController(){
        this.api = new IamportClient("6685630644289929","bf21670bef0bfc7e541818e6dcaec606e8d69ad65c1191e478a902b0658a63f7859ae9134aa59680");
    }

    @GetMapping("/order")
    public String order(){
        return "order/orderForm";
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
