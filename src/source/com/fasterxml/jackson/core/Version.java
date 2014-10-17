// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.core;

import java.io.Serializable;

public class Version
    implements Comparable, Serializable
{

    public Version(int i, int j, int k, String s)
    {
        this(i, j, k, s, null, null);
    }

    public Version(int i, int j, int k, String s, String s1, String s2)
    {
        _majorVersion = i;
        _minorVersion = j;
        _patchLevel = k;
        _snapshotInfo = s;
        if(s1 == null)
            s1 = "";
        _groupId = s1;
        if(s2 == null)
            s2 = "";
        _artifactId = s2;
    }

    public static Version unknownVersion()
    {
        return UNKNOWN_VERSION;
    }

    public int compareTo(Version version)
    {
        int i;
        if(version == this)
        {
            i = 0;
        } else
        {
            i = _groupId.compareTo(version._groupId);
            if(i == 0)
            {
                i = _artifactId.compareTo(version._artifactId);
                if(i == 0)
                {
                    i = _majorVersion - version._majorVersion;
                    if(i == 0)
                    {
                        i = _minorVersion - version._minorVersion;
                        if(i == 0)
                            return _patchLevel - version._patchLevel;
                    }
                }
            }
        }
        return i;
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((Version)obj);
    }

    public boolean equals(Object obj)
    {
        if(obj != this)
        {
            if(obj == null)
                return false;
            if(obj.getClass() != getClass())
                return false;
            Version version = (Version)obj;
            if(version._majorVersion != _majorVersion || version._minorVersion != _minorVersion || version._patchLevel != _patchLevel || !version._artifactId.equals(_artifactId) || !version._groupId.equals(_groupId))
                return false;
        }
        return true;
    }

    public String getArtifactId()
    {
        return _artifactId;
    }

    public String getGroupId()
    {
        return _groupId;
    }

    public int getMajorVersion()
    {
        return _majorVersion;
    }

    public int getMinorVersion()
    {
        return _minorVersion;
    }

    public int getPatchLevel()
    {
        return _patchLevel;
    }

    public int hashCode()
    {
        return _artifactId.hashCode() ^ ((_groupId.hashCode() + _majorVersion) - _minorVersion) + _patchLevel;
    }

    public boolean isSnapshot()
    {
        return _snapshotInfo != null && _snapshotInfo.length() > 0;
    }

    public boolean isUknownVersion()
    {
        return this == UNKNOWN_VERSION;
    }

    public String toFullString()
    {
        return (new StringBuilder()).append(_groupId).append('/').append(_artifactId).append('/').append(toString()).toString();
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append(_majorVersion).append('.');
        stringbuilder.append(_minorVersion).append('.');
        stringbuilder.append(_patchLevel);
        if(isSnapshot())
            stringbuilder.append('-').append(_snapshotInfo);
        return stringbuilder.toString();
    }

    private static final Version UNKNOWN_VERSION = new Version(0, 0, 0, null, null, null);
    private static final long serialVersionUID = 1L;
    protected final String _artifactId;
    protected final String _groupId;
    protected final int _majorVersion;
    protected final int _minorVersion;
    protected final int _patchLevel;
    protected final String _snapshotInfo;

}
