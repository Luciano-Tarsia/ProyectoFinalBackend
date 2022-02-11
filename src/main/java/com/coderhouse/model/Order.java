package com.coderhouse.model;

import com.coderhouse.repository.MongoRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
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

    @Id
    private String id;
    private List<CartAux> listOfItems;
    private String date;
    private String state = "generada";
    private String email;

    Order() {
    }

    public Order(User user, Cart cart) {
        listOfItems = cart.getListOfProducts();
        date = LocalDateTime.now().toString();
        email = user.getEmail();
    }

}
