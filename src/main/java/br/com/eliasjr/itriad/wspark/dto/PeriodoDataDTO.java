package br.com.eliasjr.itriad.wspark.dto;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class PeriodoDataDTO {
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	Date inicio;
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	Date fim;

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getFim() {
		return fim;
	}

	public void setFim(Date fim) {
		this.fim = fim;
	}

}
