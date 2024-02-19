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

public class RestaurateurDaoImpl implements IDao<Restaurateur> {

	@Override
	public void create(Restaurateur entity) throws DAOException {
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
	public Restaurateur read(int id) throws DAOException {
		// TODO Auto-generated method stub
		Restaurateur restaurateur = null;
		try {
			Session session = HibernateConnection.getInstance().getSession();
			restaurateur = session.find(Restaurateur.class, id);
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return restaurateur;
	}

	@Override
	public List<Restaurateur> list() throws DAOException {
		// TODO Auto-generated method stub
		List<Restaurateur> restaurateurs = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();

		
			Query<Restaurateur> query = session.createQuery("From T_Restaurateurs");
			restaurateurs = query.getResultList();
			//HibernateConnection.getInstance().closeSession();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return restaurateurs;
	}

	@Override
	public void update(Restaurateur entity) throws DAOException {
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
			
			Restaurateur restaurateur = read(id);
			if(restaurateur != null) {
				session.delete(restaurateur);
			}
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

}
