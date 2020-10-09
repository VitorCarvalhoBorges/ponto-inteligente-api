package com.carvalho.pontointeligente.api.services.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.carvalho.pontointeligente.api.entities.Empresa;
import com.carvalho.pontointeligente.api.repositories.EmpresaRepository;
import com.carvalho.pontointeligente.api.services.EmpresaService;

@Service
public class EmpresaServiceImpl implements EmpresaService {

	private static final Logger log = LoggerFactory.getLogger(EmpresaServiceImpl.class);

	@Autowired
	EmpresaRepository empresaRep;

	@Override
	public Optional<Empresa> buscarPorCnpj(String cnpj) {

		log.info("Buscando informações da empresa de CPNJ: {}", cnpj);
		return Optional.ofNullable(empresaRep.findByCnpj(cnpj));

	}

	@Override
	public Empresa persistir(Empresa empresa) {
		log.info("Persistindo empresa {}", empresa);
		return empresaRep.save(empresa);

	}

}
