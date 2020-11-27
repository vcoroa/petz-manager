package br.com.petz.petzmanager.integration;

import static org.assertj.core.api.Assertions.assertThat;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.petz.petzmanager.model.Cliente;
import br.com.petz.petzmanager.service.ClienteService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CadastroClienteIntegrationTests {
	
	@Autowired
	private ClienteService clienteService;
	
	@Test
	public void deveAtribuirId_QuandoCadastrarClienteComDadosCorretos() {
		Cliente novoCliente = new Cliente();
		novoCliente.setNome("João Da Silva");
		novoCliente.setCpf("87086435040");
		novoCliente.setEmail("joao@gmail.com");
		
		/*Pet pet = new Pet();
		pet.setNome("Thor");
		pet.setDescricao("Peludo");
		pet.setRaca("Labrador");
		pet.setEspecie("Cão");
		pet.setDono(novoCliente);
		novoCliente.setPets(new ArrayList<Pet>());
		novoCliente.getPets().add(pet);*/
		
		novoCliente = clienteService.cadastrar(novoCliente);
		
		assertThat(novoCliente).isNotNull();
		assertThat(novoCliente.getIdCliente()).isNotNull();
		
	}
	
	@Test(expected = ConstraintViolationException.class)
	public void deveFalhar_QuandoCadastrarClienteSemNome() {
		Cliente novoCliente = new Cliente();
		novoCliente.setNome(null);
		novoCliente.setCpf("87086435040");
		novoCliente.setEmail("joao@gmail.com");
		
		novoCliente = clienteService.cadastrar(novoCliente);
	}

}
