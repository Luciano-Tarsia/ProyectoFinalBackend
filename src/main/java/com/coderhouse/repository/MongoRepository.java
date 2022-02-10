package com.coderhouse.repository;

import com.coderhouse.model.Cart;
import com.coderhouse.model.Order;
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

    // Métodos para la clase User

    public User saveUser(User user) {
        return mongoTemplate.save(user, "Users");
    }

    public List<User> findByEmail(String email) {
        Query query = new Query();
        query.addCriteria(Criteria.where("email").is(email));
        return mongoTemplate.find(query, User.class);
    }

    public User findByUserId(String id) {
        return mongoTemplate.findById(id, User.class, "Users");
    }

    // Métodos para la clase Product

    public Product saveProduct(Product product) {
        return mongoTemplate.save(product, "Products");
    }

    public List<Product> findAllProducts() {
        return mongoTemplate.findAll(Product.class, "Products");
    }

    public void findAndRemoveById(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        Product productToRemove = mongoTemplate.findOne(query, Product.class, "Products");
        mongoTemplate.remove(productToRemove);
    }

    public List<Product> findByCategory(String categoria) {
        Query query = new Query();
        query.addCriteria(Criteria.where("category").is(categoria));
        return mongoTemplate.find(query, Product.class);
    }

    public Product findByProductId(String id) {
        return mongoTemplate.findById(id, Product.class, "Products");
    }

    public Long countByProductId(String id) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(id));
        return mongoTemplate.count(query, Product.class, "Products");
    }

    // Métodos para la clase Cart

    public Cart saveCart(Cart cart) {
        return mongoTemplate.save(cart, "Carts");
    }

    public Cart findCartById(String id) {
        return mongoTemplate.findById(id, Cart.class, "Carts");
    }

    public Cart updateCart(Cart cart) {
        return mongoTemplate.save(cart, "Carts");
    }

    // Métodos para la clase Order

    public Order saveOrder(Order order) {
        return mongoTemplate.save(order, "Orders");
    }

    public Order findByOrderId(String orderId) {
        return mongoTemplate.findById(orderId, Order.class, "Orders");
    }

    public Long countByOrderId(String orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(orderId));
        return mongoTemplate.count(query, Order.class, "Orders");
    }

    public void deleteOrder(String orderId) {
        Query query = new Query();
        query.addCriteria(Criteria.where("id").is(orderId));
        Order orderToRemove = mongoTemplate.findOne(query, Order.class, "Orders");
        mongoTemplate.remove(orderToRemove);
    }

}
