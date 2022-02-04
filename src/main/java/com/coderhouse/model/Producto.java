package com.coderhouse.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Builder
@Getter
@Setter
@ToString
@Document("productos")
public class Producto {

    @Id
    private String id;
    private String nombre;
    private Float precio;
    private String categoria;
    private String descripcion;
    private String actualizacion;

}
