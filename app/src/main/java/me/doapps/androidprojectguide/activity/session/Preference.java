package me.doapps.androidprojectguide.activity.session;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by doapps on 6/15/17.
 */

public class Preference {

    private static final String PREFERENCE_NAME = "demo";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static Preference preference;

    private static final String EMAIL = "email";
    private static final String LOGIN = "login";

    private boolean logout;

    public static Preference getIntance(Context context) {
        if (preference == null) {
            preference = new Preference(context);
        }
        return preference;
    }

    public Preference(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setEmail(String email) {
        editor.putString(EMAIL, email).commit();
    }

    public String getEmail() {
        return sharedPreferences.getString(EMAIL, null);
    }

    public void setLogin(boolean login) {
        editor.putBoolean(LOGIN, login).commit();
    }

    public boolean isLogin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }
}
