package com.yqq.yqqusuarioservice.Repocitorio;

import com.yqq.yqqusuarioservice.Entidad.Usuario;
import org.springframework.data.repository.CrudRepository;

public interface UsuarioRepositorio extends CrudRepository<Usuario, Long> {
}
