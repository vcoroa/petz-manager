package br.com.petz.petzmanager.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.Data;

@Entity
@Data
public class Pet {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPet;
	private String nome;
	private String raca;
	private String especie;
	private String descricao;
	
	@ManyToOne()
	@JoinColumn(name = "idCliente")
	@JsonBackReference
	private Cliente dono;
	
}
