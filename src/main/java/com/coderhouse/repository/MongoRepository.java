package com.coderhouse.repository;

import com.coderhouse.model.Producto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MongoRepository {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Producto save(Producto producto, String collection) {
        return mongoTemplate.save(producto, collection);
    }

    public Producto findById(String id) {
        return mongoTemplate.findById(id, Producto.class);
    }

    public void findAndRemove(Query query) {
        mongoTemplate.findOne(query, Producto.class);
    }

    public Producto findOne(Query query) {
        return mongoTemplate.findOne(query, Producto.class);
    }
}
