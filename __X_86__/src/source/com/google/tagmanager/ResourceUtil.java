// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;

import com.google.analytics.containertag.common.Key;
import java.util.*;

// Referenced classes of package com.google.tagmanager:
//            Types, Log

class ResourceUtil
{
    public static class ExpandedFunctionCall
    {

        public static ExpandedFunctionCallBuilder newBuilder()
        {
            return new ExpandedFunctionCallBuilder();
        }

        public Map getProperties()
        {
            return Collections.unmodifiableMap(mPropertiesMap);
        }

        public String toString()
        {
            return (new StringBuilder()).append("Properties: ").append(getProperties()).toString();
        }

        public void updateCacheableProperty(String s, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            mPropertiesMap.put(s, value);
        }

        private final Map mPropertiesMap;

        private ExpandedFunctionCall(Map map)
        {
            mPropertiesMap = map;
        }

    }

    public static class ExpandedFunctionCallBuilder
    {

        public ExpandedFunctionCallBuilder addProperty(String s, com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        {
            mPropertiesMap.put(s, value);
            return this;
        }

        public ExpandedFunctionCall build()
        {
            return new ExpandedFunctionCall(mPropertiesMap);
        }

        private final Map mPropertiesMap;

        private ExpandedFunctionCallBuilder()
        {
            mPropertiesMap = new HashMap();
        }

    }

    public static class ExpandedResource
    {

        public static ExpandedResourceBuilder newBuilder()
        {
            return new ExpandedResourceBuilder();
        }

        public Map getAllMacros()
        {
            return mMacros;
        }

        public List getMacros(String s)
        {
            return (List)mMacros.get(s);
        }

        public int getResourceFormatVersion()
        {
            return mResourceFormatVersion;
        }

        public List getRules()
        {
            return mRules;
        }

        public String getVersion()
        {
            return mVersion;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Rules: ").append(getRules()).append("  Macros: ").append(mMacros).toString();
        }

        private final Map mMacros;
        private final int mResourceFormatVersion;
        private final List mRules;
        private final String mVersion;

        private ExpandedResource(List list, Map map, String s, int i)
        {
            mRules = Collections.unmodifiableList(list);
            mMacros = Collections.unmodifiableMap(map);
            mVersion = s;
            mResourceFormatVersion = i;
        }

    }

    public static class ExpandedResourceBuilder
    {

        public ExpandedResourceBuilder addMacro(ExpandedFunctionCall expandedfunctioncall)
        {
            String s = Types.valueToString((com.google.analytics.midtier.proto.containertag.TypeSystem.Value)expandedfunctioncall.getProperties().get(Key.INSTANCE_NAME.toString()));
            Object obj = (List)mMacros.get(s);
            if(obj == null)
            {
                obj = new ArrayList();
                mMacros.put(s, obj);
            }
            ((List) (obj)).add(expandedfunctioncall);
            return this;
        }

        public ExpandedResourceBuilder addRule(ExpandedRule expandedrule)
        {
            mRules.add(expandedrule);
            return this;
        }

        public ExpandedResource build()
        {
            return new ExpandedResource(mRules, mMacros, mVersion, mResourceFormatVersion);
        }

        public ExpandedResourceBuilder setResourceFormatVersion(int i)
        {
            mResourceFormatVersion = i;
            return this;
        }

        public ExpandedResourceBuilder setVersion(String s)
        {
            mVersion = s;
            return this;
        }

        private final Map mMacros;
        private final List mPredicates;
        private int mResourceFormatVersion;
        private final List mRules;
        private final List mTags;
        private String mVersion;

        private ExpandedResourceBuilder()
        {
            mRules = new ArrayList();
            mTags = new ArrayList();
            mPredicates = new ArrayList();
            mMacros = new HashMap();
            mVersion = "";
            mResourceFormatVersion = 0;
        }

    }

    public static class ExpandedRule
    {

        public static ExpandedRuleBuilder newBuilder()
        {
            return new ExpandedRuleBuilder();
        }

        public List getAddMacroRuleNames()
        {
            return mAddMacroRuleNames;
        }

        public List getAddMacros()
        {
            return mAddMacros;
        }

        public List getAddTagRuleNames()
        {
            return mAddTagRuleNames;
        }

        public List getAddTags()
        {
            return mAddTags;
        }

