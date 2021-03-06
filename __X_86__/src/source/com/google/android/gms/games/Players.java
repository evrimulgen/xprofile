// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.games;

import android.content.Intent;
import com.google.android.gms.common.api.*;

// Referenced classes of package com.google.android.gms.games:
//            Player, PlayerBuffer

public interface Players
{
    public static interface LoadPlayersResult
        extends Releasable, Result
    {

        public abstract PlayerBuffer getPlayers();
    }


    public abstract Player getCurrentPlayer(GoogleApiClient googleapiclient);

    public abstract String getCurrentPlayerId(GoogleApiClient googleapiclient);

    public abstract Intent getPlayerSearchIntent(GoogleApiClient googleapiclient);

    public abstract PendingResult loadConnectedPlayers(GoogleApiClient googleapiclient, boolean flag);

    public abstract PendingResult loadInvitablePlayers(GoogleApiClient googleapiclient, int i, boolean flag);

    public abstract PendingResult loadMoreInvitablePlayers(GoogleApiClient googleapiclient, int i);

    public abstract PendingResult loadMoreRecentlyPlayedWithPlayers(GoogleApiClient googleapiclient, int i);

    public abstract PendingResult loadPlayer(GoogleApiClient googleapiclient, String s);

    public abstract PendingResult loadRecentlyPlayedWithPlayers(GoogleApiClient googleapiclient, int i, boolean flag);

    public static final String EXTRA_PLAYER_SEARCH_RESULTS = "player_search_results";
}
