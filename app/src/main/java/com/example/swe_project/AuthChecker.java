package com.example.swe_project;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class AuthChecker {
    public static String authChecker(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        String token = sharedPreferences.getString("token",null);
        String role= sharedPreferences.getString("role","");
        if(token !=null){
            return role;
        }else
            return "";
    }

    public static void logout(Context context){
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context.getApplicationContext());
        SharedPreferences.Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }
}
