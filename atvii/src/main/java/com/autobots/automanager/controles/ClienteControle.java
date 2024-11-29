package com.autobots.automanager.controles;

import java.util.List;

import com.autobots.automanager.entidades.Endereco;
import com.autobots.automanager.modelos.endereco.EnderecoAtualizador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelos.adicionador.AdicionadorLinkCliente;
import com.autobots.automanager.modelos.cliente.ClienteAtualizador;
import com.autobots.automanager.modelos.cliente.ClienteSelecionador;
import com.autobots.automanager.repositorios.ClienteRepositorio;

@RestController
@RequestMapping("/cliente")
public class ClienteControle {

	@Autowired
	private ClienteRepositorio repositorio;

	@Autowired
	private AdicionadorLinkCliente adicionadorLink;

	@GetMapping("/{id}")
	public ResponseEntity<Cliente> obterCliente
			(@PathVariable long id) {

		Cliente cliente = repositorio.findById(id).orElse(null);

		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			adicionadorLink.adicionarLink(cliente);
			return new ResponseEntity<>(cliente, HttpStatus.OK);
		}
	}

	@GetMapping
	public ResponseEntity<List<Cliente>> obterClientes() {

		List<Cliente> clientes = repositorio.findAll();

		if (clientes.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);

		} else {
			adicionadorLink.adicionarLink(clientes);
			return new ResponseEntity<>(clientes, HttpStatus.OK);
		}
	}

	@PostMapping("/cadastro")
	public ResponseEntity<Void> cadastrarCliente
			(@RequestBody Cliente cliente) {

		repositorio.save(cliente);
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping("/atualizar/{id}")
	public ResponseEntity<Void> atualizarCliente
			(@PathVariable long id, @RequestBody Cliente atualizacao) {

		Cliente cliente = repositorio.findById(id).orElse(null);

		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

		ClienteAtualizador atualizador = new ClienteAtualizador();
		atualizador.atualizar(cliente, atualizacao);
		repositorio.save(cliente);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping("/atualizar/endereco/{id}")
	public ResponseEntity<Void> atualizarClienteEndereco
			(@PathVariable long id, @RequestBody Endereco atualizacao) {

		Cliente cliente = repositorio.findById(id).orElse(null);

		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		EnderecoAtualizador atualizador = new EnderecoAtualizador();
		atualizador.atualizar(cliente.getEndereco(), atualizacao);
		repositorio.save(cliente);

		return new ResponseEntity<>(HttpStatus.OK);
	}

	@DeleteMapping("/excluir/{id}")
	public ResponseEntity<Void> excluirCliente(@PathVariable long id) {
		Cliente cliente = repositorio.findById(id).orElse(null);
		if (cliente == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		repositorio.delete(cliente);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
