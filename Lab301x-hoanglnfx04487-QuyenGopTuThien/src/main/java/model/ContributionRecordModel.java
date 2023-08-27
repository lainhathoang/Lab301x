package model;

public class ContributionRecordModel {

	private int contributionId;
    private int campaignId;
    private String email;
    private String donationTime;
    private double contributionAmount;
    private String contributionMethod;
    private int verify;

    // Constructor
    public ContributionRecordModel() {
    	this.verify = 0;
    }

	public ContributionRecordModel(int campaignId, String email, String donationTime,
			double contributionAmount, String contributionMethod) {
		super();
		this.campaignId = campaignId;
		this.email = email;
		this.donationTime = donationTime;
		this.contributionAmount = contributionAmount;
		this.contributionMethod = contributionMethod;
		this.verify = 0;
	}

	// Getters and Setters
    public int getContributionId() {
        return contributionId;
    }

    public void setContributionId(int contributionId) {
        this.contributionId = contributionId;
    }

    public int getCampaignId() {
        return campaignId;
    }

    public void setCampaignId(int campaignId) {
        this.campaignId = campaignId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDonationTime() {
        return donationTime;
    }

    public void setDonationTime(String donationTime) {
        this.donationTime = donationTime;
    }

    public double getContributionAmount() {
        return contributionAmount;
    }

    public void setContributionAmount(double contributionAmount) {
        this.contributionAmount = contributionAmount;
    }

    public String getContributionMethod() {
        return contributionMethod;
    }

    public void setContributionMethod(String contributionMethod) {
        this.contributionMethod = contributionMethod;
    }

	public int getVerify() {
		return verify;
	}

	public void setVerify(int verify) {
		this.verify = verify;
	}

}
