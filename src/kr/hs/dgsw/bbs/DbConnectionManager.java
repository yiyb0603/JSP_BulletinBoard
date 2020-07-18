package kr.hs.dgsw.bbs;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class DbConnectionManager
{

	public abstract Connection getConnection() 
			throws ClassNotFoundException, SQLException;
	/*
	{
		Class.forName("org.mariadb.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(
				"jdbc:mariadb://10.80.161.224/dgsw", 
				"root", "dgswjava");
		
		return con;
	}*/
	
}
