package com.carvalho.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carvalho.pontointeligente.api.entities.Funcionario;
import com.carvalho.pontointeligente.api.repositories.FuncionarioRepository;
import com.carvalho.pontointeligente.api.services.FuncionarioService;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

	private static final Logger log = LoggerFactory.getLogger(FuncionarioServiceImpl.class);

	@Autowired
	FuncionarioRepository funcRep;

	@Override
	public Funcionario persistir(Funcionario funcionario) {

		log.info("Persistindo funcionário {}", funcionario);

		return funcRep.save(funcionario);

	}

	@Override
	public Optional<Funcionario> buscarPorCpf(String cpf) {

		log.info("Realizando busca de funcionário pelo CPF: {}", cpf);

		return Optional.ofNullable(funcRep.findByCpf(cpf));

	}

	@Override
	public Optional<Funcionario> buscarPorEmail(String email) {

		log.info("Realizando busca de funcionário pelo e-mail: {}", email);

		return Optional.ofNullable(funcRep.findByEmail(email));

	}

	@Override
	public Optional<Funcionario> buscarPorId(Long id) {

		log.info("Realizando busca de funcionário pelo id do banco, id: {}", id);

		return funcRep.findById(id);

	}

}
