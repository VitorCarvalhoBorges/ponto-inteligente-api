package com.carvalho.pontointeligente.api.services;

import java.util.Optional;

import com.carvalho.pontointeligente.api.entities.Empresa;

public interface EmpresaService {

	/**
	 * Retorna uma empresa dado um cnpj
	 *
	 * @param cnpj
	 * @return Optional<Empresa>
	 */

	Optional<Empresa> buscarPorCnpj(String cnpj);

	/**
	 * Cadastra uma nova empresa na base dados
	 * 
	 * @param empresa
	 * @return Empresa
	 */

	Empresa persistir(Empresa empresa);

}
