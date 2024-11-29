package com.autobots.automanager.repositorios.usuario;

import com.autobots.automanager.entitades.usuario.Documento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentoRepositorio extends JpaRepository<Documento, Long> {
}
