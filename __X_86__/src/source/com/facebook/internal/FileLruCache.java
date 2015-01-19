// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.internal;

import android.content.Context;
import com.facebook.LoggingBehavior;
import com.facebook.Settings;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.*;
import java.security.InvalidParameterException;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicLong;
import org.json.*;

// Referenced classes of package com.facebook.internal:
//            Utility, Logger

public final class FileLruCache
{
    private static class BufferFile
    {

        static void deleteAll(File file)
        {
            File afile[] = file.listFiles(excludeNonBufferFiles());
            if(afile != null)
            {
                int i = afile.length;
                for(int j = 0; j < i; j++)
                    afile[j].delete();

            }
        }

        static FilenameFilter excludeBufferFiles()
        {
            return filterExcludeBufferFiles;
        }

        static FilenameFilter excludeNonBufferFiles()
        {
            return filterExcludeNonBufferFiles;
        }

        static File newFile(File file)
        {
            return new File(file, (new StringBuilder()).append("buffer").append(Long.valueOf(FileLruCache.bufferIndex.incrementAndGet()).toString()).toString());
        }

        private static final String FILE_NAME_PREFIX = "buffer";
        private static final FilenameFilter filterExcludeBufferFiles = new FilenameFilter() {

            public boolean accept(File file, String s)
            {
                return !s.startsWith("buffer");
            }

        }
;
        private static final FilenameFilter filterExcludeNonBufferFiles = new FilenameFilter() {

            public boolean accept(File file, String s)
            {
                return s.startsWith("buffer");
            }

        }
;


        private BufferFile()
        {
        }
    }

    private static class CloseCallbackOutputStream extends OutputStream
    {

        public void close()
            throws IOException
        {
            innerStream.close();
            callback.onClose();
            return;
            Exception exception;
            exception;
            callback.onClose();
            throw exception;
        }

        public void flush()
            throws IOException
        {
            innerStream.flush();
        }

        public void write(int i)
            throws IOException
        {
            innerStream.write(i);
        }

        public void write(byte abyte0[])
            throws IOException
        {
            innerStream.write(abyte0);
        }

        public void write(byte abyte0[], int i, int j)
            throws IOException
        {
            innerStream.write(abyte0, i, j);
        }

        final StreamCloseCallback callback;
        final OutputStream innerStream;

        CloseCallbackOutputStream(OutputStream outputstream, StreamCloseCallback streamclosecallback)
        {
            innerStream = outputstream;
            callback = streamclosecallback;
        }
    }

    private static final class CopyingInputStream extends InputStream
    {

        public int available()
            throws IOException
        {
            return input.available();
        }

        public void close()
            throws IOException
        {
            input.close();
            output.close();
            return;
            Exception exception;
            exception;
            output.close();
            throw exception;
        }

        public void mark(int i)
        {
            throw new UnsupportedOperationException();
        }

        public boolean markSupported()
        {
            return false;
        }

        public int read()
            throws IOException
        {
            int i = input.read();
            if(i >= 0)
                output.write(i);
            return i;
        }

        public int read(byte abyte0[])
            throws IOException
        {
            int i = input.read(abyte0);
            if(i > 0)
                output.write(abyte0, 0, i);
            return i;
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            int k = input.read(abyte0, i, j);
            if(k > 0)
                output.write(abyte0, i, k);
            return k;
        }

        public void reset()
        {
            this;
            JVM INSTR monitorenter ;
            throw new UnsupportedOperationException();
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public long skip(long l)
            throws IOException
        {
            byte abyte0[] = new byte[1024];
            long l1 = 0L;
            do
            {
                int i;
label0:
                {
                    if(l1 < l)
                    {
                        i = read(abyte0, 0, (int)Math.min(l - l1, abyte0.length));
                        if(i >= 0)
                            break label0;
                    }
                    return l1;
                }
                l1 += i;
            } while(true);
        }

        final InputStream input;
        final OutputStream output;

        CopyingInputStream(InputStream inputstream, OutputStream outputstream)
        {
            input = inputstream;
            output = outputstream;
        }
    }

    public static final class Limits
    {

        int getByteCount()
        {
            return byteCount;
        }

        int getFileCount()
        {
            return fileCount;
        }

        void setByteCount(int i)
        {
            if(i < 0)
            {
                throw new InvalidParameterException("Cache byte-count limit must be >= 0");
            } else
            {
                byteCount = i;
                return;
            }
        }

