package com.heliommsfilho.erp.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.heliommsfilho.erp.model.Empresa;
import com.heliommsfilho.erp.model.TipoEmpresa;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Empresa empresa = new Empresa();
	
	public void salvar() {
		System.out.println("Razão social: " + empresa.getRazaoSocial() + 
						   "\nNomeFantasia: " + empresa.getNomeFantasia() +
						   "\nTipo: " + empresa.getTipo());
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
}
