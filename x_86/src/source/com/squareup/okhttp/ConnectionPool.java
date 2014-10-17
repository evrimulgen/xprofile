// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp;

import com.squareup.okhttp.internal.Platform;
import com.squareup.okhttp.internal.Util;
import java.io.Closeable;
import java.net.SocketException;
import java.util.*;
import java.util.concurrent.*;

// Referenced classes of package com.squareup.okhttp:
//            Connection, Route, Address

public class ConnectionPool
{

    public ConnectionPool(int i, long l)
    {
        executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
        maxIdleConnections = i;
        keepAliveDurationNs = 1000L * (l * 1000L);
    }

    public static ConnectionPool getDefault()
    {
        return systemDefault;
    }

    private void waitForCleanupCallableToRun()
    {
        try
        {
            executorService.submit(new Runnable() {

                public void run()
                {
                }

                final ConnectionPool this$0;

            
            {
                this$0 = ConnectionPool.this;
                super();
            }
            }
).get();
            return;
        }
        catch(Exception exception)
        {
            throw new AssertionError();
        }
    }

    public void evictAll()
    {
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist;
        arraylist = new ArrayList(connections);
        connections.clear();
        this;
        JVM INSTR monitorexit ;
        int i = 0;
        for(int j = arraylist.size(); i < j; i++)
            Util.closeQuietly((Closeable)arraylist.get(i));

        break MISSING_BLOCK_LABEL_63;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public Connection get(Address address)
    {
        this;
        JVM INSTR monitorenter ;
        ListIterator listiterator = connections.listIterator(connections.size());
_L2:
        boolean flag = listiterator.hasPrevious();
        Connection connection;
        connection = null;
        if(!flag)
            break MISSING_BLOCK_LABEL_117;
        Connection connection1 = (Connection)listiterator.previous();
        if(!connection1.getRoute().getAddress().equals(address) || !connection1.isAlive() || System.nanoTime() - connection1.getIdleStartTimeNs() >= keepAliveDurationNs) goto _L2; else goto _L1
_L1:
        boolean flag1;
        listiterator.remove();
        flag1 = connection1.isSpdy();
        if(flag1)
            break MISSING_BLOCK_LABEL_113;
        Platform.get().tagSocket(connection1.getSocket());
        connection = connection1;
        if(connection == null)
            break MISSING_BLOCK_LABEL_139;
        if(connection.isSpdy())
            connections.addFirst(connection);
        executorService.execute(connectionsCleanupRunnable);
        this;
        JVM INSTR monitorexit ;
        return connection;
        SocketException socketexception;
        socketexception;
        Util.closeQuietly(connection1);
        Platform.get().logW((new StringBuilder()).append("Unable to tagSocket(): ").append(socketexception).toString());
          goto _L2
        Exception exception;
        exception;
        throw exception;
    }

    public int getConnectionCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = connections.size();
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    List getConnections()
    {
        waitForCleanupCallableToRun();
        this;
        JVM INSTR monitorenter ;
        ArrayList arraylist = new ArrayList(connections);
        this;
        JVM INSTR monitorexit ;
        return arraylist;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public int getHttpConnectionCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = 0;
        Iterator iterator = connections.iterator();
_L1:
        boolean flag;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_46;
        flag = ((Connection)iterator.next()).isSpdy();
        if(!flag)
            i++;
          goto _L1
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public int getSpdyConnectionCount()
    {
        this;
        JVM INSTR monitorenter ;
        int i = 0;
        Iterator iterator = connections.iterator();
_L1:
        boolean flag;
        if(!iterator.hasNext())
            break MISSING_BLOCK_LABEL_46;
        flag = ((Connection)iterator.next()).isSpdy();
        if(flag)
            i++;
          goto _L1
        this;
        JVM INSTR monitorexit ;
        return i;
        Exception exception;
        exception;
        throw exception;
    }

    public void recycle(Connection connection)
    {
        while(connection.isSpdy() || !connection.clearOwner()) 
            return;
        if(!connection.isAlive())
        {
            Util.closeQuietly(connection);
            return;
        }
        try
        {
            Platform.get().untagSocket(connection.getSocket());
        }
        catch(SocketException socketexception)
        {
            Platform.get().logW((new StringBuilder()).append("Unable to untagSocket(): ").append(socketexception).toString());
            Util.closeQuietly(connection);
            return;
        }
        this;
        JVM INSTR monitorenter ;
        connections.addFirst(connection);
        connection.incrementRecycleCount();
        connection.resetIdleStartTime();
        this;
        JVM INSTR monitorexit ;
        executorService.execute(connectionsCleanupRunnable);
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public void share(Connection connection)
    {
        if(!connection.isSpdy())
            throw new IllegalArgumentException();
        executorService.execute(connectionsCleanupRunnable);
        if(!connection.isAlive())
            break MISSING_BLOCK_LABEL_53;
        this;
        JVM INSTR monitorenter ;
        connections.addFirst(connection);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static final long DEFAULT_KEEP_ALIVE_DURATION_MS = 0x493e0L;
    private static final int MAX_CONNECTIONS_TO_CLEANUP = 2;
    private static final ConnectionPool systemDefault;
    private final LinkedList connections = new LinkedList();
    private final Runnable connectionsCleanupRunnable = new Runnable() {

        public void run()
        {
            ArrayList arraylist;
            int j;
            arraylist = new ArrayList(2);
            j = 0;
            ConnectionPool connectionpool = ConnectionPool.this;
            connectionpool;
            JVM INSTR monitorenter ;
            ListIterator listiterator = connections.listIterator(connections.size());
_L5:
            if(!listiterator.hasPrevious()) goto _L2; else goto _L1
_L1:
            Connection connection1 = (Connection)listiterator.previous();
            if(connection1.isAlive() && !connection1.isExpired(keepAliveDurationNs)) goto _L4; else goto _L3
_L3:
            listiterator.remove();
            arraylist.add(connection1);
            if(arraylist.size() != 2) goto _L5; else goto _L2
_L2:
            ListIterator listiterator1 = connections.listIterator(connections.size());
_L6:
            Connection connection;
            do
            {
                if(!listiterator1.hasPrevious() || j <= maxIdleConnections)
                    break MISSING_BLOCK_LABEL_210;
                connection = (Connection)listiterator1.previous();
            } while(!connection.isIdle());
            arraylist.add(connection);
            listiterator1.remove();
            j--;
              goto _L6
_L4:
            if(!connection1.isIdle()) goto _L5; else goto _L7
_L7:
            j++;
              goto _L5
            connectionpool;
            JVM INSTR monitorexit ;
            for(Iterator iterator = arraylist.iterator(); iterator.hasNext(); Util.closeQuietly((Connection)iterator.next()));
            break MISSING_BLOCK_LABEL_253;
            Exception exception;
            exception;
            connectionpool;
            JVM INSTR monitorexit ;
            throw exception;
        }

        final ConnectionPool this$0;

            
            {
                this$0 = ConnectionPool.this;
                super();
            }
    }
;
    private final ExecutorService executorService;
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;

    static 
    {
        String s = System.getProperty("http.keepAlive");
        String s1 = System.getProperty("http.keepAliveDuration");
        String s2 = System.getProperty("http.maxConnections");
        long l;
        if(s1 != null)
            l = Long.parseLong(s1);
        else
            l = 0x493e0L;
        if(s != null && !Boolean.parseBoolean(s))
            systemDefault = new ConnectionPool(0, l);
        else
        if(s2 != null)
            systemDefault = new ConnectionPool(Integer.parseInt(s2), l);
        else
            systemDefault = new ConnectionPool(5, l);
    }



}
