package br.com.petz.petzmanager.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class Cliente {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idCliente;
	
	private String cpf;
	
	@NotBlank
	@Column(nullable = false)
	private String nome;
	
	@NotBlank
	@Column(nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "dono", targetEntity = Pet.class, fetch = FetchType.LAZY)
	private List<Pet> pets;

}
