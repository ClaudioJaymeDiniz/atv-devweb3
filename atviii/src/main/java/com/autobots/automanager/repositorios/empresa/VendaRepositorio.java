package com.autobots.automanager.repositorios.empresa;

import com.autobots.automanager.entitades.empresa.Venda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VendaRepositorio extends JpaRepository<Venda, Long> {
}
