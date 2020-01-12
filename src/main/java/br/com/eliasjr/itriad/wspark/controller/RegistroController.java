package br.com.eliasjr.itriad.wspark.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.eliasjr.itriad.wspark.domain.Registro;
import br.com.eliasjr.itriad.wspark.domain.Veiculo;
import br.com.eliasjr.itriad.wspark.dto.PeriodoDataDTO;
import br.com.eliasjr.itriad.wspark.dto.RelatorioDTO;
import br.com.eliasjr.itriad.wspark.service.RegistroService;

@RestController
@RequestMapping("registros")
@CrossOrigin(origins = "*")
public class RegistroController {
	
	@Autowired
	RegistroService service;

	@GetMapping
	public List<Veiculo> buscaVeiculosAtivos() {
		return service.buscaVeiculosAtivos();
	}

	@PostMapping
	public void registrarVeiculo(@RequestBody Veiculo veiculo) {
		service.registrarVeiculo(veiculo);
	}

	@GetMapping("saida")
	public Double calculaPrecoAPagar(@RequestParam("placa") String placa) {
		return service.verificaValorAPagar(placa);
	}

	@PostMapping("saida")
	public Registro registrarSaidaVeiculo(@RequestParam("placa") String placa) {
		return service.registrarSaidaVeiculo(placa);
	}

	@GetMapping("relatorio")
	public List<RelatorioDTO> buscaRelatorio(PeriodoDataDTO periodo) {
		return service.buscaDadosRelatorio(periodo);
	}
}
