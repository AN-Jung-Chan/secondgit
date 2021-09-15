package spring.friend;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;

public class FriendsDao {
	private JdbcTemplate jdbcTemplate;

	private RowMapper<Friends> memRowMapper = new RowMapper<Friends>() {

		@Override
		public Friends mapRow(ResultSet rs, int rowNum) throws SQLException {
			Friends friends = new Friends(rs.getString("ID"), rs.getString("PASSWORD"), rs.getString("NAME"),
					rs.getString("GENDER"), rs.getString("EMAIL"), rs.getTimestamp("REGDATE"));
			friends.setNum(rs.getInt("NUM"));
			return friends;
		}
	};

	public FriendsDao(DataSource dataSource) {
		System.out.println("FriendsDao(DataSource)");
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	public Friends selectById(String id) { // select
		List<Friends> result = jdbcTemplate
				.query("select \"NUM\", \"ID\", \"PASSWORD\", \"NAME\", \"GENDER\", \"EMAIL\", \"REGDATE\" from "
						+ "\"FRIEND\" where \"ID\"=?", new FriendsRowMapper(), id);
		return result.isEmpty() ? null : result.get(0);
	}

	public List<Friends> selectAll() {
		List<Friends> result = jdbcTemplate
				.query("select \"NUM\", \"ID\", \"PASSWORD\", \"NAME\", \"GENDER\", \"EMAIL\", \"REGDATE\" from "
						+ "\"FRIEND\" ", new FriendsRowMapper());
		return result;
	}

	public int count() {
		String query = "select count(*) from \"FRIEND\"";
		// 쿼리 결과가 하나만 있을 경우 활용(쿼리 결과가 하나가 아니면 예외)
		return jdbcTemplate.queryForObject(query, Integer.class);
	}

	public void update(Friends friends) {
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				String query = "update \"FRIEND\" set \"EMAIL\"=?, \"GENDER\"=?, \"NAME\"= ?, \"PASSWORD\" = ? where \"ID\"= ?";
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, friends.getEmail());
				pstmt.setString(2, friends.getGender());
				pstmt.setString(3, friends.getName());
				pstmt.setString(4, friends.getPassword());
				pstmt.setString(5, friends.getId());
				return pstmt;
			}

		});
	}

	public void insert(Friends friends) { // insert
		String query = "insert into \"FRIEND\" (\"NUM\", \"ID\", \"PASSWORD\", \"NAME\", \"GENDER\", \"EMAIL\", \"REGDATE\")"
				+ "values (\"FRIEND_SEQ\".nextval, ?, ?, ?, ?, ?, sysdate)";
		jdbcTemplate.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(query);
				pstmt.setString(1, friends.getId());
				pstmt.setString(2, friends.getPassword());
				pstmt.setString(3, friends.getName());
				pstmt.setString(4, friends.getGender());
				pstmt.setString(5, friends.getEmail());
				return pstmt;
			}
		});
		friends.setNum(this.lastSequence());
	}

	private int lastSequence() {
		String sql = "select max(\"NUM\") from \"FRIEND\"";
		return jdbcTemplate.queryForObject(sql, int.class);
	}

	public List<Friends> selectByRegDate(Date from, Date to) {
		List<Friends> results = jdbcTemplate.query(
				"select * from FRIEND where REGDATE between ? and ? " + 
				"order by REGDATE desc", memRowMapper, from, to);
		return results;
	}

	public Friends selectByEmail(int num) {
		List<Friends> results = jdbcTemplate.query(
				"select * from \"FRIEND\" where \"NUM\" =?", 
				memRowMapper, num);
		return results.isEmpty() ? null : results.get(0);
	}

}