package com.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import com.app.models.*;
import com.app.exception.*;
import com.app.dao.*;



public class AdministrateurDaoImpl implements IDao<Administrateur> {

	@Override
	public void create(Administrateur entity) throws DAOException {
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
	public Administrateur read(int id) throws DAOException {
		// TODO Auto-generated method stub
		Administrateur administrateur = null;
		try {
			Session session = HibernateConnection.getInstance().getSession();
			administrateur = session.find(Administrateur.class, id);
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return administrateur;
	}

	@Override
	public List<Administrateur> list() throws DAOException {
		// TODO Auto-generated method stub
		List<Administrateur> administrateurs = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();

			
			Query<Administrateur> query = session.createQuery("From T_Administrateurs");
			administrateurs = query.getResultList();
			//HibernateConnection.getInstance().closeSession();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return administrateurs;
	}

	@Override
	public void update(Administrateur entity) throws DAOException {
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
			
			Administrateur administrateur = read(id);
			if(administrateur != null) {
				session.delete(administrateur);
			}
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

}
