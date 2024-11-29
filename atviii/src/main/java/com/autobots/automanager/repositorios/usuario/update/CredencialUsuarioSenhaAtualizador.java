package com.autobots.automanager.repositorios.usuario.update;

import com.autobots.automanager.entitades.usuario.CredencialUsuarioSenha;
import com.autobots.automanager.modelos.StringVerificadorNulo;

import java.util.List;
import java.util.Set;

public class CredencialUsuarioSenhaAtualizador {
    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(CredencialUsuarioSenha credencial, CredencialUsuarioSenha atualizacao) {

        if (atualizacao != null && credencial != null) {
            if (!verificador.verificar(atualizacao.getNomeUsuario())) {
                credencial.setNomeUsuario(atualizacao.getNomeUsuario());
            }
            if (!verificador.verificar(atualizacao.getSenha())) {
                credencial.setSenha(atualizacao.getSenha());
            }
        }
    }

    public void atualizar(Set<CredencialUsuarioSenha> credenciais, List<CredencialUsuarioSenha> atualizacoes) {

        if (credenciais == null || atualizacoes == null || credenciais.isEmpty() || atualizacoes.isEmpty()) {
            return;

        }

        for (CredencialUsuarioSenha atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                for (CredencialUsuarioSenha credencial : credenciais) {
                    if (atualizacao.getId().equals(credencial.getId())) {
                        atualizar(credencial, atualizacao);
                        break;
                    }
                }
            }
        }
    }
}