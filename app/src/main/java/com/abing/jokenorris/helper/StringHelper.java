package com.abing.jokenorris.helper;

/**
 * Created by Catingub on 4/25/2015.
 */
public class StringHelper {
    public static String removeSpecialChars(String name){
        return name.replaceAll("[^\\w\\s]", "");
    }
}
