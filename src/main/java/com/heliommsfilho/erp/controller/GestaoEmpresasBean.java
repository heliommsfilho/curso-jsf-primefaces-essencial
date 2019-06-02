package com.heliommsfilho.erp.controller;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.convert.Converter;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.context.RequestContext;

import com.heliommsfilho.erp.model.Empresa;
import com.heliommsfilho.erp.model.RamoAtividade;
import com.heliommsfilho.erp.model.TipoEmpresa;
import com.heliommsfilho.erp.repository.Empresas;
import com.heliommsfilho.erp.repository.RamoAtividades;
import com.heliommsfilho.erp.service.CadastroEmpresaService;
import com.heliommsfilho.erp.util.FacesMessages;

@Named
@ViewScoped
public class GestaoEmpresasBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	private Empresas empresas;
	
	@Inject
	private RamoAtividades ramoAtividades;
	
	@Inject
	private CadastroEmpresaService cadastroEmpresaService;
	
	@Inject
	private FacesMessages messages;
	
	private Converter ramoAtividadeConverter;
	
	private Empresa empresa;
	
	private String termoPesquisa;
	
	private List<Empresa> listaEmpresas;
	
	public void prepararNovaEmpresa() {
		empresa = new Empresa();
	}
	
	public void salvar() {
		cadastroEmpresaService.salvar(empresa);
		
		if (hasPesquisa()) {
			pesquisar();
		} else {
			todasEmpresas();
		}
		
		messages.info("Empresa salva com sucesso");
		
		RequestContext.getCurrentInstance().update(Arrays.asList("frm:empresasDataTable", "frm:messages"));
	}
	
	private boolean hasPesquisa() {
		return termoPesquisa != null && !"".equals(termoPesquisa);
	}
	
	public void pesquisar() {
		listaEmpresas = empresas.pesquisar(termoPesquisa);
		
		if (listaEmpresas.isEmpty()) {
			messages.info("Nada encontrado para a consulta realizada...");
		}
	}
	
	public void todasEmpresas() {
		listaEmpresas = empresas.todas();
	}
	
	public List<RamoAtividade> completarRamoAtividade(String termo) {
		List<RamoAtividade> listaRamoAtividades = ramoAtividades.pesquisar(termo);
		ramoAtividadeConverter = new RamoAtividadeConverter(listaRamoAtividades);
		
		return listaRamoAtividades;
	}
	
	public List<Empresa> getListaEmpresas() {
		return listaEmpresas;
	}

	public String getTermoPesquisa() {
		return termoPesquisa;
	}

	public void setTermoPesquisa(String termoPesquisa) {
		this.termoPesquisa = termoPesquisa;
	}
	
	public TipoEmpresa[] getTiposEmpresa() {
		return TipoEmpresa.values();
	}
	
	public Converter getRamoAtividadeConverter() {
		return ramoAtividadeConverter;
	}
	
	public Empresa getEmpresa() {
		return empresa;
	}
}
