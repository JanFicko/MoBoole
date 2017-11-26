package xyz.janficko.moboole.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.google.gson.Gson;

import java.lang.reflect.Type;

public class SharedPreferenceUtil {

    private SharedPreferences mSharedPreferences;

    public SharedPreferenceUtil(Context context) {
        this.mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean hasKey(String key) {
        return mSharedPreferences.contains(key);
    }

    public void saveString(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String retrieveString(String key, String defaultValue) {
        return mSharedPreferences.getString(key, defaultValue);
    }

    public void saveBoolean(String key, boolean value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public boolean retrieveBoolean(String key, boolean defaultValue) {
        return mSharedPreferences.getBoolean(key, defaultValue);
    }

    public void saveLong(String key, long value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putLong(key, value);
        editor.apply();
    }

    public long retrieveLong(String key, long defaultValue) {
        return mSharedPreferences.getLong(key, defaultValue);
    }

    public void saveObject(String key, Object value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(value);
        editor.putString(key, json);
        editor.apply();
    }

    public <T> T retrieveObject(String key, Type type) {
        Gson gson = new Gson();
        String json = retrieveString(key, null);
        return gson.fromJson(json, type);
    }


}
