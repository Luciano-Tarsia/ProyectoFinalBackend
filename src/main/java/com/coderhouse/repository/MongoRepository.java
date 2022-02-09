package com.coderhouse.repository;

import com.coderhouse.model.Cart;
import com.coderhouse.model.Product;
import com.coderhouse.model.User;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    // Methods used by the User class

    public User saveUser(User user) {
        return mongoTemplate.save(user, "Users");
    }

    public List<User> findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.find(query, User.class);
    }

    // Methods used by the Product class

    public Product saveProduct(Product product) {
        return mongoTemplate.save(product, "Products");
    }

    public List<Product> findAllProducts() {
        return mongoTemplate.findAll(Product.class, "Products");
    }

    public void findAndRemoveById(String id) { //Chequear el funcionamiento
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        mongoTemplate.findAllAndRemove(query, "Products");
    }

    public List<Product> findByCategory(String categoria) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(categoria));
        return mongoTemplate.find(query, Product.class);
    }

    public Product findByProductId(String id) {
        return mongoTemplate.findById(id, Product.class, "Products");
    }

    // Methods used by the Cart class

    public Cart saveCart(Cart cart) {
        return mongoTemplate.save(cart, "Carts");
    }

    public Cart findCartById(String id) {
        return mongoTemplate.findById(id, Cart.class, "Carts");
    }

    public Cart updateCart(Cart cart) {
        return mongoTemplate.save(cart, "Carts");
    }

}
