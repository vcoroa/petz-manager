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

import br.com.petz.petzmanager.controller.dto.PetDTO;
import br.com.petz.petzmanager.model.Cliente;
import br.com.petz.petzmanager.model.Pet;
import br.com.petz.petzmanager.service.PetService;

@RestController
@RequestMapping("/petzmanager/v1/pets")
public class PetController {
	
	@Autowired
	private PetService petService;
	
	@GetMapping
	public List<PetDTO> listar(String nome) {
		
		List<Pet> pets = new ArrayList<Pet>();
		
		if (nome == null) {
			pets = petService.listarTodos();
		} else {
			pets = petService.listarPorNome(nome);
		}
		return PetDTO.converter(pets);
	}
	
	@GetMapping("/listar-por-dono")
	public List<PetDTO> listarPorCliente(@RequestBody @Valid Cliente cliente) {
		return PetDTO.converter(petService.listarPorCliente(cliente));
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<PetDTO> cadastrar(@RequestBody @Valid Pet pet, UriComponentsBuilder uriBuilder) {
		petService.cadastrar(pet);
		URI uri = uriBuilder.path("/pets/{id}").buildAndExpand(pet.getIdPet()).toUri();
		return ResponseEntity.created(uri).body(new PetDTO(pet));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<PetDTO> detalhar(@PathVariable Long id) {
		Pet pet = petService.obterPorId(id);
		if (nonNull(pet)) {
			return ResponseEntity.ok(new PetDTO(pet));
		}
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<PetDTO> atualizar(@PathVariable Long id, @RequestBody @Valid Pet petParam) {
		petParam.setIdPet(id);
		Pet pet = petService.atualizar(petParam);
		if (nonNull(pet)) {
			return ResponseEntity.ok(new PetDTO(pet));
		}
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Pet pet = petService.obterPorId(id);
		if (nonNull(pet)) {
			petService.remover(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}

}







