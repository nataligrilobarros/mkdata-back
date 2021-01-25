package com.br.mkdata.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.br.mkdata.domain.Cliente;
import com.br.mkdata.domain.Telefone;
import com.br.mkdata.repositories.ClienteRepository;

@Service
public class ClienteService {

	@Autowired
	ClienteRepository clienteRepository;
	
	@Autowired
	TelefoneService telefoneService;
	
	public Cliente find(Integer id) {
		Optional<Cliente> cliente = clienteRepository.findById(id);
		return cliente.orElse(null);
	}
	
	public int pesquisarPorCpf(String cpf) {
		int total = clienteRepository.pesquisarPorCpf(cpf);
		return total;
	}

	public int pesquisarPorCnpj(String cnpj) {
		int total = clienteRepository.pesquisarPorCnpj(cnpj);
		return total;
	}

	public List<Cliente> filtrar(String pesquisa) {
		List<Cliente> clientes = clienteRepository.filtrar(pesquisa);
		return clientes;
	}

	public Cliente cadastrar(Cliente obj) {
		Date dtAtual = new Date(System.currentTimeMillis());
		obj.setDataCadastro(dtAtual);
		obj.setId(null);
		Cliente cli = clienteRepository.save(obj);
		List<Telefone> telefones = new ArrayList<>();
		for (Telefone tel : obj.getTelefones()) {
			Telefone t = new Telefone();
			t.setCliente(cli);
			t.setDdd(tel.getDdd());
			t.setTelefone(tel.getTelefone());
			telefones.add(t);
		}
		telefoneService.insert(telefones);
		return find(cli.getId());
	}
	
	public Cliente atualizar(Cliente obj) {
		Cliente clienteAtual = find(obj.getId());
		clienteRepository.save(obj);	
		
		Cliente cli = clienteRepository.save(obj);
		List<Telefone> telefones = new ArrayList<>();
		
		//*** apagando todos os telefones
		for (Telefone tel : clienteAtual.getTelefones()) {
			telefoneService.deletarTelefone(tel);
		}
		
		//*** Adicionando telefones vindo no body da requisicao
		for (Telefone tel : obj.getTelefones()) {
			Telefone t = new Telefone();
			t.setCliente(cli);
			t.setDdd(tel.getDdd());
			t.setTelefone(tel.getTelefone());
			telefones.add(t);
		}
		telefoneService.insert(telefones);
		return find(cli.getId());
	}
	
	public void apagar(Integer id) {
		Cliente cliente = find(id);
		for (Telefone tel : cliente.getTelefones()) {
			telefoneService.deletarTelefone(tel);
		}
		clienteRepository.deleteById(id);
	}
	
	public List<Cliente> findAll() {
		return clienteRepository.findAll();
	}
}
