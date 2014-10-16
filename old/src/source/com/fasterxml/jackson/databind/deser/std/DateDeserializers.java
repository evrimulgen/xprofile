// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.deser.std;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.TimeZone;

// Referenced classes of package com.fasterxml.jackson.databind.deser.std:
//            StdScalarDeserializer, FromStringDeserializer

public class DateDeserializers
{
    public static class CalendarDeserializer extends DateBasedDeserializer
    {

        public volatile JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
            throws JsonMappingException
        {
            return super.createContextual(deserializationcontext, beanproperty);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Calendar deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            java.util.Date date = _parseDate(jsonparser, deserializationcontext);
            if(date != null) goto _L2; else goto _L1
_L1:
            Calendar calendar = null;
_L4:
            return calendar;
_L2:
            if(_calendarClass == null)
                return deserializationcontext.constructCalendar(date);
            TimeZone timezone;
            try
            {
                calendar = (Calendar)_calendarClass.newInstance();
                calendar.setTimeInMillis(date.getTime());
                timezone = deserializationcontext.getTimeZone();
            }
            catch(Exception exception)
            {
                throw deserializationcontext.instantiationException(_calendarClass, exception);
            }
            if(timezone == null) goto _L4; else goto _L3
_L3:
            calendar.setTimeZone(timezone);
            return calendar;
        }

        protected CalendarDeserializer withDateFormat(DateFormat dateformat, String s)
        {
            return new CalendarDeserializer(this, dateformat, s);
        }

        protected volatile DateBasedDeserializer withDateFormat(DateFormat dateformat, String s)
        {
            return withDateFormat(dateformat, s);
        }

        public static final CalendarDeserializer gregorianInstance = new CalendarDeserializer(java/util/GregorianCalendar);
        public static final CalendarDeserializer instance = new CalendarDeserializer();
        protected final Class _calendarClass;


        public CalendarDeserializer()
        {
            super(java/util/Calendar);
            _calendarClass = null;
        }

        public CalendarDeserializer(CalendarDeserializer calendardeserializer, DateFormat dateformat, String s)
        {
            super(calendardeserializer, dateformat, s);
            _calendarClass = calendardeserializer._calendarClass;
        }

        public CalendarDeserializer(Class class1)
        {
            super(class1);
            _calendarClass = class1;
        }
    }

    protected static abstract class DateBasedDeserializer extends StdScalarDeserializer
        implements ContextualDeserializer
    {

        protected java.util.Date _parseDate(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            String s;
            if(_customFormat == null || jsonparser.getCurrentToken() != JsonToken.VALUE_STRING)
                break MISSING_BLOCK_LABEL_127;
            s = jsonparser.getText().trim();
            if(s.length() == 0)
                return (java.util.Date)getEmptyValue();
            if(true) goto _L2; else goto _L1
_L1:
            dateformat;
            JVM INSTR monitorenter ;
_L2:
            java.util.Date date;
            synchronized(_customFormat)
            {
                date = _customFormat.parse(s);
            }
            return date;
            exception;
            dateformat;
            JVM INSTR monitorexit ;
            throw exception;
            ParseException parseexception;
            parseexception;
            throw new IllegalArgumentException((new StringBuilder()).append("Failed to parse Date value '").append(s).append("' (format: \"").append(_formatString).append("\"): ").append(parseexception.getMessage()).toString());
            return super._parseDate(jsonparser, deserializationcontext);
        }

        public JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
            throws JsonMappingException
        {
            if(beanproperty != null)
            {
                com.fasterxml.jackson.annotation.JsonFormat.Value value = deserializationcontext.getAnnotationIntrospector().findFormat(beanproperty.getMember());
                if(value != null)
                {
                    TimeZone timezone = value.getTimeZone();
                    String s = value.getPattern();
                    if(s.length() > 0)
                    {
                        java.util.Locale locale = value.getLocale();
                        if(locale == null)
                            locale = deserializationcontext.getLocale();
                        SimpleDateFormat simpledateformat = new SimpleDateFormat(s, locale);
                        DateFormat dateformat;
                        Object obj;
                        TimeZone timezone1;
                        if(timezone == null)
                            timezone1 = deserializationcontext.getTimeZone();
                        else
                            timezone1 = timezone;
                        simpledateformat.setTimeZone(timezone1);
                        this = withDateFormat(simpledateformat, s);
                    } else
                    if(timezone != null)
                    {
                        dateformat = deserializationcontext.getConfig().getDateFormat();
                        if(dateformat.getClass() == com/fasterxml/jackson/databind/util/StdDateFormat)
                        {
                            obj = ((StdDateFormat)dateformat).withTimeZone(timezone);
                        } else
                        {
                            obj = (DateFormat)dateformat.clone();
                            ((DateFormat) (obj)).setTimeZone(timezone);
                        }
                        return withDateFormat(((DateFormat) (obj)), s);
                    }
                }
            }
            return this;
        }

        protected abstract DateBasedDeserializer withDateFormat(DateFormat dateformat, String s);

        protected final DateFormat _customFormat;
        protected final String _formatString;

        protected DateBasedDeserializer(DateBasedDeserializer datebaseddeserializer, DateFormat dateformat, String s)
        {
            super(datebaseddeserializer._valueClass);
            _customFormat = dateformat;
            _formatString = s;
        }

        protected DateBasedDeserializer(Class class1)
        {
            super(class1);
            _customFormat = null;
            _formatString = null;
        }
    }

