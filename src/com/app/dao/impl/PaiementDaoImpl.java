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



public class PaiementDaoImpl implements IDao<Paiement> {

	@Override
	public void create(Paiement entity) throws DAOException {
		// TODO Auto-generated method stub
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Transaction transaction	= session.beginTransaction();
			
			entity.setStatut("Effectué");
			
			session.persist(entity);
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + " : " + e.getMessage());
		}
	}

	@Override
	public Paiement read(int id) throws DAOException {
		// TODO Auto-generated method stub
		Paiement paiement = null;
		try {
			Session session = HibernateConnection.getInstance().getSession();
			paiement = session.find(Paiement.class, id);
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return paiement;
	}

	@Override
	public List<Paiement> list() throws DAOException {
		// TODO Auto-generated method stub
		List<Paiement> paiements = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();

			
			Query<Paiement> query = session.createQuery("From T_Paiements");
			paiements = query.getResultList();
			//HibernateConnection.getInstance().closeSession();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return paiements;
	}

	@Override
	public void update(Paiement entity) throws DAOException {
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
			
			Paiement paiement = read(id);
			if(paiement != null) {
				session.delete(paiement);
			}
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

}
