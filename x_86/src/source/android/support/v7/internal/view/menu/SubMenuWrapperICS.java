// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package android.support.v7.internal.view.menu;

import android.graphics.drawable.Drawable;
import android.support.v4.internal.view.SupportSubMenu;
import android.view.*;

// Referenced classes of package android.support.v7.internal.view.menu:
//            MenuWrapperICS

class SubMenuWrapperICS extends MenuWrapperICS
    implements SupportSubMenu
{

    SubMenuWrapperICS(SubMenu submenu)
    {
        super(submenu);
    }

    public void clearHeader()
    {
        ((SubMenu)mWrappedObject).clearHeader();
    }

    public MenuItem getItem()
    {
        return getMenuItemWrapper(((SubMenu)mWrappedObject).getItem());
    }

    public SubMenu getWrappedObject()
    {
        return (SubMenu)mWrappedObject;
    }

    public volatile Object getWrappedObject()
    {
        return getWrappedObject();
    }

    public SubMenu setHeaderIcon(int i)
    {
        ((SubMenu)mWrappedObject).setHeaderIcon(i);
        return this;
    }

    public SubMenu setHeaderIcon(Drawable drawable)
    {
        ((SubMenu)mWrappedObject).setHeaderIcon(drawable);
        return this;
    }

    public SubMenu setHeaderTitle(int i)
    {
        ((SubMenu)mWrappedObject).setHeaderTitle(i);
        return this;
    }

    public SubMenu setHeaderTitle(CharSequence charsequence)
    {
        ((SubMenu)mWrappedObject).setHeaderTitle(charsequence);
        return this;
    }

    public SubMenu setHeaderView(View view)
    {
        ((SubMenu)mWrappedObject).setHeaderView(view);
        return this;
    }

    public SubMenu setIcon(int i)
    {
        ((SubMenu)mWrappedObject).setIcon(i);
        return this;
    }

    public SubMenu setIcon(Drawable drawable)
    {
        ((SubMenu)mWrappedObject).setIcon(drawable);
        return this;
    }
}
