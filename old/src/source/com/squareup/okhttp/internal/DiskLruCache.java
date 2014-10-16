// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal;

import com.squareup.okhttp.internal.okio.BufferedSink;
import com.squareup.okhttp.internal.okio.BufferedSource;
import com.squareup.okhttp.internal.okio.OkBuffer;
import com.squareup.okhttp.internal.okio.Okio;
import java.io.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Referenced classes of package com.squareup.okhttp.internal:
//            Util, Platform

public final class DiskLruCache
    implements Closeable
{
    public final class Editor
    {

        public void abort()
            throws IOException
        {
            completeEdit(this, false);
        }

        public void abortUnlessCommitted()
        {
            if(committed)
                break MISSING_BLOCK_LABEL_11;
            abort();
            return;
            IOException ioexception;
            ioexception;
        }

        public void commit()
            throws IOException
        {
            if(hasErrors)
            {
                completeEdit(this, false);
                remove(entry.key);
            } else
            {
                completeEdit(this, true);
            }
            committed = true;
        }

        public String getString(int i)
            throws IOException
        {
            InputStream inputstream = newInputStream(i);
            if(inputstream != null)
                return DiskLruCache.inputStreamToString(inputstream);
            else
                return null;
        }

        public InputStream newInputStream(int i)
            throws IOException
        {
            DiskLruCache disklrucache = DiskLruCache.this;
            disklrucache;
            JVM INSTR monitorenter ;
            if(entry.currentEditor != this)
                throw new IllegalStateException();
            break MISSING_BLOCK_LABEL_31;
            Exception exception;
            exception;
            disklrucache;
            JVM INSTR monitorexit ;
            throw exception;
            if(entry.readable)
                break MISSING_BLOCK_LABEL_45;
            disklrucache;
            JVM INSTR monitorexit ;
            return null;
            FileInputStream fileinputstream = new FileInputStream(entry.getCleanFile(i));
            disklrucache;
            JVM INSTR monitorexit ;
            return fileinputstream;
            FileNotFoundException filenotfoundexception;
            filenotfoundexception;
            disklrucache;
            JVM INSTR monitorexit ;
            return null;
        }

        public OutputStream newOutputStream(int i)
            throws IOException
        {
            DiskLruCache disklrucache = DiskLruCache.this;
            disklrucache;
            JVM INSTR monitorenter ;
            if(entry.currentEditor != this)
                throw new IllegalStateException();
            break MISSING_BLOCK_LABEL_31;
            Exception exception;
            exception;
            disklrucache;
            JVM INSTR monitorexit ;
            throw exception;
            File file;
            if(!entry.readable)
                written[i] = true;
            file = entry.getDirtyFile(i);
            FileOutputStream fileoutputstream = new FileOutputStream(file);
_L1:
            FaultHidingOutputStream faulthidingoutputstream = new FaultHidingOutputStream(fileoutputstream);
            disklrucache;
            JVM INSTR monitorexit ;
            return faulthidingoutputstream;
            FileNotFoundException filenotfoundexception;
            filenotfoundexception;
            directory.mkdirs();
            fileoutputstream = new FileOutputStream(file);
              goto _L1
            FileNotFoundException filenotfoundexception1;
            filenotfoundexception1;
            OutputStream outputstream = DiskLruCache.NULL_OUTPUT_STREAM;
            disklrucache;
            JVM INSTR monitorexit ;
            return outputstream;
        }

        public void set(int i, String s)
            throws IOException
        {
            BufferedSink bufferedsink = Okio.buffer(Okio.sink(newOutputStream(i)));
            bufferedsink.writeUtf8(s);
            bufferedsink.close();
        }

        private boolean committed;
        private final Entry entry;
        private boolean hasErrors;
        final DiskLruCache this$0;
        private final boolean written[];




/*
        static boolean access$2302(Editor editor, boolean flag)
        {
            editor.hasErrors = flag;
            return flag;
        }

*/

        private Editor(Entry entry1)
        {
            this$0 = DiskLruCache.this;
            super();
            entry = entry1;
            boolean aflag[];
            if(entry1.readable)
                aflag = null;
            else
                aflag = new boolean[valueCount];
            written = aflag;
        }

    }

    private class Editor.FaultHidingOutputStream extends FilterOutputStream
    {

        public void close()
        {
            try
            {
                out.close();
                return;
            }
            catch(IOException ioexception)
            {
                hasErrors = true;
            }
        }

        public void flush()
        {
            try
            {
                out.flush();
                return;
            }
            catch(IOException ioexception)
            {
                hasErrors = true;
            }
        }

        public void write(int i)
        {
            try
            {
                out.write(i);
                return;
            }
            catch(IOException ioexception)
            {
                hasErrors = true;
            }
        }

        public void write(byte abyte0[], int i, int j)
        {
            try
            {
                out.write(abyte0, i, j);
                return;
            }
            catch(IOException ioexception)
            {
                hasErrors = true;
            }
        }

        final Editor this$1;

        private Editor.FaultHidingOutputStream(OutputStream outputstream)
        {
            this$1 = Editor.this;
            super(outputstream);
        }

    }

    private final class Entry
    {

        private IOException invalidLengths(String as[])
            throws IOException
        {
            throw new IOException((new StringBuilder()).append("unexpected journal line: ").append(Arrays.toString(as)).toString());
        }

        private void setLengths(String as[])
            throws IOException
        {
            if(as.length != valueCount)
                throw invalidLengths(as);
            int i = 0;
            do
            {
                try
                {
                    if(i >= as.length)
                        break;
                    lengths[i] = Long.parseLong(as[i]);
                }
                catch(NumberFormatException numberformatexception)
                {
                    throw invalidLengths(as);
                }
                i++;
            } while(true);
        }

        public File getCleanFile(int i)
        {
            return new File(directory, (new StringBuilder()).append(key).append(".").append(i).toString());
        }

        public File getDirtyFile(int i)
        {
            return new File(directory, (new StringBuilder()).append(key).append(".").append(i).append(".tmp").toString());
        }

        public String getLengths()
            throws IOException
        {
            StringBuilder stringbuilder = new StringBuilder();
            long al[] = lengths;
            int i = al.length;
            for(int j = 0; j < i; j++)
            {
                long l = al[j];
                stringbuilder.append(' ').append(l);
            }

            return stringbuilder.toString();
        }

        private Editor currentEditor;
        private final String key;
        private final long lengths[];
        private boolean readable;
        private long sequenceNumber;
        final DiskLruCache this$0;





/*
        static long access$1202(Entry entry, long l)
        {
            entry.sequenceNumber = l;
            return l;
        }

*/



/*
        static boolean access$602(Entry entry, boolean flag)
        {
            entry.readable = flag;
            return flag;
        }

*/



/*
        static Editor access$702(Entry entry, Editor editor)
        {
            entry.currentEditor = editor;
            return editor;
        }

*/


        private Entry(String s)
        {
            this$0 = DiskLruCache.this;
            super();
            key = s;
            lengths = new long[valueCount];
        }

    }

    public final class Snapshot
        implements Closeable
    {

        public void close()
        {
            InputStream ainputstream[] = ins;
            int i = ainputstream.length;
            for(int j = 0; j < i; j++)
                Util.closeQuietly(ainputstream[j]);

        }

        public Editor edit()
            throws IOException
        {
            return DiskLruCache.this.edit(key, sequenceNumber);
        }

        public InputStream getInputStream(int i)
        {
            return ins[i];
        }

        public long getLength(int i)
        {
            return lengths[i];
        }

        public String getString(int i)
            throws IOException
        {
            return DiskLruCache.inputStreamToString(getInputStream(i));
        }

        private final InputStream ins[];
        private final String key;
        private final long lengths[];
        private final long sequenceNumber;
        final DiskLruCache this$0;

        private Snapshot(String s, long l, InputStream ainputstream[], long al[])
        {
            this$0 = DiskLruCache.this;
            super();
            key = s;
            sequenceNumber = l;
            ins = ainputstream;
            lengths = al;
        }

        Snapshot(String s, long l, InputStream ainputstream[], long al[], _cls1 _pcls1)
        {
            this(s, l, ainputstream, al);
        }
    }


    private DiskLruCache(File file, int i, int j, long l)
    {
        size = 0L;
        nextSequenceNumber = 0L;
        executorService = new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true));
        directory = file;
        appVersion = i;
        journalFile = new File(file, "journal");
        journalFileTmp = new File(file, "journal.tmp");
        journalFileBackup = new File(file, "journal.bkp");
        valueCount = j;
        maxSize = l;
    }

    private void checkNotClosed()
    {
        if(journalWriter == null)
            throw new IllegalStateException("cache is closed");
        else
            return;
    }

    private void completeEdit(Editor editor, boolean flag)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        Entry entry;
        entry = editor.entry;
        if(entry.currentEditor != editor)
            throw new IllegalStateException();
        break MISSING_BLOCK_LABEL_30;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        if(!flag) goto _L2; else goto _L1
