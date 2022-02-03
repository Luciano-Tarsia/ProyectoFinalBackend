package com.coderhouse.controller;

import com.coderhouse.handle.ExceptionUsuario;
import com.coderhouse.model.Usuario;
import com.coderhouse.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/coder")
public class Controller {

    @Autowired
    UserService service;

    @PostMapping(path = "/usuario")
    private Usuario crearUsuario(@RequestBody Usuario nuevoUsuario) throws ResponseStatusException {
        return service.crearUsuario(nuevoUsuario);
    }


    @GetMapping(path = "/usuario/{id}")
    private Usuario getUsuarioById(@PathVariable String id) throws ResponseStatusException {
        try {
            return service.getUsuarioById(id);
        }catch(ExceptionUsuario exceptionUsuario){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionUsuario.getMessage());
        }
    }

    @PutMapping(path = "/usuario")
    private Usuario updateUsuario(@RequestBody @Validated Usuario usuario) throws ResponseStatusException {
        try {
            return service.updateUsuario(usuario);
        }catch(ExceptionUsuario exceptionUsuario){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionUsuario.getMessage());
        }
    }

    @DeleteMapping(path = "/usuario/{nombre}")
    private void deleteUsuario(@PathVariable String nombre) throws ResponseStatusException {
        try{
            service.deleteByName(nombre);
        }catch(ExceptionUsuario exceptionUsuario){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, exceptionUsuario.getMessage());
        }

    }
    
}
