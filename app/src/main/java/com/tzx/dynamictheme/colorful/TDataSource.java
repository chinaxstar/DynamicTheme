package com.tzx.dynamictheme.colorful;

import com.tzx.dynamictheme.theme.ThemeJson;

import java.util.List;

/**
 * Created by xstar on 2016/5/25.
 */
public interface TDataSource
{
	List<ThemeJson> getSource();

	boolean saveSource(List<ThemeJson> themeJsons);
}
