package com.coderhouse.service;

import com.coderhouse.handle.ExceptionProduct;
import com.coderhouse.model.Product;
import com.coderhouse.repository.MongoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository mongoRepository;

    private static final Logger logger = LoggerFactory.getLogger(ProductService.class);

    public Product addProduct(Product product) {
        logger.info("Agregando producto");
        return mongoRepository.saveProduct(product);
    }

    public void eliminateProduct(String productId) throws ExceptionProduct {
        logger.info("Iniciando eliminado del producto");
        if (mongoRepository.countByProductId(productId) > 0) {
            mongoRepository.findAndRemoveById(productId);
            logger.info("Producto eliminado");
        } else {
            logger.error("El producto a eliminar no está presente en la base de datos");
            throw new ExceptionProduct("El producto a eliminar no está presente en la base de datos");
        }
    }

    public List<Product> traerProductos() {
        logger.info("Trayendo el listado de productos completo");
        return mongoRepository.findAllProducts();
    }

    public List<Product> traerProductosPorCategoria(String categoria) {
        logger.info("Trayendo el listado de productos cuya categoria es:" + categoria);
        return mongoRepository.findByCategory(categoria);
    }

    public Product traerProductoPorId(String productId) throws ExceptionProduct {
        logger.info("Trayendo los el producto");
        if (mongoRepository.countByProductId(productId) > 0) {
            return mongoRepository.findByProductId(productId);
        } else {
            logger.error("El producto no está presente en la base de datos");
            throw new ExceptionProduct("El producto buscado no se encuentra en la base de datos");
        }
    }
}
