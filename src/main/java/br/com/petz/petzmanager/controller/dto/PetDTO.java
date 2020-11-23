package br.com.petz.petzmanager.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.petz.petzmanager.model.Pet;
import lombok.Data;

@Data
public class PetDTO {

	private Long id;
	private String nome;
	private String raca;
	private String especie;
	private String descricao;
	//private Cliente dono;
	
	public PetDTO(Pet pet) {
		this.id = pet.getIdPet();
		this.nome = pet.getNome();
		this.raca = pet.getRaca();
		this.especie = pet.getEspecie();
		this.descricao = pet.getDescricao();
		//this.dono = pet.getDono();
	}
	
	public static List<PetDTO> converter(List<Pet> pets) {
		return pets.stream().map(PetDTO::new).collect(Collectors.toList());
	}

}
