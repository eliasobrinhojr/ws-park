package br.com.eliasjr.itriad.wspark.service.validator;

import java.util.Calendar;

import br.com.eliasjr.itriad.wspark.domain.Registro;

public class EntradaVeiculoHorarioValidator extends RegistroVeiculoValidator {

	@Override
	protected void valida(Registro registro) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(registro.getEntrada());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour > 18 || hour < 8) {
			throw new ValidacaoEntradaRegistroException("Fora do horário de expediente.");
		}
	}

}
