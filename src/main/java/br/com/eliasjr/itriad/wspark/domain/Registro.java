package br.com.eliasjr.itriad.wspark.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.eliasjr.itriad.wspark.dto.RegistroDTO;

@Entity
@Table(name = "registro")
public class Registro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, optional = false)
	private Veiculo veiculo;

	@Column(name = "entrada", nullable = false)
	private Date entrada;

	@Column(name = "saida")
	private Date saida;

	@Column(name = "valor")
	private Double valor;

	@Column(name = "dentro")
	private Boolean dentro = true;

	Registro() {
	}

	public Registro(RegistroDTO dto) {
		if (dto.getId() != null) {
			setId(dto.getId());
		}

		this.valor = dto.getValor();
		this.veiculo = dto.getVeiculo();
		this.entrada = dto.getEntrada();

		if (dto.getSaida() != null) {
			this.setSaida(dto.getSaida());
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
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

	public Boolean getDentro() {
		return dentro;
	}

	public void setDentro(Boolean dentro) {
		this.dentro = dentro;
	}

}
