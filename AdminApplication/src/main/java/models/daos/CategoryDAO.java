package models.daos;
import java.util.ArrayList;
import java.util.List;

import UtilitiesDatabase.DatabaseUtil;

import java.sql.*;

import models.dtos.*;

public class CategoryDAO {
	
	private static final String SELECT_ALL = "SELECT * FROM category";
	private static final String INSERT = "INSERT INTO category (name) VALUES (?)";
	private static final String SELECT_BY_NAME = "SELECT * FROM category WHERE name=?";
	private static final String DELETE = "DELETE FROM category WHERE name=?";
	
	public static List<CategoryDTO> selectAll() {
		List<CategoryDTO> categories = new ArrayList<>();
		Connection connection = null;
		Statement s = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			s = connection.createStatement();
			rs = s.executeQuery(SELECT_ALL);
			while(rs.next()) {
				categories.add(new CategoryDTO(rs.getString(1)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeAll(rs, s, connection);
		}
		
		return categories;
	}
	
	public static int insert(CategoryDTO category) {
		int value = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {category.getName()};
			ps = DatabaseUtil.prepareStatement(connection, INSERT, true, values);
			value = ps.executeUpdate();
			
			if (value != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					String name = rs.getString(1);
					category.setName(name);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(rs, ps, connection);
		}
		return value;
	}
	
	public static CategoryDTO getCategoryByName(String name) {
		CategoryDTO category = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {name};
			ps = DatabaseUtil.prepareStatement(connection, SELECT_BY_NAME, false, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				category = new CategoryDTO(rs.getString(1));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeAll(rs, ps, connection);
		}
		return category;
	}
	
	public static int delete(CategoryDTO category) {
		int value = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {category.getName()};
			ps = DatabaseUtil.prepareStatement(connection, DELETE, true, values);
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
