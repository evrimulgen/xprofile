// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.internal.view.menu;

import android.support.v4.internal.view.SupportMenuItem;
import android.view.MenuItem;
import android.view.SubMenu;
import java.util.*;

// Referenced classes of package android.support.v7.internal.view.menu:
//            BaseWrapper, MenuWrapperFactory

abstract class BaseMenuWrapper extends BaseWrapper
{

    BaseMenuWrapper(Object obj)
    {
        super(obj);
    }

    final SupportMenuItem getMenuItemWrapper(MenuItem menuitem)
    {
        if(menuitem != null)
        {
            if(mMenuItems == null)
                mMenuItems = new HashMap();
            SupportMenuItem supportmenuitem = (SupportMenuItem)mMenuItems.get(menuitem);
            if(supportmenuitem == null)
            {
                supportmenuitem = MenuWrapperFactory.createSupportMenuItemWrapper(menuitem);
                mMenuItems.put(menuitem, supportmenuitem);
            }
            return supportmenuitem;
        } else
        {
            return null;
        }
    }

    final SubMenu getSubMenuWrapper(SubMenu submenu)
    {
        if(submenu != null)
        {
            if(mSubMenus == null)
                mSubMenus = new HashMap();
            Object obj = (SubMenu)mSubMenus.get(submenu);
            if(obj == null)
            {
                obj = MenuWrapperFactory.createSupportSubMenuWrapper(submenu);
                mSubMenus.put(submenu, obj);
            }
            return ((SubMenu) (obj));
        } else
        {
            return null;
        }
    }

    final void internalClear()
    {
        if(mMenuItems != null)
            mMenuItems.clear();
        if(mSubMenus != null)
            mSubMenus.clear();
    }

    final void internalRemoveGroup(int i)
    {
        if(mMenuItems != null)
        {
            Iterator iterator = mMenuItems.keySet().iterator();
            while(iterator.hasNext()) 
                if(i == ((MenuItem)iterator.next()).getGroupId())
                    iterator.remove();
        }
    }

    final void internalRemoveItem(int i)
    {
        if(mMenuItems != null)
        {
            Iterator iterator = mMenuItems.keySet().iterator();
            while(iterator.hasNext()) 
                if(i == ((MenuItem)iterator.next()).getItemId())
                {
                    iterator.remove();
                    return;
                }
        }
    }

    private HashMap mMenuItems;
    private HashMap mSubMenus;
}
