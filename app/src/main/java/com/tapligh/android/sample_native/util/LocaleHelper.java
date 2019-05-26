package com.tapligh.android.sample_native.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.preference.Preference;
import android.preference.PreferenceManager;

import java.util.Calendar;
import java.util.Locale;

/**
 * CREATED BY Javadroid FOR `android-native-sample` PROJECT
 * AT: 2019/May/21 18:09
 */
public class LocaleHelper {

    private static final String SELECTED_LANGUAGE = "Locale.Helper.Selected.Language";

    public static Context onAttach(Context context, String defaultLanguage) {
        if (context == null) return null;
        String lang = getPersistedData(context, defaultLanguage);

        return setLocale(context, lang);

    }

    private static String getPersistedData(Context context, String defaultLanguage) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        return preferences.getString(SELECTED_LANGUAGE, defaultLanguage);
    }

    private static Context setLocale(Context context, String lang) {
        persist(context, lang);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return updateResources(context, lang);
        } else {
            return updateResourcesLegacy(context, lang);
        }

    }

    private static void persist(Context context, String lang) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        preferences.edit().putString(SELECTED_LANGUAGE, lang).apply();
    }

    private static Context updateResources(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        context.getResources().getConfiguration().setLocale(locale);
        context.getResources().getConfiguration().setLayoutDirection(locale);
        return context.createConfigurationContext(context.getResources().getConfiguration());
    }


    private static Context updateResourcesLegacy(Context context, String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);

        context.getResources().getConfiguration().setLocale(locale);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            context.getResources().getConfiguration().setLayoutDirection(locale);
        }
        context.getResources().updateConfiguration(context.getResources().getConfiguration(), context.getResources().getDisplayMetrics());
        return context;

    }

}
