// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.adapters;

import android.app.Activity;
import android.view.*;
import android.widget.*;
import butterknife.ButterKnife;
import com.roadtrippers.RoadTrippersApp;
import com.roadtrippers.adapters.base.InflaterAdapter;
import com.roadtrippers.api.models.Category;
import com.roadtrippers.api.models.Group;
import com.roadtrippers.events.*;
import com.roadtrippers.util.RTAnalytics;
import com.squareup.otto.Bus;
import dagger.Lazy;
import io.segment.android.models.Props;
import java.util.*;

public class CategoriesAdapter extends InflaterAdapter
{
    class InnerViewHolder
        implements android.view.View.OnClickListener
    {

        private void drawCheckedState()
        {
            if(category.checked)
            {
                setChecked();
                return;
            } else
            {
                setUnchecked();
                return;
            }
        }

        void check()
        {
            setChecked();
            category.checked = true;
        }

        public void onClick(View view)
        {
            Category category1 = category;
            boolean flag;
            if(!category.checked)
                flag = true;
            else
                flag = false;
            category1.checked = flag;
            drawCheckedState();
            ((Bus)bus.get()).post(this);
        }

        void setCategory(Group group1, Category category1)
        {
            group = group1;
            category = category1;
            title.setText(category1.name.toLowerCase());
            drawCheckedState();
        }

        void setChecked()
        {
            smallCheckmark.setColorFilter(group.getColor(smallCheckmark.getContext()));
            indicator.setBackgroundResource(group.getColorRes(indicator.getContext()));
            InflaterAdapter.setVisibility(smallCheckmark, 0);
            InflaterAdapter.setVisibility(indicator, 0);
        }

        void setUnchecked()
        {
            InflaterAdapter.setVisibility(smallCheckmark, 4);
            InflaterAdapter.setVisibility(indicator, 4);
        }

        void unCheck()
        {
            setUnchecked();
            category.checked = false;
        }

        Category category;
        Group group;
        View indicator;
        ImageView smallCheckmark;
        final CategoriesAdapter this$0;
        TextView title;

        InnerViewHolder(View view)
        {
            this$0 = CategoriesAdapter.this;
            super();
            ButterKnife.inject(this, view);
        }
    }

    class ViewHolder
    {

        void drawCheckedState()
        {
            if(group.checked)
            {
                setChecked();
                return;
            } else
            {
                setUnChecked();
                return;
            }
        }

        void drawExpandedState()
        {
            ImageView imageview = arrowOpenClose;
            int i;
            if(group.expanded)
                i = 0x7f020070;
            else
                i = 0x7f02006f;
            imageview.setImageResource(i);
        }

        public void onCategoryClick(InnerViewHolder innerviewholder)
        {
            if(!innerviewholder.group.equals(group))
                return;
            Category acategory[] = group.categories;
            int i = acategory.length;
            int j = 0;
            do
            {
label0:
                {
                    boolean flag = false;
                    if(j < i)
                    {
                        if(!acategory[j].checked)
                            break label0;
                        flag = true;
                    }
                    group.checked = flag;
                    drawCheckedState();
                    Props props = new Props();
                    props.put("label", innerviewholder.category.name);
                    if(group.name != null)
                    {
                        RTAnalytics.logEvent(groupView.getContext(), 0x7f0c0067, 0x7f0c0015, props);
                        ((Bus)bus.get()).post(new GroupCheck(groups));
                        return;
                    } else
                    {
                        RTAnalytics.logEvent(groupView.getContext(), 0x7f0c0066, 0x7f0c0012, props);
                        ((Bus)bus.get()).post(new BucketsChangedEvent(acategory));
                        return;
                    }
                }
                j++;
            } while(true);
        }

        public void onCheckMarkClick()
        {
            Category acategory[] = group.categories;
            int i = 0;
            while(i < acategory.length) 
            {
                InnerViewHolder innerviewholder = (InnerViewHolder)categoriesLayout.getChildAt(i).getTag();
                if(group.checked)
                    innerviewholder.unCheck();
                else
                    innerviewholder.check();
                i++;
            }
            boolean flag;
            if(!group.checked)
                flag = true;
            else
                flag = false;
            group.checked = flag;
            drawCheckedState();
            if(group.name != null)
            {
                ((Bus)bus.get()).post(new GroupCheck(groups));
                return;
            } else
            {
                ((Bus)bus.get()).post(new BucketsChangedEvent(acategory));
                return;
            }
        }

