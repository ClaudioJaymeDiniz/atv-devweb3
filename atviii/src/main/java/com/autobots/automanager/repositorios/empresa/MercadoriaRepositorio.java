package com.autobots.automanager.repositorios.empresa;

import com.autobots.automanager.entitades.empresa.Mercadoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MercadoriaRepositorio extends JpaRepository<Mercadoria, Long> {

}
