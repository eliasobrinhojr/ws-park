package br.com.eliasjr.itriad.wspark.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.eliasjr.itriad.wspark.dto.VeiculoDTO;
import br.com.eliasjr.itriad.wspark.service.VeiculoService;

@RestController
@RequestMapping("/veiculo")
public class VeiculoController {

	@Autowired
	private VeiculoService service;

	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public ResponseEntity<VeiculoDTO> findOne(@PathVariable("id") Long id) {
		VeiculoDTO response = this.service.findOne(id);

		return ResponseEntity.ok(response);
	}

	@RequestMapping(method = RequestMethod.GET, path = "/")
	public ResponseEntity<Collection<VeiculoDTO>> findAll() {
		Collection<VeiculoDTO> response = this.service.findAll();

		return ResponseEntity.ok(response);
	}

	@RequestMapping(method = RequestMethod.POST, path = "/")
	public ResponseEntity<?> save(@Valid @RequestBody VeiculoDTO veiculoDTO, BindingResult result) {

		return getResponseEntity(veiculoDTO, result);
	}

	@RequestMapping(method = RequestMethod.PUT, path = "/")
	public ResponseEntity<?> update(@Valid @RequestBody VeiculoDTO veiculoDTO, BindingResult result) {

		return getResponseEntity(veiculoDTO, result);
	}

	private ResponseEntity<?> getResponseEntity(@RequestBody @Valid VeiculoDTO veiculoDTO, BindingResult result) {
		if (result.hasErrors()) {
			List<String> errors = new ArrayList<>();
			result.getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(errors);
		}

		VeiculoDTO response = this.service.save(veiculoDTO);

		return ResponseEntity.ok(response);
	}

	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") Long id) {
		this.service.delete(id);
		return ResponseEntity.ok(null);
	}
}
