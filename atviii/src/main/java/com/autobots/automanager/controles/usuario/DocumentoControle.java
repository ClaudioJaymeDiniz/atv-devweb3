package com.autobots.automanager.controles.usuario;

import com.autobots.automanager.adicionador.usuario.AdicionadorLinkDocumento;
import com.autobots.automanager.entitades.usuario.Documento;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.repositorios.usuario.DocumentoRepositorio;
import com.autobots.automanager.repositorios.usuario.UsuarioRepositorio;
import com.autobots.automanager.repositorios.usuario.create.DocumentoCadastrador;
import com.autobots.automanager.repositorios.usuario.delete.DocumentoExcluidor;
import com.autobots.automanager.repositorios.usuario.select.DocumentoSelecionador;
import com.autobots.automanager.repositorios.usuario.update.DocumentoAtualizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {

    @Autowired
    private AdicionadorLinkDocumento adicionadorLink;

    @Autowired
    private DocumentoRepositorio repositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private DocumentoSelecionador selecionador;

    @Autowired
    private DocumentoCadastrador cadastrador;

    @Autowired
    private DocumentoAtualizador documentoAtualizador;

    @Autowired
    private DocumentoExcluidor documentoExcluidor;

    @GetMapping("/{id}")
    public ResponseEntity<Documento> obterDocumento(@PathVariable long id) {

        List<Documento> documentos = repositorio.findAll();
        Documento documento = selecionador.selecionar(documentos, id);

        if (documento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            adicionadorLink.adicionarLink(documento);
            return new ResponseEntity<>(documento, HttpStatus.FOUND);
        }
    }

    @GetMapping("/documentos")
    public ResponseEntity<List<Documento>> obterDocumentos() {

        List<Documento> documentos = repositorio.findAll();

        if (documentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            adicionadorLink.adicionarLink(documentos);
            return new ResponseEntity<>(documentos, HttpStatus.OK);
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Documento>> obterusuarioDocumento(@PathVariable long id) {

        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Documento> documentos = usuario.getDocumentos().stream().collect(Collectors.toList());

        if (documentos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            adicionadorLink.adicionarLink(documentos);
            return new ResponseEntity<>(documentos, HttpStatus.OK);
        }
    }

    @PostMapping("/cadastro/{id}")
    public ResponseEntity<Void> cadastrarDocumento(@RequestBody List<Documento> documento, @PathVariable long id) {

        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        cadastrador.cadastrar(usuario, documento);
        usuarioRepositorio.save(usuario);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/atualizar/usuario/{id}")
    public ResponseEntity<Void> atualizarusuarioDocumento(@RequestBody List<Documento> atualizacao, @PathVariable long id) {

        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        documentoAtualizador.atualizar(usuario.getDocumentos(), atualizacao);
        usuarioRepositorio.save(usuario);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<Void> atualizarDocumento(@RequestBody Documento atualizacao, @PathVariable long id) {

        Documento documento = repositorio.findById(id).orElse(null);

        if (documento == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        documentoAtualizador.atualizar(documento, atualizacao);
        repositorio.save(documento);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/excluir/usuario/{id}")
    public ResponseEntity<Void> excluirUsuarioDocumento(@RequestBody List<Documento> documentos, @PathVariable long id) {

        Usuario usuario = usuarioRepositorio.findById(id).orElse(null);

        if (usuario == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        documentoExcluidor.excluir(usuario, documentos);
        usuarioRepositorio.save(usuario);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}