package com.carvalho.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carvalho.pontointeligente.api.entities.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {

}
