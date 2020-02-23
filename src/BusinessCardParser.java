/**
 * This file holds a 'BusinessCardParser' class. This class will parse the 
 * given text document that is passed in to the input either via the command
 * line or a junit test. It will iterate through each line and test to see 
 * if that line matches a phone number pattern or email pattern. If the
 * line does not match either, then the line will be considered for 
 * containing a name.  
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.regex.*;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.util.Span;

public class BusinessCardParser {
	// Patterns that we are looking for in the lines if we want to match with 
	// phone number or email. 
	private Pattern phonePattern = Pattern.compile("\\+?(1?)\\s*\\(?(\\d{3})\\)?-?\\s*(\\d{3})-?\\s*(\\d{4})");
	private Pattern emailPattern = Pattern.compile("(\\S+@\\S+\\.com)");

	public ContactInfo getContactInfo(String document) throws Exception {
		// Will store an object that contains text of line probability where
		// the line is not a phone number or email line
		LinkedList<NameLine> lst = new LinkedList<NameLine>(); 

		Scanner sc;
		if (document.equals("")) {
			// We are feeding input from command line
			sc = new Scanner(System.in);
		} else {
			// We are feeding input from junit test
			File file = new File(document);
			sc = new Scanner(file);
		}

		/* Temporary Contact Info Variables that will store the 
		 * best matches of the name, phone, and email in the text
		 * document. These variables will be used for the fields  
		 * in the 'ContactInfo' object that will be returned.
		 */
		String name = "";
		String phone = "";
		String email = "";

		// Matcher Reference
		Matcher m;

		// NLP Library with source: http://opennlp.sourceforge.net/models-1.5/
		// The model we will be using to check the line that best matches the name
		// of the person is in the 'en-ner-person.bin' file.
		InputStream inputStream = new FileInputStream("Resources/en-ner-person.bin");

		TokenNameFinderModel model = new TokenNameFinderModel(inputStream);

		//Instantiating the NameFinder class 
		NameFinderME nameFinder = new NameFinderME(model);

		// Iterate through the entire document checking line by line 
		// and fill in the name, phone, and email fields above. 
		while (sc.hasNextLine()) {
			String currentLine = sc.nextLine();

			// Check line if it is name, phone number, or email
			if ((m = emailPattern.matcher(currentLine)).find()) {
				// Current line is email.
				// Email in the line is captured as group 0 using the regex above
				email = m.group(0);

			} else if ((m = phonePattern.matcher(currentLine)).find()) {
				/* Current line is phone number or fax number.
				 * I used if-statement to check if the phone number field is already 
				 * filled in. If there are both the fax and phone numbers in the 
				 * document and the fax number appears after the phone number, then 
				 * if we did not have this if-statement, parsePhone would overwrite
				 * the phone field with an empty string when we encounter the fax 
				 * number line. 
				 */
				if (phone.equals("")) {
					phone = parsePhone(currentLine, m);
				}
			} else {
				/* Current line might include possible name. We will add the 
				 * current line into our list since the line does not follow 
				 * a phone number or an email pattern. We split the string into 
				 * different words and add the probability of each word being a 
				 * name to the total variable (which contains the total probability
				 * that the entire line is a name).
				 */
				double total = 0;
				Span nameSpans[] =nameFinder.find(currentLine.split("\\s"));
				for (Span span : nameSpans)         
					total += span.getProb();
				// Add a new NameLine object that will hold the current line 
				// and probability of that line
				lst.add(new NameLine(currentLine, total));				
			}

		}

		// Retrieve line that has the highest probability to be the name
		// and assign the name variable to that name with highest probability.
		name = parseName(lst);

		// Close the scanner
		sc.close();

		return new ContactInfo(name, phone, email);
	}

	private String parseName(LinkedList<NameLine> lst) {
		// We have our list of potential lines of names. 
		// We now need to find the highest probability.
		NameLine max = lst.remove();
		while(!lst.isEmpty()) {
			NameLine current = lst.remove();
			if (current.getProbability() > max.getProbability()) {
				max = current; 
			}
		}
		return max.getName();
	}

	private String parsePhone(String currentLine, Matcher m) {
		// We will first check if the line contains an 'f' and 'F'. 
		// If it does, then we know that this has to be a fax number,
		// so we return an empty string. 
		for(int i = 0; i < currentLine.length(); i++) {
			char current = currentLine.charAt(i);
			if(current == 'f' || current == 'F') {
				// The current number is a fax number so 
				// we return an empty string
				return "";
			}
		}
		// No 'f' or 'F' was found since that will indicate a 
		// fax number so we return the captured number
		// We need to now filter only the numbers from the phone number.
		String parsedNumber = "";
		for(int i = 1; i <= m.groupCount(); i++) {
			parsedNumber += m.group(i);
		}

		return parsedNumber;
	}

}
