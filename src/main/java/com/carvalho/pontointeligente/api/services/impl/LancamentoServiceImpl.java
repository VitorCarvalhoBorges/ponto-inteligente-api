package com.carvalho.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.carvalho.pontointeligente.api.entities.Lancamento;
import com.carvalho.pontointeligente.api.repositories.LancamentoRepository;

public class LancamentoServiceImpl {

	private static final Logger log = LoggerFactory.getLogger(LancamentoServiceImpl.class);

	@Autowired
	LancamentoRepository lancamentoRepository;

	public Page<Lancamento> buscarPorFuncionarioId(Long id, PageRequest pageRequest) {

		log.info("Buscando lançamentos para o funcionário de id {}", id);

		return lancamentoRepository.findByFuncionarioId(id, pageRequest);

	}

	public Optional<Lancamento> buscarPorId(Long id) {
		log.info("Buscando lançamento pelo id {}", id);

		return Optional.ofNullable(lancamentoRepository.findOne(id));

	}

	public Lancamento persistir(Lancamento lancamento) {
		log.info("Persistindo lançamento no banco de dados.");

		return lancamentoRepository.save(lancamento);

	}

	public void remover(Long id) {
		log.info("Removendo lançamento de funcionário, id do lançamento : {}", id);

		lancamentoRepository.deleteById(id);

	}
}
