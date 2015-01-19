// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.tjerkw.slideexpandable.library;

import android.app.Activity;
import android.view.View;
import android.widget.ListAdapter;

// Referenced classes of package com.tjerkw.slideexpandable.library:
//            AbstractSlideExpandableListAdapter

public class SlideExpandableListAdapter extends AbstractSlideExpandableListAdapter
{

    public SlideExpandableListAdapter(Activity activity, ListAdapter listadapter, int i, int j)
    {
        super(activity, listadapter);
        toggle_button_id = i;
        expandable_view_id = j;
    }

    public View getExpandToggleButton(View view)
    {
        return view.findViewById(toggle_button_id);
    }

    public View getExpandableView(View view)
    {
        return view.findViewById(expandable_view_id);
    }

    private int expandable_view_id;
    private int toggle_button_id;
}
