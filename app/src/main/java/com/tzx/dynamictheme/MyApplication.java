package com.tzx.dynamictheme;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Color;

import com.tzx.dynamictheme.colorful.PreferencesDataSource;
import com.tzx.dynamictheme.colorful.ThemeManager;
import com.tzx.dynamictheme.theme.ResType;
import com.tzx.dynamictheme.theme.ThemeJson;

/**
 * Created by Administrator on 2016/5/25.
 */
public class MyApplication extends Application
{
	private static final String FIRST = "FIRST";

	@Override
	public void onCreate()
	{
		super.onCreate();
		SharedPreferences sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
		boolean first = sharedPreferences.getBoolean(FIRST, true);
		if (first) initTheme();
		sharedPreferences.edit().putBoolean(FIRST, false).apply();
	}

	ThemeManager themeManager;

	public void initTheme()
	{
		themeManager = ThemeManager.getInstance();
		themeManager.bindDataSource(new PreferencesDataSource(getApplicationContext()));
		ThemeJson white = new ThemeJson();
		white.setThemeName("白底黑字");
		white.addResContent(R.attr.bgColor, ThemeManager.createResContent(ResType.RES, R.color.white, null)).addResContent(R.attr.titleColor, ThemeManager.createResContent(ResType.RES, R.color.colorAccent, null))
				.addResContent(R.attr.listSelectColor, ThemeManager.createResContent(ResType.COLOR, Color.RED, null)).addResContent(R.attr.listDividerColor, ThemeManager.createResContent(ResType.COLOR, Color.BLACK, null));
		ThemeJson black = new ThemeJson();
		black.setThemeName("黑底白字");
		black.addResContent(R.attr.bgColor, ThemeManager.createResContent(ResType.RES, R.color.black, null)).addResContent(R.attr.titleColor, ThemeManager.createResContent(ResType.COLOR, Color.WHITE, null))
				.addResContent(R.attr.listSelectColor, ThemeManager.createResContent(ResType.COLOR, Color.BLUE, null)).addResContent(R.attr.listDividerColor, ThemeManager.createResContent(ResType.COLOR, Color.GREEN, null));
		ThemeJson imgBg = new ThemeJson();
		imgBg.setThemeName("图片背景白字");
		imgBg.addResContent(R.attr.bgColor, ThemeManager.createResContent(ResType.PATH, 0, "/mnt/internal_sd/Download/ship.jpg")).addResContent(R.attr.titleColor, ThemeManager.createResContent(ResType.COLOR, Color.BLACK, null))
				.addResContent(R.attr.listSelectColor, ThemeManager.createResContent(ResType.COLOR, Color.YELLOW, null)).addResContent(R.attr.listDividerColor, ThemeManager.createResContent(ResType.COLOR, Color.CYAN, null));
		themeManager.addNewTheme(white);
		themeManager.addNewTheme(black);
		themeManager.addNewTheme(imgBg);
		themeManager.saveTheme();
	}

}
