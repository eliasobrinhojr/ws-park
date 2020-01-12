package br.com.eliasjr.itriad.wspark.service.validator;

import br.com.eliasjr.itriad.wspark.domain.Registro;

public abstract class RegistroVeiculoValidator {
	private RegistroVeiculoValidator proximo;

	public RegistroVeiculoValidator link(RegistroVeiculoValidator proximo) {
		this.proximo = proximo;
		return proximo;
	}

	public void executaValidacao(Registro registro) {
		this.valida(registro);
		validaProximo(registro);
	}

	protected abstract void valida(Registro registro);

	private void validaProximo(Registro registro) {
		if (proximo != null)
			proximo.valida(registro);
	}
}
