package com.coderhouse.repository;

import com.coderhouse.model.Product;
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

    public User saveUser(User user) {
        return mongoTemplate.save(user, "Users");
    }

    public Product saveProduct(Product product) {
        return mongoTemplate.save(product, "Products");
    }

    public List<User> findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.find(query, User.class);
    }

    public void findAndRemoveById(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.findAllAndRemove(query, "Products");
    }

    public List<Product> findByCategory(String categoria) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(categoria));
        return mongoTemplate.find(query, Product.class);
    }

//    public Producto findById(String id) {
//        return mongoTemplate.findById(id, Producto.class);
//    }

//    public void findAndRemove(Query query) {
//        mongoTemplate.findOne(query, Producto.class);
//    }

}
