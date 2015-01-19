// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager.protobuf;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

// Referenced classes of package com.google.tagmanager.protobuf:
//            AbstractMutableMessageLite, ByteString, InvalidProtocolBufferException, MessageLite, 
//            MutableMessageLite, GeneratedMessageLite, WireFormat, ExtensionRegistryLite, 
//            CodedInputStream, FieldSet, CodedOutputStream, Parser

public abstract class GeneratedMutableMessageLite extends AbstractMutableMessageLite
    implements Serializable
{
    public static abstract class ExtendableMutableMessage extends GeneratedMutableMessageLite
    {

        private void ensureExtensionsIsMutable()
        {
            if(extensions.isImmutable())
                extensions = extensions.clone();
        }

        private void verifyExtensionContainingType(GeneratedMessageLite.GeneratedExtension generatedextension)
        {
            if(generatedextension.getContainingTypeDefaultInstance() != getDefaultInstanceForType())
                throw new IllegalArgumentException("This extension is for a different message type.  Please make sure that you are not suppressing any generics type warnings.");
            else
                return;
        }

        public final ExtendableMutableMessage addExtension(GeneratedMessageLite.GeneratedExtension generatedextension, Object obj)
        {
            assertMutable();
            verifyExtensionContainingType(generatedextension);
            ensureExtensionsIsMutable();
            extensions.addRepeatedField(generatedextension.descriptor, generatedextension.singularToFieldSetType(obj));
            return this;
        }

        public ExtendableMutableMessage clear()
        {
            assertMutable();
            extensions = FieldSet.emptySet();
            return (ExtendableMutableMessage)clear();
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public final ExtendableMutableMessage clearExtension(GeneratedMessageLite.GeneratedExtension generatedextension)
        {
            assertMutable();
            verifyExtensionContainingType(generatedextension);
            ensureExtensionsIsMutable();
            extensions.clearField(generatedextension.descriptor);
            return this;
        }

        protected boolean extensionsAreInitialized()
        {
            return extensions.isInitialized();
        }

        protected int extensionsSerializedSize()
        {
            return extensions.getSerializedSize();
        }

        protected int extensionsSerializedSizeAsMessageSet()
        {
            return extensions.getMessageSetSerializedSize();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public final Object getExtension(GeneratedMessageLite.GeneratedExtension generatedextension)
        {
            verifyExtensionContainingType(generatedextension);
            Object obj = extensions.getField(generatedextension.descriptor);
            if(obj == null)
                return generatedextension.defaultValue;
            if(generatedextension.descriptor.isRepeated)
                return Collections.unmodifiableList((List)generatedextension.fromFieldSetType(obj));
            else
                return generatedextension.fromFieldSetType(obj);
        }

        public final Object getExtension(GeneratedMessageLite.GeneratedExtension generatedextension, int i)
        {
            verifyExtensionContainingType(generatedextension);
            return generatedextension.singularFromFieldSetType(extensions.getRepeatedField(generatedextension.descriptor, i));
        }

        public final int getExtensionCount(GeneratedMessageLite.GeneratedExtension generatedextension)
        {
            verifyExtensionContainingType(generatedextension);
            return extensions.getRepeatedFieldCount(generatedextension.descriptor);
        }

        public final MutableMessageLite getMutableExtension(GeneratedMessageLite.GeneratedExtension generatedextension)
        {
            assertMutable();
            verifyExtensionContainingType(generatedextension);
            ensureExtensionsIsMutable();
            GeneratedMessageLite.ExtensionDescriptor extensiondescriptor = generatedextension.descriptor;
            if(extensiondescriptor.getLiteJavaType() != WireFormat.JavaType.MESSAGE)
                throw new UnsupportedOperationException("getMutableExtension() called on a non-Message type.");
            if(extensiondescriptor.isRepeated())
                throw new UnsupportedOperationException("getMutableExtension() called on a repeated type.");
            Object obj = extensions.getField(generatedextension.descriptor);
            if(obj == null)
            {
                MutableMessageLite mutablemessagelite = ((MutableMessageLite)generatedextension.defaultValue).newMessageForType();
                extensions.setField(generatedextension.descriptor, mutablemessagelite);
                return mutablemessagelite;
            } else
            {
                return (MutableMessageLite)obj;
            }
        }

        public final boolean hasExtension(GeneratedMessageLite.GeneratedExtension generatedextension)
        {
            verifyExtensionContainingType(generatedextension);
            return extensions.hasField(generatedextension.descriptor);
        }

        public MessageLite immutableCopy()
        {
            GeneratedMessageLite.ExtendableBuilder extendablebuilder = (GeneratedMessageLite.ExtendableBuilder)internalCopyToBuilder(this, internalImmutableDefault());
            extendablebuilder.internalSetExtensionSet(extensions.cloneWithAllFieldsToImmutable());
            return extendablebuilder.buildPartial();
        }

        void internalSetExtensionSet(FieldSet fieldset)
        {
            extensions = fieldset;
        }

        protected final void mergeExtensionFields(ExtendableMutableMessage extendablemutablemessage)
        {
            ensureExtensionsIsMutable();
            extensions.mergeFrom(extendablemutablemessage.extensions);
        }

        protected ExtensionWriter newExtensionWriter()
        {
            return new ExtensionWriter(false);
        }

        protected ExtensionWriter newMessageSetExtensionWriter()
        {
            return new ExtensionWriter(true);
        }

        protected boolean parseUnknownField(CodedInputStream codedinputstream, CodedOutputStream codedoutputstream, ExtensionRegistryLite extensionregistrylite, int i)
            throws IOException
        {
            ensureExtensionsIsMutable();
            return GeneratedMutableMessageLite.parseUnknownField(extensions, getDefaultInstanceForType(), codedinputstream, codedoutputstream, extensionregistrylite, i);
        }

        public final ExtendableMutableMessage setExtension(GeneratedMessageLite.GeneratedExtension generatedextension, int i, Object obj)
        {
            assertMutable();
            verifyExtensionContainingType(generatedextension);
            ensureExtensionsIsMutable();
            extensions.setRepeatedField(generatedextension.descriptor, i, generatedextension.singularToFieldSetType(obj));
            return this;
        }

        public final ExtendableMutableMessage setExtension(GeneratedMessageLite.GeneratedExtension generatedextension, Object obj)
        {
            assertMutable();
            verifyExtensionContainingType(generatedextension);
            ensureExtensionsIsMutable();
            extensions.setField(generatedextension.descriptor, generatedextension.toFieldSetType(obj));
            return this;
        }

        private FieldSet extensions;


        protected ExtendableMutableMessage()
        {
            extensions = FieldSet.emptySet();
        }
    }

    protected class ExtendableMutableMessage.ExtensionWriter
    {

        public void writeUntil(int i, CodedOutputStream codedoutputstream)
            throws IOException
        {
            while(next != null && ((GeneratedMessageLite.ExtensionDescriptor)next.getKey()).getNumber() < i) 
            {
                GeneratedMessageLite.ExtensionDescriptor extensiondescriptor = (GeneratedMessageLite.ExtensionDescriptor)next.getKey();
                if(messageSetWireFormat && extensiondescriptor.getLiteJavaType() == WireFormat.JavaType.MESSAGE && !extensiondescriptor.isRepeated())
                    codedoutputstream.writeMessageSetExtension(extensiondescriptor.getNumber(), (MessageLite)next.getValue());
                else
                    FieldSet.writeField(extensiondescriptor, next.getValue(), codedoutputstream);
                if(iter.hasNext())
                    next = (java.util.Map.Entry)iter.next();
                else
                    next = null;
            }
        }

        private final Iterator iter;
        private final boolean messageSetWireFormat;
        private java.util.Map.Entry next;
        final ExtendableMutableMessage this$0;

        private ExtendableMutableMessage.ExtensionWriter(boolean flag)
        {
            this$0 = ExtendableMutableMessage.this;
            super();
            iter = extensions.iterator();
            if(iter.hasNext())
                next = (java.util.Map.Entry)iter.next();
            messageSetWireFormat = flag;
        }

    }

    static final class SerializedForm
        implements Serializable
    {

        protected Object readResolve()
            throws ObjectStreamException
        {
            MutableMessageLite mutablemessagelite;
            try
            {
                mutablemessagelite = (MutableMessageLite)Class.forName(messageClassName).getMethod("newMessage", new Class[0]).invoke(null, new Object[0]);
                if(!mutablemessagelite.mergeFrom(CodedInputStream.newInstance(asBytes)))
                    throw new RuntimeException("Unable to understand proto buffer");
            }
            catch(ClassNotFoundException classnotfoundexception)
            {
                throw new RuntimeException("Unable to find proto buffer class", classnotfoundexception);
            }
            catch(NoSuchMethodException nosuchmethodexception)
            {
                throw new RuntimeException("Unable to find newMessage method", nosuchmethodexception);
            }
            catch(IllegalAccessException illegalaccessexception)
            {
                throw new RuntimeException("Unable to call newMessage method", illegalaccessexception);
            }
            catch(InvocationTargetException invocationtargetexception)
            {
                throw new RuntimeException("Error calling newMessage", invocationtargetexception.getCause());
            }
            return mutablemessagelite;
        }

        private static final long serialVersionUID;
        private byte asBytes[];
        private String messageClassName;

        SerializedForm(MutableMessageLite mutablemessagelite)
        {
            messageClassName = mutablemessagelite.getClass().getName();
            asBytes = mutablemessagelite.toByteArray();
        }
    }


    public GeneratedMutableMessageLite()
    {
        unknownFields = ByteString.EMPTY;
    }

    static MessageLite.Builder internalCopyToBuilder(MutableMessageLite mutablemessagelite, MessageLite messagelite)
    {
        MessageLite.Builder builder = messagelite.newBuilderForType();
        try
        {
            builder.mergeFrom(mutablemessagelite.toByteArray());
        }
        catch(InvalidProtocolBufferException invalidprotocolbufferexception)
        {
            throw new RuntimeException("Failed to parse serialized bytes (should not happen)");
        }
        return builder;
    }

    protected static MessageLite internalImmutableDefault(String s)
    {
        MessageLite messagelite;
        try
        {
            messagelite = (MessageLite)GeneratedMessageLite.invokeOrDie(GeneratedMessageLite.getMethodOrDie(Class.forName(s), "getDefaultInstance", new Class[0]), null, new Object[0]);
        }
        catch(ClassNotFoundException classnotfoundexception)
        {
            throw new UnsupportedOperationException("Cannot load the corresponding immutable class. Please add necessary dependencies.");
        }
        return messagelite;
    }

    static boolean parseUnknownField(FieldSet fieldset, MutableMessageLite mutablemessagelite, CodedInputStream codedinputstream, CodedOutputStream codedoutputstream, ExtensionRegistryLite extensionregistrylite, int i)
        throws IOException
    {
        GeneratedMessageLite.GeneratedExtension generatedextension;
        boolean flag;
        int j = WireFormat.getTagWireType(i);
        generatedextension = extensionregistrylite.findLiteExtensionByNumber(mutablemessagelite, WireFormat.getTagFieldNumber(i));
        flag = false;
        boolean flag1;
        if(generatedextension == null)
            flag1 = true;
        else
        if(j == FieldSet.getWireFormatForFieldType(generatedextension.descriptor.getLiteType(), false))
        {
            flag = false;
            flag1 = false;
        } else
        if(generatedextension.descriptor.isRepeated && generatedextension.descriptor.type.isPackable() && j == FieldSet.getWireFormatForFieldType(generatedextension.descriptor.getLiteType(), true))
        {
            flag = true;
            flag1 = false;
        } else
        {
            flag1 = true;
            flag = false;
        }
        if(flag1)
            return codedinputstream.skipField(i, codedoutputstream);
        if(!flag) goto _L2; else goto _L1
_L1:
        int l = codedinputstream.pushLimit(codedinputstream.readRawVarint32());
        if(generatedextension.descriptor.getLiteType() == WireFormat.FieldType.ENUM)
        {
            Internal.EnumLite enumlite;
            for(; codedinputstream.getBytesUntilLimit() > 0; fieldset.addRepeatedField(generatedextension.descriptor, generatedextension.singularToFieldSetType(enumlite)))
            {
                int i1 = codedinputstream.readEnum();
                enumlite = generatedextension.descriptor.getEnumType().findValueByNumber(i1);
                if(enumlite == null)
                    return true;
            }

        } else
        {
            Object obj1;
            for(; codedinputstream.getBytesUntilLimit() > 0; fieldset.addRepeatedField(generatedextension.descriptor, obj1))
                obj1 = FieldSet.readPrimitiveFieldForMutable(codedinputstream, generatedextension.descriptor.getLiteType(), false);

        }
        codedinputstream.popLimit(l);
_L6:
        return true;
_L2:
        static class _cls1
        {

            static final int $SwitchMap$com$google$protobuf$WireFormat$JavaType[];

            static 
            {
                $SwitchMap$com$google$protobuf$WireFormat$JavaType = new int[WireFormat.JavaType.values().length];
                try
                {
                    $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.MESSAGE.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$google$protobuf$WireFormat$JavaType[WireFormat.JavaType.ENUM.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1)
                {
                    return;
                }
            }
        }

        _cls1..SwitchMap.com.google.protobuf.WireFormat.JavaType[generatedextension.descriptor.getLiteJavaType().ordinal()];
        JVM INSTR tableswitch 1 2: default 296
    //                   1 341
    //                   2 401;
           goto _L3 _L4 _L5
_L3:
        Object obj = FieldSet.readPrimitiveFieldForMutable(codedinputstream, generatedextension.descriptor.getLiteType(), false);
_L7:
        if(generatedextension.descriptor.isRepeated())
            fieldset.addRepeatedField(generatedextension.descriptor, generatedextension.singularToFieldSetType(obj));
        else
            fieldset.setField(generatedextension.descriptor, generatedextension.singularToFieldSetType(obj));
        if(true) goto _L6; else goto _L4
_L4:
        MutableMessageLite mutablemessagelite1 = ((MutableMessageLite)generatedextension.messageDefaultInstance).newMessageForType();
        if(generatedextension.descriptor.getLiteType() == WireFormat.FieldType.GROUP)
            codedinputstream.readGroup(generatedextension.getNumber(), mutablemessagelite1, extensionregistrylite);
        else
            codedinputstream.readMessage(mutablemessagelite1, extensionregistrylite);
        obj = mutablemessagelite1;
          goto _L7
_L5:
        int k = codedinputstream.readEnum();
        obj = generatedextension.descriptor.getEnumType().findValueByNumber(k);
        if(obj == null)
        {
            codedoutputstream.writeRawVarint32(i);
            codedoutputstream.writeUInt32NoTag(k);
            return true;
        }
          goto _L7
    }

    public GeneratedMutableMessageLite clear()
    {
        assertMutable();
        unknownFields = ByteString.EMPTY;
        return this;
    }

    public volatile MutableMessageLite clear()
    {
        return clear();
    }

    public abstract GeneratedMutableMessageLite getDefaultInstanceForType();

    public volatile MessageLite getDefaultInstanceForType()
    {
        return getDefaultInstanceForType();
    }

    public Parser getParserForType()
    {
        throw new UnsupportedOperationException("This is supposed to be overridden by subclasses.");
    }

    public MessageLite immutableCopy()
    {
        MessageLite messagelite = internalImmutableDefault();
        if(this == getDefaultInstanceForType())
            return messagelite;
        else
            return internalCopyToBuilder(this, messagelite).buildPartial();
    }

    protected abstract MessageLite internalImmutableDefault();

    public abstract GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite);

    protected boolean parseUnknownField(CodedInputStream codedinputstream, CodedOutputStream codedoutputstream, ExtensionRegistryLite extensionregistrylite, int i)
        throws IOException
    {
        return codedinputstream.skipField(i, codedoutputstream);
    }

    protected Object writeReplace()
        throws ObjectStreamException
    {
        return new SerializedForm(this);
    }

    private static final long serialVersionUID = 1L;
    protected ByteString unknownFields;
}
