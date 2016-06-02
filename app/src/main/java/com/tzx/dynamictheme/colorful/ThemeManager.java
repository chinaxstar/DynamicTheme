package com.tzx.dynamictheme.colorful;

import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.tzx.dynamictheme.theme.ResContent;
import com.tzx.dynamictheme.theme.ResType;
import com.tzx.dynamictheme.theme.ThemeJson;

/**
 * Created by xstar on 2016/5/24.
 */
public class ThemeManager
{
	private static ThemeManager	instance;
	private List<ThemeJson>		themeJsons	= new ArrayList<>();
	private TDataSource			dataSource;

	private ThemeManager()
	{
	}

	public static ThemeManager getInstance()
	{
		if (instance == null) instance = new ThemeManager();
		return instance;
	}

	public TDataSource getDataSource()
	{
		return dataSource;
	}

	public void bindDataSource(TDataSource dataSource)
	{
		this.dataSource = dataSource;
		if (dataSource != null)
		{
			themeJsons.clear();
			themeJsons.addAll(dataSource.getSource());
		}
	}

	public List<ThemeJson> getAllThemes()
	{
		if (themeJsons.size() == 0)
		{
			bindDataSource(dataSource);
		}
		return themeJsons;
	}

	public void addNewTheme(ThemeJson themeJson)
	{
		if (themeJson != null)
		{
			boolean isUnqie = true;
			for (ThemeJson json : themeJsons)
			{
				if (json.getThemeID().equals(themeJson.getThemeID())) isUnqie = false;
			}
			if (isUnqie) themeJsons.add(themeJson);
			else Log.w("addNewTheme", "same ID:" + themeJson.getThemeID());
		}
	}

	public void remove(ThemeJson themeJson)
	{
		if (themeJson != null) themeJsons.remove(themeJson);
	}

	public void remove(String themeID)
	{
		ThemeJson themeJson = null;
		for (ThemeJson json : themeJsons)
		{
			if (json.getThemeID().equals(json.getThemeID()))
			{
				themeJson = json;
				break;
			}
		}
		if (themeJson != null) themeJsons.remove(themeJson);
	}

	public ThemeJson getUsedTheme()
	{
		ThemeJson used = null;
		for (ThemeJson themeJson : themeJsons)
		{
			if (themeJson.isUsed()) used = themeJson;
		}
		return used;
	}

	public void saveTheme()
	{
		if (dataSource != null) dataSource.saveSource(themeJsons);
	}

	public void changeUsedTheme(ThemeJson themeJson)
	{
		if (themeJson == null) return;
		for (ThemeJson json : themeJsons)
		{
            json.setUsed(false);
			if (themeJson.getThemeID().equals(json.getThemeID()))
			{
                json.setUsed(true);
			}
		}
	}

	public static ResContent createResContent(ResType resType, int resInt, String resStr)
	{
		ResContent resContent = null;
		if (resType != null)
		{
			resContent = new ResContent();
			resContent.resType = resType;
			if (resType == ResType.COLOR || resType == ResType.RES)
			{
				resContent.colorRes = resInt;
			}
			else if (resType == ResType.PATH || resType == ResType.URL)
			{
				resContent.resPath = resStr;
			}
		}
		return resContent;
	}
}
