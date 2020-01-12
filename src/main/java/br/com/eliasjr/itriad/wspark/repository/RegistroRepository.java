package br.com.eliasjr.itriad.wspark.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.eliasjr.itriad.wspark.domain.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long> {
	@Query(value = "SELECT r FROM Registro r JOIN r.veiculo v WHERE r.saida = null AND v.placa = :placa")
	public Registro findRegistroWithActiveVeiculoByPlaca(String placa);

	@Query(value = "SELECT r FROM Registro r WHERE r.saida = null")
	public List<Registro> findRegistrosWithActiveVeiculos();

	public List<Registro> findBySaidaBetween(Date inicio, Date fim);
}
