// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import android.content.Context;
import com.google.analytics.containertag.common.Key;
import java.util.*;

// Referenced classes of package com.google.tagmanager:
//            ObjectAndStatic, Types, NoopEventInfoDistributor, CacheFactory, 
//            ArbitraryPixelTag, CustomFunctionCall, UniversalAnalyticsTag, ContainsPredicate, 
//            EndsWithPredicate, EqualsPredicate, GreaterEqualsPredicate, GreaterThanPredicate, 
//            LessEqualsPredicate, LessThanPredicate, RegexPredicate, StartsWithPredicate, 
//            AdvertiserIdMacro, AdvertisingTrackingEnabledMacro, AdwordsClickReferrerMacro, AppIdMacro, 
//            AppNameMacro, AppVersionMacro, ConstantMacro, DataLayerMacro, 
//            DeviceIdMacro, DeviceNameMacro, EncodeMacro, EventMacro, 
//            GtmVersionMacro, HashMacro, InstallReferrerMacro, JoinerMacro, 
//            LanguageMacro, MobileAdwordsUniqueIdMacro, OsVersionMacro, PlatformMacro, 
//            RandomMacro, RegexGroupMacro, ResolutionMacro, RuntimeVersionMacro, 
//            SdkVersionMacro, TimeMacro, EventInfoDistributor, FunctionCallImplementation, 
//            RuleEvaluationStepInfoBuilder, Cache, Log, MacroEvaluationInfoBuilder, 
//            ResolvedFunctionCallBuilder, ResolvedPropertyBuilder, ResourceUtil, ValueBuilder, 
//            ValueEscapeUtil, EventInfoBuilder, ResolvedRuleBuilder, DataLayerEventEvaluationInfoBuilder, 
//            DataLayer, ResolvedFunctionCallTranslatorList

class Runtime
{
    static interface AddRemoveSetPopulator
    {

        public abstract void rulePassed(ResourceUtil.ExpandedRule expandedrule, Set set, Set set1, ResolvedRuleBuilder resolvedrulebuilder);
    }

    private static class MacroInfo
    {

        public void addAddMacroForRule(ResourceUtil.ExpandedRule expandedrule, ResourceUtil.ExpandedFunctionCall expandedfunctioncall)
        {
            Object obj = (List)mAddMacros.get(expandedrule);
            if(obj == null)
            {
                obj = new ArrayList();
                mAddMacros.put(expandedrule, obj);
            }
            ((List) (obj)).add(expandedfunctioncall);
        }

        public void addAddMacroRuleNameForRule(ResourceUtil.ExpandedRule expandedrule, String s)
        {
            Object obj = (List)mAddMacroNames.get(expandedrule);
            if(obj == null)
            {
                obj = new ArrayList();
                mAddMacroNames.put(expandedrule, obj);
            }
            ((List) (obj)).add(s);
        }

        public void addRemoveMacroForRule(ResourceUtil.ExpandedRule expandedrule, ResourceUtil.ExpandedFunctionCall expandedfunctioncall)
        {
            Object obj = (List)mRemoveMacros.get(expandedrule);
            if(obj == null)
            {
                obj = new ArrayList();
                mRemoveMacros.put(expandedrule, obj);
            }
            ((List) (obj)).add(expandedfunctioncall);
        }

        public void addRemoveMacroRuleNameForRule(ResourceUtil.ExpandedRule expandedrule, String s)
        {
            Object obj = (List)mRemoveMacroNames.get(expandedrule);
            if(obj == null)
            {
                obj = new ArrayList();
                mRemoveMacroNames.put(expandedrule, obj);
            }
            ((List) (obj)).add(s);
        }

        public void addRule(ResourceUtil.ExpandedRule expandedrule)
        {
            mRules.add(expandedrule);
        }

        public Map getAddMacroRuleNames()
        {
            return mAddMacroNames;
        }

        public Map getAddMacros()
        {
            return mAddMacros;
        }

        public ResourceUtil.ExpandedFunctionCall getDefault()
        {
            return mDefault;
        }

        public Map getRemoveMacroRuleNames()
        {
            return mRemoveMacroNames;
        }

