package com.tzx.dynamictheme;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.tzx.dynamictheme.colorful.DyThemeHelper;
import com.tzx.dynamictheme.colorful.ItemTextColorSetter;
import com.tzx.dynamictheme.colorful.ListViewDividerSetter;
import com.tzx.dynamictheme.colorful.ListViewSelectorSetter;
import com.tzx.dynamictheme.colorful.ThemeManager;
import com.tzx.dynamictheme.theme.ThemeJson;

import java.util.ArrayList;
import java.util.List;

public class ThemeManageActivity extends Activity
{

	ListView				theme_list_lv;
	ArrayAdapter<String>	adapter;
	List<ThemeJson>			themeJsonList;
	List<String>			names	= new ArrayList<>();
	DyThemeHelper			dyThemeHelper;
	ThemeManager			themeManager;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_theme_manage);
		themeManager = ThemeManager.getInstance();
		themeJsonList = themeManager.getAllThemes();
		for (int i = 0; i < themeJsonList.size(); i++)
		{
			names.add(themeJsonList.get(i).getThemeName() + "");
		}
		theme_list_lv = (ListView) findViewById(R.id.theme_list_lv);
		adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, names);
		theme_list_lv.setAdapter(adapter);
		theme_list_lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id)
			{
				dyThemeHelper.themeChange(themeJsonList.get(position));
			}
		});
		DyThemeHelper.Builder builder = new DyThemeHelper.Builder(this);
		builder.setViewBgColor(R.id.main, R.attr.bgColor).bindThemeManage(themeManager);
		builder.setViewSetter(new ListViewSelectorSetter(theme_list_lv, R.attr.listSelectColor));
		builder.setViewSetter(new ListViewDividerSetter(theme_list_lv, R.attr.listDividerColor));
		builder.setViewSetter(new ItemTextColorSetter(theme_list_lv, android.R.id.text1, R.attr.titleColor));
		dyThemeHelper = builder.create();
	}
}
