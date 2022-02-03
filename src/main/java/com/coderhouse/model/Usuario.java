package com.coderhouse.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Getter
@Setter
@ToString
@Document("usuarios")
public class Usuario implements Serializable {
    @Id
    private String id;
    private String nombre;
    private String tipo;
    private String actualizacion;

    public Usuario(String nombre, String tipo){
        this.nombre = nombre;
        this.tipo = tipo;
        this.actualizacion = "Hola";
    }

}
