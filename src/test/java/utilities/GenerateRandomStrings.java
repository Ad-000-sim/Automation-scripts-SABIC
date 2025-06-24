package utilities;

import org.apache.commons.lang3.RandomStringUtils;

public class GenerateRandomStrings {
	
	public static String RandomGmail() {
	    String em = RandomStringUtils.randomAlphanumeric(10).toLowerCase();
	    return em + "@simreka.com";
	}
	
	public static String generateRandomFirstName() {
        return "First" + RandomStringUtils.randomAlphabetic(4);
    }

    public static String generateRandomLastName() {
        return "Last" + RandomStringUtils.randomAlphabetic(5);
    }

    public static String generateRandomPhoneNumber() {
        char[] startDigits = {'9', '8', '7', '6'};
        char firstDigit = startDigits[(int)(Math.random() * startDigits.length)];
        String restDigits = RandomStringUtils.randomNumeric(9);
        return firstDigit + restDigits;
    }

    public static String generateRandomPassword() {
        String base = RandomStringUtils.randomAlphanumeric(8);
        String specialChars = "!@#$%&*";
        char special = specialChars.charAt((int)(Math.random() * specialChars.length()));
        return base + special;
    }
    
    public static String generateAlphanumericPassword() {
        return RandomStringUtils.randomAlphanumeric(9); // example: "aZ9bT6LpQ"
    }
    
    public static String generateNumber() {
    	return RandomStringUtils.randomNumeric(3);
    }
    
    public static String specialCharacters() {
    	String specialChars = " !#%^&+()/}{><[]*:'\"\\";
		return RandomStringUtils.random(3, specialChars);
    }

}
