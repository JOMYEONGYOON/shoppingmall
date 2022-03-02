package com.portfolio.shoppingmall.controller;

import com.portfolio.shoppingmall.domain.Product;
import com.portfolio.shoppingmall.domain.cart.Cart;
import com.portfolio.shoppingmall.domain.member.Address;
import com.portfolio.shoppingmall.domain.member.Member;
import com.portfolio.shoppingmall.dto.AddressDto;
import com.portfolio.shoppingmall.dto.MemberDto;
import com.portfolio.shoppingmall.service.AddressService;
import com.portfolio.shoppingmall.service.MemberService;
import com.portfolio.shoppingmall.service.ProductService;
import com.siot.IamportRestClient.Iamport;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
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

    @Autowired
    private AddressService addressService;

    @Autowired
    private ModelMapper modelMapper;
//    @Autowired
//    private ProductService productService;

    private IamportClient api;

    public OrderController(){
        this.api = new IamportClient("6685630644289929","bf21670bef0bfc7e541818e6dcaec606e8d69ad65c1191e478a902b0658a63f7859ae9134aa59680");
    }

    @ModelAttribute("address")
    public AddressDto addressDto(){
        return new AddressDto();
    }

    @PostMapping("/order/service")
    @ResponseBody
    public HashMap<String, String> orderService(@RequestParam HashMap<String, String> map){
        log.info("map={}",map);
        String idValue = map.get("cart2[id]");
        String quantity = map.get("allData["+idValue+"][quantity]");
        String name = map.get("allData["+idValue+"][name]");
        HashMap<String,String> hashMap = new HashMap<>();
        hashMap.put("name",name);
        hashMap.put("quantity",quantity);
        return hashMap;
    }

    @GetMapping("/order")
    public String order(Model model, Authentication authentication , @RequestParam HashMap<String, String> map){

        String name = authentication.getName();
        Member member = memberService.findByEmail(name);
        Long id = member.getId();
        List<Address> addressMember = addressService.findByMember_id(id);
        for (Address address : addressMember) {
            if(address.isSelection()){
                model.addAttribute("addressSelection",address);
            }

        }
        model.addAttribute("orderMember",member);

        model.addAttribute("orderItem",map);
        return "/order/orderForm";
    }

    @GetMapping("/popup")
    public String popup(Model model, Authentication authentication){
        String name = authentication.getName();
        Member member = memberService.findByEmail(name);
        Long id = member.getId();
        List<Address> addressList = addressService.findByMember_id(id);
        model.addAttribute("addressList",addressList);
        return "/order/addresspopup";
    }

    @PostMapping("/popup")
    public String addAddress(AddressDto addressDto , Authentication authentication) {
        String name = authentication.getName();
        Member member = memberService.findByEmail(name);
        addressDto.setMember(member);
        Address address = modelMapper.map(addressDto, Address.class);
        addressService.save(address);
        return "redirect:/popup";
    }

    @ResponseBody
    @GetMapping("/update/{id}")
    public Map<String, Object> updateAddress(@PathVariable Long id , Model model){
        Map<String, Object> response = new HashMap<>();
        Optional<Address> addressId = addressService.findById(id);
        response.put("recipient",addressId.get().getRecipient());
        response.put("id",addressId.get().getId());
        response.put("phone",addressId.get().getPhone());
        response.put("address",addressId.get().getAddress());
        response.put("detailAddress",addressId.get().getDetailedAddress());
        return response;
    }

    @ResponseBody
    @PutMapping("/update/{id}")
    public String updatePutAddress(@PathVariable Long id , @RequestBody AddressDto addressDto, Authentication authentication){
        String name = authentication.getName();
        Member member = memberService.findByEmail(name);
        addressDto.setMember(member);
        Optional<Address> addressId = addressService.findById(id);
        List<Address> memberId = addressService.findByMember_id(addressId.get().getMember().getId());
        List<Address> addresses = addressService.pickAddress(memberId.get(0).getId());
        for (Address address : addresses) {
            if(address.isSelection()) {
                address.setSelection(false);
                addressId.get().setAddress(addressDto.getAddress());
                addressId.get().setDetailedAddress(addressDto.getDetailedAddress());
                addressId.get().setPhone(addressDto.getPhone());
                addressId.get().setRecipient(addressDto.getRecipient());
            }
        }
        if(addressDto.getAddress() == null){
            addressId.get().setSelection(true);
        }else {
            addressId.get().setAddress(addressDto.getAddress());
            addressId.get().setDetailedAddress(addressDto.getDetailedAddress());
            addressId.get().setPhone(addressDto.getPhone());
            addressId.get().setRecipient(addressDto.getRecipient());

        }

        Address address = modelMapper.map(addressDto, Address.class);
        addressService.save(address);

        return "ok";
    }

    @ResponseBody
    @DeleteMapping("/delete/{id}")
    public String deleteAddress(@PathVariable Long id){
        addressService.delete(id);
        return "ok";
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
        return api.paymentByImpUid(imp_uid);
    }


}
