package Ex11z9;

import java.io.Serializable;

public class Professor implements Serializable{
  private String dni;
	private String nom;
	private String edat;
	private String materia;
	
	public Professor(String dni, String nom, String edat, String materia) {
		super();
		this.dni = dni;
		this.nom = nom;
		this.edat = edat;
		this.materia = materia;
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

	public String getMateria() {
		return materia;
	}

	public void setMateria(String materia) {
		this.materia = materia;
	}
}
