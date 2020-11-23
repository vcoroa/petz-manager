package br.com.petz.petzmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.petz.petzmanager.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	
	List<Cliente> findByNome(String nome);
}
