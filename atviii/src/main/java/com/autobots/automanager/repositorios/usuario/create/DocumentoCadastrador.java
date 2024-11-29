package com.autobots.automanager.repositorios.usuario.create;

import com.autobots.automanager.entitades.usuario.Documento;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.modelos.StringVerificadorNulo;
import com.autobots.automanager.repositorios.usuario.DocumentoRepositorio;
import com.autobots.automanager.repositorios.usuario.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentoCadastrador {

    private StringVerificadorNulo verificador = new StringVerificadorNulo();

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private DocumentoRepositorio documentoRepositorio;

    public void cadastrar(Usuario usuario, Documento documento) {

        if (documento != null) {
            Documento novoDocumento = new Documento();
            novoDocumento.setTipo(documento.getTipo());

            if (!verificador.verificar(documento.getNumero())) {
                novoDocumento.setNumero(documento.getNumero());
                novoDocumento.setDataEmissao(documento.getDataEmissao());
            }

            usuario.getDocumentos().add(novoDocumento);
            documentoRepositorio.save(novoDocumento);
            usuarioRepositorio.save(usuario);
        }
    }

    public void cadastrar(Usuario usuario, List<Documento> documentosNovos) {

        for (Documento documento : documentosNovos) {
            cadastrar(usuario, documento);
        }
    }
}