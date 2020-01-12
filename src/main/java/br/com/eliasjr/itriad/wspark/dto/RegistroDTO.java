package br.com.eliasjr.itriad.wspark.dto;

import java.util.Date;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import br.com.eliasjr.itriad.wspark.domain.Registro;
import br.com.eliasjr.itriad.wspark.domain.Veiculo;

public class RegistroDTO {
	private Long id;

	@NotNull(message = "Veículo não pode estar vazio.")
	@JsonProperty("carro")
	private Veiculo veiculo;

	@NotNull(message = "Entrada não pode estar vazio.")
	private Date entrada;

	private Date saida;

	private Double valor;

	RegistroDTO() {
	}

	public RegistroDTO(Registro model) {
		this.id = model.getId();
		this.veiculo = model.getVeiculo();
		this.entrada = model.getSaida();
		this.valor = model.getValor();

		if (model.getSaida() != null) {
			this.setSaida(model.getSaida());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Veiculo getVeiculo() {
		return veiculo;
	}

	public void setVeiculo(Veiculo veiculo) {
		this.veiculo = veiculo;
	}

	public Date getEntrada() {
		return entrada;
	}

	public void setEntrada(Date entrada) {
		this.entrada = entrada;
	}

	public Date getSaida() {
		return saida;
	}

	public void setSaida(Date saida) {
		this.saida = saida;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

}
