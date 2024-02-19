package com.app.metier;

import java.util.List;

import com.app.dao.impl.UserDaoImpl;
import com.app.enums.Role;
import com.app.exception.DAOException;
import com.app.models.Administrateur;
import com.app.models.Utilisateur;

public class UserMetier {
	
	private UserDaoImpl userDao;

	public UserMetier(){
	   userDao = new UserDaoImpl();
	}
	
	public List<Utilisateur> listerUtilisateur() throws DAOException {
		return userDao.list();
	}
	
	public void creerUtilisation(Utilisateur user) throws DAOException {
		userDao.create(user);
	}
	
	public void creerAdministrateur(Administrateur admin) throws DAOException{
		userDao.create(admin);
	}
	public void modifierUtilisateur(Utilisateur user) throws DAOException {
		userDao.update(user);
	}
	
	public void initAdmin() throws DAOException {
		if (this.listerUtilisateur().isEmpty()) {
			userDao.create(new Utilisateur("fadi","fadi", "fadi", "fadi",Role.Admin));
		}
	}
	
	public Utilisateur authenticate(String login, String password) {
	    try {
	        Utilisateur user = userDao.getByLogin(login);
	        if (user != null && user.getPassword().equals(password)) {
	            return user;
	        }
	    } catch (DAOException e) {
	        e.printStackTrace();
	    }
	    return null;
	}


}
	