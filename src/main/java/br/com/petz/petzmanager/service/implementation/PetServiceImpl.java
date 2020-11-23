package br.com.petz.petzmanager.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petz.petzmanager.model.Cliente;
import br.com.petz.petzmanager.model.Pet;
import br.com.petz.petzmanager.repository.PetRepository;
import br.com.petz.petzmanager.service.PetService;

@Service
public class PetServiceImpl implements PetService {
	
	@Autowired
	private PetRepository petRepository;

	@Override
	public List<Pet> listarTodos() {
		return petRepository.findAll();
	}

	@Override
	public List<Pet> listarPorNome(String nome) {
		return petRepository.findByNomeContaining(nome);
	}

	@Override
	public void cadastrar(Pet pet) {
		petRepository.save(pet);
		
	}

	@Override
	public Pet obterPorId(Long id) {
		
		Optional<Pet> pet = petRepository.findById(id);
		if (pet.isPresent()) {
			return pet.get();
		} else {
			return null;
		}
	}

	@Override
	public Pet atualizar(Pet petParam) {
		Pet pet = this.obterPorId(petParam.getIdPet());
		
		if(petParam.getNome() != null && !petParam.getNome().isEmpty()) {
			pet.setNome(petParam.getNome());
		}
		
		if(petParam.getRaca()!= null && !petParam.getRaca().isEmpty()) {
			pet.setRaca(petParam.getRaca());
		}
		
		if(petParam.getEspecie() != null && !petParam.getEspecie().isEmpty()) {
			pet.setEspecie(petParam.getEspecie());
		}
		
		if(petParam.getDono()!= null && petParam.getDono().getIdCliente() != null) {
			pet.setDono(petParam.getDono());
		}
		
		return petRepository.save(pet);
	}

	@Override
	public void remover(Long id) {
		petRepository.deleteById(id);
	}

	@Override
	public List<Pet> listarPorCliente(Cliente cliente) {
		return petRepository.findByDono(cliente);
	}
	
}
