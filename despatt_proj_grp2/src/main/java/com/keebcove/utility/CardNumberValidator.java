package com.keebcove.utility;

public class CardNumberValidator {
	public static boolean luhnTest(String number){
		if (number == null || number.trim().isEmpty()) {
            return false;  
        }
        number = number.trim(); 

        int s1 = 0, s2 = 0;
        String reverse = new StringBuilder(number).reverse().toString();
        for (int i = 0; i < reverse.length(); i++) {
            int digit = Character.digit(reverse.charAt(i), 10);
            if (i % 2 == 0) {
                s1 += digit;
            } else {
                s2 += 2 * digit;
                if (digit >= 5) {
                    s2 -= 9;
                }
            }
        }
        return (s1 + s2) % 10 == 0;
    }
}
