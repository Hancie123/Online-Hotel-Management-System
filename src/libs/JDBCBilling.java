package libs;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class JDBCBilling extends Database{
	
	SimpleDateFormat dateformate=new SimpleDateFormat("yyyy-MM-dd");
	Date date=new Date();
	String currentDate=dateformate.format(date);
	
	public ArrayList view_all1() {

		Connection conn;
		PreparedStatement pstat;
		ResultSet rs;
		ArrayList<BillingLibs2>Billing=new ArrayList();
		String sql="SELECT customers.Name, booking.Booking_ID, booking.CheckIn, "
				+ "booking.CheckOut, rooms.Room_ID, rooms.rate, rooms.rooms_status"
				+ " from customers inner join booking on customers.Customer_ID ="
				+ " booking.Customer_ID inner join rooms on rooms.Room_ID=booking."
				+ "Room_ID Where Booking_Status='Active';";
		
		
		
		
		try {
			conn=connect();
			
			pstat=conn.prepareStatement(sql);
		
			rs=pstat.executeQuery();
			
			while(rs.next()) {
				BillingLibs2 billing=new BillingLibs2(
						rs.getString("Name"),
						rs.getInt("Booking_ID"),
						rs.getString("CheckIn"),
						rs.getString("CheckOut"),
						rs.getInt("Room_ID"),
						rs.getInt("rate"),
						rs.getString("rooms_status"));
						
				
				Billing.add(billing);
				
			}
			
			
		}
		catch(Exception ex) {
			System.out.println("Error"+ex.getMessage());
		}
		return Billing;
		
	}
	
	
	
	public boolean insert (BillingLibs3 billing) {
		
		Connection conn;
		PreparedStatement pstat;
		boolean result=false;
		String sql="INSERT INTO `billing`(`Billing_ID`, `Booking_ID`, `Name`, `VAT`, `Service_Charge`,`Room_Price`,  `Total_Bill`, `Biiling_Status`) VALUES (?,?,?,?,?,?,?,?)";
		try {
			
			conn=connect();
			
			pstat=conn.prepareStatement(sql);
			
			pstat.setInt(1, billing.getBilling_ID());
			pstat.setInt(2, billing.getBooking_ID());
			pstat.setString(3, billing.getName());
			pstat.setDouble(4, billing.getVAT());
			pstat.setInt(5, billing.getService_Charge());
			pstat.setDouble(6, billing.getRoom_Price());
			pstat.setDouble(7, billing.getTotal_Bill());
			pstat.setString(8, billing.getBilling_Status());
			
			pstat.executeUpdate();
			
			conn.close();
			pstat.close();
			result=true;
			
			
		}
		
		catch(Exception ex) {
			System.out.println("Error"+ex.getMessage());
		}
		return result;
	}
	


	
public boolean update(BookingLibs booking) {
	
	Connection conn;
	PreparedStatement pstat;
	boolean result5=false;
	String sql="UPDATE booking SET Booking_Status=? WHERE Booking_ID=?";
	try {
		
		conn=connect();
		
		pstat=conn.prepareStatement(sql);
		
		pstat.setString(1, booking.getBooking_Status());		
		pstat.setInt(2, booking.getBooking_ID());
		
		
		pstat.executeUpdate();
		conn.close();
		pstat.close();
		result5=true;
		
	}
	catch(Exception ex) {
		System.out.println("Error"+ex.getMessage());
	}
	return result5;
}

}
