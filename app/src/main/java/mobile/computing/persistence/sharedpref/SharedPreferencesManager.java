package mobile.computing.persistence.sharedpref;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesManager {

    private static final String TAG = "SharedPrefManager";

    SharedPreferences sharedPreferences;
    Activity activity;

    public SharedPreferencesManager(Activity activity) {

        sharedPreferences = activity.getPreferences(Context.MODE_PRIVATE);
        this.activity = activity;

    }

    public void saveString(String key, String stringValue) {

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, stringValue);
        Log.d(TAG, "String saved");
        editor.commit();
    }

    public String readString(String key) {

        SharedPreferences sharedPref = activity.getPreferences(Context.MODE_PRIVATE);
        String stringValue = sharedPref.getString(key, "https://ibm.biz/BdzLPG");
        Log.d(TAG, "String loaded: " + stringValue);

        return stringValue;
    }
}