        void setFileCount(int i)
        {
            if(i < 0)
            {
                throw new InvalidParameterException("Cache file count limit must be >= 0");
            } else
            {
                fileCount = i;
                return;
            }
        }

        private int byteCount;
        private int fileCount;

        public Limits()
        {
            fileCount = 1024;
            byteCount = 0x100000;
        }
    }

    private static final class ModifiedFile
        implements Comparable
    {

        public int compareTo(ModifiedFile modifiedfile)
        {
            if(getModified() < modifiedfile.getModified())
                return -1;
            if(getModified() > modifiedfile.getModified())
                return 1;
            else
                return getFile().compareTo(modifiedfile.getFile());
        }

        public volatile int compareTo(Object obj)
        {
            return compareTo((ModifiedFile)obj);
        }

        public boolean equals(Object obj)
        {
            return (obj instanceof ModifiedFile) && compareTo((ModifiedFile)obj) == 0;
        }

        File getFile()
        {
            return file;
        }

        long getModified()
        {
            return modified;
        }

        public int hashCode()
        {
            return 37 * (1073 + file.hashCode()) + (int)(modified % 0x7fffffffL);
        }

        private static final int HASH_MULTIPLIER = 37;
        private static final int HASH_SEED = 29;
        private final File file;
        private final long modified;

        ModifiedFile(File file1)
        {
            file = file1;
            modified = file1.lastModified();
        }
    }

    private static interface StreamCloseCallback
    {

        public abstract void onClose();
    }

    private static final class StreamHeader
    {

        static JSONObject readHeader(InputStream inputstream)
            throws IOException
        {
            JSONTokener jsontokener;
            if(inputstream.read() != 0)
                return null;
            int i = 0;
            for(int j = 0; j < 3; j++)
            {
                int i1 = inputstream.read();
                if(i1 == -1)
                {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, "readHeader: stream.read returned -1 while reading header size");
                    return null;
                }
                i = (i << 8) + (i1 & 0xff);
            }

            byte abyte0[] = new byte[i];
            int l;
            for(int k = 0; k < abyte0.length; k += l)
            {
                l = inputstream.read(abyte0, k, abyte0.length - k);
                if(l < 1)
                {
                    Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, (new StringBuilder()).append("readHeader: stream.read stopped at ").append(Integer.valueOf(k)).append(" when expected ").append(abyte0.length).toString());
                    return null;
                }
            }

            jsontokener = new JSONTokener(new String(abyte0));
            Object obj;
            obj = jsontokener.nextValue();
            if(obj instanceof JSONObject)
                break MISSING_BLOCK_LABEL_218;
            Logger.log(LoggingBehavior.CACHE, FileLruCache.TAG, (new StringBuilder()).append("readHeader: expected JSONObject, got ").append(obj.getClass().getCanonicalName()).toString());
            return null;
            JSONObject jsonobject;
            try
            {
                jsonobject = (JSONObject)obj;
            }
            catch(JSONException jsonexception)
            {
                throw new IOException(jsonexception.getMessage());
            }
            return jsonobject;
        }

        static void writeHeader(OutputStream outputstream, JSONObject jsonobject)
            throws IOException
        {
            String s;
            byte abyte0[];
            if(!(jsonobject instanceof JSONObject))
                s = jsonobject.toString();
            else
                s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
            abyte0 = s.getBytes();
            outputstream.write(0);
            outputstream.write(0xff & abyte0.length >> 16);
            outputstream.write(0xff & abyte0.length >> 8);
            outputstream.write(0xff & abyte0.length >> 0);
            outputstream.write(abyte0);
        }

        private static final int HEADER_VERSION;

