package com.autobots.automanager.repositorios.empresa.select;

import com.autobots.automanager.entitades.empresa.Mercadoria;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MercadoriaSelecionador {
    public Mercadoria selecionar(List<Mercadoria> mercadorias, long id) {
        for (Mercadoria mercadoria : mercadorias) {
            if (mercadoria.getId() == id) {
                return mercadoria;
            }
        }
        return null;
    }
}