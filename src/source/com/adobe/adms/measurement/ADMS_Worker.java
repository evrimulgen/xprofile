// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.adobe.adms.measurement;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

// Referenced classes of package com.adobe.adms.measurement:
//            ADMS_Measurement, ADMS_RequestProperties, ADMS_RequestHandler

final class ADMS_Worker
{
    private static class WorkerThread extends Thread
    {

        public void run()
        {
_L9:
            if(cancelled) goto _L2; else goto _L1
_L1:
            if(!ADMS_Measurement.isOnline())
                break MISSING_BLOCK_LABEL_162;
            if(worker.getTrackingQueueSize() <= 0) goto _L4; else goto _L3
_L3:
            String s = worker.popRequest();
            if(s == null) goto _L6; else goto _L5
_L5:
            ADMS_RequestProperties adms_requestproperties = new ADMS_RequestProperties(s);
            if(!ADMS_RequestHandler.sendRequest(adms_requestproperties.getUrl(), adms_requestproperties.getHeaders())) goto _L8; else goto _L7
_L7:
            if(worker.trackOffline)
                worker.writeToDisk();
_L6:
            Exception exception;
            Thread.sleep(delay);
            delay = 0L;
              goto _L9
_L2:
            return;
_L8:
            ADMS_Measurement.sharedInstance().debugLog("ADMS SDK Error: Error Sending Hit");
            if(worker.trackOffline)
            {
                delay = 30000L;
                worker.pushRequest(s);
            }
              goto _L6
_L4:
            try
            {
                cancelled = true;
                return;
            }
            // Misplaced declaration of an exception variable
            catch(Exception exception)
            {
                ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("ADMS SDK Error: Background Thread Interrupted -- ").append(exception.getMessage()).toString());
            }
              goto _L2
            delay = 30000L;
              goto _L6
        }

        public boolean cancelled;
        private long delay;
        private ADMS_Worker worker;

