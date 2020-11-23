package br.com.petz.petzmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petz.petzmanager.model.Cliente;
import br.com.petz.petzmanager.model.Pet;

public interface PetRepository extends JpaRepository<Pet, Long> {
	
	List<Pet> findByNomeContaining(String nome);
	
	List<Pet> findByDono(Cliente cliente);
}
