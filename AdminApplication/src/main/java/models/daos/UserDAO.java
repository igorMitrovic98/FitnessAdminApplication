package models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import UtilitiesDatabase.DatabaseUtil;
import models.dtos.SupportDTO;
import models.dtos.UserDTO;

public class UserDAO {
	
	private static final String SELECT_ALL_USER = "SELECT * FROM user";
	private static final String SELECT_ALL_SUPPORT = "SELECT * FROM support";
	private static final String INSERT_SUPPORT = "INSERT INTO support(username,password,firstName,lastName) VALUES (?,?,?,?)";
	private static final String DELETE_SUPPORT = "DELETE FROM support WHERE username=?";
	private static final String DELETE_USER = "DELETE FROM user WHERE username=?";
	
	public static List<UserDTO> selectAllUsers() {
		List<UserDTO> users = new ArrayList<>();
		Connection connection = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			s = connection.createStatement();
			rs = s.executeQuery(SELECT_ALL_USER);
			while(rs.next()) {
				users.add(new UserDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getByte(7)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeAll(rs, s, connection);
		}
		
		return users;
	}
	
	public static List<SupportDTO> selectAllSupports() {
		List<SupportDTO> supports = new ArrayList<>();
		Connection connection = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			s = connection.createStatement();
			rs = s.executeQuery(SELECT_ALL_SUPPORT);
			while(rs.next()) {
				supports.add(new SupportDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeAll(rs, s, connection);
		}
		
		return supports;
	}
	
	public static int insertSupport(SupportDTO support) {
		int value = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {support.getUsername(),support.getPassword(),support.getFirstName(),support.getLastName()};
			ps = DatabaseUtil.prepareStatement(connection, INSERT_SUPPORT, true, values);
			value = ps.executeUpdate();
			if (value != 0) {
				rs = ps.getGeneratedKeys();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(rs, ps, connection);
		}
		return value;
	}
	
	
	public static int deleteUser(String username) {
		int value = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {username};
			ps = DatabaseUtil.prepareStatement(connection, DELETE_USER, true, values);
			value = ps.executeUpdate();
			if (value != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					value = 1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeAll(rs, ps, connection);
		}
		return value;
	}
	
	public static int deleteSupport(String username) {
		int value = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {username};
			ps = DatabaseUtil.prepareStatement(connection, DELETE_SUPPORT, true, values);
			value = ps.executeUpdate();
			if (value != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					value = 1;
			}
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeAll(rs, ps, connection);
		}
		return value;
	}
	
}