        private StreamHeader()
        {
        }
    }


    public FileLruCache(Context context, String s, Limits limits1)
    {
        lastClearCacheTime = new AtomicLong(0L);
        tag = s;
        limits = limits1;
        directory = new File(context.getCacheDir(), s);
        if(directory.mkdirs() || directory.isDirectory())
            BufferFile.deleteAll(directory);
    }

    private void postTrim()
    {
        synchronized(lock)
        {
            if(!isTrimPending)
            {
                isTrimPending = true;
                Settings.getExecutor().execute(new Runnable() {

                    public void run()
                    {
                        trim();
                    }

                    final FileLruCache this$0;

            
            {
                this$0 = FileLruCache.this;
                super();
            }
                }
);
            }
        }
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private void renameToTargetAndTrim(String s, File file)
    {
        if(!file.renameTo(new File(directory, Utility.md5hash(s))))
            file.delete();
        postTrim();
    }

    private void trim()
    {
        synchronized(lock)
        {
            isTrimPending = false;
            isTrimInProgress = true;
        }
        PriorityQueue priorityqueue;
        Logger.log(LoggingBehavior.CACHE, TAG, "trim started");
        priorityqueue = new PriorityQueue();
        long l;
        long l1;
        l = 0L;
        l1 = 0L;
        File afile[] = directory.listFiles(BufferFile.excludeBufferFiles());
        if(afile == null) goto _L2; else goto _L1
_L1:
        int i = afile.length;
        int j = 0;
_L3:
        if(j >= i)
            break; /* Loop/switch isn't completed */
        long l2;
        File file = afile[j];
        ModifiedFile modifiedfile = new ModifiedFile(file);
        priorityqueue.add(modifiedfile);
        Logger.log(LoggingBehavior.CACHE, TAG, (new StringBuilder()).append("  trim considering time=").append(Long.valueOf(modifiedfile.getModified())).append(" name=").append(modifiedfile.getFile().getName()).toString());
        l2 = file.length();
        l += l2;
        l1++;
        j++;
        if(true) goto _L3; else goto _L2
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
_L2:
        File file1;
        if(l <= (long)limits.getByteCount() && l1 <= (long)limits.getFileCount())
            break MISSING_BLOCK_LABEL_310;
        file1 = ((ModifiedFile)priorityqueue.remove()).getFile();
        Logger.log(LoggingBehavior.CACHE, TAG, (new StringBuilder()).append("  trim removing ").append(file1.getName()).toString());
        l -= file1.length();
        l1--;
        file1.delete();
          goto _L2
        Exception exception1;
        exception1;
        synchronized(lock)
        {
            isTrimInProgress = false;
            lock.notifyAll();
        }
        throw exception1;
        synchronized(lock)
        {
            isTrimInProgress = false;
            lock.notifyAll();
        }
        return;
        exception3;
        obj2;
        JVM INSTR monitorexit ;
        throw exception3;
        exception2;
        obj1;
        JVM INSTR monitorexit ;
        throw exception2;
    }

    public void clearCache()
    {
        final File filesToDelete[] = directory.listFiles(BufferFile.excludeBufferFiles());
        lastClearCacheTime.set(System.currentTimeMillis());
        if(filesToDelete != null)
            Settings.getExecutor().execute(new Runnable() {

                public void run()
                {
                    File afile[] = filesToDelete;
                    int i = afile.length;
                    for(int j = 0; j < i; j++)
                        afile[j].delete();

                }

                final FileLruCache this$0;
                final File val$filesToDelete[];

            
            {
                this$0 = FileLruCache.this;
                filesToDelete = afile;
                super();
            }
            }
);
    }

    public InputStream get(String s)
        throws IOException
    {
        return get(s, null);
    }

    public InputStream get(String s, String s1)
        throws IOException
    {
        File file;
        BufferedInputStream bufferedinputstream;
        JSONObject jsonobject;
        file = new File(directory, Utility.md5hash(s));
        FileInputStream fileinputstream;
        try
        {
            fileinputstream = new FileInputStream(file);
        }
        catch(IOException ioexception)
        {
            return null;
        }
        bufferedinputstream = new BufferedInputStream(fileinputstream, 8192);
        jsonobject = StreamHeader.readHeader(bufferedinputstream);
        if(jsonobject == null)
        {
            if(true)
                bufferedinputstream.close();
            return null;
        }
        String s2 = jsonobject.optString("key");
        if(s2 == null)
            break MISSING_BLOCK_LABEL_94;
        boolean flag = s2.equals(s);
        if(flag)
            break MISSING_BLOCK_LABEL_105;
        if(true)
            bufferedinputstream.close();
        return null;
        String s3 = jsonobject.optString("tag", null);
        if(s1 == null && s3 != null)
            break MISSING_BLOCK_LABEL_141;
        if(s1 == null)
            break MISSING_BLOCK_LABEL_152;
        boolean flag1 = s1.equals(s3);
        if(flag1)
            break MISSING_BLOCK_LABEL_152;
        if(true)
            bufferedinputstream.close();
        return null;
        long l = (new Date()).getTime();
        Logger.log(LoggingBehavior.CACHE, TAG, (new StringBuilder()).append("Setting lastModified to ").append(Long.valueOf(l)).append(" for ").append(file.getName()).toString());
        file.setLastModified(l);
        if(false)
            bufferedinputstream.close();
        return bufferedinputstream;
        Exception exception;
        exception;
        if(true)
            bufferedinputstream.close();
        throw exception;
    }

    public InputStream interceptAndPut(String s, InputStream inputstream)
        throws IOException
    {
        return new CopyingInputStream(inputstream, openPutStream(s));
    }

    OutputStream openPutStream(String s)
        throws IOException
    {
        return openPutStream(s, null);
    }

    public OutputStream openPutStream(String s, String s1)
        throws IOException
    {
        BufferedOutputStream bufferedoutputstream;
        File file = BufferFile.newFile(directory);
        file.delete();
        if(!file.createNewFile())
            throw new IOException((new StringBuilder()).append("Could not create file at ").append(file.getAbsolutePath()).toString());
        FileOutputStream fileoutputstream;
        JSONObject jsonobject;
        try
        {
            fileoutputstream = new FileOutputStream(file);
        }
        catch(FileNotFoundException filenotfoundexception)
        {
            Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder()).append("Error creating buffer output stream: ").append(filenotfoundexception).toString());
            throw new IOException(filenotfoundexception.getMessage());
        }
        bufferedoutputstream = new BufferedOutputStream(new CloseCallbackOutputStream(fileoutputstream, new StreamCloseCallback() {

            public void onClose()
            {
                if(bufferFileCreateTime < lastClearCacheTime.get())
                {
                    buffer.delete();
                    return;
                } else
                {
                    renameToTargetAndTrim(key, buffer);
                    return;
                }
            }

            final FileLruCache this$0;
            final File val$buffer;
            final long val$bufferFileCreateTime;
            final String val$key;

            
            {
                this$0 = FileLruCache.this;
                bufferFileCreateTime = l;
                buffer = file;
                key = s;
                super();
            }
        }
), 8192);
        jsonobject = new JSONObject();
        jsonobject.put("key", s);
        if(!Utility.isNullOrEmpty(s1))
            jsonobject.put("tag", s1);
        StreamHeader.writeHeader(bufferedoutputstream, jsonobject);
        if(false)
            bufferedoutputstream.close();
        return bufferedoutputstream;
        JSONException jsonexception;
        jsonexception;
        Logger.log(LoggingBehavior.CACHE, 5, TAG, (new StringBuilder()).append("Error creating JSON header for cache file: ").append(jsonexception).toString());
        throw new IOException(jsonexception.getMessage());
        Exception exception;
        exception;
        if(true)
            bufferedoutputstream.close();
        throw exception;
    }

    long sizeInBytesForTest()
    {
        Object obj = lock;
        obj;
        JVM INSTR monitorenter ;
_L2:
        boolean flag;
        if(isTrimPending)
            break MISSING_BLOCK_LABEL_25;
        flag = isTrimInProgress;
        if(!flag)
            break; /* Loop/switch isn't completed */
        try
        {
            lock.wait();
        }
        catch(InterruptedException interruptedexception) { }
        if(true) goto _L2; else goto _L1
_L1:
        obj;
        JVM INSTR monitorexit ;
        long l;
        File afile[] = directory.listFiles();
        l = 0L;
        if(afile != null)
        {
            int i = afile.length;
            for(int j = 0; j < i; j++)
                l += afile[j].length();

        }
        break MISSING_BLOCK_LABEL_97;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        return l;
    }

    public String toString()
    {
        return (new StringBuilder()).append("{FileLruCache: tag:").append(tag).append(" file:").append(directory.getName()).append("}").toString();
    }

    private static final String HEADER_CACHEKEY_KEY = "key";
    private static final String HEADER_CACHE_CONTENT_TAG_KEY = "tag";
    static final String TAG = com/facebook/internal/FileLruCache.getSimpleName();
    private static final AtomicLong bufferIndex = new AtomicLong();
    private final File directory;
    private boolean isTrimInProgress;
    private boolean isTrimPending;
    private AtomicLong lastClearCacheTime;
    private final Limits limits;
    private final Object lock = new Object();
    private final String tag;





}
