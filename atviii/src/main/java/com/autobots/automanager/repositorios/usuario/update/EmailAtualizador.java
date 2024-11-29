package com.autobots.automanager.repositorios.usuario.update;

import com.autobots.automanager.entitades.usuario.Email;
import com.autobots.automanager.modelos.StringVerificadorNulo;

import java.util.List;
import java.util.Set;

public class EmailAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Email email, Email atualizacao) {

        if (atualizacao != null && email != null) {
            if (!verificador.verificar(atualizacao.getEndereco())) {
                email.setEndereco(atualizacao.getEndereco());
            }
        }
    }

    public void atualizar(Set<Email> emails, List<Email> atualizacoes) {

        if (emails == null || atualizacoes == null || emails.isEmpty() || atualizacoes.isEmpty()) {
            return;
        }

        for (Email atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                for (Email email : emails) {
                    if (atualizacao.getId().equals(email.getId())) {
                        atualizar(email, atualizacao);
                        break;
                    }
                }
            }
        }
    }
}