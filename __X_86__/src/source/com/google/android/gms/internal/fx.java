// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.android.gms.internal;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.net.LocalSocket;
import android.net.LocalSocketAddress;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.games.Game;
import com.google.android.gms.games.GameBuffer;
import com.google.android.gms.games.GameEntity;
import com.google.android.gms.games.Player;
import com.google.android.gms.games.PlayerBuffer;
import com.google.android.gms.games.PlayerEntity;
import com.google.android.gms.games.achievement.AchievementBuffer;
import com.google.android.gms.games.leaderboard.Leaderboard;
import com.google.android.gms.games.leaderboard.LeaderboardBuffer;
import com.google.android.gms.games.leaderboard.LeaderboardScore;
import com.google.android.gms.games.leaderboard.LeaderboardScoreBuffer;
import com.google.android.gms.games.leaderboard.ScoreSubmissionData;
import com.google.android.gms.games.leaderboard.c;
import com.google.android.gms.games.multiplayer.Invitation;
import com.google.android.gms.games.multiplayer.InvitationBuffer;
import com.google.android.gms.games.multiplayer.OnInvitationReceivedListener;
import com.google.android.gms.games.multiplayer.ParticipantResult;
import com.google.android.gms.games.multiplayer.ParticipantUtils;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessage;
import com.google.android.gms.games.multiplayer.realtime.RealTimeMessageReceivedListener;
import com.google.android.gms.games.multiplayer.realtime.RealTimeSocket;
import com.google.android.gms.games.multiplayer.realtime.Room;
import com.google.android.gms.games.multiplayer.realtime.RoomConfig;
import com.google.android.gms.games.multiplayer.realtime.RoomEntity;
import com.google.android.gms.games.multiplayer.realtime.RoomStatusUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.RoomUpdateListener;
import com.google.android.gms.games.multiplayer.realtime.a;
import com.google.android.gms.games.multiplayer.turnbased.LoadMatchesResponse;
import com.google.android.gms.games.multiplayer.turnbased.OnTurnBasedMatchUpdateReceivedListener;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatch;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchBuffer;
import com.google.android.gms.games.multiplayer.turnbased.TurnBasedMatchConfig;
import com.google.android.gms.games.request.GameRequest;
import com.google.android.gms.games.request.GameRequestBuffer;
import com.google.android.gms.games.request.OnRequestReceivedListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.android.gms.internal:
//            eh, er, gd, gb, 
//            fz, gc, ge, en, 
//            fw, gs, hb

