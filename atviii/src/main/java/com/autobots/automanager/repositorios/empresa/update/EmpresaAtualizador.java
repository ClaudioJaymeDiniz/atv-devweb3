package com.autobots.automanager.repositorios.empresa.update;

import com.autobots.automanager.entitades.empresa.Empresa;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.modelos.empresa.EmpresaDto;
import com.autobots.automanager.repositorios.usuario.update.EnderecoAtualizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class EmpresaAtualizador {

    private final StringVerificadorNulo verificador = new StringVerificadorNulo();

    @Autowired
    private EnderecoAtualizador enderecoAtualizador;

    private void atualizarDados(Empresa empresa, EmpresaDto atualizacao) {

        if (atualizacao == null) {
            return;
        }

        if (!verificador.verificar(atualizacao.getNomeFantasia())) {
            empresa.setNomeFantasia(atualizacao.getNomeFantasia());
        }

        if (!verificador.verificar(atualizacao.getRazaoSocial())) {
            empresa.setRazaoSocial(atualizacao.getRazaoSocial());
        }
    }

    public void atualizar(Empresa empresa, EmpresaDto atualizacao) {

        if (empresa == null || atualizacao == null) {
            return;
        }

        atualizarDados(empresa, atualizacao);

        if (empresa.getEndereco() != null && atualizacao.getEndereco() != null) {
            enderecoAtualizador.atualizar(empresa.getEndereco(), atualizacao.getEndereco());
        }
    }
}