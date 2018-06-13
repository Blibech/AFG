package com.AFG.bean;


import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import com.AFG.service.FournisseurService;
import com.AFG.persistance.Fournisseur;

@FacesConverter("fournisseurConverter")
public class FournisseurConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext arg0, UIComponent arg1, String value) {
		FournisseurService ser = new FournisseurService();
		String[] nomPrenom = value.split(" ");
		Fournisseur fournisseur = ser.RechercheParNomPrenom(nomPrenom[0],nomPrenom[1]);
		return fournisseur;
	}

	@Override
	public String getAsString(FacesContext arg0, UIComponent arg1, Object object) {
		if(object != null)
			return ((Fournisseur) object).getNom()+" "+((Fournisseur) object).getPrenom();
		return null;
	}

}
