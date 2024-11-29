package com.autobots.automanager.repositorios.empresa.update;

import com.autobots.automanager.entitades.empresa.Mercadoria;
import com.autobots.automanager.modelos.StringVerificadorNulo;

import java.util.List;
import java.util.Set;

public class MercadoriaAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Mercadoria mercadoria, Mercadoria atualizacao) {

        if (atualizacao == null || mercadoria == null) {
            return;
        }

        if (!verificador.verificar(atualizacao.getNome())) {
            mercadoria.setNome(atualizacao.getNome());
        }
        if (!verificador.verificar(atualizacao.getDescricao())) {
            mercadoria.setDescricao(atualizacao.getDescricao());
        }
        mercadoria.setCadastro(atualizacao.getCadastro());
        mercadoria.setValidade(atualizacao.getValidade());
        mercadoria.setFabricao(atualizacao.getFabricao());
        mercadoria.setQuantidade(atualizacao.getQuantidade());
        mercadoria.setValor(atualizacao.getValor());
    }

    public void atualizar(Set<Mercadoria> mercadorias, List<Mercadoria> atualizacoes) {

        if (mercadorias == null || atualizacoes == null || mercadorias.isEmpty() || atualizacoes.isEmpty()) {
            return;
        }

        for (Mercadoria atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                for (Mercadoria mercadoria : mercadorias) {
                    if (atualizacao.getId().equals(mercadoria.getId())) {
                        atualizar(mercadoria, atualizacao);
                        break;
                    }
                }
            }
        }
    }
}