        public List getNegativePredicates()
        {
            return mNegativePredicates;
        }

        public List getPositivePredicates()
        {
            return mPositivePredicates;
        }

        public List getRemoveMacroRuleNames()
        {
            return mRemoveMacroRuleNames;
        }

        public List getRemoveMacros()
        {
            return mRemoveMacros;
        }

        public List getRemoveTagRuleNames()
        {
            return mRemoveTagRuleNames;
        }

        public List getRemoveTags()
        {
            return mRemoveTags;
        }

        public String toString()
        {
            return (new StringBuilder()).append("Positive predicates: ").append(getPositivePredicates()).append("  Negative predicates: ").append(getNegativePredicates()).append("  Add tags: ").append(getAddTags()).append("  Remove tags: ").append(getRemoveTags()).append("  Add macros: ").append(getAddMacros()).append("  Remove macros: ").append(getRemoveMacros()).toString();
        }

        private final List mAddMacroRuleNames;
        private final List mAddMacros;
        private final List mAddTagRuleNames;
        private final List mAddTags;
        private final List mNegativePredicates;
        private final List mPositivePredicates;
        private final List mRemoveMacroRuleNames;
        private final List mRemoveMacros;
        private final List mRemoveTagRuleNames;
        private final List mRemoveTags;

        private ExpandedRule(List list, List list1, List list2, List list3, List list4, List list5, List list6, 
                List list7, List list8, List list9)
        {
            mPositivePredicates = Collections.unmodifiableList(list);
            mNegativePredicates = Collections.unmodifiableList(list1);
            mAddTags = Collections.unmodifiableList(list2);
            mRemoveTags = Collections.unmodifiableList(list3);
            mAddMacros = Collections.unmodifiableList(list4);
            mRemoveMacros = Collections.unmodifiableList(list5);
            mAddMacroRuleNames = Collections.unmodifiableList(list6);
            mRemoveMacroRuleNames = Collections.unmodifiableList(list7);
            mAddTagRuleNames = Collections.unmodifiableList(list8);
            mRemoveTagRuleNames = Collections.unmodifiableList(list9);
        }

    }

    public static class ExpandedRuleBuilder
    {

        public ExpandedRuleBuilder addAddMacro(ExpandedFunctionCall expandedfunctioncall)
        {
            mAddMacros.add(expandedfunctioncall);
            return this;
        }

        public ExpandedRuleBuilder addAddMacroRuleName(String s)
        {
            mAddMacroRuleNames.add(s);
            return this;
        }

        public ExpandedRuleBuilder addAddTag(ExpandedFunctionCall expandedfunctioncall)
        {
            mAddTags.add(expandedfunctioncall);
            return this;
        }

        public ExpandedRuleBuilder addAddTagRuleName(String s)
        {
            mAddTagRuleNames.add(s);
            return this;
        }

        public ExpandedRuleBuilder addNegativePredicate(ExpandedFunctionCall expandedfunctioncall)
        {
            mNegativePredicates.add(expandedfunctioncall);
            return this;
        }

        public ExpandedRuleBuilder addPositivePredicate(ExpandedFunctionCall expandedfunctioncall)
        {
            mPositivePredicates.add(expandedfunctioncall);
            return this;
        }

        public ExpandedRuleBuilder addRemoveMacro(ExpandedFunctionCall expandedfunctioncall)
        {
            mRemoveMacros.add(expandedfunctioncall);
            return this;
        }

        public ExpandedRuleBuilder addRemoveMacroRuleName(String s)
        {
            mRemoveMacroRuleNames.add(s);
            return this;
        }

        public ExpandedRuleBuilder addRemoveTag(ExpandedFunctionCall expandedfunctioncall)
        {
            mRemoveTags.add(expandedfunctioncall);
            return this;
        }

        public ExpandedRuleBuilder addRemoveTagRuleName(String s)
        {
            mRemoveTagRuleNames.add(s);
            return this;
        }

        public ExpandedRule build()
        {
            return new ExpandedRule(mPositivePredicates, mNegativePredicates, mAddTags, mRemoveTags, mAddMacros, mRemoveMacros, mAddMacroRuleNames, mRemoveMacroRuleNames, mAddTagRuleNames, mRemoveTagRuleNames);
        }

