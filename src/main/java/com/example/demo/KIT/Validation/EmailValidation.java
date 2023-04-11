package com.example.demo.kit.Validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailValidation {
    private static final String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    private static Pattern pattern = Pattern.compile(EMAIL_REGEX);;
    private static Matcher matcher;

    public static boolean track(String email) {
        matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
