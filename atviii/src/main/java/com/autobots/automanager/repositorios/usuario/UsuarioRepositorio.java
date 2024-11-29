package com.autobots.automanager.repositorios.usuario;

import com.autobots.automanager.entitades.usuario.Documento;
import com.autobots.automanager.entitades.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Long> {
    Set<Documento> findDocumentosById(Long usuarioId);
}