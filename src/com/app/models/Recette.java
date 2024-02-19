package com.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity(name="T_Recettes")
public class Recette implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Recette() {
		
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@OneToMany(mappedBy = "Recette", cascade= {CascadeType.PERSIST})
	private List<Paiement> Paiements = new ArrayList<>();
	
	
	public Recette(int id, List<Paiement> Paiements) {
		super();
		this.id = id;
		this.Paiements = Paiements;
	}
	
	public Recette(List<Paiement> Paiements) {
		super();
		this.Paiements = Paiements;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Paiement> getPaiements() {
		return Paiements;
	}

	public void setPaiements(List<Paiement> Paiements) {
		this.Paiements = Paiements;
	}
	
	public void addPaiement(Paiement Paiement) {
		Paiement.setRecette(this);
		Paiements.add(Paiement);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		if(!(obj instanceof Recette)) return false;
		
		if(this.id == ((Recette)obj).getId())
			return true;
		
		return false;
	}

}
