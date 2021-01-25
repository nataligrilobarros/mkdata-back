package com.br.mkdata.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Cliente implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //*** para gerar ID automaticos
	private Integer id;
	
	private String nome;
	private String cpf;
	private String cnpj;
	private int tipoCliente;
	private String ie;
	private String rg;
	private Date dataCadastro;
	private int ativo;
	

	@OneToMany(mappedBy = "cliente")
	private List<Telefone> telefones = new ArrayList<>();
	
	public Cliente() {
	}

	public Cliente(Integer id, String nome, String cpf, String cnpj, int tipoCliente, String ie, String rg,
			Date dataCadastro, int ativo) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.cnpj = cnpj;
		this.tipoCliente = tipoCliente;
		this.ie = ie;
		this.rg = rg;
		this.dataCadastro = dataCadastro;
		this.ativo = ativo;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getCnpj() {
		return cnpj;
	}


	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}


	public int getTipoCliente() {
		return tipoCliente;
	}


	public void setTipoCliente(int tipoCliente) {
		this.tipoCliente = tipoCliente;
	}


	public String getIe() {
		return ie;
	}


	public void setIe(String ie) {
		this.ie = ie;
	}


	public String getRg() {
		return rg;
	}


	public void setRg(String rg) {
		this.rg = rg;
	}


	public Date getDataCadastro() {
		return dataCadastro;
	}


	public void setDataCadastro(Date dataCadastro) {
		this.dataCadastro = dataCadastro;
	}


	public int getAtivo() {
		return ativo;
	}


	public void setAtivo(int ativo) {
		this.ativo = ativo;
	}


	public List<Telefone> getTelefones() {
		return telefones;
	}


	public void setTelefones(List<Telefone> telefones) {
		this.telefones = telefones;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente other = (Cliente) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "Cliente [id=" + id + ", nome=" + nome + ", cpf=" + cpf + ", cnpj=" + cnpj + ", tipoCliente="
				+ tipoCliente + ", ie=" + ie + ", rg=" + rg + ", dataCadastro=" + dataCadastro + ", ativo=" + ativo
				+ ", telefones=" + telefones + "]";
	}


}
