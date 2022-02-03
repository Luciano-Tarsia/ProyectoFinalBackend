package com.coderhouse.service;

import com.coderhouse.handle.ExceptionUsuario;
import com.coderhouse.model.Factory;
import com.coderhouse.model.Usuario;
import com.coderhouse.repository.MongoRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Service
@RequiredArgsConstructor
public class UserService {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    private MongoRepository mongoRepository;
    private final Factory userFactory = new Factory();

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public Usuario crearUsuario(Usuario usuario){
        logger.info("Creación");
        Usuario usuarioDeFactory =  userFactory.crearUsuario(usuario.getNombre(), usuario.getTipo());
        if(usuarioDeFactory == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario ingresado no es valido");
        }
        Usuario usuarioNuevo = mongoRepository.save(usuarioDeFactory, "usuarios");
        return usuarioNuevo;
    }

    public  Usuario getUsuarioById(String id) throws ExceptionUsuario{
        if(mongoRepository.findById(id) != null){
            Usuario usuario = mongoRepository.findById(id);
            return usuario;
        }else {
            throw new ExceptionUsuario("El usuario que se intentó actualizar no existe");
        }
    }

    private Usuario actualizar(Usuario usuario){
        usuario.setActualizacion("Chau");
        Usuario usuarioActualizado = mongoRepository.save(usuario, "usuarios");
        return usuarioActualizado;
    }

    public  Usuario updateUsuario(Usuario usuario) throws ExceptionUsuario {
        logger.info("Actualización");
        Usuario usuarioDeFactory = userFactory.crearUsuario(usuario.getNombre(), usuario.getTipo());
        if(mongoRepository.findById(usuario.getId()) != null){
            return actualizar(mongoRepository.findById(usuario.getId()));
        }else {
            throw new ExceptionUsuario("El usuario que se intentó actualizar no existe");
        }
    }

    public void deleteByName(String nombre) throws ExceptionUsuario {
        logger.info("Eliminación");
        try {
            Usuario usuario = mongoRepository.findOne((new Query(where("nombre").is(nombre))));
            mongoRepository.findAndRemove((new Query(where("nombre").is(usuario.getNombre()))));
        }catch(NullPointerException n){
            throw new ExceptionUsuario("No se ha encontrado un usuario con ese nombre");
        }
    }

}
