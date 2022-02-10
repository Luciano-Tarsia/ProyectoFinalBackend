package com.coderhouse.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartAux {

    private Product product;
    private Integer quantity;

    CartAux() {
    }

    CartAux(Product prod, Integer quant) {
        this.product = prod;
        this.quantity = quant;
    }

}
