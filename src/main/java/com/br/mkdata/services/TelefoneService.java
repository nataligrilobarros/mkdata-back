package com.br.mkdata.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.mkdata.domain.Telefone;
import com.br.mkdata.repositories.TelefoneRepository;

@Service
public class TelefoneService {

	@Autowired
	TelefoneRepository telefoneRepository;
	
	public void insert(List<Telefone> telefones) {
		for (Telefone tel : telefones) {
			telefoneRepository.save(tel);
		}
	}
	
	public void deletarTelefone(Telefone telefone) {
		telefoneRepository.delete(telefone);
	}
	
	
}
