package com.tzx.dynamictheme;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tzx.dynamictheme.colorful.DyThemeHelper;
import com.tzx.dynamictheme.colorful.PreferencesDataSource;
import com.tzx.dynamictheme.colorful.ThemeManager;
import com.tzx.dynamictheme.theme.ThemeJson;

import java.util.List;

public class MainActivity extends Activity
{

	DyThemeHelper	dyThemeHelper;
	ThemeJson		usedTheme;
	List<ThemeJson>	themeJsons;
    ThemeManager themeManager;
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        themeManager=ThemeManager.getInstance();
        themeManager.bindDataSource(new PreferencesDataSource(getApplicationContext()));
		themeJsons = themeManager.getAllThemes();
		usedTheme = themeManager.getUsedTheme();
		dyThemeHelper = new DyThemeHelper.Builder(this).bindThemeManage(themeManager).setViewBgColor(R.id.main, R.attr.bgColor).setTextColor(R.id.content, R.attr.titleColor).setTextColor(R.id.button, R.attr.titleColor).create();
		dyThemeHelper.review();
	}

	public void changeTheme(View view)
	{
		if (view instanceof Button)
		{
			if (usedTheme == null)
			{
				usedTheme = themeJsons.get(0);
			}
			else
			{
				int index = themeJsons.indexOf(usedTheme);
				if (index + 1 == themeJsons.size()) usedTheme = themeJsons.get(0);
				else usedTheme = themeJsons.get(index + 1);
			}
			((Button) view).setText(usedTheme.getThemeName());
			dyThemeHelper.themeChange(usedTheme);
		}
	}

	public void save(View view)
	{
		themeManager.saveTheme();
		startActivity(new Intent(this, ThemeManageActivity.class));
	}

    @Override
    protected void onResume() {
        super.onResume();
        dyThemeHelper.review();
    }
}
