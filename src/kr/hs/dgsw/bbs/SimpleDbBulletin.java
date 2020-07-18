package kr.hs.dgsw.bbs;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SimpleDbBulletin implements Bulletin
{
	private final JdbcContext jdbcContext = new JdbcContext();
	
	@Override
	public void write(Writing writing)
	{
		writing.setSequence(readNewId());
		
		this.jdbcContext.executeUpdate(new DatabaseStrategy()
		{
			
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO bulletin ");
				sql.append("  (id, title, content, writer) ");
				sql.append("VALUES ");
				sql.append("  (?, ?, ?, ?) ");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, writing.getSequence());
				pstmt.setString(2, writing.getTitle());
				pstmt.setString(3, writing.getContent());
				pstmt.setString(4, writing.getWriter());
								
				return pstmt;
			}
		});
	}
	
	@Override
	public void update(Writing writing)
	{
		this.jdbcContext.executeUpdate(new DatabaseStrategy()
		{
			
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE bulletin SET ");
				sql.append("  title = ?, ");
				sql.append("  content = ? ");
				sql.append(" WHERE id = ? ");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setString(1, writing.getTitle());
				pstmt.setString(2, writing.getContent());
				pstmt.setInt(3, writing.getSequence());

				return pstmt;
			}
		});
	}
	
	@Override
	public Writing read(int sequence)
	{
		return (Writing) this.jdbcContext.executeQuery(new DatabaseStrategy()
		{
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT  ");
				sql.append("    id, ");
				sql.append("    title, ");
				sql.append("    content, ");
				sql.append("	hit, ");
				sql.append("    writer ");
				sql.append("  FROM bulletin ");
				sql.append(" WHERE id = ? ");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, sequence);
//				increaseCount(sequence);
				return pstmt;
			}
		}, new ResultSetExtractor()
		{
			
			@Override
			public Object extractData(ResultSet rs) throws SQLException
			{
				Writing writing = null;

				if (rs.next())
				{
					writing = new Writing();
					writing.setSequence(rs.getInt("id"));
					writing.setTitle(rs.getString("title"));
					writing.setContent(rs.getString("content"));
					writing.setWriter(rs.getString("writer"));
					writing.setHit(rs.getInt("hit"));
				}

				return writing;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Writing> list()
	{
		return (List<Writing>) this.jdbcContext.executeQuery(new DatabaseStrategy()
		{
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT  ");
				sql.append("    id, ");
				sql.append("    title, ");
				sql.append("    content, ");
				sql.append("    writer, ");
				sql.append("    hit ");
				sql.append("  FROM bulletin ");
				sql.append(" ORDER BY id DESC ");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				
				return pstmt;
			}
		}, new ResultSetExtractor()
		{
			
			@Override
			public Object extractData(ResultSet rs) throws SQLException
			{
				List<Writing> writingsList = new ArrayList<>();
				Writing writing = null;

				while (rs.next())
				{
					writing = new Writing();
					writing.setSequence(rs.getInt("id"));
					writing.setTitle(rs.getString("title"));
					writing.setContent(rs.getString("content"));
					writing.setWriter(rs.getString("writer"));
					writing.setHit(rs.getInt("hit"));

					writingsList.add(writing);
				}
				
				return writingsList;
			}
		});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Writing> readHit(int sequence) {
		return (List<Writing>) this.jdbcContext.executeQuery(new DatabaseStrategy()
		{
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT  ");
				sql.append("    writer_id, ");
				sql.append("    hit_time  ");
				sql.append("  	FROM dgsw_java.bulletin_good ");
				sql.append(" 	WHERE bulletin_id = ? ");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, sequence);
				return pstmt;
			}
		}, new ResultSetExtractor()
		{

			@Override
			public Object extractData(ResultSet rs) throws SQLException
			{
				List<Writing> writingsList = new ArrayList<>();
				Writing writing = null;

				while (rs.next())
				{
					writing = new Writing();
					writing.setHitTime(rs.getTimestamp("hit_time"));
					writing.setWriter(rs.getString("writer_id"));
					writingsList.add(writing);
				}

				return writingsList;
			}
		});
	}

	@Override
	public void delete(int sequence) {
		this.jdbcContext.executeUpdate(new DatabaseStrategy()
		{
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("	DELETE FROM  ");
				sql.append("	bulletin where	");
				sql.append("	id = ?	");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, sequence);

				return pstmt;
			}
		});
	}

	@Override
	public void deleteHit(int sequence) {
		this.jdbcContext.executeUpdate(new DatabaseStrategy()
		{
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("	DELETE FROM  ");
				sql.append("	bulletin_good where	");
				sql.append("	bulletin_id = ?	");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, sequence);

				return pstmt;
			}
		});
	}

	@Override
	public void increaseHit(Writing writing) {
		this.jdbcContext.executeUpdate(new DatabaseStrategy()
		{
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO bulletin_good ");
				sql.append("  (bulletin_id, writer_id, hit_time) ");
				sql.append("VALUES ");
				sql.append("  (?, ?, ?) ");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, writing.getSequence());
				pstmt.setString(2, writing.getWriter());
				pstmt.setTimestamp(3, writing.getHitTime());

				return pstmt;
			}
		});
	}

	@Override
	public void increaseListHitCount(int sequence) {
		this.jdbcContext.executeUpdate(new DatabaseStrategy()
		{
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE bulletin ");
				sql.append("  SET  ");
				sql.append("  hit = hit + 1 ");
				sql.append("  WHERE id = ? ");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				pstmt.setInt(1, sequence);

				return pstmt;
			}
		});
	}

	public int readNewId()
	{
		return (int) this.jdbcContext.executeQuery(new DatabaseStrategy()
		{
			@Override
			public PreparedStatement makePreparedStatement(Connection con) throws SQLException
			{
				StringBuilder sql = new StringBuilder();
				sql.append("SELECT  ");
				sql.append("    MAX(id) AS newId ");
				sql.append("  FROM bulletin ");

				PreparedStatement pstmt = con.prepareStatement(sql.toString());
				
				return pstmt;
			}
		}, new ResultSetExtractor()
		{
			
			@Override
			public Object extractData(ResultSet rs) throws SQLException
			{
				Integer maxId = 0;

				if (rs.next())
				{
					maxId = rs.getInt(1);
				}
				
				return maxId + 1;
			}
		});
	}
}
