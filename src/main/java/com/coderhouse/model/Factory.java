package com.coderhouse.model;

import com.coderhouse.service.UserService;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Data
public class Factory {

    private static final Logger logger = LoggerFactory.getLogger(Factory.class);

    public Usuario crearUsuario(String nombre, String tipo){
        switch (tipo){
            case "ADMIN":
                logger.info("Hola Admin");
                return new Admin(nombre, tipo);
            case "EDITOR":
                logger.info("Hola Editor");
                return new Editor(nombre, tipo);
            case "CLIENT":
                logger.info("Hola Client");
                return new Client(nombre, tipo);
            default:
                logger.info("Null :,(");
                return null;
        }
    }
}
