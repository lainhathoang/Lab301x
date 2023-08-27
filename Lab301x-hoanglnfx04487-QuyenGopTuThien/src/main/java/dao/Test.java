package dao;

import java.util.ArrayList;
import java.util.List;

import model.ContributionRecordModel;

public class Test {

	public Test() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ContributionRecordDAO dao = new ContributionRecordDAO();
		List<ContributionRecordModel> contributionRecords = new ArrayList<ContributionRecordModel>();
		contributionRecords = dao.getRecordsByEmail("nhathoang8c01@gmail.com");
		
		System.out.println(contributionRecords.size());
	}

}
