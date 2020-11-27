package br.com.petz.petzmanager.service;

import java.util.List;

import br.com.petz.petzmanager.model.Cliente;
import br.com.petz.petzmanager.model.Pet;

public interface PetService {

	List<Pet> listarTodos();

	List<Pet> listarPorNome(String nome);
	
	List<Pet> listarPorCliente(Cliente cliente);
	
	Pet cadastrar(Pet cliente);
	
	Pet atualizar(Pet cliente);
	
	Pet obterPorId(Long id);

	void remover(Long id);

}
