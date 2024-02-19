package com.app.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.app.enums.Role;

@Entity(name="T_Utilisateurs")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="UTILISATEUR", discriminatorType = DiscriminatorType.STRING, length=16)
public class Utilisateur implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	protected int id;
	
	protected String lastname;
	protected String firstname;
	protected String telephone;
	protected String email;
	protected String login;
	protected String password;
	
	@Enumerated(EnumType.STRING)
	protected Role role;
	
	public Utilisateur() {}
	
	public Utilisateur(int id, String lastname, String firstname, String login, String password) {
		this.id = id;
		this.lastname = lastname;
		this.firstname = firstname;
		this.login = login;
		this.password = password;
	}
	
	public Utilisateur(String lastname, String firstname, String login, String password,Role role) {
		this.lastname = lastname;
		this.firstname = firstname;
		this.login = login;
		this.password = password;
		this.role=role;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public Role getRole() {
		return role;
	}
	
	public void setRole(Role role) {
		this.role = role;
	}
	
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	
	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	

	public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
	
	@Override
    public String toString() {
        return "User { " +
                "id=" + id +
                ", nom=" + lastname +
                ", prenom=" + firstname + 
                ",Email=" + telephone +
                ", telephone"+ telephone +
                ", login=" + login + 
                ", password=" + password +
                ", role=" + role + 
                " }";
    }
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		
		if(!(obj instanceof Utilisateur)) return false;
		
		if(this.id == ((Utilisateur)obj).getId())
			return true;
		
		return false;
	}

	
	
}
