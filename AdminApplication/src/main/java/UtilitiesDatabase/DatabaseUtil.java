package UtilitiesDatabase;
import java.util.ResourceBundle;
import java.util.PropertyResourceBundle;
import java.sql.*;

public class DatabaseUtil {
	private static final String username;
	private static final String password;
	private static final String url;
	
	static {
		ResourceBundle rb = PropertyResourceBundle.getBundle("UtilitiesDatabase.database");
		username = rb.getString("username");
		password = rb.getString("password");
		url = rb.getString("url");
	}
	
	public static Connection getConnection() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		}catch(Exception e) {
			e.printStackTrace();
		}
		return DriverManager.getConnection(url,username,password);
	}
	
	public static void closeConnection(Connection connection) {
        if(connection != null)
            try {
                connection.close();
            }catch(SQLException e){
                e.printStackTrace();
            }
	}
	
	public static void closeStatement(Statement statement) {
        if(statement != null)
            try{
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
    }
	
	public static void closeRs(ResultSet rs) {
        if(rs != null)
            try {
                rs.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
    }
	
	public static void closeAll(ResultSet rs, Statement s, Connection c) {
        closeRs(rs);
        closeStatement(s);
        closeConnection(c);
    }
	
	public static void closeSC(Statement s, Connection c) {
        closeStatement(s);
        closeConnection(c);
    }
	
	public static PreparedStatement prepareStatement(Connection connection, String query, boolean retGenKeys, Object... values) throws SQLException {
        PreparedStatement ps = connection.prepareStatement(query, retGenKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);

        for(int i = 0; i< values.length; i++) {
            ps.setObject(i+1, values[i]);
        }
        return ps;
    }
}
