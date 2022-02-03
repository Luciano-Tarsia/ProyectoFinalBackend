package com.coderhouse.dto;

import com.coderhouse.model.Usuario;

public class UsuarioDTO {
    private String nombre;
    private String tipo;

    public UsuarioDTO(){
    }

    public UsuarioDTO(String nombre, String tipo){
        this.nombre = nombre;
        this.tipo = tipo;
    }

    public UsuarioDTO(Usuario usuario){
        this.nombre = usuario.getNombre();
        this. tipo = usuario.getTipo();
    }


}
