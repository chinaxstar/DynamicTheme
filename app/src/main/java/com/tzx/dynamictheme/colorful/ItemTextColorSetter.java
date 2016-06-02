package com.tzx.dynamictheme.colorful;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.tzx.dynamictheme.theme.ResContent;

/**
 * Created by xstar on 2016/5/27.
 */
public class ItemTextColorSetter extends ViewSetter
{
	private ListView	listView;
	private int			viewID;

	public ItemTextColorSetter(ListView listView, int viewID, int resId)
	{
		super(listView, resId);
		this.listView = listView;
		this.viewID = viewID;
	}

	@Override
	public void setValue(ResContent resContent)
	{
		for (int i = 0; i < listView.getChildCount(); i++)
		{
			TextView textView = (TextView) listView.getChildAt(i).findViewById(viewID);
			if (textView != null)
			{
				new TextColorSetter(textView, mAttrResId).setValue(resContent);
			}
		}
	}
}
