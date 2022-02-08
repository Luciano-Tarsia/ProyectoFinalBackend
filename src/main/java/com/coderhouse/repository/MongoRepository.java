package com.coderhouse.repository;

import com.coderhouse.model.Producto;
import com.coderhouse.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class MongoRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Producto save(Producto producto, String collection) {
        return mongoTemplate.save(producto, collection);
    }

    public User saveUser(User user, String collection) {
        return mongoTemplate.save(user, collection);
    }

    public List<User> findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.find(query, User.class);
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
