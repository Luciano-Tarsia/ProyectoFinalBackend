package com.coderhouse.service;

import com.coderhouse.auxiliaries.email.EmailService;
import com.coderhouse.handle.ExceptionOrder;
import com.coderhouse.model.Cart;
import com.coderhouse.model.Order;
import com.coderhouse.model.User;
import com.coderhouse.repository.MongoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository mongoRepository;

    @Autowired
    private EmailService emailService;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public Order createNewOrder(String userId, String cartId){
        logger.info("Creando nueva orden");
        Cart cart = mongoRepository.findCartById(cartId);
        User user = mongoRepository.findByUserId(userId);
        Order order = new Order(user, cart);
        return mongoRepository.saveOrder(order);
    }

    public void sendOrder(String orderId){
        logger.info("Enviando la orden al email");
        Order order = mongoRepository.findByOrderId(orderId);
        logger.info("El email al que enviaremos la orden es: " + order.getEmail());
        emailService.sendEmail(order);
    }

    public void deleteOrder(String orderId) throws ExceptionOrder {
        logger.info("Eliminando la orden");
        if (mongoRepository.countByOrderId(orderId) > 0){
            mongoRepository.deleteOrder(orderId);
        }else{
            logger.error("La orden a eliminar no existe en la base de datos");
            throw new ExceptionOrder("La orden a eliminar no existe en la base de datos");
        }
    }


}
