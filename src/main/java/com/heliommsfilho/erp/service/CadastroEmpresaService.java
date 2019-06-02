package com.heliommsfilho.erp.service;

import java.io.Serializable;

import javax.inject.Inject;

import com.heliommsfilho.erp.model.Empresa;
import com.heliommsfilho.erp.repository.Empresas;
import com.heliommsfilho.erp.util.Transactional;

public class CadastroEmpresaService implements Serializable{

	private static final long serialVersionUID = 3099733303918678083L;
	
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
