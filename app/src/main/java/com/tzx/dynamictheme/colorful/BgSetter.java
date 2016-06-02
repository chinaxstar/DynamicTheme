package com.tzx.dynamictheme.colorful;

import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.View;

import com.tzx.dynamictheme.theme.ResContent;
import com.tzx.dynamictheme.theme.ResType;

/**
 * Created by xstar on 2016/5/23.
 */
public class BgSetter extends ViewSetter
{
	public BgSetter(View targetView, int resId)
	{
		super(targetView, resId);
	}

	@Override
	public void setValue(ResContent resContent)
	{
		if (resContent == null)
		{
            TypedValue typedValue=new TypedValue();
            mView.getContext().getTheme().resolveAttribute(mAttrResId,typedValue,true);
			mView.setBackgroundColor(typedValue.data);
		}
		else
		{
			switch (resContent.resType)
			{
				case RES:
					mView.setBackgroundResource(resContent.colorRes);
					break;
				case COLOR:
					mView.setBackgroundColor(resContent.colorRes);
					break;
				case PATH:
					Drawable bitmapDrawable = Drawable.createFromPath(resContent.resPath);
					mView.setBackgroundDrawable(bitmapDrawable);
					break;
				case URL:
					break;
			}
		}
	}
}
