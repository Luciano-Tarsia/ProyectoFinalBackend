package com.coderhouse.model;

import com.coderhouse.service.ProductService;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
@Document("Carts")
public class Cart {

    @Id
    private String cartId;
    private String email;
    private String date;
    private List<CartAux> listOfProducts;
    private String homeAddress;

    Cart() {
    }

    public Cart(String email, String homeAddress) {
        this.email = email;
        this.date = LocalDateTime.now().toString();
        this.listOfProducts = new ArrayList<>();
        this.homeAddress = homeAddress;
    }

    public void addItem(Product product, Integer quantity) {
        CartAux item = new CartAux(product, quantity);
        this.listOfProducts.add(item);
    }

    public void modifyItem(Integer quantity, String productId) {
        Integer index = -1;
        // Busco la posición del item a modificar
        for (Integer i = 0; i < this.listOfProducts.size(); i++) {
            if (listOfProducts.get(i).getProduct().getId().equals(productId)) {
                index = i;
            }
        }
        // Si o existe el item, no hago nada
        if (index == -1) {
            return;
        }

        // Si existe el item, creo uno nuevo con las mismas caracteristicas que el anterior, pero con distinta cantidad
        // Elimino el item viejo
        // Agrego el item nuevo
        CartAux item = new CartAux(listOfProducts.get(index).getProduct(),
                quantity);
        this.listOfProducts.remove(listOfProducts.get(index));
        listOfProducts.add(item);
    }

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public void deleteItem(String productId) {
        Integer index = -1;
        for (Integer i = 0; i < this.listOfProducts.size(); i++) {
            if (listOfProducts.get(i).getProduct().getId().equals(productId)) {
                index = i;
                break;
            }
        }
        if (index == -1) {
            return;
        }
        this.listOfProducts.remove(listOfProducts.get(index));
    }

}
