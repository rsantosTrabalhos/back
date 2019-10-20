package com.example.doggis.api.model;

public class RelatorioClienteServicosMes {

	private String nome;
	private String dataRealizacao;
	private int patazReceber;
	private int totalPataz;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(String dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public int getPatazReceber() {
		return patazReceber;
	}

	public void setPatazReceber(int patazReceber) {
		this.patazReceber = patazReceber;
	}

	public int getTotalPataz() {
		return totalPataz;
	}

	public void setTotalPataz(int totalPataz) {
		this.totalPataz = totalPataz;
	}

}
