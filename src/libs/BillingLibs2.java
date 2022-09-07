package libs;

public class BillingLibs2 {
	
	
	String Name;
	int Booking_ID;
	String CheckIn;
	String CheckOut;
	int Room_ID;
	int rate;
	String rooms_status;
	
	
	
	public BillingLibs2() {
		
		this.Name = "";
		this.Booking_ID = 0;
		this.CheckIn = "";
		this.CheckOut = "";
		this.Room_ID = 0;
		this.rate = 0;
		this.rooms_status = "";
	}

	public BillingLibs2(String name, int booking_ID, String checkIn, String checkOut, int room_ID, int rate,
			String rooms_status) {
		
		this.Name = name;
		this.Booking_ID = booking_ID;
		this.CheckIn = checkIn;
		this.CheckOut = checkOut;
		this.Room_ID = room_ID;
		this.rate = rate;
		this.rooms_status = rooms_status;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public int getBooking_ID() {
		return Booking_ID;
	}

	public void setBooking_ID(int booking_ID) {
		Booking_ID = booking_ID;
	}

	public String getCheckIn() {
		return CheckIn;
	}

	public void setCheckIn(String checkIn) {
		CheckIn = checkIn;
	}

	public String getCheckOut() {
		return CheckOut;
	}

	public void setCheckOut(String checkOut) {
		CheckOut = checkOut;
	}

	public int getRoom_ID() {
		return Room_ID;
	}

	public void setRoom_ID(int room_ID) {
		Room_ID = room_ID;
	}

	public int getRate() {
		return rate;
	}

	public void setRate(int rate) {
		this.rate = rate;
	}

	public String getRooms_status() {
		return rooms_status;
	}

	public void setRooms_status(String rooms_status) {
		this.rooms_status = rooms_status;
	}

	@Override
	public String toString() {
		return "BillingLibs2 [Name=" + Name + ", Booking_ID=" + Booking_ID + ", CheckIn=" + CheckIn + ", CheckOut="
				+ CheckOut + ", Room_ID=" + Room_ID + ", rate=" + rate + ", rooms_status=" + rooms_status + "]";
	}

	
	

}
