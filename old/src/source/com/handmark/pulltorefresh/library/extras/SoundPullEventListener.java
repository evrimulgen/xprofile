// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.handmark.pulltorefresh.library.extras;

import android.content.Context;
import android.media.MediaPlayer;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import java.util.HashMap;

public class SoundPullEventListener
    implements com.handmark.pulltorefresh.library.PullToRefreshBase.OnPullEventListener
{

    public SoundPullEventListener(Context context)
    {
        mContext = context;
    }

    private void playSound(int i)
    {
        if(mCurrentMediaPlayer != null)
        {
            mCurrentMediaPlayer.stop();
            mCurrentMediaPlayer.release();
        }
        mCurrentMediaPlayer = MediaPlayer.create(mContext, i);
        if(mCurrentMediaPlayer != null)
            mCurrentMediaPlayer.start();
    }

    public void addSoundEvent(com.handmark.pulltorefresh.library.PullToRefreshBase.State state, int i)
    {
        mSoundMap.put(state, Integer.valueOf(i));
    }

    public void clearSounds()
    {
        mSoundMap.clear();
    }

    public MediaPlayer getCurrentMediaPlayer()
    {
        return mCurrentMediaPlayer;
    }

    public final void onPullEvent(PullToRefreshBase pulltorefreshbase, com.handmark.pulltorefresh.library.PullToRefreshBase.State state, com.handmark.pulltorefresh.library.PullToRefreshBase.Mode mode)
    {
        Integer integer = (Integer)mSoundMap.get(state);
        if(integer != null)
            playSound(integer.intValue());
    }

    private final Context mContext;
    private MediaPlayer mCurrentMediaPlayer;
    private final HashMap mSoundMap = new HashMap();
}
