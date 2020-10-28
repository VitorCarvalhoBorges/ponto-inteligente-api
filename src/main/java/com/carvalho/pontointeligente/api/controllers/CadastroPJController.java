package com.carvalho.pontointeligente.api.controllers;

import java.security.NoSuchAlgorithmException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.carvalho.pontointeligente.api.DTO.CadastroPJDto;
import com.carvalho.pontointeligente.api.entities.Empresa;
import com.carvalho.pontointeligente.api.entities.Funcionario;
import com.carvalho.pontointeligente.api.enums.PerfilEnum;
import com.carvalho.pontointeligente.api.response.Response;
import com.carvalho.pontointeligente.api.services.EmpresaService;
import com.carvalho.pontointeligente.api.services.FuncionarioService;
import com.carvalho.pontointeligente.api.utils.PasswordUtils;

@RestController
@RequestMapping("/api/cadastrar-pj")
public class CadastroPJController {

	private static final Logger log = LoggerFactory.getLogger(CadastroPJController.class);

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private EmpresaService empresaService;

	@PostMapping
	public ResponseEntity<Response<CadastroPJDto>> cadastrarPJ(@RequestBody CadastroPJDto cadastroDto,
			BindingResult result) throws NoSuchAlgorithmException {

		log.info("Cadastrando PJ : {}", cadastroDto.toString());

		Response<CadastroPJDto> response = new Response<>();

		validarDadosExistentes(cadastroDto, result);

		Empresa empresa = converterDtoParaEmpresa(cadastroDto);

		Funcionario funcionario = converterDtoParaFuncionario(cadastroDto, result);

		if (result.hasErrors()) {

			log.error("Erro validando dados de cadastro de PJ: {}", result.getAllErrors());
			result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}

		empresaService.persistir(empresa);
		funcionario.setEmpresa(empresa);
		funcionarioService.persistir(funcionario);

		response.setData(this.converterCadastroPJDto(funcionario));
		return ResponseEntity.ok(response);
	}

	public void validarDadosExistentes(CadastroPJDto dto, BindingResult result) {

		empresaService.buscarPorCnpj(dto.getCnpj()).ifPresent(emp -> result.addError(new ObjectError("empresa", "Empresa já existente no sistema.")));

		funcionarioService.buscarPorCpf(dto.getCpf()).ifPresent(emp -> result.addError(new ObjectError("funcionario", "Funcionario já cadastrado!")));

		funcionarioService.buscarPorEmail(dto.getEmail())
				.ifPresent(emp -> result.addError(new ObjectError("funcionario", "email do funcionario já cadastrado")));
	}

	public Empresa converterDtoParaEmpresa(CadastroPJDto dto) {

		Empresa empresa = new Empresa();

		empresa.setCnpj(dto.getCnpj());
		empresa.setRazaoSocial(dto.getRazaoSocial());

		return empresa;

	}

	public Funcionario converterDtoParaFuncionario(CadastroPJDto dto, BindingResult result)
			throws NoSuchAlgorithmException {

		Funcionario funcionario = new Funcionario();

		funcionario.setNome(dto.getNome());
		funcionario.setEmail(dto.getEmail());
		funcionario.setCpf(dto.getCpf());
		funcionario.setPerfil(PerfilEnum.ROLE_ADMIN);
		funcionario.setSenha(PasswordUtils.gerarBCrypt(dto.getSenha()));

		return funcionario;

	}

	private CadastroPJDto converterCadastroPJDto(Funcionario funcionario) {

		CadastroPJDto cadastro = new CadastroPJDto();

		cadastro.setCnpj(funcionario.getEmpresa().getCnpj());
		cadastro.setCpf(funcionario.getCpf());
		cadastro.setEmail(funcionario.getEmail());
		cadastro.setId(funcionario.getId());
		cadastro.setNome(funcionario.getNome());
		cadastro.setRazaoSocial(funcionario.getEmpresa().getRazaoSocial());
		// cadastro.setSenha(funcionario.getSenha());

		return cadastro;
	}

}
