package com.app.exception;

import org.hibernate.HibernateException;

public class DAOException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DAOException(String message) {
		super(message);
	}

	public DAOException(String string, HibernateException e) {
		// TODO Auto-generated constructor stub
	}

}
