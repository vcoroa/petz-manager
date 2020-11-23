package br.com.petz.petzmanager.controller.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import br.com.petz.petzmanager.model.Cliente;
import br.com.petz.petzmanager.model.Pet;
import lombok.Data;

@Data
public class ClienteDTO {

	private Long id;
	private String cpf;
	private String nome;
	private String email;
	private List<Pet> pets = new ArrayList<Pet>();
	
	public ClienteDTO(Cliente cliente) {
		this.id = cliente.getIdCliente();
		this.cpf = cliente.getCpf();
		this.nome = cliente.getNome();
		this.email = cliente.getEmail();
		this.pets = cliente.getPets();
	}
	
	public static List<ClienteDTO> converter(List<Cliente> clientes) {
		return clientes.stream().map(ClienteDTO::new).collect(Collectors.toList());
	}

}