    public static class DateDeserializer extends DateBasedDeserializer
    {

        public volatile JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
            throws JsonMappingException
        {
            return super.createContextual(deserializationcontext, beanproperty);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public java.util.Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _parseDate(jsonparser, deserializationcontext);
        }

        protected volatile DateBasedDeserializer withDateFormat(DateFormat dateformat, String s)
        {
            return withDateFormat(dateformat, s);
        }

        protected DateDeserializer withDateFormat(DateFormat dateformat, String s)
        {
            return new DateDeserializer(this, dateformat, s);
        }

        public static final DateDeserializer instance = new DateDeserializer();


        public DateDeserializer()
        {
            super(java/util/Date);
        }

        public DateDeserializer(DateDeserializer datedeserializer, DateFormat dateformat, String s)
        {
            super(datedeserializer, dateformat, s);
        }
    }

    public static class SqlDateDeserializer extends DateBasedDeserializer
    {

        public volatile JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
            throws JsonMappingException
        {
            return super.createContextual(deserializationcontext, beanproperty);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Date deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            java.util.Date date = _parseDate(jsonparser, deserializationcontext);
            if(date == null)
                return null;
            else
                return new Date(date.getTime());
        }

        protected volatile DateBasedDeserializer withDateFormat(DateFormat dateformat, String s)
        {
            return withDateFormat(dateformat, s);
        }

        protected SqlDateDeserializer withDateFormat(DateFormat dateformat, String s)
        {
            return new SqlDateDeserializer(this, dateformat, s);
        }

        public static final SqlDateDeserializer instance = new SqlDateDeserializer();


        public SqlDateDeserializer()
        {
            super(java/sql/Date);
        }

        public SqlDateDeserializer(SqlDateDeserializer sqldatedeserializer, DateFormat dateformat, String s)
        {
            super(sqldatedeserializer, dateformat, s);
        }
    }

    protected static class TimeZoneDeserializer extends FromStringDeserializer
    {

        protected volatile Object _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return _deserialize(s, deserializationcontext);
        }

        protected TimeZone _deserialize(String s, DeserializationContext deserializationcontext)
            throws IOException
        {
            return TimeZone.getTimeZone(s);
        }

        public static final TimeZoneDeserializer instance = new TimeZoneDeserializer();


        public TimeZoneDeserializer()
        {
            super(java/util/TimeZone);
        }
    }

    public static class TimestampDeserializer extends DateBasedDeserializer
    {

        public volatile JsonDeserializer createContextual(DeserializationContext deserializationcontext, BeanProperty beanproperty)
            throws JsonMappingException
        {
            return super.createContextual(deserializationcontext, beanproperty);
        }

        public volatile Object deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return deserialize(jsonparser, deserializationcontext);
        }

        public Timestamp deserialize(JsonParser jsonparser, DeserializationContext deserializationcontext)
            throws IOException, JsonProcessingException
        {
            return new Timestamp(_parseDate(jsonparser, deserializationcontext).getTime());
        }

        protected volatile DateBasedDeserializer withDateFormat(DateFormat dateformat, String s)
        {
            return withDateFormat(dateformat, s);
        }

        protected TimestampDeserializer withDateFormat(DateFormat dateformat, String s)
        {
            return new TimestampDeserializer(this, dateformat, s);
        }

        public static final TimestampDeserializer instance = new TimestampDeserializer();


        public TimestampDeserializer()
        {
            super(java/sql/Timestamp);
        }

        public TimestampDeserializer(TimestampDeserializer timestampdeserializer, DateFormat dateformat, String s)
        {
            super(timestampdeserializer, dateformat, s);
        }
    }


    public DateDeserializers()
    {
    }

    public static JsonDeserializer find(Class class1, String s)
    {
        if(!_classNames.contains(s))
            return null;
        if(class1 == java/util/Calendar)
            return CalendarDeserializer.instance;
        if(class1 == java/util/Date)
            return DateDeserializer.instance;
        if(class1 == java/sql/Date)
            return SqlDateDeserializer.instance;
        if(class1 == java/sql/Timestamp)
            return TimestampDeserializer.instance;
        if(class1 == java/util/TimeZone)
            return TimeZoneDeserializer.instance;
        if(class1 == java/util/GregorianCalendar)
            return CalendarDeserializer.gregorianInstance;
        else
            throw new IllegalArgumentException((new StringBuilder()).append("Internal error: can't find deserializer for ").append(s).toString());
    }

    private static final HashSet _classNames;

    static 
    {
        int i = 0;
        _classNames = new HashSet();
        Class aclass[] = {
            java/util/Calendar, java/util/GregorianCalendar, java/sql/Date, java/util/Date, java/sql/Timestamp, java/util/TimeZone
        };
        for(int j = aclass.length; i < j; i++)
        {
            Class class1 = aclass[i];
            _classNames.add(class1.getName());
        }

    }
}
