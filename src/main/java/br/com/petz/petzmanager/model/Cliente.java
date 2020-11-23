package br.com.petz.petzmanager.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	private String cpf;
	private String nome;
	private String email;
	
	@OneToMany(mappedBy = "dono", targetEntity = Pet.class, fetch = FetchType.LAZY)
	private List<Pet> pets;

}
