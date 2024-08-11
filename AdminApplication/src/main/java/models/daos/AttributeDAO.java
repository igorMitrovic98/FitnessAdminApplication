package models.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import UtilitiesDatabase.DatabaseUtil;
import models.dtos.AttributeDTO;


public class AttributeDAO {
	
	private static final String SELECT_ALL = "SELECT * FROM attribute WHERE category_name=?";
	private static final String INSERT = "INSERT INTO attribute (name,category_name) VALUES(?,?)";
	private static final String SELECT_BY_NAME = "SELECT * FROM attribute WHERE name=?";
	private static final String DELETE = "DELETE FROM attribute WHERE name=?";
	
	public static List<AttributeDTO> selectAll(String categoryName) {
		List<AttributeDTO> attributes = new ArrayList<>();
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {categoryName};
			ps = DatabaseUtil.prepareStatement(connection, SELECT_ALL, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				attributes.add(new AttributeDTO(rs.getString(1), rs.getString(2)));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeAll(rs, ps, connection);
		}
		
		return attributes;
	}
	
	public static int insert(AttributeDTO attribute) {
		int value = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {attribute.getName(),attribute.getCategoryName()};
			ps = DatabaseUtil.prepareStatement(connection, INSERT, true, values);
			value = ps.executeUpdate();
			
			if (value != 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next()) {
					String name = rs.getString(1);
					attribute.setName(name);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DatabaseUtil.closeAll(rs, ps, connection);
		}
		return value;
	}
	
	public static AttributeDTO getAttributeByName(String name) {
		AttributeDTO attribute = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {name};
			ps = DatabaseUtil.prepareStatement(connection, SELECT_BY_NAME, false, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				attribute = new AttributeDTO(rs.getString(1),rs.getString(2));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			DatabaseUtil.closeAll(rs, ps, connection);
		}
		return attribute;
	}
	
	public static int delete(AttributeDTO attribute) {
		int value = 0;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] values = {attribute.getName()};
			System.out.println(attribute.getName());
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
