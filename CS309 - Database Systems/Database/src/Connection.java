import java.sql.*;

public class Connection {
	
	static final String  JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/final?useSSL=false";
	
	static final String USER = "root";
	static final String PASS = "mysql";
	
	public static void main(String[] args)
	{
		java.sql.Connection conn = null;
		Statement stmt = null;
		
		try
		{
			Class.forName(JDBC_DRIVER);
			
		}catch(ClassNotFoundException e)
		{
			System.out.println("Cannot find the driver class!");
			e.printStackTrace();
		}
		
		
		try
		{
			
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			
			String sql;
			sql = "SELECT count(EMPLOYEE_ID) from EMPLOYEE";
			ResultSet rs = stmt.executeQuery(sql);
			
			while (rs.next())
			{
				String amount = rs.getString("count(EMPLOYEE_ID)");
				
				System.out.format("There are %s employees in the database.",amount);
			}
			
			rs.close();
			stmt.close();
			conn.close();
			
		}catch(SQLException se)
		{
			se.printStackTrace();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (stmt != null)
					stmt.close();
			}catch(SQLException se2)
			{	
				se2.printStackTrace();
			}
			
			try
			{
				if (conn != null)
					conn.close();
			}catch(SQLException se)
			{
				se.printStackTrace();
			}
		}
	}

}
