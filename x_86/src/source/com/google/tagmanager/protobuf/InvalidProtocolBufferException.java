// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.IOException;

// Referenced classes of package com.google.tagmanager.protobuf:
//            MessageLite

public class InvalidProtocolBufferException extends IOException
{

    public InvalidProtocolBufferException(String s)
    {
        super(s);
        unfinishedMessage = null;
    }

    static InvalidProtocolBufferException invalidEndTag()
    {
        return new InvalidProtocolBufferException("Protocol message end-group tag did not match expected tag.");
    }

    static InvalidProtocolBufferException invalidTag()
    {
        return new InvalidProtocolBufferException("Protocol message contained an invalid tag (zero).");
    }

    static InvalidProtocolBufferException invalidUtf8()
    {
        return new InvalidProtocolBufferException("Protocol message had invalid UTF-8.");
    }

    static InvalidProtocolBufferException invalidWireType()
    {
        return new InvalidProtocolBufferException("Protocol message tag had invalid wire type.");
    }

    static InvalidProtocolBufferException malformedVarint()
    {
        return new InvalidProtocolBufferException("CodedInputStream encountered a malformed varint.");
    }

    static InvalidProtocolBufferException negativeSize()
    {
        return new InvalidProtocolBufferException("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
    }

    static InvalidProtocolBufferException parseFailure()
    {
        return new InvalidProtocolBufferException("Failed to parse the message.");
    }

    static InvalidProtocolBufferException recursionLimitExceeded()
    {
        return new InvalidProtocolBufferException("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
    }

    static InvalidProtocolBufferException sizeLimitExceeded()
    {
        return new InvalidProtocolBufferException("Protocol message was too large.  May be malicious.  Use CodedInputStream.setSizeLimit() to increase the size limit.");
    }

    static InvalidProtocolBufferException truncatedMessage()
    {
        return new InvalidProtocolBufferException("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
    }

    public MessageLite getUnfinishedMessage()
    {
        return unfinishedMessage;
    }

    public InvalidProtocolBufferException setUnfinishedMessage(MessageLite messagelite)
    {
        unfinishedMessage = messagelite;
        return this;
    }

    private static final long serialVersionUID = 0xe9924688c2f20054L;
    private MessageLite unfinishedMessage;
}
