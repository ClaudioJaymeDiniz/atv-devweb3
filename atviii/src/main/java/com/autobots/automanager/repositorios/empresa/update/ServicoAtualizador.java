package com.autobots.automanager.repositorios.empresa.update;

import com.autobots.automanager.entitades.empresa.Servico;
import com.autobots.automanager.modelos.StringVerificadorNulo;

import java.util.List;
import java.util.Set;

public class ServicoAtualizador {

        private StringVerificadorNulo verificador = new StringVerificadorNulo();

        public void atualizar(Servico servico, Servico atualizacao) {

            if (atualizacao == null || servico == null) {
                return;
            }
            if (!verificador.verificar(atualizacao.getNome())) {
                servico.setNome(atualizacao.getNome());
            }
            if (!verificador.verificar(atualizacao.getDescricao())) {
                servico.setDescricao(atualizacao.getDescricao());
            }
            servico.setValor(atualizacao.getValor());
        }

        public void atualizar(Set<Servico> servicos, List<Servico> atualizacoes) {

            if (servicos == null || atualizacoes == null || servicos.isEmpty() || atualizacoes.isEmpty()) {
                return;
            }
            for (Servico atualizacao : atualizacoes) {
                if (atualizacao.getId() != null) {
                    for (Servico servico : servicos) {
                        if (atualizacao.getId().equals(servico.getId())) {
                            atualizar(servico, atualizacao);
                            break;
                        }
                    }
                }
            }
        }
}
