package com.carvalho.pontointeligente.api.services;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.carvalho.pontointeligente.api.entities.Lancamento;

public interface LancamentoService {

	/**
	 * Retorna um lançamento paginado de um funcionário
	 *
	 * @param funcionarioId
	 * @param pageRequest
	 * @return Page<Lancamento>
	 */

	Page<Lancamento> buscarPorFuncionarioId(Long funcionarioId, PageRequest pageRequest);

	/**
	 * Retorna um lancamento por id
	 *
	 * @param id
	 * @return Optional<Lancamento>
	 */

	Optional<Lancamento> buscarPorId(Long id);

	/**
	 * Persiste um lançamento na base dados
	 *
	 * @param lancamento
	 * @return Lancamento
	 */

	Lancamento persistir(Lancamento lancamento);

	/**
	 * Remove um lançamento da base de dados
	 *
	 * @param id
	 */

	void remover(Long id);
}
