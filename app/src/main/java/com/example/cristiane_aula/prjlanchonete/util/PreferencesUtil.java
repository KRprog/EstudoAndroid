package com.example.cristiane_aula.prjlanchonete.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by RAFAEL on 12/03/2017.
 */

public class PreferencesUtil {
    private static final String KEY_USER = "user";
    private static final String KEY_TOKEN = "token";

    private static PreferencesUtil mInstance = null;
    private SharedPreferences preferences;
    private Context mCtx;

    private PreferencesUtil(Context context) {
        mCtx = context;
        preferences = context.getSharedPreferences("config", Context.MODE_PRIVATE);
    }

    public static synchronized PreferencesUtil getInstance(Context context){
        if(mInstance == null)
            mInstance = new PreferencesUtil(context);
        return mInstance;
    }

    private SharedPreferences getSharedPreferences() {
        if(preferences == null)
            preferences = mCtx.getSharedPreferences("config", Context.MODE_PRIVATE);
        return preferences;
    }

    public void setUser(String user) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_USER, user);
        editor.commit();
    }

    public void setToken(String password) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_TOKEN, password);
        editor.commit();
    }

    public String getUser() {
        return preferences.getString(KEY_USER, "");
    }

    public String getToken() {
        return preferences.getString(KEY_TOKEN, "");
    }

}
