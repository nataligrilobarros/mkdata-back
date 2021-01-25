package com.br.mkdata.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.mkdata.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

	@Query(value = "select count(*) as total from cliente where cpf = :cpf", nativeQuery=true)
    Integer pesquisarPorCpf(@Param("cpf") String cpf);
	
	@Query(value = "select count(*) as total from cliente where cnpj = :cnpj", nativeQuery=true)
	Integer pesquisarPorCnpj(@Param("cnpj") String cnpj);
	
	@Query(value = "SELECT * FROM cliente WHERE nome LIKE %:pesquisa% AND ativo = 1", nativeQuery=true)
	List<Cliente> filtrar(@Param("pesquisa") String pesquisa);
	
}
