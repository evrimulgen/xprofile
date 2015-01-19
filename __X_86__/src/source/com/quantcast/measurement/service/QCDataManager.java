// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.quantcast.measurement.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabaseCorruptException;
import android.os.AsyncTask;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.instrumentation.AsyncTaskInstrumentation;
import com.newrelic.agent.android.tracing.Trace;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.Arrays;
import java.util.List;

// Referenced classes of package com.quantcast.measurement.service:
//            QCDatabaseDAO, QCDataUploader, QCPolicy, QCEvent, 
//            QCLog, QCMeasurement

class QCDataManager
{

    QCDataManager(Context context)
    {
        m_database = new QCDatabaseDAO(context);
        m_uploadCount = 25;
        m_maxUploadCount = 200;
        m_eventCount = m_database.numberOfEvents();
        m_uploading = false;
    }

    QCDatabaseDAO getDataBase()
    {
        return m_database;
    }

    long getEventCount()
    {
        return m_eventCount;
    }

    AsyncTask newDBTask(final QCPolicy policy)
    {
        return new TraceFieldInterface() {

            public void _nr_setTrace(Trace trace)
            {
                try
                {
                    _nr_trace = trace;
                    return;
                }
                catch(Exception exception)
                {
                    return;
                }
            }

            protected transient Integer doInBackground(QCEvent aqcevent[])
            {
                forceUpload = aqcevent[0].shouldForceUpload();
                int j = m_database.writeEvents(Arrays.asList(aqcevent));
                int i = j;
_L2:
                return Integer.valueOf(i);
                SQLiteDatabaseCorruptException sqlitedatabasecorruptexception;
                sqlitedatabasecorruptexception;
                QCLog.e(QCDataManager.TAG, "DB Write error", sqlitedatabasecorruptexception);
                m_database.deleteDB(QCMeasurement.INSTANCE.getAppContext());
                cancel(true);
                i = 0;
                continue; /* Loop/switch isn't completed */
                OutOfMemoryError outofmemoryerror;
                outofmemoryerror;
                QCLog.e(QCDataManager.TAG, "DB Write error", outofmemoryerror);
                System.gc();
                cancel(true);
                i = 0;
                continue; /* Loop/switch isn't completed */
                Throwable throwable;
                throwable;
                QCLog.e(QCDataManager.TAG, "DB Write error", throwable);
                cancel(true);
                i = 0;
                if(true) goto _L2; else goto _L1
_L1:
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                TraceMachine.enterMethod(_nr_trace, "QCDataManager$1#doInBackground", null);
_L1:
                Integer integer = doInBackground((QCEvent[])aobj);
                TraceMachine.exitMethod();
                TraceMachine.unloadTraceContext(this);
                return integer;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCDataManager$1#doInBackground", null);
                  goto _L1
            }

            protected void onPostExecute(Integer integer)
            {
                if(!isCancelled() && integer.intValue() > 0)
                {
                    long l1 = integer + ((java.util) (this)).;
                    QCLog.i(QCDataManager.TAG, (new StringBuilder()).append("Successfully wrote ").append(integer).append(" events! total: ").append(m_eventCount).toString());
                    if(policy != null && (forceUpload || m_eventCount >= (long)m_uploadCount && !m_uploading))
                        uploadEvents(policy);
                    return;
                } else
                {
                    QCLog.w(QCDataManager.TAG, "DB Write canceled or nothing written");
                    return;
                }
            }

            protected volatile void onPostExecute(Object obj)
            {
                TraceMachine.enterMethod(_nr_trace, "QCDataManager$1#onPostExecute", null);
_L1:
                onPostExecute((Integer)obj);
                TraceMachine.exitMethod();
                return;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCDataManager$1#onPostExecute", null);
                  goto _L1
            }

            public Trace _nr_trace;
            private boolean forceUpload;
            final QCDataManager this$0;
            final QCPolicy val$policy;

            
            {
                this$0 = QCDataManager.this;
                policy = qcpolicy;
                super();
            }
        }
;
    }

