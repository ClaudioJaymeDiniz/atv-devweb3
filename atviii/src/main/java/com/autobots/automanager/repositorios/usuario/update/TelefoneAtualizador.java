package com.autobots.automanager.repositorios.usuario.update;

import com.autobots.automanager.entitades.usuario.Telefone;
import com.autobots.automanager.modelos.StringVerificadorNulo;

import java.util.List;
import java.util.Set;

public class TelefoneAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Telefone telefone, Telefone atualizacao) {

        if (telefone == null || atualizacao == null) {
            return;
        }

        if (!verificador.verificar(atualizacao.getDdd())) {
            telefone.setDdd(atualizacao.getDdd());
        }

        if (!verificador.verificar(atualizacao.getNumero())) {
            telefone.setNumero(atualizacao.getNumero());
        }
    }

    public void atualizar(Set<Telefone> telefones, List<Telefone> atualizacoes) {

        for (Telefone atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                telefones.stream()
                        .filter(telefone -> telefone.getId().equals(atualizacao.getId()))
                            .forEach(telefone -> atualizar(telefone, atualizacao));
            }
        }
    }
}
