package com.app.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaQuery;
import org.hibernate.query.Query;

import com.app.dao.IDao;
import com.app.exception.DAOException;
import com.app.models.Administrateur;
import com.app.models.Utilisateur;


public class UserDaoImpl implements IDao<Utilisateur> {

	@Override
	public void create(Utilisateur entity) throws DAOException {
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
	public Utilisateur read(int id) throws DAOException {
		// TODO Auto-generated method stub
		Utilisateur administrateur = null;
		try {
			Session session = HibernateConnection.getInstance().getSession();
			administrateur = session.find(Utilisateur.class, id);
			
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return administrateur;
	}

	@Override
	public List<Utilisateur> list() throws DAOException {
		// TODO Auto-generated method stub
		List<Utilisateur> administrateurs = new ArrayList<>();
		try {
			Session session = HibernateConnection.getInstance().getSession();
			
			Query<Utilisateur> query = session.createQuery("From T_Utilisateurs");
			administrateurs = query.getResultList();
			//HibernateConnection.getInstance().closeSession();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
		return administrateurs;
	}

	@Override
	public void update(Utilisateur entity) throws DAOException {
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
			
			Utilisateur administrateur = read(id);
			if(administrateur != null) {
				session.delete(administrateur);
			}
			
			transaction.commit();
		} catch (Exception e) {
			throw new DAOException("ERROR : " + e.getClass() + ":" + e.getMessage());
		}
	}
	
	public Administrateur convertToAdministrateur(Utilisateur utilisateur) {
	    if (utilisateur instanceof Administrateur) {
	        return (Administrateur) utilisateur;
	    } else {
	        throw new IllegalArgumentException("L'utilisateur n'est pas un administrateur.");
	    }
	}

	
	public Utilisateur getByLogin(String login) throws DAOException{
        // Implémentez la logique pour récupérer un Administrateur en fonction du login
		Administrateur admin = null;
    	Utilisateur u = null;
    	List<Utilisateur> users;
		try {
			users = list();
			for(Utilisateur uu : users) {
				if(uu.getLogin().equalsIgnoreCase(login)) {
					System.out.println(uu.toString());
					u = uu;
					//admin = convertToAdministrateur(uu);
				}
			}
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	/*
        try (Session session = HibernateConnection.getInstance().getSession()) {
        	
        	
        	String hql = "FROM Utilisateur WHERE login = :login";
            Query<Administrateur> query = session.createQuery(hql, Administrateur.class);
            query.setParameter("login", login);
            admin = query.uniqueResult();
            
            System.out.println(admin);
            return u;
        } catch (HibernateException e) {
            throw new DAOException("Error while fetching user by login: " + login, e);
        }
        */
		return u;
    }


}