public final class fx extends eh
    implements com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener
{
    abstract class a extends c
    {

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            a(roomstatusupdatelistener, room, GI);
        }

        protected abstract void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist);

        private final ArrayList GI = new ArrayList();
        final fx GJ;

        a(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as1[])
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder);
            int i1 = 0;
            for(int j1 = as1.length; i1 < j1; i1++)
                GI.add(as1[i1]);

        }
    }

    final class aa extends eh.b
    {

        public void a(RoomStatusUpdateListener roomstatusupdatelistener)
        {
            if(roomstatusupdatelistener != null)
                roomstatusupdatelistener.onP2PConnected(GZ);
        }

        public volatile void a(Object obj)
        {
            a((RoomStatusUpdateListener)obj);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final String GZ;

        aa(RoomStatusUpdateListener roomstatusupdatelistener, String s1)
        {
            GJ = fx.this;
            super(fx.this, roomstatusupdatelistener);
            GZ = s1;
        }
    }

    final class ab extends eh.b
    {

        public void a(RoomStatusUpdateListener roomstatusupdatelistener)
        {
            if(roomstatusupdatelistener != null)
                roomstatusupdatelistener.onP2PDisconnected(GZ);
        }

        public volatile void a(Object obj)
        {
            a((RoomStatusUpdateListener)obj);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final String GZ;

        ab(RoomStatusUpdateListener roomstatusupdatelistener, String s1)
        {
            GJ = fx.this;
            super(fx.this, roomstatusupdatelistener);
            GZ = s1;
        }
    }

    final class ac extends a
    {

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeersConnected(room, arraylist);
        }

        final fx GJ;

        ac(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as1[])
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder, as1);
        }
    }

    final class ad extends a
    {

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeerDeclined(room, arraylist);
        }

        final fx GJ;

        ad(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as1[])
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder, as1);
        }
    }

    final class ae extends a
    {

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeersDisconnected(room, arraylist);
        }

        final fx GJ;

        ae(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as1[])
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder, as1);
        }
    }

    final class af extends a
    {

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeerInvitedToRoom(room, arraylist);
        }

        final fx GJ;

        af(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as1[])
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder, as1);
        }
    }

    final class ag extends a
    {

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeerJoined(room, arraylist);
        }

        final fx GJ;

        ag(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as1[])
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder, as1);
        }
    }

    final class ah extends a
    {

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room, ArrayList arraylist)
        {
            roomstatusupdatelistener.onPeerLeft(room, arraylist);
        }

        final fx GJ;

        ah(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder, String as1[])
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder, as1);
        }
    }

    final class ai extends fw
    {

        public void C(DataHolder dataholder)
        {
            GJ.a(GJ. new aj(vj, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        ai(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class aj extends eh.d
        implements com.google.android.gms.games.leaderboard.Leaderboards.LoadPlayerScoreResult
    {

        protected void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            c1.b(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public LeaderboardScore getScore()
        {
            return Ha;
        }

        public Status getStatus()
        {
            return vl;
        }

        final fx GJ;
        private final com.google.android.gms.games.leaderboard.d Ha = null;
        private final Status vl;

        aj(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            LeaderboardScoreBuffer leaderboardscorebuffer;
            GJ = fx.this;
            super(fx.this, c1, dataholder);
            vl = new Status(dataholder.getStatusCode());
            leaderboardscorebuffer = new LeaderboardScoreBuffer(dataholder);
            if(leaderboardscorebuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            Ha = (com.google.android.gms.games.leaderboard.d)leaderboardscorebuffer.get(0).freeze();
_L4:
            leaderboardscorebuffer.close();
            return;
_L2:
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            leaderboardscorebuffer.close();
            throw exception;
        }
    }

    final class ak extends fw
    {

        public void e(DataHolder dataholder)
        {
            GJ.a(GJ. new al(vj, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        ak(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class al extends av
        implements com.google.android.gms.games.Players.LoadPlayersResult
    {

        protected void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            c1.b(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public PlayerBuffer getPlayers()
        {
            return Hb;
        }

        final fx GJ;
        private final PlayerBuffer Hb;

        al(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
            Hb = new PlayerBuffer(dataholder);
        }
    }

    final class am extends eh.b
    {

        public void a(com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback reliablemessagesentcallback)
        {
            if(reliablemessagesentcallback != null)
                reliablemessagesentcallback.onRealTimeMessageSent(yJ, Hd, Hc);
        }

        public volatile void a(Object obj)
        {
            a((com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback)obj);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final String Hc;
        private final int Hd;
        private final int yJ;

        am(com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback reliablemessagesentcallback, int i1, int j1, String s1)
        {
            GJ = fx.this;
            super(fx.this, reliablemessagesentcallback);
            yJ = i1;
            Hd = j1;
            Hc = s1;
        }
    }

    final class an extends fw
    {

        public void b(int i1, int j1, String s1)
        {
            GJ.a(GJ. new am(He, i1, j1, s1));
        }

        final fx GJ;
        final com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback He;

        public an(com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback reliablemessagesentcallback)
        {
            GJ = fx.this;
            super();
            He = reliablemessagesentcallback;
        }
    }

    final class ao extends fw
    {

        public void m(DataHolder dataholder)
        {
            GameRequestBuffer gamerequestbuffer = new GameRequestBuffer(dataholder);
            int i1 = gamerequestbuffer.getCount();
            GameRequest gamerequest;
            gamerequest = null;
            if(i1 <= 0)
                break MISSING_BLOCK_LABEL_41;
            gamerequest = (GameRequest)((GameRequest)gamerequestbuffer.get(0)).freeze();
            gamerequestbuffer.close();
            if(gamerequest != null)
                GJ.a(GJ. new ap(Hf, gamerequest));
            return;
            Exception exception;
            exception;
            gamerequestbuffer.close();
            throw exception;
        }

        public void onRequestRemoved(String s1)
        {
            GJ.a(GJ. new aq(Hf, s1));
        }

        final fx GJ;
        private final OnRequestReceivedListener Hf;

        ao(OnRequestReceivedListener onrequestreceivedlistener)
        {
            GJ = fx.this;
            super();
            Hf = onrequestreceivedlistener;
        }
    }

    final class ap extends eh.b
    {

        protected void a(Object obj)
        {
            b((OnRequestReceivedListener)obj);
        }

        protected void b(OnRequestReceivedListener onrequestreceivedlistener)
        {
            onrequestreceivedlistener.onRequestReceived(Hg);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final GameRequest Hg;

        ap(OnRequestReceivedListener onrequestreceivedlistener, GameRequest gamerequest)
        {
            GJ = fx.this;
            super(fx.this, onrequestreceivedlistener);
            Hg = gamerequest;
        }
    }

    final class aq extends eh.b
    {

        protected void a(Object obj)
        {
            b((OnRequestReceivedListener)obj);
        }

        protected void b(OnRequestReceivedListener onrequestreceivedlistener)
        {
            onrequestreceivedlistener.onRequestRemoved(Hh);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final String Hh;

        aq(OnRequestReceivedListener onrequestreceivedlistener, String s1)
        {
            GJ = fx.this;
            super(fx.this, onrequestreceivedlistener);
            Hh = s1;
        }
    }

    final class ar extends fw
    {

        public void b(int i1, Bundle bundle)
        {
            bundle.setClassLoader(getClass().getClassLoader());
            Status status = new Status(i1);
            GJ.a(GJ. new as(Hi, status, bundle));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c Hi;

        public ar(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            Hi = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class as extends eh.b
        implements com.google.android.gms.games.request.Requests.LoadRequestsResult
    {

        protected void a(Object obj)
        {
            c((com.google.android.gms.common.api.a.c)obj);
        }

        protected void c(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        protected void cP()
        {
            release();
        }

        public GameRequestBuffer getRequests(int i1)
        {
            String s1 = gs.aW(i1);
            if(!Hj.containsKey(s1))
                return null;
            else
                return new GameRequestBuffer((DataHolder)Hj.get(s1));
        }

        public Status getStatus()
        {
            return vl;
        }

        public void release()
        {
            Iterator iterator = Hj.keySet().iterator();
            do
            {
                if(!iterator.hasNext())
                    break;
                String s1 = (String)iterator.next();
                DataHolder dataholder = (DataHolder)Hj.getParcelable(s1);
                if(dataholder != null)
                    dataholder.close();
            } while(true);
        }

        final fx GJ;
        private final Bundle Hj;
        private final Status vl;

        as(com.google.android.gms.common.api.a.c c1, Status status, Bundle bundle)
        {
            GJ = fx.this;
            super(fx.this, c1);
            vl = status;
            Hj = bundle;
        }
    }

    final class at extends fw
    {

        public void D(DataHolder dataholder)
        {
            GJ.a(GJ. new au(Hk, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c Hk;

        public at(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            Hk = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class au extends av
        implements com.google.android.gms.games.request.Requests.UpdateRequestsResult
    {

        protected void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            c1.b(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public Set getRequestIds()
        {
            return Hl.getRequestIds();
        }

        public int getRequestOutcome(String s1)
        {
            return Hl.getRequestOutcome(s1);
        }

        final fx GJ;
        private final hb Hl;

        au(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
            Hl = hb.H(dataholder);
        }
    }

    abstract class av extends eh.d
        implements Releasable, Result
    {

        public Status getStatus()
        {
            return vl;
        }

        public void release()
        {
            if(zU != null)
                zU.close();
        }

        final fx GJ;
        final Status vl;
        final DataHolder zU;

        public av(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(fx.this, c1, dataholder);
            vl = new Status(dataholder.getStatusCode());
            zU = dataholder;
        }
    }

    final class aw extends c
    {

        public void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            roomstatusupdatelistener.onRoomAutoMatching(room);
        }

        final fx GJ;

        aw(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder);
        }
    }

    final class ax extends fw
    {

        public void a(DataHolder dataholder, String as1[])
        {
            GJ.a(GJ. new af(Hn, dataholder, as1));
        }

        public void b(DataHolder dataholder, String as1[])
        {
            GJ.a(GJ. new ag(Hn, dataholder, as1));
        }

        public void c(DataHolder dataholder, String as1[])
        {
            GJ.a(GJ. new ah(Hn, dataholder, as1));
        }

        public void d(DataHolder dataholder, String as1[])
        {
            GJ.a(GJ. new ad(Hn, dataholder, as1));
        }

        public void e(DataHolder dataholder, String as1[])
        {
            GJ.a(GJ. new ac(Hn, dataholder, as1));
        }

        public void f(DataHolder dataholder, String as1[])
        {
            GJ.a(GJ. new ae(Hn, dataholder, as1));
        }

        public void onLeftRoom(int i1, String s1)
        {
            GJ.a(GJ. new v(Hm, i1, s1));
        }

        public void onP2PConnected(String s1)
        {
            GJ.a(GJ. new aa(Hn, s1));
        }

        public void onP2PDisconnected(String s1)
        {
            GJ.a(GJ. new ab(Hn, s1));
        }

        public void onRealTimeMessageReceived(RealTimeMessage realtimemessage)
        {
            GJ.a(GJ. new z(Ho, realtimemessage));
        }

        public void s(DataHolder dataholder)
        {
            GJ.a(GJ. new ba(Hm, dataholder));
        }

        public void t(DataHolder dataholder)
        {
            GJ.a(GJ. new q(Hm, dataholder));
        }

        public void u(DataHolder dataholder)
        {
            GJ.a(GJ. new az(Hn, dataholder));
        }

        public void v(DataHolder dataholder)
        {
            GJ.a(GJ. new aw(Hn, dataholder));
        }

        public void w(DataHolder dataholder)
        {
            GJ.a(GJ. new ay(Hm, dataholder));
        }

        public void x(DataHolder dataholder)
        {
            GJ.a(GJ. new h(Hn, dataholder));
        }

        public void y(DataHolder dataholder)
        {
            GJ.a(GJ. new i(Hn, dataholder));
        }

        final fx GJ;
        private final RoomUpdateListener Hm;
        private final RoomStatusUpdateListener Hn;
        private final RealTimeMessageReceivedListener Ho;

        public ax(RoomUpdateListener roomupdatelistener)
        {
            GJ = fx.this;
            super();
            Hm = (RoomUpdateListener)er.b(roomupdatelistener, "Callbacks must not be null");
            Hn = null;
            Ho = null;
        }

        public ax(RoomUpdateListener roomupdatelistener, RoomStatusUpdateListener roomstatusupdatelistener, RealTimeMessageReceivedListener realtimemessagereceivedlistener)
        {
            GJ = fx.this;
            super();
            Hm = (RoomUpdateListener)er.b(roomupdatelistener, "Callbacks must not be null");
            Hn = roomstatusupdatelistener;
            Ho = realtimemessagereceivedlistener;
        }
    }

    final class ay extends b
    {

        public void a(RoomUpdateListener roomupdatelistener, Room room, int i1)
        {
            roomupdatelistener.onRoomConnected(i1, room);
        }

        final fx GJ;

        ay(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            GJ = fx.this;
            super(roomupdatelistener, dataholder);
        }
    }

    final class az extends c
    {

        public void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            roomstatusupdatelistener.onRoomConnecting(room);
        }

        final fx GJ;

        az(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder);
        }
    }

    abstract class b extends eh.d
    {

        protected void a(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            a(roomupdatelistener, com.google.android.gms.internal.fx.a(GJ, dataholder), dataholder.getStatusCode());
        }

        protected abstract void a(RoomUpdateListener roomupdatelistener, Room room, int i1);

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((RoomUpdateListener)obj, dataholder);
        }

        final fx GJ;

        b(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            GJ = fx.this;
            super(fx.this, roomupdatelistener, dataholder);
        }
    }

    final class ba extends b
    {

        public void a(RoomUpdateListener roomupdatelistener, Room room, int i1)
        {
            roomupdatelistener.onRoomCreated(i1, room);
        }

        final fx GJ;

        public ba(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            GJ = fx.this;
            super(roomupdatelistener, dataholder);
        }
    }

    final class bb extends fw
    {

        public void cM()
        {
            Status status = new Status(0);
            GJ.a(GJ. new bc(vj, status));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        public bb(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class bc extends eh.b
    {

        public void a(Object obj)
        {
            c((com.google.android.gms.common.api.a.c)obj);
        }

        public void c(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(vl);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final Status vl;

        public bc(com.google.android.gms.common.api.a.c c1, Status status)
        {
            GJ = fx.this;
            super(fx.this, c1);
            vl = status;
        }
    }

    final class bd extends fw
    {

        public void d(DataHolder dataholder)
        {
            GJ.a(GJ. new be(vj, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        public bd(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class be extends av
        implements com.google.android.gms.games.leaderboard.Leaderboards.SubmitScoreResult
    {

        public void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            c1.b(this);
        }

        public volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public ScoreSubmissionData getScoreData()
        {
            return Hp;
        }

        final fx GJ;
        private final ScoreSubmissionData Hp;

        public be(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
            Hp = new ScoreSubmissionData(dataholder);
            dataholder.close();
            return;
            Exception exception;
            exception;
            dataholder.close();
            throw exception;
        }
    }

    abstract class bf extends av
    {

        protected void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            h(c1);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public TurnBasedMatch getMatch()
        {
            return GX;
        }

        abstract void h(com.google.android.gms.common.api.a.c c1);

        final fx GJ;
        final TurnBasedMatch GX = null;

        bf(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            TurnBasedMatchBuffer turnbasedmatchbuffer;
            GJ = fx.this;
            super(c1, dataholder);
            turnbasedmatchbuffer = new TurnBasedMatchBuffer(dataholder);
            if(turnbasedmatchbuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            GX = (TurnBasedMatch)((TurnBasedMatch)turnbasedmatchbuffer.get(0)).freeze();
_L4:
            turnbasedmatchbuffer.close();
            return;
_L2:
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            turnbasedmatchbuffer.close();
            throw exception;
        }
    }

    final class bg extends fw
    {

        public void f(int i1, String s1)
        {
            Status status = new Status(i1);
            GJ.a(GJ. new bh(Hq, status, s1));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c Hq;

        public bg(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            Hq = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class bh extends eh.b
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.CancelMatchResult
    {

        public void a(Object obj)
        {
            c((com.google.android.gms.common.api.a.c)obj);
        }

        public void c(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        protected void cP()
        {
        }

        public String getMatchId()
        {
            return Hr;
        }

        public Status getStatus()
        {
            return vl;
        }

        final fx GJ;
        private final String Hr;
        private final Status vl;

        bh(com.google.android.gms.common.api.a.c c1, Status status, String s1)
        {
            GJ = fx.this;
            super(fx.this, c1);
            vl = status;
            Hr = s1;
        }
    }

    final class bi extends fw
    {

        public void o(DataHolder dataholder)
        {
            GJ.a(GJ. new bj(Hs, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c Hs;

        public bi(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            Hs = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class bj extends bf
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.InitiateMatchResult
    {

        protected void h(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        final fx GJ;

        bj(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
        }
    }

    final class bk extends fw
    {

        public void q(DataHolder dataholder)
        {
            GJ.a(GJ. new bl(Ht, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c Ht;

        public bk(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            Ht = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class bl extends bf
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LeaveMatchResult
    {

        protected void h(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        final fx GJ;

        bl(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
        }
    }

    final class bm extends fw
    {

        public void n(DataHolder dataholder)
        {
            GJ.a(GJ. new bn(Hu, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c Hu;

        public bm(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            Hu = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class bn extends bf
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchResult
    {

        protected void h(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        final fx GJ;

        bn(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
        }
    }

    final class bo extends fw
    {

        public void p(DataHolder dataholder)
        {
            GJ.a(GJ. new bp(Hv, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c Hv;

        public bo(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            Hv = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class bp extends bf
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.UpdateMatchResult
    {

        protected void h(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        final fx GJ;

        bp(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
        }
    }

    final class bq extends fw
    {

        public void a(int i1, Bundle bundle)
        {
            bundle.setClassLoader(getClass().getClassLoader());
            Status status = new Status(i1);
            GJ.a(GJ. new br(Hw, status, bundle));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c Hw;

        public bq(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            Hw = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class br extends eh.b
        implements com.google.android.gms.games.multiplayer.turnbased.TurnBasedMultiplayer.LoadMatchesResult
    {

        protected void a(Object obj)
        {
            c((com.google.android.gms.common.api.a.c)obj);
        }

        protected void c(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        protected void cP()
        {
        }

        public LoadMatchesResponse getMatches()
        {
            return Hx;
        }

        public Status getStatus()
        {
            return vl;
        }

        public void release()
        {
            Hx.close();
        }

        final fx GJ;
        private final LoadMatchesResponse Hx;
        private final Status vl;

        br(com.google.android.gms.common.api.a.c c1, Status status, Bundle bundle)
        {
            GJ = fx.this;
            super(fx.this, c1);
            vl = status;
            Hx = new LoadMatchesResponse(bundle);
        }
    }

    abstract class c extends eh.d
    {

        protected void a(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            a(roomstatusupdatelistener, com.google.android.gms.internal.fx.a(GJ, dataholder));
        }

        protected abstract void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room);

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((RoomStatusUpdateListener)obj, dataholder);
        }

        final fx GJ;

        c(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            GJ = fx.this;
            super(fx.this, roomstatusupdatelistener, dataholder);
        }
    }

    final class d extends fw
    {

        public void e(int i1, String s1)
        {
            GJ.a(GJ. new e(vj, i1, s1));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        d(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class e extends eh.b
        implements com.google.android.gms.games.achievement.Achievements.UpdateAchievementResult
    {

        protected void a(Object obj)
        {
            c((com.google.android.gms.common.api.a.c)obj);
        }

        protected void c(com.google.android.gms.common.api.a.c c1)
        {
            c1.b(this);
        }

        protected void cP()
        {
        }

        public String getAchievementId()
        {
            return GK;
        }

        public Status getStatus()
        {
            return vl;
        }

        final fx GJ;
        private final String GK;
        private final Status vl;

        e(com.google.android.gms.common.api.a.c c1, int i1, String s1)
        {
            GJ = fx.this;
            super(fx.this, c1);
            vl = new Status(i1);
            GK = s1;
        }
    }

    final class f extends fw
    {

        public void b(DataHolder dataholder)
        {
            GJ.a(GJ. new g(vj, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        f(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class g extends av
        implements com.google.android.gms.games.achievement.Achievements.LoadAchievementsResult
    {

        protected void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            c1.b(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public AchievementBuffer getAchievements()
        {
            return GL;
        }

        final fx GJ;
        private final AchievementBuffer GL;

        g(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
            GL = new AchievementBuffer(dataholder);
        }
    }

    final class h extends c
    {

        public void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            roomstatusupdatelistener.onConnectedToRoom(room);
        }

        final fx GJ;

        h(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder);
        }
    }

    final class i extends c
    {

        public void a(RoomStatusUpdateListener roomstatusupdatelistener, Room room)
        {
            roomstatusupdatelistener.onDisconnectedFromRoom(room);
        }

        final fx GJ;

        i(RoomStatusUpdateListener roomstatusupdatelistener, DataHolder dataholder)
        {
            GJ = fx.this;
            super(roomstatusupdatelistener, dataholder);
        }
    }

    final class j extends fw
    {

        public void g(DataHolder dataholder)
        {
            GJ.a(GJ. new k(vj, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        j(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class k extends av
        implements com.google.android.gms.games.GamesMetadata.LoadGamesResult
    {

        protected void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            c1.b(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public GameBuffer getGames()
        {
            return GM;
        }

        final fx GJ;
        private final GameBuffer GM;

        k(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
            GM = new GameBuffer(dataholder);
        }
    }

    final class l extends fw
    {

        public void l(DataHolder dataholder)
        {
            InvitationBuffer invitationbuffer = new InvitationBuffer(dataholder);
            int i1 = invitationbuffer.getCount();
            Invitation invitation;
            invitation = null;
            if(i1 <= 0)
                break MISSING_BLOCK_LABEL_41;
            invitation = (Invitation)((Invitation)invitationbuffer.get(0)).freeze();
            invitationbuffer.close();
            if(invitation != null)
                GJ.a(GJ. new m(GN, invitation));
            return;
            Exception exception;
            exception;
            invitationbuffer.close();
            throw exception;
        }

        public void onInvitationRemoved(String s1)
        {
            GJ.a(GJ. new n(GN, s1));
        }

        final fx GJ;
        private final OnInvitationReceivedListener GN;

        l(OnInvitationReceivedListener oninvitationreceivedlistener)
        {
            GJ = fx.this;
            super();
            GN = oninvitationreceivedlistener;
        }
    }

    final class m extends eh.b
    {

        protected void a(Object obj)
        {
            b((OnInvitationReceivedListener)obj);
        }

        protected void b(OnInvitationReceivedListener oninvitationreceivedlistener)
        {
            oninvitationreceivedlistener.onInvitationReceived(GO);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final Invitation GO;

        m(OnInvitationReceivedListener oninvitationreceivedlistener, Invitation invitation)
        {
            GJ = fx.this;
            super(fx.this, oninvitationreceivedlistener);
            GO = invitation;
        }
    }

    final class n extends eh.b
    {

        protected void a(Object obj)
        {
            b((OnInvitationReceivedListener)obj);
        }

        protected void b(OnInvitationReceivedListener oninvitationreceivedlistener)
        {
            oninvitationreceivedlistener.onInvitationRemoved(GP);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final String GP;

        n(OnInvitationReceivedListener oninvitationreceivedlistener, String s1)
        {
            GJ = fx.this;
            super(fx.this, oninvitationreceivedlistener);
            GP = s1;
        }
    }

    final class o extends fw
    {

        public void k(DataHolder dataholder)
        {
            GJ.a(GJ. new p(vj, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        o(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class p extends av
        implements com.google.android.gms.games.multiplayer.Invitations.LoadInvitationsResult
    {

        protected void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            c1.b(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public InvitationBuffer getInvitations()
        {
            return GQ;
        }

        final fx GJ;
        private final InvitationBuffer GQ;

        p(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
            GQ = new InvitationBuffer(dataholder);
        }
    }

    final class q extends b
    {

        public void a(RoomUpdateListener roomupdatelistener, Room room, int i1)
        {
            roomupdatelistener.onJoinedRoom(i1, room);
        }

        final fx GJ;

        public q(RoomUpdateListener roomupdatelistener, DataHolder dataholder)
        {
            GJ = fx.this;
            super(roomupdatelistener, dataholder);
        }
    }

    final class r extends fw
    {

        public void a(DataHolder dataholder, DataHolder dataholder1)
        {
            GJ.a(GJ. new s(vj, dataholder, dataholder1));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        r(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class s extends av
        implements com.google.android.gms.games.leaderboard.Leaderboards.LoadScoresResult
    {

        protected void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            c1.b(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public Leaderboard getLeaderboard()
        {
            return GR;
        }

        public LeaderboardScoreBuffer getScores()
        {
            return GS;
        }

        final fx GJ;
        private final com.google.android.gms.games.leaderboard.a GR = null;
        private final LeaderboardScoreBuffer GS;

        s(com.google.android.gms.common.api.a.c c1, DataHolder dataholder, DataHolder dataholder1)
        {
            LeaderboardBuffer leaderboardbuffer;
            GJ = fx.this;
            super(c1, dataholder1);
            leaderboardbuffer = new LeaderboardBuffer(dataholder);
            if(leaderboardbuffer.getCount() <= 0) goto _L2; else goto _L1
_L1:
            GR = (com.google.android.gms.games.leaderboard.a)((Leaderboard)leaderboardbuffer.get(0)).freeze();
_L4:
            leaderboardbuffer.close();
            GS = new LeaderboardScoreBuffer(dataholder1);
            return;
_L2:
            if(true) goto _L4; else goto _L3
_L3:
            Exception exception;
            exception;
            leaderboardbuffer.close();
            throw exception;
        }
    }

    final class t extends fw
    {

        public void c(DataHolder dataholder)
        {
            GJ.a(GJ. new u(vj, dataholder));
        }

        final fx GJ;
        private final com.google.android.gms.common.api.a.c vj;

        t(com.google.android.gms.common.api.a.c c1)
        {
            GJ = fx.this;
            super();
            vj = (com.google.android.gms.common.api.a.c)er.b(c1, "Holder must not be null");
        }
    }

    final class u extends av
        implements com.google.android.gms.games.leaderboard.Leaderboards.LeaderboardMetadataResult
    {

        protected void a(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            c1.b(this);
        }

        protected volatile void a(Object obj, DataHolder dataholder)
        {
            a((com.google.android.gms.common.api.a.c)obj, dataholder);
        }

        public LeaderboardBuffer getLeaderboards()
        {
            return GT;
        }

        final fx GJ;
        private final LeaderboardBuffer GT;

        u(com.google.android.gms.common.api.a.c c1, DataHolder dataholder)
        {
            GJ = fx.this;
            super(c1, dataholder);
            GT = new LeaderboardBuffer(dataholder);
        }
    }

    final class v extends eh.b
    {

        public void a(RoomUpdateListener roomupdatelistener)
        {
            roomupdatelistener.onLeftRoom(yJ, GU);
        }

        public volatile void a(Object obj)
        {
            a((RoomUpdateListener)obj);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final String GU;
        private final int yJ;

        v(RoomUpdateListener roomupdatelistener, int i1, String s1)
        {
            GJ = fx.this;
            super(fx.this, roomupdatelistener);
            yJ = i1;
            GU = s1;
        }
    }

    final class w extends eh.b
    {

        protected void a(Object obj)
        {
            b((OnTurnBasedMatchUpdateReceivedListener)obj);
        }

        protected void b(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
        {
            onturnbasedmatchupdatereceivedlistener.onTurnBasedMatchRemoved(GV);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final String GV;

        w(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener, String s1)
        {
            GJ = fx.this;
            super(fx.this, onturnbasedmatchupdatereceivedlistener);
            GV = s1;
        }
    }

    final class x extends fw
    {

        public void onTurnBasedMatchRemoved(String s1)
        {
            GJ.a(GJ. new w(GW, s1));
        }

        public void r(DataHolder dataholder)
        {
            TurnBasedMatchBuffer turnbasedmatchbuffer = new TurnBasedMatchBuffer(dataholder);
            int i1 = turnbasedmatchbuffer.getCount();
            TurnBasedMatch turnbasedmatch;
            turnbasedmatch = null;
            if(i1 <= 0)
                break MISSING_BLOCK_LABEL_41;
            turnbasedmatch = (TurnBasedMatch)((TurnBasedMatch)turnbasedmatchbuffer.get(0)).freeze();
            turnbasedmatchbuffer.close();
            if(turnbasedmatch != null)
                GJ.a(GJ. new y(GW, turnbasedmatch));
            return;
            Exception exception;
            exception;
            turnbasedmatchbuffer.close();
            throw exception;
        }

        final fx GJ;
        private final OnTurnBasedMatchUpdateReceivedListener GW;

        x(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
        {
            GJ = fx.this;
            super();
            GW = onturnbasedmatchupdatereceivedlistener;
        }
    }

    final class y extends eh.b
    {

        protected void a(Object obj)
        {
            b((OnTurnBasedMatchUpdateReceivedListener)obj);
        }

        protected void b(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
        {
            onturnbasedmatchupdatereceivedlistener.onTurnBasedMatchReceived(GX);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final TurnBasedMatch GX;

        y(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener, TurnBasedMatch turnbasedmatch)
        {
            GJ = fx.this;
            super(fx.this, onturnbasedmatchupdatereceivedlistener);
            GX = turnbasedmatch;
        }
    }

    final class z extends eh.b
    {

        public void a(RealTimeMessageReceivedListener realtimemessagereceivedlistener)
        {
            if(realtimemessagereceivedlistener != null)
                realtimemessagereceivedlistener.onRealTimeMessageReceived(GY);
        }

        public volatile void a(Object obj)
        {
            a((RealTimeMessageReceivedListener)obj);
        }

        protected void cP()
        {
        }

        final fx GJ;
        private final RealTimeMessage GY;

        z(RealTimeMessageReceivedListener realtimemessagereceivedlistener, RealTimeMessage realtimemessage)
        {
            GJ = fx.this;
            super(fx.this, realtimemessagereceivedlistener);
            GY = realtimemessage;
        }
    }


    public fx(Context context, Looper looper, String s1, String s2, com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks connectioncallbacks, com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener onconnectionfailedlistener, String as1[], 
            int i1, View view, boolean flag, boolean flag1, int j1, boolean flag2, int k1)
    {
        super(context, looper, connectioncallbacks, onconnectionfailedlistener, as1);
        GA = false;
        GB = false;
        Gv = s1;
        vi = (String)er.f(s2);
        Gz = com.google.android.gms.internal.gd.a(this, i1);
        e(view);
        GB = flag1;
        GC = j1;
        GF = flag;
        GH = flag2;
        GG = k1;
        registerConnectionCallbacks(this);
        registerConnectionFailedListener(this);
    }

    private Room G(DataHolder dataholder)
    {
        com.google.android.gms.games.multiplayer.realtime.a a1 = new com.google.android.gms.games.multiplayer.realtime.a(dataholder);
        int i1 = a1.getCount();
        Room room;
        room = null;
        if(i1 <= 0)
            break MISSING_BLOCK_LABEL_41;
        room = (Room)((Room)a1.get(0)).freeze();
        a1.close();
        return room;
        Exception exception;
        exception;
        a1.close();
        throw exception;
    }

    static Room a(fx fx1, DataHolder dataholder)
    {
        return fx1.G(dataholder);
    }

    private RealTimeSocket aw(String s1)
    {
        android.os.ParcelFileDescriptor parcelfiledescriptor;
        gc gc1;
        String s2;
        LocalSocket localsocket;
        ge ge1;
        try
        {
            parcelfiledescriptor = ((gb)eb()).aD(s1);
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.h("GamesClientImpl", "Unable to create socket. Service died.");
            return null;
        }
        if(parcelfiledescriptor == null)
            break MISSING_BLOCK_LABEL_51;
        com.google.android.gms.internal.fz.f("GamesClientImpl", "Created native libjingle socket.");
        gc1 = new gc(parcelfiledescriptor);
        Gw.put(s1, gc1);
        return gc1;
        com.google.android.gms.internal.fz.f("GamesClientImpl", "Unable to create native libjingle socket, resorting to old socket.");
        s2 = ((gb)eb()).ay(s1);
        if(s2 == null)
            return null;
        localsocket = new LocalSocket();
        localsocket.connect(new LocalSocketAddress(s2));
        ge1 = new ge(localsocket, s1);
        Gw.put(s1, ge1);
        return ge1;
        IOException ioexception;
        ioexception;
        com.google.android.gms.internal.fz.h("GamesClientImpl", (new StringBuilder()).append("connect() call failed on socket: ").append(ioexception.getMessage()).toString());
        return null;
    }

    private void fG()
    {
        for(Iterator iterator = Gw.values().iterator(); iterator.hasNext();)
        {
            RealTimeSocket realtimesocket = (RealTimeSocket)iterator.next();
            try
            {
                realtimesocket.close();
            }
            catch(IOException ioexception)
            {
                com.google.android.gms.internal.fz.a("GamesClientImpl", "IOException:", ioexception);
            }
        }

        Gw.clear();
    }

    private void fm()
    {
        Gx = null;
    }

    protected gb H(IBinder ibinder)
    {
        return gb.a.J(ibinder);
    }

    public int a(com.google.android.gms.games.multiplayer.realtime.RealTimeMultiplayer.ReliableMessageSentCallback reliablemessagesentcallback, byte abyte0[], String s1, String s2)
    {
        int i1;
        try
        {
            i1 = ((gb)eb()).a(new an(reliablemessagesentcallback), abyte0, s1, s2);
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public int a(byte abyte0[], String s1, String as1[])
    {
        er.b(as1, "Participant IDs must not be null");
        int i1;
        try
        {
            i1 = ((gb)eb()).b(abyte0, s1, as1);
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public Intent a(int i1, int j1, boolean flag)
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).a(i1, j1, flag);
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent a(int i1, byte abyte0[], int j1, Bitmap bitmap, String s1)
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).a(i1, abyte0, j1, s1);
            er.b(bitmap, "Must provide a non null icon");
            intent.putExtra("com.google.android.gms.games.REQUEST_ITEM_ICON", bitmap);
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent a(Room room, int i1)
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).a((RoomEntity)room.freeze(), i1);
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    protected void a(int i1, IBinder ibinder, Bundle bundle)
    {
        if(i1 == 0 && bundle != null)
            GA = bundle.getBoolean("show_welcome_popup");
        super.a(i1, ibinder, bundle);
    }

    public void a(IBinder ibinder, Bundle bundle)
    {
        if(!isConnected())
            break MISSING_BLOCK_LABEL_21;
        ((gb)eb()).a(ibinder, bundle);
        return;
        RemoteException remoteexception;
        remoteexception;
        com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        return;
    }

    public void a(com.google.android.gms.common.api.a.c c1, int i1, int j1, int k1)
    {
        try
        {
            ((gb)eb()).a(new ar(c1), i1, j1, k1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, int i1, boolean flag, boolean flag1)
    {
        try
        {
            ((gb)eb()).a(new ak(c1), i1, flag, flag1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, int i1, int ai1[])
    {
        try
        {
            ((gb)eb()).a(new bq(c1), i1, ai1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, LeaderboardScoreBuffer leaderboardscorebuffer, int i1, int j1)
    {
        try
        {
            ((gb)eb()).a(new r(c1), leaderboardscorebuffer.fX().fY(), i1, j1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, TurnBasedMatchConfig turnbasedmatchconfig)
    {
        try
        {
            ((gb)eb()).a(new bi(c1), turnbasedmatchconfig.getVariant(), turnbasedmatchconfig.getMinPlayers(), turnbasedmatchconfig.getInvitedPlayerIds(), turnbasedmatchconfig.getAutoMatchCriteria());
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1)
    {
        try
        {
            ((gb)eb()).a(new ak(c1), s1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1, int i1)
    {
        Object obj;
        if(c1 != null)
            break MISSING_BLOCK_LABEL_38;
        obj = null;
_L1:
        try
        {
            ((gb)eb()).a(((ga) (obj)), s1, i1, Gz.fP(), Gz.fO());
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_62;
        obj = new d(c1);
          goto _L1
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1, int i1, int j1, int k1, boolean flag)
    {
        try
        {
            ((gb)eb()).a(new r(c1), s1, i1, j1, k1, flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1, int i1, boolean flag, boolean flag1)
    {
        if(!s1.equals("playedWith"))
            throw new IllegalArgumentException((new StringBuilder()).append("Invalid player collection: ").append(s1).toString());
        try
        {
            ((gb)eb()).d(new ak(c1), s1, i1, flag, flag1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1, long l1, String s2)
    {
        Object obj;
        if(c1 != null)
            break MISSING_BLOCK_LABEL_26;
        obj = null;
_L1:
        try
        {
            ((gb)eb()).a(((ga) (obj)), s1, l1, s2);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_50;
        obj = new bd(c1);
          goto _L1
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1, String s2)
    {
        try
        {
            ((gb)eb()).c(new bk(c1), s1, s2);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1, String s2, int i1, int j1)
    {
        try
        {
            ((gb)eb()).a(new ai(c1), s1, s2, i1, j1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1, boolean flag)
    {
        try
        {
            ((gb)eb()).c(new t(c1), s1, flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1, byte abyte0[], String s2, ParticipantResult aparticipantresult[])
    {
        try
        {
            ((gb)eb()).a(new bo(c1), s1, abyte0, s2, aparticipantresult);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, String s1, byte abyte0[], ParticipantResult aparticipantresult[])
    {
        try
        {
            ((gb)eb()).a(new bo(c1), s1, abyte0, aparticipantresult);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, boolean flag)
    {
        try
        {
            ((gb)eb()).c(new ak(c1), flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(com.google.android.gms.common.api.a.c c1, String as1[])
    {
        try
        {
            ((gb)eb()).a(new at(c1), as1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(OnInvitationReceivedListener oninvitationreceivedlistener)
    {
        try
        {
            l l1 = new l(oninvitationreceivedlistener);
            ((gb)eb()).a(l1, GE);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(RoomConfig roomconfig)
    {
        try
        {
            ax ax1 = new ax(roomconfig.getRoomUpdateListener(), roomconfig.getRoomStatusUpdateListener(), roomconfig.getMessageReceivedListener());
            ((gb)eb()).a(ax1, GD, roomconfig.getVariant(), roomconfig.getInvitedPlayerIds(), roomconfig.getAutoMatchCriteria(), roomconfig.isSocketEnabled(), GE);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(RoomUpdateListener roomupdatelistener, String s1)
    {
        try
        {
            ((gb)eb()).c(new ax(roomupdatelistener), s1);
            fG();
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(OnTurnBasedMatchUpdateReceivedListener onturnbasedmatchupdatereceivedlistener)
    {
        try
        {
            x x1 = new x(onturnbasedmatchupdatereceivedlistener);
            ((gb)eb()).b(x1, GE);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void a(OnRequestReceivedListener onrequestreceivedlistener)
    {
        try
        {
            ao ao1 = new ao(onrequestreceivedlistener);
            ((gb)eb()).c(ao1, GE);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    protected void a(en en1, eh.e e1)
        throws RemoteException
    {
        String s1 = getContext().getResources().getConfiguration().locale.toString();
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.games.key.isHeadless", GF);
        bundle.putBoolean("com.google.android.gms.games.key.showConnectingPopup", GB);
        bundle.putInt("com.google.android.gms.games.key.connectingPopupGravity", GC);
        bundle.putBoolean("com.google.android.gms.games.key.retryingSignIn", GH);
        bundle.putInt("com.google.android.gms.games.key.sdkVariant", GG);
        en1.a(e1, 0x41f6b8, getContext().getPackageName(), vi, ea(), Gv, Gz.fP(), s1, bundle);
    }

    protected String aF()
    {
        return "com.google.android.gms.games.service.START";
    }

    protected String aG()
    {
        return "com.google.android.gms.games.internal.IGamesService";
    }

    public void aT(int i1)
    {
        Gz.setGravity(i1);
    }

    public void aU(int i1)
    {
        try
        {
            ((gb)eb()).aU(i1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public Intent au(String s1)
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).au(s1);
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public void av(String s1)
    {
        try
        {
            ((gb)eb()).aC(s1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public Intent b(int i1, int j1, boolean flag)
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).b(i1, j1, flag);
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public void b(com.google.android.gms.common.api.a.c c1)
    {
        try
        {
            ((gb)eb()).a(new bb(c1));
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.c c1, String s1)
    {
        if(c1 != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L3:
        d d1;
        try
        {
            ((gb)eb()).a(((ga) (obj)), s1, Gz.fP(), Gz.fO());
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_63;
_L2:
        d1 = new d(c1);
        obj = d1;
          goto _L3
    }

    public void b(com.google.android.gms.common.api.a.c c1, String s1, int i1)
    {
        Object obj;
        if(c1 != null)
            break MISSING_BLOCK_LABEL_38;
        obj = null;
_L1:
        try
        {
            ((gb)eb()).b(((ga) (obj)), s1, i1, Gz.fP(), Gz.fO());
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_62;
        obj = new d(c1);
          goto _L1
    }

    public void b(com.google.android.gms.common.api.a.c c1, String s1, int i1, int j1, int k1, boolean flag)
    {
        try
        {
            ((gb)eb()).b(new r(c1), s1, i1, j1, k1, flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.c c1, boolean flag)
    {
        try
        {
            ((gb)eb()).b(new t(c1), flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void b(com.google.android.gms.common.api.a.c c1, String as1[])
    {
        try
        {
            ((gb)eb()).b(new at(c1), as1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void b(RoomConfig roomconfig)
    {
        try
        {
            ax ax1 = new ax(roomconfig.getRoomUpdateListener(), roomconfig.getRoomStatusUpdateListener(), roomconfig.getMessageReceivedListener());
            ((gb)eb()).a(ax1, GD, roomconfig.getInvitationId(), roomconfig.isSocketEnabled(), GE);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    protected transient void b(String as1[])
    {
        int i1 = 0;
        boolean flag = false;
        boolean flag1 = false;
        while(i1 < as1.length) 
        {
            String s1 = as1[i1];
            if(s1.equals("https://www.googleapis.com/auth/games"))
                flag1 = true;
            else
            if(s1.equals("https://www.googleapis.com/auth/games.firstparty"))
                flag = true;
            i1++;
        }
        if(flag)
        {
            boolean flag2;
            if(!flag1)
                flag2 = true;
            else
                flag2 = false;
            com.google.android.gms.internal.er.a(flag2, String.format("Cannot have both %s and %s!", new Object[] {
                "https://www.googleapis.com/auth/games", "https://www.googleapis.com/auth/games.firstparty"
            }));
            return;
        } else
        {
            com.google.android.gms.internal.er.a(flag1, String.format("Games APIs requires %s to function.", new Object[] {
                "https://www.googleapis.com/auth/games"
            }));
            return;
        }
    }

    public void c(com.google.android.gms.common.api.a.c c1, int i1)
    {
        try
        {
            ((gb)eb()).a(new o(c1), i1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void c(com.google.android.gms.common.api.a.c c1, String s1)
    {
        if(c1 != null) goto _L2; else goto _L1
_L1:
        Object obj = null;
_L3:
        d d1;
        try
        {
            ((gb)eb()).b(((ga) (obj)), s1, Gz.fP(), Gz.fO());
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
        break MISSING_BLOCK_LABEL_63;
_L2:
        d1 = new d(c1);
        obj = d1;
          goto _L3
    }

    public void c(com.google.android.gms.common.api.a.c c1, boolean flag)
    {
        try
        {
            ((gb)eb()).a(new f(c1), flag);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public Bundle cY()
    {
        Bundle bundle;
        try
        {
            bundle = ((gb)eb()).cY();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        if(bundle == null)
            break MISSING_BLOCK_LABEL_26;
        bundle.setClassLoader(com/google/android/gms/internal/fx.getClassLoader());
        return bundle;
    }

    public void connect()
    {
        fm();
        super.connect();
    }

    public int d(byte abyte0[], String s1)
    {
        int i1;
        try
        {
            i1 = ((gb)eb()).b(abyte0, s1, null);
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return -1;
        }
        return i1;
    }

    public void d(com.google.android.gms.common.api.a.c c1, String s1)
    {
        try
        {
            ((gb)eb()).l(new bi(c1), s1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void disconnect()
    {
        GA = false;
        if(isConnected())
            try
            {
                gb gb1 = (gb)eb();
                gb1.fH();
                gb1.n(GE);
            }
            catch(RemoteException remoteexception)
            {
                com.google.android.gms.internal.fz.g("GamesClientImpl", "Failed to notify client disconnect.");
            }
        fG();
        super.disconnect();
    }

    public void e(View view)
    {
        Gz.f(view);
    }

    public void e(com.google.android.gms.common.api.a.c c1, String s1)
    {
        try
        {
            ((gb)eb()).m(new bi(c1), s1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void f(com.google.android.gms.common.api.a.c c1, String s1)
    {
        try
        {
            ((gb)eb()).o(new bk(c1), s1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public int fA()
    {
        int i1;
        try
        {
            i1 = ((gb)eb()).fA();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return 4368;
        }
        return i1;
    }

    public String fB()
    {
        String s1;
        try
        {
            s1 = ((gb)eb()).fB();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return s1;
    }

    public int fC()
    {
        int i1;
        try
        {
            i1 = ((gb)eb()).fC();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return 2;
        }
        return i1;
    }

    public Intent fD()
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).fD();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public int fE()
    {
        int i1;
        try
        {
            i1 = ((gb)eb()).fE();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return 2;
        }
        return i1;
    }

    public int fF()
    {
        int i1;
        try
        {
            i1 = ((gb)eb()).fF();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return 2;
        }
        return i1;
    }

    public void fH()
    {
        if(!isConnected())
            break MISSING_BLOCK_LABEL_19;
        ((gb)eb()).fH();
        return;
        RemoteException remoteexception;
        remoteexception;
        com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        return;
    }

    public String fn()
    {
        String s1;
        try
        {
            s1 = ((gb)eb()).fn();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return s1;
    }

    public String fo()
    {
        String s1;
        try
        {
            s1 = ((gb)eb()).fo();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return s1;
    }

    public Player fp()
    {
        bm();
        this;
        JVM INSTR monitorenter ;
        PlayerEntity playerentity = Gx;
        if(playerentity != null)
            break MISSING_BLOCK_LABEL_63;
        PlayerBuffer playerbuffer = new PlayerBuffer(((gb)eb()).fI());
        if(playerbuffer.getCount() > 0)
            Gx = (PlayerEntity)playerbuffer.get(0).freeze();
        playerbuffer.close();
_L1:
        this;
        JVM INSTR monitorexit ;
        return Gx;
        Exception exception1;
        exception1;
        try
        {
            playerbuffer.close();
            throw exception1;
        }
        catch(RemoteException remoteexception) { }
        com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
          goto _L1
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Game fq()
    {
        bm();
        this;
        JVM INSTR monitorenter ;
        GameEntity gameentity = Gy;
        if(gameentity != null)
            break MISSING_BLOCK_LABEL_63;
        GameBuffer gamebuffer = new GameBuffer(((gb)eb()).fK());
        if(gamebuffer.getCount() > 0)
            Gy = (GameEntity)gamebuffer.get(0).freeze();
        gamebuffer.close();
_L1:
        this;
        JVM INSTR monitorexit ;
        return Gy;
        Exception exception1;
        exception1;
        try
        {
            gamebuffer.close();
            throw exception1;
        }
        catch(RemoteException remoteexception) { }
        com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
          goto _L1
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Intent fr()
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).fr();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent fs()
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).fs();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent ft()
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).ft();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent fu()
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).fu();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public void fv()
    {
        try
        {
            ((gb)eb()).o(GE);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void fw()
    {
        try
        {
            ((gb)eb()).p(GE);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void fx()
    {
        try
        {
            ((gb)eb()).q(GE);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public Intent fy()
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).fy();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public Intent fz()
    {
        Intent intent;
        try
        {
            intent = ((gb)eb()).fz();
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
            return null;
        }
        return intent;
    }

    public void g(com.google.android.gms.common.api.a.c c1)
    {
        try
        {
            ((gb)eb()).d(new j(c1));
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void g(com.google.android.gms.common.api.a.c c1, String s1)
    {
        try
        {
            ((gb)eb()).n(new bg(c1), s1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void h(com.google.android.gms.common.api.a.c c1, String s1)
    {
        try
        {
            ((gb)eb()).p(new bm(c1), s1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public RealTimeSocket i(String s1, String s2)
    {
        if(s2 == null || !ParticipantUtils.aE(s2))
            throw new IllegalArgumentException("Bad participant ID");
        RealTimeSocket realtimesocket = (RealTimeSocket)Gw.get(s2);
        if(realtimesocket == null || realtimesocket.isClosed())
            realtimesocket = aw(s2);
        return realtimesocket;
    }

    public void l(String s1, int i1)
    {
        try
        {
            ((gb)eb()).l(s1, i1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void m(String s1, int i1)
    {
        try
        {
            ((gb)eb()).m(s1, i1);
            return;
        }
        catch(RemoteException remoteexception)
        {
            com.google.android.gms.internal.fz.g("GamesClientImpl", "service died");
        }
    }

    public void onConnected(Bundle bundle)
    {
        if(GA)
        {
            Gz.fN();
            GA = false;
        }
    }

    public void onConnectionFailed(ConnectionResult connectionresult)
    {
        GA = false;
    }

    public void onConnectionSuspended(int i1)
    {
    }

    protected IInterface p(IBinder ibinder)
    {
        return H(ibinder);
    }

    private boolean GA;
    private boolean GB;
    private int GC;
    private final Binder GD = new Binder();
    private final long GE = (long)hashCode();
    private final boolean GF;
    private final int GG;
    private final boolean GH;
    private final String Gv;
    private final Map Gw = new HashMap();
    private PlayerEntity Gx;
    private GameEntity Gy;
    private final gd Gz;
    private final String vi;
}
