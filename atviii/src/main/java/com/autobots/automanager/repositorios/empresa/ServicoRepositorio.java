package com.autobots.automanager.repositorios.empresa;

import com.autobots.automanager.entitades.empresa.Servico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicoRepositorio extends JpaRepository<Servico, Long> {
}
