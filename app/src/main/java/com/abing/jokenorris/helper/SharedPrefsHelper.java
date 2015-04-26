package com.abing.jokenorris.helper;

import android.content.Context;
import android.content.SharedPreferences;

public class SharedPrefsHelper {

    private static final String NAME_PREF_FILE_NAME = "NAME_PREF_FILE_NAME";

    public static void saveToPreference(Context context, String preferenceKey, String preferenceValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME_PREF_FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(preferenceKey, preferenceValue);
        editor.apply();
    }

    public static String readPreference(Context context, String preferenceKey, String defaultValue) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(NAME_PREF_FILE_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(preferenceKey, defaultValue);
    }
}
