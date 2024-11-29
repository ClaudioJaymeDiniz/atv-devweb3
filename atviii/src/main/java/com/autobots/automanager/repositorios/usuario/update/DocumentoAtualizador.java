package com.autobots.automanager.repositorios.usuario.update;


import com.autobots.automanager.entitades.usuario.Documento;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class DocumentoAtualizador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    public void atualizar(Documento documento, Documento atualizacao) {
        if (atualizacao != null && documento != null) {
            if (!verificador.verificar(atualizacao.getTipo().toString())) {
                documento.setTipo(atualizacao.getTipo());
            }

            if (!verificador.verificar(atualizacao.getNumero())) {
                documento.setNumero(atualizacao.getNumero());
            }
        }
    }

    public void atualizar(Set<Documento> documentos, List<Documento> atualizacoes) {
        if (documentos == null || atualizacoes == null || documentos.isEmpty() || atualizacoes.isEmpty()) {
            return;
        }

        for (Documento atualizacao : atualizacoes) {
            if (atualizacao.getId() != null) {
                for (Documento documento : documentos) {
                    if (atualizacao.getId().equals(documento.getId())) {
                        atualizar(documento, atualizacao);
                        break;
                    }
                }
            }
        }
    }
}
