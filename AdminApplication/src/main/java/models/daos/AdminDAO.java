package models.daos;
import java.sql.*;

import UtilitiesDatabase.DatabaseUtil;
import models.dtos.AdminDTO;


public class AdminDAO {
	
	private static final String SELECT_ADMIN = "SELECT * FROM admin WHERE username=? AND password=?";
	
	public static AdminDTO getByUsernameAndPassword(String username, String password) {
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		AdminDTO value = null;
		try {
			connection = DatabaseUtil.getConnection();
			Object[] parameters = {username,password};
			ps = DatabaseUtil.prepareStatement(connection, SELECT_ADMIN, true, parameters);
			rs = ps.executeQuery();
			if(rs.next()) {
				value = new AdminDTO(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
			}
			}catch(Exception e) {
			e.printStackTrace();
			}finally {
				DatabaseUtil.closeAll(rs, ps, connection);
			}
			return value;
		}

}
