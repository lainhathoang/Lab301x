package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.CampaignModel;
import model.ContributionRecordModel;
import service.DBService;

public class ContributionRecordDAO {

	/**
	 * Method to store contribution record in the database
	 * 
	 * @param contribution record will be added to the DB
	 */
	public void addContribution(ContributionRecordModel contribution) {		
		String sql = "INSERT INTO ContributionRecord (campaign_id, email, donation_time, contribution_amount, contribution_method, verify) VALUES (?, ?, ?, ?, ?, ?)";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, contribution.getCampaignId());
			preparedStatement.setString(2, contribution.getEmail());
			preparedStatement.setString(3, contribution.getDonationTime());
			preparedStatement.setDouble(4, contribution.getContributionAmount());
			preparedStatement.setString(5, contribution.getContributionMethod());
			preparedStatement.setInt(6, contribution.getVerify());

			preparedStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Method to get a contribution records by ID
	 * 
	 * @param contributionId
	 * @return
	 */
	public ContributionRecordModel getContributionById(int contributionId) {
		String sql = "SELECT * FROM ContributionRecord WHERE contribution_id = ?";

		ContributionRecordModel contribution = new ContributionRecordModel();

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, contributionId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				contribution.setContributionId(resultSet.getInt("contribution_id"));
				contribution.setCampaignId(resultSet.getInt("campaign_id"));
				contribution.setEmail(resultSet.getString("email"));
				contribution.setDonationTime(resultSet.getString("donation_time"));
				contribution.setContributionAmount(resultSet.getDouble("contribution_amount"));
				contribution.setContributionMethod(resultSet.getString("contribution_method"));
				contribution.setVerify(resultSet.getInt("verify"));

			}
			
			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contribution;
	}
 
	/**
	 * Method to get all contribution records by ID
	 * 
	 * @param campaignId
	 * @return
	 */
	public List<ContributionRecordModel> getAllContributionsByCampaignId(int campaignId) {
		String sql = "SELECT * FROM ContributionRecord WHERE campaign_id = ?";

		List<ContributionRecordModel> contributions = new ArrayList<ContributionRecordModel>();

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, campaignId);

			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				ContributionRecordModel contribution = new ContributionRecordModel();
				contribution.setContributionId(resultSet.getInt("contribution_id"));
				contribution.setCampaignId(resultSet.getInt("campaign_id"));
				contribution.setEmail(resultSet.getString("email"));
				contribution.setDonationTime(resultSet.getString("donation_time"));
				contribution.setContributionAmount(resultSet.getDouble("contribution_amount"));
				contribution.setContributionMethod(resultSet.getString("contribution_method"));
				contribution.setVerify(resultSet.getInt("verify"));

				contributions.add(contribution);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return contributions;
	}

	/**
	 * Method to store contribution record in the database
	 * 
	 * @param campaignId
	 * @return
	 */
	public String getCampaignNameByContributionId(int campaignId) {
		String sql = "SELECT campaign.title_cam FROM campaign INNER JOIN contributionrecord ON campaign.id_cam = contributionrecord.contribution_id WHERE campaign.id_cam = ?";
		String campaignTitle = "";

		try {
			Connection connection = new DBService().getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setInt(1, campaignId);
			
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                campaignTitle = resultSet.getString("title_cam");
            }

			resultSet.close();
			preparedStatement.close();
			connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return campaignTitle;
	}
	 
	/**
	 * Method to set verify status for a contribution record by contributionId
	 * 
	 * @param contributionId
	 * @param verify 1 if this record is verified
	 */
    public void setContributionVerifyStatus(int contributionId, int verify) {
        String sql = "UPDATE ContributionRecord SET verify = ? WHERE contribution_id = ?";

        try {
            Connection connection = new DBService().getConnection();
            PreparedStatement  preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setInt(1, verify);
            preparedStatement.setInt(2, contributionId);

            preparedStatement.executeUpdate();

            preparedStatement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public List<ContributionRecordModel> getRecordsByEmail(String email) {
    	List<ContributionRecordModel> records = new ArrayList<ContributionRecordModel>();
    	
    	String sql = "SELECT * FROM ContributionRecord WHERE email = ?";
    	
    	try {
    		Connection connection = new DBService().getConnection();
    		PreparedStatement  preparedStatement = connection.prepareStatement(sql);
    		
    		preparedStatement.setString(1, email);
    		
    		ResultSet resultSet = preparedStatement.executeQuery();
    		while (resultSet.next()) {
				ContributionRecordModel record = new ContributionRecordModel();
				record.setContributionId(resultSet.getInt("contribution_id"));
				record.setCampaignId(resultSet.getInt("campaign_id"));
				record.setEmail(resultSet.getString("email"));
				record.setDonationTime(resultSet.getString("donation_time"));
				record.setContributionAmount(resultSet.getDouble("contribution_amount"));
				record.setContributionMethod(resultSet.getString("contribution_method"));
				record.setVerify(resultSet.getInt("verify"));

				records.add(record);
			}
    		
    		preparedStatement.close();
    		connection.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return records;
    }
}