        private final List mAddMacroRuleNames;
        private final List mAddMacros;
        private final List mAddTagRuleNames;
        private final List mAddTags;
        private final List mNegativePredicates;
        private final List mPositivePredicates;
        private final List mRemoveMacroRuleNames;
        private final List mRemoveMacros;
        private final List mRemoveTagRuleNames;
        private final List mRemoveTags;

        private ExpandedRuleBuilder()
        {
            mPositivePredicates = new ArrayList();
            mNegativePredicates = new ArrayList();
            mAddTags = new ArrayList();
            mRemoveTags = new ArrayList();
            mAddMacros = new ArrayList();
            mRemoveMacros = new ArrayList();
            mAddMacroRuleNames = new ArrayList();
            mRemoveMacroRuleNames = new ArrayList();
            mAddTagRuleNames = new ArrayList();
            mRemoveTagRuleNames = new ArrayList();
        }

    }

    public static class InvalidResourceException extends Exception
    {

        public InvalidResourceException(String s)
        {
            super(s);
        }
    }


    private ResourceUtil()
    {
    }

    private static ExpandedFunctionCall expandFunctionCall(com.google.analytics.containertag.proto.Serving.FunctionCall functioncall, com.google.analytics.containertag.proto.Serving.Resource resource, com.google.analytics.midtier.proto.containertag.TypeSystem.Value avalue[], int i)
        throws InvalidResourceException
    {
        ExpandedFunctionCallBuilder expandedfunctioncallbuilder = ExpandedFunctionCall.newBuilder();
        com.google.analytics.containertag.proto.Serving.Property property;
        for(Iterator iterator = functioncall.getPropertyList().iterator(); iterator.hasNext(); expandedfunctioncallbuilder.addProperty((String)getWithBoundsCheck(resource.getKeyList(), property.getKey(), "keys"), (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)getWithBoundsCheck(avalue, property.getValue(), "values")))
        {
            Integer integer = (Integer)iterator.next();
            property = (com.google.analytics.containertag.proto.Serving.Property)getWithBoundsCheck(resource.getPropertyList(), integer.intValue(), "properties");
        }

        return expandedfunctioncallbuilder.build();
    }

    private static ExpandedRule expandRule(com.google.analytics.containertag.proto.Serving.Rule rule, List list, List list1, List list2, com.google.analytics.containertag.proto.Serving.Resource resource)
    {
        ExpandedRuleBuilder expandedrulebuilder = ExpandedRule.newBuilder();
        for(Iterator iterator = rule.getPositivePredicateList().iterator(); iterator.hasNext(); expandedrulebuilder.addPositivePredicate((ExpandedFunctionCall)list2.get(((Integer)iterator.next()).intValue())));
        for(Iterator iterator1 = rule.getNegativePredicateList().iterator(); iterator1.hasNext(); expandedrulebuilder.addNegativePredicate((ExpandedFunctionCall)list2.get(((Integer)iterator1.next()).intValue())));
        for(Iterator iterator2 = rule.getAddTagList().iterator(); iterator2.hasNext(); expandedrulebuilder.addAddTag((ExpandedFunctionCall)list.get(((Integer)iterator2.next()).intValue())));
        for(Iterator iterator3 = rule.getAddTagRuleNameList().iterator(); iterator3.hasNext(); expandedrulebuilder.addAddTagRuleName(resource.getValue(((Integer)iterator3.next()).intValue()).getString()));
        for(Iterator iterator4 = rule.getRemoveTagList().iterator(); iterator4.hasNext(); expandedrulebuilder.addRemoveTag((ExpandedFunctionCall)list.get(((Integer)iterator4.next()).intValue())));
        for(Iterator iterator5 = rule.getRemoveTagRuleNameList().iterator(); iterator5.hasNext(); expandedrulebuilder.addRemoveTagRuleName(resource.getValue(((Integer)iterator5.next()).intValue()).getString()));
        for(Iterator iterator6 = rule.getAddMacroList().iterator(); iterator6.hasNext(); expandedrulebuilder.addAddMacro((ExpandedFunctionCall)list1.get(((Integer)iterator6.next()).intValue())));
        for(Iterator iterator7 = rule.getAddMacroRuleNameList().iterator(); iterator7.hasNext(); expandedrulebuilder.addAddMacroRuleName(resource.getValue(((Integer)iterator7.next()).intValue()).getString()));
        for(Iterator iterator8 = rule.getRemoveMacroList().iterator(); iterator8.hasNext(); expandedrulebuilder.addRemoveMacro((ExpandedFunctionCall)list1.get(((Integer)iterator8.next()).intValue())));
        for(Iterator iterator9 = rule.getRemoveMacroRuleNameList().iterator(); iterator9.hasNext(); expandedrulebuilder.addRemoveMacroRuleName(resource.getValue(((Integer)iterator9.next()).intValue()).getString()));
        return expandedrulebuilder.build();
    }

