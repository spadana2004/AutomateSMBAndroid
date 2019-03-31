package com.swish.vtigerautomatecrm.PreferenceManager;

import android.content.Context;
import android.content.SharedPreferences;

import com.swish.vtigerautomatecrm.Constants.AppConstants;

public class ContextSharedPreferenceManager {
    SharedPreferences preferences;
    private static ContextSharedPreferenceManager contextSharedPreferenceManager;

    public static ContextSharedPreferenceManager getInstance(){
        if(contextSharedPreferenceManager==null){
            contextSharedPreferenceManager=new ContextSharedPreferenceManager();
            return contextSharedPreferenceManager;
        }
        else {
            return contextSharedPreferenceManager;
        }
    }

    public SharedPreferences getContextSharedPrefs(Context context){
        if (preferences == null) {
            preferences = context.getSharedPreferences(AppConstants.AppPrefs, Context.MODE_PRIVATE);
            return preferences;
        }
        else{
            return preferences;
        }
    }

}
