package com.springboot.prueba.controllers;

import com.springboot.prueba.dao.UsuarioDao;
import com.springboot.prueba.models.Usuario;
import com.springboot.prueba.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
 private UsuarioDao usuarioDao;
    @Autowired
 private JWTUtil jwtUtil;

    @RequestMapping (value = "api/usuarios/{id}", method = RequestMethod.GET)
    public Usuario getUsuario(@PathVariable Long id){
      Usuario usuario =new Usuario();
      usuario.setId(id);
      usuario.setNombre("monica");
      usuario.setApellido("robles");
      usuario.setEmail("monicar@gmail.com");
      usuario.setPassword("12345");
      usuario.setTelefono("32123231");
        return usuario;
    }
    @RequestMapping (value="api/usuarios", method = RequestMethod.GET)
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){


        if(!validarToken(token)){
            return null;
        }
        return usuarioDao.getUsuarios();
    }


private boolean validarToken(String token){

    String usuarioId=jwtUtil.getKey(token);
        return usuarioId !=null;

}




    @RequestMapping (value="api/usuarios", method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario){
        Argon2 argon2= Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash =argon2.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(hash);
        usuarioDao.registar(usuario);

    }

    @RequestMapping ("/usuario2")
    public Usuario editar(){
        Usuario usuario =new Usuario();
        usuario.setNombre("monica");
        usuario.setApellido("robles");
        usuario.setEmail("monicar@gmail.com");
        usuario.setPassword("12345");
        usuario.setTelefono("32123231");
        return usuario;
    }
    @RequestMapping (value = "api/usuarios/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token,
                         @PathVariable Long id){
        if(!validarToken(token)){return;}
        usuarioDao.eliminar(id);
    }



    }

