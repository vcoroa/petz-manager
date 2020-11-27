package br.com.petz.petzmanager.controller;

import static java.util.Objects.nonNull;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.petz.petzmanager.controller.dto.ClienteDTO;
import br.com.petz.petzmanager.model.Cliente;
import br.com.petz.petzmanager.service.ClienteService;

@RestController
@RequestMapping("/petzmanager/v1/clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@GetMapping
	public List<ClienteDTO> listar(String nome) {
		
		List<Cliente> clientes = new ArrayList<Cliente>();
		
		if (nome == null) {
			clientes = clienteService.listarTodos();
		} else {
			clientes = clienteService.listarPorNome(nome);
		}
		return ClienteDTO.converter(clientes);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<ClienteDTO> cadastrar(@RequestBody @Valid Cliente cliente, UriComponentsBuilder uriBuilder) {
		cliente = clienteService.cadastrar(cliente);
		URI uri = uriBuilder.path("/clientes/{id}").buildAndExpand(cliente.getIdCliente()).toUri();
		return ResponseEntity.created(uri).body(new ClienteDTO(cliente));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteDTO> detalhar(@PathVariable Long id) {
		Cliente cliente = clienteService.obterPorId(id);
		if (nonNull(cliente)) {
			return ResponseEntity.ok(new ClienteDTO(cliente));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<ClienteDTO> atualizar(@PathVariable Long id, @RequestBody @Valid Cliente clienteParam) {
		Cliente cliente = clienteService.atualizar(clienteParam);
		if (nonNull(cliente)) {
			return ResponseEntity.ok(new ClienteDTO(cliente));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Cliente cliente = clienteService.obterPorId(id);
		if (nonNull(cliente)) {
			clienteService.remover(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}







