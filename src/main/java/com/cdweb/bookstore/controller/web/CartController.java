package com.cdweb.bookstore.controller.web;

import org.springframework.web.bind.annotation.RestController;


import com.cdweb.bookstore.api.output.CartOutput;
import com.cdweb.bookstore.dto.BookDTO;
import com.cdweb.bookstore.dto.CartDTO;
import com.cdweb.bookstore.oauth2.CustomOAuth2User;
import com.cdweb.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


import java.util.List;

@RestController
public class CartController {
    @Autowired
    private ICartService cartService;

    @GetMapping("/them-san-pham")
    public CartDTO addProduct(@RequestParam(name = "bookID", required = false) int bookId, @RequestParam(name = "quantity", required = false) int quantity, Authentication authentication) {
        if (authentication != null) {
            String userEmail = "";
            if (authentication.getPrincipal() instanceof CustomOAuth2User) {
                CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
                userEmail = oAuth2User.getAttribute("email");
            } else userEmail = authentication.getName();
            return cartService.addProduct(userEmail, bookId, quantity);
        }
        //tạo cart mới set user null, tạo book để nó đừng lỗi
        CartDTO result = new CartDTO();
        result.setBook(new BookDTO());
        return result;
    }

    @GetMapping("get-books")
    public CartOutput getBooks(Authentication authentication) {
        if (authentication == null) return null;
        String userEmail = "";
        if (authentication.getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            userEmail = oAuth2User.getAttribute("email");
        } else userEmail = authentication.getName();
        CartOutput output = new CartOutput();
        double total = 0.0;
        List<CartDTO> booksDb = cartService.getBooks(userEmail);
        for (CartDTO c : booksDb) {
            total += c.getBook().getPrice() * (1 - (c.getBook().getDiscountPercent() / 100)) * c.getQuantity();
        }
        output.setTotal(total);
        output.setBooksList(booksDb);
        return output;
    }

    @GetMapping("xoa-san-pham")
    public CartOutput deleteBook(@RequestParam(name = "bookID") int bookId, Authentication authentication) {
        if (authentication == null) return null;
        String userEmail = "";
        if (authentication.getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            userEmail = oAuth2User.getAttribute("email");
        } else userEmail = authentication.getName();
        List<CartDTO> cartDeleted = cartService.deleteBook(userEmail, bookId);
        CartOutput outputDelete = new CartOutput();
        double total = 0.0;
        for (CartDTO c : cartDeleted) {
            total += c.getBook().getPrice() * (1 - (c.getBook().getDiscountPercent() / 100)) * c.getQuantity();
        }
        outputDelete.setTotal(total);
        outputDelete.setBooksList(cartDeleted);
        return outputDelete;
    }

    @GetMapping("update-quantity")
    public CartOutput updateQuantity(@RequestParam(name = "bookID") int bookId, @RequestParam(name = "quantity") int newQuantity, Authentication authentication) {
        if (authentication == null) return null;
        String userEmail = "";
        if (authentication.getPrincipal() instanceof CustomOAuth2User) {
            CustomOAuth2User oAuth2User = (CustomOAuth2User) authentication.getPrincipal();
            userEmail = oAuth2User.getAttribute("email");
        } else userEmail = authentication.getName();
        List<CartDTO> cartUpdate = cartService.updateQuantity(userEmail, bookId, newQuantity);
        CartOutput outputUpdate = new CartOutput();
        double total = 0.0;
        for (CartDTO c : cartUpdate) {
            total += c.getBook().getPrice() * (1 - (c.getBook().getDiscountPercent() / 100)) * c.getQuantity();
        }
        outputUpdate.setTotal(total);
        outputUpdate.setBooksList(cartUpdate);
        return outputUpdate;
    }
}