package com.example.doggis.api.model;

public class Veterinario {

	private Long codigo;
	private String registroConselho;
	private Pessoa pessoa;
	private VeterinarioTipoPet veterinarioTipoPet;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getRegistroConselho() {
		return registroConselho;
	}

	public void setRegistroConselho(String registroConselho) {
		this.registroConselho = registroConselho;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public VeterinarioTipoPet getVeterinarioTipoPet() {
		return veterinarioTipoPet;
	}

	public void setVeterinarioTipoPet(VeterinarioTipoPet veterinarioTipoPet) {
		this.veterinarioTipoPet = veterinarioTipoPet;
	}
}
