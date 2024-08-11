package models.beans;

import java.io.Serializable;

import models.daos.AdminDAO;
import models.dtos.AdminDTO;

public class AdminBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4814529297815512003L;

	/**
	 * 
	 */
	private boolean isLoggedIn = false;
	
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public AdminBean() {
		
	}
	
	public AdminDTO getByUsernameAndPassword(String username,String password) {
		
		AdminDTO admin = AdminDAO.getByUsernameAndPassword(username, password);
		if(admin != null) {
			this.isLoggedIn = true;
			return admin;
		}
		this.isLoggedIn = false;
		return admin;
	}

}
