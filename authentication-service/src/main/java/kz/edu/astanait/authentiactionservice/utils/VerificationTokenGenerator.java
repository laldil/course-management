package kz.edu.astanait.authentiactionservice.utils;

import java.security.SecureRandom;
import java.util.Random;

/**
 * @author aldi
 * @since 06.06.2024
 */
public class VerificationTokenGenerator {
    private static final int REGISTRATION_CODE_SIZE = 6;

    static char[] NUMBERS = "0123456789".toCharArray();
    static Random rand = new SecureRandom();

    public static String getRegistrationCode() {
        return getCode(REGISTRATION_CODE_SIZE);
    }

    public static String getCode(int length) {
        assert length >= 4;
        char[] code = new char[length];


        //populate rest of the password with random chars
        for (int i = 0; i < length; i++) {
            code[i] = NUMBERS[rand.nextInt(NUMBERS.length)];
        }

        //shuffle it up
        for (int i = 0; i < code.length; i++) {
            int randomPosition = rand.nextInt(code.length);
            char temp = code[i];
            code[i] = code[randomPosition];
            code[randomPosition] = temp;
        }

        return new String(code);
    }
}