_L1:
        if(entry.readable) goto _L2; else goto _L3
_L3:
        int j = 0;
_L7:
        if(j >= valueCount) goto _L2; else goto _L4
_L4:
        if(!editor.written[j])
        {
            editor.abort();
            throw new IllegalStateException((new StringBuilder()).append("Newly created entry didn't create value for index ").append(j).toString());
        }
        if(entry.getDirtyFile(j).exists()) goto _L6; else goto _L5
_L5:
        editor.abort();
_L8:
        this;
        JVM INSTR monitorexit ;
        return;
_L6:
        j++;
          goto _L7
_L2:
        int i = 0;
_L10:
        File file;
        if(i >= valueCount)
            break MISSING_BLOCK_LABEL_224;
        file = entry.getDirtyFile(i);
        if(!flag)
            break MISSING_BLOCK_LABEL_216;
        if(file.exists())
        {
            File file1 = entry.getCleanFile(i);
            file.renameTo(file1);
            long l1 = entry.lengths[i];
            long l2 = file1.length();
            entry.lengths[i] = l2;
            size = l2 + (size - l1);
        }
        break MISSING_BLOCK_LABEL_428;
        deleteIfExists(file);
        break MISSING_BLOCK_LABEL_428;
        redundantOpCount = 1 + redundantOpCount;
        entry.currentEditor = null;
        if(!(flag | entry.readable))
            break MISSING_BLOCK_LABEL_373;
        entry.readable = true;
        journalWriter.writeUtf8((new StringBuilder()).append("CLEAN ").append(entry.key).append(entry.getLengths()).append('\n').toString());
        if(!flag)
            break MISSING_BLOCK_LABEL_331;
        long l = nextSequenceNumber;
        nextSequenceNumber = 1L + l;
        entry.sequenceNumber = l;
