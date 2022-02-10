package com.coderhouse.service;

import com.coderhouse.handle.ExceptionCart;
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

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository mongoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public Cart createNewCart(Cart cart) {
        logger.info("Creando un nuevo cart");
        return mongoRepository.saveCart(cart);
    }

    public void addItemToCart(String cartId, String productId, Integer cantidad) throws ExceptionCart {
        logger.info("Agregando un producto al cart");
        Product product = mongoRepository.findByProductId(productId);
        if (product == null) {
            logger.error("El producto que se intentó agregar no fue encontrado");
            throw new ExceptionCart("El producto que se intentó agregar no fue encontrado");
        }
        Cart cart = mongoRepository.findCartById(cartId);
        if (cart == null) {
            logger.error("El cart al que se intentó agregar el producto no fue encontrado");
            throw new ExceptionCart("El cart al que se intentó agregar el producto no fue encontrado");
        }
        cart.addItem(product, cantidad);
        mongoRepository.updateCart(cart);
    }

    public List<CartAux> showCart(String cartId) {
        logger.info("Mostrando el cart");
        return mongoRepository.findCartById(cartId).getListOfProducts();
    }

    public void modifyCart(String cartId, String productId, Integer cantidad) throws ExceptionCart {
        logger.info("Modificando un producto del cart");
        Cart cart = mongoRepository.findCartById(cartId);
        if (cart == null) {
            logger.error("El cart en el que intentó modificar un producto no fue encontrado");
            throw new ExceptionCart("El cart en el que intentó modificar un producto no fue encontrado");
        }
        Integer aux = cart.modifyItem(cantidad, productId);
        if (aux == -1) {
            logger.error("El producto a modificar no fue encontrado en el cart");
            throw new ExceptionCart("El producto a modificar no fue encontrado en el cart");
        }
        mongoRepository.updateCart(cart);
    }

    public void deleteItemFromCart(String cartId, String productId) throws ExceptionCart {
        logger.info("Eliminando un producto del cart");
        Cart cart = mongoRepository.findCartById(cartId);
        if (cart == null) {
            logger.error("El cart en el que intentó eliminar un producto no fue encontrado");
            throw new ExceptionCart("El cart al que se intentó agregar el producto no fue encontrado");
        }
        Integer aux = cart.deleteItem(productId);
        if (aux == -1) {
            logger.error("El producto que intento eliminar no fue encontrado en el cart");
            throw new ExceptionCart("El producto que intento eliminar no fue encontrado en el cart");
        }
        mongoRepository.updateCart(cart);
    }
}
