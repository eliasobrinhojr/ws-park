package br.com.eliasjr.itriad.wspark.dto;

import javax.validation.constraints.NotNull;

import br.com.eliasjr.itriad.wspark.domain.Veiculo;

public class VeiculoDTO {
	
	private Long id;

	@NotNull(message = "Modelo não pode estar vazio.")
	private String modelo;

	@NotNull(message = "Cor não pode estar vazio.")
	private String cor;

	@NotNull(message = "Placa não pode estar vazio.")
	private String placa;

	public VeiculoDTO() {
	}

	public VeiculoDTO(Veiculo model) {
		this.id = model.getId();
		this.modelo = model.getModelo();
		this.cor = model.getCor();
		this.placa = model.getPlaca();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}
	
}
