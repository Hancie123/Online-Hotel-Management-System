package libs;

public class CustomerLibs123 {
	
	int CustomerID;
	String Gender;
	String Title;
	
	
	public CustomerLibs123() {
		
		this.CustomerID = 0;
		this.Gender = "";
		this.Title = "";
	}
	
	
	
	public CustomerLibs123(int customerID, String gender, String title) {
		
		CustomerID = customerID;
		Gender = gender;
		Title = title;
	}



	public int getCustomerID() {
		return CustomerID;
	}



	public void setCustomerID(int customerID) {
		CustomerID = customerID;
	}



	public String getGender() {
		return Gender;
	}



	public void setGender(String gender) {
		Gender = gender;
	}



	public String getTitle() {
		return Title;
	}



	public void setTitle(String title) {
		Title = title;
	}



	@Override
	public String toString() {
		return "CustomerLibs123 [CustomerID=" + CustomerID + ", Gender=" + Gender + ", Title=" + Title + "]";
	}
	
	
	
	
	

}
