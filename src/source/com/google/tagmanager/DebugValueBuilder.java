// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.google.tagmanager;


// Referenced classes of package com.google.tagmanager:
//            ValueBuilder, Log, DebugMacroEvaluationInfoBuilder, MacroEvaluationInfoBuilder

class DebugValueBuilder
    implements ValueBuilder
{
    private static class TypeMismatchException extends IllegalStateException
    {

        public TypeMismatchException(String s, com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.Type type)
        {
            super((new StringBuilder()).append("Attempted operation: ").append(s).append(" on object of type: ").append(type).toString());
        }
    }


    public DebugValueBuilder(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value1)
    {
        value = value1;
    }

    public static com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value copyImmutableValue(com.google.analytics.midtier.proto.containertag.TypeSystem.Value value1)
    {
        com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value2 = com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.newMessage();
        if(!value2.mergeFrom(value1.toByteArray()))
            Log.e("Failed to copy runtime value into debug value");
        return value2;
    }

    private void validateType(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.Type type, com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.Type type1, String s)
    {
        if(!type.equals(type1))
            throw new TypeMismatchException(s, type1);
        else
            return;
    }

    public MacroEvaluationInfoBuilder createValueMacroEvaluationInfoExtension()
    {
        validateType(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.Type.MACRO_REFERENCE, value.getType(), "set macro evaluation extension");
        return new DebugMacroEvaluationInfoBuilder((com.google.analytics.containertag.proto.MutableDebug.MacroEvaluationInfo)value.getMutableExtension(com.google.analytics.containertag.proto.MutableDebug.MacroEvaluationInfo.macro));
    }

    public ValueBuilder getListItem(int i)
    {
        validateType(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.Type.LIST, value.getType(), "add new list item");
        return new DebugValueBuilder(value.getListItem(i));
    }

    public ValueBuilder getMapKey(int i)
    {
        validateType(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.Type.MAP, value.getType(), "add new map key");
        return new DebugValueBuilder(value.getMapKey(i));
    }

    public ValueBuilder getMapValue(int i)
    {
        validateType(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.Type.MAP, value.getType(), "add new map value");
        return new DebugValueBuilder(value.getMapValue(i));
    }

    public ValueBuilder getTemplateToken(int i)
    {
        validateType(com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value.Type.TEMPLATE, value.getType(), "add template token");
        return new DebugValueBuilder(value.getTemplateToken(i));
    }

    private com.google.analytics.midtier.proto.containertag.MutableTypeSystem.Value value;
}
