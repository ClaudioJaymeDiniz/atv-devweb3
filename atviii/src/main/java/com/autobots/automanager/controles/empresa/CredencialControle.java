package com.autobots.automanager.controles.empresa;

import com.autobots.automanager.adicionador.empresa.AdicionadorLinkCredencialCodigoBarra;
import com.autobots.automanager.adicionador.usuario.AdicionadorLinkCredencialUsuarioSenha;
import com.autobots.automanager.entitades.Credencial;
import com.autobots.automanager.entitades.empresa.CredencialCodigoBarra;
import com.autobots.automanager.entitades.usuario.CredencialUsuarioSenha;
import com.autobots.automanager.entitades.usuario.Usuario;
import com.autobots.automanager.repositorios.CredencialRepositorio;
import com.autobots.automanager.repositorios.empresa.CredencialCodigoBarraRepositorio;
import com.autobots.automanager.repositorios.empresa.create.CredencialCodigoBarraCadastrador;
import com.autobots.automanager.repositorios.empresa.delete.CredencialExcluidor;
import com.autobots.automanager.repositorios.empresa.update.CredencialAtualizadorCodigoBarra;
import com.autobots.automanager.repositorios.usuario.CredencialUsuarioSenhaRepositorio;
import com.autobots.automanager.repositorios.usuario.UsuarioRepositorio;
import com.autobots.automanager.repositorios.usuario.create.CredencialUsuarioSenhaCadastrador;
import com.autobots.automanager.repositorios.usuario.select.UsuarioSelecionador;
import com.autobots.automanager.repositorios.usuario.update.CredencialUsuarioSenhaAtualizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/credencial")
public class CredencialControle {

    @Autowired
    private AdicionadorLinkCredencialUsuarioSenha adicionadorLinkUsuarioSenha;

    @Autowired
    private AdicionadorLinkCredencialCodigoBarra adicionadorLinkCodigoBarra;

    @Autowired
    private CredencialUsuarioSenhaRepositorio repositorioUsuarioSenha;

    @Autowired
    private CredencialCodigoBarraRepositorio repositorioCodigoBarra;

    @Autowired
    private CredencialRepositorio repositorio;

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private UsuarioSelecionador usuarioSelecionador;

    @GetMapping("/usuariosenha/{id}")
    public ResponseEntity<CredencialUsuarioSenha> obterCredencialUsuarioSenha(@PathVariable long id) {

        CredencialUsuarioSenha credencial = repositorioUsuarioSenha.findById(id).orElse(null);

        if (credencial == null) {
            ResponseEntity<CredencialUsuarioSenha> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLinkUsuarioSenha.adicionarLink(credencial);
            ResponseEntity<CredencialUsuarioSenha> resposta = new ResponseEntity<CredencialUsuarioSenha>(credencial, HttpStatus.FOUND);

            return resposta;
        }
    }

    @GetMapping("/codigobarra/{id}")
    public ResponseEntity<Credencial> obterCredencialCodigoBarra(@PathVariable long id) {
        CredencialCodigoBarra credencial = repositorioCodigoBarra.findById(id).orElse(null);
        if (credencial == null) {
            ResponseEntity<Credencial> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;
        } else {
            adicionadorLinkCodigoBarra.adicionarLink(credencial);
            ResponseEntity<Credencial> resposta = new ResponseEntity<Credencial>(credencial, HttpStatus.FOUND);
            return resposta;
        }
    }

    @GetMapping("/usuariosenha/credenciais")
    public ResponseEntity<List<CredencialUsuarioSenha>> obterCredenciaisUsuarioSenha() {

        List<CredencialUsuarioSenha> credenciais = repositorioUsuarioSenha.findAll();

        if (credenciais.isEmpty()) {
            ResponseEntity<List<CredencialUsuarioSenha>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLinkUsuarioSenha.adicionarLink(credenciais);
            ResponseEntity<List<CredencialUsuarioSenha>> resposta = new ResponseEntity<>(credenciais, HttpStatus.FOUND);

            return resposta;
        }
    }

    @GetMapping("/codigobarra/credenciais")
    public ResponseEntity<List<CredencialCodigoBarra>> obterCredenciaisCodigoBarra() {

        List<CredencialCodigoBarra> credenciais = repositorioCodigoBarra.findAll();

        if (credenciais.isEmpty()) {
            ResponseEntity<List<CredencialCodigoBarra>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLinkCodigoBarra.adicionarLink(credenciais);
            ResponseEntity<List<CredencialCodigoBarra>> resposta = new ResponseEntity<>(credenciais, HttpStatus.FOUND);

            return resposta;
        }
    }