        public WorkerThread(ADMS_Worker adms_worker)
        {
            delay = 0L;
            worker = null;
            cancelled = false;
            worker = adms_worker;
        }
    }


    protected ADMS_Worker()
    {
        backgroundThread = null;
        cacheFilename = null;
        trackOffline = false;
        offlineLimit = 100;
        offlineForced = false;
    }

    private void killThread()
    {
        if(backgroundThread != null)
        {
            backgroundThread.cancelled = true;
            backgroundThread = null;
        }
    }

    protected void clearTrackingQueue()
    {
        synchronized(queuedMessages)
        {
            queuedMessages.clear();
            writeToDisk();
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected int getTrackingQueueSize()
    {
        int i;
        synchronized(queuedMessages)
        {
            i = queuedMessages.size();
        }
        return i;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected String popRequest()
    {
        ArrayList arraylist = queuedMessages;
        arraylist;
        JVM INSTR monitorenter ;
        int i = queuedMessages.size();
        String s;
        s = null;
        if(i <= 0)
            break MISSING_BLOCK_LABEL_44;
        s = (String)queuedMessages.get(0);
        queuedMessages.remove(0);
        arraylist;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void pushRequest(String s)
    {
        synchronized(queuedMessages)
        {
            queuedMessages.add(0, s);
        }
        return;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void queue(String s)
    {
        if(trackOffline || !offlineForced) goto _L2; else goto _L1
_L1:
        return;
_L2:
        ArrayList arraylist = queuedMessages;
        arraylist;
        JVM INSTR monitorenter ;
        queuedMessages.add(s);
        if(trackOffline)
            for(; queuedMessages.size() > offlineLimit; queuedMessages.remove(0));
        break MISSING_BLOCK_LABEL_69;
        Exception exception;
        exception;
        arraylist;
        JVM INSTR monitorexit ;
        throw exception;
        arraylist;
        JVM INSTR monitorexit ;
        if(trackOffline)
            writeToDisk();
        if(!offlineForced)
        {
            setOnline(true);
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
    }

    protected void readFromDisk()
    {
        File file;
        while(cacheFilename == null || ((file = new File(cacheFilename)) == null || !file.exists())) 
            return;
        BufferedReader bufferedreader;
        ArrayList arraylist;
        bufferedreader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"));
        arraylist = new ArrayList();
_L1:
        String s = bufferedreader.readLine();
label0:
        {
            if(s == null)
                break label0;
            try
            {
                arraylist.add(s);
            }
            catch(IOException ioexception)
            {
                ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("ADMS SDK Error: Cannot Read Requests From Disk -- ").append(ioexception.getMessage()).toString());
                return;
            }
        }
          goto _L1
        if(arraylist.size() > 0)
            synchronized(queuedMessages)
            {
                queuedMessages.clear();
                queuedMessages.addAll(arraylist);
            }
        bufferedreader.close();
        return;
        exception;
        arraylist1;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void setOnline(boolean flag)
    {
label0:
        {
            if(!flag)
                break MISSING_BLOCK_LABEL_91;
            synchronized(backgroundMutex)
            {
                if(backgroundThread == null || backgroundThread.cancelled)
                    break label0;
            }
            return;
        }
        if(backgroundThread == null || backgroundThread.cancelled)
        {
            killThread();
            backgroundThread = new WorkerThread(this);
            backgroundThread.start();
        }
        offlineForced = false;
        obj1;
        JVM INSTR monitorexit ;
        return;
        exception1;
        obj1;
        JVM INSTR monitorexit ;
        throw exception1;
        synchronized(backgroundMutex)
        {
            if(backgroundThread != null && !backgroundThread.cancelled)
                killThread();
        }
        if(trackOffline)
            writeToDisk();
        offlineForced = true;
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    protected void writeToDisk()
    {
_L2:
        return;
        File file;
        if(cacheFilename == null || (file = new File(cacheFilename)) == null) goto _L2; else goto _L1
_L1:
        BufferedWriter bufferedwriter = null;
        BufferedWriter bufferedwriter1 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"), 2048);
        ArrayList arraylist = queuedMessages;
        arraylist;
        JVM INSTR monitorenter ;
        ArrayList arraylist1 = new ArrayList(queuedMessages);
        arraylist;
        JVM INSTR monitorexit ;
        for(Iterator iterator = arraylist1.iterator(); iterator.hasNext(); bufferedwriter1.newLine())
        {
            String s = (String)iterator.next();
            bufferedwriter1.write(s, 0, s.length());
        }

        continue; /* Loop/switch isn't completed */
        IOException ioexception1;
        ioexception1;
        bufferedwriter = bufferedwriter1;
_L6:
        ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("ADMS SDK Error: Cannot Write Requests To Disk -- ").append(ioexception1.getMessage()).toString());
        if(bufferedwriter == null) goto _L2; else goto _L3
_L3:
        try
        {
            bufferedwriter.close();
            return;
        }
        catch(IOException ioexception2)
        {
            ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("ADMS SDK Error: Cannot close buffered writer -- ").append(ioexception2.getMessage()).toString());
        }
        return;
        Exception exception1;
        exception1;
_L7:
        arraylist;
        JVM INSTR monitorexit ;
        throw exception1;
        Exception exception;
        exception;
        bufferedwriter = bufferedwriter1;
_L5:
        IOException ioexception3;
        if(bufferedwriter != null)
            try
            {
                bufferedwriter.close();
            }
            catch(IOException ioexception)
            {
                ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("ADMS SDK Error: Cannot close buffered writer -- ").append(ioexception.getMessage()).toString());
            }
        throw exception;
        if(bufferedwriter1 == null) goto _L2; else goto _L4
_L4:
        try
        {
            bufferedwriter1.close();
            return;
        }
        // Misplaced declaration of an exception variable
        catch(IOException ioexception3)
        {
            ADMS_Measurement.sharedInstance().debugLog((new StringBuilder()).append("ADMS SDK Error: Cannot close buffered writer -- ").append(ioexception3.getMessage()).toString());
        }
        return;
        exception;
          goto _L5
        ioexception1;
        bufferedwriter = null;
          goto _L6
        exception1;
          goto _L7
    }

    private final Object backgroundMutex = new Object();
    private WorkerThread backgroundThread;
    protected String cacheFilename;
    private boolean offlineForced;
    protected int offlineLimit;
    private final ArrayList queuedMessages = new ArrayList();
    protected boolean trackOffline;
}
