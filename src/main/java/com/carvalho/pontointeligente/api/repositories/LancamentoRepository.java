package com.carvalho.pontointeligente.api.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.carvalho.pontointeligente.api.entities.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

	Page<Lancamento> findByFuncionarioId(Long id, PageRequest pageRequest);

	@Query(value = "SELECT * FROM lancamento lm where id = ':id';", nativeQuery = true)
	Lancamento findOne(Long id);

}
