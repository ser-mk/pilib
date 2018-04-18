package sermk.pipi.pilib;

import android.content.Context;
import android.content.SharedPreferences;

import java.lang.reflect.Field;

/**
 * Created by ser on 07.04.18.
 */

public class PiUtils {

    static public String getJsonFromShared(Context context){
        final SharedPreferences settings = context.
                getSharedPreferences(NameFieldCollection.SHARED_PREF_NAME_FILE,
                        Context.MODE_PRIVATE);
        final String json = settings.getString(NameFieldCollection.FIELD_JSON_SETTINGS, "");

        return json;
    }

    static public void saveJson(Context context, String json){
        final SharedPreferences mSettings =
                context.getSharedPreferences(
                        NameFieldCollection.SHARED_PREF_NAME_FILE, Context.MODE_PRIVATE);
        mSettings.edit().putString(NameFieldCollection.FIELD_JSON_SETTINGS, json).apply();
    }

    static public void clearJson(Context context){
        final SharedPreferences mSettings =
            context.getSharedPreferences(
                NameFieldCollection.SHARED_PREF_NAME_FILE, Context.MODE_PRIVATE);
        mSettings.edit().clear().apply();
    }


    static public <T> boolean checkHasNullPublicField(T tmp, Class<T> clazz){
        if(tmp == null){
            return true;
        }
        try {
            for (Field f : clazz.getDeclaredFields())
                if (f.get(tmp) == null)
                    return true;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return true;
        }
        return false;
    }

    public static <T> boolean contains2(final T[] array, final T v) {

        if (v == null)
            return false;

        for (final T e : array)
            if (e == v || v.equals(e))
                return true;

        return false;
    }
}
