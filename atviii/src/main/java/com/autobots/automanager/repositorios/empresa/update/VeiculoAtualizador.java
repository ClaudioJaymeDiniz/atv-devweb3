package com.autobots.automanager.repositorios.empresa.update;

import com.autobots.automanager.entitades.empresa.Veiculo;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.empresa.VeiculoDto;

import java.util.List;
import java.util.Set;

public class VeiculoAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Veiculo veiculo, VeiculoDto atualizacao) {

        if (atualizacao == null || veiculo == null) {
            return;
        }
        if (!verificador.verificar(atualizacao.getModelo())) {
            veiculo.setModelo(atualizacao.getModelo());
        }
        if (!verificador.verificar(atualizacao.getPlaca())) {
            veiculo.setPlaca(atualizacao.getPlaca());
        }
        veiculo.setTipo(atualizacao.getTipo());
    }

    public void atualizar(Set<Veiculo> veiculos, List<VeiculoDto> atualizacoes) {

        if (veiculos == null || atualizacoes == null || veiculos.isEmpty() || atualizacoes.isEmpty()) {
            return;
        }

        for (VeiculoDto atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                for (Veiculo veiculo : veiculos) {
                    if (atualizacao.getId().equals(veiculo.getId())) {
                        atualizar(veiculo, atualizacao);
                        break;
                    }
                }
            }
        }
    }
}