_L9:
        journalWriter.flush();
        if(size > maxSize || journalRebuildRequired())
            executorService.execute(cleanupRunnable);
          goto _L8
        lruEntries.remove(entry.key);
        journalWriter.writeUtf8((new StringBuilder()).append("REMOVE ").append(entry.key).append('\n').toString());
          goto _L9
        i++;
          goto _L10
    }

    private static void deleteIfExists(File file)
        throws IOException
    {
        if(!file.delete() && file.exists())
            throw new IOException((new StringBuilder()).append("failed to delete ").append(file).toString());
        else
            return;
    }

    private Editor edit(String s, long l)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        Entry entry;
        checkNotClosed();
        validateKey(s);
        entry = (Entry)lruEntries.get(s);
        if(l == -1L) goto _L2; else goto _L1
_L1:
        Editor editor = null;
        if(entry == null) goto _L4; else goto _L3
_L3:
        long l1 = entry.sequenceNumber;
        int i;
        i = l1 != l;
        editor = null;
        if(i == 0) goto _L2; else goto _L4
_L4:
        this;
        JVM INSTR monitorexit ;
        return editor;
_L2:
        if(entry != null) goto _L6; else goto _L5
_L5:
        entry = new Entry(s);
        lruEntries.put(s, entry);
_L8:
        editor = new Editor(entry);
        entry.currentEditor = editor;
        journalWriter.writeUtf8((new StringBuilder()).append("DIRTY ").append(s).append('\n').toString());
        journalWriter.flush();
        continue; /* Loop/switch isn't completed */
        Exception exception;
        exception;
        throw exception;
_L6:
        Editor editor1 = entry.currentEditor;
        if(editor1 == null) goto _L8; else goto _L7
_L7:
        editor = null;
        if(true) goto _L4; else goto _L9
