package br.com.eliasjr.itriad.wspark.service;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.parking.exception.BOException;

import br.com.eliasjr.itriad.wspark.calculo.CalculoPreco;
import br.com.eliasjr.itriad.wspark.calculo.RegraFDS;
import br.com.eliasjr.itriad.wspark.calculo.RegraSemanaManha;
import br.com.eliasjr.itriad.wspark.calculo.RegraSemanaTarde;
import br.com.eliasjr.itriad.wspark.domain.Registro;
import br.com.eliasjr.itriad.wspark.domain.Veiculo;
import br.com.eliasjr.itriad.wspark.dto.PeriodoDataDTO;
import br.com.eliasjr.itriad.wspark.dto.RelatorioDTO;
import br.com.eliasjr.itriad.wspark.exception.GException;
import br.com.eliasjr.itriad.wspark.repository.RegistroRepository;
import br.com.eliasjr.itriad.wspark.service.validator.EntradaVeiculoAtivoValidator;
import br.com.eliasjr.itriad.wspark.service.validator.EntradaVeiculoHorarioValidator;
import br.com.eliasjr.itriad.wspark.service.validator.RegistroVeiculoValidator;

@Service
public class RegistroService {

	@Autowired
	RegistroRepository registroRepository;

	private Double calculaValorAPagar(Registro registro) {
		CalculoPreco cPreco = new RegraFDS();
		cPreco.link(new RegraSemanaTarde()).link(new RegraSemanaManha());
		return cPreco.executaCalculo(registro.getEntrada(), new Date());
	}

	public List<Veiculo> buscaVeiculosAtivos() {
		return registroRepository.findRegistrosWithActiveVeiculos().stream().map((registro) -> registro.getVeiculo())
				.collect(Collectors.toList());
	}

	public void registrarVeiculo(Veiculo veiculo) {
		Registro registro = new Registro();
		veiculo.setPlaca(veiculo.getPlaca().toUpperCase());
		registro.setVeiculo(veiculo);
		registro.setEntrada(new Date());

		RegistroVeiculoValidator validator = new EntradaVeiculoAtivoValidator(registroRepository);
		validator.link(new EntradaVeiculoHorarioValidator());
		validator.executaValidacao(registro);

		registroRepository.save(registro);
	}

	public Registro registrarSaidaVeiculo(String placa) {
		Registro registro = registroRepository.findRegistroWithActiveVeiculoByPlaca(placa);
		registro.setSaida(new Date());
		registro.setValorPago(calculaValorAPagar(registro));
		return registroRepository.save(registro);
	}

	public Double verificaValorAPagar(String placa) {
		Registro registro = registroRepository.findRegistroWithActiveVeiculoByPlaca(placa);
		
		if(registro == null)
			 throw new GException("Registro saida inexistente.", new Throwable("Registration.notfound"));
		
		return calculaValorAPagar(registro);
	}

	public List<RelatorioDTO> buscaDadosRelatorio(PeriodoDataDTO periodo) {
		final Map<Date, RelatorioDTO> map = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		cal.setTime(periodo.getFim());
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);

		registroRepository.findBySaidaBetween(periodo.getInicio(), cal.getTime()).stream().forEach(registro -> {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(registro.getSaida());
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);

			RelatorioDTO relatorio = map.getOrDefault(calendar.getTime(), new RelatorioDTO(calendar.getTime(), 0L, 0D));
			relatorio.setNumeroCarros(relatorio.getNumeroCarros() + 1);
			relatorio.setValorRecebido(relatorio.getValorRecebido() + registro.getValorPago());
			map.put(calendar.getTime(), relatorio);
		});

		return map.values().stream().sorted((RelatorioDTO a, RelatorioDTO b) -> a.getDia().compareTo(b.getDia()))
				.collect(Collectors.toList());
	}
}
