package br.com.petz.petzmanager.service;

import java.util.List;

import br.com.petz.petzmanager.model.Cliente;

public interface ClienteService {

	List<Cliente> listarTodos();

	List<Cliente> listarPorNome(String nome);
	
	Cliente cadastrar(Cliente cliente);
	
	Cliente atualizar(Cliente cliente);
	
	Cliente obterPorId(Long id);

	void remover(Long id);

}
