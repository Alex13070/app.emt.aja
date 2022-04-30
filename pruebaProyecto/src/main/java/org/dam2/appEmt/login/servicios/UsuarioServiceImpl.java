package org.dam2.appEmt.login.servicios;

import java.util.Optional;

import javax.transaction.Transactional;

import org.dam2.appEmt.login.modelo.Rol;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.repositorio.RolRepository;
import org.dam2.appEmt.login.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * UsuarioService
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository daoUsuario;

    @Autowired
    private RolRepository daoRol;

    @Override
    public boolean insert(Usuario usuario) {
        boolean exito = false;

        if (!daoUsuario.existsById(usuario.getCorreo())) {
            daoUsuario.save(usuario);
            exito = true;
        }
        
        return exito;
    }

    @Override
    public boolean update(Usuario usuario) {
        boolean exito = false;

        if (daoUsuario.existsById(usuario.getCorreo())) {

            daoUsuario.save(usuario);
            exito = true;
        }
        
        return exito;
    }

    @Override
    public Optional<Usuario> findById(String id) {
        return daoUsuario.findById(id);
    }

    @Override
    public Optional<Usuario> findByCorreoAndClave(String correo, String clave) {
        
        return daoUsuario.findByCorreoAndClave(correo, clave);
    }

    @Override
    public boolean existsById(String id) {
        return daoUsuario.existsById(id);
    }

    @Override
    public boolean addRol(String correo, String nombreRol) {
        Optional<Rol> rol = daoRol.findByNombre(nombreRol);
        Optional<Usuario> usuario = daoUsuario.findByCorreo(correo);

        boolean respuesta = false;

        if (usuario.isPresent() && rol.isPresent()) {
            Usuario u = usuario.get();
            u.addRol(rol.get());
            daoUsuario.save(u);
        }
        return respuesta;
        
    }

    /*
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) daoUsuario.findAll();
    }
    */    
}