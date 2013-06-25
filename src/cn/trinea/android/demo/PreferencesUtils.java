package cn.trinea.android.demo;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * PreferencesUtils
 * 
 * @author Trinea 2013-5-9
 */
public class PreferencesUtils {

    public static final String PREFERENCES_NAME     = "TrineaAndroidCommon";
    public static final String KEY_NAME_DOWNLOAD_ID = "downloadId";

    /**
     * put long preferences
     * 
     * @param context
     * @param key
     * @param value
     */
    public static void putLongPreferences(Context context, String key, long value) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putLong(key, value);
        editor.commit();
    }

    /**
     * get long preferences
     * 
     * @param context
     * @param key
     * @return
     */
    public static long getLongPreferences(Context context, String key) {
        SharedPreferences settings = context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        return settings.getLong(key, -1);
    }
}
