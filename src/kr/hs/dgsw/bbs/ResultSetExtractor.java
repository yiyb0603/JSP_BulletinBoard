package kr.hs.dgsw.bbs;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface ResultSetExtractor
{
	public Object extractData(ResultSet rs) throws SQLException;
}