_L9:
    }

    private static String inputStreamToString(InputStream inputstream)
        throws IOException
    {
        OkBuffer okbuffer = Util.readFully(Okio.source(inputstream));
        return okbuffer.readUtf8(okbuffer.size());
    }

    private boolean journalRebuildRequired()
    {
        return redundantOpCount >= 2000 && redundantOpCount >= lruEntries.size();
    }

    public static DiskLruCache open(File file, int i, int j, long l)
        throws IOException
    {
        DiskLruCache disklrucache;
        if(l <= 0L)
            throw new IllegalArgumentException("maxSize <= 0");
        if(j <= 0)
            throw new IllegalArgumentException("valueCount <= 0");
        File file1 = new File(file, "journal.bkp");
        if(file1.exists())
        {
            File file2 = new File(file, "journal");
            if(file2.exists())
                file1.delete();
            else
                renameTo(file1, file2, false);
        }
        disklrucache = new DiskLruCache(file, i, j, l);
        if(!disklrucache.journalFile.exists())
            break MISSING_BLOCK_LABEL_203;
        disklrucache.readJournal();
        disklrucache.processJournal();
        disklrucache.journalWriter = Okio.buffer(Okio.sink(new FileOutputStream(disklrucache.journalFile, true)));
        return disklrucache;
        IOException ioexception;
        ioexception;
        Platform.get().logW((new StringBuilder()).append("DiskLruCache ").append(file).append(" is corrupt: ").append(ioexception.getMessage()).append(", removing").toString());
        disklrucache.delete();
        file.mkdirs();
        DiskLruCache disklrucache1 = new DiskLruCache(file, i, j, l);
        disklrucache1.rebuildJournal();
        return disklrucache1;
    }

    private void processJournal()
        throws IOException
    {
        deleteIfExists(journalFileTmp);
        for(Iterator iterator = lruEntries.values().iterator(); iterator.hasNext();)
        {
            Entry entry = (Entry)iterator.next();
            if(entry.currentEditor == null)
            {
                int j = 0;
                while(j < valueCount) 
                {
                    size = size + entry.lengths[j];
                    j++;
                }
            } else
            {
                entry.currentEditor = null;
                for(int i = 0; i < valueCount; i++)
                {
                    deleteIfExists(entry.getCleanFile(i));
                    deleteIfExists(entry.getDirtyFile(i));
                }

                iterator.remove();
            }
        }

    }

    private void readJournal()
        throws IOException
    {
        BufferedSource bufferedsource = Okio.buffer(Okio.source(new FileInputStream(journalFile)));
        String s = bufferedsource.readUtf8Line(true);
        String s1 = bufferedsource.readUtf8Line(true);
        String s2 = bufferedsource.readUtf8Line(true);
        String s3 = bufferedsource.readUtf8Line(true);
        String s4 = bufferedsource.readUtf8Line(true);
        if(!"libcore.io.DiskLruCache".equals(s) || !"1".equals(s1) || !Integer.toString(appVersion).equals(s2) || !Integer.toString(valueCount).equals(s3) || !"".equals(s4))
            throw new IOException((new StringBuilder()).append("unexpected journal header: [").append(s).append(", ").append(s1).append(", ").append(s3).append(", ").append(s4).append("]").toString());
        break MISSING_BLOCK_LABEL_196;
        Exception exception;
        exception;
        Util.closeQuietly(bufferedsource);
        throw exception;
        int i = 0;
_L2:
        readJournalLine(bufferedsource.readUtf8Line(true));
        i++;
        if(true) goto _L2; else goto _L1
_L1:
        EOFException eofexception;
        eofexception;
        redundantOpCount = i - lruEntries.size();
        Util.closeQuietly(bufferedsource);
        return;
    }

    private void readJournalLine(String s)
        throws IOException
    {
        int i;
        int j;
        int k;
        i = s.indexOf(' ');
        if(i == -1)
            throw new IOException((new StringBuilder()).append("unexpected journal line: ").append(s).toString());
        j = i + 1;
        k = s.indexOf(' ', j);
        if(k != -1) goto _L2; else goto _L1
_L1:
        String s1 = s.substring(j);
        if(i != "REMOVE".length() || !s.startsWith("REMOVE")) goto _L4; else goto _L3
_L3:
        lruEntries.remove(s1);
_L6:
        return;
_L2:
        s1 = s.substring(j, k);
_L4:
        Entry entry = (Entry)lruEntries.get(s1);
        if(entry == null)
        {
            entry = new Entry(s1);
            lruEntries.put(s1, entry);
        }
        if(k != -1 && i == "CLEAN".length() && s.startsWith("CLEAN"))
        {
            String as[] = s.substring(k + 1).split(" ");
            entry.readable = true;
            entry.currentEditor = null;
            entry.setLengths(as);
            return;
        }
        if(k == -1 && i == "DIRTY".length() && s.startsWith("DIRTY"))
        {
            entry.currentEditor = new Editor(entry);
            return;
        }
        if(k != -1 || i != "READ".length() || !s.startsWith("READ"))
            throw new IOException((new StringBuilder()).append("unexpected journal line: ").append(s).toString());
        if(true) goto _L6; else goto _L5
_L5:
    }

    private void rebuildJournal()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        BufferedSink bufferedsink;
        if(journalWriter != null)
            journalWriter.close();
        bufferedsink = Okio.buffer(Okio.sink(new FileOutputStream(journalFileTmp)));
        Iterator iterator;
        bufferedsink.writeUtf8("libcore.io.DiskLruCache");
        bufferedsink.writeUtf8("\n");
        bufferedsink.writeUtf8("1");
        bufferedsink.writeUtf8("\n");
        bufferedsink.writeUtf8(Integer.toString(appVersion));
        bufferedsink.writeUtf8("\n");
        bufferedsink.writeUtf8(Integer.toString(valueCount));
        bufferedsink.writeUtf8("\n");
        bufferedsink.writeUtf8("\n");
        iterator = lruEntries.values().iterator();
