package com.coderhouse.model;

import com.coderhouse.repository.MongoRepository;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Document("Orders")
public class Order {

    @Autowired
    MongoRepository mongoRepository;

    @Id
    private String id;
    private List<CartAux> listOfItems;
    private String date;
    private String state = "generada";
    private String email;

    Order() {
    }

    public Order(String userId, String cartId) {
        listOfItems = mongoRepository.findCartById(cartId).getListOfProducts();
        date = LocalDateTime.now().toString();
        email = mongoRepository.findByUserId(userId).getEmail();
    }

}
