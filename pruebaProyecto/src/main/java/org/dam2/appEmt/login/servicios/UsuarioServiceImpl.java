package org.dam2.appEmt.login.servicios;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import org.dam2.appEmt.login.modelo.Rol;
import org.dam2.appEmt.login.modelo.Usuario;
import org.dam2.appEmt.login.repositorio.RolRepository;
import org.dam2.appEmt.login.repositorio.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

/**
 * UsuarioService
 */
@Service
public class UsuarioServiceImpl implements IUsuarioService, UserDetailsService {

    @Autowired
    private UsuarioRepository daoUsuario;

    @Autowired
    private RolRepository daoRol;

    @Autowired 
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public boolean insert(Usuario usuario) {
        boolean exito = false;

        if (!daoUsuario.existsById(usuario.getCorreo())) {
            usuario.setClave(passwordEncoder.encode(usuario.getClave()));
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
            respuesta = true; 
        }
        return respuesta;
        
    }

    @Override
    public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {
        Optional<Usuario> usuario = daoUsuario.findByCorreo(correo);

        if (usuario.isEmpty()){
            throw new UsernameNotFoundException("Usuario no encontrado en la base de datos");
        }

        Usuario u = usuario.get();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        u.getRoles().forEach(rol -> {
            authorities.add(new SimpleGrantedAuthority(rol.getNombre()));
        });

        return new User(u.getCorreo(), u.getClave(), authorities);
    }

    /*
    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) daoUsuario.findAll();
    }
    */    
}
