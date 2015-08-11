package com.example.uppersky_movil.myapplication.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceUtils {

    private static final String TAG = "{references";
    private static PreferenceUtils instance = null;

    private SharedPreferences mPrefs;
    private SharedPreferences.Editor mEditor;
    private Context mContext;

    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "PedlarPreferences";

    // Shared Preferences Keys
    private static final String TOKEN = "token";
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String NAME = "name";
    private static final String STORE = "STORE";


    public static PreferenceUtils getInstance(Context context) {
        if (instance == null) {
            instance = new PreferenceUtils(context);
        }
        return instance;
    }

    // Constructor

    private PreferenceUtils(Context context){
        this.mContext = context;
        mPrefs = this.mContext.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        mEditor = mPrefs.edit();
    }

    public void setToken(String value) {
       mEditor.putString(TOKEN,value);
       mEditor.apply();
    }

    public void removeToken() {
        mEditor.putString(TOKEN,"");
        mEditor.apply();
    }
    public String getToken(){
        return mPrefs.getString(TOKEN,"");
    }
    public void setLatitude(Float value) {
        mEditor.putFloat(LATITUDE, value);
        mEditor.apply();
    }

    public void removeLatitude() {
        mEditor.putFloat(LATITUDE, 0);
        mEditor.apply();
    }
    public Float getLatitude(){
        return mPrefs.getFloat(LATITUDE, 0);
    }

    public void setLongitude(Float value) {
        mEditor.putFloat(LONGITUDE,value);
        mEditor.apply();
    }

    public void removeLongitude() {
        mEditor.putFloat(LONGITUDE,0);
        mEditor.apply();
    }
    public Float getLongitude(){
        return mPrefs.getFloat(LONGITUDE,0);
    }

    public void setName(String value) {
        mEditor.putString(NAME, value);
        mEditor.apply();
    }

    public void removeName() {
        mEditor.putString(NAME, "");
        mEditor.apply();
    }
    public String getName(){
        return mPrefs.getString(NAME, "Jhon Smith");
    }

    public void setstore(boolean value) {
        mEditor.putBoolean(STORE, value);
        mEditor.apply();
    }

    public boolean getStore(){
            return mPrefs.getBoolean(STORE, false);
    }
}
