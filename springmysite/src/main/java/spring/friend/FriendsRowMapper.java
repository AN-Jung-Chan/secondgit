package spring.friend;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class FriendsRowMapper implements RowMapper<Friends> {
	
	@Override
	public Friends mapRow(ResultSet rs, int rowNum) throws SQLException{
		Friends friends = new Friends(
					rs.getString("ID"),
					rs.getString("PASSWORD"),
					rs.getString("NAME"),
					rs.getString("GENDER"),
					rs.getString("EMAIL"),
					rs.getTimestamp("REGDATE"));
		friends.setNum(rs.getInt("NUM"));
		return friends;
	}
}
