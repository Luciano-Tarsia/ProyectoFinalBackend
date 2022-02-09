package com.coderhouse.controller;

import com.coderhouse.handle.ExceptionAutentication;
import com.coderhouse.model.Cart;
import com.coderhouse.model.User;
import com.coderhouse.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/carrito")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping(path = "/create")
    private Cart createNewCart(@RequestParam("email") String email, @RequestParam("address") String address) {
        Cart cart = new Cart(email,address);
        return cartService.createNewCart(cart);
    }

    @PostMapping(path = "/add")
    private void addItemToCart(@RequestParam("cartId") String cartId, @RequestParam("productId") String productId, @RequestParam("cantidad") Integer cantidad) {
        cartService.addItemToCart(cartId, productId, cantidad);
    }

}
