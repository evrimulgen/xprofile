// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.analytics.containertag.proto;

import com.google.tagmanager.protobuf.*;
import java.io.IOException;
import java.io.ObjectStreamException;
import java.util.*;

public final class MutableDebug
{
    public static final class DataLayerEventEvaluationInfo extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureResultsInitialized()
        {
            if(results_ == null)
                results_ = new ArrayList();
        }

        private void ensureRulesEvaluationInitialized()
        {
            if(rulesEvaluation_ == RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_ = RuleEvaluationStepInfo.newMessage();
        }

        public static DataLayerEventEvaluationInfo getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
        }

        public static DataLayerEventEvaluationInfo newMessage()
        {
            return new DataLayerEventEvaluationInfo();
        }

        public DataLayerEventEvaluationInfo addAllResults(Iterable iterable)
        {
            assertMutable();
            ensureResultsInitialized();
            AbstractMutableMessageLite.addAll(iterable, results_);
            return this;
        }

        public DataLayerEventEvaluationInfo addResults(ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureResultsInitialized();
                results_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedFunctionCall addResults()
        {
            assertMutable();
            ensureResultsInitialized();
            ResolvedFunctionCall resolvedfunctioncall = ResolvedFunctionCall.newMessage();
            results_.add(resolvedfunctioncall);
            return resolvedfunctioncall;
        }

        public DataLayerEventEvaluationInfo clear()
        {
            assertMutable();
            super.clear();
            if(rulesEvaluation_ != RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_.clear();
            bitField0_ = -2 & bitField0_;
            results_ = null;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public DataLayerEventEvaluationInfo clearResults()
        {
            assertMutable();
            results_ = null;
            return this;
        }

        public DataLayerEventEvaluationInfo clearRulesEvaluation()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            if(rulesEvaluation_ != RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_.clear();
            return this;
        }

        public DataLayerEventEvaluationInfo clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final DataLayerEventEvaluationInfo getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedFunctionCall getMutableResults(int i)
        {
            return (ResolvedFunctionCall)results_.get(i);
        }

        public List getMutableResultsList()
        {
            assertMutable();
            ensureResultsInitialized();
            return results_;
        }

        public RuleEvaluationStepInfo getMutableRulesEvaluation()
        {
            assertMutable();
            ensureRulesEvaluationInitialized();
            bitField0_ = 1 | bitField0_;
            return rulesEvaluation_;
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
            if(results_ == null)
                return 0;
            else
                return results_.size();
        }

        public List getResultsList()
        {
            if(results_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(results_);
        }

        public RuleEvaluationStepInfo getRulesEvaluation()
        {
            return rulesEvaluation_;
        }

        public int getSerializedSize()
        {
            int i = 1 & bitField0_;
            int j = 0;
            if(i == 1)
                j = 0 + CodedOutputStream.computeMessageSize(1, rulesEvaluation_);
            if(results_ != null)
            {
                for(int l = 0; l < results_.size(); l++)
                    j += CodedOutputStream.computeMessageSize(2, (MessageLite)results_.get(l));

            }
            int k = j + unknownFields.size();
            cachedSize = k;
            return k;
        }

        public boolean hasRulesEvaluation()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            int i = 41;
            if(hasRulesEvaluation())
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getRulesEvaluation().hashCode();
            }
            if(getResultsCount() > 0)
                i = 53 * (2 + i * 37) + getResultsList().hashCode();
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Debug$DataLayerEventEvaluationInfo");
            return immutableDefault;
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

        public DataLayerEventEvaluationInfo mergeFrom(DataLayerEventEvaluationInfo datalayereventevaluationinfo)
        {
            if(this == datalayereventevaluationinfo)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(datalayereventevaluationinfo == getDefaultInstance())
                return this;
            if(datalayereventevaluationinfo.hasRulesEvaluation())
            {
                ensureRulesEvaluationInitialized();
                rulesEvaluation_.mergeFrom(datalayereventevaluationinfo.getRulesEvaluation());
                bitField0_ = 1 | bitField0_;
            }
            if(datalayereventevaluationinfo.results_ != null && !datalayereventevaluationinfo.results_.isEmpty())
            {
                ensureResultsInitialized();
                AbstractMutableMessageLite.addAll(datalayereventevaluationinfo.results_, results_);
            }
            unknownFields = unknownFields.concat(datalayereventevaluationinfo.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((DataLayerEventEvaluationInfo)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 3: default 68
        //                       0: 154
        //                       10: 87
        //                       18: 126;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if(rulesEvaluation_ == RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_ = RuleEvaluationStepInfo.newMessage();
            bitField0_ = 1 | bitField0_;
            codedinputstream.readMessage(rulesEvaluation_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L6:
            codedinputstream.readMessage(addResults(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L8; else goto _L7
_L8:
            break MISSING_BLOCK_LABEL_19;
_L7:
        }

        public DataLayerEventEvaluationInfo newMessageForType()
        {
            return new DataLayerEventEvaluationInfo();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public DataLayerEventEvaluationInfo setResults(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureResultsInitialized();
                results_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public DataLayerEventEvaluationInfo setRulesEvaluation(RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            assertMutable();
            if(ruleevaluationstepinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                rulesEvaluation_ = ruleevaluationstepinfo;
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessageWithCachedSizes(1, rulesEvaluation_);
            if(results_ != null)
            {
                for(int k = 0; k < results_.size(); k++)
                    codedoutputstream.writeMessageWithCachedSizes(2, (MutableMessageLite)results_.get(k));

            }
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static Parser PARSER;
        public static final int RESULTS_FIELD_NUMBER = 2;
        public static final int RULES_EVALUATION_FIELD_NUMBER = 1;
        private static final DataLayerEventEvaluationInfo defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private List results_;
        private RuleEvaluationStepInfo rulesEvaluation_;

        static 
        {
            defaultInstance = new DataLayerEventEvaluationInfo(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private DataLayerEventEvaluationInfo()
        {
            results_ = null;
            initFields();
        }

        private DataLayerEventEvaluationInfo(boolean flag)
        {
            results_ = null;
        }
    }

    public static final class DebugEvents extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureEventInitialized()
        {
            if(event_ == null)
                event_ = new ArrayList();
        }

        public static DebugEvents getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
        }

        public static DebugEvents newMessage()
        {
            return new DebugEvents();
        }

        public DebugEvents addAllEvent(Iterable iterable)
        {
            assertMutable();
            ensureEventInitialized();
            AbstractMutableMessageLite.addAll(iterable, event_);
            return this;
        }

        public DebugEvents addEvent(EventInfo eventinfo)
        {
            assertMutable();
            if(eventinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEventInitialized();
                event_.add(eventinfo);
                return this;
            }
        }

        public EventInfo addEvent()
        {
            assertMutable();
            ensureEventInitialized();
            EventInfo eventinfo = EventInfo.newMessage();
            event_.add(eventinfo);
            return eventinfo;
        }

        public DebugEvents clear()
        {
            assertMutable();
            super.clear();
            event_ = null;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public DebugEvents clearEvent()
        {
            assertMutable();
            event_ = null;
            return this;
        }

        public DebugEvents clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final DebugEvents getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
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
            if(event_ == null)
                return 0;
            else
                return event_.size();
        }

        public List getEventList()
        {
            if(event_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(event_);
        }

        public EventInfo getMutableEvent(int i)
        {
            return (EventInfo)event_.get(i);
        }

        public List getMutableEventList()
        {
            assertMutable();
            ensureEventInitialized();
            return event_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            List list = event_;
            int i = 0;
            if(list != null)
            {
                for(int k = 0; k < event_.size(); k++)
                    i += CodedOutputStream.computeMessageSize(1, (MessageLite)event_.get(k));

            }
            int j = i + unknownFields.size();
            cachedSize = j;
            return j;
        }

        public int hashCode()
        {
            int i = 41;
            if(getEventCount() > 0)
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getEventList().hashCode();
            }
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Debug$DebugEvents");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            for(int i = 0; i < getEventCount(); i++)
                if(!getEvent(i).isInitialized())
                    return false;

            return true;
        }

        public DebugEvents mergeFrom(DebugEvents debugevents)
        {
            if(this == debugevents)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(debugevents == getDefaultInstance())
                return this;
            if(debugevents.event_ != null && !debugevents.event_.isEmpty())
            {
                ensureEventInitialized();
                AbstractMutableMessageLite.addAll(debugevents.event_, event_);
            }
            unknownFields = unknownFields.concat(debugevents.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((DebugEvents)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 2: default 60
        //                       0: 107
        //                       10: 79;
               goto _L3 _L4 _L5
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            codedinputstream.readMessage(addEvent(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L7; else goto _L6
_L7:
            break MISSING_BLOCK_LABEL_19;
_L6:
        }

        public DebugEvents newMessageForType()
        {
            return new DebugEvents();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public DebugEvents setEvent(int i, EventInfo eventinfo)
        {
            assertMutable();
            if(eventinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEventInitialized();
                event_.set(i, eventinfo);
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if(event_ != null)
            {
                for(int k = 0; k < event_.size(); k++)
                    codedoutputstream.writeMessageWithCachedSizes(1, (MutableMessageLite)event_.get(k));

            }
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int EVENT_FIELD_NUMBER = 1;
        public static Parser PARSER;
        private static final DebugEvents defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private List event_;

        static 
        {
            defaultInstance = new DebugEvents(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private DebugEvents()
        {
            event_ = null;
            initFields();
        }

        private DebugEvents(boolean flag)
        {
            event_ = null;
        }
    }

    public static final class EventInfo extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureDataLayerEventResultInitialized()
        {
            if(dataLayerEventResult_ == DataLayerEventEvaluationInfo.getDefaultInstance())
                dataLayerEventResult_ = DataLayerEventEvaluationInfo.newMessage();
        }

        private void ensureMacroResultInitialized()
        {
            if(macroResult_ == MacroEvaluationInfo.getDefaultInstance())
                macroResult_ = MacroEvaluationInfo.newMessage();
        }

        public static EventInfo getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            eventType_ = EventType.DATA_LAYER_EVENT;
            macroResult_ = MacroEvaluationInfo.getDefaultInstance();
            dataLayerEventResult_ = DataLayerEventEvaluationInfo.getDefaultInstance();
        }

        public static EventInfo newMessage()
        {
            return new EventInfo();
        }

        public EventInfo clear()
        {
            assertMutable();
            super.clear();
            eventType_ = EventType.DATA_LAYER_EVENT;
            bitField0_ = -2 & bitField0_;
            containerVersion_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -3 & bitField0_;
            containerId_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -5 & bitField0_;
            key_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -9 & bitField0_;
            if(macroResult_ != MacroEvaluationInfo.getDefaultInstance())
                macroResult_.clear();
            bitField0_ = 0xffffffef & bitField0_;
            if(dataLayerEventResult_ != DataLayerEventEvaluationInfo.getDefaultInstance())
                dataLayerEventResult_.clear();
            bitField0_ = 0xffffffdf & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public EventInfo clearContainerId()
        {
            assertMutable();
            bitField0_ = -5 & bitField0_;
            containerId_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public EventInfo clearContainerVersion()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            containerVersion_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public EventInfo clearDataLayerEventResult()
        {
            assertMutable();
            bitField0_ = 0xffffffdf & bitField0_;
            if(dataLayerEventResult_ != DataLayerEventEvaluationInfo.getDefaultInstance())
                dataLayerEventResult_.clear();
            return this;
        }

        public EventInfo clearEventType()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            eventType_ = EventType.DATA_LAYER_EVENT;
            return this;
        }

        public EventInfo clearKey()
        {
            assertMutable();
            bitField0_ = -9 & bitField0_;
            key_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public EventInfo clearMacroResult()
        {
            assertMutable();
            bitField0_ = 0xffffffef & bitField0_;
            if(macroResult_ != MacroEvaluationInfo.getDefaultInstance())
                macroResult_.clear();
            return this;
        }

        public EventInfo clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                containerId_ = s;
            return s;
        }

        public byte[] getContainerIdAsBytes()
        {
            Object obj = containerId_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                containerId_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
            }
        }

        public String getContainerVersion()
        {
            Object obj = containerVersion_;
            if(obj instanceof String)
                return (String)obj;
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                containerVersion_ = s;
            return s;
        }

        public byte[] getContainerVersionAsBytes()
        {
            Object obj = containerVersion_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                containerVersion_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
            }
        }

        public DataLayerEventEvaluationInfo getDataLayerEventResult()
        {
            return dataLayerEventResult_;
        }

        public final EventInfo getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
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
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                key_ = s;
            return s;
        }

        public byte[] getKeyAsBytes()
        {
            Object obj = key_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                key_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
            }
        }

        public MacroEvaluationInfo getMacroResult()
        {
            return macroResult_;
        }

        public DataLayerEventEvaluationInfo getMutableDataLayerEventResult()
        {
            assertMutable();
            ensureDataLayerEventResultInitialized();
            bitField0_ = 0x20 | bitField0_;
            return dataLayerEventResult_;
        }

        public MacroEvaluationInfo getMutableMacroResult()
        {
            assertMutable();
            ensureMacroResultInitialized();
            bitField0_ = 0x10 | bitField0_;
            return macroResult_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            int i = 1 & bitField0_;
            int j = 0;
            if(i == 1)
                j = 0 + CodedOutputStream.computeEnumSize(1, eventType_.getNumber());
            if((2 & bitField0_) == 2)
                j += CodedOutputStream.computeByteArraySize(2, getContainerVersionAsBytes());
            if((4 & bitField0_) == 4)
                j += CodedOutputStream.computeByteArraySize(3, getContainerIdAsBytes());
            if((8 & bitField0_) == 8)
                j += CodedOutputStream.computeByteArraySize(4, getKeyAsBytes());
            if((0x10 & bitField0_) == 16)
                j += CodedOutputStream.computeMessageSize(6, macroResult_);
            if((0x20 & bitField0_) == 32)
                j += CodedOutputStream.computeMessageSize(7, dataLayerEventResult_);
            int k = j + unknownFields.size();
            cachedSize = k;
            return k;
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
            int i = 41;
            if(hasEventType())
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + Internal.hashEnum(getEventType());
            }
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
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Debug$EventInfo");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            while(hasMacroResult() && !getMacroResult().isInitialized() || hasDataLayerEventResult() && !getDataLayerEventResult().isInitialized()) 
                return false;
            return true;
        }

        public EventInfo mergeFrom(EventInfo eventinfo)
        {
            if(this == eventinfo)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(eventinfo == getDefaultInstance())
                return this;
            if(eventinfo.hasEventType())
                setEventType(eventinfo.getEventType());
            if(eventinfo.hasContainerVersion())
            {
                bitField0_ = 2 | bitField0_;
                if(eventinfo.containerVersion_ instanceof String)
                {
                    containerVersion_ = eventinfo.containerVersion_;
                } else
                {
                    byte abyte2[] = (byte[])(byte[])eventinfo.containerVersion_;
                    containerVersion_ = Arrays.copyOf(abyte2, abyte2.length);
                }
            }
            if(eventinfo.hasContainerId())
            {
                bitField0_ = 4 | bitField0_;
                if(eventinfo.containerId_ instanceof String)
                {
                    containerId_ = eventinfo.containerId_;
                } else
                {
                    byte abyte1[] = (byte[])(byte[])eventinfo.containerId_;
                    containerId_ = Arrays.copyOf(abyte1, abyte1.length);
                }
            }
            if(eventinfo.hasKey())
            {
                bitField0_ = 8 | bitField0_;
                if(eventinfo.key_ instanceof String)
                {
                    key_ = eventinfo.key_;
                } else
                {
                    byte abyte0[] = (byte[])(byte[])eventinfo.key_;
                    key_ = Arrays.copyOf(abyte0, abyte0.length);
                }
            }
            if(eventinfo.hasMacroResult())
            {
                ensureMacroResultInitialized();
                macroResult_.mergeFrom(eventinfo.getMacroResult());
                bitField0_ = 0x10 | bitField0_;
            }
            if(eventinfo.hasDataLayerEventResult())
            {
                ensureDataLayerEventResultInitialized();
                dataLayerEventResult_.mergeFrom(eventinfo.getDataLayerEventResult());
                bitField0_ = 0x20 | bitField0_;
            }
            unknownFields = unknownFields.concat(eventinfo.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((EventInfo)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            int j;
            EventType eventtype;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 7: default 100
        //                       0: 333
        //                       8: 119
        //                       18: 173
        //                       26: 194
        //                       34: 215
        //                       50: 237
        //                       58: 277;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            j = codedinputstream.readEnum();
            eventtype = EventType.valueOf(j);
            if(eventtype != null) goto _L12; else goto _L11
_L11:
            codedoutputstream.writeRawVarint32(i);
            codedoutputstream.writeRawVarint32(j);
            continue; /* Loop/switch isn't completed */
_L12:
            bitField0_ = 1 | bitField0_;
            eventType_ = eventtype;
            continue; /* Loop/switch isn't completed */
_L6:
            bitField0_ = 2 | bitField0_;
            containerVersion_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L7:
            bitField0_ = 4 | bitField0_;
            containerId_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L8:
            bitField0_ = 8 | bitField0_;
            key_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L9:
            if(macroResult_ == MacroEvaluationInfo.getDefaultInstance())
                macroResult_ = MacroEvaluationInfo.newMessage();
            bitField0_ = 0x10 | bitField0_;
            codedinputstream.readMessage(macroResult_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L10:
            if(dataLayerEventResult_ == DataLayerEventEvaluationInfo.getDefaultInstance())
                dataLayerEventResult_ = DataLayerEventEvaluationInfo.newMessage();
            bitField0_ = 0x20 | bitField0_;
            codedinputstream.readMessage(dataLayerEventResult_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L14; else goto _L13
_L14:
            break MISSING_BLOCK_LABEL_19;
_L13:
        }

        public EventInfo newMessageForType()
        {
            return new EventInfo();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public EventInfo setContainerId(String s)
        {
            assertMutable();
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

        public EventInfo setContainerIdAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 4 | bitField0_;
                containerId_ = abyte0;
                return this;
            }
        }

        public EventInfo setContainerVersion(String s)
        {
            assertMutable();
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

        public EventInfo setContainerVersionAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                containerVersion_ = abyte0;
                return this;
            }
        }

        public EventInfo setDataLayerEventResult(DataLayerEventEvaluationInfo datalayereventevaluationinfo)
        {
            assertMutable();
            if(datalayereventevaluationinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x20 | bitField0_;
                dataLayerEventResult_ = datalayereventevaluationinfo;
                return this;
            }
        }

        public EventInfo setEventType(EventType eventtype)
        {
            assertMutable();
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

        public EventInfo setKey(String s)
        {
            assertMutable();
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

        public EventInfo setKeyAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 8 | bitField0_;
                key_ = abyte0;
                return this;
            }
        }

        public EventInfo setMacroResult(MacroEvaluationInfo macroevaluationinfo)
        {
            assertMutable();
            if(macroevaluationinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 0x10 | bitField0_;
                macroResult_ = macroevaluationinfo;
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeEnum(1, eventType_.getNumber());
            if((2 & bitField0_) == 2)
                codedoutputstream.writeByteArray(2, getContainerVersionAsBytes());
            if((4 & bitField0_) == 4)
                codedoutputstream.writeByteArray(3, getContainerIdAsBytes());
            if((8 & bitField0_) == 8)
                codedoutputstream.writeByteArray(4, getKeyAsBytes());
            if((0x10 & bitField0_) == 16)
                codedoutputstream.writeMessageWithCachedSizes(6, macroResult_);
            if((0x20 & bitField0_) == 32)
                codedoutputstream.writeMessageWithCachedSizes(7, dataLayerEventResult_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int CONTAINER_ID_FIELD_NUMBER = 3;
        public static final int CONTAINER_VERSION_FIELD_NUMBER = 2;
        public static final int DATA_LAYER_EVENT_RESULT_FIELD_NUMBER = 7;
        public static final int EVENT_TYPE_FIELD_NUMBER = 1;
        public static final int KEY_FIELD_NUMBER = 4;
        public static final int MACRO_RESULT_FIELD_NUMBER = 6;
        public static Parser PARSER;
        private static final EventInfo defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private Object containerId_;
        private Object containerVersion_;
        private DataLayerEventEvaluationInfo dataLayerEventResult_;
        private EventType eventType_;
        private Object key_;
        private MacroEvaluationInfo macroResult_;

        static 
        {
            defaultInstance = new EventInfo(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private EventInfo()
        {
            eventType_ = EventType.DATA_LAYER_EVENT;
            containerVersion_ = Internal.EMPTY_BYTE_ARRAY;
            containerId_ = Internal.EMPTY_BYTE_ARRAY;
            key_ = Internal.EMPTY_BYTE_ARRAY;
            initFields();
        }

        private EventInfo(boolean flag)
        {
            eventType_ = EventType.DATA_LAYER_EVENT;
            containerVersion_ = Internal.EMPTY_BYTE_ARRAY;
            containerId_ = Internal.EMPTY_BYTE_ARRAY;
            key_ = Internal.EMPTY_BYTE_ARRAY;
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
            return (EventInfo.EventType)Enum.valueOf(com/google/analytics/containertag/proto/MutableDebug$EventInfo$EventType, s);
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

    public static final class MacroEvaluationInfo extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureResultInitialized()
        {
            if(result_ == ResolvedFunctionCall.getDefaultInstance())
                result_ = ResolvedFunctionCall.newMessage();
        }

        private void ensureRulesEvaluationInitialized()
        {
            if(rulesEvaluation_ == RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_ = RuleEvaluationStepInfo.newMessage();
        }

        public static MacroEvaluationInfo getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            rulesEvaluation_ = RuleEvaluationStepInfo.getDefaultInstance();
            result_ = ResolvedFunctionCall.getDefaultInstance();
        }

        public static MacroEvaluationInfo newMessage()
        {
            return new MacroEvaluationInfo();
        }

        public MacroEvaluationInfo clear()
        {
            assertMutable();
            super.clear();
            if(rulesEvaluation_ != RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_.clear();
            bitField0_ = -2 & bitField0_;
            if(result_ != ResolvedFunctionCall.getDefaultInstance())
                result_.clear();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public MacroEvaluationInfo clearResult()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            if(result_ != ResolvedFunctionCall.getDefaultInstance())
                result_.clear();
            return this;
        }

        public MacroEvaluationInfo clearRulesEvaluation()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            if(rulesEvaluation_ != RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_.clear();
            return this;
        }

        public MacroEvaluationInfo clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final MacroEvaluationInfo getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedFunctionCall getMutableResult()
        {
            assertMutable();
            ensureResultInitialized();
            bitField0_ = 2 | bitField0_;
            return result_;
        }

        public RuleEvaluationStepInfo getMutableRulesEvaluation()
        {
            assertMutable();
            ensureRulesEvaluationInitialized();
            bitField0_ = 1 | bitField0_;
            return rulesEvaluation_;
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
            int i = 1 & bitField0_;
            int j = 0;
            if(i == 1)
                j = 0 + CodedOutputStream.computeMessageSize(1, rulesEvaluation_);
            if((2 & bitField0_) == 2)
                j += CodedOutputStream.computeMessageSize(3, result_);
            int k = j + unknownFields.size();
            cachedSize = k;
            return k;
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
            int i = 41;
            if(hasRulesEvaluation())
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getRulesEvaluation().hashCode();
            }
            if(hasResult())
                i = 53 * (3 + i * 37) + getResult().hashCode();
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Debug$MacroEvaluationInfo");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            while(hasRulesEvaluation() && !getRulesEvaluation().isInitialized() || hasResult() && !getResult().isInitialized()) 
                return false;
            return true;
        }

        public MacroEvaluationInfo mergeFrom(MacroEvaluationInfo macroevaluationinfo)
        {
            if(this == macroevaluationinfo)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(macroevaluationinfo == getDefaultInstance())
                return this;
            if(macroevaluationinfo.hasRulesEvaluation())
            {
                ensureRulesEvaluationInitialized();
                rulesEvaluation_.mergeFrom(macroevaluationinfo.getRulesEvaluation());
                bitField0_ = 1 | bitField0_;
            }
            if(macroevaluationinfo.hasResult())
            {
                ensureResultInitialized();
                result_.mergeFrom(macroevaluationinfo.getResult());
                bitField0_ = 2 | bitField0_;
            }
            unknownFields = unknownFields.concat(macroevaluationinfo.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((MacroEvaluationInfo)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 3: default 68
        //                       0: 181
        //                       10: 87
        //                       26: 126;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            if(rulesEvaluation_ == RuleEvaluationStepInfo.getDefaultInstance())
                rulesEvaluation_ = RuleEvaluationStepInfo.newMessage();
            bitField0_ = 1 | bitField0_;
            codedinputstream.readMessage(rulesEvaluation_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L6:
            if(result_ == ResolvedFunctionCall.getDefaultInstance())
                result_ = ResolvedFunctionCall.newMessage();
            bitField0_ = 2 | bitField0_;
            codedinputstream.readMessage(result_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L8; else goto _L7
_L8:
            break MISSING_BLOCK_LABEL_19;
_L7:
        }

        public MacroEvaluationInfo newMessageForType()
        {
            return new MacroEvaluationInfo();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public MacroEvaluationInfo setResult(ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                result_ = resolvedfunctioncall;
                return this;
            }
        }

        public MacroEvaluationInfo setRulesEvaluation(RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            assertMutable();
            if(ruleevaluationstepinfo == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                rulesEvaluation_ = ruleevaluationstepinfo;
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessageWithCachedSizes(1, rulesEvaluation_);
            if((2 & bitField0_) == 2)
                codedoutputstream.writeMessageWithCachedSizes(3, result_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int MACRO_FIELD_NUMBER = 0x2d4c0bd;
        public static Parser PARSER;
        public static final int RESULT_FIELD_NUMBER = 3;
        public static final int RULES_EVALUATION_FIELD_NUMBER = 1;
        private static final MacroEvaluationInfo defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        public static final com.google.tagmanager.protobuf.GeneratedMessageLite.GeneratedExtension macro;
        private static final long serialVersionUID;
        private int bitField0_;
        private ResolvedFunctionCall result_;
        private RuleEvaluationStepInfo rulesEvaluation_;

        static 
        {
            defaultInstance = new MacroEvaluationInfo(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
            macro = GeneratedMessageLite.newSingularGeneratedExtension(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance(), getDefaultInstance(), getDefaultInstance(), null, 0x2d4c0bd, com.google.tagmanager.protobuf.WireFormat.FieldType.MESSAGE, com/google/analytics/containertag/proto/MutableDebug$MacroEvaluationInfo);
        }

        private MacroEvaluationInfo()
        {
            initFields();
        }

        private MacroEvaluationInfo(boolean flag)
        {
        }
    }

    public static final class ResolvedFunctionCall extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensurePropertiesInitialized()
        {
            if(properties_ == null)
                properties_ = new ArrayList();
        }

        private void ensureResultInitialized()
        {
            if(result_ == com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                result_ = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.newMessage();
        }

        public static ResolvedFunctionCall getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            result_ = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance();
        }

        public static ResolvedFunctionCall newMessage()
        {
            return new ResolvedFunctionCall();
        }

        public ResolvedFunctionCall addAllProperties(Iterable iterable)
        {
            assertMutable();
            ensurePropertiesInitialized();
            AbstractMutableMessageLite.addAll(iterable, properties_);
            return this;
        }

        public ResolvedFunctionCall addProperties(ResolvedProperty resolvedproperty)
        {
            assertMutable();
            if(resolvedproperty == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertiesInitialized();
                properties_.add(resolvedproperty);
                return this;
            }
        }

        public ResolvedProperty addProperties()
        {
            assertMutable();
            ensurePropertiesInitialized();
            ResolvedProperty resolvedproperty = ResolvedProperty.newMessage();
            properties_.add(resolvedproperty);
            return resolvedproperty;
        }

        public ResolvedFunctionCall clear()
        {
            assertMutable();
            super.clear();
            properties_ = null;
            if(result_ != com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                result_.clear();
            bitField0_ = -2 & bitField0_;
            associatedRuleName_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public ResolvedFunctionCall clearAssociatedRuleName()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            associatedRuleName_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public ResolvedFunctionCall clearProperties()
        {
            assertMutable();
            properties_ = null;
            return this;
        }

        public ResolvedFunctionCall clearResult()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            if(result_ != com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                result_.clear();
            return this;
        }

        public ResolvedFunctionCall clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                associatedRuleName_ = s;
            return s;
        }

        public byte[] getAssociatedRuleNameAsBytes()
        {
            Object obj = associatedRuleName_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                associatedRuleName_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
            }
        }

        public final ResolvedFunctionCall getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedProperty getMutableProperties(int i)
        {
            return (ResolvedProperty)properties_.get(i);
        }

        public List getMutablePropertiesList()
        {
            assertMutable();
            ensurePropertiesInitialized();
            return properties_;
        }

        public com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value getMutableResult()
        {
            assertMutable();
            ensureResultInitialized();
            bitField0_ = 1 | bitField0_;
            return result_;
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
            if(properties_ == null)
                return 0;
            else
                return properties_.size();
        }

        public List getPropertiesList()
        {
            if(properties_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(properties_);
        }

        public com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value getResult()
        {
            return result_;
        }

        public int getSerializedSize()
        {
            List list = properties_;
            int i = 0;
            if(list != null)
            {
                for(int k = 0; k < properties_.size(); k++)
                    i += CodedOutputStream.computeMessageSize(1, (MessageLite)properties_.get(k));

            }
            if((1 & bitField0_) == 1)
                i += CodedOutputStream.computeMessageSize(2, result_);
            if((2 & bitField0_) == 2)
                i += CodedOutputStream.computeByteArraySize(3, getAssociatedRuleNameAsBytes());
            int j = i + unknownFields.size();
            cachedSize = j;
            return j;
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
            int i = 41;
            if(getPropertiesCount() > 0)
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getPropertiesList().hashCode();
            }
            if(hasResult())
                i = 53 * (2 + i * 37) + getResult().hashCode();
            if(hasAssociatedRuleName())
                i = 53 * (3 + i * 37) + getAssociatedRuleName().hashCode();
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Debug$ResolvedFunctionCall");
            return immutableDefault;
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

        public ResolvedFunctionCall mergeFrom(ResolvedFunctionCall resolvedfunctioncall)
        {
            if(this == resolvedfunctioncall)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(resolvedfunctioncall == getDefaultInstance())
                return this;
            if(resolvedfunctioncall.properties_ != null && !resolvedfunctioncall.properties_.isEmpty())
            {
                ensurePropertiesInitialized();
                AbstractMutableMessageLite.addAll(resolvedfunctioncall.properties_, properties_);
            }
            if(resolvedfunctioncall.hasResult())
            {
                ensureResultInitialized();
                result_.mergeFrom(resolvedfunctioncall.getResult());
                bitField0_ = 1 | bitField0_;
            }
            if(resolvedfunctioncall.hasAssociatedRuleName())
            {
                bitField0_ = 2 | bitField0_;
                if(resolvedfunctioncall.associatedRuleName_ instanceof String)
                {
                    associatedRuleName_ = resolvedfunctioncall.associatedRuleName_;
                } else
                {
                    byte abyte0[] = (byte[])(byte[])resolvedfunctioncall.associatedRuleName_;
                    associatedRuleName_ = Arrays.copyOf(abyte0, abyte0.length);
                }
            }
            unknownFields = unknownFields.concat(resolvedfunctioncall.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((ResolvedFunctionCall)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 4: default 76
        //                       0: 183
        //                       10: 95
        //                       18: 107
        //                       26: 146;
               goto _L3 _L4 _L5 _L6 _L7
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            codedinputstream.readMessage(addProperties(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L6:
            if(result_ == com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                result_ = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.newMessage();
            bitField0_ = 1 | bitField0_;
            codedinputstream.readMessage(result_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L7:
            bitField0_ = 2 | bitField0_;
            associatedRuleName_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L9; else goto _L8
_L9:
            break MISSING_BLOCK_LABEL_19;
_L8:
        }

        public ResolvedFunctionCall newMessageForType()
        {
            return new ResolvedFunctionCall();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public ResolvedFunctionCall setAssociatedRuleName(String s)
        {
            assertMutable();
            if(s == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                associatedRuleName_ = s;
                return this;
            }
        }

        public ResolvedFunctionCall setAssociatedRuleNameAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                associatedRuleName_ = abyte0;
                return this;
            }
        }

        public ResolvedFunctionCall setProperties(int i, ResolvedProperty resolvedproperty)
        {
            assertMutable();
            if(resolvedproperty == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePropertiesInitialized();
                properties_.set(i, resolvedproperty);
                return this;
            }
        }

        public ResolvedFunctionCall setResult(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value)
        {
            assertMutable();
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                result_ = value;
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if(properties_ != null)
            {
                for(int k = 0; k < properties_.size(); k++)
                    codedoutputstream.writeMessageWithCachedSizes(1, (MutableMessageLite)properties_.get(k));

            }
            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessageWithCachedSizes(2, result_);
            if((2 & bitField0_) == 2)
                codedoutputstream.writeByteArray(3, getAssociatedRuleNameAsBytes());
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int ASSOCIATED_RULE_NAME_FIELD_NUMBER = 3;
        public static Parser PARSER;
        public static final int PROPERTIES_FIELD_NUMBER = 1;
        public static final int RESULT_FIELD_NUMBER = 2;
        private static final ResolvedFunctionCall defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private Object associatedRuleName_;
        private int bitField0_;
        private List properties_;
        private com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value result_;

        static 
        {
            defaultInstance = new ResolvedFunctionCall(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private ResolvedFunctionCall()
        {
            properties_ = null;
            associatedRuleName_ = Internal.EMPTY_BYTE_ARRAY;
            initFields();
        }

        private ResolvedFunctionCall(boolean flag)
        {
            properties_ = null;
            associatedRuleName_ = Internal.EMPTY_BYTE_ARRAY;
        }
    }

    public static final class ResolvedProperty extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureValueInitialized()
        {
            if(value_ == com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                value_ = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.newMessage();
        }

        public static ResolvedProperty getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            value_ = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance();
        }

        public static ResolvedProperty newMessage()
        {
            return new ResolvedProperty();
        }

        public ResolvedProperty clear()
        {
            assertMutable();
            super.clear();
            key_ = Internal.EMPTY_BYTE_ARRAY;
            bitField0_ = -2 & bitField0_;
            if(value_ != com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                value_.clear();
            bitField0_ = -3 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public ResolvedProperty clearKey()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            key_ = Internal.EMPTY_BYTE_ARRAY;
            return this;
        }

        public ResolvedProperty clearValue()
        {
            assertMutable();
            bitField0_ = -3 & bitField0_;
            if(value_ != com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                value_.clear();
            return this;
        }

        public ResolvedProperty clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final ResolvedProperty getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
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
            if(obj instanceof String)
                return (String)obj;
            byte abyte0[] = (byte[])(byte[])obj;
            String s = Internal.toStringUtf8(abyte0);
            if(Internal.isValidUtf8(abyte0))
                key_ = s;
            return s;
        }

        public byte[] getKeyAsBytes()
        {
            Object obj = key_;
            if(obj instanceof String)
            {
                byte abyte0[] = Internal.toByteArray((String)obj);
                key_ = abyte0;
                return abyte0;
            } else
            {
                return (byte[])(byte[])obj;
            }
        }

        public com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value getMutableValue()
        {
            assertMutable();
            ensureValueInitialized();
            bitField0_ = 2 | bitField0_;
            return value_;
        }

        public Parser getParserForType()
        {
            return PARSER;
        }

        public int getSerializedSize()
        {
            int i = 1 & bitField0_;
            int j = 0;
            if(i == 1)
                j = 0 + CodedOutputStream.computeByteArraySize(1, getKeyAsBytes());
            if((2 & bitField0_) == 2)
                j += CodedOutputStream.computeMessageSize(2, value_);
            int k = j + unknownFields.size();
            cachedSize = k;
            return k;
        }

        public com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value getValue()
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
            int i = 41;
            if(hasKey())
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getKey().hashCode();
            }
            if(hasValue())
                i = 53 * (2 + i * 37) + getValue().hashCode();
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Debug$ResolvedProperty");
            return immutableDefault;
        }

        public final boolean isInitialized()
        {
            return !hasValue() || getValue().isInitialized();
        }

        public ResolvedProperty mergeFrom(ResolvedProperty resolvedproperty)
        {
            if(this == resolvedproperty)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(resolvedproperty == getDefaultInstance())
                return this;
            if(resolvedproperty.hasKey())
            {
                bitField0_ = 1 | bitField0_;
                if(resolvedproperty.key_ instanceof String)
                {
                    key_ = resolvedproperty.key_;
                } else
                {
                    byte abyte0[] = (byte[])(byte[])resolvedproperty.key_;
                    key_ = Arrays.copyOf(abyte0, abyte0.length);
                }
            }
            if(resolvedproperty.hasValue())
            {
                ensureValueInitialized();
                value_.mergeFrom(resolvedproperty.getValue());
                bitField0_ = 2 | bitField0_;
            }
            unknownFields = unknownFields.concat(resolvedproperty.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((ResolvedProperty)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 3: default 68
        //                       0: 163
        //                       10: 87
        //                       18: 108;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            bitField0_ = 1 | bitField0_;
            key_ = codedinputstream.readByteArray();
            continue; /* Loop/switch isn't completed */
_L6:
            if(value_ == com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                value_ = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.newMessage();
            bitField0_ = 2 | bitField0_;
            codedinputstream.readMessage(value_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L8; else goto _L7
_L8:
            break MISSING_BLOCK_LABEL_19;
_L7:
        }

        public ResolvedProperty newMessageForType()
        {
            return new ResolvedProperty();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public ResolvedProperty setKey(String s)
        {
            assertMutable();
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

        public ResolvedProperty setKeyAsBytes(byte abyte0[])
        {
            assertMutable();
            if(abyte0 == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                key_ = abyte0;
                return this;
            }
        }

        public ResolvedProperty setValue(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value)
        {
            assertMutable();
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 2 | bitField0_;
                value_ = value;
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if((1 & bitField0_) == 1)
                codedoutputstream.writeByteArray(1, getKeyAsBytes());
            if((2 & bitField0_) == 2)
                codedoutputstream.writeMessageWithCachedSizes(2, value_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int KEY_FIELD_NUMBER = 1;
        public static Parser PARSER;
        public static final int VALUE_FIELD_NUMBER = 2;
        private static final ResolvedProperty defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private int bitField0_;
        private Object key_;
        private com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value_;

        static 
        {
            defaultInstance = new ResolvedProperty(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private ResolvedProperty()
        {
            key_ = Internal.EMPTY_BYTE_ARRAY;
            initFields();
        }

        private ResolvedProperty(boolean flag)
        {
            key_ = Internal.EMPTY_BYTE_ARRAY;
        }
    }

    public static final class ResolvedRule extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureAddMacrosInitialized()
        {
            if(addMacros_ == null)
                addMacros_ = new ArrayList();
        }

        private void ensureAddTagsInitialized()
        {
            if(addTags_ == null)
                addTags_ = new ArrayList();
        }

        private void ensureNegativePredicatesInitialized()
        {
            if(negativePredicates_ == null)
                negativePredicates_ = new ArrayList();
        }

        private void ensurePositivePredicatesInitialized()
        {
            if(positivePredicates_ == null)
                positivePredicates_ = new ArrayList();
        }

        private void ensureRemoveMacrosInitialized()
        {
            if(removeMacros_ == null)
                removeMacros_ = new ArrayList();
        }

        private void ensureRemoveTagsInitialized()
        {
            if(removeTags_ == null)
                removeTags_ = new ArrayList();
        }

        private void ensureResultInitialized()
        {
            if(result_ == com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                result_ = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.newMessage();
        }

        public static ResolvedRule getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
            result_ = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance();
        }

        public static ResolvedRule newMessage()
        {
            return new ResolvedRule();
        }

        public ResolvedFunctionCall addAddMacros()
        {
            assertMutable();
            ensureAddMacrosInitialized();
            ResolvedFunctionCall resolvedfunctioncall = ResolvedFunctionCall.newMessage();
            addMacros_.add(resolvedfunctioncall);
            return resolvedfunctioncall;
        }

        public ResolvedRule addAddMacros(ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddMacrosInitialized();
                addMacros_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedFunctionCall addAddTags()
        {
            assertMutable();
            ensureAddTagsInitialized();
            ResolvedFunctionCall resolvedfunctioncall = ResolvedFunctionCall.newMessage();
            addTags_.add(resolvedfunctioncall);
            return resolvedfunctioncall;
        }

        public ResolvedRule addAddTags(ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddTagsInitialized();
                addTags_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule addAllAddMacros(Iterable iterable)
        {
            assertMutable();
            ensureAddMacrosInitialized();
            AbstractMutableMessageLite.addAll(iterable, addMacros_);
            return this;
        }

        public ResolvedRule addAllAddTags(Iterable iterable)
        {
            assertMutable();
            ensureAddTagsInitialized();
            AbstractMutableMessageLite.addAll(iterable, addTags_);
            return this;
        }

        public ResolvedRule addAllNegativePredicates(Iterable iterable)
        {
            assertMutable();
            ensureNegativePredicatesInitialized();
            AbstractMutableMessageLite.addAll(iterable, negativePredicates_);
            return this;
        }

        public ResolvedRule addAllPositivePredicates(Iterable iterable)
        {
            assertMutable();
            ensurePositivePredicatesInitialized();
            AbstractMutableMessageLite.addAll(iterable, positivePredicates_);
            return this;
        }

        public ResolvedRule addAllRemoveMacros(Iterable iterable)
        {
            assertMutable();
            ensureRemoveMacrosInitialized();
            AbstractMutableMessageLite.addAll(iterable, removeMacros_);
            return this;
        }

        public ResolvedRule addAllRemoveTags(Iterable iterable)
        {
            assertMutable();
            ensureRemoveTagsInitialized();
            AbstractMutableMessageLite.addAll(iterable, removeTags_);
            return this;
        }

        public ResolvedFunctionCall addNegativePredicates()
        {
            assertMutable();
            ensureNegativePredicatesInitialized();
            ResolvedFunctionCall resolvedfunctioncall = ResolvedFunctionCall.newMessage();
            negativePredicates_.add(resolvedfunctioncall);
            return resolvedfunctioncall;
        }

        public ResolvedRule addNegativePredicates(ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureNegativePredicatesInitialized();
                negativePredicates_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedFunctionCall addPositivePredicates()
        {
            assertMutable();
            ensurePositivePredicatesInitialized();
            ResolvedFunctionCall resolvedfunctioncall = ResolvedFunctionCall.newMessage();
            positivePredicates_.add(resolvedfunctioncall);
            return resolvedfunctioncall;
        }

        public ResolvedRule addPositivePredicates(ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePositivePredicatesInitialized();
                positivePredicates_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedFunctionCall addRemoveMacros()
        {
            assertMutable();
            ensureRemoveMacrosInitialized();
            ResolvedFunctionCall resolvedfunctioncall = ResolvedFunctionCall.newMessage();
            removeMacros_.add(resolvedfunctioncall);
            return resolvedfunctioncall;
        }

        public ResolvedRule addRemoveMacros(ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveMacrosInitialized();
                removeMacros_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedFunctionCall addRemoveTags()
        {
            assertMutable();
            ensureRemoveTagsInitialized();
            ResolvedFunctionCall resolvedfunctioncall = ResolvedFunctionCall.newMessage();
            removeTags_.add(resolvedfunctioncall);
            return resolvedfunctioncall;
        }

        public ResolvedRule addRemoveTags(ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveTagsInitialized();
                removeTags_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule clear()
        {
            assertMutable();
            super.clear();
            positivePredicates_ = null;
            negativePredicates_ = null;
            addTags_ = null;
            removeTags_ = null;
            addMacros_ = null;
            removeMacros_ = null;
            if(result_ != com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                result_.clear();
            bitField0_ = -2 & bitField0_;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public ResolvedRule clearAddMacros()
        {
            assertMutable();
            addMacros_ = null;
            return this;
        }

        public ResolvedRule clearAddTags()
        {
            assertMutable();
            addTags_ = null;
            return this;
        }

        public ResolvedRule clearNegativePredicates()
        {
            assertMutable();
            negativePredicates_ = null;
            return this;
        }

        public ResolvedRule clearPositivePredicates()
        {
            assertMutable();
            positivePredicates_ = null;
            return this;
        }

        public ResolvedRule clearRemoveMacros()
        {
            assertMutable();
            removeMacros_ = null;
            return this;
        }

        public ResolvedRule clearRemoveTags()
        {
            assertMutable();
            removeTags_ = null;
            return this;
        }

        public ResolvedRule clearResult()
        {
            assertMutable();
            bitField0_ = -2 & bitField0_;
            if(result_ != com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                result_.clear();
            return this;
        }

        public ResolvedRule clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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
            if(addMacros_ == null)
                return 0;
            else
                return addMacros_.size();
        }

        public List getAddMacrosList()
        {
            if(addMacros_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(addMacros_);
        }

        public ResolvedFunctionCall getAddTags(int i)
        {
            return (ResolvedFunctionCall)addTags_.get(i);
        }

        public int getAddTagsCount()
        {
            if(addTags_ == null)
                return 0;
            else
                return addTags_.size();
        }

        public List getAddTagsList()
        {
            if(addTags_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(addTags_);
        }

        public final ResolvedRule getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public volatile MessageLite getDefaultInstanceForType()
        {
            return getDefaultInstanceForType();
        }

        public ResolvedFunctionCall getMutableAddMacros(int i)
        {
            return (ResolvedFunctionCall)addMacros_.get(i);
        }

        public List getMutableAddMacrosList()
        {
            assertMutable();
            ensureAddMacrosInitialized();
            return addMacros_;
        }

        public ResolvedFunctionCall getMutableAddTags(int i)
        {
            return (ResolvedFunctionCall)addTags_.get(i);
        }

        public List getMutableAddTagsList()
        {
            assertMutable();
            ensureAddTagsInitialized();
            return addTags_;
        }

        public ResolvedFunctionCall getMutableNegativePredicates(int i)
        {
            return (ResolvedFunctionCall)negativePredicates_.get(i);
        }

        public List getMutableNegativePredicatesList()
        {
            assertMutable();
            ensureNegativePredicatesInitialized();
            return negativePredicates_;
        }

        public ResolvedFunctionCall getMutablePositivePredicates(int i)
        {
            return (ResolvedFunctionCall)positivePredicates_.get(i);
        }

        public List getMutablePositivePredicatesList()
        {
            assertMutable();
            ensurePositivePredicatesInitialized();
            return positivePredicates_;
        }

        public ResolvedFunctionCall getMutableRemoveMacros(int i)
        {
            return (ResolvedFunctionCall)removeMacros_.get(i);
        }

        public List getMutableRemoveMacrosList()
        {
            assertMutable();
            ensureRemoveMacrosInitialized();
            return removeMacros_;
        }

        public ResolvedFunctionCall getMutableRemoveTags(int i)
        {
            return (ResolvedFunctionCall)removeTags_.get(i);
        }

        public List getMutableRemoveTagsList()
        {
            assertMutable();
            ensureRemoveTagsInitialized();
            return removeTags_;
        }

        public com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value getMutableResult()
        {
            assertMutable();
            ensureResultInitialized();
            bitField0_ = 1 | bitField0_;
            return result_;
        }

        public ResolvedFunctionCall getNegativePredicates(int i)
        {
            return (ResolvedFunctionCall)negativePredicates_.get(i);
        }

        public int getNegativePredicatesCount()
        {
            if(negativePredicates_ == null)
                return 0;
            else
                return negativePredicates_.size();
        }

        public List getNegativePredicatesList()
        {
            if(negativePredicates_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(negativePredicates_);
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
            if(positivePredicates_ == null)
                return 0;
            else
                return positivePredicates_.size();
        }

        public List getPositivePredicatesList()
        {
            if(positivePredicates_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(positivePredicates_);
        }

        public ResolvedFunctionCall getRemoveMacros(int i)
        {
            return (ResolvedFunctionCall)removeMacros_.get(i);
        }

        public int getRemoveMacrosCount()
        {
            if(removeMacros_ == null)
                return 0;
            else
                return removeMacros_.size();
        }

        public List getRemoveMacrosList()
        {
            if(removeMacros_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(removeMacros_);
        }

        public ResolvedFunctionCall getRemoveTags(int i)
        {
            return (ResolvedFunctionCall)removeTags_.get(i);
        }

        public int getRemoveTagsCount()
        {
            if(removeTags_ == null)
                return 0;
            else
                return removeTags_.size();
        }

        public List getRemoveTagsList()
        {
            if(removeTags_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(removeTags_);
        }

        public com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value getResult()
        {
            return result_;
        }

        public int getSerializedSize()
        {
            List list = positivePredicates_;
            int i = 0;
            if(list != null)
            {
                for(int l1 = 0; l1 < positivePredicates_.size(); l1++)
                    i += CodedOutputStream.computeMessageSize(1, (MessageLite)positivePredicates_.get(l1));

            }
            if(negativePredicates_ != null)
            {
                for(int k1 = 0; k1 < negativePredicates_.size(); k1++)
                    i += CodedOutputStream.computeMessageSize(2, (MessageLite)negativePredicates_.get(k1));

            }
            if(addTags_ != null)
            {
                for(int j1 = 0; j1 < addTags_.size(); j1++)
                    i += CodedOutputStream.computeMessageSize(3, (MessageLite)addTags_.get(j1));

            }
            if(removeTags_ != null)
            {
                for(int i1 = 0; i1 < removeTags_.size(); i1++)
                    i += CodedOutputStream.computeMessageSize(4, (MessageLite)removeTags_.get(i1));

            }
            if(addMacros_ != null)
            {
                for(int l = 0; l < addMacros_.size(); l++)
                    i += CodedOutputStream.computeMessageSize(5, (MessageLite)addMacros_.get(l));

            }
            if(removeMacros_ != null)
            {
                for(int k = 0; k < removeMacros_.size(); k++)
                    i += CodedOutputStream.computeMessageSize(6, (MessageLite)removeMacros_.get(k));

            }
            if((1 & bitField0_) == 1)
                i += CodedOutputStream.computeMessageSize(7, result_);
            int j = i + unknownFields.size();
            cachedSize = j;
            return j;
        }

        public boolean hasResult()
        {
            return (1 & bitField0_) == 1;
        }

        public int hashCode()
        {
            int i = 41;
            if(getPositivePredicatesCount() > 0)
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getPositivePredicatesList().hashCode();
            }
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
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Debug$ResolvedRule");
            return immutableDefault;
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

        public ResolvedRule mergeFrom(ResolvedRule resolvedrule)
        {
            if(this == resolvedrule)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(resolvedrule == getDefaultInstance())
                return this;
            if(resolvedrule.positivePredicates_ != null && !resolvedrule.positivePredicates_.isEmpty())
            {
                ensurePositivePredicatesInitialized();
                AbstractMutableMessageLite.addAll(resolvedrule.positivePredicates_, positivePredicates_);
            }
            if(resolvedrule.negativePredicates_ != null && !resolvedrule.negativePredicates_.isEmpty())
            {
                ensureNegativePredicatesInitialized();
                AbstractMutableMessageLite.addAll(resolvedrule.negativePredicates_, negativePredicates_);
            }
            if(resolvedrule.addTags_ != null && !resolvedrule.addTags_.isEmpty())
            {
                ensureAddTagsInitialized();
                AbstractMutableMessageLite.addAll(resolvedrule.addTags_, addTags_);
            }
            if(resolvedrule.removeTags_ != null && !resolvedrule.removeTags_.isEmpty())
            {
                ensureRemoveTagsInitialized();
                AbstractMutableMessageLite.addAll(resolvedrule.removeTags_, removeTags_);
            }
            if(resolvedrule.addMacros_ != null && !resolvedrule.addMacros_.isEmpty())
            {
                ensureAddMacrosInitialized();
                AbstractMutableMessageLite.addAll(resolvedrule.addMacros_, addMacros_);
            }
            if(resolvedrule.removeMacros_ != null && !resolvedrule.removeMacros_.isEmpty())
            {
                ensureRemoveMacrosInitialized();
                AbstractMutableMessageLite.addAll(resolvedrule.removeMacros_, removeMacros_);
            }
            if(resolvedrule.hasResult())
            {
                ensureResultInitialized();
                result_.mergeFrom(resolvedrule.getResult());
                bitField0_ = 1 | bitField0_;
            }
            unknownFields = unknownFields.concat(resolvedrule.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((ResolvedRule)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 8: default 108
        //                       0: 254
        //                       10: 127
        //                       18: 139
        //                       26: 151
        //                       34: 163
        //                       42: 175
        //                       50: 187
        //                       58: 199;
               goto _L3 _L4 _L5 _L6 _L7 _L8 _L9 _L10 _L11
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            codedinputstream.readMessage(addPositivePredicates(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L6:
            codedinputstream.readMessage(addNegativePredicates(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L7:
            codedinputstream.readMessage(addAddTags(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L8:
            codedinputstream.readMessage(addRemoveTags(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L9:
            codedinputstream.readMessage(addAddMacros(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L10:
            codedinputstream.readMessage(addRemoveMacros(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L11:
            if(result_ == com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.getDefaultInstance())
                result_ = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.newMessage();
            bitField0_ = 1 | bitField0_;
            codedinputstream.readMessage(result_, extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L13; else goto _L12
_L13:
            break MISSING_BLOCK_LABEL_19;
_L12:
        }

        public ResolvedRule newMessageForType()
        {
            return new ResolvedRule();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public ResolvedRule setAddMacros(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddMacrosInitialized();
                addMacros_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule setAddTags(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureAddTagsInitialized();
                addTags_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule setNegativePredicates(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureNegativePredicatesInitialized();
                negativePredicates_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule setPositivePredicates(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensurePositivePredicatesInitialized();
                positivePredicates_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule setRemoveMacros(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveMacrosInitialized();
                removeMacros_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule setRemoveTags(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRemoveTagsInitialized();
                removeTags_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule setResult(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value)
        {
            assertMutable();
            if(value == null)
            {
                throw new NullPointerException();
            } else
            {
                bitField0_ = 1 | bitField0_;
                result_ = value;
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if(positivePredicates_ != null)
            {
                for(int l1 = 0; l1 < positivePredicates_.size(); l1++)
                    codedoutputstream.writeMessageWithCachedSizes(1, (MutableMessageLite)positivePredicates_.get(l1));

            }
            if(negativePredicates_ != null)
            {
                for(int k1 = 0; k1 < negativePredicates_.size(); k1++)
                    codedoutputstream.writeMessageWithCachedSizes(2, (MutableMessageLite)negativePredicates_.get(k1));

            }
            if(addTags_ != null)
            {
                for(int j1 = 0; j1 < addTags_.size(); j1++)
                    codedoutputstream.writeMessageWithCachedSizes(3, (MutableMessageLite)addTags_.get(j1));

            }
            if(removeTags_ != null)
            {
                for(int i1 = 0; i1 < removeTags_.size(); i1++)
                    codedoutputstream.writeMessageWithCachedSizes(4, (MutableMessageLite)removeTags_.get(i1));

            }
            if(addMacros_ != null)
            {
                for(int l = 0; l < addMacros_.size(); l++)
                    codedoutputstream.writeMessageWithCachedSizes(5, (MutableMessageLite)addMacros_.get(l));

            }
            if(removeMacros_ != null)
            {
                for(int k = 0; k < removeMacros_.size(); k++)
                    codedoutputstream.writeMessageWithCachedSizes(6, (MutableMessageLite)removeMacros_.get(k));

            }
            if((1 & bitField0_) == 1)
                codedoutputstream.writeMessageWithCachedSizes(7, result_);
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int ADD_MACROS_FIELD_NUMBER = 5;
        public static final int ADD_TAGS_FIELD_NUMBER = 3;
        public static final int NEGATIVE_PREDICATES_FIELD_NUMBER = 2;
        public static Parser PARSER;
        public static final int POSITIVE_PREDICATES_FIELD_NUMBER = 1;
        public static final int REMOVE_MACROS_FIELD_NUMBER = 6;
        public static final int REMOVE_TAGS_FIELD_NUMBER = 4;
        public static final int RESULT_FIELD_NUMBER = 7;
        private static final ResolvedRule defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private List addMacros_;
        private List addTags_;
        private int bitField0_;
        private List negativePredicates_;
        private List positivePredicates_;
        private List removeMacros_;
        private List removeTags_;
        private com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value result_;

        static 
        {
            defaultInstance = new ResolvedRule(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private ResolvedRule()
        {
            positivePredicates_ = null;
            negativePredicates_ = null;
            addTags_ = null;
            removeTags_ = null;
            addMacros_ = null;
            removeMacros_ = null;
            initFields();
        }

        private ResolvedRule(boolean flag)
        {
            positivePredicates_ = null;
            negativePredicates_ = null;
            addTags_ = null;
            removeTags_ = null;
            addMacros_ = null;
            removeMacros_ = null;
        }
    }

    public static final class RuleEvaluationStepInfo extends GeneratedMutableMessageLite
        implements MutableMessageLite
    {

        private void ensureEnabledFunctionsInitialized()
        {
            if(enabledFunctions_ == null)
                enabledFunctions_ = new ArrayList();
        }

        private void ensureRulesInitialized()
        {
            if(rules_ == null)
                rules_ = new ArrayList();
        }

        public static RuleEvaluationStepInfo getDefaultInstance()
        {
            return defaultInstance;
        }

        private void initFields()
        {
        }

        public static RuleEvaluationStepInfo newMessage()
        {
            return new RuleEvaluationStepInfo();
        }

        public RuleEvaluationStepInfo addAllEnabledFunctions(Iterable iterable)
        {
            assertMutable();
            ensureEnabledFunctionsInitialized();
            AbstractMutableMessageLite.addAll(iterable, enabledFunctions_);
            return this;
        }

        public RuleEvaluationStepInfo addAllRules(Iterable iterable)
        {
            assertMutable();
            ensureRulesInitialized();
            AbstractMutableMessageLite.addAll(iterable, rules_);
            return this;
        }

        public ResolvedFunctionCall addEnabledFunctions()
        {
            assertMutable();
            ensureEnabledFunctionsInitialized();
            ResolvedFunctionCall resolvedfunctioncall = ResolvedFunctionCall.newMessage();
            enabledFunctions_.add(resolvedfunctioncall);
            return resolvedfunctioncall;
        }

        public RuleEvaluationStepInfo addEnabledFunctions(ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEnabledFunctionsInitialized();
                enabledFunctions_.add(resolvedfunctioncall);
                return this;
            }
        }

        public ResolvedRule addRules()
        {
            assertMutable();
            ensureRulesInitialized();
            ResolvedRule resolvedrule = ResolvedRule.newMessage();
            rules_.add(resolvedrule);
            return resolvedrule;
        }

        public RuleEvaluationStepInfo addRules(ResolvedRule resolvedrule)
        {
            assertMutable();
            if(resolvedrule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRulesInitialized();
                rules_.add(resolvedrule);
                return this;
            }
        }

        public RuleEvaluationStepInfo clear()
        {
            assertMutable();
            super.clear();
            rules_ = null;
            enabledFunctions_ = null;
            return this;
        }

        public volatile GeneratedMutableMessageLite clear()
        {
            return clear();
        }

        public volatile MutableMessageLite clear()
        {
            return clear();
        }

        public RuleEvaluationStepInfo clearEnabledFunctions()
        {
            assertMutable();
            enabledFunctions_ = null;
            return this;
        }

        public RuleEvaluationStepInfo clearRules()
        {
            assertMutable();
            rules_ = null;
            return this;
        }

        public RuleEvaluationStepInfo clone()
        {
            return newMessageForType().mergeFrom(this);
        }

        public volatile MutableMessageLite clone()
        {
            return clone();
        }

        public volatile Object clone()
            throws CloneNotSupportedException
        {
            return clone();
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

        public final RuleEvaluationStepInfo getDefaultInstanceForType()
        {
            return defaultInstance;
        }

        public volatile GeneratedMutableMessageLite getDefaultInstanceForType()
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
            if(enabledFunctions_ == null)
                return 0;
            else
                return enabledFunctions_.size();
        }

        public List getEnabledFunctionsList()
        {
            if(enabledFunctions_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(enabledFunctions_);
        }

        public ResolvedFunctionCall getMutableEnabledFunctions(int i)
        {
            return (ResolvedFunctionCall)enabledFunctions_.get(i);
        }

        public List getMutableEnabledFunctionsList()
        {
            assertMutable();
            ensureEnabledFunctionsInitialized();
            return enabledFunctions_;
        }

        public ResolvedRule getMutableRules(int i)
        {
            return (ResolvedRule)rules_.get(i);
        }

        public List getMutableRulesList()
        {
            assertMutable();
            ensureRulesInitialized();
            return rules_;
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
            if(rules_ == null)
                return 0;
            else
                return rules_.size();
        }

        public List getRulesList()
        {
            if(rules_ == null)
                return Collections.emptyList();
            else
                return Collections.unmodifiableList(rules_);
        }

        public int getSerializedSize()
        {
            List list = rules_;
            int i = 0;
            if(list != null)
            {
                for(int l = 0; l < rules_.size(); l++)
                    i += CodedOutputStream.computeMessageSize(1, (MessageLite)rules_.get(l));

            }
            if(enabledFunctions_ != null)
            {
                for(int k = 0; k < enabledFunctions_.size(); k++)
                    i += CodedOutputStream.computeMessageSize(2, (MessageLite)enabledFunctions_.get(k));

            }
            int j = i + unknownFields.size();
            cachedSize = j;
            return j;
        }

        public int hashCode()
        {
            int i = 41;
            if(getRulesCount() > 0)
            {
                int _tmp = 1517 + 1;
                i = 0x13a46 + getRulesList().hashCode();
            }
            if(getEnabledFunctionsCount() > 0)
                i = 53 * (2 + i * 37) + getEnabledFunctionsList().hashCode();
            return i * 29 + unknownFields.hashCode();
        }

        protected MessageLite internalImmutableDefault()
        {
            if(immutableDefault == null)
                immutableDefault = internalImmutableDefault("com.google.analytics.containertag.proto.Debug$RuleEvaluationStepInfo");
            return immutableDefault;
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

        public RuleEvaluationStepInfo mergeFrom(RuleEvaluationStepInfo ruleevaluationstepinfo)
        {
            if(this == ruleevaluationstepinfo)
                throw new IllegalArgumentException("mergeFrom(message) called on the same message.");
            assertMutable();
            if(ruleevaluationstepinfo == getDefaultInstance())
                return this;
            if(ruleevaluationstepinfo.rules_ != null && !ruleevaluationstepinfo.rules_.isEmpty())
            {
                ensureRulesInitialized();
                AbstractMutableMessageLite.addAll(ruleevaluationstepinfo.rules_, rules_);
            }
            if(ruleevaluationstepinfo.enabledFunctions_ != null && !ruleevaluationstepinfo.enabledFunctions_.isEmpty())
            {
                ensureEnabledFunctionsInitialized();
                AbstractMutableMessageLite.addAll(ruleevaluationstepinfo.enabledFunctions_, enabledFunctions_);
            }
            unknownFields = unknownFields.concat(ruleevaluationstepinfo.unknownFields);
            return this;
        }

        public volatile GeneratedMutableMessageLite mergeFrom(GeneratedMutableMessageLite generatedmutablemessagelite)
        {
            return mergeFrom((RuleEvaluationStepInfo)generatedmutablemessagelite);
        }

        public boolean mergeFrom(CodedInputStream codedinputstream, ExtensionRegistryLite extensionregistrylite)
        {
            assertMutable();
            com.google.tagmanager.protobuf.ByteString.Output output;
            CodedOutputStream codedoutputstream;
            boolean flag;
            int i;
            try
            {
                output = ByteString.newOutput();
                codedoutputstream = CodedOutputStream.newInstance(output);
            }
            catch(IOException ioexception)
            {
                return false;
            }
            flag = false;
            if(flag) goto _L2; else goto _L1
_L1:
            i = codedinputstream.readTag();
            i;
            JVM INSTR lookupswitch 3: default 68
        //                       0: 127
        //                       10: 87
        //                       18: 99;
               goto _L3 _L4 _L5 _L6
_L3:
            if(!parseUnknownField(codedinputstream, codedoutputstream, extensionregistrylite, i))
                flag = true;
            continue; /* Loop/switch isn't completed */
_L5:
            codedinputstream.readMessage(addRules(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L6:
            codedinputstream.readMessage(addEnabledFunctions(), extensionregistrylite);
            continue; /* Loop/switch isn't completed */
_L2:
            codedoutputstream.flush();
            unknownFields = output.toByteString();
            return true;
_L4:
            flag = true;
            if(true) goto _L8; else goto _L7
_L8:
            break MISSING_BLOCK_LABEL_19;
_L7:
        }

        public RuleEvaluationStepInfo newMessageForType()
        {
            return new RuleEvaluationStepInfo();
        }

        public volatile MutableMessageLite newMessageForType()
        {
            return newMessageForType();
        }

        public RuleEvaluationStepInfo setEnabledFunctions(int i, ResolvedFunctionCall resolvedfunctioncall)
        {
            assertMutable();
            if(resolvedfunctioncall == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureEnabledFunctionsInitialized();
                enabledFunctions_.set(i, resolvedfunctioncall);
                return this;
            }
        }

        public RuleEvaluationStepInfo setRules(int i, ResolvedRule resolvedrule)
        {
            assertMutable();
            if(resolvedrule == null)
            {
                throw new NullPointerException();
            } else
            {
                ensureRulesInitialized();
                rules_.set(i, resolvedrule);
                return this;
            }
        }

        protected Object writeReplace()
            throws ObjectStreamException
        {
            return super.writeReplace();
        }

        public void writeToWithCachedSizes(CodedOutputStream codedoutputstream)
            throws IOException
        {
            int i = codedoutputstream.getTotalBytesWritten();
            if(rules_ != null)
            {
                for(int l = 0; l < rules_.size(); l++)
                    codedoutputstream.writeMessageWithCachedSizes(1, (MutableMessageLite)rules_.get(l));

            }
            if(enabledFunctions_ != null)
            {
                for(int k = 0; k < enabledFunctions_.size(); k++)
                    codedoutputstream.writeMessageWithCachedSizes(2, (MutableMessageLite)enabledFunctions_.get(k));

            }
            codedoutputstream.writeRawBytes(unknownFields);
            int j = codedoutputstream.getTotalBytesWritten();
            if(getCachedSize() != j - i)
                throw new RuntimeException("Serialized size doesn't match cached size. You may forget to call getSerializedSize() or the message is being modified concurrently.");
            else
                return;
        }

        public static final int ENABLED_FUNCTIONS_FIELD_NUMBER = 2;
        public static Parser PARSER;
        public static final int RULES_FIELD_NUMBER = 1;
        private static final RuleEvaluationStepInfo defaultInstance;
        private static volatile MessageLite immutableDefault = null;
        private static final long serialVersionUID;
        private List enabledFunctions_;
        private List rules_;

        static 
        {
            defaultInstance = new RuleEvaluationStepInfo(true);
            defaultInstance.initFields();
            defaultInstance.makeImmutable();
            PARSER = AbstractMutableMessageLite.internalNewParserForType(defaultInstance);
        }

        private RuleEvaluationStepInfo()
        {
            rules_ = null;
            enabledFunctions_ = null;
            initFields();
        }

        private RuleEvaluationStepInfo(boolean flag)
        {
            rules_ = null;
            enabledFunctions_ = null;
        }
    }


    private MutableDebug()
    {
    }

    public static void registerAllExtensions(ExtensionRegistryLite extensionregistrylite)
    {
        extensionregistrylite.add(MacroEvaluationInfo.macro);
    }

}
