package com.autobots.automanager.repositorios.empresa.update;

import com.autobots.automanager.entitades.empresa.CredencialCodigoBarra;

import java.util.List;
import java.util.Set;

public class CredencialAtualizadorCodigoBarra {

    public void atualizar(CredencialCodigoBarra credencial, CredencialCodigoBarra atualizacao) {

        if (atualizacao != null && credencial != null) {
            if (atualizacao.getCodigo() != 0) {
                credencial.setCodigo(atualizacao.getCodigo());
            }
        }
    }

    public void atualizar(Set<CredencialCodigoBarra> credenciais, List<CredencialCodigoBarra> atualizacoes) {

        if (credenciais == null || atualizacoes == null) {
            return;
        }

        for (CredencialCodigoBarra atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                for (CredencialCodigoBarra credencial : credenciais) {
                    if (credencial.getId() != null && atualizacao.getId().equals(credencial.getId())) {
                        atualizar(credencial, atualizacao);
                        break;
                    }
                }
            }
        }
    }
}