    @GetMapping("/usuarosenha/credenciais/{id}")
    public ResponseEntity<List<CredencialUsuarioSenha>> obterUsuarioCredenciaisUsuarioSenha(@PathVariable long id) {

        List<Usuario> usuarios = usuarioRepositorio.findAll();
        Usuario usuario = usuarioSelecionador.selecionar(usuarios, id);
        List<CredencialUsuarioSenha> credenciais = usuario.getCredenciaisUsuarioSenha().stream().collect(Collectors.toList());

        if (credenciais.isEmpty()) {
            ResponseEntity<List<CredencialUsuarioSenha>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);
            return resposta;

        } else {
            adicionadorLinkUsuarioSenha.adicionarLink(credenciais);
            ResponseEntity<List<CredencialUsuarioSenha>> resposta = new ResponseEntity<>(credenciais, HttpStatus.FOUND);

            return resposta;
        }
    }

    @GetMapping("/codigobarra/credenciais/{id}")
    public ResponseEntity<List<CredencialCodigoBarra>> obterUsuarioCredenciaisCodigoBarra(@PathVariable long id) {

        List<Usuario> usuarios = usuarioRepositorio.findAll();
        Usuario usuario = usuarioSelecionador.selecionar(usuarios, id);
        List<CredencialCodigoBarra> credenciais = usuario.getCredenciaisCodigoBarra().stream().collect(Collectors.toList());

        if (credenciais.isEmpty()) {
            ResponseEntity<List<CredencialCodigoBarra>> resposta = new ResponseEntity<>(HttpStatus.NOT_FOUND);

            return resposta;

        } else {
            adicionadorLinkCodigoBarra.adicionarLink(credenciais);
            ResponseEntity<List<CredencialCodigoBarra>> resposta = new ResponseEntity<>(credenciais, HttpStatus.FOUND);

            return resposta;
        }
    }

    @PostMapping("/cadastro/usuariosenha/{id}")
    public void cadastrarCredencialUsuarioSenha(@RequestBody List<CredencialUsuarioSenha> credencial, @PathVariable long id) {

        Usuario usuario = usuarioRepositorio.getById(id);
        CredencialUsuarioSenhaCadastrador cadastrador = new CredencialUsuarioSenhaCadastrador();
        cadastrador.cadastrar(usuario, credencial);

        usuarioRepositorio.save(usuario);
    }


    @PostMapping("/cadastro/codigobarra/{id}")
    public void cadastrarCredencialCodigoBarra(@RequestBody List<CredencialCodigoBarra> credencial, @PathVariable long id) {

        Usuario usuario = usuarioRepositorio.getById(id);
        CredencialCodigoBarraCadastrador cadastrador = new CredencialCodigoBarraCadastrador();
        cadastrador.cadastrar(usuario, credencial);

        usuarioRepositorio.save(usuario);
    }

    @PutMapping("/atualizar/usuariosenha/usuario/{id}")
    public void atualizarUsuarioCredencialUsuarioSenha(@RequestBody List<CredencialUsuarioSenha> atualizacao, @PathVariable long id) {

        List<Usuario> usuarios = usuarioRepositorio.findAll();
        Usuario usuario = usuarioSelecionador.selecionar(usuarios, id);
        Set<CredencialUsuarioSenha> credenciais = usuario.getCredenciaisUsuarioSenha();
        CredencialUsuarioSenhaAtualizador atualizador = new CredencialUsuarioSenhaAtualizador();
        atualizador.atualizar(credenciais, atualizacao);

        usuarioRepositorio.save(usuario);
    }

    @PutMapping("/atualizar/usuariosenha/{id}")
    public void atualizarCredencialUsuarioSenha(@RequestBody CredencialUsuarioSenha atualizacao, @PathVariable long id) {

        CredencialUsuarioSenha credencial = repositorioUsuarioSenha.getById(id);
        CredencialUsuarioSenhaAtualizador atualizador = new CredencialUsuarioSenhaAtualizador();
        atualizador.atualizar(credencial, atualizacao);

        repositorio.save(credencial);
    }

    @PutMapping("/atualizar/codigobarra/usuario/{id}")
    public void atualizarUsuarioCredencialCodigoBarra(@RequestBody List<CredencialCodigoBarra> atualizacao, @PathVariable long id) {

        List<Usuario> usuarios = usuarioRepositorio.findAll();
        Usuario usuario = usuarioSelecionador.selecionar(usuarios, id);
        Set<CredencialCodigoBarra> credenciais = usuario.getCredenciaisCodigoBarra();
        CredencialAtualizadorCodigoBarra atualizador = new CredencialAtualizadorCodigoBarra();
        atualizador.atualizar(credenciais, atualizacao);

        usuarioRepositorio.save(usuario);
    }

    @PutMapping("/atualizar/codigobarra/{id}")
    public void atualizarCredencialCodigoBarra(@RequestBody CredencialCodigoBarra atualizacao, @PathVariable long id) {

        CredencialCodigoBarra credencial = repositorioCodigoBarra.getById(id);
        CredencialAtualizadorCodigoBarra atualizador = new CredencialAtualizadorCodigoBarra();
        atualizador.atualizar(credencial, atualizacao);

        repositorio.save(credencial);
    }

    @DeleteMapping("/excluir/{id}")
    public void excluirCredencial(@PathVariable long id) {

        Credencial credencial = repositorio.getById(id);
        CredencialExcluidor credencialExcluidor = new CredencialExcluidor();
        credencialExcluidor.excluir(credencial, credencial);

        repositorio.save(credencial);

    }
}