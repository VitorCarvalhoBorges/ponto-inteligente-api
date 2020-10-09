package com.carvalho.pontointeligente.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.carvalho.pontointeligente.api.entities.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

	Funcionario findByCpf(String cpf);

	Funcionario findByEmail(String email);

}
