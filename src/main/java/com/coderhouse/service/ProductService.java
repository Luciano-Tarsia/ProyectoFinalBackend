package com.coderhouse.service;

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

    public Product addProduct(Product product){
        logger.info("Agregando producto");
        return mongoRepository.saveProduct(product);
    }

    public void eliminateProduct(String id){
        mongoRepository.findAndRemoveById(id);
    }

    public List<Product> traerProductosPorCategoria(String categoria){
        return mongoRepository.findByCategory(categoria);
    }

}
