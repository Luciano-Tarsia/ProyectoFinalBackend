package com.coderhouse.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@ToString
@Document("Products")
public class Product {

    private Double unitPrice;
    private String name;
    private String category;
    private String description;

    Product(){}

    Product(Double unitPrice, String name, String category, String description){
        this.unitPrice = unitPrice;
        this.name = name;
        this.category = category;
        this.description = description;
    }

}
