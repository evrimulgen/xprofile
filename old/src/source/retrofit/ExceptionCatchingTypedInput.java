// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.io.IOException;
import java.io.InputStream;
import retrofit.mime.TypedInput;

class ExceptionCatchingTypedInput
    implements TypedInput
{
    private static class ExceptionCatchingInputStream extends InputStream
    {

        public int available()
            throws IOException
        {
            int i;
            try
            {
                i = _flddelegate.available();
            }
            catch(IOException ioexception)
            {
                thrownException = ioexception;
                throw ioexception;
            }
            return i;
        }

        public void close()
            throws IOException
        {
            try
            {
                _flddelegate.close();
                return;
            }
            catch(IOException ioexception)
            {
                thrownException = ioexception;
                throw ioexception;
            }
        }

        public void mark(int i)
        {
            this;
            JVM INSTR monitorenter ;
            _flddelegate.mark(i);
            this;
            JVM INSTR monitorexit ;
            return;
            Exception exception;
            exception;
            throw exception;
        }

        public boolean markSupported()
        {
            return _flddelegate.markSupported();
        }

        public int read()
            throws IOException
        {
            int i;
            try
            {
                i = _flddelegate.read();
            }
            catch(IOException ioexception)
            {
                thrownException = ioexception;
                throw ioexception;
            }
            return i;
        }

        public int read(byte abyte0[])
            throws IOException
        {
            int i;
            try
            {
                i = _flddelegate.read(abyte0);
            }
            catch(IOException ioexception)
            {
                thrownException = ioexception;
                throw ioexception;
            }
            return i;
        }

        public int read(byte abyte0[], int i, int j)
            throws IOException
        {
            int k;
            try
            {
                k = _flddelegate.read(abyte0, i, j);
            }
            catch(IOException ioexception)
            {
                thrownException = ioexception;
                throw ioexception;
            }
            return k;
        }

        public void reset()
            throws IOException
        {
            this;
            JVM INSTR monitorenter ;
            _flddelegate.reset();
            this;
            JVM INSTR monitorexit ;
            return;
            IOException ioexception;
            ioexception;
            thrownException = ioexception;
            throw ioexception;
            Exception exception;
            exception;
            this;
            JVM INSTR monitorexit ;
            throw exception;
        }

        public long skip(long l)
            throws IOException
        {
            long l1;
            try
            {
                l1 = _flddelegate.skip(l);
            }
            catch(IOException ioexception)
            {
                thrownException = ioexception;
                throw ioexception;
            }
            return l1;
        }

        private final InputStream _flddelegate;
        private IOException thrownException;


        ExceptionCatchingInputStream(InputStream inputstream)
        {
            _flddelegate = inputstream;
        }
    }


    ExceptionCatchingTypedInput(TypedInput typedinput)
        throws IOException
    {
        _flddelegate = typedinput;
        delegateStream = new ExceptionCatchingInputStream(typedinput.in());
    }

    IOException getThrownException()
    {
        return delegateStream.thrownException;
    }

    public InputStream in()
        throws IOException
    {
        return delegateStream;
    }

    public long length()
    {
        return _flddelegate.length();
    }

    public String mimeType()
    {
        return _flddelegate.mimeType();
    }

    boolean threwException()
    {
        return delegateStream.thrownException != null;
    }

    private final TypedInput _flddelegate;
    private final ExceptionCatchingInputStream delegateStream;
}
