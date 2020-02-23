/**
 * 
 * @author MattWong
 * This is the main function where it will be invoked on the command line. 
 * It will create a Business Card Parser Object call the 'getContactInfo'
 * method. We will pass in an empty string which will signify that the input
 * will be coming in through the command line. 
 */
public class Runner {
	public static void main(String[] args) {
		BusinessCardParser bcp = new BusinessCardParser();

		try {
			ContactInfo ci = bcp.getContactInfo("");

			// Print out the attributes of the parsed text. 	    	
			System.out.println("Name: " + ci.getName());
			System.out.println("Phone: " + ci.getPhoneNumber());
			System.out.println("Email: " + ci.getEmailAddress());

		} catch (Exception e) {
			System.out.print(e);
		}

	}

}
