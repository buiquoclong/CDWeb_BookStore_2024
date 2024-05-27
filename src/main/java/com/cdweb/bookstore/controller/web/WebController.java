package com.cdweb.bookstore.controller.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WebController {
    @GetMapping(value = {"/", "/trang-chu"})
    public ModelAndView index() {
        ModelAndView mav = new ModelAndView("web/index.html");
        return mav;
    }

    @GetMapping("/lien-he")
    public ModelAndView contact() {
        return new ModelAndView("web/contact.html");
    }

    @GetMapping("/chi-tiet-san-pham")
    public ModelAndView productDetail() {
        ModelAndView mav = new ModelAndView("web/detail.html");
        return mav;
    }

    @GetMapping("/gio-hang")
    public ModelAndView cart() {
        return new ModelAndView("web/cart.html");
    }

    @GetMapping("/thanh-toan")
    public ModelAndView checkout() {
        ModelAndView mav = new ModelAndView("web/checkout.html");
        return mav;
    }

    @GetMapping("/ve-chung-toi")
    public ModelAndView about() {
        return new ModelAndView("web/about");
    }

    @GetMapping("/dang-nhap")
    public ModelAndView signin (@RequestParam(name = "error", required = false) boolean error){
        ModelAndView mav = new ModelAndView("web/signin");
        if (error) mav.addObject("message", "Email hoặc mật khẩu không đúng");
        return  mav;
    }
}
