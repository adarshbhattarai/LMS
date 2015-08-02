package business;

import java.io.Serializable;

public class LibraryMember extends Person implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6141701651920356741L;

	private String memberId;

	checkoutRecord chkrecord;
	
	public LibraryMember(String memberid, String f, String l, String t, Address a ) {
		super(f, l, t, a);
		// TODO Auto-generated constructor stub
		this.setMemberId(memberid);
		 
	}
	
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	public checkoutRecord getChkrecord() {
		return chkrecord;
	}

	public void setChkrecord(checkoutRecord chkrecord) {
		this.chkrecord = chkrecord;
	}
	
	public String getFirstName() {
		return super.getFirstName();
	}
	
	public String getLastName() {
		return super.getLastName();
	}


	public String getTelephoneNumber() {
		return super.getTelephone();
	}

	public String getStreet() {
		return super.getAddress().getStreet();
	}

	public String getCity() {
		return super.getAddress().getCity();
	}

	public String getState() {
		return super.getAddress().getState();
	}

	public String getZip() {
		return super.getAddress().getZip();
	}

}
