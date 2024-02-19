package com.app.models;

import java.io.Serializable;
import javax.persistence.Entity;

import com.app.enums.Role;

@Entity
public class Administrateur extends Utilisateur implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public Administrateur() {}

	public Administrateur( String lastname, String firstname, String login, String password,Role role) {
		super( lastname, firstname, login, password,role);
	}
	
	public Administrateur( int id, String lastname, String firstname, String login, String password) {
		super( id, lastname, firstname, login, password);
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		if(!(obj instanceof Administrateur)) return false;
		
		if(this.id == ((Administrateur)obj).getId())
			return true;
		
		return false;
	}

	
}
