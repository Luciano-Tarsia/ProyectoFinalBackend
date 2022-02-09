package com.coderhouse.service;

import com.coderhouse.model.Cart;
import com.coderhouse.model.CartAux;
import com.coderhouse.model.Product;
import com.coderhouse.repository.MongoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository mongoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public Cart createNewCart(Cart cart){
        logger.info("Agregando cart");
        return mongoRepository.saveCart(cart);
    }

    public void addItemToCart(String cartId, String productId, Integer cantidad){
        logger.info("Agregando producto a un cart");
        Product product = mongoRepository.findByProductId(productId);
        Cart cart = mongoRepository.findCartById(cartId);
        cart.addItem(product, cantidad);
        mongoRepository.updateCart(cart);
    }

}
