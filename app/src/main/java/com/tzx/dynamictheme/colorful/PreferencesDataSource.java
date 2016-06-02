package com.tzx.dynamictheme.colorful;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tzx.dynamictheme.theme.ThemeJson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xstar on 2016/5/25.
 */
public class PreferencesDataSource implements TDataSource
{
	private Context				mContext;
	private SharedPreferences	sharedPreferences;
	private static final String	THEME_KEY	= "themeJsons";
	private Gson				gson;

	public PreferencesDataSource(Context context)
	{
		mContext = context;
		sharedPreferences = mContext.getSharedPreferences(context.getPackageName(), Context.MODE_PRIVATE);
		gson = new Gson();
	}

	@Override
	public List<ThemeJson> getSource()
	{
		String json = sharedPreferences.getString(THEME_KEY, "");
		List<ThemeJson> themeJsons = gson.fromJson(json, new TypeToken<List<ThemeJson>>()
		{
		}.getType());
		if (themeJsons == null) themeJsons = new ArrayList<>();
		return themeJsons;
	}

	@Override
	public boolean saveSource(List<ThemeJson> themeJsons)
	{
		String json = gson.toJson(themeJsons, new TypeToken<List<ThemeJson>>()
		{
		}.getType());
		sharedPreferences.edit().putString(THEME_KEY, json).apply();
		return true;
	}
}
