package models.beans;

import java.io.Serializable;
import java.util.List;
import models.dtos.*;
import models.daos.UserDAO;

public class UserBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8232432090873834867L;
	
	public UserBean() {
		
	}
	
	public List<UserDTO> getAllUsers(){
		return UserDAO.selectAllUsers();
	}
	public List<SupportDTO> getAllSupports(){
		return UserDAO.selectAllSupports();
	}
	public int addNewSupport(SupportDTO support) {
		return UserDAO.insertSupport(support);
	}
	public int deleteUser(String username) {
		return UserDAO.deleteUser(username);
	}
	public int deleteSupport(String username) {
		return UserDAO.deleteSupport(username);
	}
}
