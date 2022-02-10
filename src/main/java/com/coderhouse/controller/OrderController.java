package com.coderhouse.controller;

import com.coderhouse.handle.ExceptionOrder;
import com.coderhouse.model.Cart;
import com.coderhouse.model.Order;
import com.coderhouse.service.CartService;
import com.coderhouse.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/orden")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping(path = "/crear")
    private Order createNewOrder(@RequestParam("userId") String userId, @RequestParam("cartId") String cartId) {
        Order order = new Order(userId, cartId);
        return orderService.createNewOrder(order);
    }

    @GetMapping(path = "/enviar")
    private void sendOrder(@RequestParam("orderId") String orderId) {
        orderService.sendOrder(orderId);
    }

    @DeleteMapping(path = "/eliminar")
    private void deleteOrder(@RequestParam("orderId") String orderId) throws ResponseStatusException {
        try {
            orderService.deleteOrder(orderId);
        }catch (ExceptionOrder exceptionOrder){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionOrder.getMessage());
        }
    }

}
