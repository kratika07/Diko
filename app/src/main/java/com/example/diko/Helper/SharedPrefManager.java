package com.example.diko.Helper;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.diko.Activity.LoginActivity;
import com.example.diko.Class.User;


public class SharedPrefManager {
    private static final String SHARED_PREF_NAME = "diko";
    private static final String USER_TOKEN = "keyusertoken";
    private static final String USER_ID = "keyuserid";
    private static final String FULL_NAME = "keyfullname";
    private static final String FIRST_NAME = "keyfirstname";
    private static final String LAST_NAME = "keylastname";
    private static final String EMAIL = "keyemail";
    private static final String GROUPS_NAME = "keygroupname";
    private static final String PROFILE_PIC_MINI = "keyprofilepic";
    private static final String APP_ID = "keyappid";
    private static final String APP_OPTION = "keyappoption";
    private static final String SITE_ID = "keysiteid";
    private static final String PARENTID="keyparentid";
    public static SharedPrefManager mInstance;
    public static Context mCtx;

    public SharedPrefManager(Context context) {
        mCtx = context;
    }

    public static synchronized SharedPrefManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefManager(context);
        }
        return mInstance;
    }
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USER_TOKEN, user.getUSER_TOKEN());
        editor.putString(USER_ID, user.getUSER_ID());
        editor.putString(FULL_NAME, user.getFULL_NAME());
        editor.putString(FIRST_NAME, user.getFIRST_NAME());
        editor.putString(LAST_NAME, user.getLAST_NAME());
        editor.putString(EMAIL, user.getEMAIL());
        editor.putString(GROUPS_NAME, user.getGROUPS_NAME());
        editor.putString(PROFILE_PIC_MINI, user.getPROFILE_PIC_MINI());
        editor.putString(APP_ID, user.getAPP_ID());
        editor.putString(APP_OPTION, user.getAPP_OPTION());
        editor.putString(SITE_ID, user.getSITE_ID());
        editor.apply();
    }



    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(USER_TOKEN, null) != null;
    }

    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(USER_TOKEN, null),
                sharedPreferences.getString(USER_ID, null),
                sharedPreferences.getString(FULL_NAME, null),
                sharedPreferences.getString(FIRST_NAME, null),
                sharedPreferences.getString(LAST_NAME, null),
                sharedPreferences.getString(EMAIL, null),
                sharedPreferences.getString(GROUPS_NAME, null),
                sharedPreferences.getString(PROFILE_PIC_MINI, null),
                sharedPreferences.getString(APP_ID, null),
                sharedPreferences.getString(APP_OPTION, null),
                sharedPreferences.getString(SITE_ID, null)
        );
    }

    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}