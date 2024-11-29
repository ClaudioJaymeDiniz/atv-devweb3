package com.autobots.automanager.repositorios.empresa.select;

import com.autobots.automanager.entitades.empresa.Servico;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ServicoSelecionador {
    public Servico selecionar(List<Servico> servicos, long id) {

        for (Servico servico : servicos) {
            if (servico.getId() == id) {
                return servico;
            }
        }
        return null;
    }
}