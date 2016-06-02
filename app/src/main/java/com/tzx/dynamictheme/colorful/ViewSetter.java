package com.tzx.dynamictheme.colorful;

import android.content.res.Resources.Theme;
import android.util.TypedValue;
import android.view.View;

import com.tzx.dynamictheme.theme.ResContent;

/**
 * Created by xstar on 2016/5/19.
 */
public abstract class ViewSetter
{
	/**
	 * 目标View
	 */
	View	mView;
	/**
	 * 目标View要的特定属性id
	 */
	int	mAttrResId;

	public ViewSetter(View targetView, int resId)
	{
		mView = targetView;
		mAttrResId = resId;
	}

	public abstract void setValue(ResContent resContent);

	/**
	 * 获取视图的Id
	 *
	 * @return
	 */
	protected int getViewId()
	{
		return mView != null ? mView.getId() : -1;
	}
	protected int getResID()
	{
		return mAttrResId;
	}

	protected boolean isViewNotFound()
	{
		return mView == null;
	}

}
