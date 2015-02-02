package com.rain.utils.android.robocop.model;

public class StringUtils {

    public static final String STRING = "string";
    public static final String DOUBLE = "double";
    public static final String INT = "int";
    public static final String BOOLEAN = "boolean";
    public static final String LONG = "long";
    public static final String DATE_TIME = "datetime";
    
    public static String getPrivateVariableName(String input) {
        input = convertToCamelCase(input);
        String firstChar = input.substring(0, 1);
        String remaining = input.substring(1, input.length());
        return "m" + firstChar.toUpperCase() + remaining;
    }

    public static String convertToCamelCase(String input) {
        String[] parts = input.split("_");
        String camelCaseString = "";
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            camelCaseString = camelCaseString + ((i == 0) ? part.toLowerCase() : toProperCase(part));
//            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    public static String convertToTitleCase(String input) {
        String[] parts = input.split("_");
        String camelCaseString = "";
        for (String part : parts){
            camelCaseString = camelCaseString + toProperCase(part);
        }
        return camelCaseString;
    }

    public static String getUnderscoreNameString(String input) {
        String regex = "([a-z])([A-Z])";
        String replacement = "$1_$2";
        return input.replaceAll(regex, replacement);
    }

    public static String getConstantString(String input) {
        String constant = StringUtils.getUnderscoreNameString(input).toUpperCase();
        return constant;
    }

    private static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }

    public static String getTypeString(String text) {
        if (text.equals(INT) || text.equals(BOOLEAN)) {
            return "INTEGER";
        } else if (text.equals(LONG) || text.equals(DOUBLE) || text.equals(DATE_TIME)) {
            return "NUMERIC";
        } else if (text.equals(STRING)) {
            return "TEXT";
        } else {
            return "CUSTOM";
        }
    }

    public static String getJavaTypeString(String text) {
        String typeLower = text.toLowerCase();
        if (typeLower.equals(BOOLEAN)) {
            return "boolean";
        } else if (typeLower.equals(INT)) {
            return "int";
        } else if (typeLower.equals(LONG)) {
            return "long";
        } else if (typeLower.equals(DOUBLE) || typeLower.equals(DATE_TIME)) {
            return "double";
        } else if (typeLower.equals(STRING)) {
            return "String";
        } else {
            return text;
        }
    }

    public static String getJavaTypeLongString(String text) {
        String typeLower = text.toLowerCase();
        if (typeLower.equals(BOOLEAN)) {
            return "Boolean";
        } else if (typeLower.equals(INT)) {
            return "Integer";
        } else if (typeLower.equals(DOUBLE)) {
            return "Double";
        } else if (typeLower.equals(LONG) || typeLower.equals(DATE_TIME)) {
            return "Long";
        } else {
            return "String";
        }
    }
}
