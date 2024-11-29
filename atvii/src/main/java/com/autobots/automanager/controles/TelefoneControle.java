package com.autobots.automanager.controles;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelos.adicionador.AdicionadorLinkTelefone;
import com.autobots.automanager.modelos.cliente.ClienteSelecionador;
import com.autobots.automanager.modelos.telefone.TelefoneAtualizador;
import com.autobots.automanager.modelos.telefone.TelefoneCadastrador;
import com.autobots.automanager.modelos.telefone.TelefoneExcluidor;
import com.autobots.automanager.modelos.telefone.TelefoneSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

    @Autowired
    private TelefoneRepositorio repositorio;

    @Autowired
    private ClienteRepositorio clienteRepositorio;

    @Autowired
    private ClienteSelecionador clienteSelecionador;

    @Autowired
    private TelefoneSelecionador selecionador;

    @Autowired
    private AdicionadorLinkTelefone adicionadorLink;

    @GetMapping("/telefones")
    public ResponseEntity<List<Telefone>> obterTelefone() {
        List<Telefone> telefones = repositorio.findAll();

        if (telefones.isEmpty()) {
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLink.adicionarLink(telefones);
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(telefones, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> obterTelefone
            (@PathVariable long id) {

        List<Telefone> telefones = repositorio.findAll();
        Telefone telefone = selecionador.selecionar(telefones, id);

        if (telefone == null) {
            ResponseEntity<Telefone> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLink.adicionarLink(telefone);
            ResponseEntity<Telefone> resposta = new ResponseEntity<>(telefone, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/telefone/{id}")
    public ResponseEntity<List<Telefone>> obterClienteTelefone
            (@PathVariable long id) {

        List<Cliente> clientes = clienteRepositorio.findAll();
        Cliente cliente = clienteSelecionador.selecionar(clientes, id);

        if (cliente == null) {
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLink.adicionarLink(cliente.getTelefones());
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(cliente.getTelefones(), HttpStatus.FOUND);
            return resposta;

        }
    }

    @PostMapping("/cadastro/{id}")
    public void cadastrarTelefone
            (@RequestBody List<Telefone> telefone, @PathVariable long id) {

        Cliente cliente = clienteRepositorio.getById(id);
        TelefoneCadastrador cadastrador = new TelefoneCadastrador();
        cadastrador.cadastrar(cliente, telefone);
        clienteRepositorio.save(cliente);

    }

    @PutMapping("/atualizar/{id}")
    public void atualizarClienteTelefone
            (@RequestBody List<Telefone> atualizacao, @PathVariable long id) {

        Cliente cliente = clienteRepositorio.getById(id);
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(cliente.getTelefones(), atualizacao);
        clienteRepositorio.save(cliente);

    }


}
