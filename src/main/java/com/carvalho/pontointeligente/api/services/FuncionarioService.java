package com.carvalho.pontointeligente.api.services;

import java.util.Optional;

import com.carvalho.pontointeligente.api.entities.Funcionario;

public interface FuncionarioService {

	/**
	 * Persiste um funcionário na base de dados
	 *
	 * @param funcionario
	 * @return Funcionario
	 */

	Funcionario persistir(Funcionario funcionario);

	/**
	 * Busca e retorna um funcionário dado um CPF
	 *
	 * @param cpf
	 * @return Optional<Funcionario>
	 */

	Optional<Funcionario> buscarPorCpf(String cpf);

	/**
	 * Busca e retorna um funcionário dado um e-mail
	 *
	 * @param email
	 * @return Optional<Funcionario>
	 */

	Optional<Funcionario> buscarPorEmail(String email);

	/**
	 * Buscar e retorna um funcionário por ID
	 *
	 * @param id
	 * @return Optional<Funcionario>
	 */

	Optional<Funcionario> buscarPorId(Long id);

}
