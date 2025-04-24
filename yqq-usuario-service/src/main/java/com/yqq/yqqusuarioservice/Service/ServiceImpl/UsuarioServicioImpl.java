package com.yqq.yqqusuarioservice.Service.ServiceImpl;
import com.yqq.yqqusuarioservice.Entidad.Usuario;
import com.yqq.yqqusuarioservice.Repocitorio.UsuarioRepositorio;
import com.yqq.yqqusuarioservice.Service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Override
    public List<Usuario> Listar() {
        return (List<Usuario>) usuarioRepositorio.findAll();
    }

    @Override
    public Optional<Usuario> Buscar(Long id) {
        return usuarioRepositorio.findById(id);
    }

    @Override
    public Usuario Guardar(Usuario usuario) {
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public Usuario Modificar(Long id, Usuario usuario) {
        usuario.setId(id);
        return usuarioRepositorio.save(usuario);
    }

    @Override
    public void Eliminar(Long id) {
        usuarioRepositorio.deleteById(id);
    }
}