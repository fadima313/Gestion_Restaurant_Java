package com.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import com.app.models.Commande;
import com.app.models.Produit;
import com.app.dao.IDao;
import com.app.exception.DAOException;





public class CommandeDaoImpl implements IDao<Commande> {

	@Override
	public void create(Commande entity) throws DAOException {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			
			session.persist(entity);
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + " : " + e.getMessage());
		}
	}

	@Override
	public Commande read(int id) throws DAOException {
		// TODO Auto-generated method stub
		Commande commande = null;
		try {
			Session session = HibernateConnection.getInstance().getSession();
			commande = session.find(Commande.class, id);
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return commande;
	}

	@Override
	public List<Commande> list() throws DAOException {
		// TODO Auto-generated method stub
		List<Commande> commandes = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();

			
			Query<Commande> query = session.createQuery("From T_Commandes");
			commandes = query.getResultList();
			//HibernateConnection.getInstance().closeSession();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return commandes;
	}

	@Override
	public void update(Commande entity) throws DAOException {
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
		// TODO Auto-generated method stub
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			
			Commande commande = read(id);
			if(commande != null) {
				session.delete(commande);
			}
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}
	 public void removeProduitFromCommande(Commande commande, Produit produit) throws DAOException {
	        try {
	            Session session = HibernateConnection.getInstance().getSession();

	            Transaction transaction = session.beginTransaction();

	            // Assuming you have a method in the Commande class to remove a produit
	            commande.removeProduit(produit);

	            session.update(commande);

	            transaction.commit();
	        } catch (Exception e) {
	            throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
	        }
	    }
	}

