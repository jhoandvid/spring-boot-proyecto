package com.springboot.prueba.dao;

import com.springboot.prueba.models.Usuario;

import java.util.List;

public interface UsuarioDao {
    List<Usuario> getUsuarios();

    void eliminar(Long id);

    void registar(Usuario usuario);

    Usuario obtenerUsuariosPorCredenciales(Usuario usuario);
}
