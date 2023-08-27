package service;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Random;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import dao.CampaignDAO;
import dao.ContributionRecordDAO;
import dao.PersonDAO;
import model.CampaignModel;
import model.PersonModel;
import util.GetOTPUtil;

public class Test {
	public static void main(String[] args) throws SQLException {
		PersonDAO dao = new PersonDAO();
		
		CampaignDAO camDAO = new CampaignDAO();
		ContributionRecordDAO conDAO = new ContributionRecordDAO();
//		System.out.println(dao.updatePassword("nhathoang8c01@gmail.com", "deptraicogilasai"));
//		System.out.println(dao.updateLastLogin("nhathoang8c01@gmail.com"));

//		List<PersonModel> list = dao.querySearch(0, 20, "hoang");
//		System.out.println("list size: " + list.size());
//		
//		for (PersonModel p: list) {
//			System.out.println(p.getEmail() + " " + p.getPhone() + " " + p.getPassword() + " " + p.getRole() + " " + p.getStatus() + " " + p.getLastLogin());
//		}

		// delete user
//		int[] testArr = {61, 63};
//		System.out.println(dao.deleteUser(testArr));

		// change role
//		System.out.println(dao.updateRole(75, "User"));

		// calculate date
//		String dateString1 = "20/07/2023";
//		String dateString2 = "10/07/2023";
//		String dateString1 = "2023-07-20";
//		String dateString2 = "2023-07-10";

//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

//		try {
//			Date date1 = sdf.parse(dateString1);
//			Date date2 = sdf.parse(dateString2);
//
//			// Convert to LocalDate for date calculation
//			LocalDate localDate1 = date1.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
//			LocalDate localDate2 = date2.toInstant().atZone(java.time.ZoneId.systemDefault()).toLocalDate();
//
//			// Calculate the difference in days
//			long differenceInDays = ChronoUnit.DAYS.between(localDate2, localDate1);
//
//			System.out.println("Date 1: " + dateString1);
//			System.out.println("Date 2: " + dateString2);
//			System.out.println("Difference in days: " + differenceInDays);
//		} catch (ParseException e) {
//			System.out.println("Error parsing date: " + e.getMessage());
//		}
		
		// CAMPAIGN DAO TEST
//		CampaignModel campaign = new CampaignModel();
//        campaign.setOwner("Jane Smith");
//        campaign.setEmail("john@example.com"); // This email already exists in the database
//        campaign.setPhone(9876543210L);
//        campaign.setBankName("Bank of Test");
//        campaign.setBankAccount(1234567890L);
//        campaign.setTitle("Test Campaign");
//        campaign.setStatus("New");
//        campaign.setDescription("Test Campaign Description");
//        campaign.setTargetAmount(1000.00);
//        campaign.setCurrentAmount(0.00);
//        campaign.setStartDate("2023-07-01");
//        campaign.setEndDate("2023-07-21");
        
//        System.out.println("Diff: " + campaign.calculateDaysRemaining(campaign.getStartDate(), campaign.getEndDate()));
//        
//        try {
//			System.out.println(camDAO.insertCampaign(campaign));
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		// get campaigns
//		List<CampaignModel> campaigns = camDAO.findCampaigns("");
//		System.out.println("num of campaigns: " + campaigns.size());
		
		// get campaign name through id
//		System.out.println(conDAO.getCampaignNameByContributionId(3));
		
		// test verify
//		conDAO.setContributionVerifyStatus(26, 1);
		
		camDAO.updateCurrentAmount(3, 100.0, 1);
	}
}
