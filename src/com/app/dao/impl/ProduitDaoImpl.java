package com.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import com.app.models.*;
import com.app.exception.*;
import com.app.dao.*;
//import dao.IDao;


public class ProduitDaoImpl implements IDao<Produit> {

	@Override
	public void create(Produit entity) throws DAOException {
	    try {
	        Session session = HibernateConnection.getInstance().getSession();
	        
	        Transaction transaction = session.getTransaction(); // Récupérer la transaction en cours
	        if (!transaction.isActive()) {
	            transaction.begin(); // Démarrer la transaction si elle n'est pas active
	        }
	        
	        session.save(entity);
	        
	        transaction.commit();
	    } catch (Exception e) {
	        throw new DAOException("ERROR : " + e.getClass() + " : " + e.getMessage());
	    }
	}


	@Override
	public Produit read(int id) throws DAOException {
		// TODO Auto-generated method stub
		Produit produit = null;
		try {
			Session session = HibernateConnection.getInstance().getSession();
			produit = session.find(Produit.class, id);
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return produit;
	}

	@Override
	public List<Produit> list() throws DAOException {
		// TODO Auto-generated method stub
		List<Produit> produits = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();

			
			Query<Produit> query = session.createQuery("From T_Produits");
			produits = query.getResultList();
			//HibernateConnection.getInstance().closeSession();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return produits;
	}

	@Override
	public void update(Produit entity) throws DAOException {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			session.update(entity);
			transaction.commit();
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

	@Override
	public void delete(int id) throws DAOException {
	    System.out.println("Tentative de suppression du produit avec l'ID : " + id);
	    
	    try {
	        Session session = HibernateConnection.getInstance().getSession();
	        
	        Transaction transaction = session.beginTransaction();
	        
	        Produit produit = read(id);
	        if (produit != null) {
	            System.out.println("Produit trouvé avec l'ID : " + produit.getId() + ". En train de supprimer...");
	            session.delete(produit);
	            System.out.println("Suppression réussie.");
	        } else {
	            System.out.println("Produit non trouvé avec l'ID : " + id);
	        }
	        
	        transaction.commit();
	    } catch (Exception e) {
	        throw new DAOException("ERREUR : " + e.getClass() + " : " + e.getMessage());
	    }
	}

}
