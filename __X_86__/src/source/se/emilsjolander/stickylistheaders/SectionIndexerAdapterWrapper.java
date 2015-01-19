// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package se.emilsjolander.stickylistheaders;

import android.content.Context;
import android.widget.SectionIndexer;

// Referenced classes of package se.emilsjolander.stickylistheaders:
//            AdapterWrapper, StickyListHeadersAdapter

class SectionIndexerAdapterWrapper extends AdapterWrapper
    implements SectionIndexer
{

    SectionIndexerAdapterWrapper(Context context, StickyListHeadersAdapter stickylistheadersadapter)
    {
        super(context, stickylistheadersadapter);
        mSectionIndexerDelegate = (SectionIndexer)stickylistheadersadapter;
    }

    public int getPositionForSection(int i)
    {
        return mSectionIndexerDelegate.getPositionForSection(i);
    }

    public int getSectionForPosition(int i)
    {
        return mSectionIndexerDelegate.getSectionForPosition(i);
    }

    public Object[] getSections()
    {
        return mSectionIndexerDelegate.getSections();
    }

    final SectionIndexer mSectionIndexerDelegate;
}
