package com.coderhouse.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
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

    private Integer cartItemId = 0;

    Cart(){}

    public Cart(String email, String homeAddress){
        this.email = email;
        this.date = LocalDateTime.now().toString();
        this.listOfProducts = new ArrayList<>();
        this.homeAddress = homeAddress;
    }

    public void addItem(Product product, Integer quantity){
        CartAux item = new CartAux(product, quantity, cartItemId);
        cartItemId = cartItemId + 1;
        this.listOfProducts.add(item);
    }

    public void modifyItem(Integer quantity, Integer id){
        Integer index = -1;
        // Busco la posición del item a modificar
        for (Integer i = 0; i < this.listOfProducts.size(); i++){
            if (listOfProducts.get(i).getId().equals(id)){
                index = i;
            }
        }
        // Si o existe el item, no hago nada
        if (index == -1){
            return;
        }

        // Si existe el item, creo uno nuevo con las mismas caracteristicas que el anterior, pero con distinta cantidad
        // Elimino el item viejo
        // Agrego el item nuevo
        CartAux item = new CartAux(listOfProducts.get(index).getProduct(),
                quantity, listOfProducts.get(index).getId());
        listOfProducts.remove(index);
        listOfProducts.add(item);
    }

    public void deleteItem(Integer id){
        Integer index = -1;
        for (Integer i = 0; i < this.listOfProducts.size(); i++){
            if (listOfProducts.get(i).getId().equals(id)){
                index = i;
            }
        }
        if (index == -1){
            return;
        }
        this.listOfProducts.remove(index);
    }

}
