package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.adicionador.AdicionadorLinkDocumento;
import com.autobots.automanager.modelos.documento.DocumentoAtualizador;
import com.autobots.automanager.modelos.documento.DocumentoCadastrador;
import com.autobots.automanager.modelos.documento.DocumentoExcluidor;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {

    @Autowired
    private AdicionadorLinkDocumento adicionadorLink;

    @Autowired
    private DocumentoRepositorio repositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @GetMapping("/documentos")
    public ResponseEntity<List<Documento>> obterDocumentos() {
        List<Documento> documentos = repositorio.findAll();
        if(documentos.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else{
            adicionadorLink.adicionarLink(documentos);
            return ResponseEntity.ok(documentos);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Documento> obterDocumento
            (@PathVariable long id) {
        Documento documento = repositorio.findById(id).orElse(null);
        if(documento == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            adicionadorLink.adicionarLink(List.of(documento));
            return ResponseEntity.ok(documento);
        }
    }

    @GetMapping("/cliente/{id}")
    public  ResponseEntity<List<Documento>> obterClienteDocumentos(@PathVariable long id) {
        Cliente cliente = clienteRepositorio.findById(id).orElse(null);

        if(cliente == null || cliente.getDocumentos().isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            List<Documento> documentos = cliente.getDocumentos();
            adicionadorLink.adicionarLink(documentos);
            return ResponseEntity.ok(documentos);
        }
    }

    @GetMapping("/cadastro/{clienteId}")
    public ResponseEntity<Void> cadastroDocumento
            (@RequestBody List<Documento> documentos, @PathVariable long clienteId) {
        Cliente cliente = clienteRepositorio.findById(clienteId).orElse(null);
        if(cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        DocumentoCadastrador cadastrador = new DocumentoCadastrador();

        cadastrador.cadastrar(cliente, documentos);

        clienteRepositorio.save(cliente);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("atualizar/{clienteId}")
    public ResponseEntity<Void> atualizarClienteDocumentos
            (@RequestBody List<Documento> atualizacao, @PathVariable long clienteId) {
        Cliente cliente = clienteRepositorio.findById(clienteId).orElse(null);

        if(cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        DocumentoAtualizador atualizador = new DocumentoAtualizador();

        atualizador.atualizar(cliente.getDocumentos(), atualizacao);

        clienteRepositorio.save(cliente);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/excluir/{clienteId}")
    public ResponseEntity<Void> excluirClienteDocumento
            (@RequestBody List<Documento> documentos, @PathVariable long clienteId) {
        Cliente cliente = clienteRepositorio.findById(clienteId).orElse(null);

        if(cliente == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        DocumentoExcluidor excluidor = new DocumentoExcluidor();

        excluidor.excluir(cliente, documentos);

        clienteRepositorio.save(cliente);

        return ResponseEntity.noContent().build();
    }

}
