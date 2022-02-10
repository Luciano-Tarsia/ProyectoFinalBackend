package com.coderhouse.controller;

import com.coderhouse.handle.ExceptionCart;
import com.coderhouse.model.Cart;
import com.coderhouse.model.CartAux;
import com.coderhouse.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/carrito")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping(path = "/crear")
    private Cart createNewCart(@RequestParam("email") String email, @RequestParam("address") String address) {
        Cart cart = new Cart(email, address);
        return cartService.createNewCart(cart);
    }

    @PostMapping(path = "/agregar")
    private void addItemToCart(@RequestParam("cartId") String cartId, @RequestParam("productId") String productId, @RequestParam("cantidad") Integer cantidad) throws ExceptionCart, ResponseStatusException {
        try {
            cartService.addItemToCart(cartId, productId, cantidad);
        } catch (ExceptionCart exceptionCart) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionCart.getMessage());
        }
    }

    @GetMapping(path = "")
    private List<CartAux> showCart(@RequestParam("cartId") String cartId) {
        return cartService.showCart(cartId);
    }

    @PutMapping(path = "/modificar/{productId}")
    private void modifyItemFromCart(@RequestParam("cartId") String cartId, @PathVariable("productId") String productId, @RequestParam("cantidad") Integer cantidad) throws ExceptionCart, ResponseStatusException {
        try {
            cartService.modifyCart(cartId, productId, cantidad);
        } catch (ExceptionCart exceptionCart) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionCart.getMessage());
        }

    }

    @DeleteMapping(path = "/eliminar/{productId}")
    private void deleteItemFromCart(@RequestParam("cartId") String cartId, @PathVariable("productId") String productId) throws ExceptionCart, ResponseStatusException {
        try {
            cartService.deleteItemFromCart(cartId, productId);
        } catch (ExceptionCart exceptionCart) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionCart.getMessage());
        }
    }
}