_L1:
        Entry entry;
        do
        {
            if(!iterator.hasNext())
                break MISSING_BLOCK_LABEL_276;
            entry = (Entry)iterator.next();
            if(entry.currentEditor == null)
                break MISSING_BLOCK_LABEL_229;
            bufferedsink.writeUtf8((new StringBuilder()).append("DIRTY ").append(entry.key).append('\n').toString());
        } while(true);
        Exception exception1;
        exception1;
        bufferedsink.close();
        throw exception1;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
        bufferedsink.writeUtf8((new StringBuilder()).append("CLEAN ").append(entry.key).append(entry.getLengths()).append('\n').toString());
          goto _L1
        bufferedsink.close();
        if(journalFile.exists())
            renameTo(journalFile, journalFileBackup, true);
        renameTo(journalFileTmp, journalFile, false);
        journalFileBackup.delete();
        journalWriter = Okio.buffer(Okio.sink(new FileOutputStream(journalFile, true)));
        this;
        JVM INSTR monitorexit ;
    }

    private static void renameTo(File file, File file1, boolean flag)
        throws IOException
    {
        if(flag)
            deleteIfExists(file1);
        if(!file.renameTo(file1))
            throw new IOException();
        else
            return;
    }

    private void trimToSize()
        throws IOException
    {
        while(size > maxSize) 
            remove((String)((java.util.Map.Entry)lruEntries.entrySet().iterator().next()).getKey());
    }

    private void validateKey(String s)
    {
        if(!LEGAL_KEY_PATTERN.matcher(s).matches())
            throw new IllegalArgumentException((new StringBuilder()).append("keys must match regex [a-z0-9_-]{1,64}: \"").append(s).append("\"").toString());
        else
            return;
    }

    public void close()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        BufferedSink bufferedsink = journalWriter;
        if(bufferedsink != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return;
_L2:
        Object aobj[];
        int i;
        aobj = lruEntries.values().toArray();
        i = aobj.length;
        Exception exception;
        Entry entry;
        for(int j = 0; j >= i; j++)
            break MISSING_BLOCK_LABEL_69;

        entry = (Entry)aobj[j];
        if(entry.currentEditor != null)
            entry.currentEditor.abort();
        break MISSING_BLOCK_LABEL_95;
        trimToSize();
        journalWriter.close();
        journalWriter = null;
        if(true) goto _L1; else goto _L3
_L3:
        exception;
        throw exception;
    }

    public void delete()
        throws IOException
    {
        close();
        Util.deleteContents(directory);
    }

    public Editor edit(String s)
        throws IOException
    {
        return edit(s, -1L);
    }

    public void flush()
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        checkNotClosed();
        trimToSize();
        journalWriter.flush();
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public Snapshot get(String s)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        Entry entry;
        checkNotClosed();
        validateKey(s);
        entry = (Entry)lruEntries.get(s);
        Snapshot snapshot = null;
        if(entry != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorexit ;
        return snapshot;
_L2:
        boolean flag = entry.readable;
        snapshot = null;
        if(!flag)
            continue; /* Loop/switch isn't completed */
        InputStream ainputstream[] = new InputStream[valueCount];
        int i = 0;
_L4:
        if(i >= valueCount)
            break; /* Loop/switch isn't completed */
        ainputstream[i] = new FileInputStream(entry.getCleanFile(i));
        i++;
        if(true) goto _L4; else goto _L3
        FileNotFoundException filenotfoundexception;
        filenotfoundexception;
        int j = 0;
_L5:
        int k = valueCount;
        snapshot = null;
        if(j >= k)
            continue; /* Loop/switch isn't completed */
        InputStream inputstream = ainputstream[j];
        snapshot = null;
        if(inputstream == null)
            continue; /* Loop/switch isn't completed */
        Util.closeQuietly(ainputstream[j]);
        j++;
        if(true) goto _L5; else goto _L3
_L3:
        redundantOpCount = 1 + redundantOpCount;
        journalWriter.writeUtf8((new StringBuilder()).append("READ ").append(s).append('\n').toString());
        if(journalRebuildRequired())
            executorService.execute(cleanupRunnable);
        snapshot = new Snapshot(s, entry.sequenceNumber, ainputstream, entry.lengths);
        if(true) goto _L1; else goto _L6
_L6:
        Exception exception;
        exception;
        throw exception;
    }

    public File getDirectory()
    {
        return directory;
    }

    public long getMaxSize()
    {
        this;
        JVM INSTR monitorenter ;
        long l = maxSize;
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    public boolean isClosed()
    {
        return journalWriter == null;
    }

    public boolean remove(String s)
        throws IOException
    {
        this;
        JVM INSTR monitorenter ;
        Entry entry;
        checkNotClosed();
        validateKey(s);
        entry = (Entry)lruEntries.get(s);
        if(entry == null) goto _L2; else goto _L1
_L1:
        Editor editor = entry.currentEditor;
        if(editor == null) goto _L3; else goto _L2
_L2:
        boolean flag = false;
_L7:
        this;
        JVM INSTR monitorexit ;
        return flag;
_L3:
        int i = 0;
_L5:
        if(i >= valueCount)
            break; /* Loop/switch isn't completed */
        deleteIfExists(entry.getCleanFile(i));
        size = size - entry.lengths[i];
        entry.lengths[i] = 0L;
        i++;
        if(true) goto _L5; else goto _L4
_L4:
        redundantOpCount = 1 + redundantOpCount;
        journalWriter.writeUtf8((new StringBuilder()).append("REMOVE ").append(s).append('\n').toString());
        lruEntries.remove(s);
        if(journalRebuildRequired())
            executorService.execute(cleanupRunnable);
        flag = true;
        if(true) goto _L7; else goto _L6
_L6:
        Exception exception;
        exception;
        throw exception;
    }

    public void setMaxSize(long l)
    {
        this;
        JVM INSTR monitorenter ;
        maxSize = l;
        executorService.execute(cleanupRunnable);
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    public long size()
    {
        this;
        JVM INSTR monitorenter ;
        long l = size;
        this;
        JVM INSTR monitorexit ;
        return l;
        Exception exception;
        exception;
        throw exception;
    }

    static final long ANY_SEQUENCE_NUMBER = -1L;
    private static final String CLEAN = "CLEAN";
    private static final String DIRTY = "DIRTY";
    static final String JOURNAL_FILE = "journal";
    static final String JOURNAL_FILE_BACKUP = "journal.bkp";
    static final String JOURNAL_FILE_TEMP = "journal.tmp";
    static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,64}");
    static final String MAGIC = "libcore.io.DiskLruCache";
    private static final OutputStream NULL_OUTPUT_STREAM = new OutputStream() {

        public void write(int i)
            throws IOException
        {
        }

    }
;
    private static final String READ = "READ";
    private static final String REMOVE = "REMOVE";
    static final String VERSION_1 = "1";
    private final int appVersion;
    private final Runnable cleanupRunnable = new Runnable() {

        public void run()
        {
label0:
            {
                synchronized(DiskLruCache.this)
                {
                    if(journalWriter != null)
                        break label0;
                }
                return;
            }
            trimToSize();
            if(journalRebuildRequired())
            {
                rebuildJournal();
                redundantOpCount = 0;
            }
            disklrucache;
            JVM INSTR monitorexit ;
            return;
            exception;
            disklrucache;
            JVM INSTR monitorexit ;
            throw exception;
            IOException ioexception;
            ioexception;
            throw new RuntimeException(ioexception);
        }

        final DiskLruCache this$0;

            
            {
                this$0 = DiskLruCache.this;
                super();
            }
    }
;
    private final File directory;
    final ThreadPoolExecutor executorService;
    private final File journalFile;
    private final File journalFileBackup;
    private final File journalFileTmp;
    private BufferedSink journalWriter;
    private final LinkedHashMap lruEntries = new LinkedHashMap(0, 0.75F, true);
    private long maxSize;
    private long nextSequenceNumber;
    private int redundantOpCount;
    private long size;
    private final int valueCount;













/*
    static int access$402(DiskLruCache disklrucache, int i)
    {
        disklrucache.redundantOpCount = i;
        return i;
    }

*/
}
