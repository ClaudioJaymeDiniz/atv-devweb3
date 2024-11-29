package com.autobots.automanager.modelos.documento;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.StringVerificadorNulo;

import java.util.List;

public class DocumentoExcluidor {
    private final StringVerificadorNulo verificador;

    public DocumentoExcluidor() {
        this.verificador = new StringVerificadorNulo();
    }
    public void excluir(Cliente cliente, Documento documento) {
        if(documento != null) {
            if (
                    !verificador.verificar(documento.getTipo()) &&
                            !verificador.verificar(documento.getNumero())
            ) {
                cliente.getDocumentos().remove(documento);
            }
        }
    }

    public void excluir(Cliente cliente, List<Documento> documentos) {
        for (Documento documentoExcluido : documentos) {
            if(documentoExcluido.getId() != null) {
                excluir(cliente, documentoExcluido);
            }
        }
    }
}