    private static com.google.analytics.midtier.proto.containertag.TypeSystem.Value expandValue(int i, com.google.analytics.containertag.proto.Serving.Resource resource, com.google.analytics.midtier.proto.containertag.TypeSystem.Value avalue[], Set set)
        throws InvalidResourceException
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value;
        int j;
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1;
        if(set.contains(Integer.valueOf(i)))
            logAndThrow((new StringBuilder()).append("Value cycle detected.  Current value reference: ").append(i).append(".").append("  Previous value references: ").append(set).append(".").toString());
        value = (com.google.analytics.midtier.proto.containertag.TypeSystem.Value)getWithBoundsCheck(resource.getValueList(), i, "values");
        if(avalue[i] != null)
            return avalue[i];
        set.add(Integer.valueOf(i));
        static class _cls1
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
                catch(NoSuchFieldError nosuchfielderror3) { }
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.STRING.ordinal()] = 5;
                }
                catch(NoSuchFieldError nosuchfielderror4) { }
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.FUNCTION_ID.ordinal()] = 6;
                }
                catch(NoSuchFieldError nosuchfielderror5) { }
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.INTEGER.ordinal()] = 7;
                }
                catch(NoSuchFieldError nosuchfielderror6) { }
                try
                {
                    $SwitchMap$com$google$analytics$midtier$proto$containertag$TypeSystem$Value$Type[com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type.BOOLEAN.ordinal()] = 8;
                }
                catch(NoSuchFieldError nosuchfielderror7)
                {
                    return;
                }
            }
        }

        j = _cls1..SwitchMap.com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Type[value.getType().ordinal()];
        value1 = null;
        j;
        JVM INSTR tableswitch 1 8: default 156
    //                   1 203
    //                   2 273
    //                   3 457
    //                   4 527
    //                   5 597
    //                   6 597
    //                   7 597
    //                   8 597;
           goto _L1 _L2 _L3 _L4 _L5 _L6 _L6 _L6 _L6
_L1:
        if(value1 == null)
            logAndThrow((new StringBuilder()).append("Invalid value: ").append(value).toString());
        avalue[i] = value1;
        set.remove(Integer.valueOf(i));
        return value1;
_L2:
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder3 = newValueBuilderBasedOnValue(value);
        for(Iterator iterator3 = getServingValue(value).getListItemList().iterator(); iterator3.hasNext(); builder3.addListItem(expandValue(((Integer)iterator3.next()).intValue(), resource, avalue, set)));
        value1 = builder3.build();
        continue; /* Loop/switch isn't completed */
_L3:
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder2 = newValueBuilderBasedOnValue(value);
        com.google.analytics.containertag.proto.Serving.ServingValue servingvalue1 = getServingValue(value);
        if(servingvalue1.getMapKeyCount() != servingvalue1.getMapValueCount())
            logAndThrow((new StringBuilder()).append("Uneven map keys (").append(servingvalue1.getMapKeyCount()).append(") and map values (").append(servingvalue1.getMapValueCount()).append(")").toString());
        for(Iterator iterator1 = servingvalue1.getMapKeyList().iterator(); iterator1.hasNext(); builder2.addMapKey(expandValue(((Integer)iterator1.next()).intValue(), resource, avalue, set)));
        for(Iterator iterator2 = servingvalue1.getMapValueList().iterator(); iterator2.hasNext(); builder2.addMapValue(expandValue(((Integer)iterator2.next()).intValue(), resource, avalue, set)));
        value1 = builder2.build();
        continue; /* Loop/switch isn't completed */
