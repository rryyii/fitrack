package fitrack_proj.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ProfileDAO {

	public ProfileDAO(Connection connection) {
		this.connection = connection;
	}

	public int updateUsername(int userid, String username) {
		String query = "UPDATE users_ft SET username=? WHERE user_id=?;";
		try {
			PreparedStatement update = connection.prepareStatement(query);
			update.setString(1, username);
			update.setInt(2, userid);
			return update.executeUpdate();
		} catch (SQLException sqe) {
			System.out.println("Couldn't update user's username: " + sqe);
		}
		return -1;
	}

	public int updateActivity(int userid, String activity) {
		String query = "UPDATE users_ft SET activity=? WHERE user_id=?;";
		try {
			PreparedStatement update = connection.prepareStatement(query);
			update.setString(1, activity);
			update.setInt(2, userid);
			return update.executeUpdate();
		} catch (SQLException sqe) {
			System.out.println("Couldn't update user's activity level: " + sqe);
		}
		return -1;
	}

	public int updateWeight(int userid, int weight) {
		String query = "UPDATE users_ft SET weight=? WHERE user_id=?;";
		try {
			PreparedStatement update = connection.prepareStatement(query);
			update.setInt(1, weight);
			update.setInt(2, userid);
			return update.executeUpdate();
		} catch (SQLException sqe) {
			System.out.println("Couldn't update user's weight: " + sqe);
		}
		return -1;
	}

	private Connection connection;

}
