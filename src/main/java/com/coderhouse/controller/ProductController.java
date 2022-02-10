package com.coderhouse.controller;

import com.coderhouse.handle.ExceptionProduct;
import com.coderhouse.model.Product;
import com.coderhouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/productos")
public class ProductController {

    @Autowired
    ProductService productService;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    @PostMapping(path = "/agregar")
    private Product agregarProducto(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping(path = "/eliminar/{id}")
    private void eliminarProducto(@PathVariable("id") String id) {
        productService.eliminateProduct(id);
    }

    @GetMapping(path = "/{categoria}")
    private List<Product> traerProductosPorCategoria(@PathVariable("categoria") String categoria) {
        return productService.traerProductosPorCategoria(categoria);
    }

    @GetMapping(path = "/traer/{productId}")
    private Product traerProductosPorId(@PathVariable("productId") String productId) throws ResponseStatusException {
        try {
            return productService.traerProductoPorId(productId);
        }catch (ExceptionProduct exceptionProduct){
            logger.error(exceptionProduct.getMessage());
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionProduct.getMessage());
        }
    }

    @GetMapping(path = "")
    private List<Product> traerProductos() {
        return productService.traerProductos();
    }

}
