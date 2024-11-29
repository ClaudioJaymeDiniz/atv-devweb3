package com.autobots.automanager.controles.usuario;

import com.autobots.automanager.adicionador.usuario.AdicionadorLinkTelefone;
import com.autobots.automanager.entitades.empresa.Empresa;
import com.autobots.automanager.entitades.usuario.Telefone;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.repositorios.empresa.EmpresaRepositorio;
import com.autobots.automanager.repositorios.empresa.select.EmpresaSelecionador;
import com.autobots.automanager.repositorios.usuario.TelefoneRepositorio;
import com.autobots.automanager.repositorios.usuario.UsuarioRepositorio;
import com.autobots.automanager.repositorios.usuario.create.TelefoneCadastrador;
import com.autobots.automanager.repositorios.usuario.delete.TelefoneExcluidor;
import com.autobots.automanager.repositorios.usuario.select.TelefoneSelecionador;
import com.autobots.automanager.repositorios.usuario.select.UsuarioSelecionador;
import com.autobots.automanager.repositorios.usuario.update.TelefoneAtualizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

    @Autowired
    private AdicionadorLinkTelefone adicionadorLink;

    @Autowired
    private TelefoneRepositorio repositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private TelefoneSelecionador selecionador;

    @Autowired
    private UsuarioSelecionador usuarioSelecionador;

    @Autowired
    private EmpresaRepositorio empresaRepositorio;

    @Autowired
    private EmpresaSelecionador empresaSelecionador;

    @Autowired
    private TelefoneCadastrador telefoneCadastrador;

    @GetMapping("/{id}")
    public ResponseEntity<Telefone> obterTelefone(@PathVariable long id) {

        List<Telefone> Telefones = repositorio.findAll();
        Telefone Telefone = selecionador.selecionar(Telefones, id);

        if (Telefone == null) {
            ResponseEntity<Telefone> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLink.adicionarLink(Telefone);
            ResponseEntity<Telefone> resposta = new ResponseEntity<Telefone>(Telefone, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/telefones")
    public ResponseEntity<List<Telefone>> obterTelefones() {

        List<Telefone> Telefones = repositorio.findAll();

        if (Telefones.isEmpty()) {
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLink.adicionarLink(Telefones);
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(Telefones, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/usuario/{id}")
    public ResponseEntity<List<Telefone>> obterUsuarioTelefone(@PathVariable long id) {

        List<Usuario> usuarios = usuarioRepositorio.findAll();
        Usuario usuario = usuarioSelecionador.selecionar(usuarios, id);
        List<Telefone> Telefones = usuario.getTelefones().stream().collect(Collectors.toList());

        if (Telefones.isEmpty()) {
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLink.adicionarLink(Telefones);
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(Telefones, HttpStatus.FOUND);

            return resposta;
        }
    }

    @GetMapping("/empresa/{id}")
    public ResponseEntity<List<Telefone>> obterEmpresaTelefone(@PathVariable long id) {

        List<Empresa> empresas = empresaRepositorio.findAll();
        Empresa empresa = empresaSelecionador.selecionar(empresas, id);
        List<Telefone> Telefones = empresa.getTelefones().stream().collect(Collectors.toList());

        if (Telefones.isEmpty()) {
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLink.adicionarLink(Telefones);
            ResponseEntity<List<Telefone>> resposta = new ResponseEntity<>(Telefones, HttpStatus.FOUND);

            return resposta;
        }
    }

    @PostMapping("/cadastro/usuario/{id}")
    @Transactional
    public void cadastrarUsuarioTelefone(@RequestBody List<Telefone> telefone, @PathVariable long id) {

        Usuario usuario = usuarioRepositorio.getById(id);
        telefoneCadastrador.cadastrar(usuario.getTelefones(), telefone);

        usuarioRepositorio.save(usuario);
    }

    @PostMapping("/cadastro/empresa/{id}")
    @Transactional
    public void cadastrarEmpresaTelefone(@RequestBody List<Telefone> telefone, @PathVariable long id) {

        Empresa empresa = empresaRepositorio.getById(id);
        telefoneCadastrador.cadastrar(empresa.getTelefones(), telefone);

        empresaRepositorio.save(empresa);
    }

    @PutMapping("/atualizar/usuario/{id}")
    @Transactional
    public void atualizarUsuarioTelefone(@RequestBody List<Telefone> atualizacao, @PathVariable long id) {

        List<Usuario> usuarios = usuarioRepositorio.findAll();
        Usuario usuario = usuarioSelecionador.selecionar(usuarios, id);
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(usuario.getTelefones(), atualizacao);

        usuarioRepositorio.save(usuario);
    }

    @PutMapping("/atualizar/empresa/{id}")
    @Transactional
    public void atualizarEmpresaTelefone(@RequestBody List<Telefone> atualizacao, @PathVariable long id) {

        List<Empresa> empresas = empresaRepositorio.findAll();
        Empresa empresa = empresaSelecionador.selecionar(empresas, id);
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(empresa.getTelefones(), atualizacao);

        empresaRepositorio.save(empresa);
    }

    @PutMapping("/atualizar/{id}")
    public void atualizarTelefone(@RequestBody Telefone atualizacao, @PathVariable long id) {

        List<Telefone> telefones = repositorio.findAll();
        Telefone telefone = selecionador.selecionar(telefones, id);
        TelefoneAtualizador atualizador = new TelefoneAtualizador();
        atualizador.atualizar(telefone, atualizacao);

        repositorio.save(telefone);
    }

    @DeleteMapping("/excluir/usuario/{id}")
    @Transactional
    public void excluirUsuarioTelefone(@RequestBody List<Telefone> telefones, @PathVariable long id) {

        Usuario usuario = usuarioRepositorio.getById(id);
        TelefoneExcluidor excluidor = new TelefoneExcluidor();
        excluidor.excluir(usuario, telefones);

        usuarioRepositorio.save(usuario);
    }
}