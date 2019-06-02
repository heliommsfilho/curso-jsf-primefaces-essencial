package com.heliommsfilho.erp.controller;

import java.util.List;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import com.heliommsfilho.erp.model.RamoAtividade;

public class RamoAtividadeConverter implements Converter {

	private List<RamoAtividade> listaRamoAtividades;
	
	public RamoAtividadeConverter(List<RamoAtividade> listaRamoAtividades) {
		this.listaRamoAtividades = listaRamoAtividades;
	}

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		
		if (value == null) {
			return null;
		}
		
		return listaRamoAtividades.stream().filter(ramo -> ramo.getId().equals(Long.parseLong(value)))
										   .findFirst().orElse(null);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object ramoAtividade) {

		if (ramoAtividade == null) {
			return null;
		}
		
		return ((RamoAtividade) ramoAtividade).getId().toString();
	}
}