_L4:
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder1 = newValueBuilderBasedOnValue(value);
        com.google.analytics.containertag.proto.Serving.ServingValue servingvalue = getServingValue(value);
        String s;
        if(!servingvalue.hasMacroNameReference())
        {
            logAndThrow("Missing macro name reference");
            s = "";
        } else
        {
            s = Types.valueToString(expandValue(servingvalue.getMacroNameReference(), resource, avalue, set));
        }
        builder1.setMacroReference(s);
        value1 = builder1.build();
        continue; /* Loop/switch isn't completed */
_L5:
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder = newValueBuilderBasedOnValue(value);
        for(Iterator iterator = getServingValue(value).getTemplateTokenList().iterator(); iterator.hasNext(); builder.addTemplateToken(expandValue(((Integer)iterator.next()).intValue(), resource, avalue, set)));
        value1 = builder.build();
        continue; /* Loop/switch isn't completed */
_L6:
        value1 = value;
        if(true) goto _L1; else goto _L7
_L7:
    }

    public static ExpandedResource getExpandedResource(com.google.analytics.containertag.proto.Serving.Resource resource)
        throws InvalidResourceException
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value avalue[] = new com.google.analytics.midtier.proto.containertag.TypeSystem.Value[resource.getValueCount()];
        for(int i = 0; i < resource.getValueCount(); i++)
            expandValue(i, resource, avalue, new HashSet(0));

        ExpandedResourceBuilder expandedresourcebuilder = ExpandedResource.newBuilder();
        ArrayList arraylist = new ArrayList();
        for(int j = 0; j < resource.getTagCount(); j++)
            arraylist.add(expandFunctionCall(resource.getTag(j), resource, avalue, j));

        ArrayList arraylist1 = new ArrayList();
        for(int k = 0; k < resource.getPredicateCount(); k++)
            arraylist1.add(expandFunctionCall(resource.getPredicate(k), resource, avalue, k));

        ArrayList arraylist2 = new ArrayList();
        for(int l = 0; l < resource.getMacroCount(); l++)
        {
            ExpandedFunctionCall expandedfunctioncall = expandFunctionCall(resource.getMacro(l), resource, avalue, l);
            expandedresourcebuilder.addMacro(expandedfunctioncall);
            arraylist2.add(expandedfunctioncall);
        }

        for(Iterator iterator = resource.getRuleList().iterator(); iterator.hasNext(); expandedresourcebuilder.addRule(expandRule((com.google.analytics.containertag.proto.Serving.Rule)iterator.next(), arraylist, arraylist2, arraylist1, resource)));
        expandedresourcebuilder.setVersion(resource.getVersion());
        expandedresourcebuilder.setResourceFormatVersion(resource.getResourceFormatVersion());
        return expandedresourcebuilder.build();
    }

    private static com.google.analytics.containertag.proto.Serving.ServingValue getServingValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
        throws InvalidResourceException
    {
        if(!value.hasExtension(com.google.analytics.containertag.proto.Serving.ServingValue.ext))
            logAndThrow((new StringBuilder()).append("Expected a ServingValue and didn't get one. Value is: ").append(value).toString());
        return (com.google.analytics.containertag.proto.Serving.ServingValue)value.getExtension(com.google.analytics.containertag.proto.Serving.ServingValue.ext);
    }

    private static Object getWithBoundsCheck(List list, int i, String s)
        throws InvalidResourceException
    {
        if(i < 0 || i >= list.size())
            logAndThrow((new StringBuilder()).append("Index out of bounds detected: ").append(i).append(" in ").append(s).toString());
        return list.get(i);
    }

    private static Object getWithBoundsCheck(Object aobj[], int i, String s)
        throws InvalidResourceException
    {
        if(i < 0 || i >= aobj.length)
            logAndThrow((new StringBuilder()).append("Index out of bounds detected: ").append(i).append(" in ").append(s).toString());
        return aobj[i];
    }

    private static void logAndThrow(String s)
        throws InvalidResourceException
    {
        Log.e(s);
        throw new InvalidResourceException(s);
    }

    public static com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder newValueBuilderBasedOnValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value)
    {
        com.google.analytics.midtier.proto.containertag.TypeSystem.Value.Builder builder = com.google.analytics.midtier.proto.containertag.TypeSystem.Value.newBuilder().setType(value.getType()).addAllEscaping(value.getEscapingList());
        if(value.getContainsReferences())
            builder.setContainsReferences(true);
        return builder;
    }
}
