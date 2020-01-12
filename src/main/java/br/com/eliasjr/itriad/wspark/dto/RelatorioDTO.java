package br.com.eliasjr.itriad.wspark.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class RelatorioDTO {
	@JsonFormat(pattern = "dd/MM/yyyy")
	Date dia;
	Long numeroCarros;
	Double valorRecebido;

	public RelatorioDTO() {
		this.numeroCarros = 0L;
		this.valorRecebido = 0d;
	}

	public RelatorioDTO(Date dia, Long numeroCarros, Double valorRecebido) {
		super();
		this.dia = dia;
		this.numeroCarros = numeroCarros;
		this.valorRecebido = valorRecebido;
	}

	public Date getDia() {
		return dia;
	}

	public void setDia(Date dia) {
		this.dia = dia;
	}

	public Long getNumeroCarros() {
		return numeroCarros;
	}

	public void setNumeroCarros(Long numeroCarros) {
		this.numeroCarros = numeroCarros;
	}

	public Double getValorRecebido() {
		return valorRecebido;
	}

	public void setValorRecebido(Double valorRecebido) {
		this.valorRecebido = valorRecebido;
	}

}
