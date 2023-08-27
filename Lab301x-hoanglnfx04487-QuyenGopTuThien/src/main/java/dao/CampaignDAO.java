package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.CampaignModel;
import service.DBService;

public class CampaignDAO {

	/**
	 * Method to add a new campaign to DB
	 * 
	 * @param campaign campaign muon them vao DB
	 * @throws Exception
	 */
	public boolean insertCampaign(CampaignModel campaign) throws SQLException {
		String sql = "INSERT INTO Campaign (owner_cam, email_cam, phone_cam, bank_name_cam, bank_account_cam, "
				+ "title_cam, status_cam, description_cam, target_amount_cam, current_amount_cam, start_cam, end_cam) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, campaign.getOwner());
			statement.setString(2, campaign.getEmail());
			statement.setLong(3, campaign.getPhone());
			statement.setString(4, campaign.getBankName());
			statement.setLong(5, campaign.getBankAccount());
			statement.setString(6, campaign.getTitle());
			statement.setString(7, campaign.getStatus());
			statement.setString(8, campaign.getDescription());
			statement.setDouble(9, campaign.getTargetAmount());
			statement.setDouble(10, campaign.getCurrentAmount());
			statement.setString(11, campaign.getStartDate());
			statement.setString(12, campaign.getEndDate());

			statement.executeUpdate();

			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * Method to retrieve all campaigns from the database
	 * 
	 * @return List<CampaignModel> list of CampaignModel
	 * @throws SQLException
	 */
	public List<CampaignModel> getAllCampaigns() throws SQLException {
		List<CampaignModel> campaigns = new ArrayList<CampaignModel>();
		String sql = "SELECT * FROM Campaign";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				CampaignModel campaign = new CampaignModel();
				campaign.setId(resultSet.getInt("id_cam"));
				campaign.setOwner(resultSet.getString("owner_cam"));
				campaign.setEmail(resultSet.getString("email_cam"));
				campaign.setPhone(resultSet.getLong("phone_cam"));
				campaign.setBankName(resultSet.getString("bank_name_cam"));
				campaign.setBankAccount(resultSet.getLong("bank_account_cam"));
				campaign.setTitle(resultSet.getString("title_cam"));
				campaign.setStatus(resultSet.getString("status_cam"));
				campaign.setDescription(resultSet.getString("description_cam"));
				campaign.setTargetAmount(resultSet.getDouble("target_amount_cam"));
				campaign.setCurrentAmount(resultSet.getDouble("current_amount_cam"));
				campaign.setStartDate(resultSet.getString("start_cam"));
				campaign.setEndDate(resultSet.getString("end_cam"));

				campaigns.add(campaign);
			}

			resultSet.close();
			statement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return campaigns;
	}

	/**
	 * Method to find campaigns by ID, phone number, email, or owner
	 * 
	 * @param searchTerm
	 * @return list of campaigns matched with record in DFB
	 */
	public List<CampaignModel> findCampaigns(String searchTerm) {
		List<CampaignModel> campaigns = new ArrayList<>();

		ResultSet resultSet = null;

		String sql = "select * from Campaign where (id_cam like concat('%', ?, '%')) or (owner_cam like concat('%', ?, '%')) or (email_cam like concat('%', ?, '%')) or (phone_cam like concat('%', ?, '%')) or (title_cam like concat('%', ?, '%'))";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, searchTerm);
			preparedStatement.setString(2, searchTerm);
			preparedStatement.setString(3, searchTerm);
			preparedStatement.setString(4, searchTerm);
			preparedStatement.setString(5, searchTerm);

			resultSet = preparedStatement.executeQuery();

