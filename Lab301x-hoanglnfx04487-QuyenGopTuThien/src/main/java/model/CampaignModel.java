package model;

import util.DateUtil;

public class CampaignModel {
	private int id;
	private String owner;
	private String email;
	private long phone;
	private String bankName;
	private long bankAccount;
	private String title;
	private String status;
	private String description;
	private double targetAmount;
	private double currentAmount;
	private String startDate;
	private String endDate;

	public CampaignModel() {

	}

	public CampaignModel(int id, String owner, String email, long phone, String bankName, long bankAccount,
			String title, String status, String description, double targetAmount, double currentAmount,
			String startDate, String endDate) {
		super();
		this.id = id;
		this.owner = owner;
		this.email = email;
		this.phone = phone;
		this.bankName = bankName;
		this.bankAccount = bankAccount;
		this.title = title;
		this.status = status;
		this.description = description;
		this.targetAmount = targetAmount;
		this.currentAmount = currentAmount;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public long getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(long bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public double getCurrentAmount() {
		return currentAmount;
	}

	public void setCurrentAmount(double currentAmount) {
		this.currentAmount = currentAmount;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
	public long calculateDaysRemaining(String endDate) {
		return new DateUtil().calculateDaysRemaining(endDate);
	}
}
