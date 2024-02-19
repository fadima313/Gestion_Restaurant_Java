package com.app.models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.persistence.*;

@Entity(name="T_Commandes")
public class Commande implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	
	public Commande() {
	}

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	
	@ManyToMany(cascade = CascadeType.PERSIST)
	@JoinTable(
	    name = "T_Produits_T_Commande",
	    joinColumns = @JoinColumn(name = "commande_id"),
	    inverseJoinColumns = @JoinColumn(name = "produit_id")
	)
	private List<Produit> produits = new ArrayList<>();

	
    private Date DateCommande;
    private double totalPrice;

	@ManyToOne
    @JoinColumn(name = "paiement_id")
    private Paiement paiement = null;
	
	@ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;
	
	
    
	public Paiement getPaiement() {
		return paiement;
	}

	public void setPaiement(Paiement paiement) {
		this.paiement = paiement;
	}

	public Commande( int id, List<Produit> produits, Date hourCommande, double totalPrice) {
		super();
		this.id = id;
		this.produits = produits;
		this.DateCommande = hourCommande;
		this.totalPrice = totalPrice;
	}
	
	public Commande(List<Produit> produits, Date hourCommande, double totalPrice) {
		super();
		this.produits = produits;
		
		this.DateCommande = hourCommande;
		this.totalPrice = totalPrice;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public Date getDateCommande() {
		return DateCommande;
	}

	public void setDateCommande(Date hourCommande) {
		this.DateCommande = hourCommande;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
	
	
	
	
	
	
	public void removeProduit(Produit produit) {
        produits.remove(produit);
    }
	
	 
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		if(!(obj instanceof Commande)) return false;
		
		if(this.id == ((Commande)obj).getId())
			return true;
		
		return false;
	}
    
}
