// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.containertag.proto;

import com.google.tagmanager.protobuf.*;
import java.io.*;
import java.util.*;

public final class Debug
{
    public static final class DataLayerEventEvaluationInfo extends GeneratedMessageLite
        implements DataLayerEventEvaluationInfoOrBuilder
    {

        public static DataLayerEventEvaluationInfo getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
            results_ = Collections.emptyList();
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(DataLayerEventEvaluationInfo datalayereventevaluationinfo)
        {
            return newBuilder().mergeFrom(datalayereventevaluationinfo);
        }

        public static DataLayerEventEvaluationInfo parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseDelimitedFrom(inputstream);
        }

        public static DataLayerEventEvaluationInfo parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static DataLayerEventEvaluationInfo parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseFrom(bytestring);
        }

        public static DataLayerEventEvaluationInfo parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static DataLayerEventEvaluationInfo parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseFrom(codedinputstream);
        }

        public static DataLayerEventEvaluationInfo parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static DataLayerEventEvaluationInfo parseFrom(InputStream inputstream)
            throws IOException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseFrom(inputstream);
        }

        public static DataLayerEventEvaluationInfo parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static DataLayerEventEvaluationInfo parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseFrom(abyte0);
        }

        public static DataLayerEventEvaluationInfo parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (DataLayerEventEvaluationInfo)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof DataLayerEventEvaluationInfo))
                return super.equals(obj);
            DataLayerEventEvaluationInfo datalayereventevaluationinfo = (DataLayerEventEvaluationInfo)obj;
            boolean flag;
            boolean flag1;
            if(true && hasRulesEvaluation() == datalayereventevaluationinfo.hasRulesEvaluation())
                flag = true;
            else
                flag = false;
            if(hasRulesEvaluation())
                if(flag && getRulesEvaluation().equals(datalayereventevaluationinfo.getRulesEvaluation()))
                    flag = true;
                else
                    flag = false;
            if(flag && getResultsList().equals(datalayereventevaluationinfo.getResultsList()))
                flag1 = true;
            else
                flag1 = false;
            return flag1;
        }

        public DataLayerEventEvaluationInfo getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public ResolvedFunctionCall getResults(int i)
        {
            return (ResolvedFunctionCall)results_.get(i);
        }

        public int getResultsCount()
        {
            return results_.size();
        }

        public List getResultsList()
        {
            return results_;
        }

        public ResolvedFunctionCallOrBuilder getResultsOrBuilder(int i)
        {
            return (ResolvedFunctionCallOrBuilder)results_.get(i);
        }

        public List getResultsOrBuilderList()
        {
            return results_;
        }

        public RuleEvaluationStepInfo getRulesEvaluation()
        {
            return rulesEvaluation_;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 1 & bitField0_;
            int k = 0;
            if(j == 1)
                k = 0 + CodedOutputStream.computeMessageSize(1, rulesEvaluation_);
            for(int l = 0; l < results_.size(); l++)
                k += CodedOutputStream.computeMessageSize(2, (MessageLite)results_.get(l));

            int i1 = k + unknownFields.size();
            memoizedSerializedSize = i1;
            return i1;
        }

        public boolean hasRulesEvaluation()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Debug$DataLayerEventEvaluationInfo.hashCode();
            if(hasRulesEvaluation())
                i = 53 * (1 + i * 37) + getRulesEvaluation().hashCode();
            if(getResultsCount() > 0)
                i = 53 * (2 + i * 37) + getResultsList().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableDebug$DataLayerEventEvaluationInfo");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            if(hasRulesEvaluation() && !getRulesEvaluation().isInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            }
            for(int i = 0; i < getResultsCount(); i++)
                if(!getResults(i).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            memoizedIsInitialized = 1;
            return true;
        }

        public Builder newBuilderForType()
        {
            return newBuilder();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder newBuilderForType()
        {
            return newBuilderForType();
        }

        public Builder toBuilder()
        {
            return newBuilder(this);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder toBuilder()
        {
            return toBuilder();
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedoutputstream)
            throws IOException
        {
            getSerializedSize();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessage(1, rulesEvaluation_);
            for(int i = 0; i < results_.size(); i++)
                codedoutputstream.writeMessage(2, (MessageLite)results_.get(i));

            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static Parser PARSER = new AbstractParser() {

            public DataLayerEventEvaluationInfo parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new DataLayerEventEvaluationInfo(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int RESULTS_FIELD_NUMBER = 2;
        public static final int RULES_EVALUATION_FIELD_NUMBER = 1;
        private static final DataLayerEventEvaluationInfo defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List results_;
        private RuleEvaluationStepInfo rulesEvaluation_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new DataLayerEventEvaluationInfo(true);
            defaultInstance.initFields();
        }


/*
        static RuleEvaluationStepInfo access$4502(DataLayerEventEvaluationInfo datalayereventevaluationinfo, RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            datalayereventevaluationinfo.rulesEvaluation_ = ruleevaluationstepinfo;
            return ruleevaluationstepinfo;
        }

*/



/*
        static List access$4602(DataLayerEventEvaluationInfo datalayereventevaluationinfo, List list)
        {
            datalayereventevaluationinfo.results_ = list;
            return list;
        }

*/


/*
        static int access$4702(DataLayerEventEvaluationInfo datalayereventevaluationinfo, int i)
        {
            datalayereventevaluationinfo.bitField0_ = i;
            return i;
        }

*/


        private DataLayerEventEvaluationInfo(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            int i;
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            i = 0;
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L16:
            if(flag) goto _L2; else goto _L1
_L1:
            int j = codedinputstream.readTag();
            j;
            JVM INSTR lookupswitch 3: default 84
        //                       0: 386
        //                       10: 103
        //                       18: 231;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, j))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            int k = 1 & bitField0_;
            RuleEvaluationStepInfo.Builder builder = null;
            if(k != 1) goto _L8; else goto _L7
_L7:
            builder = rulesEvaluation_.toBuilder();
_L8:
            rulesEvaluation_ = (RuleEvaluationStepInfo)codedinputstream.readMessage(RuleEvaluationStepInfo.PARSER, extensionregistrylite);
            if(builder == null) goto _L10; else goto _L9
_L9:
            builder.mergeFrom(rulesEvaluation_);
            rulesEvaluation_ = builder.buildPartial();
_L10:
            bitField0_ = 1 | bitField0_;
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((i & 2) == 2)
                results_ = Collections.unmodifiableList(results_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L14:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            if((i & 2) == 2)
                break MISSING_BLOCK_LABEL_253;
            results_ = new ArrayList();
            i |= 2;
            results_.add(codedinputstream.readMessage(ResolvedFunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L2:
            if((i & 2) == 2)
                results_ = Collections.unmodifiableList(results_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L12:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L12; else goto _L11
_L11:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L14; else goto _L13
_L13:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L16; else goto _L15
_L15:
        }


        private DataLayerEventEvaluationInfo(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private DataLayerEventEvaluationInfo(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class DataLayerEventEvaluationInfo.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements DataLayerEventEvaluationInfoOrBuilder
    {

        private static DataLayerEventEvaluationInfo.Builder create()
        {
            return new DataLayerEventEvaluationInfo.Builder();
        }

        private void ensureResultsIsMutable()
        {
            if((2 & bitField0_) != 2)
            {
                results_ = new ArrayList(results_);
                bitField0_ = 2 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public DataLayerEventEvaluationInfo.Builder addAllResults(Iterable iterable)
        {
            ensureResultsIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, results_);
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder addResults(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureResultsIsMutable();
            results_.add(i, builder.build());
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder addResults(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureResultsIsMutable();
                results_.add(i, resolvedfunctioncall);
                return this;
            }
        }

        public DataLayerEventEvaluationInfo.Builder addResults(ResolvedFunctionCall.Builder builder)
        {
            ensureResultsIsMutable();
            results_.add(builder.build());
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder addResults(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureResultsIsMutable();
                results_.add(resolvedfunctioncall);
                return this;
            }
        }

        public DataLayerEventEvaluationInfo build()
        {
            DataLayerEventEvaluationInfo datalayereventevaluationinfo = buildPartial();
            if(!datalayereventevaluationinfo.isInitialized())
                throw newUninitializedMessageException(datalayereventevaluationinfo);
            else
                return datalayereventevaluationinfo;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public DataLayerEventEvaluationInfo buildPartial()
        {
            DataLayerEventEvaluationInfo datalayereventevaluationinfo = new DataLayerEventEvaluationInfo(this);
            int i = 1 & bitField0_;
            int j = 0;
            if(i == 1)
                j = false | true;
            datalayereventevaluationinfo.rulesEvaluation_ = rulesEvaluation_;
            if((2 & bitField0_) == 2)
            {
                results_ = Collections.unmodifiableList(results_);
                bitField0_ = -3 & bitField0_;
            }
            datalayereventevaluationinfo.results_ = results_;
            datalayereventevaluationinfo.bitField0_ = j;
            return datalayereventevaluationinfo;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public DataLayerEventEvaluationInfo.Builder clear()
        {
            super.clear();
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
            bitField0_ = -2 & bitField0_;
            results_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public DataLayerEventEvaluationInfo.Builder clearResults()
        {
            results_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder clearRulesEvaluation()
        {
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder clone()
        {
            return create().mergeFrom(buildPartial());
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public DataLayerEventEvaluationInfo getDefaultInstanceForType()
        {
            return DataLayerEventEvaluationInfo.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedFunctionCall getResults(int i)
        {
            return (ResolvedFunctionCall)results_.get(i);
        }

        public int getResultsCount()
        {
            return results_.size();
        }

        public List getResultsList()
        {
            return Collections.unmodifiableList(results_);
        }

        public RuleEvaluationStepInfo getRulesEvaluation()
        {
            return rulesEvaluation_;
        }

        public boolean hasRulesEvaluation()
        {
            return (1 & bitField0_) == 1;
        }

        public final boolean isInitialized()
        {
            if(!hasRulesEvaluation() || getRulesEvaluation().isInitialized()) goto _L2; else goto _L1
_L1:
            return false;
_L2:
            int i = 0;
label0:
            do
            {
label1:
                {
                    if(i >= getResultsCount())
                        break label1;
                    if(!getResults(i).isInitialized())
                        break label0;
                    i++;
                }
            } while(true);
            if(true) goto _L1; else goto _L3
_L3:
            return true;
        }

        public DataLayerEventEvaluationInfo.Builder mergeFrom(DataLayerEventEvaluationInfo datalayereventevaluationinfo)
        {
            if(datalayereventevaluationinfo == DataLayerEventEvaluationInfo.getDefaultInstance())
                return this;
            if(datalayereventevaluationinfo.hasRulesEvaluation())
                mergeRulesEvaluation(datalayereventevaluationinfo.getRulesEvaluation());
            if(!datalayereventevaluationinfo.results_.isEmpty())
                if(results_.isEmpty())
                {
                    results_ = datalayereventevaluationinfo.results_;
                    bitField0_ = -3 & bitField0_;
                } else
                {
                    ensureResultsIsMutable();
                    results_.addAll(datalayereventevaluationinfo.results_);
                }
            setUnknownFields(getUnknownFields().concat(datalayereventevaluationinfo.unknownFields));
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            DataLayerEventEvaluationInfo datalayereventevaluationinfo = null;
            DataLayerEventEvaluationInfo datalayereventevaluationinfo1 = (DataLayerEventEvaluationInfo)DataLayerEventEvaluationInfo.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(datalayereventevaluationinfo1 != null)
                mergeFrom(datalayereventevaluationinfo1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            datalayereventevaluationinfo = (DataLayerEventEvaluationInfo)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(datalayereventevaluationinfo != null)
                mergeFrom(datalayereventevaluationinfo);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((DataLayerEventEvaluationInfo)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public DataLayerEventEvaluationInfo.Builder mergeRulesEvaluation(RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            if((1 & bitField0_) == 1 && rulesEvaluation_ != RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_ = RuleEvaluationStepInfo.newBuilder(rulesEvaluation_).mergeFrom(ruleevaluationstepinfo).buildPartial();
            else
                rulesEvaluation_ = ruleevaluationstepinfo;
            bitField0_ = 1 | bitField0_;
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder removeResults(int i)
        {
            ensureResultsIsMutable();
            results_.remove(i);
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder setResults(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureResultsIsMutable();
            results_.set(i, builder.build());
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder setResults(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureResultsIsMutable();
                results_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public DataLayerEventEvaluationInfo.Builder setRulesEvaluation(RuleEvaluationStepInfo.Builder builder)
        {
            rulesEvaluation_ = builder.build();
            bitField0_ = 1 | bitField0_;
            return this;
        }

        public DataLayerEventEvaluationInfo.Builder setRulesEvaluation(RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            if(ruleevaluationstepinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                rulesEvaluation_ = ruleevaluationstepinfo;
                bitField0_ = 1 | bitField0_;
                return this;
            }
        }

        private int bitField0_;
        private List results_;
        private RuleEvaluationStepInfo rulesEvaluation_;


        private DataLayerEventEvaluationInfo.Builder()
        {
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
            results_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }
    }

    public static interface DataLayerEventEvaluationInfoOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract ResolvedFunctionCall getResults(int i);

        public abstract int getResultsCount();

        public abstract List getResultsList();

        public abstract RuleEvaluationStepInfo getRulesEvaluation();

        public abstract boolean hasRulesEvaluation();
    }

    public static final class DebugEvents extends GeneratedMessageLite
        implements DebugEventsOrBuilder
    {

        public static DebugEvents getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            event_ = Collections.emptyList();
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(DebugEvents debugevents)
        {
            return newBuilder().mergeFrom(debugevents);
        }

        public static DebugEvents parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (DebugEvents)PARSER.parseDelimitedFrom(inputstream);
        }

        public static DebugEvents parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (DebugEvents)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static DebugEvents parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (DebugEvents)PARSER.parseFrom(bytestring);
        }

        public static DebugEvents parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (DebugEvents)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static DebugEvents parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (DebugEvents)PARSER.parseFrom(codedinputstream);
        }

        public static DebugEvents parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (DebugEvents)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static DebugEvents parseFrom(InputStream inputstream)
            throws IOException
        {
            return (DebugEvents)PARSER.parseFrom(inputstream);
        }

        public static DebugEvents parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (DebugEvents)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static DebugEvents parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (DebugEvents)PARSER.parseFrom(abyte0);
        }

        public static DebugEvents parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (DebugEvents)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof DebugEvents))
                return super.equals(obj);
            DebugEvents debugevents = (DebugEvents)obj;
            boolean flag;
            if(true && getEventList().equals(debugevents.getEventList()))
                flag = true;
            else
                flag = false;
            return flag;
        }

        public DebugEvents getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public EventInfo getEvent(int i)
        {
            return (EventInfo)event_.get(i);
        }

        public int getEventCount()
        {
            return event_.size();
        }

        public List getEventList()
        {
            return event_;
        }

        public EventInfoOrBuilder getEventOrBuilder(int i)
        {
            return (EventInfoOrBuilder)event_.get(i);
        }

        public List getEventOrBuilderList()
        {
            return event_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 0;
            for(int k = 0; k < event_.size(); k++)
                j += CodedOutputStream.computeMessageSize(1, (MessageLite)event_.get(k));

            int l = j + unknownFields.size();
            memoizedSerializedSize = l;
            return l;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Debug$DebugEvents.hashCode();
            if(getEventCount() > 0)
                i = 53 * (1 + i * 37) + getEventList().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableDebug$DebugEvents");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            for(int i = 0; i < getEventCount(); i++)
                if(!getEvent(i).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            memoizedIsInitialized = 1;
            return true;
        }

        public Builder newBuilderForType()
        {
            return newBuilder();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder newBuilderForType()
        {
            return newBuilderForType();
        }

        public Builder toBuilder()
        {
            return newBuilder(this);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder toBuilder()
        {
            return toBuilder();
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedoutputstream)
            throws IOException
        {
            getSerializedSize();
            for(int i = 0; i < event_.size(); i++)
                codedoutputstream.writeMessage(1, (MessageLite)event_.get(i));

            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int EVENT_FIELD_NUMBER = 1;
        public static Parser PARSER = new AbstractParser() {

            public DebugEvents parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new DebugEvents(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        private static final DebugEvents defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private List event_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new DebugEvents(true);
            defaultInstance.initFields();
        }



/*
        static List access$302(DebugEvents debugevents, List list)
        {
            debugevents.event_ = list;
            return list;
        }

*/


        private DebugEvents(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            boolean flag;
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag1;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            flag = false;
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag1 = false;
_L11:
            if(flag1) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 2: default 76
        //                       0: 300
        //                       10: 95;
               goto _L3 _L4 _L5
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag1 = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if((flag & true))
                break MISSING_BLOCK_LABEL_117;
            event_ = new ArrayList();
            flag |= true;
            event_.add(codedinputstream.readMessage(EventInfo.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((flag & true))
                event_ = Collections.unmodifiableList(event_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L9:
            makeExtensionsImmutable();
            throw exception1;
_L2:
            if((flag & true))
                event_ = Collections.unmodifiableList(event_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L7:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L7; else goto _L6
_L6:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L9; else goto _L8
_L8:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag1 = true;
            if(true) goto _L11; else goto _L10
_L10:
        }


        private DebugEvents(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private DebugEvents(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class DebugEvents.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements DebugEventsOrBuilder
    {

        private static DebugEvents.Builder create()
        {
            return new DebugEvents.Builder();
        }

        private void ensureEventIsMutable()
        {
            if((1 & bitField0_) != 1)
            {
                event_ = new ArrayList(event_);
                bitField0_ = 1 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public DebugEvents.Builder addAllEvent(Iterable iterable)
        {
            ensureEventIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, event_);
            return this;
        }

        public DebugEvents.Builder addEvent(int i, EventInfo.Builder builder)
        {
            ensureEventIsMutable();
            event_.add(i, builder.build());
            return this;
        }

        public DebugEvents.Builder addEvent(int i, EventInfo eventinfo)
        {
            if(eventinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEventIsMutable();
                event_.add(i, eventinfo);
                return this;
            }
        }

        public DebugEvents.Builder addEvent(EventInfo.Builder builder)
        {
            ensureEventIsMutable();
            event_.add(builder.build());
            return this;
        }

        public DebugEvents.Builder addEvent(EventInfo eventinfo)
        {
            if(eventinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEventIsMutable();
                event_.add(eventinfo);
                return this;
            }
        }

        public DebugEvents build()
        {
            DebugEvents debugevents = buildPartial();
            if(!debugevents.isInitialized())
                throw newUninitializedMessageException(debugevents);
            else
                return debugevents;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public DebugEvents buildPartial()
        {
            DebugEvents debugevents = new DebugEvents(this);
            int _tmp = bitField0_;
            if((1 & bitField0_) == 1)
            {
                event_ = Collections.unmodifiableList(event_);
                bitField0_ = -2 & bitField0_;
            }
            debugevents.event_ = event_;
            return debugevents;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public DebugEvents.Builder clear()
        {
            super.clear();
            event_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public DebugEvents.Builder clearEvent()
        {
            event_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public DebugEvents.Builder clone()
        {
            return create().mergeFrom(buildPartial());
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public DebugEvents getDefaultInstanceForType()
        {
            return DebugEvents.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public EventInfo getEvent(int i)
        {
            return (EventInfo)event_.get(i);
        }

        public int getEventCount()
        {
            return event_.size();
        }

        public List getEventList()
        {
            return Collections.unmodifiableList(event_);
        }

        public final boolean isInitialized()
        {
            for(int i = 0; i < getEventCount(); i++)
                if(!getEvent(i).isInitialized())
                    return false;

            return true;
        }

        public DebugEvents.Builder mergeFrom(DebugEvents debugevents)
        {
            if(debugevents == DebugEvents.getDefaultInstance())
                return this;
            if(!debugevents.event_.isEmpty())
                if(event_.isEmpty())
                {
                    event_ = debugevents.event_;
                    bitField0_ = -2 & bitField0_;
                } else
                {
                    ensureEventIsMutable();
                    event_.addAll(debugevents.event_);
                }
            setUnknownFields(getUnknownFields().concat(debugevents.unknownFields));
            return this;
        }

        public DebugEvents.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            DebugEvents debugevents = null;
            DebugEvents debugevents1 = (DebugEvents)DebugEvents.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(debugevents1 != null)
                mergeFrom(debugevents1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            debugevents = (DebugEvents)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(debugevents != null)
                mergeFrom(debugevents);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((DebugEvents)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public DebugEvents.Builder removeEvent(int i)
        {
            ensureEventIsMutable();
            event_.remove(i);
            return this;
        }

        public DebugEvents.Builder setEvent(int i, EventInfo.Builder builder)
        {
            ensureEventIsMutable();
            event_.set(i, builder.build());
            return this;
        }

        public DebugEvents.Builder setEvent(int i, EventInfo eventinfo)
        {
            if(eventinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEventIsMutable();
                event_.set(i, eventinfo);
                return this;
            }
        }

        private int bitField0_;
        private List event_;


        private DebugEvents.Builder()
        {
            event_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }
    }

    public static interface DebugEventsOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract EventInfo getEvent(int i);

        public abstract int getEventCount();

        public abstract List getEventList();
    }

    public static final class EventInfo extends GeneratedMessageLite
        implements EventInfoOrBuilder
    {

        public static EventInfo getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            eventType_ = EventType.DATA_LAYER_EVENT;
            containerVersion_ = "";
            containerId_ = "";
            key_ = "";
            macroResult_ = MacroEvaluationInfo.getDefaultInstance();
            dataLayerEventResult_ = DataLayerEventEvaluationInfo.getDefaultInstance();
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(EventInfo eventinfo)
        {
            return newBuilder().mergeFrom(eventinfo);
        }

        public static EventInfo parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (EventInfo)PARSER.parseDelimitedFrom(inputstream);
        }

        public static EventInfo parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (EventInfo)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static EventInfo parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (EventInfo)PARSER.parseFrom(bytestring);
        }

        public static EventInfo parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (EventInfo)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static EventInfo parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (EventInfo)PARSER.parseFrom(codedinputstream);
        }

        public static EventInfo parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (EventInfo)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static EventInfo parseFrom(InputStream inputstream)
            throws IOException
        {
            return (EventInfo)PARSER.parseFrom(inputstream);
        }

        public static EventInfo parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (EventInfo)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static EventInfo parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (EventInfo)PARSER.parseFrom(abyte0);
        }

        public static EventInfo parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (EventInfo)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof EventInfo))
                return super.equals(obj);
            EventInfo eventinfo = (EventInfo)obj;
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            if(true && hasEventType() == eventinfo.hasEventType())
                flag = true;
            else
                flag = false;
            if(hasEventType())
                if(flag && getEventType() == eventinfo.getEventType())
                    flag = true;
                else
                    flag = false;
            if(flag && hasContainerVersion() == eventinfo.hasContainerVersion())
                flag1 = true;
            else
                flag1 = false;
            if(hasContainerVersion())
                if(flag1 && getContainerVersion().equals(eventinfo.getContainerVersion()))
                    flag1 = true;
                else
                    flag1 = false;
            if(flag1 && hasContainerId() == eventinfo.hasContainerId())
                flag2 = true;
            else
                flag2 = false;
            if(hasContainerId())
                if(flag2 && getContainerId().equals(eventinfo.getContainerId()))
                    flag2 = true;
                else
                    flag2 = false;
            if(flag2 && hasKey() == eventinfo.hasKey())
                flag3 = true;
            else
                flag3 = false;
            if(hasKey())
                if(flag3 && getKey().equals(eventinfo.getKey()))
                    flag3 = true;
                else
                    flag3 = false;
            if(flag3 && hasMacroResult() == eventinfo.hasMacroResult())
                flag4 = true;
            else
                flag4 = false;
            if(hasMacroResult())
                if(flag4 && getMacroResult().equals(eventinfo.getMacroResult()))
                    flag4 = true;
                else
                    flag4 = false;
            if(flag4 && hasDataLayerEventResult() == eventinfo.hasDataLayerEventResult())
                flag5 = true;
            else
                flag5 = false;
            if(hasDataLayerEventResult())
                if(flag5 && getDataLayerEventResult().equals(eventinfo.getDataLayerEventResult()))
                    flag5 = true;
                else
                    flag5 = false;
            return flag5;
        }

        public String getContainerId()
        {
            Object obj = containerId_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                containerId_ = s;
            return s;
        }

        public ByteString getContainerIdBytes()
        {
            Object obj = containerId_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                containerId_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public String getContainerVersion()
        {
            Object obj = containerVersion_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                containerVersion_ = s;
            return s;
        }

        public ByteString getContainerVersionBytes()
        {
            Object obj = containerVersion_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                containerVersion_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public DataLayerEventEvaluationInfo getDataLayerEventResult()
        {
            return dataLayerEventResult_;
        }

        public EventInfo getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public EventType getEventType()
        {
            return eventType_;
        }

        public String getKey()
        {
            Object obj = key_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                key_ = s;
            return s;
        }

        public ByteString getKeyBytes()
        {
            Object obj = key_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                key_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public MacroEvaluationInfo getMacroResult()
        {
            return macroResult_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 1 & bitField0_;
            int k = 0;
            if(j == 1)
                k = 0 + CodedOutputStream.computeEnumSize(1, eventType_.getNumber());
            if((2 & bitField0_) == 2)
                k += CodedOutputStream.computeBytesSize(2, getContainerVersionBytes());
            if((4 & bitField0_) == 4)
                k += CodedOutputStream.computeBytesSize(3, getContainerIdBytes());
            if((8 & bitField0_) == 8)
                k += CodedOutputStream.computeBytesSize(4, getKeyBytes());
            if((0x10 & bitField0_) == 16)
                k += CodedOutputStream.computeMessageSize(6, macroResult_);
            if((0x20 & bitField0_) == 32)
                k += CodedOutputStream.computeMessageSize(7, dataLayerEventResult_);
            int l = k + unknownFields.size();
            memoizedSerializedSize = l;
            return l;
        }

        public boolean hasContainerId()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasContainerVersion()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasDataLayerEventResult()
        {
            return (0x20 & bitField0_) == 32;
        }

        public boolean hasEventType()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasKey()
        {
            return (8 & bitField0_) == 8;
        }

        public boolean hasMacroResult()
        {
            return (0x10 & bitField0_) == 16;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Debug$EventInfo.hashCode();
            if(hasEventType())
                i = 53 * (1 + i * 37) + Internal.hashEnum(getEventType());
            if(hasContainerVersion())
                i = 53 * (2 + i * 37) + getContainerVersion().hashCode();
            if(hasContainerId())
                i = 53 * (3 + i * 37) + getContainerId().hashCode();
            if(hasKey())
                i = 53 * (4 + i * 37) + getKey().hashCode();
            if(hasMacroResult())
                i = 53 * (6 + i * 37) + getMacroResult().hashCode();
            if(hasDataLayerEventResult())
                i = 53 * (7 + i * 37) + getDataLayerEventResult().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableDebug$EventInfo");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            if(hasMacroResult() && !getMacroResult().isInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            }
            if(hasDataLayerEventResult() && !getDataLayerEventResult().isInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
            }
        }

        public Builder newBuilderForType()
        {
            return newBuilder();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder newBuilderForType()
        {
            return newBuilderForType();
        }

        public Builder toBuilder()
        {
            return newBuilder(this);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder toBuilder()
        {
            return toBuilder();
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedoutputstream)
            throws IOException
        {
            getSerializedSize();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeEnum(1, eventType_.getNumber());
            if((2 & bitField0_) == 2)
                codedoutputstream.writeBytes(2, getContainerVersionBytes());
            if((4 & bitField0_) == 4)
                codedoutputstream.writeBytes(3, getContainerIdBytes());
            if((8 & bitField0_) == 8)
                codedoutputstream.writeBytes(4, getKeyBytes());
            if((0x10 & bitField0_) == 16)
                codedoutputstream.writeMessage(6, macroResult_);
            if((0x20 & bitField0_) == 32)
                codedoutputstream.writeMessage(7, dataLayerEventResult_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int CONTAINER_ID_FIELD_NUMBER = 3;
        public static final int CONTAINER_VERSION_FIELD_NUMBER = 2;
        public static final int DATA_LAYER_EVENT_RESULT_FIELD_NUMBER = 7;
        public static final int EVENT_TYPE_FIELD_NUMBER = 1;
        public static final int KEY_FIELD_NUMBER = 4;
        public static final int MACRO_RESULT_FIELD_NUMBER = 6;
        public static Parser PARSER = new AbstractParser() {

            public EventInfo parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new EventInfo(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        private static final EventInfo defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private Object containerId_;
        private Object containerVersion_;
        private DataLayerEventEvaluationInfo dataLayerEventResult_;
        private EventType eventType_;
        private Object key_;
        private MacroEvaluationInfo macroResult_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new EventInfo(true);
            defaultInstance.initFields();
        }



/*
        static Object access$1002(EventInfo eventinfo, Object obj)
        {
            eventinfo.containerId_ = obj;
            return obj;
        }

*/



/*
        static Object access$1102(EventInfo eventinfo, Object obj)
        {
            eventinfo.key_ = obj;
            return obj;
        }

*/


/*
        static MacroEvaluationInfo access$1202(EventInfo eventinfo, MacroEvaluationInfo macroevaluationinfo)
        {
            eventinfo.macroResult_ = macroevaluationinfo;
            return macroevaluationinfo;
        }

*/


/*
        static DataLayerEventEvaluationInfo access$1302(EventInfo eventinfo, DataLayerEventEvaluationInfo datalayereventevaluationinfo)
        {
            eventinfo.dataLayerEventResult_ = datalayereventevaluationinfo;
            return datalayereventevaluationinfo;
        }

*/


/*
        static int access$1402(EventInfo eventinfo, int i)
        {
            eventinfo.bitField0_ = i;
            return i;
        }

*/



/*
        static EventType access$802(EventInfo eventinfo, EventType eventtype)
        {
            eventinfo.eventType_ = eventtype;
            return eventtype;
        }

*/



/*
        static Object access$902(EventInfo eventinfo, Object obj)
        {
            eventinfo.containerVersion_ = obj;
            return obj;
        }

*/

        private EventInfo(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L26:
            if(flag) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 7: default 112
        //                       0: 543
        //                       8: 131
        //                       18: 235
        //                       26: 260
        //                       34: 285
        //                       50: 311
        //                       58: 392;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            int l;
            EventType eventtype;
            l = codedinputstream.readEnum();
            eventtype = EventType.valueOf(l);
            if(eventtype != null) goto _L12; else goto _L11
_L11:
            codedoutputstream.writeRawVarint32(i);
            codedoutputstream.writeRawVarint32(l);
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L24:
            makeExtensionsImmutable();
            throw exception1;
_L12:
            bitField0_ = 1 | bitField0_;
            eventType_ = eventtype;
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L6:
            ByteString bytestring2 = codedinputstream.readBytes();
            bitField0_ = 2 | bitField0_;
            containerVersion_ = bytestring2;
            continue; /* Loop/switch isn't completed */
_L7:
            ByteString bytestring1 = codedinputstream.readBytes();
            bitField0_ = 4 | bitField0_;
            containerId_ = bytestring1;
            continue; /* Loop/switch isn't completed */
_L8:
            ByteString bytestring = codedinputstream.readBytes();
            bitField0_ = 8 | bitField0_;
            key_ = bytestring;
            continue; /* Loop/switch isn't completed */
_L9:
            int k = 0x10 & bitField0_;
            MacroEvaluationInfo.Builder builder1 = null;
            if(k != 16) goto _L14; else goto _L13
_L13:
            builder1 = macroResult_.toBuilder();
_L14:
            macroResult_ = (MacroEvaluationInfo)codedinputstream.readMessage(MacroEvaluationInfo.PARSER, extensionregistrylite);
            if(builder1 == null) goto _L16; else goto _L15
_L15:
            builder1.mergeFrom(macroResult_);
            macroResult_ = builder1.buildPartial();
_L16:
            bitField0_ = 0x10 | bitField0_;
            continue; /* Loop/switch isn't completed */
_L10:
            int j = 0x20 & bitField0_;
            DataLayerEventEvaluationInfo.Builder builder = null;
            if(j != 32) goto _L18; else goto _L17
_L17:
            builder = dataLayerEventResult_.toBuilder();
_L18:
            dataLayerEventResult_ = (DataLayerEventEvaluationInfo)codedinputstream.readMessage(DataLayerEventEvaluationInfo.PARSER, extensionregistrylite);
            if(builder == null) goto _L20; else goto _L19
_L19:
            builder.mergeFrom(dataLayerEventResult_);
            dataLayerEventResult_ = builder.buildPartial();
_L20:
            bitField0_ = 0x20 | bitField0_;
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L22:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L22; else goto _L21
_L21:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L24; else goto _L23
_L23:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L26; else goto _L25
_L25:
        }


        private EventInfo(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private EventInfo(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class EventInfo.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements EventInfoOrBuilder
    {

        private static EventInfo.Builder create()
        {
            return new EventInfo.Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public EventInfo build()
        {
            EventInfo eventinfo = buildPartial();
            if(!eventinfo.isInitialized())
                throw newUninitializedMessageException(eventinfo);
            else
                return eventinfo;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public EventInfo buildPartial()
        {
            EventInfo eventinfo = new EventInfo(this);
            int i = bitField0_;
            int j = i & 1;
            int k = 0;
            if(j == 1)
                k = false | true;
            eventinfo.eventType_ = eventType_;
            if((i & 2) == 2)
                k |= 2;
            eventinfo.containerVersion_ = containerVersion_;
            if((i & 4) == 4)
                k |= 4;
            eventinfo.containerId_ = containerId_;
            if((i & 8) == 8)
                k |= 8;
            eventinfo.key_ = key_;
            if((i & 0x10) == 16)
                k |= 0x10;
            eventinfo.macroResult_ = macroResult_;
            if((i & 0x20) == 32)
                k |= 0x20;
            eventinfo.dataLayerEventResult_ = dataLayerEventResult_;
            eventinfo.bitField0_ = k;
            return eventinfo;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public EventInfo.Builder clear()
        {
            super.clear();
            eventType_ = EventInfo.EventType.DATA_LAYER_EVENT;
            bitField0_ = -2 & bitField0_;
            containerVersion_ = "";
            bitField0_ = -3 & bitField0_;
            containerId_ = "";
            bitField0_ = -5 & bitField0_;
            key_ = "";
            bitField0_ = -9 & bitField0_;
            macroResult_ = MacroEvaluationInfo.getDefaultInstance();
            bitField0_ = 0xffffffef & bitField0_;
            dataLayerEventResult_ = DataLayerEventEvaluationInfo.getDefaultInstance();
            bitField0_ = 0xffffffdf & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public EventInfo.Builder clearContainerId()
        {
            bitField0_ = -5 & bitField0_;
            containerId_ = EventInfo.getDefaultInstance().getContainerId();
            return this;
        }

        public EventInfo.Builder clearContainerVersion()
        {
            bitField0_ = -3 & bitField0_;
            containerVersion_ = EventInfo.getDefaultInstance().getContainerVersion();
            return this;
        }

        public EventInfo.Builder clearDataLayerEventResult()
        {
            dataLayerEventResult_ = DataLayerEventEvaluationInfo.getDefaultInstance();
            bitField0_ = 0xffffffdf & bitField0_;
            return this;
        }

        public EventInfo.Builder clearEventType()
        {
            bitField0_ = -2 & bitField0_;
            eventType_ = EventInfo.EventType.DATA_LAYER_EVENT;
            return this;
        }

        public EventInfo.Builder clearKey()
        {
            bitField0_ = -9 & bitField0_;
            key_ = EventInfo.getDefaultInstance().getKey();
            return this;
        }

        public EventInfo.Builder clearMacroResult()
        {
            macroResult_ = MacroEvaluationInfo.getDefaultInstance();
            bitField0_ = 0xffffffef & bitField0_;
            return this;
        }

        public EventInfo.Builder clone()
        {
            return create().mergeFrom(buildPartial());
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public String getContainerId()
        {
            Object obj = containerId_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    containerId_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getContainerIdBytes()
        {
            Object obj = containerId_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                containerId_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public String getContainerVersion()
        {
            Object obj = containerVersion_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    containerVersion_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getContainerVersionBytes()
        {
            Object obj = containerVersion_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                containerVersion_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public DataLayerEventEvaluationInfo getDataLayerEventResult()
        {
            return dataLayerEventResult_;
        }

        public EventInfo getDefaultInstanceForType()
        {
            return EventInfo.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public EventInfo.EventType getEventType()
        {
            return eventType_;
        }

        public String getKey()
        {
            Object obj = key_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    key_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getKeyBytes()
        {
            Object obj = key_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                key_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public MacroEvaluationInfo getMacroResult()
        {
            return macroResult_;
        }

        public boolean hasContainerId()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasContainerVersion()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasDataLayerEventResult()
        {
            return (0x20 & bitField0_) == 32;
        }

        public boolean hasEventType()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasKey()
        {
            return (8 & bitField0_) == 8;
        }

        public boolean hasMacroResult()
        {
            return (0x10 & bitField0_) == 16;
        }

        public final boolean isInitialized()
        {
            while(hasMacroResult() && !getMacroResult().isInitialized() || hasDataLayerEventResult() && !getDataLayerEventResult().isInitialized()) 
                return false;
            return true;
        }

        public EventInfo.Builder mergeDataLayerEventResult(DataLayerEventEvaluationInfo datalayereventevaluationinfo)
        {
            if((0x20 & bitField0_) == 32 && dataLayerEventResult_ != DataLayerEventEvaluationInfo.getDefaultInstance())
                dataLayerEventResult_ = DataLayerEventEvaluationInfo.newBuilder(dataLayerEventResult_).mergeFrom(datalayereventevaluationinfo).buildPartial();
            else
                dataLayerEventResult_ = datalayereventevaluationinfo;
            bitField0_ = 0x20 | bitField0_;
            return this;
        }

        public EventInfo.Builder mergeFrom(EventInfo eventinfo)
        {
            if(eventinfo == EventInfo.getDefaultInstance())
                return this;
            if(eventinfo.hasEventType())
                setEventType(eventinfo.getEventType());
            if(eventinfo.hasContainerVersion())
            {
                bitField0_ = 2 | bitField0_;
                containerVersion_ = eventinfo.containerVersion_;
            }
            if(eventinfo.hasContainerId())
            {
                bitField0_ = 4 | bitField0_;
                containerId_ = eventinfo.containerId_;
            }
            if(eventinfo.hasKey())
            {
                bitField0_ = 8 | bitField0_;
                key_ = eventinfo.key_;
            }
            if(eventinfo.hasMacroResult())
                mergeMacroResult(eventinfo.getMacroResult());
            if(eventinfo.hasDataLayerEventResult())
                mergeDataLayerEventResult(eventinfo.getDataLayerEventResult());
            setUnknownFields(getUnknownFields().concat(eventinfo.unknownFields));
            return this;
        }

        public EventInfo.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            EventInfo eventinfo = null;
            EventInfo eventinfo1 = (EventInfo)EventInfo.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(eventinfo1 != null)
                mergeFrom(eventinfo1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            eventinfo = (EventInfo)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(eventinfo != null)
                mergeFrom(eventinfo);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((EventInfo)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public EventInfo.Builder mergeMacroResult(MacroEvaluationInfo macroevaluationinfo)
        {
            if((0x10 & bitField0_) == 16 && macroResult_ != MacroEvaluationInfo.getDefaultInstance())
                macroResult_ = MacroEvaluationInfo.newBuilder(macroResult_).mergeFrom(macroevaluationinfo).buildPartial();
            else
                macroResult_ = macroevaluationinfo;
            bitField0_ = 0x10 | bitField0_;
            return this;
        }

        public EventInfo.Builder setContainerId(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 4 | bitField0_;
                containerId_ = s;
                return this;
            }
        }

        public EventInfo.Builder setContainerIdBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 4 | bitField0_;
                containerId_ = bytestring;
                return this;
            }
        }

        public EventInfo.Builder setContainerVersion(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                containerVersion_ = s;
                return this;
            }
        }

        public EventInfo.Builder setContainerVersionBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                containerVersion_ = bytestring;
                return this;
            }
        }

        public EventInfo.Builder setDataLayerEventResult(DataLayerEventEvaluationInfo.Builder builder)
        {
            dataLayerEventResult_ = builder.build();
            bitField0_ = 0x20 | bitField0_;
            return this;
        }

        public EventInfo.Builder setDataLayerEventResult(DataLayerEventEvaluationInfo datalayereventevaluationinfo)
        {
            if(datalayereventevaluationinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                dataLayerEventResult_ = datalayereventevaluationinfo;
                bitField0_ = 0x20 | bitField0_;
                return this;
            }
        }

        public EventInfo.Builder setEventType(EventInfo.EventType eventtype)
        {
            if(eventtype == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                eventType_ = eventtype;
                return this;
            }
        }

        public EventInfo.Builder setKey(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 8 | bitField0_;
                key_ = s;
                return this;
            }
        }

        public EventInfo.Builder setKeyBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 8 | bitField0_;
                key_ = bytestring;
                return this;
            }
        }

        public EventInfo.Builder setMacroResult(MacroEvaluationInfo.Builder builder)
        {
            macroResult_ = builder.build();
            bitField0_ = 0x10 | bitField0_;
            return this;
        }

        public EventInfo.Builder setMacroResult(MacroEvaluationInfo macroevaluationinfo)
        {
            if(macroevaluationinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                macroResult_ = macroevaluationinfo;
                bitField0_ = 0x10 | bitField0_;
                return this;
            }
        }

        private int bitField0_;
        private Object containerId_;
        private Object containerVersion_;
        private DataLayerEventEvaluationInfo dataLayerEventResult_;
        private EventInfo.EventType eventType_;
        private Object key_;
        private MacroEvaluationInfo macroResult_;


        private EventInfo.Builder()
        {
            eventType_ = EventInfo.EventType.DATA_LAYER_EVENT;
            containerVersion_ = "";
            containerId_ = "";
            key_ = "";
            macroResult_ = MacroEvaluationInfo.getDefaultInstance();
            dataLayerEventResult_ = DataLayerEventEvaluationInfo.getDefaultInstance();
            maybeForceBuilderInitialization();
        }
    }

    public static final class EventInfo.EventType extends Enum
        implements com.google.tagmanager.protobuf.Internal.EnumLite
    {

        public static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalGetValueMap()
        {
            return internalValueMap;
        }

        public static EventInfo.EventType valueOf(int i)
        {
            switch(i)
            {
            default:
                return null;

            case 1: // '\001'
                return DATA_LAYER_EVENT;

            case 2: // '\002'
                return MACRO_REFERENCE;
            }
        }

        public static EventInfo.EventType valueOf(String s)
        {
            return (EventInfo.EventType)Enum.valueOf(com/google/analytics/containertag/proto/Debug$EventInfo$EventType, s);
        }

        public static EventInfo.EventType[] values()
        {
            return (EventInfo.EventType[])$VALUES.clone();
        }

        public final int getNumber()
        {
            return value;
        }

        private static final EventInfo.EventType $VALUES[];
        public static final EventInfo.EventType DATA_LAYER_EVENT;
        public static final int DATA_LAYER_EVENT_VALUE = 1;
        public static final EventInfo.EventType MACRO_REFERENCE;
        public static final int MACRO_REFERENCE_VALUE = 2;
        private static com.google.tagmanager.protobuf.Internal.EnumLiteMap internalValueMap = new com.google.tagmanager.protobuf.Internal.EnumLiteMap() {

            public EventInfo.EventType findValueByNumber(int i)
            {
                return EventInfo.EventType.valueOf(i);
            }

            public volatile com.google.tagmanager.protobuf.Internal.EnumLite findValueByNumber(int i)
            {
                return findValueByNumber(i);
            }

        }
;
        private final int value;

        static 
        {
            DATA_LAYER_EVENT = new EventInfo.EventType("DATA_LAYER_EVENT", 0, 0, 1);
            MACRO_REFERENCE = new EventInfo.EventType("MACRO_REFERENCE", 1, 1, 2);
            EventInfo.EventType aeventtype[] = new EventInfo.EventType[2];
            aeventtype[0] = DATA_LAYER_EVENT;
            aeventtype[1] = MACRO_REFERENCE;
            $VALUES = aeventtype;
        }

        private EventInfo.EventType(String s, int i, int j, int k)
        {
            super(s, i);
            value = k;
        }
    }

    public static interface EventInfoOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract String getContainerId();

        public abstract ByteString getContainerIdBytes();

        public abstract String getContainerVersion();

        public abstract ByteString getContainerVersionBytes();

        public abstract DataLayerEventEvaluationInfo getDataLayerEventResult();

        public abstract EventInfo.EventType getEventType();

        public abstract String getKey();

        public abstract ByteString getKeyBytes();

        public abstract MacroEvaluationInfo getMacroResult();

        public abstract boolean hasContainerId();

        public abstract boolean hasContainerVersion();

        public abstract boolean hasDataLayerEventResult();

        public abstract boolean hasEventType();

        public abstract boolean hasKey();

        public abstract boolean hasMacroResult();
    }

    public static final class MacroEvaluationInfo extends GeneratedMessageLite
        implements MacroEvaluationInfoOrBuilder
    {

        public static MacroEvaluationInfo getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
            result_ = ResolvedFunctionCall.getDefaultInstance();
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(MacroEvaluationInfo macroevaluationinfo)
        {
            return newBuilder().mergeFrom(macroevaluationinfo);
        }

        public static MacroEvaluationInfo parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (MacroEvaluationInfo)PARSER.parseDelimitedFrom(inputstream);
        }

        public static MacroEvaluationInfo parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (MacroEvaluationInfo)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static MacroEvaluationInfo parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (MacroEvaluationInfo)PARSER.parseFrom(bytestring);
        }

        public static MacroEvaluationInfo parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (MacroEvaluationInfo)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static MacroEvaluationInfo parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (MacroEvaluationInfo)PARSER.parseFrom(codedinputstream);
        }

        public static MacroEvaluationInfo parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (MacroEvaluationInfo)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static MacroEvaluationInfo parseFrom(InputStream inputstream)
            throws IOException
        {
            return (MacroEvaluationInfo)PARSER.parseFrom(inputstream);
        }

        public static MacroEvaluationInfo parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (MacroEvaluationInfo)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static MacroEvaluationInfo parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (MacroEvaluationInfo)PARSER.parseFrom(abyte0);
        }

        public static MacroEvaluationInfo parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (MacroEvaluationInfo)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof MacroEvaluationInfo))
                return super.equals(obj);
            MacroEvaluationInfo macroevaluationinfo = (MacroEvaluationInfo)obj;
            boolean flag;
            boolean flag1;
            if(true && hasRulesEvaluation() == macroevaluationinfo.hasRulesEvaluation())
                flag = true;
            else
                flag = false;
            if(hasRulesEvaluation())
                if(flag && getRulesEvaluation().equals(macroevaluationinfo.getRulesEvaluation()))
                    flag = true;
                else
                    flag = false;
            if(flag && hasResult() == macroevaluationinfo.hasResult())
                flag1 = true;
            else
                flag1 = false;
            if(hasResult())
                if(flag1 && getResult().equals(macroevaluationinfo.getResult()))
                    flag1 = true;
                else
                    flag1 = false;
            return flag1;
        }

        public MacroEvaluationInfo getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public ResolvedFunctionCall getResult()
        {
            return result_;
        }

        public RuleEvaluationStepInfo getRulesEvaluation()
        {
            return rulesEvaluation_;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 1 & bitField0_;
            int k = 0;
            if(j == 1)
                k = 0 + CodedOutputStream.computeMessageSize(1, rulesEvaluation_);
            if((2 & bitField0_) == 2)
                k += CodedOutputStream.computeMessageSize(3, result_);
            int l = k + unknownFields.size();
            memoizedSerializedSize = l;
            return l;
        }

        public boolean hasResult()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasRulesEvaluation()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Debug$MacroEvaluationInfo.hashCode();
            if(hasRulesEvaluation())
                i = 53 * (1 + i * 37) + getRulesEvaluation().hashCode();
            if(hasResult())
                i = 53 * (3 + i * 37) + getResult().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableDebug$MacroEvaluationInfo");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            if(hasRulesEvaluation() && !getRulesEvaluation().isInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            }
            if(hasResult() && !getResult().isInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
            }
        }

        public Builder newBuilderForType()
        {
            return newBuilder();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder newBuilderForType()
        {
            return newBuilderForType();
        }

        public Builder toBuilder()
        {
            return newBuilder(this);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder toBuilder()
        {
            return toBuilder();
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedoutputstream)
            throws IOException
        {
            getSerializedSize();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessage(1, rulesEvaluation_);
            if((2 & bitField0_) == 2)
                codedoutputstream.writeMessage(3, result_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int MACRO_FIELD_NUMBER = 0x2d4c0bd;
        public static Parser PARSER = new AbstractParser() {

            public MacroEvaluationInfo parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new MacroEvaluationInfo(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int RESULT_FIELD_NUMBER = 3;
        public static final int RULES_EVALUATION_FIELD_NUMBER = 1;
        private static final MacroEvaluationInfo defaultInstance;
        public static final com.google.tagmanager.protobuf.GeneratedMessageLite.GeneratedExtension macro;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private ResolvedFunctionCall result_;
        private RuleEvaluationStepInfo rulesEvaluation_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new MacroEvaluationInfo(true);
            defaultInstance.initFields();
            macro = GeneratedMessageLite.newSingularGeneratedExtension(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 0x2d4c0bd, com.google.tagmanager.protobuf.WireFormat.FieldType.MESSAGE, com/google/analytics/containertag/proto/Debug$MacroEvaluationInfo);
        }


/*
        static RuleEvaluationStepInfo access$5202(MacroEvaluationInfo macroevaluationinfo, RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            macroevaluationinfo.rulesEvaluation_ = ruleevaluationstepinfo;
            return ruleevaluationstepinfo;
        }

*/


/*
        static ResolvedFunctionCall access$5302(MacroEvaluationInfo macroevaluationinfo, ResolvedFunctionCall resolvedfunctioncall)
        {
            macroevaluationinfo.result_ = resolvedfunctioncall;
            return resolvedfunctioncall;
        }

*/


/*
        static int access$5402(MacroEvaluationInfo macroevaluationinfo, int i)
        {
            macroevaluationinfo.bitField0_ = i;
            return i;
        }

*/


        private MacroEvaluationInfo(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L20:
            if(flag) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 3: default 80
        //                       0: 375
        //                       10: 99
        //                       26: 208;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            int k = 1 & bitField0_;
            RuleEvaluationStepInfo.Builder builder1 = null;
            if(k != 1) goto _L8; else goto _L7
_L7:
            builder1 = rulesEvaluation_.toBuilder();
_L8:
            rulesEvaluation_ = (RuleEvaluationStepInfo)codedinputstream.readMessage(RuleEvaluationStepInfo.PARSER, extensionregistrylite);
            if(builder1 == null) goto _L10; else goto _L9
_L9:
            builder1.mergeFrom(rulesEvaluation_);
            rulesEvaluation_ = builder1.buildPartial();
_L10:
            bitField0_ = 1 | bitField0_;
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L18:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            int j = 2 & bitField0_;
            ResolvedFunctionCall.Builder builder = null;
            if(j != 2) goto _L12; else goto _L11
_L11:
            builder = result_.toBuilder();
_L12:
            result_ = (ResolvedFunctionCall)codedinputstream.readMessage(ResolvedFunctionCall.PARSER, extensionregistrylite);
            if(builder == null) goto _L14; else goto _L13
_L13:
            builder.mergeFrom(result_);
            result_ = builder.buildPartial();
_L14:
            bitField0_ = 2 | bitField0_;
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L16:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L16; else goto _L15
_L15:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L18; else goto _L17
_L17:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L20; else goto _L19
_L19:
        }


        private MacroEvaluationInfo(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private MacroEvaluationInfo(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class MacroEvaluationInfo.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements MacroEvaluationInfoOrBuilder
    {

        private static MacroEvaluationInfo.Builder create()
        {
            return new MacroEvaluationInfo.Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public MacroEvaluationInfo build()
        {
            MacroEvaluationInfo macroevaluationinfo = buildPartial();
            if(!macroevaluationinfo.isInitialized())
                throw newUninitializedMessageException(macroevaluationinfo);
            else
                return macroevaluationinfo;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public MacroEvaluationInfo buildPartial()
        {
            MacroEvaluationInfo macroevaluationinfo = new MacroEvaluationInfo(this);
            int i = bitField0_;
            int j = i & 1;
            int k = 0;
            if(j == 1)
                k = false | true;
            macroevaluationinfo.rulesEvaluation_ = rulesEvaluation_;
            if((i & 2) == 2)
                k |= 2;
            macroevaluationinfo.result_ = result_;
            macroevaluationinfo.bitField0_ = k;
            return macroevaluationinfo;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public MacroEvaluationInfo.Builder clear()
        {
            super.clear();
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
            bitField0_ = -2 & bitField0_;
            result_ = ResolvedFunctionCall.getDefaultInstance();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public MacroEvaluationInfo.Builder clearResult()
        {
            result_ = ResolvedFunctionCall.getDefaultInstance();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public MacroEvaluationInfo.Builder clearRulesEvaluation()
        {
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public MacroEvaluationInfo.Builder clone()
        {
            return create().mergeFrom(buildPartial());
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public MacroEvaluationInfo getDefaultInstanceForType()
        {
            return MacroEvaluationInfo.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedFunctionCall getResult()
        {
            return result_;
        }

        public RuleEvaluationStepInfo getRulesEvaluation()
        {
            return rulesEvaluation_;
        }

        public boolean hasResult()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasRulesEvaluation()
        {
            return (1 & bitField0_) == 1;
        }

        public final boolean isInitialized()
        {
            while(hasRulesEvaluation() && !getRulesEvaluation().isInitialized() || hasResult() && !getResult().isInitialized()) 
                return false;
            return true;
        }

        public MacroEvaluationInfo.Builder mergeFrom(MacroEvaluationInfo macroevaluationinfo)
        {
            if(macroevaluationinfo == MacroEvaluationInfo.getDefaultInstance())
                return this;
            if(macroevaluationinfo.hasRulesEvaluation())
                mergeRulesEvaluation(macroevaluationinfo.getRulesEvaluation());
            if(macroevaluationinfo.hasResult())
                mergeResult(macroevaluationinfo.getResult());
            setUnknownFields(getUnknownFields().concat(macroevaluationinfo.unknownFields));
            return this;
        }

        public MacroEvaluationInfo.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            MacroEvaluationInfo macroevaluationinfo = null;
            MacroEvaluationInfo macroevaluationinfo1 = (MacroEvaluationInfo)MacroEvaluationInfo.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(macroevaluationinfo1 != null)
                mergeFrom(macroevaluationinfo1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            macroevaluationinfo = (MacroEvaluationInfo)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(macroevaluationinfo != null)
                mergeFrom(macroevaluationinfo);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((MacroEvaluationInfo)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public MacroEvaluationInfo.Builder mergeResult(ResolvedFunctionCall resolvedfunctioncall)
        {
            if((2 & bitField0_) == 2 && result_ != ResolvedFunctionCall.getDefaultInstance())
                result_ = ResolvedFunctionCall.newBuilder(result_).mergeFrom(resolvedfunctioncall).buildPartial();
            else
                result_ = resolvedfunctioncall;
            bitField0_ = 2 | bitField0_;
            return this;
        }

        public MacroEvaluationInfo.Builder mergeRulesEvaluation(RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            if((1 & bitField0_) == 1 && rulesEvaluation_ != RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_ = RuleEvaluationStepInfo.newBuilder(rulesEvaluation_).mergeFrom(ruleevaluationstepinfo).buildPartial();
            else
                rulesEvaluation_ = ruleevaluationstepinfo;
            bitField0_ = 1 | bitField0_;
            return this;
        }

        public MacroEvaluationInfo.Builder setResult(ResolvedFunctionCall.Builder builder)
        {
            result_ = builder.build();
            bitField0_ = 2 | bitField0_;
            return this;
        }

        public MacroEvaluationInfo.Builder setResult(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                result_ = resolvedfunctioncall;
                bitField0_ = 2 | bitField0_;
                return this;
            }
        }

        public MacroEvaluationInfo.Builder setRulesEvaluation(RuleEvaluationStepInfo.Builder builder)
        {
            rulesEvaluation_ = builder.build();
            bitField0_ = 1 | bitField0_;
            return this;
        }

        public MacroEvaluationInfo.Builder setRulesEvaluation(RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            if(ruleevaluationstepinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                rulesEvaluation_ = ruleevaluationstepinfo;
                bitField0_ = 1 | bitField0_;
                return this;
            }
        }

        private int bitField0_;
        private ResolvedFunctionCall result_;
        private RuleEvaluationStepInfo rulesEvaluation_;


        private MacroEvaluationInfo.Builder()
        {
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
            result_ = ResolvedFunctionCall.getDefaultInstance();
            maybeForceBuilderInitialization();
        }
    }

    public static interface MacroEvaluationInfoOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract ResolvedFunctionCall getResult();

        public abstract RuleEvaluationStepInfo getRulesEvaluation();

        public abstract boolean hasResult();

        public abstract boolean hasRulesEvaluation();
    }

    public static final class ResolvedFunctionCall extends GeneratedMessageLite
        implements ResolvedFunctionCallOrBuilder
    {

        public static ResolvedFunctionCall getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            properties_ = Collections.emptyList();
            result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            associatedRuleName_ = "";
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(ResolvedFunctionCall resolvedfunctioncall)
        {
            return newBuilder().mergeFrom(resolvedfunctioncall);
        }

        public static ResolvedFunctionCall parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (ResolvedFunctionCall)PARSER.parseDelimitedFrom(inputstream);
        }

        public static ResolvedFunctionCall parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ResolvedFunctionCall)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static ResolvedFunctionCall parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (ResolvedFunctionCall)PARSER.parseFrom(bytestring);
        }

        public static ResolvedFunctionCall parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (ResolvedFunctionCall)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static ResolvedFunctionCall parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (ResolvedFunctionCall)PARSER.parseFrom(codedinputstream);
        }

        public static ResolvedFunctionCall parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ResolvedFunctionCall)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static ResolvedFunctionCall parseFrom(InputStream inputstream)
            throws IOException
        {
            return (ResolvedFunctionCall)PARSER.parseFrom(inputstream);
        }

        public static ResolvedFunctionCall parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ResolvedFunctionCall)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static ResolvedFunctionCall parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (ResolvedFunctionCall)PARSER.parseFrom(abyte0);
        }

        public static ResolvedFunctionCall parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (ResolvedFunctionCall)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof ResolvedFunctionCall))
                return super.equals(obj);
            ResolvedFunctionCall resolvedfunctioncall = (ResolvedFunctionCall)obj;
            boolean flag;
            boolean flag1;
            boolean flag2;
            if(true && getPropertiesList().equals(resolvedfunctioncall.getPropertiesList()))
                flag = true;
            else
                flag = false;
            if(flag && hasResult() == resolvedfunctioncall.hasResult())
                flag1 = true;
            else
                flag1 = false;
            if(hasResult())
                if(flag1 && getResult().equals(resolvedfunctioncall.getResult()))
                    flag1 = true;
                else
                    flag1 = false;
            if(flag1 && hasAssociatedRuleName() == resolvedfunctioncall.hasAssociatedRuleName())
                flag2 = true;
            else
                flag2 = false;
            if(hasAssociatedRuleName())
                if(flag2 && getAssociatedRuleName().equals(resolvedfunctioncall.getAssociatedRuleName()))
                    flag2 = true;
                else
                    flag2 = false;
            return flag2;
        }

        public String getAssociatedRuleName()
        {
            Object obj = associatedRuleName_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                associatedRuleName_ = s;
            return s;
        }

        public ByteString getAssociatedRuleNameBytes()
        {
            Object obj = associatedRuleName_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                associatedRuleName_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public ResolvedFunctionCall getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public ResolvedProperty getProperties(int i)
        {
            return (ResolvedProperty)properties_.get(i);
        }

        public int getPropertiesCount()
        {
            return properties_.size();
        }

        public List getPropertiesList()
        {
            return properties_;
        }

        public ResolvedPropertyOrBuilder getPropertiesOrBuilder(int i)
        {
            return (ResolvedPropertyOrBuilder)properties_.get(i);
        }

        public List getPropertiesOrBuilderList()
        {
            return properties_;
        }

        public com.google.analytics.midtier.proto.containertag.TypeSystem.Value getResult()
        {
            return result_;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 0;
            for(int k = 0; k < properties_.size(); k++)
                j += CodedOutputStream.computeMessageSize(1, (MessageLite)properties_.get(k));

            if((1 & bitField0_) == 1)
                j += CodedOutputStream.computeMessageSize(2, result_);
            if((2 & bitField0_) == 2)
                j += CodedOutputStream.computeBytesSize(3, getAssociatedRuleNameBytes());
            int l = j + unknownFields.size();
            memoizedSerializedSize = l;
            return l;
        }

        public boolean hasAssociatedRuleName()
        {
            return (2 & bitField0_) == 2;
        }

        public boolean hasResult()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Debug$ResolvedFunctionCall.hashCode();
            if(getPropertiesCount() > 0)
                i = 53 * (1 + i * 37) + getPropertiesList().hashCode();
            if(hasResult())
                i = 53 * (2 + i * 37) + getResult().hashCode();
            if(hasAssociatedRuleName())
                i = 53 * (3 + i * 37) + getAssociatedRuleName().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableDebug$ResolvedFunctionCall");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            for(int i = 0; i < getPropertiesCount(); i++)
                if(!getProperties(i).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            if(hasResult() && !getResult().isInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
            }
        }

        public Builder newBuilderForType()
        {
            return newBuilder();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder newBuilderForType()
        {
            return newBuilderForType();
        }

        public Builder toBuilder()
        {
            return newBuilder(this);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder toBuilder()
        {
            return toBuilder();
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedoutputstream)
            throws IOException
        {
            getSerializedSize();
            for(int i = 0; i < properties_.size(); i++)
                codedoutputstream.writeMessage(1, (MessageLite)properties_.get(i));

            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessage(2, result_);
            if((2 & bitField0_) == 2)
                codedoutputstream.writeBytes(3, getAssociatedRuleNameBytes());
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int ASSOCIATED_RULE_NAME_FIELD_NUMBER = 3;
        public static Parser PARSER = new AbstractParser() {

            public ResolvedFunctionCall parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new ResolvedFunctionCall(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int PROPERTIES_FIELD_NUMBER = 1;
        public static final int RESULT_FIELD_NUMBER = 2;
        private static final ResolvedFunctionCall defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private Object associatedRuleName_;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List properties_;
        private com.google.analytics.midtier.proto.containertag.TypeSystem.Value result_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new ResolvedFunctionCall(true);
            defaultInstance.initFields();
        }



/*
        static List access$3102(ResolvedFunctionCall resolvedfunctioncall, List list)
        {
            resolvedfunctioncall.properties_ = list;
            return list;
        }

*/


/*
        static com.google.analytics.midtier.proto.containertag.TypeSystem.Value access$3202(ResolvedFunctionCall resolvedfunctioncall, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            resolvedfunctioncall.result_ = value;
            return value;
        }

*/



/*
        static Object access$3302(ResolvedFunctionCall resolvedfunctioncall, Object obj)
        {
            resolvedfunctioncall.associatedRuleName_ = obj;
            return obj;
        }

*/


/*
        static int access$3402(ResolvedFunctionCall resolvedfunctioncall, int i)
        {
            resolvedfunctioncall.bitField0_ = i;
            return i;
        }

*/


        private ResolvedFunctionCall(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            boolean flag;
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag1;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            flag = false;
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag1 = false;
_L17:
            if(flag1) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 4: default 92
        //                       0: 419
        //                       10: 111
        //                       18: 204
        //                       26: 301;
               goto _L3 _L4 _L5 _L6 _L7
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag1 = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if((flag & true))
                break MISSING_BLOCK_LABEL_133;
            properties_ = new ArrayList();
            flag |= true;
            properties_.add(codedinputstream.readMessage(ResolvedProperty.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((flag & true))
                properties_ = Collections.unmodifiableList(properties_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L15:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            int j = 1 & bitField0_;
            com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder = null;
            if(j != 1) goto _L9; else goto _L8
_L8:
            builder = result_.toBuilder();
_L9:
            result_ = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)codedinputstream.readMessage(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.PARSER, extensionregistrylite);
            if(builder == null) goto _L11; else goto _L10
_L10:
            builder.mergeFrom(result_);
            result_ = builder.buildPartial();
_L11:
            bitField0_ = 1 | bitField0_;
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L7:
            ByteString bytestring = codedinputstream.readBytes();
            bitField0_ = 2 | bitField0_;
            associatedRuleName_ = bytestring;
            continue; /* Loop/switch isn't completed */
_L2:
            if((flag & true))
                properties_ = Collections.unmodifiableList(properties_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L13:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L13; else goto _L12
_L12:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L15; else goto _L14
_L14:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag1 = true;
            if(true) goto _L17; else goto _L16
_L16:
        }


        private ResolvedFunctionCall(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private ResolvedFunctionCall(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class ResolvedFunctionCall.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements ResolvedFunctionCallOrBuilder
    {

        private static ResolvedFunctionCall.Builder create()
        {
            return new ResolvedFunctionCall.Builder();
        }

        private void ensurePropertiesIsMutable()
        {
            if((1 & bitField0_) != 1)
            {
                properties_ = new ArrayList(properties_);
                bitField0_ = 1 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public ResolvedFunctionCall.Builder addAllProperties(Iterable iterable)
        {
            ensurePropertiesIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, properties_);
            return this;
        }

        public ResolvedFunctionCall.Builder addProperties(int i, ResolvedProperty.Builder builder)
        {
            ensurePropertiesIsMutable();
            properties_.add(i, builder.build());
            return this;
        }

        public ResolvedFunctionCall.Builder addProperties(int i, ResolvedProperty resolvedproperty)
        {
            if(resolvedproperty == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertiesIsMutable();
                properties_.add(i, resolvedproperty);
                return this;
            }
        }

        public ResolvedFunctionCall.Builder addProperties(ResolvedProperty.Builder builder)
        {
            ensurePropertiesIsMutable();
            properties_.add(builder.build());
            return this;
        }

        public ResolvedFunctionCall.Builder addProperties(ResolvedProperty resolvedproperty)
        {
            if(resolvedproperty == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertiesIsMutable();
                properties_.add(resolvedproperty);
                return this;
            }
        }

        public ResolvedFunctionCall build()
        {
            ResolvedFunctionCall resolvedfunctioncall = buildPartial();
            if(!resolvedfunctioncall.isInitialized())
                throw newUninitializedMessageException(resolvedfunctioncall);
            else
                return resolvedfunctioncall;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public ResolvedFunctionCall buildPartial()
        {
            ResolvedFunctionCall resolvedfunctioncall = new ResolvedFunctionCall(this);
            int i = bitField0_;
            if((1 & bitField0_) == 1)
            {
                properties_ = Collections.unmodifiableList(properties_);
                bitField0_ = -2 & bitField0_;
            }
            resolvedfunctioncall.properties_ = properties_;
            int j = i & 2;
            int k = 0;
            if(j == 2)
                k = false | true;
            resolvedfunctioncall.result_ = result_;
            if((i & 4) == 4)
                k |= 2;
            resolvedfunctioncall.associatedRuleName_ = associatedRuleName_;
            resolvedfunctioncall.bitField0_ = k;
            return resolvedfunctioncall;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public ResolvedFunctionCall.Builder clear()
        {
            super.clear();
            properties_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            bitField0_ = -3 & bitField0_;
            associatedRuleName_ = "";
            bitField0_ = -5 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public ResolvedFunctionCall.Builder clearAssociatedRuleName()
        {
            bitField0_ = -5 & bitField0_;
            associatedRuleName_ = ResolvedFunctionCall.getDefaultInstance().getAssociatedRuleName();
            return this;
        }

        public ResolvedFunctionCall.Builder clearProperties()
        {
            properties_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public ResolvedFunctionCall.Builder clearResult()
        {
            result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public ResolvedFunctionCall.Builder clone()
        {
            return create().mergeFrom(buildPartial());
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public String getAssociatedRuleName()
        {
            Object obj = associatedRuleName_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    associatedRuleName_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getAssociatedRuleNameBytes()
        {
            Object obj = associatedRuleName_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                associatedRuleName_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public ResolvedFunctionCall getDefaultInstanceForType()
        {
            return ResolvedFunctionCall.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedProperty getProperties(int i)
        {
            return (ResolvedProperty)properties_.get(i);
        }

        public int getPropertiesCount()
        {
            return properties_.size();
        }

        public List getPropertiesList()
        {
            return Collections.unmodifiableList(properties_);
        }

        public com.google.analytics.midtier.proto.containertag.TypeSystem.Value getResult()
        {
            return result_;
        }

        public boolean hasAssociatedRuleName()
        {
            return (4 & bitField0_) == 4;
        }

        public boolean hasResult()
        {
            return (2 & bitField0_) == 2;
        }

        public final boolean isInitialized()
        {
            int i = 0;
_L7:
            if(i >= getPropertiesCount()) goto _L2; else goto _L1
_L1:
            if(getProperties(i).isInitialized()) goto _L4; else goto _L3
_L3:
            return false;
_L4:
            i++;
            continue; /* Loop/switch isn't completed */
_L2:
            if(hasResult() && !getResult().isInitialized()) goto _L3; else goto _L5
_L5:
            return true;
            if(true) goto _L7; else goto _L6
_L6:
        }

        public ResolvedFunctionCall.Builder mergeFrom(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == ResolvedFunctionCall.getDefaultInstance())
                return this;
            if(!resolvedfunctioncall.properties_.isEmpty())
                if(properties_.isEmpty())
                {
                    properties_ = resolvedfunctioncall.properties_;
                    bitField0_ = -2 & bitField0_;
                } else
                {
                    ensurePropertiesIsMutable();
                    properties_.addAll(resolvedfunctioncall.properties_);
                }
            if(resolvedfunctioncall.hasResult())
                mergeResult(resolvedfunctioncall.getResult());
            if(resolvedfunctioncall.hasAssociatedRuleName())
            {
                bitField0_ = 4 | bitField0_;
                associatedRuleName_ = resolvedfunctioncall.associatedRuleName_;
            }
            setUnknownFields(getUnknownFields().concat(resolvedfunctioncall.unknownFields));
            return this;
        }

        public ResolvedFunctionCall.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            ResolvedFunctionCall resolvedfunctioncall = null;
            ResolvedFunctionCall resolvedfunctioncall1 = (ResolvedFunctionCall)ResolvedFunctionCall.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(resolvedfunctioncall1 != null)
                mergeFrom(resolvedfunctioncall1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            resolvedfunctioncall = (ResolvedFunctionCall)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(resolvedfunctioncall != null)
                mergeFrom(resolvedfunctioncall);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((ResolvedFunctionCall)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public ResolvedFunctionCall.Builder mergeResult(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            if((2 & bitField0_) == 2 && result_ != com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance())
                result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.newBuilder(result_).mergeFrom(value).buildPartial();
            else
                result_ = value;
            bitField0_ = 2 | bitField0_;
            return this;
        }

        public ResolvedFunctionCall.Builder removeProperties(int i)
        {
            ensurePropertiesIsMutable();
            properties_.remove(i);
            return this;
        }

        public ResolvedFunctionCall.Builder setAssociatedRuleName(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 4 | bitField0_;
                associatedRuleName_ = s;
                return this;
            }
        }

        public ResolvedFunctionCall.Builder setAssociatedRuleNameBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 4 | bitField0_;
                associatedRuleName_ = bytestring;
                return this;
            }
        }

        public ResolvedFunctionCall.Builder setProperties(int i, ResolvedProperty.Builder builder)
        {
            ensurePropertiesIsMutable();
            properties_.set(i, builder.build());
            return this;
        }

        public ResolvedFunctionCall.Builder setProperties(int i, ResolvedProperty resolvedproperty)
        {
            if(resolvedproperty == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertiesIsMutable();
                properties_.set(i, resolvedproperty);
                return this;
            }
        }

        public ResolvedFunctionCall.Builder setResult(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder)
        {
            result_ = builder.build();
            bitField0_ = 2 | bitField0_;
            return this;
        }

        public ResolvedFunctionCall.Builder setResult(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                result_ = value;
                bitField0_ = 2 | bitField0_;
                return this;
            }
        }

        private Object associatedRuleName_;
        private int bitField0_;
        private List properties_;
        private com.google.analytics.midtier.proto.containertag.TypeSystem.Value result_;


        private ResolvedFunctionCall.Builder()
        {
            properties_ = Collections.emptyList();
            result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            associatedRuleName_ = "";
            maybeForceBuilderInitialization();
        }
    }

    public static interface ResolvedFunctionCallOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract String getAssociatedRuleName();

        public abstract ByteString getAssociatedRuleNameBytes();

        public abstract ResolvedProperty getProperties(int i);

        public abstract int getPropertiesCount();

        public abstract List getPropertiesList();

        public abstract com.google.analytics.midtier.proto.containertag.TypeSystem.Value getResult();

        public abstract boolean hasAssociatedRuleName();

        public abstract boolean hasResult();
    }

    public static final class ResolvedProperty extends GeneratedMessageLite
        implements ResolvedPropertyOrBuilder
    {

        public static ResolvedProperty getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            key_ = "";
            value_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(ResolvedProperty resolvedproperty)
        {
            return newBuilder().mergeFrom(resolvedproperty);
        }

        public static ResolvedProperty parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (ResolvedProperty)PARSER.parseDelimitedFrom(inputstream);
        }

        public static ResolvedProperty parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ResolvedProperty)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static ResolvedProperty parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (ResolvedProperty)PARSER.parseFrom(bytestring);
        }

        public static ResolvedProperty parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (ResolvedProperty)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static ResolvedProperty parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (ResolvedProperty)PARSER.parseFrom(codedinputstream);
        }

        public static ResolvedProperty parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ResolvedProperty)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static ResolvedProperty parseFrom(InputStream inputstream)
            throws IOException
        {
            return (ResolvedProperty)PARSER.parseFrom(inputstream);
        }

        public static ResolvedProperty parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ResolvedProperty)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static ResolvedProperty parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (ResolvedProperty)PARSER.parseFrom(abyte0);
        }

        public static ResolvedProperty parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (ResolvedProperty)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof ResolvedProperty))
                return super.equals(obj);
            ResolvedProperty resolvedproperty = (ResolvedProperty)obj;
            boolean flag;
            boolean flag1;
            if(true && hasKey() == resolvedproperty.hasKey())
                flag = true;
            else
                flag = false;
            if(hasKey())
                if(flag && getKey().equals(resolvedproperty.getKey()))
                    flag = true;
                else
                    flag = false;
            if(flag && hasValue() == resolvedproperty.hasValue())
                flag1 = true;
            else
                flag1 = false;
            if(hasValue())
                if(flag1 && getValue().equals(resolvedproperty.getValue()))
                    flag1 = true;
                else
                    flag1 = false;
            return flag1;
        }

        public ResolvedProperty getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public String getKey()
        {
            Object obj = key_;
            if(obj instanceof String)
                return (String)obj;
            ByteString bytestring = (ByteString)obj;
            String s = bytestring.toStringUtf8();
            if(bytestring.isValidUtf8())
                key_ = s;
            return s;
        }

        public ByteString getKeyBytes()
        {
            Object obj = key_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                key_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 1 & bitField0_;
            int k = 0;
            if(j == 1)
                k = 0 + CodedOutputStream.computeBytesSize(1, getKeyBytes());
            if((2 & bitField0_) == 2)
                k += CodedOutputStream.computeMessageSize(2, value_);
            int l = k + unknownFields.size();
            memoizedSerializedSize = l;
            return l;
        }

        public com.google.analytics.midtier.proto.containertag.TypeSystem.Value getValue()
        {
            return value_;
        }

        public boolean hasKey()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasValue()
        {
            return (2 & bitField0_) == 2;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Debug$ResolvedProperty.hashCode();
            if(hasKey())
                i = 53 * (1 + i * 37) + getKey().hashCode();
            if(hasValue())
                i = 53 * (2 + i * 37) + getValue().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableDebug$ResolvedProperty");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            if(hasValue() && !getValue().isInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            } else
            {
                memoizedIsInitialized = 1;
                return true;
            }
        }

        public Builder newBuilderForType()
        {
            return newBuilder();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder newBuilderForType()
        {
            return newBuilderForType();
        }

        public Builder toBuilder()
        {
            return newBuilder(this);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder toBuilder()
        {
            return toBuilder();
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedoutputstream)
            throws IOException
        {
            getSerializedSize();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeBytes(1, getKeyBytes());
            if((2 & bitField0_) == 2)
                codedoutputstream.writeMessage(2, value_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int KEY_FIELD_NUMBER = 1;
        public static Parser PARSER = new AbstractParser() {

            public ResolvedProperty parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new ResolvedProperty(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final ResolvedProperty defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private Object key_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private final ByteString unknownFields;
        private com.google.analytics.midtier.proto.containertag.TypeSystem.Value value_;

        static 
        {
            defaultInstance = new ResolvedProperty(true);
            defaultInstance.initFields();
        }



/*
        static Object access$5902(ResolvedProperty resolvedproperty, Object obj)
        {
            resolvedproperty.key_ = obj;
            return obj;
        }

*/


/*
        static com.google.analytics.midtier.proto.containertag.TypeSystem.Value access$6002(ResolvedProperty resolvedproperty, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            resolvedproperty.value_ = value;
            return value;
        }

*/


/*
        static int access$6102(ResolvedProperty resolvedproperty, int i)
        {
            resolvedproperty.bitField0_ = i;
            return i;
        }

*/


        private ResolvedProperty(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L16:
            if(flag) goto _L2; else goto _L1
_L1:
            int i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 3: default 80
        //                       0: 322
        //                       10: 99
        //                       18: 155;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            ByteString bytestring = codedinputstream.readBytes();
            bitField0_ = 1 | bitField0_;
            key_ = bytestring;
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L14:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            int j = 2 & bitField0_;
            com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder = null;
            if(j != 2) goto _L8; else goto _L7
_L7:
            builder = value_.toBuilder();
_L8:
            value_ = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)codedinputstream.readMessage(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.PARSER, extensionregistrylite);
            if(builder == null) goto _L10; else goto _L9
_L9:
            builder.mergeFrom(value_);
            value_ = builder.buildPartial();
_L10:
            bitField0_ = 2 | bitField0_;
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L12:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L12; else goto _L11
_L11:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L14; else goto _L13
_L13:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L16; else goto _L15
_L15:
        }


        private ResolvedProperty(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private ResolvedProperty(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class ResolvedProperty.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements ResolvedPropertyOrBuilder
    {

        private static ResolvedProperty.Builder create()
        {
            return new ResolvedProperty.Builder();
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public ResolvedProperty build()
        {
            ResolvedProperty resolvedproperty = buildPartial();
            if(!resolvedproperty.isInitialized())
                throw newUninitializedMessageException(resolvedproperty);
            else
                return resolvedproperty;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public ResolvedProperty buildPartial()
        {
            ResolvedProperty resolvedproperty = new ResolvedProperty(this);
            int i = bitField0_;
            int j = i & 1;
            int k = 0;
            if(j == 1)
                k = false | true;
            resolvedproperty.key_ = key_;
            if((i & 2) == 2)
                k |= 2;
            resolvedproperty.value_ = value_;
            resolvedproperty.bitField0_ = k;
            return resolvedproperty;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public ResolvedProperty.Builder clear()
        {
            super.clear();
            key_ = "";
            bitField0_ = -2 & bitField0_;
            value_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public ResolvedProperty.Builder clearKey()
        {
            bitField0_ = -2 & bitField0_;
            key_ = ResolvedProperty.getDefaultInstance().getKey();
            return this;
        }

        public ResolvedProperty.Builder clearValue()
        {
            value_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public ResolvedProperty.Builder clone()
        {
            return create().mergeFrom(buildPartial());
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public ResolvedProperty getDefaultInstanceForType()
        {
            return ResolvedProperty.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public String getKey()
        {
            Object obj = key_;
            if(!(obj instanceof String))
            {
                ByteString bytestring = (ByteString)obj;
                String s = bytestring.toStringUtf8();
                if(bytestring.isValidUtf8())
                    key_ = s;
                return s;
            } else
            {
                return (String)obj;
            }
        }

        public ByteString getKeyBytes()
        {
            Object obj = key_;
            if(obj instanceof String)
            {
                ByteString bytestring = ByteString.copyFromUtf8((String)obj);
                key_ = bytestring;
                return bytestring;
            } else
            {
                return (ByteString)obj;
            }
        }

        public com.google.analytics.midtier.proto.containertag.TypeSystem.Value getValue()
        {
            return value_;
        }

        public boolean hasKey()
        {
            return (1 & bitField0_) == 1;
        }

        public boolean hasValue()
        {
            return (2 & bitField0_) == 2;
        }

        public final boolean isInitialized()
        {
            return !hasValue() || getValue().isInitialized();
        }

        public ResolvedProperty.Builder mergeFrom(ResolvedProperty resolvedproperty)
        {
            if(resolvedproperty == ResolvedProperty.getDefaultInstance())
                return this;
            if(resolvedproperty.hasKey())
            {
                bitField0_ = 1 | bitField0_;
                key_ = resolvedproperty.key_;
            }
            if(resolvedproperty.hasValue())
                mergeValue(resolvedproperty.getValue());
            setUnknownFields(getUnknownFields().concat(resolvedproperty.unknownFields));
            return this;
        }

        public ResolvedProperty.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            ResolvedProperty resolvedproperty = null;
            ResolvedProperty resolvedproperty1 = (ResolvedProperty)ResolvedProperty.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(resolvedproperty1 != null)
                mergeFrom(resolvedproperty1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            resolvedproperty = (ResolvedProperty)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(resolvedproperty != null)
                mergeFrom(resolvedproperty);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((ResolvedProperty)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public ResolvedProperty.Builder mergeValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            if((2 & bitField0_) == 2 && value_ != com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance())
                value_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.newBuilder(value_).mergeFrom(value).buildPartial();
            else
                value_ = value;
            bitField0_ = 2 | bitField0_;
            return this;
        }

        public ResolvedProperty.Builder setKey(String s)
        {
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                key_ = s;
                return this;
            }
        }

        public ResolvedProperty.Builder setKeyBytes(ByteString bytestring)
        {
            if(bytestring == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                key_ = bytestring;
                return this;
            }
        }

        public ResolvedProperty.Builder setValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder)
        {
            value_ = builder.build();
            bitField0_ = 2 | bitField0_;
            return this;
        }

        public ResolvedProperty.Builder setValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                value_ = value;
                bitField0_ = 2 | bitField0_;
                return this;
            }
        }

        private int bitField0_;
        private Object key_;
        private com.google.analytics.midtier.proto.containertag.TypeSystem.Value value_;


        private ResolvedProperty.Builder()
        {
            key_ = "";
            value_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            maybeForceBuilderInitialization();
        }
    }

    public static interface ResolvedPropertyOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract String getKey();

        public abstract ByteString getKeyBytes();

        public abstract com.google.analytics.midtier.proto.containertag.TypeSystem.Value getValue();

        public abstract boolean hasKey();

        public abstract boolean hasValue();
    }

    public static final class ResolvedRule extends GeneratedMessageLite
        implements ResolvedRuleOrBuilder
    {

        public static ResolvedRule getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            positivePredicates_ = Collections.emptyList();
            negativePredicates_ = Collections.emptyList();
            addTags_ = Collections.emptyList();
            removeTags_ = Collections.emptyList();
            addMacros_ = Collections.emptyList();
            removeMacros_ = Collections.emptyList();
            result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(ResolvedRule resolvedrule)
        {
            return newBuilder().mergeFrom(resolvedrule);
        }

        public static ResolvedRule parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (ResolvedRule)PARSER.parseDelimitedFrom(inputstream);
        }

        public static ResolvedRule parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ResolvedRule)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static ResolvedRule parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (ResolvedRule)PARSER.parseFrom(bytestring);
        }

        public static ResolvedRule parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (ResolvedRule)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static ResolvedRule parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (ResolvedRule)PARSER.parseFrom(codedinputstream);
        }

        public static ResolvedRule parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ResolvedRule)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static ResolvedRule parseFrom(InputStream inputstream)
            throws IOException
        {
            return (ResolvedRule)PARSER.parseFrom(inputstream);
        }

        public static ResolvedRule parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (ResolvedRule)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static ResolvedRule parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (ResolvedRule)PARSER.parseFrom(abyte0);
        }

        public static ResolvedRule parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (ResolvedRule)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof ResolvedRule))
                return super.equals(obj);
            ResolvedRule resolvedrule = (ResolvedRule)obj;
            boolean flag;
            boolean flag1;
            boolean flag2;
            boolean flag3;
            boolean flag4;
            boolean flag5;
            boolean flag6;
            if(true && getPositivePredicatesList().equals(resolvedrule.getPositivePredicatesList()))
                flag = true;
            else
                flag = false;
            if(flag && getNegativePredicatesList().equals(resolvedrule.getNegativePredicatesList()))
                flag1 = true;
            else
                flag1 = false;
            if(flag1 && getAddTagsList().equals(resolvedrule.getAddTagsList()))
                flag2 = true;
            else
                flag2 = false;
            if(flag2 && getRemoveTagsList().equals(resolvedrule.getRemoveTagsList()))
                flag3 = true;
            else
                flag3 = false;
            if(flag3 && getAddMacrosList().equals(resolvedrule.getAddMacrosList()))
                flag4 = true;
            else
                flag4 = false;
            if(flag4 && getRemoveMacrosList().equals(resolvedrule.getRemoveMacrosList()))
                flag5 = true;
            else
                flag5 = false;
            if(flag5 && hasResult() == resolvedrule.hasResult())
                flag6 = true;
            else
                flag6 = false;
            if(hasResult())
                if(flag6 && getResult().equals(resolvedrule.getResult()))
                    flag6 = true;
                else
                    flag6 = false;
            return flag6;
        }

        public ResolvedFunctionCall getAddMacros(int i)
        {
            return (ResolvedFunctionCall)addMacros_.get(i);
        }

        public int getAddMacrosCount()
        {
            return addMacros_.size();
        }

        public List getAddMacrosList()
        {
            return addMacros_;
        }

        public ResolvedFunctionCallOrBuilder getAddMacrosOrBuilder(int i)
        {
            return (ResolvedFunctionCallOrBuilder)addMacros_.get(i);
        }

        public List getAddMacrosOrBuilderList()
        {
            return addMacros_;
        }

        public ResolvedFunctionCall getAddTags(int i)
        {
            return (ResolvedFunctionCall)addTags_.get(i);
        }

        public int getAddTagsCount()
        {
            return addTags_.size();
        }

        public List getAddTagsList()
        {
            return addTags_;
        }

        public ResolvedFunctionCallOrBuilder getAddTagsOrBuilder(int i)
        {
            return (ResolvedFunctionCallOrBuilder)addTags_.get(i);
        }

        public List getAddTagsOrBuilderList()
        {
            return addTags_;
        }

        public ResolvedRule getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedFunctionCall getNegativePredicates(int i)
        {
            return (ResolvedFunctionCall)negativePredicates_.get(i);
        }

        public int getNegativePredicatesCount()
        {
            return negativePredicates_.size();
        }

        public List getNegativePredicatesList()
        {
            return negativePredicates_;
        }

        public ResolvedFunctionCallOrBuilder getNegativePredicatesOrBuilder(int i)
        {
            return (ResolvedFunctionCallOrBuilder)negativePredicates_.get(i);
        }

        public List getNegativePredicatesOrBuilderList()
        {
            return negativePredicates_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public ResolvedFunctionCall getPositivePredicates(int i)
        {
            return (ResolvedFunctionCall)positivePredicates_.get(i);
        }

        public int getPositivePredicatesCount()
        {
            return positivePredicates_.size();
        }

        public List getPositivePredicatesList()
        {
            return positivePredicates_;
        }

        public ResolvedFunctionCallOrBuilder getPositivePredicatesOrBuilder(int i)
        {
            return (ResolvedFunctionCallOrBuilder)positivePredicates_.get(i);
        }

        public List getPositivePredicatesOrBuilderList()
        {
            return positivePredicates_;
        }

        public ResolvedFunctionCall getRemoveMacros(int i)
        {
            return (ResolvedFunctionCall)removeMacros_.get(i);
        }

        public int getRemoveMacrosCount()
        {
            return removeMacros_.size();
        }

        public List getRemoveMacrosList()
        {
            return removeMacros_;
        }

        public ResolvedFunctionCallOrBuilder getRemoveMacrosOrBuilder(int i)
        {
            return (ResolvedFunctionCallOrBuilder)removeMacros_.get(i);
        }

        public List getRemoveMacrosOrBuilderList()
        {
            return removeMacros_;
        }

        public ResolvedFunctionCall getRemoveTags(int i)
        {
            return (ResolvedFunctionCall)removeTags_.get(i);
        }

        public int getRemoveTagsCount()
        {
            return removeTags_.size();
        }

        public List getRemoveTagsList()
        {
            return removeTags_;
        }

        public ResolvedFunctionCallOrBuilder getRemoveTagsOrBuilder(int i)
        {
            return (ResolvedFunctionCallOrBuilder)removeTags_.get(i);
        }

        public List getRemoveTagsOrBuilderList()
        {
            return removeTags_;
        }

        public com.google.analytics.midtier.proto.containertag.TypeSystem.Value getResult()
        {
            return result_;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 0;
            for(int k = 0; k < positivePredicates_.size(); k++)
                j += CodedOutputStream.computeMessageSize(1, (MessageLite)positivePredicates_.get(k));

            for(int l = 0; l < negativePredicates_.size(); l++)
                j += CodedOutputStream.computeMessageSize(2, (MessageLite)negativePredicates_.get(l));

            for(int i1 = 0; i1 < addTags_.size(); i1++)
                j += CodedOutputStream.computeMessageSize(3, (MessageLite)addTags_.get(i1));

            for(int j1 = 0; j1 < removeTags_.size(); j1++)
                j += CodedOutputStream.computeMessageSize(4, (MessageLite)removeTags_.get(j1));

            for(int k1 = 0; k1 < addMacros_.size(); k1++)
                j += CodedOutputStream.computeMessageSize(5, (MessageLite)addMacros_.get(k1));

            for(int l1 = 0; l1 < removeMacros_.size(); l1++)
                j += CodedOutputStream.computeMessageSize(6, (MessageLite)removeMacros_.get(l1));

            if((1 & bitField0_) == 1)
                j += CodedOutputStream.computeMessageSize(7, result_);
            int i2 = j + unknownFields.size();
            memoizedSerializedSize = i2;
            return i2;
        }

        public boolean hasResult()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Debug$ResolvedRule.hashCode();
            if(getPositivePredicatesCount() > 0)
                i = 53 * (1 + i * 37) + getPositivePredicatesList().hashCode();
            if(getNegativePredicatesCount() > 0)
                i = 53 * (2 + i * 37) + getNegativePredicatesList().hashCode();
            if(getAddTagsCount() > 0)
                i = 53 * (3 + i * 37) + getAddTagsList().hashCode();
            if(getRemoveTagsCount() > 0)
                i = 53 * (4 + i * 37) + getRemoveTagsList().hashCode();
            if(getAddMacrosCount() > 0)
                i = 53 * (5 + i * 37) + getAddMacrosList().hashCode();
            if(getRemoveMacrosCount() > 0)
                i = 53 * (6 + i * 37) + getRemoveMacrosList().hashCode();
            if(hasResult())
                i = 53 * (7 + i * 37) + getResult().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableDebug$ResolvedRule");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            boolean flag = true;
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
            {
                if(byte0 != flag)
                    flag = false;
                return flag;
            }
            for(int i = 0; i < getPositivePredicatesCount(); i++)
                if(!getPositivePredicates(i).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int j = 0; j < getNegativePredicatesCount(); j++)
                if(!getNegativePredicates(j).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int k = 0; k < getAddTagsCount(); k++)
                if(!getAddTags(k).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int l = 0; l < getRemoveTagsCount(); l++)
                if(!getRemoveTags(l).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int i1 = 0; i1 < getAddMacrosCount(); i1++)
                if(!getAddMacros(i1).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int j1 = 0; j1 < getRemoveMacrosCount(); j1++)
                if(!getRemoveMacros(j1).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            if(hasResult() && !getResult().isInitialized())
            {
                memoizedIsInitialized = 0;
                return false;
            } else
            {
                memoizedIsInitialized = flag;
                return flag;
            }
        }

        public Builder newBuilderForType()
        {
            return newBuilder();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder newBuilderForType()
        {
            return newBuilderForType();
        }

        public Builder toBuilder()
        {
            return newBuilder(this);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder toBuilder()
        {
            return toBuilder();
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedoutputstream)
            throws IOException
        {
            getSerializedSize();
            for(int i = 0; i < positivePredicates_.size(); i++)
                codedoutputstream.writeMessage(1, (MessageLite)positivePredicates_.get(i));

            for(int j = 0; j < negativePredicates_.size(); j++)
                codedoutputstream.writeMessage(2, (MessageLite)negativePredicates_.get(j));

            for(int k = 0; k < addTags_.size(); k++)
                codedoutputstream.writeMessage(3, (MessageLite)addTags_.get(k));

            for(int l = 0; l < removeTags_.size(); l++)
                codedoutputstream.writeMessage(4, (MessageLite)removeTags_.get(l));

            for(int i1 = 0; i1 < addMacros_.size(); i1++)
                codedoutputstream.writeMessage(5, (MessageLite)addMacros_.get(i1));

            for(int j1 = 0; j1 < removeMacros_.size(); j1++)
                codedoutputstream.writeMessage(6, (MessageLite)removeMacros_.get(j1));

            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessage(7, result_);
            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int ADD_MACROS_FIELD_NUMBER = 5;
        public static final int ADD_TAGS_FIELD_NUMBER = 3;
        public static final int NEGATIVE_PREDICATES_FIELD_NUMBER = 2;
        public static Parser PARSER = new AbstractParser() {

            public ResolvedRule parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new ResolvedRule(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int POSITIVE_PREDICATES_FIELD_NUMBER = 1;
        public static final int REMOVE_MACROS_FIELD_NUMBER = 6;
        public static final int REMOVE_TAGS_FIELD_NUMBER = 4;
        public static final int RESULT_FIELD_NUMBER = 7;
        private static final ResolvedRule defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private List addMacros_;
        private List addTags_;
        private int bitField0_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List negativePredicates_;
        private List positivePredicates_;
        private List removeMacros_;
        private List removeTags_;
        private com.google.analytics.midtier.proto.containertag.TypeSystem.Value result_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new ResolvedRule(true);
            defaultInstance.initFields();
        }



/*
        static List access$1902(ResolvedRule resolvedrule, List list)
        {
            resolvedrule.positivePredicates_ = list;
            return list;
        }

*/



/*
        static List access$2002(ResolvedRule resolvedrule, List list)
        {
            resolvedrule.negativePredicates_ = list;
            return list;
        }

*/



/*
        static List access$2102(ResolvedRule resolvedrule, List list)
        {
            resolvedrule.addTags_ = list;
            return list;
        }

*/



/*
        static List access$2202(ResolvedRule resolvedrule, List list)
        {
            resolvedrule.removeTags_ = list;
            return list;
        }

*/



/*
        static List access$2302(ResolvedRule resolvedrule, List list)
        {
            resolvedrule.addMacros_ = list;
            return list;
        }

*/



/*
        static List access$2402(ResolvedRule resolvedrule, List list)
        {
            resolvedrule.removeMacros_ = list;
            return list;
        }

*/


/*
        static com.google.analytics.midtier.proto.containertag.TypeSystem.Value access$2502(ResolvedRule resolvedrule, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            resolvedrule.result_ = value;
            return value;
        }

*/


/*
        static int access$2602(ResolvedRule resolvedrule, int i)
        {
            resolvedrule.bitField0_ = i;
            return i;
        }

*/


        private ResolvedRule(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            int i;
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            i = 0;
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L21:
            if(flag) goto _L2; else goto _L1
_L1:
            int j = codedinputstream.readTag();
            j;
            JVM INSTR lookupswitch 8: default 124
        //                       0: 842
        //                       10: 143
        //                       18: 332
        //                       26: 394
        //                       34: 437
        //                       42: 483
        //                       50: 529
        //                       58: 575;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, j))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if((i & 1) == 1)
                break MISSING_BLOCK_LABEL_165;
            positivePredicates_ = new ArrayList();
            i |= 1;
            positivePredicates_.add(codedinputstream.readMessage(ResolvedFunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((i & 1) == 1)
                positivePredicates_ = Collections.unmodifiableList(positivePredicates_);
            if((i & 2) == 2)
                negativePredicates_ = Collections.unmodifiableList(negativePredicates_);
            if((i & 4) == 4)
                addTags_ = Collections.unmodifiableList(addTags_);
            if((i & 8) == 8)
                removeTags_ = Collections.unmodifiableList(removeTags_);
            if((i & 0x10) == 16)
                addMacros_ = Collections.unmodifiableList(addMacros_);
            if((i & 0x20) == 32)
                removeMacros_ = Collections.unmodifiableList(removeMacros_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L19:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            if((i & 2) == 2)
                break MISSING_BLOCK_LABEL_354;
            negativePredicates_ = new ArrayList();
            i |= 2;
            negativePredicates_.add(codedinputstream.readMessage(ResolvedFunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L7:
            if((i & 4) == 4)
                break MISSING_BLOCK_LABEL_416;
            addTags_ = new ArrayList();
            i |= 4;
            addTags_.add(codedinputstream.readMessage(ResolvedFunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L8:
            if((i & 8) == 8)
                break MISSING_BLOCK_LABEL_462;
            removeTags_ = new ArrayList();
            i |= 8;
            removeTags_.add(codedinputstream.readMessage(ResolvedFunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L9:
            if((i & 0x10) == 16)
                break MISSING_BLOCK_LABEL_508;
            addMacros_ = new ArrayList();
            i |= 0x10;
            addMacros_.add(codedinputstream.readMessage(ResolvedFunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L10:
            if((i & 0x20) == 32)
                break MISSING_BLOCK_LABEL_554;
            removeMacros_ = new ArrayList();
            i |= 0x20;
            removeMacros_.add(codedinputstream.readMessage(ResolvedFunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
_L11:
            int k = 1 & bitField0_;
            com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder = null;
            if(k != 1) goto _L13; else goto _L12
_L12:
            builder = result_.toBuilder();
_L13:
            result_ = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)codedinputstream.readMessage(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.PARSER, extensionregistrylite);
            if(builder == null) goto _L15; else goto _L14
_L14:
            builder.mergeFrom(result_);
            result_ = builder.buildPartial();
_L15:
            bitField0_ = 1 | bitField0_;
            continue; /* Loop/switch isn't completed */
_L2:
            if((i & 1) == 1)
                positivePredicates_ = Collections.unmodifiableList(positivePredicates_);
            if((i & 2) == 2)
                negativePredicates_ = Collections.unmodifiableList(negativePredicates_);
            if((i & 4) == 4)
                addTags_ = Collections.unmodifiableList(addTags_);
            if((i & 8) == 8)
                removeTags_ = Collections.unmodifiableList(removeTags_);
            if((i & 0x10) == 16)
                addMacros_ = Collections.unmodifiableList(addMacros_);
            if((i & 0x20) == 32)
                removeMacros_ = Collections.unmodifiableList(removeMacros_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L17:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L17; else goto _L16
_L16:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L19; else goto _L18
_L18:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L21; else goto _L20
_L20:
        }


        private ResolvedRule(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private ResolvedRule(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class ResolvedRule.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements ResolvedRuleOrBuilder
    {

        private static ResolvedRule.Builder create()
        {
            return new ResolvedRule.Builder();
        }

        private void ensureAddMacrosIsMutable()
        {
            if((0x10 & bitField0_) != 16)
            {
                addMacros_ = new ArrayList(addMacros_);
                bitField0_ = 0x10 | bitField0_;
            }
        }

        private void ensureAddTagsIsMutable()
        {
            if((4 & bitField0_) != 4)
            {
                addTags_ = new ArrayList(addTags_);
                bitField0_ = 4 | bitField0_;
            }
        }

        private void ensureNegativePredicatesIsMutable()
        {
            if((2 & bitField0_) != 2)
            {
                negativePredicates_ = new ArrayList(negativePredicates_);
                bitField0_ = 2 | bitField0_;
            }
        }

        private void ensurePositivePredicatesIsMutable()
        {
            if((1 & bitField0_) != 1)
            {
                positivePredicates_ = new ArrayList(positivePredicates_);
                bitField0_ = 1 | bitField0_;
            }
        }

        private void ensureRemoveMacrosIsMutable()
        {
            if((0x20 & bitField0_) != 32)
            {
                removeMacros_ = new ArrayList(removeMacros_);
                bitField0_ = 0x20 | bitField0_;
            }
        }

        private void ensureRemoveTagsIsMutable()
        {
            if((8 & bitField0_) != 8)
            {
                removeTags_ = new ArrayList(removeTags_);
                bitField0_ = 8 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public ResolvedRule.Builder addAddMacros(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureAddMacrosIsMutable();
            addMacros_.add(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder addAddMacros(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddMacrosIsMutable();
                addMacros_.add(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addAddMacros(ResolvedFunctionCall.Builder builder)
        {
            ensureAddMacrosIsMutable();
            addMacros_.add(builder.build());
            return this;
        }

        public ResolvedRule.Builder addAddMacros(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddMacrosIsMutable();
                addMacros_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addAddTags(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureAddTagsIsMutable();
            addTags_.add(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder addAddTags(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddTagsIsMutable();
                addTags_.add(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addAddTags(ResolvedFunctionCall.Builder builder)
        {
            ensureAddTagsIsMutable();
            addTags_.add(builder.build());
            return this;
        }

        public ResolvedRule.Builder addAddTags(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddTagsIsMutable();
                addTags_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addAllAddMacros(Iterable iterable)
        {
            ensureAddMacrosIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, addMacros_);
            return this;
        }

        public ResolvedRule.Builder addAllAddTags(Iterable iterable)
        {
            ensureAddTagsIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, addTags_);
            return this;
        }

        public ResolvedRule.Builder addAllNegativePredicates(Iterable iterable)
        {
            ensureNegativePredicatesIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, negativePredicates_);
            return this;
        }

        public ResolvedRule.Builder addAllPositivePredicates(Iterable iterable)
        {
            ensurePositivePredicatesIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, positivePredicates_);
            return this;
        }

        public ResolvedRule.Builder addAllRemoveMacros(Iterable iterable)
        {
            ensureRemoveMacrosIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, removeMacros_);
            return this;
        }

        public ResolvedRule.Builder addAllRemoveTags(Iterable iterable)
        {
            ensureRemoveTagsIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, removeTags_);
            return this;
        }

        public ResolvedRule.Builder addNegativePredicates(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureNegativePredicatesIsMutable();
            negativePredicates_.add(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder addNegativePredicates(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureNegativePredicatesIsMutable();
                negativePredicates_.add(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addNegativePredicates(ResolvedFunctionCall.Builder builder)
        {
            ensureNegativePredicatesIsMutable();
            negativePredicates_.add(builder.build());
            return this;
        }

        public ResolvedRule.Builder addNegativePredicates(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureNegativePredicatesIsMutable();
                negativePredicates_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addPositivePredicates(int i, ResolvedFunctionCall.Builder builder)
        {
            ensurePositivePredicatesIsMutable();
            positivePredicates_.add(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder addPositivePredicates(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePositivePredicatesIsMutable();
                positivePredicates_.add(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addPositivePredicates(ResolvedFunctionCall.Builder builder)
        {
            ensurePositivePredicatesIsMutable();
            positivePredicates_.add(builder.build());
            return this;
        }

        public ResolvedRule.Builder addPositivePredicates(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePositivePredicatesIsMutable();
                positivePredicates_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addRemoveMacros(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureRemoveMacrosIsMutable();
            removeMacros_.add(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder addRemoveMacros(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveMacrosIsMutable();
                removeMacros_.add(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addRemoveMacros(ResolvedFunctionCall.Builder builder)
        {
            ensureRemoveMacrosIsMutable();
            removeMacros_.add(builder.build());
            return this;
        }

        public ResolvedRule.Builder addRemoveMacros(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveMacrosIsMutable();
                removeMacros_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addRemoveTags(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureRemoveTagsIsMutable();
            removeTags_.add(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder addRemoveTags(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveTagsIsMutable();
                removeTags_.add(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder addRemoveTags(ResolvedFunctionCall.Builder builder)
        {
            ensureRemoveTagsIsMutable();
            removeTags_.add(builder.build());
            return this;
        }

        public ResolvedRule.Builder addRemoveTags(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveTagsIsMutable();
                removeTags_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule build()
        {
            ResolvedRule resolvedrule = buildPartial();
            if(!resolvedrule.isInitialized())
                throw newUninitializedMessageException(resolvedrule);
            else
                return resolvedrule;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public ResolvedRule buildPartial()
        {
            ResolvedRule resolvedrule = new ResolvedRule(this);
            int i = bitField0_;
            if((1 & bitField0_) == 1)
            {
                positivePredicates_ = Collections.unmodifiableList(positivePredicates_);
                bitField0_ = -2 & bitField0_;
            }
            resolvedrule.positivePredicates_ = positivePredicates_;
            if((2 & bitField0_) == 2)
            {
                negativePredicates_ = Collections.unmodifiableList(negativePredicates_);
                bitField0_ = -3 & bitField0_;
            }
            resolvedrule.negativePredicates_ = negativePredicates_;
            if((4 & bitField0_) == 4)
            {
                addTags_ = Collections.unmodifiableList(addTags_);
                bitField0_ = -5 & bitField0_;
            }
            resolvedrule.addTags_ = addTags_;
            if((8 & bitField0_) == 8)
            {
                removeTags_ = Collections.unmodifiableList(removeTags_);
                bitField0_ = -9 & bitField0_;
            }
            resolvedrule.removeTags_ = removeTags_;
            if((0x10 & bitField0_) == 16)
            {
                addMacros_ = Collections.unmodifiableList(addMacros_);
                bitField0_ = 0xffffffef & bitField0_;
            }
            resolvedrule.addMacros_ = addMacros_;
            if((0x20 & bitField0_) == 32)
            {
                removeMacros_ = Collections.unmodifiableList(removeMacros_);
                bitField0_ = 0xffffffdf & bitField0_;
            }
            resolvedrule.removeMacros_ = removeMacros_;
            int j = i & 0x40;
            int k = 0;
            if(j == 64)
                k = false | true;
            resolvedrule.result_ = result_;
            resolvedrule.bitField0_ = k;
            return resolvedrule;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public ResolvedRule.Builder clear()
        {
            super.clear();
            positivePredicates_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            negativePredicates_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            addTags_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            removeTags_ = Collections.emptyList();
            bitField0_ = -9 & bitField0_;
            addMacros_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            removeMacros_ = Collections.emptyList();
            bitField0_ = 0xffffffdf & bitField0_;
            result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            bitField0_ = 0xffffffbf & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public ResolvedRule.Builder clearAddMacros()
        {
            addMacros_ = Collections.emptyList();
            bitField0_ = 0xffffffef & bitField0_;
            return this;
        }

        public ResolvedRule.Builder clearAddTags()
        {
            addTags_ = Collections.emptyList();
            bitField0_ = -5 & bitField0_;
            return this;
        }

        public ResolvedRule.Builder clearNegativePredicates()
        {
            negativePredicates_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public ResolvedRule.Builder clearPositivePredicates()
        {
            positivePredicates_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public ResolvedRule.Builder clearRemoveMacros()
        {
            removeMacros_ = Collections.emptyList();
            bitField0_ = 0xffffffdf & bitField0_;
            return this;
        }

        public ResolvedRule.Builder clearRemoveTags()
        {
            removeTags_ = Collections.emptyList();
            bitField0_ = -9 & bitField0_;
            return this;
        }

        public ResolvedRule.Builder clearResult()
        {
            result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            bitField0_ = 0xffffffbf & bitField0_;
            return this;
        }

        public ResolvedRule.Builder clone()
        {
            return create().mergeFrom(buildPartial());
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public ResolvedFunctionCall getAddMacros(int i)
        {
            return (ResolvedFunctionCall)addMacros_.get(i);
        }

        public int getAddMacrosCount()
        {
            return addMacros_.size();
        }

        public List getAddMacrosList()
        {
            return Collections.unmodifiableList(addMacros_);
        }

        public ResolvedFunctionCall getAddTags(int i)
        {
            return (ResolvedFunctionCall)addTags_.get(i);
        }

        public int getAddTagsCount()
        {
            return addTags_.size();
        }

        public List getAddTagsList()
        {
            return Collections.unmodifiableList(addTags_);
        }

        public ResolvedRule getDefaultInstanceForType()
        {
            return ResolvedRule.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedFunctionCall getNegativePredicates(int i)
        {
            return (ResolvedFunctionCall)negativePredicates_.get(i);
        }

        public int getNegativePredicatesCount()
        {
            return negativePredicates_.size();
        }

        public List getNegativePredicatesList()
        {
            return Collections.unmodifiableList(negativePredicates_);
        }

        public ResolvedFunctionCall getPositivePredicates(int i)
        {
            return (ResolvedFunctionCall)positivePredicates_.get(i);
        }

        public int getPositivePredicatesCount()
        {
            return positivePredicates_.size();
        }

        public List getPositivePredicatesList()
        {
            return Collections.unmodifiableList(positivePredicates_);
        }

        public ResolvedFunctionCall getRemoveMacros(int i)
        {
            return (ResolvedFunctionCall)removeMacros_.get(i);
        }

        public int getRemoveMacrosCount()
        {
            return removeMacros_.size();
        }

        public List getRemoveMacrosList()
        {
            return Collections.unmodifiableList(removeMacros_);
        }

        public ResolvedFunctionCall getRemoveTags(int i)
        {
            return (ResolvedFunctionCall)removeTags_.get(i);
        }

        public int getRemoveTagsCount()
        {
            return removeTags_.size();
        }

        public List getRemoveTagsList()
        {
            return Collections.unmodifiableList(removeTags_);
        }

        public com.google.analytics.midtier.proto.containertag.TypeSystem.Value getResult()
        {
            return result_;
        }

        public boolean hasResult()
        {
            return (0x40 & bitField0_) == 64;
        }

        public final boolean isInitialized()
        {
            int i = 0;
_L17:
            if(i >= getPositivePredicatesCount()) goto _L2; else goto _L1
_L1:
            if(getPositivePredicates(i).isInitialized()) goto _L4; else goto _L3
_L3:
            return false;
_L4:
            i++;
            continue; /* Loop/switch isn't completed */
_L2:
            int j = 0;
_L6:
            if(j >= getNegativePredicatesCount())
                break MISSING_BLOCK_LABEL_56;
            if(!getNegativePredicates(j).isInitialized()) goto _L3; else goto _L5
_L5:
            j++;
              goto _L6
            int k = 0;
_L8:
            if(k >= getAddTagsCount())
                break MISSING_BLOCK_LABEL_83;
            if(!getAddTags(k).isInitialized()) goto _L3; else goto _L7
_L7:
            k++;
              goto _L8
            int l = 0;
_L10:
            if(l >= getRemoveTagsCount())
                break MISSING_BLOCK_LABEL_113;
            if(!getRemoveTags(l).isInitialized()) goto _L3; else goto _L9
_L9:
            l++;
              goto _L10
            int i1 = 0;
_L12:
            if(i1 >= getAddMacrosCount())
                break MISSING_BLOCK_LABEL_143;
            if(!getAddMacros(i1).isInitialized()) goto _L3; else goto _L11
_L11:
            i1++;
              goto _L12
            int j1 = 0;
_L14:
            if(j1 >= getRemoveMacrosCount())
                continue; /* Loop/switch isn't completed */
            if(!getRemoveMacros(j1).isInitialized()) goto _L3; else goto _L13
_L13:
            j1++;
              goto _L14
            if(hasResult() && !getResult().isInitialized()) goto _L3; else goto _L15
_L15:
            return true;
            if(true) goto _L17; else goto _L16
_L16:
        }

        public ResolvedRule.Builder mergeFrom(ResolvedRule resolvedrule)
        {
            if(resolvedrule == ResolvedRule.getDefaultInstance())
                return this;
            if(!resolvedrule.positivePredicates_.isEmpty())
                if(positivePredicates_.isEmpty())
                {
                    positivePredicates_ = resolvedrule.positivePredicates_;
                    bitField0_ = -2 & bitField0_;
                } else
                {
                    ensurePositivePredicatesIsMutable();
                    positivePredicates_.addAll(resolvedrule.positivePredicates_);
                }
            if(!resolvedrule.negativePredicates_.isEmpty())
                if(negativePredicates_.isEmpty())
                {
                    negativePredicates_ = resolvedrule.negativePredicates_;
                    bitField0_ = -3 & bitField0_;
                } else
                {
                    ensureNegativePredicatesIsMutable();
                    negativePredicates_.addAll(resolvedrule.negativePredicates_);
                }
            if(!resolvedrule.addTags_.isEmpty())
                if(addTags_.isEmpty())
                {
                    addTags_ = resolvedrule.addTags_;
                    bitField0_ = -5 & bitField0_;
                } else
                {
                    ensureAddTagsIsMutable();
                    addTags_.addAll(resolvedrule.addTags_);
                }
            if(!resolvedrule.removeTags_.isEmpty())
                if(removeTags_.isEmpty())
                {
                    removeTags_ = resolvedrule.removeTags_;
                    bitField0_ = -9 & bitField0_;
                } else
                {
                    ensureRemoveTagsIsMutable();
                    removeTags_.addAll(resolvedrule.removeTags_);
                }
            if(!resolvedrule.addMacros_.isEmpty())
                if(addMacros_.isEmpty())
                {
                    addMacros_ = resolvedrule.addMacros_;
                    bitField0_ = 0xffffffef & bitField0_;
                } else
                {
                    ensureAddMacrosIsMutable();
                    addMacros_.addAll(resolvedrule.addMacros_);
                }
            if(!resolvedrule.removeMacros_.isEmpty())
                if(removeMacros_.isEmpty())
                {
                    removeMacros_ = resolvedrule.removeMacros_;
                    bitField0_ = 0xffffffdf & bitField0_;
                } else
                {
                    ensureRemoveMacrosIsMutable();
                    removeMacros_.addAll(resolvedrule.removeMacros_);
                }
            if(resolvedrule.hasResult())
                mergeResult(resolvedrule.getResult());
            setUnknownFields(getUnknownFields().concat(resolvedrule.unknownFields));
            return this;
        }

        public ResolvedRule.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            ResolvedRule resolvedrule = null;
            ResolvedRule resolvedrule1 = (ResolvedRule)ResolvedRule.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(resolvedrule1 != null)
                mergeFrom(resolvedrule1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            resolvedrule = (ResolvedRule)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(resolvedrule != null)
                mergeFrom(resolvedrule);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((ResolvedRule)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public ResolvedRule.Builder mergeResult(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            if((0x40 & bitField0_) == 64 && result_ != com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance())
                result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.newBuilder(result_).mergeFrom(value).buildPartial();
            else
                result_ = value;
            bitField0_ = 0x40 | bitField0_;
            return this;
        }

        public ResolvedRule.Builder removeAddMacros(int i)
        {
            ensureAddMacrosIsMutable();
            addMacros_.remove(i);
            return this;
        }

        public ResolvedRule.Builder removeAddTags(int i)
        {
            ensureAddTagsIsMutable();
            addTags_.remove(i);
            return this;
        }

        public ResolvedRule.Builder removeNegativePredicates(int i)
        {
            ensureNegativePredicatesIsMutable();
            negativePredicates_.remove(i);
            return this;
        }

        public ResolvedRule.Builder removePositivePredicates(int i)
        {
            ensurePositivePredicatesIsMutable();
            positivePredicates_.remove(i);
            return this;
        }

        public ResolvedRule.Builder removeRemoveMacros(int i)
        {
            ensureRemoveMacrosIsMutable();
            removeMacros_.remove(i);
            return this;
        }

        public ResolvedRule.Builder removeRemoveTags(int i)
        {
            ensureRemoveTagsIsMutable();
            removeTags_.remove(i);
            return this;
        }

        public ResolvedRule.Builder setAddMacros(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureAddMacrosIsMutable();
            addMacros_.set(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder setAddMacros(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddMacrosIsMutable();
                addMacros_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder setAddTags(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureAddTagsIsMutable();
            addTags_.set(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder setAddTags(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddTagsIsMutable();
                addTags_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder setNegativePredicates(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureNegativePredicatesIsMutable();
            negativePredicates_.set(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder setNegativePredicates(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureNegativePredicatesIsMutable();
                negativePredicates_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder setPositivePredicates(int i, ResolvedFunctionCall.Builder builder)
        {
            ensurePositivePredicatesIsMutable();
            positivePredicates_.set(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder setPositivePredicates(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePositivePredicatesIsMutable();
                positivePredicates_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder setRemoveMacros(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureRemoveMacrosIsMutable();
            removeMacros_.set(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder setRemoveMacros(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveMacrosIsMutable();
                removeMacros_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder setRemoveTags(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureRemoveTagsIsMutable();
            removeTags_.set(i, builder.build());
            return this;
        }

        public ResolvedRule.Builder setRemoveTags(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveTagsIsMutable();
                removeTags_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule.Builder setResult(com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder)
        {
            result_ = builder.build();
            bitField0_ = 0x40 | bitField0_;
            return this;
        }

        public ResolvedRule.Builder setResult(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                result_ = value;
                bitField0_ = 0x40 | bitField0_;
                return this;
            }
        }

        private List addMacros_;
        private List addTags_;
        private int bitField0_;
        private List negativePredicates_;
        private List positivePredicates_;
        private List removeMacros_;
        private List removeTags_;
        private com.google.analytics.midtier.proto.containertag.TypeSystem.Value result_;


        private ResolvedRule.Builder()
        {
            positivePredicates_ = Collections.emptyList();
            negativePredicates_ = Collections.emptyList();
            addTags_ = Collections.emptyList();
            removeTags_ = Collections.emptyList();
            addMacros_ = Collections.emptyList();
            removeMacros_ = Collections.emptyList();
            result_ = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.getDefaultInstance();
            maybeForceBuilderInitialization();
        }
    }

    public static interface ResolvedRuleOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract ResolvedFunctionCall getAddMacros(int i);

        public abstract int getAddMacrosCount();

        public abstract List getAddMacrosList();

        public abstract ResolvedFunctionCall getAddTags(int i);

        public abstract int getAddTagsCount();

        public abstract List getAddTagsList();

        public abstract ResolvedFunctionCall getNegativePredicates(int i);

        public abstract int getNegativePredicatesCount();

        public abstract List getNegativePredicatesList();

        public abstract ResolvedFunctionCall getPositivePredicates(int i);

        public abstract int getPositivePredicatesCount();

        public abstract List getPositivePredicatesList();

        public abstract ResolvedFunctionCall getRemoveMacros(int i);

        public abstract int getRemoveMacrosCount();

        public abstract List getRemoveMacrosList();

        public abstract ResolvedFunctionCall getRemoveTags(int i);

        public abstract int getRemoveTagsCount();

        public abstract List getRemoveTagsList();

        public abstract com.google.analytics.midtier.proto.containertag.TypeSystem.Value getResult();

        public abstract boolean hasResult();
    }

    public static final class RuleEvaluationStepInfo extends GeneratedMessageLite
        implements RuleEvaluationStepInfoOrBuilder
    {

        public static RuleEvaluationStepInfo getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            rules_ = Collections.emptyList();
            enabledFunctions_ = Collections.emptyList();
        }

        public static Builder newBuilder()
        {
            return Builder.create();
        }

        public static Builder newBuilder(RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            return newBuilder().mergeFrom(ruleevaluationstepinfo);
        }

        public static RuleEvaluationStepInfo parseDelimitedFrom(InputStream inputstream)
            throws IOException
        {
            return (RuleEvaluationStepInfo)PARSER.parseDelimitedFrom(inputstream);
        }

        public static RuleEvaluationStepInfo parseDelimitedFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (RuleEvaluationStepInfo)PARSER.parseDelimitedFrom(inputstream, extensionregistrylite);
        }

        public static RuleEvaluationStepInfo parseFrom(ByteString bytestring)
            throws InvalidProtocolBufferException
        {
            return (RuleEvaluationStepInfo)PARSER.parseFrom(bytestring);
        }

        public static RuleEvaluationStepInfo parseFrom(ByteString bytestring, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (RuleEvaluationStepInfo)PARSER.parseFrom(bytestring, extensionregistrylite);
        }

        public static RuleEvaluationStepInfo parseFrom(CodedInputStream codedinputstream)
            throws IOException
        {
            return (RuleEvaluationStepInfo)PARSER.parseFrom(codedinputstream);
        }

        public static RuleEvaluationStepInfo parseFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (RuleEvaluationStepInfo)PARSER.parseFrom(codedinputstream, extensionregistrylite);
        }

        public static RuleEvaluationStepInfo parseFrom(InputStream inputstream)
            throws IOException
        {
            return (RuleEvaluationStepInfo)PARSER.parseFrom(inputstream);
        }

        public static RuleEvaluationStepInfo parseFrom(InputStream inputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return (RuleEvaluationStepInfo)PARSER.parseFrom(inputstream, extensionregistrylite);
        }

        public static RuleEvaluationStepInfo parseFrom(byte abyte0[])
            throws InvalidProtocolBufferException
        {
            return (RuleEvaluationStepInfo)PARSER.parseFrom(abyte0);
        }

        public static RuleEvaluationStepInfo parseFrom(byte abyte0[], ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            return (RuleEvaluationStepInfo)PARSER.parseFrom(abyte0, extensionregistrylite);
        }

        public boolean equals(Object obj)
        {
            if(obj == this)
                return true;
            if(!(obj instanceof RuleEvaluationStepInfo))
                return super.equals(obj);
            RuleEvaluationStepInfo ruleevaluationstepinfo = (RuleEvaluationStepInfo)obj;
            boolean flag;
            boolean flag1;
            if(true && getRulesList().equals(ruleevaluationstepinfo.getRulesList()))
                flag = true;
            else
                flag = false;
            if(flag && getEnabledFunctionsList().equals(ruleevaluationstepinfo.getEnabledFunctionsList()))
                flag1 = true;
            else
                flag1 = false;
            return flag1;
        }

        public RuleEvaluationStepInfo getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedFunctionCall getEnabledFunctions(int i)
        {
            return (ResolvedFunctionCall)enabledFunctions_.get(i);
        }

        public int getEnabledFunctionsCount()
        {
            return enabledFunctions_.size();
        }

        public List getEnabledFunctionsList()
        {
            return enabledFunctions_;
        }

        public ResolvedFunctionCallOrBuilder getEnabledFunctionsOrBuilder(int i)
        {
            return (ResolvedFunctionCallOrBuilder)enabledFunctions_.get(i);
        }

        public List getEnabledFunctionsOrBuilderList()
        {
            return enabledFunctions_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public ResolvedRule getRules(int i)
        {
            return (ResolvedRule)rules_.get(i);
        }

        public int getRulesCount()
        {
            return rules_.size();
        }

        public List getRulesList()
        {
            return rules_;
        }

        public ResolvedRuleOrBuilder getRulesOrBuilder(int i)
        {
            return (ResolvedRuleOrBuilder)rules_.get(i);
        }

        public List getRulesOrBuilderList()
        {
            return rules_;
        }

        public int getSerializedSize()
        {
            int i = memoizedSerializedSize;
            if(i != -1)
                return i;
            int j = 0;
            for(int k = 0; k < rules_.size(); k++)
                j += CodedOutputStream.computeMessageSize(1, (MessageLite)rules_.get(k));

            for(int l = 0; l < enabledFunctions_.size(); l++)
                j += CodedOutputStream.computeMessageSize(2, (MessageLite)enabledFunctions_.get(l));

            int i1 = j + unknownFields.size();
            memoizedSerializedSize = i1;
            return i1;
        }

        public int hashCode()
        {
            if(memoizedHashCode != 0)
                return memoizedHashCode;
            int i = 779 + com/google/analytics/containertag/proto/Debug$RuleEvaluationStepInfo.hashCode();
            if(getRulesCount() > 0)
                i = 53 * (1 + i * 37) + getRulesList().hashCode();
            if(getEnabledFunctionsCount() > 0)
                i = 53 * (2 + i * 37) + getEnabledFunctionsList().hashCode();
            int j = i * 29 + unknownFields.hashCode();
            memoizedHashCode = j;
            return j;
        }

        protected MutableMessageLite internalMutableDefault()
        {
            if(mutableDefault == null)
                mutableDefault = internalMutableDefault("com.google.analytics.containertag.proto.MutableDebug$RuleEvaluationStepInfo");
            return mutableDefault;
        }

        public final boolean isInitialized()
        {
            byte byte0 = memoizedIsInitialized;
            if(byte0 != -1)
                return byte0 == 1;
            for(int i = 0; i < getRulesCount(); i++)
                if(!getRules(i).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            for(int j = 0; j < getEnabledFunctionsCount(); j++)
                if(!getEnabledFunctions(j).isInitialized())
                {
                    memoizedIsInitialized = 0;
                    return false;
                }

            memoizedIsInitialized = 1;
            return true;
        }

        public Builder newBuilderForType()
        {
            return newBuilder();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder newBuilderForType()
        {
            return newBuilderForType();
        }

        public Builder toBuilder()
        {
            return newBuilder(this);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder toBuilder()
        {
            return toBuilder();
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeTo(CodedOutputStream codedoutputstream)
            throws IOException
        {
            getSerializedSize();
            for(int i = 0; i < rules_.size(); i++)
                codedoutputstream.writeMessage(1, (MessageLite)rules_.get(i));

            for(int j = 0; j < enabledFunctions_.size(); j++)
                codedoutputstream.writeMessage(2, (MessageLite)enabledFunctions_.get(j));

            codedoutputstream.writeRawBytes(unknownFields);
        }

        public static final int ENABLED_FUNCTIONS_FIELD_NUMBER = 2;
        public static Parser PARSER = new AbstractParser() {

            public RuleEvaluationStepInfo parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return new RuleEvaluationStepInfo(codedinputstream, extensionregistrylite);
            }

            public volatile Object parsePartialFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
                throws InvalidProtocolBufferException
            {
                return parsePartialFrom(codedinputstream, extensionregistrylite);
            }

        }
;
        public static final int RULES_FIELD_NUMBER = 1;
        private static final RuleEvaluationStepInfo defaultInstance;
        private static volatile MutableMessageLite mutableDefault = null;
        private static final long serialVersionUID;
        private List enabledFunctions_;
        private byte memoizedIsInitialized;
        private int memoizedSerializedSize;
        private List rules_;
        private final ByteString unknownFields;

        static 
        {
            defaultInstance = new RuleEvaluationStepInfo(true);
            defaultInstance.initFields();
        }



/*
        static List access$3902(RuleEvaluationStepInfo ruleevaluationstepinfo, List list)
        {
            ruleevaluationstepinfo.rules_ = list;
            return list;
        }

*/



/*
        static List access$4002(RuleEvaluationStepInfo ruleevaluationstepinfo, List list)
        {
            ruleevaluationstepinfo.enabledFunctions_ = list;
            return list;
        }

*/


        private RuleEvaluationStepInfo(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws InvalidProtocolBufferException
        {
            int i;
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            initFields();
            i = 0;
            output = ByteString.newOutput();
            codedoutputstream = CodedOutputStream.newInstance(output);
            flag = false;
_L12:
            if(flag) goto _L2; else goto _L1
_L1:
            int j = codedinputstream.readTag();
            j;
            JVM INSTR lookupswitch 3: default 84
        //                       0: 387
        //                       10: 103
        //                       18: 214;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, j))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if((i & 1) == 1)
                break MISSING_BLOCK_LABEL_125;
            rules_ = new ArrayList();
            i |= 1;
            rules_.add(codedinputstream.readMessage(ResolvedRule.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            throw invalidprotocolbufferexception.setUnfinishedMessage(this);
            Exception exception1;
            exception1;
            if((i & 1) == 1)
                rules_ = Collections.unmodifiableList(rules_);
            if((i & 2) == 2)
                enabledFunctions_ = Collections.unmodifiableList(enabledFunctions_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L10:
            makeExtensionsImmutable();
            throw exception1;
_L6:
            if((i & 2) == 2)
                break MISSING_BLOCK_LABEL_236;
            enabledFunctions_ = new ArrayList();
            i |= 2;
            enabledFunctions_.add(codedinputstream.readMessage(ResolvedFunctionCall.PARSER, extensionregistrylite));
            continue; /* Loop/switch isn't completed */
            IOException ioexception1;
            ioexception1;
            throw (new InvalidProtocolBufferException(ioexception1.getMessage())).setUnfinishedMessage(this);
_L2:
            if((i & 1) == 1)
                rules_ = Collections.unmodifiableList(rules_);
            if((i & 2) == 2)
                enabledFunctions_ = Collections.unmodifiableList(enabledFunctions_);
            codedoutputstream.flush();
            unknownFields = output.toByteString();
_L8:
            makeExtensionsImmutable();
            return;
            IOException ioexception;
            ioexception;
            unknownFields = output.toByteString();
            if(true) goto _L8; else goto _L7
_L7:
            Exception exception;
            exception;
            unknownFields = output.toByteString();
            throw exception;
            IOException ioexception2;
            ioexception2;
            unknownFields = output.toByteString();
            if(true) goto _L10; else goto _L9
_L9:
            Exception exception2;
            exception2;
            unknownFields = output.toByteString();
            throw exception2;
_L4:
            flag = true;
            if(true) goto _L12; else goto _L11
_L11:
        }


        private RuleEvaluationStepInfo(com.google.tagmanager.protobuf.GeneratedMessageLite.Builder builder)
        {
            super(builder);
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = builder.getUnknownFields();
        }


        private RuleEvaluationStepInfo(boolean flag)
        {
            memoizedIsInitialized = -1;
            memoizedSerializedSize = -1;
            unknownFields = ByteString.EMPTY;
        }
    }

    public static final class RuleEvaluationStepInfo.Builder extends com.google.tagmanager.protobuf.GeneratedMessageLite.Builder
        implements RuleEvaluationStepInfoOrBuilder
    {

        private static RuleEvaluationStepInfo.Builder create()
        {
            return new RuleEvaluationStepInfo.Builder();
        }

        private void ensureEnabledFunctionsIsMutable()
        {
            if((2 & bitField0_) != 2)
            {
                enabledFunctions_ = new ArrayList(enabledFunctions_);
                bitField0_ = 2 | bitField0_;
            }
        }

        private void ensureRulesIsMutable()
        {
            if((1 & bitField0_) != 1)
            {
                rules_ = new ArrayList(rules_);
                bitField0_ = 1 | bitField0_;
            }
        }

        private void maybeForceBuilderInitialization()
        {
        }

        public RuleEvaluationStepInfo.Builder addAllEnabledFunctions(Iterable iterable)
        {
            ensureEnabledFunctionsIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, enabledFunctions_);
            return this;
        }

        public RuleEvaluationStepInfo.Builder addAllRules(Iterable iterable)
        {
            ensureRulesIsMutable();
            com.google.tagmanager.protobuf.AbstractMessageLite.Builder.addAll(iterable, rules_);
            return this;
        }

        public RuleEvaluationStepInfo.Builder addEnabledFunctions(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureEnabledFunctionsIsMutable();
            enabledFunctions_.add(i, builder.build());
            return this;
        }

        public RuleEvaluationStepInfo.Builder addEnabledFunctions(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEnabledFunctionsIsMutable();
                enabledFunctions_.add(i, resolvedfunctioncall);
                return this;
            }
        }

        public RuleEvaluationStepInfo.Builder addEnabledFunctions(ResolvedFunctionCall.Builder builder)
        {
            ensureEnabledFunctionsIsMutable();
            enabledFunctions_.add(builder.build());
            return this;
        }

        public RuleEvaluationStepInfo.Builder addEnabledFunctions(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEnabledFunctionsIsMutable();
                enabledFunctions_.add(resolvedfunctioncall);
                return this;
            }
        }

        public RuleEvaluationStepInfo.Builder addRules(int i, ResolvedRule.Builder builder)
        {
            ensureRulesIsMutable();
            rules_.add(i, builder.build());
            return this;
        }

        public RuleEvaluationStepInfo.Builder addRules(int i, ResolvedRule resolvedrule)
        {
            if(resolvedrule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRulesIsMutable();
                rules_.add(i, resolvedrule);
                return this;
            }
        }

        public RuleEvaluationStepInfo.Builder addRules(ResolvedRule.Builder builder)
        {
            ensureRulesIsMutable();
            rules_.add(builder.build());
            return this;
        }

        public RuleEvaluationStepInfo.Builder addRules(ResolvedRule resolvedrule)
        {
            if(resolvedrule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRulesIsMutable();
                rules_.add(resolvedrule);
                return this;
            }
        }

        public RuleEvaluationStepInfo build()
        {
            RuleEvaluationStepInfo ruleevaluationstepinfo = buildPartial();
            if(!ruleevaluationstepinfo.isInitialized())
                throw newUninitializedMessageException(ruleevaluationstepinfo);
            else
                return ruleevaluationstepinfo;
        }

        public volatile MessageLite build()
        {
            return build();
        }

        public RuleEvaluationStepInfo buildPartial()
        {
            RuleEvaluationStepInfo ruleevaluationstepinfo = new RuleEvaluationStepInfo(this);
            int _tmp = bitField0_;
            if((1 & bitField0_) == 1)
            {
                rules_ = Collections.unmodifiableList(rules_);
                bitField0_ = -2 & bitField0_;
            }
            ruleevaluationstepinfo.rules_ = rules_;
            if((2 & bitField0_) == 2)
            {
                enabledFunctions_ = Collections.unmodifiableList(enabledFunctions_);
                bitField0_ = -3 & bitField0_;
            }
            ruleevaluationstepinfo.enabledFunctions_ = enabledFunctions_;
            return ruleevaluationstepinfo;
        }

        public volatile MessageLite buildPartial()
        {
            return buildPartial();
        }

        public RuleEvaluationStepInfo.Builder clear()
        {
            super.clear();
            rules_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            enabledFunctions_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clear()
        {
            return clear();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clear()
        {
            return clear();
        }

        public RuleEvaluationStepInfo.Builder clearEnabledFunctions()
        {
            enabledFunctions_ = Collections.emptyList();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public RuleEvaluationStepInfo.Builder clearRules()
        {
            rules_ = Collections.emptyList();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public RuleEvaluationStepInfo.Builder clone()
        {
            return create().mergeFrom(buildPartial());
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder clone()
        {
            return clone();
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
        }

        public RuleEvaluationStepInfo getDefaultInstanceForType()
        {
            return RuleEvaluationStepInfo.getDefaultInstance();
        }

        public volatile GeneratedMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedFunctionCall getEnabledFunctions(int i)
        {
            return (ResolvedFunctionCall)enabledFunctions_.get(i);
        }

        public int getEnabledFunctionsCount()
        {
            return enabledFunctions_.size();
        }

        public List getEnabledFunctionsList()
        {
            return Collections.unmodifiableList(enabledFunctions_);
        }

        public ResolvedRule getRules(int i)
        {
            return (ResolvedRule)rules_.get(i);
        }

        public int getRulesCount()
        {
            return rules_.size();
        }

        public List getRulesList()
        {
            return Collections.unmodifiableList(rules_);
        }

        public final boolean isInitialized()
        {
            int i = 0;
_L7:
            if(i >= getRulesCount()) goto _L2; else goto _L1
_L1:
            if(getRules(i).isInitialized()) goto _L4; else goto _L3
_L3:
            return false;
_L4:
            i++;
            continue; /* Loop/switch isn't completed */
_L2:
            int j = 0;
label0:
            do
            {
label1:
                {
                    if(j >= getEnabledFunctionsCount())
                        break label1;
                    if(!getEnabledFunctions(j).isInitialized())
                        break label0;
                    j++;
                }
            } while(true);
            if(true) goto _L3; else goto _L5
_L5:
            return true;
            if(true) goto _L7; else goto _L6
_L6:
        }

        public RuleEvaluationStepInfo.Builder mergeFrom(RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            if(ruleevaluationstepinfo == RuleEvaluationStepInfo.getDefaultInstance())
                return this;
            if(!ruleevaluationstepinfo.rules_.isEmpty())
                if(rules_.isEmpty())
                {
                    rules_ = ruleevaluationstepinfo.rules_;
                    bitField0_ = -2 & bitField0_;
                } else
                {
                    ensureRulesIsMutable();
                    rules_.addAll(ruleevaluationstepinfo.rules_);
                }
            if(!ruleevaluationstepinfo.enabledFunctions_.isEmpty())
                if(enabledFunctions_.isEmpty())
                {
                    enabledFunctions_ = ruleevaluationstepinfo.enabledFunctions_;
                    bitField0_ = -3 & bitField0_;
                } else
                {
                    ensureEnabledFunctionsIsMutable();
                    enabledFunctions_.addAll(ruleevaluationstepinfo.enabledFunctions_);
                }
            setUnknownFields(getUnknownFields().concat(ruleevaluationstepinfo.unknownFields));
            return this;
        }

        public RuleEvaluationStepInfo.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            RuleEvaluationStepInfo ruleevaluationstepinfo = null;
            RuleEvaluationStepInfo ruleevaluationstepinfo1 = (RuleEvaluationStepInfo)RuleEvaluationStepInfo.PARSER.parsePartialFrom(codedinputstream, extensionregistrylite);
            if(ruleevaluationstepinfo1 != null)
                mergeFrom(ruleevaluationstepinfo1);
            return this;
            InvalidProtocolBufferException invalidprotocolbufferexception;
            invalidprotocolbufferexception;
            ruleevaluationstepinfo = (RuleEvaluationStepInfo)invalidprotocolbufferexception.getUnfinishedMessage();
            throw invalidprotocolbufferexception;
            Exception exception;
            exception;
            if(ruleevaluationstepinfo != null)
                mergeFrom(ruleevaluationstepinfo);
            throw exception;
        }

        public volatile com.google.tagmanager.protobuf.AbstractMessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public volatile com.google.tagmanager.protobuf.GeneratedMessageLite.Builder mergeFrom(GeneratedMessageLite generatedmessagelite)
        {
            return mergeFrom((RuleEvaluationStepInfo)generatedmessagelite);
        }

        public volatile com.google.tagmanager.protobuf.MessageLite.Builder mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
            throws IOException
        {
            return mergeFrom(codedinputstream, extensionregistrylite);
        }

        public RuleEvaluationStepInfo.Builder removeEnabledFunctions(int i)
        {
            ensureEnabledFunctionsIsMutable();
            enabledFunctions_.remove(i);
            return this;
        }

        public RuleEvaluationStepInfo.Builder removeRules(int i)
        {
            ensureRulesIsMutable();
            rules_.remove(i);
            return this;
        }

        public RuleEvaluationStepInfo.Builder setEnabledFunctions(int i, ResolvedFunctionCall.Builder builder)
        {
            ensureEnabledFunctionsIsMutable();
            enabledFunctions_.set(i, builder.build());
            return this;
        }

        public RuleEvaluationStepInfo.Builder setEnabledFunctions(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEnabledFunctionsIsMutable();
                enabledFunctions_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public RuleEvaluationStepInfo.Builder setRules(int i, ResolvedRule.Builder builder)
        {
            ensureRulesIsMutable();
            rules_.set(i, builder.build());
            return this;
        }

        public RuleEvaluationStepInfo.Builder setRules(int i, ResolvedRule resolvedrule)
        {
            if(resolvedrule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRulesIsMutable();
                rules_.set(i, resolvedrule);
                return this;
            }
        }

        private int bitField0_;
        private List enabledFunctions_;
        private List rules_;


        private RuleEvaluationStepInfo.Builder()
        {
            rules_ = Collections.emptyList();
            enabledFunctions_ = Collections.emptyList();
            maybeForceBuilderInitialization();
        }
    }

    public static interface RuleEvaluationStepInfoOrBuilder
        extends MessageLiteOrBuilder
    {

        public abstract ResolvedFunctionCall getEnabledFunctions(int i);

        public abstract int getEnabledFunctionsCount();

        public abstract List getEnabledFunctionsList();

        public abstract ResolvedRule getRules(int i);

        public abstract int getRulesCount();

        public abstract List getRulesList();
    }


    private Debug()
    {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionregistrylite)
    {
        extensionregistrylite.add(MacroEvaluationInfo.macro);
    }

}