    AsyncTask newUploadTask(final QCPolicy policy)
    {
        return new TraceFieldInterface() {

            public void _nr_setTrace(Trace trace)
            {
                try
                {
                    _nr_trace = trace;
                    return;
                }
                catch(Exception exception)
                {
                    return;
                }
            }

            protected transient Integer doInBackground(Void avoid[])
            {
                QCLog.i(QCDataManager.TAG, "Starting upload...");
                int i = 0;
                this;
                JVM INSTR monitorenter ;
                android.database.sqlite.SQLiteDatabase sqlitedatabase;
                List list;
                String s;
                sqlitedatabase = m_database.getWritableDatabase();
                list = m_database.getEvents(sqlitedatabase, m_maxUploadCount, policy);
                uploadId = m_uploader.synchronousUploadEvents(list);
                s = uploadId;
                i = 0;
                if(s == null) goto _L2; else goto _L1
_L1:
                boolean flag = m_database.removeEvents(sqlitedatabase, list);
                i = 0;
                if(!flag) goto _L4; else goto _L3
_L3:
                i = list.size();
                QCLog.i(QCDataManager.TAG, (new StringBuilder()).append("Successfully upload ").append(i).append(" events!").toString());
_L5:
                m_database.close();
_L6:
                this;
                JVM INSTR monitorexit ;
                return Integer.valueOf(i);
_L4:
                QCLog.e(QCDataManager.TAG, (new StringBuilder()).append("Failed to remove ").append(list.size()).append(" events").toString());
                i = 0;
                  goto _L5
                SQLiteDatabaseCorruptException sqlitedatabasecorruptexception;
                sqlitedatabasecorruptexception;
                m_database.deleteDB(QCMeasurement.INSTANCE.getAppContext());
                QCLog.e(QCDataManager.TAG, "DB upload error", sqlitedatabasecorruptexception);
                m_database.close();
                  goto _L6
                Exception exception1;
                exception1;
                this;
                JVM INSTR monitorexit ;
                throw exception1;
_L2:
                QCLog.e(QCDataManager.TAG, (new StringBuilder()).append("Failed to upload ").append(list.size()).append(" events").toString());
                i = 0;
                  goto _L5
                OutOfMemoryError outofmemoryerror;
                outofmemoryerror;
                QCLog.e(QCDataManager.TAG, "DB upload error", outofmemoryerror);
                System.gc();
                m_database.close();
                  goto _L6
                Throwable throwable;
                throwable;
                QCLog.e(QCDataManager.TAG, "DB upload error", throwable);
                m_database.close();
                  goto _L6
                Exception exception;
                exception;
                m_database.close();
                throw exception;
                  goto _L5
            }

            protected volatile Object doInBackground(Object aobj[])
            {
                TraceMachine.enterMethod(_nr_trace, "QCDataManager$2#doInBackground", null);
_L1:
                Integer integer = doInBackground((Void[])aobj);
                TraceMachine.exitMethod();
                TraceMachine.unloadTraceContext(this);
                return integer;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCDataManager$2#doInBackground", null);
                  goto _L1
            }

            protected void onPostExecute(Integer integer)
            {
                if(!isCancelled() && integer.intValue() > 0)
                {
                    m_eventCount = Math.max(0L, m_eventCount - (long)integer.intValue());
                    QCMeasurement.INSTANCE.logLatency(uploadId, System.currentTimeMillis() - startTime);
                } else
                {
                    QCLog.w(QCDataManager.TAG, "DB upload canceled or nothing removed");
                }
                m_uploading = false;
            }

            protected volatile void onPostExecute(Object obj)
            {
                TraceMachine.enterMethod(_nr_trace, "QCDataManager$2#onPostExecute", null);
_L1:
                onPostExecute((Integer)obj);
                TraceMachine.exitMethod();
                return;
                NoSuchFieldError nosuchfielderror;
                nosuchfielderror;
                TraceMachine.enterMethod(null, "QCDataManager$2#onPostExecute", null);
                  goto _L1
            }

            protected void onPreExecute()
            {
                m_uploading = true;
                startTime = System.currentTimeMillis();
            }

            public Trace _nr_trace;
            private long startTime;
            final QCDataManager this$0;
            private String uploadId;
            final QCPolicy val$policy;

            
            {
                this$0 = QCDataManager.this;
                policy = qcpolicy;
                super();
            }
        }
;
    }

    void postEvent(QCEvent qcevent, QCPolicy qcpolicy)
    {
        if(qcpolicy.isBlackedOut())
            return;
        AsyncTask asynctask = newDBTask(qcpolicy);
        QCEvent aqcevent[] = {
            qcevent
        };
        if(!(asynctask instanceof AsyncTask))
        {
            asynctask.execute(aqcevent);
            return;
        } else
        {
            AsyncTaskInstrumentation.execute((AsyncTask)asynctask, aqcevent);
            return;
        }
    }

    void setMaxUploadCount(int i)
    {
        m_maxUploadCount = Math.max(2, i);
    }

    void setUploadCount(int i)
    {
        m_uploadCount = Math.max(2, Math.min(m_maxUploadCount, i));
    }

    void uploadEvents(QCPolicy qcpolicy)
    {
        AsyncTask asynctask;
        Void avoid[];
label0:
        {
            if(qcpolicy.policyIsLoaded() && !qcpolicy.isBlackedOut())
            {
                asynctask = newUploadTask(qcpolicy);
                avoid = new Void[0];
                if(asynctask instanceof AsyncTask)
                    break label0;
                asynctask.execute(avoid);
            }
            return;
        }
        AsyncTaskInstrumentation.execute((AsyncTask)asynctask, avoid);
    }

    private static final int DEFAULT_UPLOAD_EVENT_COUNT = 25;
    private static final int MAX_UPLOAD_SIZE = 200;
    private static final int MIN_UPLOAD_SIZE = 2;
    private static final QCLog.Tag TAG = new QCLog.Tag(com/quantcast/measurement/service/QCDataManager);
    private final QCDatabaseDAO m_database;
    private long m_eventCount;
    private int m_maxUploadCount;
    private int m_uploadCount;
    private final QCDataUploader m_uploader = new QCDataUploader();
    private boolean m_uploading;






/*
    static long access$202(QCDataManager qcdatamanager, long l)
    {
        qcdatamanager.m_eventCount = l;
        return l;
    }

*/


/*
    static long access$214(QCDataManager qcdatamanager, long l)
    {
        long l1 = l + qcdatamanager.m_eventCount;
        qcdatamanager.m_eventCount = l1;
        return l1;
    }

*/




/*
    static boolean access$402(QCDataManager qcdatamanager, boolean flag)
    {
        qcdatamanager.m_uploading = flag;
        return flag;
    }

*/


}
