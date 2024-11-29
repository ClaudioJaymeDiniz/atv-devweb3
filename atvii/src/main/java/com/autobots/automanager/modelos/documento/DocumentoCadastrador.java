package com.autobots.automanager.modelos.documento;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.StringVerificadorNulo;

import java.util.List;

public class DocumentoCadastrador {
    private final StringVerificadorNulo verificador;

    public DocumentoCadastrador() {
        this.verificador = new StringVerificadorNulo();
    }
    public void cadastrar(Cliente cliente, Documento documento) {
        if (documento == null) {
            Documento novoDocumento = new Documento();
            if(!verificador.verificar(documento.getTipo())){
                novoDocumento.setTipo(documento.getTipo());
            }
            if(!verificador.verificar(documento.getNumero())){
                novoDocumento.setNumero(documento.getNumero());
            }
            cliente.getDocumentos().add(novoDocumento);
        }
    }
    public void cadastrar(Cliente cliente, List<Documento> documentos) {
        for (Documento documento : documentos) {
            cadastrar(cliente, documento);
        }
    }
}