			// Iterate through the result set and create Campaign objects
			while (resultSet.next()) {
				CampaignModel campaign = new CampaignModel();
				campaign.setId(resultSet.getInt("id_cam"));
				campaign.setOwner(resultSet.getString("owner_cam"));
				campaign.setEmail(resultSet.getString("email_cam"));
				campaign.setPhone(resultSet.getLong("phone_cam"));
				campaign.setBankName(resultSet.getString("bank_name_cam"));
				campaign.setBankAccount(resultSet.getLong("bank_account_cam"));
				campaign.setTitle(resultSet.getString("title_cam"));
				campaign.setStatus(resultSet.getString("status_cam"));
				campaign.setDescription(resultSet.getString("description_cam"));
				campaign.setTargetAmount(resultSet.getDouble("target_amount_cam"));
				campaign.setCurrentAmount(resultSet.getDouble("current_amount_cam"));
				campaign.setStartDate(resultSet.getString("start_cam"));
				campaign.setEndDate(resultSet.getString("end_cam"));

				campaigns.add(campaign);
			}

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return campaigns;
	}

	/**
	 * Method to query a campaign by its ID
	 * 
	 * @param campaignId
	 * @return campaign
	 */
	public CampaignModel getCampaignById(int campaignId) {
		CampaignModel campaign = null;
		String sql = "select * from Campaign where id_cam = ?";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, campaignId);

			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				campaign = new CampaignModel();
				campaign.setId(resultSet.getInt("id_cam"));
				campaign.setOwner(resultSet.getString("owner_cam"));
				campaign.setEmail(resultSet.getString("email_cam"));
				campaign.setPhone(resultSet.getLong("phone_cam"));
				campaign.setBankName(resultSet.getString("bank_name_cam"));
				campaign.setBankAccount(resultSet.getLong("bank_account_cam"));
				campaign.setTitle(resultSet.getString("title_cam"));
				campaign.setStatus(resultSet.getString("status_cam"));
				campaign.setDescription(resultSet.getString("description_cam"));
				campaign.setTargetAmount(resultSet.getDouble("target_amount_cam"));
				campaign.setCurrentAmount(resultSet.getDouble("current_amount_cam"));
				campaign.setStartDate(resultSet.getString("start_cam"));
				campaign.setEndDate(resultSet.getString("end_cam"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return campaign;
	}

	/**
	 * Method to update a Campaign
	 * 
	 * @param campaign
	 * @param id
	 */
	public void updateCampaign(CampaignModel campaign, int id) {
		String sql = "UPDATE Campaign SET owner_cam = ?, email_cam = ?, phone_cam = ?, bank_name_cam = ?, bank_account_cam = ?, title_cam = ?, status_cam = ?, description_cam = ?, target_amount_cam = ?, current_amount_cam = ?, start_cam = ?, end_cam = ? WHERE id_cam = ?";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, campaign.getOwner());
			preparedStatement.setString(2, campaign.getEmail());
			preparedStatement.setLong(3, campaign.getPhone());
			preparedStatement.setString(4, campaign.getBankName());
			preparedStatement.setLong(5, campaign.getBankAccount());
			preparedStatement.setString(6, campaign.getTitle());
			preparedStatement.setString(7, campaign.getStatus());
			preparedStatement.setString(8, campaign.getDescription());
			preparedStatement.setDouble(9, campaign.getTargetAmount());
			preparedStatement.setDouble(10, campaign.getCurrentAmount());
			preparedStatement.setString(11, campaign.getStartDate());
			preparedStatement.setString(12, campaign.getEndDate());
			preparedStatement.setInt(13, id);

			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Method to query campaigns by status
	 * 
	 * @param status
	 * @return list of campaigns query by status
	 */
	public List<CampaignModel> findCampaignsByStatus(String status) {
		String sql = "SELECT * FROM Campaign WHERE status_cam = ?";

		List<CampaignModel> campaigns = null;

		try {
			Connection connection = new DBService().getConnection();
			campaigns = new ArrayList<>();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, status);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				CampaignModel campaign = new CampaignModel();
				campaign.setId(resultSet.getInt("id_cam"));
				campaign.setOwner(resultSet.getString("owner_cam"));
				campaign.setEmail(resultSet.getString("email_cam"));
				campaign.setPhone(resultSet.getLong("phone_cam"));
				campaign.setBankName(resultSet.getString("bank_name_cam"));
				campaign.setBankAccount(resultSet.getLong("bank_account_cam"));
				campaign.setTitle(resultSet.getString("title_cam"));
				campaign.setStatus(resultSet.getString("status_cam"));
				campaign.setDescription(resultSet.getString("description_cam"));
				campaign.setTargetAmount(resultSet.getDouble("target_amount_cam"));
				campaign.setCurrentAmount(resultSet.getDouble("current_amount_cam"));
				campaign.setStartDate(resultSet.getString("start_cam"));
				campaign.setEndDate(resultSet.getString("end_cam"));

				campaigns.add(campaign);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return campaigns;
	}

	/**
	 * Method to delete a campaign by Id
	 * 
	 * @param id
	 * @return true if the item is deleted
	 */
	public boolean deleteCampaignById(int id) {
		String sql = "DELETE FROM Campaign WHERE id_cam = ?";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, id);

			// Execute the delete query
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	/**
	 * Method to add contribution amount to current amount of a Campaign
	 * 
	 * @param campaignId
	 * @param contributionAmount
	 * @param action
	 */
	public void updateCurrentAmount(int campaignId, double contributionAmount, int action) {
		String sql = "UPDATE Campaign SET current_amount_cam = current_amount_cam + ? WHERE id_cam = ?";
		String sql2 = "UPDATE Campaign SET current_amount_cam = current_amount_cam - ? WHERE id_cam = ?";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			// SQL query to update the current amount of a Campaign
			// action: 0 => -
			// action: 1 => +
			if (action == 1) {
				preparedStatement = connection.prepareStatement(sql);
			} else {
				preparedStatement = connection.prepareStatement(sql2);
			}

			preparedStatement.setDouble(1, contributionAmount);
			preparedStatement.setInt(2, campaignId);

			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
