package com.autobots.automanager.repositorios.usuario;

import com.autobots.automanager.entitades.usuario.Telefone;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelefoneRepositorio extends JpaRepository<Telefone, Long> {
}

