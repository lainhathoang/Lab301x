package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import model.PersonModel;
import service.DBService;
import util.PasswordUtil;

public class PersonDAO {

	/**
	 * Create a new person in database
	 * 
	 * @param p person object create with PersonModel class
	 * @return true if system created the record
	 * @throws Exception
	 */
	public boolean createPerson(PersonModel p) throws Exception {
		String sql = "insert into Person (email_person, phone_person, password_person, role_person, status_person) values(?, ?, ?, ?, ?)";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, p.getEmail());
			preparedStatement.setLong(2, p.getPhone());
			preparedStatement.setString(3, new PasswordUtil().hashPasswordMD5(p.getPassword()));
			preparedStatement.setString(4, p.getRole());
			preparedStatement.setString(5, p.getStatus());

			preparedStatement.executeUpdate();
			// Created
			return true;
		} catch (Exception e) {
			// NOT created
			System.out.println("An error occured: " + e.getMessage());
		}

		return false;
	}

	/**
	 * Check if email is existed in the database
	 * 
	 * @param email user's email get from UI
	 * @return true if the user is existed in the DB
	 * @throws Exception
	 */
	public boolean isPersonExisted(String email) throws Exception {
		String sql = "select * from Person where email_person = ? limit 1";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, email);

			ResultSet rs = preparedStatement.executeQuery();

			// rs.next() => record
			if (rs.next()) {
				return true;
			}
		} catch (Exception e) {
			System.out.println("An error occured: " + e.getMessage());
		}

		return false;
	}

	/**
	 * Method get email & password of a person
	 * 
	 * @param email
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public PersonModel getPerson(String email, String password) throws Exception {
		PersonModel person = new PersonModel();
		String sql = "select * from Person where email_person = ? and password_person = ?";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			ResultSet rs = preparedStatement.executeQuery();

			if (!rs.next()) {
				return null;
			}

			person.setEmail(rs.getString("email_person"));
			person.setPassword(rs.getString("password_person"));
			person.setRole(rs.getString("role_person"));
			person.setStatus(rs.getString("status_person"));

		} catch (Exception e) {
			System.out.println("An error occured: " + e.getMessage());
		}

		return person;
	}

	/**
	 * Method use to change password of a person
	 * 
	 * @param email
	 * @param newPassword
	 * @return
	 */
	public boolean updatePassword(String email, String newPassword) {

		String sql = "update Person set password_person = ? where email_person = ?";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, new PasswordUtil().hashPasswordMD5(newPassword));
			preparedStatement.setString(2, email);

			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			System.out.println("An error occured: " + e.getMessage());
			return false;
		}

		return true;
	}

	/**
	 * Method to update the last time user logged in
	 * 
	 * @param email
	 * @return
	 */
	public boolean updateLastLogin(String email) {
		String sql = "update Person set last_login_person = ? where email_person = ?";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

//			preparedStatement.setString(1, new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			preparedStatement.setString(1, new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
			preparedStatement.setString(2, email);

			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Method to ban user(s)
	 * 
	 * @param idArray
	 * @return
	 */
	public boolean banUsers(int[] idArray) {
		String ids = "";

		try {
			Connection connection = new DBService().getConnection();

			// add id and edit this id array
			for (int id : idArray) {
				ids += id + ", ";
			}
			ids = ids.substring(0, ids.length() - 2);

			String sql = "update Person set status_person = 'Banned' where id_person in (" + ids + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Method to active user(s)
	 * 
	 * @param idArray
	 * @return
	 */
	public boolean activeUser(int[] idArray) {
		String ids = "";

		try {
			Connection connection = new DBService().getConnection();

			// add id and edit this id array
			for (int id : idArray) {
				ids += id + ", ";
			}
			ids = ids.substring(0, ids.length() - 2);

			String sql = "update Person set status_person = 'Active' where id_person in (" + ids + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Method to updapte role of a user
	 * 
	 * @param id
	 * @param role
	 * @return
	 */
	public boolean updateRole(int id, String role) {
		String sql = "";

		if (role.equalsIgnoreCase("Admin")) {
			sql = "update Person set role_person = \"User\" where id_person = ? ";
		} else if (role.equalsIgnoreCase("User")) {
			sql = "update Person set role_person = \"Admin\" where id_person = ? ";
		}

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Method to delete a user
	 * 
	 * @param idArray
	 * @return
	 */
	public boolean deleteUser(int[] idArray) {
		String ids = "";

		try {
			Connection connection = new DBService().getConnection();

			// add id and edit this id array
			for (int id : idArray) {
				ids += id + ", ";
			}
			ids = ids.substring(0, ids.length() - 2);

			String sql = "delete from Person where id_person in (" + ids + ")";

			Statement statement = connection.createStatement();
			statement.executeUpdate(sql);

			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return true;
	}

	/**
	 * Method to query user(s) with a specific number of records
	 * 
	 * @param numOfRecords
	 * @param offset
	 * @param searchKey
	 * @return
	 */
	public List<PersonModel> querySearch(int noOfRecords, int offset, String searchKey) {
		List<PersonModel> list = new ArrayList<PersonModel>();

		String sql = "select (select count(*) from Person where email_person like concat('%', ?, '%')) as row_count, "
				+ "Person.* from Person where email_person like concat('%', ?, '%') or phone_person LIKE CONCAT('%', ?, '%') order by email_person limit ?, ?";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, searchKey);
			preparedStatement.setString(2, searchKey);
			preparedStatement.setString(3, searchKey);
			preparedStatement.setInt(4, noOfRecords);
			preparedStatement.setInt(5, offset);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				this.numOfRecord = rs.getInt(1);

				PersonModel tempPerson = new PersonModel(rs.getInt("id_person"), rs.getString("email_person"),
						rs.getLong("phone_person"), rs.getString("password_person"), rs.getString("role_person"),
						rs.getString("status_person"), rs.getString("last_login_person"));

				list.add(tempPerson);
			}

			rs.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return list;
	}

	/**
	 * 	
	 */
	private int numOfRecord = 0;

	public int getNumOfRecords() {
		return numOfRecord;
	}

}
