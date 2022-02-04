package com.coderhouse.service;

import com.coderhouse.handle.ExceptionProducto;
import com.coderhouse.model.Producto;
import com.coderhouse.repository.MongoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository mongoRepository;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Producto crearProducto(Producto producto) {
        logger.info("Creación");
        Producto productoNuevo = mongoRepository.save(producto, "productos");
        return productoNuevo;
    }

    public Producto getProductoById(String id) throws ExceptionProducto {
        if (mongoRepository.findById(id) != null) {
            Producto producto = mongoRepository.findById(id);
            return producto;
        } else {
            throw new ExceptionProducto("El usuario que se intentó actualizar no existe");
        }
    }

    private Producto actualizar(String id, Producto producto) {
        producto.setId(id);
        producto.setActualizacion("Chau");
        Producto productoActualizado = mongoRepository.save(producto, "productos");
        return productoActualizado;
    }

    public Producto updateProducto(String id, Producto producto) throws ExceptionProducto {
        logger.info("Actualización");
        if (mongoRepository.findById(id) != null) {
            return actualizar(id, mongoRepository.findById(id));
        } else {
            throw new ExceptionProducto("El producto que se intentó actualizar no existe");
        }
    }

    public void deleteByName(String nombre) throws ExceptionProducto {
        logger.info("Eliminación");
        try {
            Producto producto = mongoRepository.findOne((new Query(where("nombre").is(nombre))));
            mongoRepository.findAndRemove((new Query(where("nombre").is(producto.getNombre()))));
        } catch (NullPointerException n) {
            throw new ExceptionProducto("No se ha encontrado un usuario con ese nombre");
        }
    }

}
