package com.autobots.automanager.repositorios.usuario.select;

import com.autobots.automanager.entitades.usuario.Telefone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelefoneSelecionador {
    public Telefone selecionar(List<Telefone> telefones, long id) {

        for (Telefone telefone : telefones) {
            if (telefone.getId() == id) {
                return telefone;
            }
        }
        return null;
    }
}