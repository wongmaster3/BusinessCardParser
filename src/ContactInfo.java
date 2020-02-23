/**
 * 
 * @author MattWong
 * This class will hold the name, phone, and email from the contact card after 
 * we parse the text from a document. This will be the final object that will 
 * be returned when we call 'getContactInfo' from the 'BusinessCardParser' class.
 */
public class ContactInfo {
	private String name; 
	private String phone; 
	private String email;
	
	public ContactInfo(String name, String phone, String email) {
		this.name = name;
		this.phone = phone;
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public String getPhoneNumber() {
		return phone;
	}

	public String getEmailAddress() {
		return email;
	}
}
