package Ex11z9;

import java.io.Serializable;

public class Alumne implements Serializable {
	private String dni;
	private String nom;
	private String edat;
	private String curs;
	
	public Alumne(String dni, String nom, String edat, String curs) {
		super();
		this.dni = dni;
		this.nom = nom;
		this.edat = edat;
		this.curs = curs;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getEdat() {
		return edat;
	}

	public void setEdat(String edat) {
		this.edat = edat;
	}

	public String getCurs() {
		return curs;
	}

	public void setCurs(String curs) {
		this.curs = curs;
	}
	
	
}
