package com.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;;

@Entity(name="T_Paiements")
public class Paiement implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
   
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name = "statut")
	private String statut;

	private double Montant_TotalCommande;
	private double montantPayer;
	private double MonnaieRendue;
	private Date Date_De_Paiement;
	

	@OneToMany(mappedBy = "paiement", cascade= {CascadeType.PERSIST})
	private List<Commande> commandes = new ArrayList<>();
	

	@ManyToOne
    @JoinColumn(name = "Recette_id")
	private Recette Recette = null;
	
	public Recette getRecette() {
		return Recette;
	}

	public void setRecette(Recette Recette) {
		this.Recette = Recette;
	}
	public Paiement() {
        
    }

	public Paiement(int id, double Montant_TotalCommande, double montantPayer, double MonnaieRendue, Date Date_De_Paiement, List<Commande> commandes) {
		super();
		this.id = id;
		this.Montant_TotalCommande = Montant_TotalCommande;
		this.montantPayer = montantPayer;
		this.MonnaieRendue = MonnaieRendue;
		this.Date_De_Paiement = Date_De_Paiement;
		this.commandes = commandes;
	}
	
	public Paiement(double montantPayer,double Montant_TotalCommande, double MonnaieRendu, Date Date_De_Paiement, List<Commande> commandes, String statut) {
	    super();
	    this.Montant_TotalCommande = Montant_TotalCommande;
	    this.montantPayer = montantPayer;
	    this.MonnaieRendue = MonnaieRendu;
	    this.Date_De_Paiement = Date_De_Paiement;
	    this.commandes = commandes;
	    this.statut = statut;
	}

	public String getStatut() {
		return statut;
	}

	public void setStatut(String statut) {
		this.statut = statut;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public Date getDate_De_Paiement() {
		return Date_De_Paiement;
	}

	public void setDate_De_Paiement(Date Date_De_Paiement) {
		this.Date_De_Paiement = Date_De_Paiement;
	}

	public List<Commande> getcommandes() {
		return commandes;
	}

	public void setcommandes(List<Commande> commandes) {
		this.commandes = commandes;
	}
	
	public void addcommande(Commande commande) {
		commande.setPaiement(this);
		commandes.add(commande);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		if(!(obj instanceof Paiement)) return false;
		
		if(this.id == ((Paiement)obj).getId())
			return true;
		
		return false;
	}

	public double getMontant_TotalCommande() {
		return Montant_TotalCommande;
	}

	public void setMontant_TotalCommande(double montant_TotalCommande) {
		Montant_TotalCommande = montant_TotalCommande;
	}

	public double getMontantPayer() {
		return montantPayer;
	}

	public void setMontantPayer(double montantPayer) {
		this.montantPayer = montantPayer;
	}

	public double getMonnaieRendue() {
		return MonnaieRendue;
	}

	public void setMonnaieRendue(double monnaieRendue) {
		MonnaieRendue = monnaieRendue;
	}
	

}
