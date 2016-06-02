package com.tzx.dynamictheme.colorful;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.tzx.dynamictheme.theme.ResContent;
import com.tzx.dynamictheme.theme.ThemeJson;

/**
 * Created by xstra on 2016/5/23.
 */
public class DyThemeHelper
{
	private Builder builder;

	public DyThemeHelper(Builder builder)
	{
		this.builder = builder;
	}

	public static class Builder
	{
		protected List<ViewSetter>		setterList	= new ArrayList<>();
		protected ThemeManager			themeManager;
		private WeakReference<Activity>	weakReference;

		public Builder(Activity activity)
		{
			weakReference = new WeakReference<>(activity);
		}

		public Builder bindThemeManage(ThemeManager themeManager)
		{
			this.themeManager = themeManager;
			return this;
		}

		public Builder setViewBgColor(View view, int resid)
		{
			if (view != null) setterList.add(new BgSetter(view, resid));
			return this;
		}

		public Builder setViewBgColor(int viewid, int resid)
		{
			View view = getView(viewid);
			return setViewBgColor(view, resid);
		}

		public Builder setTextColor(TextView view, int resid)
		{
			if (view != null) setterList.add(new TextColorSetter(view, resid));
			return this;
		}

		public Builder setTextColor(int viewid, int resid)
		{
			TextView view = getView(viewid);
			return setTextColor(view, resid);
		}

		public Builder setViewSetter(ViewSetter viewSetter)
		{
			if (viewSetter != null) setterList.add(viewSetter);
			return this;
		}

		private <T> T getView(int resid)
		{
			T t = null;
			Activity activity = weakReference.get();
			if (activity != null) t = (T) activity.findViewById(resid);
			return t;
		}

		public DyThemeHelper create()
		{
			return new DyThemeHelper(this);
		}
	}

	public void review()
	{
		Activity activity = builder.weakReference.get();
		activity.runOnUiThread(new Runnable()
		{
			@Override
			public void run()
			{
				ResContent resContent = null;
				ThemeJson themeJson = builder.themeManager.getUsedTheme();
				for (ViewSetter setter : builder.setterList)
				{
					if (!setter.isViewNotFound())
					{
						resContent = null;
						if (themeJson != null) resContent = themeJson.getResContent(setter.getResID());
						setter.setValue(resContent);
					}
				}
			}
		});
	}

	public void themeChange(ThemeJson themeJson)
	{
		if (builder.themeManager != null) builder.themeManager.changeUsedTheme(themeJson);
		review();
	}
}
