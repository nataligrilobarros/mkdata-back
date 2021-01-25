package com.br.mkdata.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.mkdata.domain.Cliente;
import com.br.mkdata.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")
public class ClienteResource {
	
	@Autowired
	private ClienteService clienteService;
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<?> pesquisarTodos() {
		List<Cliente> clientes = clienteService.findAll();
		return ResponseEntity.ok().body(clientes);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		Cliente cliente = clienteService.find(id);
		return ResponseEntity.ok().body(cliente);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/pesquisaCPF/{cpf}", method = RequestMethod.GET)
	public ResponseEntity<?> verificarCFP(@PathVariable String cpf) {
		int resposta = clienteService.pesquisarPorCpf(cpf);
		return ResponseEntity.ok().body(resposta);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/pesquisaCNPJ/{cnpj}", method = RequestMethod.GET)
	public ResponseEntity<?> verificarCNPJ(@PathVariable String cnpj) {
		int resposta = clienteService.pesquisarPorCnpj(cnpj);
		return ResponseEntity.ok().body(resposta);
	}
	
	@CrossOrigin
	@RequestMapping(value = "/filtrar/{pesquisa}", method = RequestMethod.GET)
	public ResponseEntity<?> filtrar(@PathVariable String pesquisa) {
		List<Cliente> clientes = clienteService.filtrar(pesquisa);
		return ResponseEntity.ok().body(clientes);
	}
	
	@CrossOrigin
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> cadastrar(@RequestBody Cliente obj) {
		obj = clienteService.cadastrar(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody Cliente obj, @PathVariable Integer id){
		obj.setId(id);
		obj = clienteService.atualizar(obj);
		return ResponseEntity.noContent().build();
	}
	
	@CrossOrigin
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<?> excluir(@PathVariable Integer id) {
		clienteService.apagar(id);
		return ResponseEntity.ok().body(true);
	}
	
}
