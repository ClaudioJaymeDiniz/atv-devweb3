package com.autobots.automanager.repositorios.usuario;

import com.autobots.automanager.entitades.usuario.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepositorio extends JpaRepository<Email, Long> {
}