package com.heliommsfilho.erp.service;

import javax.inject.Inject;

import com.heliommsfilho.erp.model.Empresa;
import com.heliommsfilho.erp.repository.Empresas;
import com.heliommsfilho.erp.util.Transactional;

public class CadastroEmpresaService {

	@Inject
	private Empresas empresas;
	
	@Transactional
	public void salvar(Empresa empresa) {
		empresas.guardar(empresa);
	}
	
	@Transactional
	public void excluir(Empresa empresa) {
		empresas.remover(empresa);
	}
}
