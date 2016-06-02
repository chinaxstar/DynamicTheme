package com.tzx.dynamictheme.theme;

import android.util.Log;

import java.util.Hashtable;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xstar on 2016/5/23.
 */
public class ThemeJson
{
	public ThemeJson()
	{
		themeID = UUID.randomUUID().toString();
	}

	private String						themeID;							// 主题id
	private String						themeName;							// 自定义主题名称
	private Map<Integer, ResContent>	viewResMap	= new Hashtable<>();	// 资源ID
	// 资源内容
	private boolean						isUsed		= false;

	public ThemeJson addResContent(int attr, ResContent resContent)
	{
		if (resContent != null) viewResMap.put(attr, resContent);
		else Log.e("bindResContent", "资源类型不能为空。");
		return this;
	}

	public String getThemeID()
	{
		return themeID;
	}

	public String getThemeName()
	{
		return themeName;
	}

	public void setThemeName(String themeName)
	{
		this.themeName = themeName;
	}

	public Map<Integer, ResContent> getViewResMap()
	{
		return viewResMap;
	}

	public void setViewResMap(Map<Integer, ResContent> viewResMap)
	{
		this.viewResMap = viewResMap;
	}

	public boolean isUsed()
	{
		return isUsed;
	}

	public void setUsed(boolean used)
	{
		isUsed = used;
	}

	public ResContent getResContent(int resID)
	{
		return viewResMap.get(resID);
	}
}
