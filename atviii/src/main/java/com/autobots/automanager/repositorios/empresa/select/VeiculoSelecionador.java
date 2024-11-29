package com.autobots.automanager.repositorios.empresa.select;

import com.autobots.automanager.entitades.empresa.Veiculo;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VeiculoSelecionador {
    public Veiculo selecionar(List<Veiculo> veiculos, long id) {

        for (Veiculo veiculo : veiculos) {
            if (veiculo.getId() == id) {
                return veiculo;
            }
        }
        return null;
    }
}