package com.example.studentapp.Pref;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.studentapp.R;


public final class Pref {


    // User Details
    public static String iUser_id = "iUser_id";



    public static SharedPreferences get(final Context context) {
        return context.getSharedPreferences(context.getResources().getString(R.string.app_name),Context.MODE_PRIVATE);
    }

    public static String getPref(final Context context, String pref,	String def) {
        SharedPreferences prefs = Pref.get(context);
        String val = prefs.getString(pref, def);

        if (val == null || val.equals("") || val.equals("null"))
            return def;
        else
            return val;
    }

    public static void putPref(final Context context, String pref,String val) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();

        editor.putString(pref, val);
        editor.commit();
    }
    public static void removevDrag_Track_ID(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove("");
        editor.commit();
    }

    public static void put_iUser_id(final Context context, String iUser_id1) {
        Pref.putPref(context, iUser_id, iUser_id1);
    }

    public static String get_iUser_id(final Context context) {
        return Pref.getPref(context, iUser_id, null);
    }

    public static void remove_iUser_id(final Context context) {
        SharedPreferences prefs = Pref.get(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(iUser_id);
        editor.commit();
    }


    public static void removeAll(Context context) {
    }
}
