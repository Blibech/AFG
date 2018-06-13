package com.AFG.bean;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.AFG.persistance.User;
import com.AFG.service.UserService;

//Generated 26 avr. 2018 14:26:27 by Hibernate Tools 5.1.0.Alpha1

/**
* User generated by hbm2java
*/

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean {
	
	private String newPassword;
	private String newRetPassword;
	private User selectedUser;
	
	private boolean edit;
	private boolean save;
	private String color;
	
	private List<User> users;
	private User userOnline;
	private int reference;
	private String username;
	private String password;
	private String nom;
	private String prenom;
	private String tel;
	private String adress;
	private String email;
	private int nature;
	
	@PostConstruct
	public void inti(){
		getLoad();
	}
	
	public User getSelectedUser() {
		return selectedUser;
	}
	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewRetPassword() {
		return newRetPassword;
	}
	public void setNewRetPassword(String newRetPassword) {
		this.newRetPassword = newRetPassword;
	}
	public String getColor() {return color;}
	public void setColor(String color) {this.color = color;}
	
	public boolean isEdit() {return edit;}
	public void setEdit(boolean edit) {this.edit = edit;}

	public boolean isSave() {return save;}
	public void setSave(boolean save) {this.save = save;}

	public int getReference() {
		return reference;
	}

	public void setReference(int reference) {
		this.reference = reference;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getNature() {
		return nature;
	}

	public void setNature(int nature) {
		this.nature = nature;
	}

	public List<User> getUsers() {
		UserService ser = new UserService();
		users = ser.rechercheTousUser();
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public User getUserOnline() {
		return userOnline;
	}

	public void setUserOnline(User userOnline) {
		this.userOnline = userOnline;
	}
	
	@SuppressWarnings("unused")
	public String getLoad(){
		UserService ser = new UserService();
		save=true;
		edit=false;
		color="grey";
		return "";
	}
	
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	public void rec(){
		UserService ser = new UserService();
		userOnline = ser.RechercheParLogin(userOnline.getUsername());
		this.nom = userOnline.getNom();
    	this.prenom = userOnline.getPrenom();
    	this.adress = userOnline.getAdress();
    	this.email = userOnline.getEmail();
    	this.tel = userOnline.getTel();
    	this.username = userOnline.getUsername();
    	this.password = userOnline.getPassword();
    	this.reference = userOnline.getReference();
	}
	
	public void Rrec(){
		userOnline.setNom(this.nom);
    	userOnline.setPrenom(this.prenom);
    	userOnline.setAdress(this.adress);
    	userOnline.setEmail(this.email);
    	userOnline.setTel(this.tel);
    	userOnline.setUsername(this.username);
    	userOnline.setPassword(this.password);
    	userOnline.setReference(this.reference);
	}
	
	public String getNoSession(){
		if (userOnline != null) return "";
		return "Refresh";
	}
	
	public String getSession(){
		if (userOnline == null) return "";
		return "Refresh";
	}
	
	public String logout(){
		userOnline = null;
		password = null;
		return "logout";
	}
	
	public String annuler(){
		username = null;
		password = null;
		return null;
	}
	
	public String connecter() {
		FacesContext faces = FacesContext.getCurrentInstance() ;
    	if (username.isEmpty() || username == null){
    		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "�chec de l'authentification.", "Veuillez saisir votre nom d'utilisateur !"));
    		return annuler();
    	}
    	if (password.isEmpty() || password == null){
    		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "�chec de l'authentification.", "Veuillez saisir votre mot de passe!")) ;
    		return null;
    	}
    	UserService ser = new UserService();  
    	User user = ser.RechercheParLogin(username);
    	if (user == null){
    		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "�chec de l'authentification.", "Veuillez v�rifier votre nom d'utilisateur et mot de passe"));
    		return annuler();
    	}
    	user = ser.RechercheParLoginMP(username,password);
    	if (user == null){
    		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "�chec de l'authentification.", "Veuillez v�rifier votre mot de passe"));
    		setPassword(null);
    		return null;
    	}
    	userOnline = user;
    	rec();
    	return "accepted";
    }

	public String saveClick(){
		FacesContext faces = FacesContext.getCurrentInstance() ;
		UserService ser = new UserService();
		Rrec();
		if (userOnline.getNom().isEmpty() || userOnline.getNom() == null){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur de saisie.", "Veuillez saisir votre nom !"));
			return null;
		}
		if (userOnline.getPrenom().isEmpty() || userOnline.getPrenom() == null){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur de saisie.", "Veuillez saisir votre pr�nom !"));
			return null;
		}
		if (tel.length()!=8 && tel.length()!=0){
    		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de saisie.", "Veuillez saisir un nombre de t�l�phone correct!"));
    		return null;
    	}
		save=true;
		edit=false;
		color="grey";
		ser.modifierUser(userOnline);
		return ""+userOnline.getReference();
	}
	public void editClick(){
		save=false;
		edit=true;
		color="black";
	}
	public void annulerClick(){
		rec();
		save=true;
		edit=false;
		color="grey";
		newPassword = "";
		newRetPassword = "";
	}
	
	public void validerChangeMotDePasse(){
		FacesContext faces = FacesContext.getCurrentInstance() ;
		if((newPassword.equals(newRetPassword)==false)||(newPassword.isEmpty())){
			newPassword = "";
			newRetPassword = "";
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "�chec de modification.", "Veuillez v�rifier votre nouveau mot de passe"));
		}
		else{
			userOnline.setPassword(newPassword);
			UserService ser = new UserService();
			ser.modifierUser(userOnline);
			newPassword = "";
			newRetPassword = "";
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succ�e de modification.", "Votre mot de passe a �t� modifier"));
		}
	}
		
	public void select(User u) {
		selectedUser = u;

	}
	
	public boolean thisIsIt(User u){
		return (userOnline.equals(u));
	}
	
	public void supprimer(){
		FacesContext faces = FacesContext.getCurrentInstance() ;
		UserService ser = new UserService();
		ser.suprimerUser(selectedUser);
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Succ�e de suppression.", "L'utilisateur \""+selectedUser.getReference()+"\" � �t� supprim� avec succ�e"));
		selectedUser = null;
	}
	
	public void nouveau(){
		selectedUser = new User();
	}
	
	public String saveNouveau(){
		FacesContext faces = FacesContext.getCurrentInstance() ;
		UserService ser = new UserService();
		if ((selectedUser.getUsername().isEmpty() || selectedUser.getUsername() == null)&&(selectedUser.getPassword().isEmpty() || selectedUser.getPassword() == null)){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur de saisie.", "Veuillez saisir votre nom d'utilisateur et mot de passe !"));
			return null;
		}
		if (selectedUser.getUsername().isEmpty() || selectedUser.getUsername() == null){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur de saisie.", "Veuillez saisir le nom d'utilisateur !"));
			return null;
		}
		if (selectedUser.getPassword().isEmpty() || selectedUser.getPassword() == null){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur de saisie.", "Veuillez saisir le mot de passe !"));
			return null;
		}
		if (selectedUser.getNom().isEmpty() || selectedUser.getNom() == null){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur de saisie.", "Veuillez saisir le nom !"));
			return null;
		}
		if (selectedUser.getPrenom().isEmpty() || selectedUser.getPrenom() == null){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur de saisie.", "Veuillez saisir le pr�nom !"));
			return null;
		}
		if (tel.length()!=8 && tel.length()!=0){
    		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de saisie.", "Veuillez saisir un nombre de t�l�phone correct!"));
    		return null;
    	}
		User u = ser.RechercheParLogin(selectedUser.getUsername());
		if(u!=null){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Utilisateur Existant.", "Veuillez saisir un autre non d'utilisateur !"));
			return null;
		}
		ser.ajouterUser(selectedUser);
		u = ser.RechercheParLogin(selectedUser.getUsername());
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Utilisateur Ajout�.", "L'utilisateur a �t� ajout� avec succ�e sous le r�f�rence : "+u.getReference()));
		return "ADDED";
	}
	
	public String edit(){
		FacesContext faces = FacesContext.getCurrentInstance() ;
		UserService ser = new UserService();
		if (selectedUser.getNom().isEmpty() || selectedUser.getNom() == null){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur de saisie.", "Veuillez saisir votre nom !"));
			return null;
		}
		if (selectedUser.getPrenom().isEmpty() || selectedUser.getPrenom() == null){
			faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Erreur de saisie.", "Veuillez saisir votre pr�nom !"));
			return null;
		}
		if (tel.length()!=8 && tel.length()!=0){
    		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Erreur de saisie.", "Veuillez saisir un nombre de t�l�phone correct!"));
    		return null;
    	}
		ser.modifierUser(selectedUser);
		faces.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "utilisateur Modifi�.", "L'utilisateur \""+selectedUser.getReference()+"\"a �t� modifi� avec succ�e !"));
		return "EDITED";
	}
	
}