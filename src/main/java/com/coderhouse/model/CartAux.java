package com.coderhouse.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartAux {

    private Product product;
    private Integer quantity;
    private Integer id;

    CartAux(){}

    CartAux(Product prod, Integer quant, Integer id){
        this.product = prod;
        this.quantity= quant;
        this.id= id;
    }

}
