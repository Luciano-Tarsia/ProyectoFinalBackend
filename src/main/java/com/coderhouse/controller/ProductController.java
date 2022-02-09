package com.coderhouse.controller;

import com.coderhouse.model.Product;
import com.coderhouse.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/productos")
public class ProductController {

    @Autowired
    ProductService productService;

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

}
