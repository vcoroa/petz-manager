package br.com.petz.petzmanager.service.implementation;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.petz.petzmanager.model.Cliente;
import br.com.petz.petzmanager.repository.ClienteRepository;
import br.com.petz.petzmanager.service.ClienteService;

@Service
public class ClienteServiceImpl implements ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;

	@Override
	public List<Cliente> listarTodos() {
		return clienteRepository.findAll();
	}

	@Override
	public List<Cliente> listarPorNome(String nome) {
		return clienteRepository.findByNome(nome);
	}

	@Override
	public Cliente cadastrar(Cliente cliente) {
		return clienteRepository.save(cliente);
		
	}

	@Override
	public Cliente obterPorId(Long id) {
		
		Optional<Cliente> cliente = clienteRepository.findById(id);
		if (cliente.isPresent()) {
			return cliente.get();
		} else {
			return null;
		}
	}

	@Override
	public Cliente atualizar(Cliente clienteParam) {
		Cliente cliente = this.obterPorId(clienteParam.getIdCliente());
		cliente.setEmail(clienteParam.getEmail());
		cliente.setNome(clienteParam.getNome());
		cliente.getPets().addAll(clienteParam.getPets());
		return clienteRepository.save(cliente);
	}

	@Override
	public void remover(Long id) {
		clienteRepository.deleteById(id);
	}
	
}
