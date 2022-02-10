package com.coderhouse.service;

import com.coderhouse.handle.ExceptionAutentication;
import com.coderhouse.model.User;
import com.coderhouse.repository.MongoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AutenticationService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository mongoRepository;

    private static final Logger logger = LoggerFactory.getLogger(AutenticationService.class);

    public User createNewUser(User user) throws ExceptionAutentication {
        logger.info("Creando un nuevo usuario de nombre:" + user.getName());
        if (!Objects.equals(user.getPassword(), user.getPassword2())) {
            //Chequeo si las contraseñas coinciden
            logger.error("Las contaseñas no coinciden, el usuario no fue creado");
            throw new ExceptionAutentication("Las contaseñas no coinciden, el usuario no fue creado");
        } else if (mongoRepository.findByEmail(user.getEmail()).size() > 0) {
            //Chequeo que el mail no esté siendo usado por otro usuario
            logger.error("El email ya está siendo usado");
            throw new ExceptionAutentication("El email ya está siendo usado");
        } else {
            User newUser = mongoRepository.saveUser(user);
            logger.info("Usuario creado!");
            return newUser;
        }
    }
}