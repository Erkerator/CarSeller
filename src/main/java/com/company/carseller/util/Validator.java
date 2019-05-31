package com.company.carseller.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validator {

    public static boolean checkToSigns(String text) {
        boolean inputHasOnlySigns;
        Pattern pattern = Pattern.compile("\\w+");
        Matcher matcher = pattern.matcher(text);
        inputHasOnlySigns = matcher.matches();
        return inputHasOnlySigns;
    }

    public static boolean checkPhoneNumberInput(String text) {
        boolean inputIncludesOnlyNumbers;
        Pattern pattern = Pattern.compile("\\d{11}");
        Matcher matcher = pattern.matcher(text);
        inputIncludesOnlyNumbers = matcher.matches();
        return inputIncludesOnlyNumbers;
    }
}
