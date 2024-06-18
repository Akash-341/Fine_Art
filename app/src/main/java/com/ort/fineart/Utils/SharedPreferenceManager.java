package com.ort.fineart.Utils;

import android.content.Context;
import android.content.SharedPreferences;

public  class SharedPreferenceManager {
    private static final String PREF_NAME = "user_pref";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_USER_EMAIL = "user_email";
    private static final String KEY_USER_FIRST_NAME = "user_first_name";
    private static final String KEY_USER_LAST_NAME = "user_last_name";
    private static final String KEY_USER_TOKEN = "user_token";
    private static final String KEY_USER_PHONE = "user_phone";

    private final SharedPreferences sharedPreferences;

    public SharedPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }
    public void saveUserDetails(String userId, String userEmail, String userFirstName, String userLastName, String userToken,String userPhoneno) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_ID, userId);
        editor.putString(KEY_USER_EMAIL, userEmail);
        editor.putString(KEY_USER_FIRST_NAME, userFirstName);
        editor.putString(KEY_USER_LAST_NAME, userLastName);
        editor.putString(KEY_USER_TOKEN, userToken);
        editor.putString(KEY_USER_PHONE, userPhoneno);
        editor.apply();
    }
    public String getUserId() {
        return sharedPreferences.getString(KEY_USER_ID, "");
    }

    public final String getUserEmail() {
        return sharedPreferences.getString(KEY_USER_EMAIL, "");
    }

    public String getUserFirstName() {
        return sharedPreferences.getString(KEY_USER_FIRST_NAME, "");
    }

    public String getUserLastName() {
        return sharedPreferences.getString(KEY_USER_LAST_NAME, "");
    }

    public  String getUserToken() {
        return sharedPreferences.getString(KEY_USER_TOKEN, "");
    }
    public String getUserPhone() {
        return sharedPreferences.getString(KEY_USER_PHONE, "");
    }

    public static void clearSharedPreferences(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_pref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply(); // or use editor.commit() if you need synchronous behavior
    }



}
