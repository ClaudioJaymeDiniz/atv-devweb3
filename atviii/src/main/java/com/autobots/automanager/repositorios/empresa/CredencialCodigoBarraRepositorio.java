package com.autobots.automanager.repositorios.empresa;

import com.autobots.automanager.entitades.empresa.CredencialCodigoBarra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CredencialCodigoBarraRepositorio extends JpaRepository<CredencialCodigoBarra, Long> {
}