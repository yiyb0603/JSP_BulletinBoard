package kr.hs.dgsw.bbs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface DatabaseStrategy
{
	public PreparedStatement makePreparedStatement(Connection con)
		throws SQLException;
}
