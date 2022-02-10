package com.coderhouse.service;

import com.coderhouse.handle.ExceptionOrder;
import com.coderhouse.model.Order;
import com.coderhouse.repository.MongoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository mongoRepository;

    private static final Logger logger = LoggerFactory.getLogger(OrderService.class);

    public Order createNewOrder(Order order){
        logger.info("Creando nueva orden");
        return mongoRepository.saveOrder(order);
    }

    public void sendOrder(String orderId){
        Order order = mongoRepository.findByOrderId(orderId);
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
