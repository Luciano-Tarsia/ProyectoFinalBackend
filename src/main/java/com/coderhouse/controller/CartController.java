package com.coderhouse.controller;

import com.coderhouse.handle.ExceptionAutentication;
import com.coderhouse.model.Cart;
import com.coderhouse.model.CartAux;
import com.coderhouse.model.Product;
import com.coderhouse.model.User;
import com.coderhouse.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/carrito")
public class CartController {

    @Autowired
    CartService cartService;

    @PostMapping(path = "/crear")
    private Cart createNewCart(@RequestParam("email") String email, @RequestParam("address") String address) {
        Cart cart = new Cart(email,address);
        return cartService.createNewCart(cart);
    }

    @PostMapping(path = "/agregar")
    private void addItemToCart(@RequestParam("cartId") String cartId, @RequestParam("productId") String productId, @RequestParam("cantidad") Integer cantidad) {
        cartService.addItemToCart(cartId, productId, cantidad);
    }

    @GetMapping(path = "")
    private List<CartAux> showCart(@RequestParam("cartId") String cartId) {
        return cartService.showCart(cartId);
    }

    // Bug en este endpoint. Agrega pero no elimina
    @PutMapping(path = "/modificar/{productId}")
    private void modifyItemFromCart(@RequestParam("cartId") String cartId, @PathVariable("productId") String productId, @RequestParam("cantidad") Integer cantidad) {
        cartService.modifyCart(cartId, productId, cantidad);
    }

    @DeleteMapping(path = "/eliminar/{productId}")
    private void deleteItemFromCart(@RequestParam("cartId") String cartId, @PathVariable("productId") String productId) {
        cartService.deleteItemFromCart(cartId, productId);
    }

}
