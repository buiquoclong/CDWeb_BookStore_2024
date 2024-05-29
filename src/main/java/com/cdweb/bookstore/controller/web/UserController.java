package com.cdweb.bookstore.controller.web;

import com.cdweb.bookstore.dto.UserDTO;
import com.cdweb.bookstore.oauth2.CustomOAuth2User;
import com.cdweb.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UserController {
    @Autowired
    private IUserService userService;

    @GetMapping("/check-mail")
    public String findByEmailAndIsEnable(@RequestParam(name = "email", required = false) String email) {
        UserDTO user = userService.findByEmailAndIsEnable(email);
        String result = "";
        if (user != null) {
            user.setPassword("");
            result = user.getEmail();
        }
        return result;
    }

    @PostMapping("/dang-ky")
    public ModelAndView postRegister(@ModelAttribute("User") UserDTO user) {
        ModelAndView mav = new ModelAndView("web/confirmCode.html");
        UserDTO userDTO = userService.sendMail(user);
        if (userDTO != null) {
            //neu sendmail thanh cong thi gui id de xac dinh confirm token
            mav.addObject("userId", userDTO.getUserID());
        }
        return mav;
    }

    @PostMapping("/confirm-account")
    public ModelAndView confirmAccount(@RequestParam(name = "confirmCode") String code, @RequestParam(name = "userId") int id) {
        ModelAndView mav = null;
        //request sẽ gồm code người dùng nhập vào và id dc gửi qua
        //lấy code đó so sánh với code được lấy ra từ user tìm dc theo id
        //nếu giống nhau thì set enable lại r trả về view sign in
        if (code.equalsIgnoreCase(userService.getConfirmCode(id))) {
            UserDTO userDTO = userService.confirmEmail(id);
            mav = new ModelAndView("web/signin.html");
        }
        return mav;
    }

    @GetMapping("/quen-mat-khau")
    public ModelAndView getForgetPassword() {
        return new ModelAndView("web/forgetPassword.html");
    }

    @PostMapping("/gui-mail-quen-mat-khau")
    public ModelAndView sendMailForgetPassword(@RequestParam(name = "mailForgot") String email) {
        ModelAndView mav = new ModelAndView("web/forgetPassword.html");
        UserDTO result = userService.sendMailForgotPassword(email);
        if (result == null) mav.addObject("message", "Tài khoản không tồn tại.");
        else mav.addObject("message", "Vui lòng kiểm tra email để nhận mật khẩu");
        return mav;
    }

    @GetMapping("/getUser")
    public UserDTO getUser(Authentication authentication) {
        if (authentication != null) {
            UserDTO user;
            if (authentication.getPrincipal() instanceof CustomOAuth2User) {
                CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                user = this.userService.findByEmailAndIsEnable(oauthUser.getAttribute("email"));
            } else {
                user = this.userService.findByEmailAndIsEnable(authentication.getName());
                user.setPassword("");
            }
            return user;
        } else {
            return new UserDTO();
        }
    }
    @GetMapping("/thong-tin-tai-khoan")
    public ModelAndView information() {
        ModelAndView mav = new ModelAndView("web/information.html");
        return mav;
    }

    @PostMapping("/cap-nhat-thong-tin")
    public ModelAndView changeInformation(@ModelAttribute(name = "user") UserDTO user, Authentication authentication) {
        ModelAndView mav = new ModelAndView("web/information.html");
        userService.changeInformation(user);
        mav.addObject("message", "Cập nhật thông tin thành công");
        if (authentication != null) return mav;
        return new ModelAndView("web/signin.html");
    }

    @GetMapping("/kiem-tra-mat-khau")
    public boolean checkPass(@RequestParam(name = "oldPassword") String oldPass, Authentication authentication) {
        String userEmail = "";
        if (authentication.getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            userEmail = oAuth2User.getAttribute("email");
        } else userEmail = authentication.getName();
        return userService.checkPass(userEmail, oldPass);
    }

    @PostMapping("/doi-mat-khau")
    public ModelAndView changePassword(@RequestParam(name = "password") String newPass, Authentication authentication) {
        ModelAndView mav = new ModelAndView("web/information.html");
        String userEmail = "";
        if (authentication.getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            userEmail = oAuth2User.getAttribute("email");
        } else userEmail = authentication.getName();

        userService.changePassword(newPass, userEmail);
        mav.addObject("message", "Cập nhật mật khẩu thành công.");

        if (authentication != null) return mav;
        return new ModelAndView("web/signin.html");
    }

}