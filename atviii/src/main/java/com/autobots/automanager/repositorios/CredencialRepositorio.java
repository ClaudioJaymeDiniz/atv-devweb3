package com.autobots.automanager.repositorios;


import com.autobots.automanager.entitades.Credencial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CredencialRepositorio extends JpaRepository<Credencial, Long> {
}
