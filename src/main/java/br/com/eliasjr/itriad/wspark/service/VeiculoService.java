package br.com.eliasjr.itriad.wspark.service;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.eliasjr.itriad.wspark.domain.Veiculo;
import br.com.eliasjr.itriad.wspark.dto.VeiculoDTO;
import br.com.eliasjr.itriad.wspark.repository.VeiculoRepository;

@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository repository;


	public VeiculoDTO save(VeiculoDTO dto) {
		Veiculo model = new Veiculo(dto);
		return new VeiculoDTO(this.repository.save(model));
	}

	public VeiculoDTO findOne(Long id) {
		Veiculo model = this.repository.getOne(id);
		return new VeiculoDTO(model);
	}

	public Collection<VeiculoDTO> findAll() {
		Collection<Veiculo> veiculos = this.repository.findAll();
		Collection<VeiculoDTO> veiculoSDTO = new ArrayList<>();

		veiculos.forEach((item) -> {
			veiculoSDTO.add(new VeiculoDTO(item));
		});

		return veiculoSDTO;
	}

	public void delete(Long id) {
		this.repository.deleteById(id);
	}
}
