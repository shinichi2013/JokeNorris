package com.abing.jokenorris.helper;

public class StringHelper {
    public static String removeSpecialChars(String name){
        return name.replaceAll("[^\\w\\s]", "");
    }
}
