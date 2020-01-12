package br.com.eliasjr.itriad.wspark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.eliasjr.itriad.wspark.domain.Registro;

public interface RegistroRepository extends JpaRepository<Registro, Long> {

}
