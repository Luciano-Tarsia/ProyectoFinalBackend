package com.coderhouse.controller;

import com.coderhouse.handle.ExceptionProducto;
import com.coderhouse.model.Producto;
import com.coderhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/coder")
public class Controller {

    @Autowired
    UserService service;

    @PostMapping(path = "/producto")
    private Producto crearProducto(@RequestBody Producto nuevoProducto) throws ResponseStatusException {
        return service.crearProducto(nuevoProducto);
    }


    @GetMapping(path = "/producto/{id}")
    private Producto getProductoById(@PathVariable String id) throws ResponseStatusException {
        try {
            return service.getProductoById(id);
        } catch (ExceptionProducto exceptionProducto) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionProducto.getMessage());
        }
    }

    @PutMapping(path = "/producto/{id}")
    private Producto updateProducto(@PathVariable String id, @RequestBody @Validated Producto producto) throws ResponseStatusException {
        try {
            return service.updateProducto(id, producto);
        } catch (ExceptionProducto exceptionProducto) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionProducto.getMessage());
        }
    }

    @DeleteMapping(path = "/producto/{nombre}")
    private void deleteProducto(@PathVariable String nombre) throws ResponseStatusException {
        try {
            service.deleteByName(nombre);
        } catch (ExceptionProducto exceptionProducto) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionProducto.getMessage());
        }
    }
}
