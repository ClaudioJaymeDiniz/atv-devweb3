package com.autobots.automanager.repositorios.usuario;

import com.autobots.automanager.entitades.usuario.CredencialUsuarioSenha;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialUsuarioSenhaRepositorio extends JpaRepository<CredencialUsuarioSenha, Long> {
}