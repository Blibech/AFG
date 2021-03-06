package com.AFG.bean;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.AFG.persistance.Demande;
import com.AFG.persistance.User;
import com.AFG.persistance.Outillage;

//Generated 26 avr. 2018 14:26:27 by Hibernate Tools 5.1.0.Alpha1

/**
* User generated by hbm2java
*/

@ManagedBean(name = "demandeBean")
@SessionScoped
public class DemandeBean {
	
	private Demande selectedDemande;
	private List<Demande> demandes;
	
	private int reference;
	private User demandeur;
	private Outillage outillage;
	private String nature;
	private int qte;
	private int state;
	private String date;
	private String ch; // Cahier de charge
	private String notes;
	
	public Demande getSelectedDemande() {
		return selectedDemande;
	}

	public void setSelectedDemande(Demande selectedDemande) {
		this.selectedDemande = selectedDemande;
	}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public User getDemandeur() {
		return demandeur;
	}

	public void setDemandeur(User demandeur) {
		this.demandeur = demandeur;
	}

	public Outillage getOutillage() {
		return outillage;
	}

	public void setOutillage(Outillage outillage) {
		this.outillage = outillage;
	}

	public String getNature() {
		return nature;
	}

	public void setNature(String nature) {
		this.nature = nature;
	}

	public int getQte() {
		return qte;
	}

	public void setQte(int qte) {
		this.qte = qte;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getCh() {
		return ch;
	}

	public void setCh(String ch) {
		this.ch = ch;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<Demande> getDemandes(){
		return demandes;
	}
	
	public void setDemandes(List<Demande> demandes){
		this.demandes = demandes;
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void select(Demande d){
		selectedDemande = d;
	}
	
}