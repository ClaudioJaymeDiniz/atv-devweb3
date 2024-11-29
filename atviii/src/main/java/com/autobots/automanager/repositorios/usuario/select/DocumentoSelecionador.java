package com.autobots.automanager.repositorios.usuario.select;

import com.autobots.automanager.entitades.usuario.Documento;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DocumentoSelecionador {
    public Documento selecionar(List<Documento> documentos, long id) {

        for (Documento documento : documentos) {
            if (documento.getId() == id) {
                return documento;
            }
        }
        return null;
    }
}