        public Map getRemoveMacros()
        {
            return mRemoveMacros;
        }

        public Set getRules()
        {
            return mRules;
        }

        public void setDefault(ResourceUtil.ExpandedFunctionCall expandedfunctioncall)
        {
            mDefault = expandedfunctioncall;
        }

        private final Map mAddMacroNames = new HashMap();
        private final Map mAddMacros = new HashMap();
        private ResourceUtil.ExpandedFunctionCall mDefault;
        private final Map mRemoveMacroNames = new HashMap();
        private final Map mRemoveMacros = new HashMap();
        private final Set mRules = new HashSet();

        public MacroInfo()
        {
        }
    }


    public Runtime(Context context, ResourceUtil.ExpandedResource expandedresource, DataLayer datalayer, CustomFunctionCall.CustomEvaluator customevaluator, CustomFunctionCall.CustomEvaluator customevaluator1)
    {
        this(context, expandedresource, datalayer, customevaluator, customevaluator1, ((EventInfoDistributor) (new NoopEventInfoDistributor())));
    }

    public Runtime(Context context, ResourceUtil.ExpandedResource expandedresource, DataLayer datalayer, CustomFunctionCall.CustomEvaluator customevaluator, CustomFunctionCall.CustomEvaluator customevaluator1, EventInfoDistributor eventinfodistributor)
    {
        if(expandedresource == null)
            throw new NullPointerException("resource cannot be null");
        mResource = expandedresource;
        mRules = new HashSet(expandedresource.getRules());
        eventInfoDistributor = eventinfodistributor;
        CacheFactory.CacheSizeManager cachesizemanager = new CacheFactory.CacheSizeManager() {

            public int sizeOf(ResourceUtil.ExpandedFunctionCall expandedfunctioncall3, ObjectAndStatic objectandstatic)
            {
                return ((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic.getObject()).toByteArray().length;
            }

            public volatile int sizeOf(Object obj, Object obj1)
            {
                return sizeOf((ResourceUtil.ExpandedFunctionCall)obj, (ObjectAndStatic)obj1);
            }

            final Runtime this$0;

            
            {
                this$0 = Runtime.this;
                super();
            }
        }
;
        mFunctionCallCache = (new CacheFactory()).createCache(0x100000, cachesizemanager);
        CacheFactory.CacheSizeManager cachesizemanager1 = new CacheFactory.CacheSizeManager() {

            public volatile int sizeOf(Object obj, Object obj1)
            {
                return sizeOf((String)obj, (ObjectAndStatic)obj1);
            }

            public int sizeOf(String s2, ObjectAndStatic objectandstatic)
            {
                return s2.length() + ((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic.getObject()).toByteArray().length;
            }

            final Runtime this$0;

            
            {
                this$0 = Runtime.this;
                super();
            }
        }
;
        mMacroEvaluationCache = (new CacheFactory()).createCache(0x100000, cachesizemanager1);
        mTrackingTagMap = new HashMap();
        addTrackingTag(new ArbitraryPixelTag(context));
        addTrackingTag(new CustomFunctionCall(customevaluator1));
        addTrackingTag(new UniversalAnalyticsTag(context, datalayer));
        mPredicateMap = new HashMap();
        addPredicate(new ContainsPredicate());
        addPredicate(new EndsWithPredicate());
        addPredicate(new EqualsPredicate());
        addPredicate(new GreaterEqualsPredicate());
        addPredicate(new GreaterThanPredicate());
        addPredicate(new LessEqualsPredicate());
        addPredicate(new LessThanPredicate());
        addPredicate(new RegexPredicate());
        addPredicate(new StartsWithPredicate());
        mMacroMap = new HashMap();
        addMacro(new AdvertiserIdMacro(context));
        addMacro(new AdvertisingTrackingEnabledMacro());
        addMacro(new AdwordsClickReferrerMacro(context));
        addMacro(new AppIdMacro(context));
        addMacro(new AppNameMacro(context));
        addMacro(new AppVersionMacro(context));
        addMacro(new ConstantMacro());
        addMacro(new CustomFunctionCall(customevaluator));
        addMacro(new DataLayerMacro(datalayer));
        addMacro(new DeviceIdMacro(context));
        addMacro(new DeviceNameMacro());
        addMacro(new EncodeMacro());
        addMacro(new EventMacro(this));
        addMacro(new GtmVersionMacro());
        addMacro(new HashMacro());
        addMacro(new InstallReferrerMacro(context));
        addMacro(new JoinerMacro());
        addMacro(new LanguageMacro());
        addMacro(new MobileAdwordsUniqueIdMacro(context));
        addMacro(new OsVersionMacro());
        addMacro(new PlatformMacro());
        addMacro(new RandomMacro());
        addMacro(new RegexGroupMacro());
        addMacro(new ResolutionMacro(context));
        addMacro(new RuntimeVersionMacro());
        addMacro(new SdkVersionMacro());
        addMacro(new TimeMacro());
        mMacroLookup = new HashMap();
        for(Iterator iterator = mRules.iterator(); iterator.hasNext();)
        {
            ResourceUtil.ExpandedRule expandedrule = (ResourceUtil.ExpandedRule)iterator.next();
            if(eventinfodistributor.debugMode())
            {
                verifyFunctionAndNameListSizes(expandedrule.getAddMacros(), expandedrule.getAddMacroRuleNames(), "add macro");
                verifyFunctionAndNameListSizes(expandedrule.getRemoveMacros(), expandedrule.getRemoveMacroRuleNames(), "remove macro");
                verifyFunctionAndNameListSizes(expandedrule.getAddTags(), expandedrule.getAddTagRuleNames(), "add tag");
                verifyFunctionAndNameListSizes(expandedrule.getRemoveTags(), expandedrule.getRemoveTagRuleNames(), "remove tag");
            }
            for(int i = 0; i < expandedrule.getAddMacros().size(); i++)
            {
                ResourceUtil.ExpandedFunctionCall expandedfunctioncall2 = (ResourceUtil.ExpandedFunctionCall)expandedrule.getAddMacros().get(i);
                String s1 = "Unknown";
                if(eventinfodistributor.debugMode() && i < expandedrule.getAddMacroRuleNames().size())
                    s1 = (String)expandedrule.getAddMacroRuleNames().get(i);
                MacroInfo macroinfo1 = getOrAddMacroInfo(mMacroLookup, getFunctionName(expandedfunctioncall2));
                macroinfo1.addRule(expandedrule);
                macroinfo1.addAddMacroForRule(expandedrule, expandedfunctioncall2);
                macroinfo1.addAddMacroRuleNameForRule(expandedrule, s1);
            }

            int j = 0;
            while(j < expandedrule.getRemoveMacros().size()) 
            {
                ResourceUtil.ExpandedFunctionCall expandedfunctioncall1 = (ResourceUtil.ExpandedFunctionCall)expandedrule.getRemoveMacros().get(j);
                String s = "Unknown";
                if(eventinfodistributor.debugMode() && j < expandedrule.getRemoveMacroRuleNames().size())
                    s = (String)expandedrule.getRemoveMacroRuleNames().get(j);
                MacroInfo macroinfo = getOrAddMacroInfo(mMacroLookup, getFunctionName(expandedfunctioncall1));
                macroinfo.addRule(expandedrule);
                macroinfo.addRemoveMacroForRule(expandedrule, expandedfunctioncall1);
                macroinfo.addRemoveMacroRuleNameForRule(expandedrule, s);
                j++;
            }
        }

        for(Iterator iterator1 = mResource.getAllMacros().entrySet().iterator(); iterator1.hasNext();)
        {
            java.util.Map.Entry entry = (java.util.Map.Entry)iterator1.next();
            Iterator iterator2 = ((List)entry.getValue()).iterator();
            while(iterator2.hasNext()) 
            {
                ResourceUtil.ExpandedFunctionCall expandedfunctioncall = (ResourceUtil.ExpandedFunctionCall)iterator2.next();
                if(!Types.valueToBoolean((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)expandedfunctioncall.getProperties().get(Key.NOT_DEFAULT_MACRO.toString())).booleanValue())
                    getOrAddMacroInfo(mMacroLookup, (String)entry.getKey()).setDefault(expandedfunctioncall);
            }
        }

    }

    private static void addFunctionImplToMap(Map map, FunctionCallImplementation functioncallimplementation)
    {
        if(map.containsKey(functioncallimplementation.getInstanceFunctionId()))
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Duplicate function type name: ").append(functioncallimplementation.getInstanceFunctionId()).toString());
        } else
        {
            map.put(functioncallimplementation.getInstanceFunctionId(), functioncallimplementation);
            return;
        }
    }

    private ObjectAndStatic calculateGenericToRun(Set set, Set set1, AddRemoveSetPopulator addremovesetpopulator, RuleEvaluationStepInfoBuilder ruleevaluationstepinfobuilder)
    {
        HashSet hashset = new HashSet();
        HashSet hashset1 = new HashSet();
        boolean flag = true;
        for(Iterator iterator = set.iterator(); iterator.hasNext();)
        {
            ResourceUtil.ExpandedRule expandedrule = (ResourceUtil.ExpandedRule)iterator.next();
            ResolvedRuleBuilder resolvedrulebuilder = ruleevaluationstepinfobuilder.createResolvedRuleBuilder();
            ObjectAndStatic objectandstatic = evaluatePredicatesInRule(expandedrule, set1, resolvedrulebuilder);
            if(((Boolean)objectandstatic.getObject()).booleanValue())
                addremovesetpopulator.rulePassed(expandedrule, hashset, hashset1, resolvedrulebuilder);
            if(flag && objectandstatic.isStatic())
                flag = true;
            else
                flag = false;
        }

        hashset.removeAll(hashset1);
        ruleevaluationstepinfobuilder.setEnabledFunctions(hashset);
        return new ObjectAndStatic(hashset, flag);
    }

    private ObjectAndStatic evaluateMacroReferenceCycleDetection(String s, Set set, MacroEvaluationInfoBuilder macroevaluationinfobuilder)
    {
        ObjectAndStatic objectandstatic = (ObjectAndStatic)mMacroEvaluationCache.get(s);
        if(objectandstatic != null && !eventInfoDistributor.debugMode())
            return objectandstatic;
        MacroInfo macroinfo = (MacroInfo)mMacroLookup.get(s);
        if(macroinfo == null)
        {
            Log.e((new StringBuilder()).append("Invalid macro: ").append(s).toString());
            return DEFAULT_VALUE_AND_STATIC;
        }
        ObjectAndStatic objectandstatic1 = calculateMacrosToRun(s, macroinfo.getRules(), macroinfo.getAddMacros(), macroinfo.getAddMacroRuleNames(), macroinfo.getRemoveMacros(), macroinfo.getRemoveMacroRuleNames(), set, macroevaluationinfobuilder.createRulesEvaluation());
        ResourceUtil.ExpandedFunctionCall expandedfunctioncall;
        if(((Set)objectandstatic1.getObject()).isEmpty())
        {
            expandedfunctioncall = macroinfo.getDefault();
        } else
        {
            if(((Set)objectandstatic1.getObject()).size() > 1)
                Log.w((new StringBuilder()).append("Multiple macros active for macroName ").append(s).toString());
            expandedfunctioncall = (ResourceUtil.ExpandedFunctionCall)((Set)objectandstatic1.getObject()).iterator().next();
        }
        if(expandedfunctioncall == null)
            return DEFAULT_VALUE_AND_STATIC;
        ObjectAndStatic objectandstatic2 = executeFunction(mMacroMap, expandedfunctioncall, set, macroevaluationinfobuilder.createResult());
        boolean flag;
        ObjectAndStatic objectandstatic3;
        if(objectandstatic1.isStatic() && objectandstatic2.isStatic())
            flag = true;
        else
            flag = false;
        if(objectandstatic2 == DEFAULT_VALUE_AND_STATIC)
        {
            objectandstatic3 = DEFAULT_VALUE_AND_STATIC;
        } else
        {
            Object obj = objectandstatic2.getObject();
            objectandstatic3 = new ObjectAndStatic(obj, flag);
        }
        if(objectandstatic3.isStatic())
            mMacroEvaluationCache.put(s, objectandstatic3);
        return objectandstatic3;
    }

    private ObjectAndStatic executeFunction(Map map, ResourceUtil.ExpandedFunctionCall expandedfunctioncall, Set set, ResolvedFunctionCallBuilder resolvedfunctioncallbuilder)
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)expandedfunctioncall.getProperties().get(Key.FUNCTION.toString());
        ObjectAndStatic objectandstatic;
        if(value == null)
        {
            Log.e("No function id in properties");
            objectandstatic = DEFAULT_VALUE_AND_STATIC;
        } else
        {
            String s = value.getFunctionId();
            FunctionCallImplementation functioncallimplementation = (FunctionCallImplementation)map.get(s);
            if(functioncallimplementation == null)
            {
                Log.e((new StringBuilder()).append(s).append(" has no backing implementation.").toString());
                return DEFAULT_VALUE_AND_STATIC;
            }
            objectandstatic = (ObjectAndStatic)mFunctionCallCache.get(expandedfunctioncall);
            if(objectandstatic == null || eventInfoDistributor.debugMode())
            {
                HashMap hashmap = new HashMap();
                boolean flag = true;
                Iterator iterator = expandedfunctioncall.getProperties().entrySet().iterator();
                while(iterator.hasNext()) 
                {
                    java.util.Map.Entry entry = (java.util.Map.Entry)iterator.next();
                    ResolvedPropertyBuilder resolvedpropertybuilder = resolvedfunctioncallbuilder.createResolvedPropertyBuilder((String)entry.getKey());
                    ObjectAndStatic objectandstatic2 = macroExpandValue((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)entry.getValue(), set, resolvedpropertybuilder.createPropertyValueBuilder((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)entry.getValue()));
                    if(objectandstatic2 == DEFAULT_VALUE_AND_STATIC)
                        return DEFAULT_VALUE_AND_STATIC;
                    if(objectandstatic2.isStatic())
                        expandedfunctioncall.updateCacheableProperty((String)entry.getKey(), (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic2.getObject());
                    else
                        flag = false;
                    hashmap.put(entry.getKey(), objectandstatic2.getObject());
                }
                if(!functioncallimplementation.hasRequiredKeys(hashmap.keySet()))
                {
                    Log.e((new StringBuilder()).append("Incorrect keys for function ").append(s).append(" required ").append(functioncallimplementation.getRequiredKeys()).append(" had ").append(hashmap.keySet()).toString());
                    return DEFAULT_VALUE_AND_STATIC;
                }
                boolean flag1;
                ObjectAndStatic objectandstatic1;
                if(flag && functioncallimplementation.isCacheable())
                    flag1 = true;
                else
                    flag1 = false;
                objectandstatic1 = new ObjectAndStatic(functioncallimplementation.evaluate(hashmap), flag1);
                if(flag1)
                    mFunctionCallCache.put(expandedfunctioncall, objectandstatic1);
                resolvedfunctioncallbuilder.setFunctionResult((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic1.getObject());
                return objectandstatic1;
            }
        }
        return objectandstatic;
    }

    private static String getFunctionName(ResourceUtil.ExpandedFunctionCall expandedfunctioncall)
    {
        return Types.valueToString((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)expandedfunctioncall.getProperties().get(Key.INSTANCE_NAME.toString()));
    }

    private static MacroInfo getOrAddMacroInfo(Map map, String s)
    {
        MacroInfo macroinfo = (MacroInfo)map.get(s);
        if(macroinfo == null)
        {
            macroinfo = new MacroInfo();
            map.put(s, macroinfo);
        }
        return macroinfo;
    }

    private ObjectAndStatic macroExpandValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value, Set set, ValueBuilder valuebuilder)
    {
        if(!value.getContainsReferences())
            return new ObjectAndStatic(value, true);
        static class _cls5
        {

            static final int $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[];

            static 
            {
                $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type = new int[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.values().length];
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.LIST.ordinal()] = 1;
                }
                catch(NoSuchFieldError nosuchfielderror) { }
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.MAP.ordinal()] = 2;
                }
                catch(NoSuchFieldError nosuchfielderror1) { }
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.MACRO_REFERENCE.ordinal()] = 3;
                }
                catch(NoSuchFieldError nosuchfielderror2) { }
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.TEMPLATE.ordinal()] = 4;
                }
                catch(NoSuchFieldError nosuchfielderror3)
                {
                    return;
                }
            }
        }

        com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder;
        int i;
        switch(_cls5..SwitchMap.com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type[value.getType().ordinal()])
        {
        default:
            Log.e((new StringBuilder()).append("Unknown type: ").append(value.getType()).toString());
            return DEFAULT_VALUE_AND_STATIC;

        case 1: // '\001'
            com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder2 = ResourceUtil.newValueBuilderBasedOnValue(value);
            for(int k = 0; k < value.getListItemCount(); k++)
            {
                ObjectAndStatic objectandstatic4 = macroExpandValue(value.getListItem(k), set, valuebuilder.getListItem(k));
                if(objectandstatic4 == DEFAULT_VALUE_AND_STATIC)
                    return DEFAULT_VALUE_AND_STATIC;
                builder2.addListItem((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic4.getObject());
            }

            return new ObjectAndStatic(builder2.build(), false);

        case 2: // '\002'
            com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder1 = ResourceUtil.newValueBuilderBasedOnValue(value);
            if(value.getMapKeyCount() != value.getMapValueCount())
            {
                Log.e((new StringBuilder()).append("Invalid serving value: ").append(value.toString()).toString());
                return DEFAULT_VALUE_AND_STATIC;
            }
            for(int j = 0; j < value.getMapKeyCount(); j++)
            {
                ObjectAndStatic objectandstatic2 = macroExpandValue(value.getMapKey(j), set, valuebuilder.getMapKey(j));
                ObjectAndStatic objectandstatic3 = macroExpandValue(value.getMapValue(j), set, valuebuilder.getMapValue(j));
                if(objectandstatic2 == DEFAULT_VALUE_AND_STATIC || objectandstatic3 == DEFAULT_VALUE_AND_STATIC)
                    return DEFAULT_VALUE_AND_STATIC;
                builder1.addMapKey((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic2.getObject());
                builder1.addMapValue((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic3.getObject());
            }

            return new ObjectAndStatic(builder1.build(), false);

        case 3: // '\003'
            if(set.contains(value.getMacroReference()))
            {
                Log.e((new StringBuilder()).append("Macro cycle detected.  Current macro reference: ").append(value.getMacroReference()).append(".").append("  Previous macro references: ").append(set.toString()).append(".").toString());
                return DEFAULT_VALUE_AND_STATIC;
            } else
            {
                set.add(value.getMacroReference());
                ObjectAndStatic objectandstatic1 = ValueEscapeUtil.applyEscapings(evaluateMacroReferenceCycleDetection(value.getMacroReference(), set, valuebuilder.createValueMacroEvaluationInfoExtension()), value.getEscapingList());
                set.remove(value.getMacroReference());
                return objectandstatic1;
            }

        case 4: // '\004'
            builder = ResourceUtil.newValueBuilderBasedOnValue(value);
            i = 0;
            break;
        }
        for(; i < value.getTemplateTokenCount(); i++)
        {
            ObjectAndStatic objectandstatic = macroExpandValue(value.getTemplateToken(i), set, valuebuilder.getTemplateToken(i));
            if(objectandstatic == DEFAULT_VALUE_AND_STATIC)
                return DEFAULT_VALUE_AND_STATIC;
            builder.addTemplateToken((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic.getObject());
        }

        return new ObjectAndStatic(builder.build(), false);
    }

    private static void verifyFunctionAndNameListSizes(List list, List list1, String s)
    {
        if(list.size() != list1.size())
            Log.i((new StringBuilder()).append("Invalid resource: imbalance of rule names of functions for ").append(s).append(" operation. Using default rule name instead").toString());
    }

    void addMacro(FunctionCallImplementation functioncallimplementation)
    {
        addFunctionImplToMap(mMacroMap, functioncallimplementation);
    }

    void addPredicate(FunctionCallImplementation functioncallimplementation)
    {
        addFunctionImplToMap(mPredicateMap, functioncallimplementation);
    }

    void addTrackingTag(FunctionCallImplementation functioncallimplementation)
    {
        addFunctionImplToMap(mTrackingTagMap, functioncallimplementation);
    }

    ObjectAndStatic calculateMacrosToRun(String s, Set set, final Map addMacros, final Map addMacroRuleNames, final Map removeMacros, final Map removeMacroRuleNames, Set set1, 
            RuleEvaluationStepInfoBuilder ruleevaluationstepinfobuilder)
    {
        return calculateGenericToRun(set, set1, new AddRemoveSetPopulator() {

            public void rulePassed(ResourceUtil.ExpandedRule expandedrule, Set set2, Set set3, ResolvedRuleBuilder resolvedrulebuilder)
            {
                List list = (List)addMacros.get(expandedrule);
                List list1 = (List)addMacroRuleNames.get(expandedrule);
                if(list != null)
                {
                    set2.addAll(list);
                    resolvedrulebuilder.getAddedMacroFunctions().translateAndAddAll(list, list1);
                }
                List list2 = (List)removeMacros.get(expandedrule);
                List list3 = (List)removeMacroRuleNames.get(expandedrule);
                if(list2 != null)
                {
                    set3.addAll(list2);
                    resolvedrulebuilder.getRemovedMacroFunctions().translateAndAddAll(list2, list3);
                }
            }

            final Runtime this$0;
            final Map val$addMacroRuleNames;
            final Map val$addMacros;
            final Map val$removeMacroRuleNames;
            final Map val$removeMacros;

            
            {
                this$0 = Runtime.this;
                addMacros = map;
                addMacroRuleNames = map1;
                removeMacros = map2;
                removeMacroRuleNames = map3;
                super();
            }
        }
, ruleevaluationstepinfobuilder);
    }

    ObjectAndStatic calculateTagsToRun(Set set, RuleEvaluationStepInfoBuilder ruleevaluationstepinfobuilder)
    {
        return calculateGenericToRun(set, new HashSet(), new AddRemoveSetPopulator() {

            public void rulePassed(ResourceUtil.ExpandedRule expandedrule, Set set1, Set set2, ResolvedRuleBuilder resolvedrulebuilder)
            {
                set1.addAll(expandedrule.getAddTags());
                set2.addAll(expandedrule.getRemoveTags());
                resolvedrulebuilder.getAddedTagFunctions().translateAndAddAll(expandedrule.getAddTags(), expandedrule.getAddTagRuleNames());
                resolvedrulebuilder.getRemovedTagFunctions().translateAndAddAll(expandedrule.getRemoveTags(), expandedrule.getRemoveTagRuleNames());
            }

            final Runtime this$0;

            
            {
                this$0 = Runtime.this;
                super();
            }
        }
, ruleevaluationstepinfobuilder);
    }

    public ObjectAndStatic evaluateMacroReference(String s)
    {
        EventInfoBuilder eventinfobuilder = eventInfoDistributor.createMacroEvalutionEventInfo(s);
        ObjectAndStatic objectandstatic = evaluateMacroReferenceCycleDetection(s, new HashSet(), eventinfobuilder.createMacroEvaluationInfoBuilder());
        eventinfobuilder.processEventInfo();
        return objectandstatic;
    }

    ObjectAndStatic evaluatePredicate(ResourceUtil.ExpandedFunctionCall expandedfunctioncall, Set set, ResolvedFunctionCallBuilder resolvedfunctioncallbuilder)
    {
        ObjectAndStatic objectandstatic = executeFunction(mPredicateMap, expandedfunctioncall, set, resolvedfunctioncallbuilder);
        Boolean boolean1 = Types.valueToBoolean((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)objectandstatic.getObject());
        resolvedfunctioncallbuilder.setFunctionResult(Types.objectToValue(boolean1));
        return new ObjectAndStatic(boolean1, objectandstatic.isStatic());
    }

    ObjectAndStatic evaluatePredicatesInRule(ResourceUtil.ExpandedRule expandedrule, Set set, ResolvedRuleBuilder resolvedrulebuilder)
    {
        boolean flag = true;
        for(Iterator iterator = expandedrule.getNegativePredicates().iterator(); iterator.hasNext();)
        {
            ObjectAndStatic objectandstatic1 = evaluatePredicate((ResourceUtil.ExpandedFunctionCall)iterator.next(), set, resolvedrulebuilder.createNegativePredicate());
            if(((Boolean)objectandstatic1.getObject()).booleanValue())
            {
                resolvedrulebuilder.setValue(Types.objectToValue(Boolean.valueOf(false)));
                return new ObjectAndStatic(Boolean.valueOf(false), objectandstatic1.isStatic());
            }
            if(flag && objectandstatic1.isStatic())
                flag = true;
            else
                flag = false;
        }

        for(Iterator iterator1 = expandedrule.getPositivePredicates().iterator(); iterator1.hasNext();)
        {
            ObjectAndStatic objectandstatic = evaluatePredicate((ResourceUtil.ExpandedFunctionCall)iterator1.next(), set, resolvedrulebuilder.createPositivePredicate());
            if(!((Boolean)objectandstatic.getObject()).booleanValue())
            {
                resolvedrulebuilder.setValue(Types.objectToValue(Boolean.valueOf(false)));
                return new ObjectAndStatic(Boolean.valueOf(false), objectandstatic.isStatic());
            }
            if(flag && objectandstatic.isStatic())
                flag = true;
            else
                flag = false;
        }

        resolvedrulebuilder.setValue(Types.objectToValue(Boolean.valueOf(true)));
        return new ObjectAndStatic(Boolean.valueOf(true), flag);
    }

    public void evaluateTags(String s)
    {
        this;
        JVM INSTR monitorenter ;
        EventInfoBuilder eventinfobuilder;
        setCurrentEventName(s);
        eventinfobuilder = eventInfoDistributor.createDataLayerEventEvaluationEventInfo(s);
        DataLayerEventEvaluationInfoBuilder datalayereventevaluationinfobuilder = eventinfobuilder.createDataLayerEventEvaluationInfoBuilder();
        ResourceUtil.ExpandedFunctionCall expandedfunctioncall;
        for(Iterator iterator = ((Set)calculateTagsToRun(mRules, datalayereventevaluationinfobuilder.createRulesEvaluation()).getObject()).iterator(); iterator.hasNext(); executeFunction(mTrackingTagMap, expandedfunctioncall, new HashSet(), datalayereventevaluationinfobuilder.createAndAddResult()))
            expandedfunctioncall = (ResourceUtil.ExpandedFunctionCall)iterator.next();

        break MISSING_BLOCK_LABEL_109;
        Exception exception;
        exception;
        throw exception;
        eventinfobuilder.processEventInfo();
        setCurrentEventName(null);
        this;
        JVM INSTR monitorexit ;
    }

    String getCurrentEventName()
    {
        this;
        JVM INSTR monitorenter ;
        String s = mCurrentEventName;
        this;
        JVM INSTR monitorexit ;
        return s;
        Exception exception;
        exception;
        throw exception;
    }

    public ResourceUtil.ExpandedResource getResource()
    {
        return mResource;
    }

    void setCurrentEventName(String s)
    {
        this;
        JVM INSTR monitorenter ;
        mCurrentEventName = s;
        this;
        JVM INSTR monitorexit ;
        return;
        Exception exception;
        exception;
        throw exception;
    }

    static final String DEFAULT_RULE_NAME = "Unknown";
    private static final ObjectAndStatic DEFAULT_VALUE_AND_STATIC = new ObjectAndStatic(Types.getDefaultValue(), true);
    private static final int MAX_CACHE_SIZE = 0x100000;
    private final EventInfoDistributor eventInfoDistributor;
    private volatile String mCurrentEventName;
    private final Cache mFunctionCallCache;
    private final Cache mMacroEvaluationCache;
    private final Map mMacroLookup;
    private final Map mMacroMap;
    private final Map mPredicateMap;
    private final ResourceUtil.ExpandedResource mResource;
    private final Set mRules;
    private final Map mTrackingTagMap;

}
