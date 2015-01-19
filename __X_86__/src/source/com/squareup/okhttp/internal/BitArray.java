// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.squareup.okhttp.internal;

import java.util.*;

public interface BitArray
{
    public static final class FixedCapacity
        implements BitArray
    {

        private static int checkInput(int i)
        {
            if(i < 0 || i > 63)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(i);
                throw new IllegalArgumentException(String.format("input must be between 0 and 63: %s", aobj));
            } else
            {
                return i;
            }
        }

        public void clear()
        {
            data = 0L;
        }

        public boolean get(int i)
        {
            return (1L & data >> checkInput(i)) == 1L;
        }

        public void set(int i)
        {
            data = data | 1L << checkInput(i);
        }

        public void shiftLeft(int i)
        {
            data = data << checkInput(i);
        }

        public String toString()
        {
            return Long.toBinaryString(data);
        }

        public BitArray toVariableCapacity()
        {
            return new VariableCapacity(this);
        }

        public void toggle(int i)
        {
            data = data ^ 1L << checkInput(i);
        }

        long data;

        public FixedCapacity()
        {
            data = 0L;
        }
    }

    public static final class VariableCapacity
        implements BitArray
    {

        private static int checkInput(int i)
        {
            if(i < 0)
            {
                Object aobj[] = new Object[1];
                aobj[0] = Integer.valueOf(i);
                throw new IllegalArgumentException(String.format("input must be a positive number: %s", aobj));
            } else
            {
                return i;
            }
        }

        private void growToSize(int i)
        {
            long al[] = new long[i];
            if(data != null)
                System.arraycopy(data, 0, al, 0, data.length);
            data = al;
        }

        private int offsetOf(int i)
        {
            int j = (i + start) / 64;
            if(j > -1 + data.length)
                growToSize(j + 1);
            return j;
        }

        private int shiftOf(int i)
        {
            return (i + start) % 64;
        }

        public void clear()
        {
            Arrays.fill(data, 0L);
        }

        public boolean get(int i)
        {
            checkInput(i);
            int j = offsetOf(i);
            return (data[j] & 1L << shiftOf(i)) != 0L;
        }

        public void set(int i)
        {
            checkInput(i);
            int j = offsetOf(i);
            long al[] = data;
            al[j] = al[j] | 1L << shiftOf(i);
        }

        public void shiftLeft(int i)
        {
            start = start - checkInput(i);
            if(start < 0)
            {
                int j = 1 + start / -64;
                long al[] = new long[j + data.length];
                System.arraycopy(data, 0, al, j, data.length);
                data = al;
                start = 64 + start % 64;
            }
        }

        List toIntegerList()
        {
            ArrayList arraylist = new ArrayList();
            int i = 0;
            for(int j = 64 * data.length - start; i < j; i++)
                if(get(i))
                    arraylist.add(Integer.valueOf(i));

            return arraylist;
        }

        public String toString()
        {
            StringBuilder stringbuilder = new StringBuilder("{");
            List list = toIntegerList();
            int i = 0;
            for(int j = list.size(); i < j; i++)
            {
                if(i > 0)
                    stringbuilder.append(',');
                stringbuilder.append(list.get(i));
            }

            return stringbuilder.append('}').toString();
        }

        public void toggle(int i)
        {
            checkInput(i);
            int j = offsetOf(i);
            long al[] = data;
            al[j] = al[j] ^ 1L << shiftOf(i);
        }

        long data[];
        private int start;

        public VariableCapacity()
        {
            data = new long[1];
        }

        private VariableCapacity(FixedCapacity fixedcapacity)
        {
            long al[] = new long[2];
            al[0] = fixedcapacity.data;
            al[1] = 0L;
            data = al;
        }

    }


    public abstract void clear();

    public abstract boolean get(int i);

    public abstract void set(int i);

    public abstract void shiftLeft(int i);

    public abstract void toggle(int i);
}
