package kr.hs.dgsw.bbs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcContext
{
	private Connection getConnection() 
			throws ClassNotFoundException, SQLException
	{
		Class.forName("org.mariadb.jdbc.Driver");
		
		Connection con = DriverManager.getConnection(
				"jdbc:mariadb://211.53.209.159/dgsw_java",
				"dgsw_student", "dgswjava");
		
		return con;
	}
	
	public void executeUpdate(DatabaseStrategy strategy)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try
		{
			con = getConnection();
			pstmt = strategy.makePreparedStatement(con);
			pstmt.executeUpdate();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally 
		{
			try
			{
				pstmt.close();
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
			
			try
			{
				con.close();
			}
			catch (Exception e2)
			{
				e2.printStackTrace();
			}
		}
	}
	
	public Object executeQuery(DatabaseStrategy strategy, ResultSetExtractor extractor)
	{
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try
		{
			con = getConnection();
			pstmt = strategy.makePreparedStatement(con);
			rs = pstmt.executeQuery();
			
			return extractor.extractData(rs);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			throw new RuntimeException(e);
		}
		finally 
		{
			try
			{
				rs.close();
			}
			catch (Exception e2)
			{
			}
			
			try
			{
				pstmt.close();
			}
			catch (Exception e2)
			{
			}
			
			try
			{
				con.close();
			}
			catch (Exception e2)
			{
			}
		}
	}
}
