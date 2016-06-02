package com.tzx.dynamictheme.colorful;

import android.util.TypedValue;
import android.widget.TextView;

import com.tzx.dynamictheme.theme.ResContent;

/**
 * Created by xstar on 2016/5/23.
 */
public class TextColorSetter extends ViewSetter
{
	private TextView mTextView;

	public TextColorSetter(TextView targetView, int resId)
	{
		super(targetView, resId);
		this.mTextView = targetView;
	}

	@Override
	public void setValue(ResContent resContent)
	{
		if (resContent == null)
		{
			TypedValue typedValue = new TypedValue();
			mTextView.getContext().getTheme().resolveAttribute(mAttrResId, typedValue, true);
			mTextView.setTextColor(typedValue.data);
		}
		else
		{
			switch (resContent.resType)
			{
				case RES:
					mTextView.setTextColor(mTextView.getResources().getColor(resContent.colorRes));
					break;
				case COLOR:
					mTextView.setTextColor(resContent.colorRes);
					break;
			}
		}
	}
}
