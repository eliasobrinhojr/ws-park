package br.com.eliasjr.itriad.wspark.service.validator;

import br.com.eliasjr.itriad.wspark.domain.Registro;
import br.com.eliasjr.itriad.wspark.repository.RegistroRepository;

public class EntradaVeiculoAtivoValidator extends RegistroVeiculoValidator {

	private RegistroRepository repository;

	public EntradaVeiculoAtivoValidator(RegistroRepository repository) {
		this.repository = repository;
	}

	@Override
	protected void valida(Registro registro) {
		if (repository.findRegistroWithActiveVeiculoByPlaca(registro.getVeiculo().getPlaca()) != null) {
			throw new ValidacaoEntradaRegistroException("Veículo já possui um registro aberto.");
		}
	}

}
