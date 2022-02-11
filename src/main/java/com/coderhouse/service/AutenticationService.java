package com.coderhouse.service;

import com.coderhouse.auxiliaries.jwt.JwtProvider;
import com.coderhouse.handle.ExceptionAutentication;
import com.coderhouse.model.User;
import com.coderhouse.model.UserRedis;
import com.coderhouse.repository.MongoRepository;
import com.coderhouse.repository.RedisRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AutenticationService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private RedisRepository redisRepository;

    @Autowired
    private MongoRepository mongoRepository;

    @Autowired
    JwtProvider jwtProvider;

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

    public String login(String email, String password) throws ExceptionAutentication {
        User user = mongoRepository.findUserByEmail(email);
        if (user.getPassword().equals(password)){
            UserRedis userRedis = redisRepository.findById(email);
            if(userRedis == null || (userRedis.getTime().getMinute() - LocalDateTime.now().getMinute()) > 30){
                // Me fijo si tengo al usuario en memoria o si pasaron los 30 min
                LocalDateTime time = LocalDateTime.now();
                String passwordJwt = jwtProvider.getJWTToken(email);
                userRedis = new UserRedis(email, passwordJwt, time);
                redisRepository.save(userRedis);
                return passwordJwt;
            }else {
                return userRedis.getPassword();
            }
        }else{
            logger.error("La contraseña no es correcta");
            throw new ExceptionAutentication("La contraseña no es correcta");
        }
    }

}