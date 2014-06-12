package com.bitoola.apps.mobile.android.validation;

import java.util.regex.Pattern;

public class Validation {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    
    private static Pattern sEmailPattern;

    static {
    	sEmailPattern = Pattern.compile(EMAIL_PATTERN);
    }
    		
	public static boolean isNull(Object input) {
		
		return input == null;
	}
	
	public static boolean isWhiteSpace(String input) {
		
		return !isNull(input) && input.trim().length() == 0;
	}
	
	public static boolean isEmpty(String input) {
		
		return !isNull(input) && input.length() == 0;
	}
	
	public static boolean isNullOrWhiteSpace(String input) {
		
		return isNull(input) || isWhiteSpace(input);
	}
	
	public static boolean isNullOrEmpty(String input) {
		
		return isNull(input) || isEmpty(input);
	}
	
	public static boolean isEmail(String input) {

        return !isNull(input) && sEmailPattern.matcher(input).matches();
	}	
}
