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


public class RecetteDaoImpl implements IDao<Recette> {

	@Override
	public void create(Recette entity) throws DAOException {
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
	public Recette read(int id) throws DAOException {
		// TODO Auto-generated method stub
		Recette recette = null;
		try {
			Session session = HibernateConnection.getInstance().getSession();
			recette = session.find(Recette.class, id);
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return recette;
	}

	@Override
	public List<Recette> list() throws DAOException {
		// TODO Auto-generated method stub
		List<Recette> recettes = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();

			
			Query<Recette> query = session.createQuery("From T_Recettes");
			recettes = query.getResultList();
			//HibernateConnection.getInstance().closeSession();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return recettes;
	}

	@Override
	public void update(Recette entity) throws DAOException {
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
			
			Recette recette = read(id);
			if(recette != null) {
				session.delete(recette);
			}
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}

}
