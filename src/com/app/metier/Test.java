package com.app.metier;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.app.dao.impl.HibernateConnection;
import com.app.exception.DAOException;
import com.app.models.Commande;

public class Test {
	
	
	public static void main(String[] args) throws DAOException {
		
		generateSampleData();
	}
	
	 public static void generateSampleData() throws DAOException {
	        try {
	            Session session = HibernateConnection.getInstance().getSession();

	            Transaction transaction = session.beginTransaction();

	            for (int i = 0; i < 10; i++) {
	                Commande commande = new Commande();
	                commande.setDateCommande(new Date()); 
	            
	                session.persist(commande);
	            }

	            transaction.commit();
	        } catch (Exception e) {
	            throw new DAOException("ERROR : " + e.getClass() + " : " + e.getMessage());
	        }
	    }

}
