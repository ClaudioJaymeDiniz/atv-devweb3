package com.autobots.automanager.modelos.telefone;

import com.autobots.automanager.entidades.Telefone;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TelefoneSelecionador {
    public Telefone selecionar(List<Telefone> telefones, long id){
        for (Telefone telefone : telefones) {
            if (telefone.getId() == id) {
                return telefone;
            }
        }
        return null;
    }
}
