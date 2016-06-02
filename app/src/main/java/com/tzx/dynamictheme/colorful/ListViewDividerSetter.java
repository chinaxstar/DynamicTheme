package com.tzx.dynamictheme.colorful;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.widget.ListView;

import com.tzx.dynamictheme.theme.ResContent;

/**
 * Created by Administrator on 2016/5/25.
 */
public class ListViewDividerSetter extends ViewSetter {
    private ListView listView;
    public ListViewDividerSetter(ListView targetView, int resId) {
        super(targetView, resId);
        this.listView =targetView;
    }

    @Override
    public void setValue(ResContent resContent) {
        if (resContent == null)
        {
            TypedValue typedValue = new TypedValue();
            mView.getContext().getTheme().resolveAttribute(mAttrResId, typedValue, true);
            mView.setBackgroundColor(typedValue.data);
        }
        else
        {
            switch (resContent.resType)
            {
                case RES:
                    listView.setDivider(new ColorDrawable(listView.getResources().getColor(resContent.colorRes)));
                    break;
                case COLOR:
                    listView.setDivider(new ColorDrawable(resContent.colorRes));
                    break;
                case PATH:
                    Drawable bitmapDrawable = Drawable.createFromPath(resContent.resPath);
                    listView.setDivider(bitmapDrawable);
                    break;
                case URL:
                    break;
            }
        }
    }
}