        public void onGroupClick(GroupExpand groupexpand)
        {
label0:
            {
                if(group != null)
                {
                    if(groupexpand.view == groupView && !group.expanded)
                        group.expanded = true;
                    else
                        group.expanded = false;
                    drawExpandedState();
                    if(group.name != null)
                        break label0;
                    RTAnalytics.logEvent(groupexpand.view.getContext(), 0x7f0c0066, 0x7f0c0011);
                }
                return;
            }
            Props props = new Props();
            props.put("label", group.name.toLowerCase());
            RTAnalytics.logEvent(groupexpand.view.getContext(), 0x7f0c0067, 0x7f0c0013, props);
        }

        void release()
        {
            ((Bus)bus.get()).unregister(this);
            ButterKnife.reset(this);
        }

        void setChecked()
        {
            checkmark.setColorFilter(group.getColor(checkmark.getContext()));
            rightFrame.setBackgroundResource(group.getColorRes(rightFrame.getContext()));
            icon.setColorFilter(-1);
        }

        void setGroup(Group group1)
        {
            group = group1;
            if(group1.name != null)
                groupName.setText(group1.name.toUpperCase());
            Category acategory[] = group1.categories;
            for(int i = 0; i < acategory.length; i++)
            {
                View view = categoriesLayout.getChildAt(i);
                InflaterAdapter.setVisibility(view, 0);
                ((InnerViewHolder)view.getTag()).setCategory(group1, acategory[i]);
            }

            for(int j = acategory.length; j < categoriesLayout.getChildCount(); j++)
                InflaterAdapter.setVisibility(categoriesLayout.getChildAt(j), 8);

            icon.setImageResource(group1.getIconRes(icon.getContext()));
            drawExpandedState();
            drawCheckedState();
        }

        void setUnChecked()
        {
            checkmark.setColorFilter(null);
            if(group.name != null)
                rightFrame.setBackgroundResource(0x7f08003d);
            else
                rightFrame.setBackgroundResource(0x7f0200e0);
            icon.setColorFilter(group.getColor(checkmark.getContext()));
        }

        ImageView arrowOpenClose;
        LinearLayout categoriesLayout;
        ImageView checkmark;
        Group group;
        TextView groupName;
        View groupView;
        ImageView icon;
        View rightFrame;
        final CategoriesAdapter this$0;

        ViewHolder(View view)
        {
            this$0 = CategoriesAdapter.this;
            super();
            ButterKnife.inject(this, view);
            for(int i = 0; i < getMaxCategoryCount(); i++)
            {
                View view1 = 
// JavaClassFileOutputException: get_constant: invalid tag
    }


    public CategoriesAdapter(Activity activity, Group agroup[])
    {
        InflaterAdapter(activity);
        RoadTrippersApp.from(activity).inject(this);
        groups = agroup;
    }

    public int getCount()
    {
        if(groups != null)
            return groups.length;
        else
            return 0;
    }

    public Group getItem(int i)
    {
        return groups[i];
    }

    public volatile Object getItem(int i)
    {
        return getItem(i);
    }

    public int getItemViewType(int i)
    {
        return getItem(i).name != null ? 1 : 0;
    }

    public int getMaxCategoryCount()
    {
        if(maxCategoryCount == 0)
        {
            Group agroup[] = groups;
            int i = agroup.length;
            for(int j = 0; j < i; j++)
            {
                Group group = agroup[j];
                maxCategoryCount = Math.max(maxCategoryCount, group.categories.length);
            }

        }
        return maxCategoryCount;
    }

    public View getView(int i, View view, ViewGroup viewgroup)
    {
        if(view == null)
        {
            LayoutInflater layoutinflater = inflater;
            int j;
            ViewHolder viewholder;
            if(getItemViewType(i) == 0)
                j = 0x7f03001f;
            else
                j = 0x7f030044;
            view = layoutinflater.inflate(j, viewgroup, false);
            viewholder = new ViewHolder(view);
            view.setTag(viewholder);
            holders.add(viewholder);
        }
        ((ViewHolder)view.getTag()).setGroup(getItem(i));
        return view;
    }

    public int getViewTypeCount()
    {
        return 2;
    }

    public boolean isEnabled(int i)
    {
        return false;
    }

    public void release()
    {
        for(Iterator iterator = holders.iterator(); iterator.hasNext(); ((ViewHolder)iterator.next()).release());
        holders.clear();
    }

    Lazy bus;
    final Group groups[];
    final List holders = new ArrayList();
    int maxCategoryCount;

}
