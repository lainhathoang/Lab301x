package model;

public class PersonModel {
	private int id;
    private String email;
    private long phone;
    private String password;
    private String role; // : Admin, : User
    private String status; // : Banned, : Active
    private String lastLogin = null;
    
    public PersonModel() {
    	role ="User";
    	status = "Active";
    }

	public PersonModel(String email, long phone, String password, String role, String status) {
		super();
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.status = status;
	}
	
	public PersonModel(int id, String email, long phone, String password, String role, String status, String lastLogin) {
		super();
		this.id = id;
		this.email = email;
		this.phone = phone;
		this.password = password;
		this.role = role;
		this.status = status;
		this.lastLogin = lastLogin;
	}

	public int getId() {
		return id;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(String lastLogin) {
		this.lastLogin = lastLogin;
	}
	
}
