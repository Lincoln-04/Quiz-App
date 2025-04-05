public class Player {
	 String name;
	 String address;
	 String phoneno;
	public Player(String name, String address,String phoneno) {
		super();
		this.name = name;
		this.address = address;
		this.phoneno = phoneno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	
}
