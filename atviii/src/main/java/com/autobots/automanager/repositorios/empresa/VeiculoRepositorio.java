package com.autobots.automanager.repositorios.empresa;

import com.autobots.automanager.entitades.empresa.Veiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VeiculoRepositorio extends JpaRepository<Veiculo, Long> {
}
