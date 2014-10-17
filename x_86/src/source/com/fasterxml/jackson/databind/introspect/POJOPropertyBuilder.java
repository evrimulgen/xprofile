// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.util.BeanUtil;

// Referenced classes of package com.fasterxml.jackson.databind.introspect:
//            BeanPropertyDefinition, AnnotatedMember, AnnotationMap, ObjectIdInfo, 
//            AnnotatedParameter, AnnotatedConstructor, AnnotatedField, AnnotatedMethod

public class POJOPropertyBuilder extends BeanPropertyDefinition
    implements Comparable
{
    private static final class Linked
    {

        private Linked append(Linked linked)
        {
            if(next == null)
                return withNext(linked);
            else
                return withNext(next.append(linked));
        }

        public String toString()
        {
            String s = (new StringBuilder()).append(value.toString()).append("[visible=").append(isVisible).append("]").toString();
            if(next != null)
                s = (new StringBuilder()).append(s).append(", ").append(next.toString()).toString();
            return s;
        }

        public Linked trimByVisibility()
        {
            Linked linked;
            if(next == null)
            {
                linked = this;
            } else
            {
                linked = next.trimByVisibility();
                if(explicitName != null)
                    if(linked.explicitName == null)
                        return withNext(null);
                    else
                        return withNext(linked);
                if(linked.explicitName == null)
                {
                    if(isVisible == linked.isVisible)
                        return withNext(linked);
                    if(isVisible)
                        return withNext(null);
                }
            }
            return linked;
        }

        public Linked withNext(Linked linked)
        {
            if(linked == next)
                return this;
            else
                return new Linked(value, linked, explicitName, isVisible, isMarkedIgnored);
        }

        public Linked withValue(Object obj)
        {
            if(obj == value)
                return this;
            else
                return new Linked(obj, next, explicitName, isVisible, isMarkedIgnored);
        }

        public Linked withoutIgnored()
        {
            if(isMarkedIgnored)
                if(next == null)
                    return null;
                else
                    return next.withoutIgnored();
            if(next != null)
            {
                Linked linked = next.withoutIgnored();
                if(linked != next)
                    return withNext(linked);
            }
            return this;
        }

        public Linked withoutNonVisible()
        {
            Linked linked;
            if(next == null)
                linked = null;
            else
                linked = next.withoutNonVisible();
            if(isVisible)
                linked = withNext(linked);
            return linked;
        }

        public final String explicitName;
        public final boolean isMarkedIgnored;
        public final boolean isVisible;
        public final Linked next;
        public final Object value;


        public Linked(Object obj, Linked linked, String s, boolean flag, boolean flag1)
        {
            value = obj;
            next = linked;
            if(s == null)
            {
                explicitName = null;
            } else
            {
                if(s.length() == 0)
                    s = null;
                explicitName = s;
            }
            isVisible = flag;
            isMarkedIgnored = flag1;
        }
    }

    private static interface WithMember
    {

        public abstract Object withMember(AnnotatedMember annotatedmember);
    }


    public POJOPropertyBuilder(PropertyName propertyname, AnnotationIntrospector annotationintrospector, boolean flag)
    {
        _internalName = propertyname;
        _name = propertyname;
        _annotationIntrospector = annotationintrospector;
        _forSerialization = flag;
    }

    public POJOPropertyBuilder(POJOPropertyBuilder pojopropertybuilder, PropertyName propertyname)
    {
        _internalName = pojopropertybuilder._internalName;
        _name = propertyname;
        _annotationIntrospector = pojopropertybuilder._annotationIntrospector;
        _fields = pojopropertybuilder._fields;
        _ctorParameters = pojopropertybuilder._ctorParameters;
        _getters = pojopropertybuilder._getters;
        _setters = pojopropertybuilder._setters;
        _forSerialization = pojopropertybuilder._forSerialization;
    }

    public POJOPropertyBuilder(String s, AnnotationIntrospector annotationintrospector, boolean flag)
    {
        this(new PropertyName(s), annotationintrospector, flag);
    }

    private boolean _anyExplicitNames(Linked linked)
    {
        for(; linked != null; linked = linked.next)
            if(linked.explicitName != null && linked.explicitName.length() > 0)
                return true;

        return false;
    }

    private boolean _anyIgnorals(Linked linked)
    {
        for(; linked != null; linked = linked.next)
            if(linked.isMarkedIgnored)
                return true;

        return false;
    }

    private boolean _anyVisible(Linked linked)
    {
        for(; linked != null; linked = linked.next)
            if(linked.isVisible)
                return true;

        return false;
    }

    private transient AnnotationMap _mergeAnnotations(int i, Linked alinked[])
    {
        AnnotationMap annotationmap = ((AnnotatedMember)alinked[i].value).getAllAnnotations();
        for(int j = i + 1; j < alinked.length; j++)
            if(alinked[j] != null)
                return AnnotationMap.merge(annotationmap, _mergeAnnotations(j, alinked));

        return annotationmap;
    }

    private Linked _removeIgnored(Linked linked)
    {
        if(linked == null)
            return linked;
        else
            return linked.withoutIgnored();
    }

    private Linked _removeNonVisible(Linked linked)
    {
        if(linked == null)
            return linked;
        else
            return linked.withoutNonVisible();
    }

    private Linked _trimByVisibility(Linked linked)
    {
        if(linked == null)
            return linked;
        else
            return linked.trimByVisibility();
    }

    private Linked findRenamed(Linked linked, Linked linked1)
    {
        Linked linked2;
        Linked linked3;
        String s;
        linked2 = linked1;
        linked3 = linked;
        break MISSING_BLOCK_LABEL_5;
_L3:
        linked3 = linked3.next;
        if(true) goto _L2; else goto _L1
_L2:
        if(linked3 == null)
            break; /* Loop/switch isn't completed */
        s = linked3.explicitName;
        if(s != null && !s.equals(_name))
        {
            if(linked2 != null)
                continue; /* Loop/switch isn't completed */
            linked2 = linked3;
        }
          goto _L3
        if(s.equals(linked2.explicitName)) goto _L3; else goto _L4
_L4:
        throw new IllegalStateException((new StringBuilder()).append("Conflicting property name definitions: '").append(linked2.explicitName).append("' (for ").append(linked2.value).append(") vs '").append(linked3.explicitName).append("' (for ").append(linked3.value).append(")").toString());
_L1:
        return linked2;
    }

    private static Linked merge(Linked linked, Linked linked1)
    {
        if(linked == null)
            return linked1;
        if(linked1 == null)
            return linked;
        else
            return linked.append(linked1);
    }

    protected String _findDescription()
    {
        return (String)fromMemberAnnotations(new WithMember() {

            public volatile Object withMember(AnnotatedMember annotatedmember)
            {
                return withMember(annotatedmember);
            }

            public String withMember(AnnotatedMember annotatedmember)
            {
                return _annotationIntrospector.findPropertyDescription(annotatedmember);
            }

            final POJOPropertyBuilder this$0;

            
            {
                this$0 = POJOPropertyBuilder.this;
                super();
            }
        }
);
    }

    protected Boolean _findRequired()
    {
        return (Boolean)fromMemberAnnotations(new WithMember() {

            public Boolean withMember(AnnotatedMember annotatedmember)
            {
                return _annotationIntrospector.hasRequiredMarker(annotatedmember);
            }

            public volatile Object withMember(AnnotatedMember annotatedmember)
            {
                return withMember(annotatedmember);
            }

            final POJOPropertyBuilder this$0;

            
            {
                this$0 = POJOPropertyBuilder.this;
                super();
            }
        }
);
    }

    public void addAll(POJOPropertyBuilder pojopropertybuilder)
    {
        _fields = merge(_fields, pojopropertybuilder._fields);
        _ctorParameters = merge(_ctorParameters, pojopropertybuilder._ctorParameters);
        _getters = merge(_getters, pojopropertybuilder._getters);
        _setters = merge(_setters, pojopropertybuilder._setters);
    }

    public void addCtor(AnnotatedParameter annotatedparameter, String s, boolean flag, boolean flag1)
    {
        _ctorParameters = new Linked(annotatedparameter, _ctorParameters, s, flag, flag1);
    }

    public void addField(AnnotatedField annotatedfield, String s, boolean flag, boolean flag1)
    {
        _fields = new Linked(annotatedfield, _fields, s, flag, flag1);
    }

    public void addGetter(AnnotatedMethod annotatedmethod, String s, boolean flag, boolean flag1)
    {
        _getters = new Linked(annotatedmethod, _getters, s, flag, flag1);
    }

    public void addSetter(AnnotatedMethod annotatedmethod, String s, boolean flag, boolean flag1)
    {
        _setters = new Linked(annotatedmethod, _setters, s, flag, flag1);
    }

    public boolean anyIgnorals()
    {
        return _anyIgnorals(_fields) || _anyIgnorals(_getters) || _anyIgnorals(_setters) || _anyIgnorals(_ctorParameters);
    }

    public boolean anyVisible()
    {
        return _anyVisible(_fields) || _anyVisible(_getters) || _anyVisible(_setters) || _anyVisible(_ctorParameters);
    }

    public int compareTo(POJOPropertyBuilder pojopropertybuilder)
    {
        if(_ctorParameters != null)
        {
            if(pojopropertybuilder._ctorParameters == null)
                return -1;
        } else
        if(pojopropertybuilder._ctorParameters != null)
            return 1;
        return getName().compareTo(pojopropertybuilder.getName());
    }

    public volatile int compareTo(Object obj)
    {
        return compareTo((POJOPropertyBuilder)obj);
    }

    public boolean couldSerialize()
    {
        return _getters != null || _fields != null;
    }

    public String findNewName()
    {
        Linked linked = findRenamed(_fields, null);
        Linked linked1 = findRenamed(_getters, linked);
        Linked linked2 = findRenamed(_setters, linked1);
        Linked linked3 = findRenamed(_ctorParameters, linked2);
        if(linked3 == null)
            return null;
        else
            return linked3.explicitName;
    }

    public ObjectIdInfo findObjectIdInfo()
    {
        return (ObjectIdInfo)fromMemberAnnotations(new WithMember() {

            public ObjectIdInfo withMember(AnnotatedMember annotatedmember)
            {
                ObjectIdInfo objectidinfo = _annotationIntrospector.findObjectIdInfo(annotatedmember);
                if(objectidinfo != null)
                    objectidinfo = _annotationIntrospector.findObjectReferenceInfo(annotatedmember, objectidinfo);
                return objectidinfo;
            }

            public volatile Object withMember(AnnotatedMember annotatedmember)
            {
                return withMember(annotatedmember);
            }

            final POJOPropertyBuilder this$0;

            
            {
                this$0 = POJOPropertyBuilder.this;
                super();
            }
        }
);
    }

    public com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty findReferenceType()
    {
        return (com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty)fromMemberAnnotations(new WithMember() {

            public com.fasterxml.jackson.databind.AnnotationIntrospector.ReferenceProperty withMember(AnnotatedMember annotatedmember)
            {
                return _annotationIntrospector.findReferenceType(annotatedmember);
            }

            public volatile Object withMember(AnnotatedMember annotatedmember)
            {
                return withMember(annotatedmember);
            }

            final POJOPropertyBuilder this$0;

            
            {
                this$0 = POJOPropertyBuilder.this;
                super();
            }
        }
);
    }

    public Class[] findViews()
    {
        return (Class[])fromMemberAnnotations(new WithMember() {

            public volatile Object withMember(AnnotatedMember annotatedmember)
            {
                return withMember(annotatedmember);
            }

            public Class[] withMember(AnnotatedMember annotatedmember)
            {
                return _annotationIntrospector.findViews(annotatedmember);
            }

            final POJOPropertyBuilder this$0;

            
            {
                this$0 = POJOPropertyBuilder.this;
                super();
            }
        }
);
    }

    protected Object fromMemberAnnotations(WithMember withmember)
    {
        AnnotationIntrospector annotationintrospector;
        Object obj;
        annotationintrospector = _annotationIntrospector;
        obj = null;
        if(annotationintrospector == null) goto _L2; else goto _L1
_L1:
        if(!_forSerialization) goto _L4; else goto _L3
_L3:
        Linked linked1 = _getters;
        obj = null;
        if(linked1 != null)
            obj = withmember.withMember((AnnotatedMember)_getters.value);
_L6:
        if(obj == null && _fields != null)
            obj = withmember.withMember((AnnotatedMember)_fields.value);
_L2:
        return obj;
_L4:
        Linked linked = _ctorParameters;
        obj = null;
        if(linked != null)
            obj = withmember.withMember((AnnotatedMember)_ctorParameters.value);
        if(obj == null && _setters != null)
            obj = withmember.withMember((AnnotatedMember)_setters.value);
        if(true) goto _L6; else goto _L5
_L5:
    }

    public AnnotatedMember getAccessor()
    {
        Object obj = getGetter();
        if(obj == null)
            obj = getField();
        return ((AnnotatedMember) (obj));
    }

    public AnnotatedParameter getConstructorParameter()
    {
        if(_ctorParameters == null)
            return null;
        Linked linked = _ctorParameters;
        do
        {
            if(((AnnotatedParameter)linked.value).getOwner() instanceof AnnotatedConstructor)
                return (AnnotatedParameter)linked.value;
            Linked linked1 = linked.next;
            if(linked1 == null)
                return (AnnotatedParameter)_ctorParameters.value;
            linked = linked1;
        } while(true);
    }

    public AnnotatedField getField()
    {
        AnnotatedField annotatedfield1;
        AnnotatedField annotatedfield2;
        if(_fields == null)
        {
            annotatedfield1 = null;
        } else
        {
            AnnotatedField annotatedfield = (AnnotatedField)_fields.value;
            Linked linked = _fields.next;
            annotatedfield1 = annotatedfield;
            while(linked != null) 
            {
label0:
                {
                    annotatedfield2 = (AnnotatedField)linked.value;
                    Class class1 = annotatedfield1.getDeclaringClass();
                    Class class2 = annotatedfield2.getDeclaringClass();
                    if(class1 == class2)
                        break label0;
                    if(!class1.isAssignableFrom(class2))
                    {
                        if(!class2.isAssignableFrom(class1))
                            break label0;
                        annotatedfield2 = annotatedfield1;
                    }
                    linked = linked.next;
                    annotatedfield1 = annotatedfield2;
                }
            }
        }
        return annotatedfield1;
        throw new IllegalArgumentException((new StringBuilder()).append("Multiple fields representing property \"").append(getName()).append("\": ").append(annotatedfield1.getFullName()).append(" vs ").append(annotatedfield2.getFullName()).toString());
    }

    public PropertyName getFullName()
    {
        return _name;
    }

    public AnnotatedMethod getGetter()
    {
        if(_getters != null) goto _L2; else goto _L1
_L1:
        AnnotatedMethod annotatedmethod1 = null;
_L4:
        return annotatedmethod1;
_L2:
        Linked linked;
        AnnotatedMethod annotatedmethod = (AnnotatedMethod)_getters.value;
        linked = _getters.next;
        annotatedmethod1 = annotatedmethod;
_L5:
        if(linked == null) goto _L4; else goto _L3
_L3:
        AnnotatedMethod annotatedmethod2;
label0:
        {
            annotatedmethod2 = (AnnotatedMethod)linked.value;
            Class class1 = annotatedmethod1.getDeclaringClass();
            Class class2 = annotatedmethod2.getDeclaringClass();
            if(class1 == class2)
                break label0;
            if(!class1.isAssignableFrom(class2))
            {
                if(!class2.isAssignableFrom(class1))
                    break label0;
                annotatedmethod2 = annotatedmethod1;
            }
        }
_L6:
        linked = linked.next;
        annotatedmethod1 = annotatedmethod2;
          goto _L5
          goto _L4
        boolean flag;
        boolean flag1;
        if(BeanUtil.okNameForIsGetter(annotatedmethod1, annotatedmethod1.getName()) != null)
            flag = true;
        else
            flag = false;
        if(BeanUtil.okNameForIsGetter(annotatedmethod2, annotatedmethod2.getName()) != null)
            flag1 = true;
        else
            flag1 = false;
        if(flag != flag1)
        {
            if(!flag)
                annotatedmethod2 = annotatedmethod1;
        } else
        {
            throw new IllegalArgumentException((new StringBuilder()).append("Conflicting getter definitions for property \"").append(getName()).append("\": ").append(annotatedmethod1.getFullName()).append(" vs ").append(annotatedmethod2.getFullName()).toString());
        }
          goto _L6
    }

    public String getInternalName()
    {
        return _internalName.getSimpleName();
    }

    public PropertyMetadata getMetadata()
    {
        Boolean boolean1 = _findRequired();
        String s = _findDescription();
        if(boolean1 == null)
        {
            if(s == null)
                return PropertyMetadata.STD_REQUIRED_OR_OPTIONAL;
            else
                return PropertyMetadata.STD_REQUIRED_OR_OPTIONAL.withDescription(s);
        } else
        {
            return PropertyMetadata.construct(boolean1.booleanValue(), _findDescription());
        }
    }

    public AnnotatedMember getMutator()
    {
        Object obj = getConstructorParameter();
        if(obj == null)
        {
            obj = getSetter();
            if(obj == null)
                obj = getField();
        }
        return ((AnnotatedMember) (obj));
    }

    public String getName()
    {
        if(_name == null)
            return null;
        else
            return _name.getSimpleName();
    }

    public AnnotatedMember getNonConstructorMutator()
    {
        Object obj = getSetter();
        if(obj == null)
            obj = getField();
        return ((AnnotatedMember) (obj));
    }

    public AnnotatedMember getPrimaryMember()
    {
        if(_forSerialization)
            return getAccessor();
        else
            return getMutator();
    }

    public AnnotatedMethod getSetter()
    {
        AnnotatedMethod annotatedmethod1;
        AnnotatedMethod annotatedmethod2;
        if(_setters == null)
        {
            annotatedmethod1 = null;
        } else
        {
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)_setters.value;
            Linked linked = _setters.next;
            annotatedmethod1 = annotatedmethod;
            while(linked != null) 
            {
label0:
                {
                    annotatedmethod2 = (AnnotatedMethod)linked.value;
                    Class class1 = annotatedmethod1.getDeclaringClass();
                    Class class2 = annotatedmethod2.getDeclaringClass();
                    if(class1 == class2)
                        break label0;
                    if(!class1.isAssignableFrom(class2))
                    {
                        if(!class2.isAssignableFrom(class1))
                            break label0;
                        annotatedmethod2 = annotatedmethod1;
                    }
                    linked = linked.next;
                    annotatedmethod1 = annotatedmethod2;
                }
            }
        }
        return annotatedmethod1;
        throw new IllegalArgumentException((new StringBuilder()).append("Conflicting setter definitions for property \"").append(getName()).append("\": ").append(annotatedmethod1.getFullName()).append(" vs ").append(annotatedmethod2.getFullName()).toString());
    }

    public PropertyName getWrapperName()
    {
        AnnotatedMember annotatedmember = getPrimaryMember();
        if(annotatedmember == null || _annotationIntrospector == null)
            return null;
        else
            return _annotationIntrospector.findWrapperName(annotatedmember);
    }

    public boolean hasConstructorParameter()
    {
        return _ctorParameters != null;
    }

    public boolean hasField()
    {
        return _fields != null;
    }

    public boolean hasGetter()
    {
        return _getters != null;
    }

    public boolean hasSetter()
    {
        return _setters != null;
    }

    public boolean isExplicitlyIncluded()
    {
        return _anyExplicitNames(_fields) || _anyExplicitNames(_getters) || _anyExplicitNames(_setters) || _anyExplicitNames(_ctorParameters);
    }

    public boolean isTypeId()
    {
        Boolean boolean1 = (Boolean)fromMemberAnnotations(new WithMember() {

            public Boolean withMember(AnnotatedMember annotatedmember)
            {
                return _annotationIntrospector.isTypeId(annotatedmember);
            }

            public volatile Object withMember(AnnotatedMember annotatedmember)
            {
                return withMember(annotatedmember);
            }

            final POJOPropertyBuilder this$0;

            
            {
                this$0 = POJOPropertyBuilder.this;
                super();
            }
        }
);
        return boolean1 != null && boolean1.booleanValue();
    }

    public void mergeAnnotations(boolean flag)
    {
        if(!flag) goto _L2; else goto _L1
_L1:
        if(_getters == null) goto _L4; else goto _L3
_L3:
        Linked alinked4[] = new Linked[4];
        alinked4[0] = _getters;
        alinked4[1] = _fields;
        alinked4[2] = _ctorParameters;
        alinked4[3] = _setters;
        AnnotationMap annotationmap4 = _mergeAnnotations(0, alinked4);
        _getters = _getters.withValue(((AnnotatedMethod)_getters.value).withAnnotations(annotationmap4));
_L6:
        return;
_L4:
        if(_fields != null)
        {
            Linked alinked3[] = new Linked[3];
            alinked3[0] = _fields;
            alinked3[1] = _ctorParameters;
            alinked3[2] = _setters;
            AnnotationMap annotationmap3 = _mergeAnnotations(0, alinked3);
            _fields = _fields.withValue(((AnnotatedField)_fields.value).withAnnotations(annotationmap3));
            return;
        }
        continue; /* Loop/switch isn't completed */
_L2:
        if(_ctorParameters != null)
        {
            Linked alinked2[] = new Linked[4];
            alinked2[0] = _ctorParameters;
            alinked2[1] = _setters;
            alinked2[2] = _fields;
            alinked2[3] = _getters;
            AnnotationMap annotationmap2 = _mergeAnnotations(0, alinked2);
            _ctorParameters = _ctorParameters.withValue(((AnnotatedParameter)_ctorParameters.value).withAnnotations(annotationmap2));
            return;
        }
        if(_setters != null)
        {
            Linked alinked1[] = new Linked[3];
            alinked1[0] = _setters;
            alinked1[1] = _fields;
            alinked1[2] = _getters;
            AnnotationMap annotationmap1 = _mergeAnnotations(0, alinked1);
            _setters = _setters.withValue(((AnnotatedMethod)_setters.value).withAnnotations(annotationmap1));
            return;
        }
        if(_fields != null)
        {
            Linked alinked[] = new Linked[2];
            alinked[0] = _fields;
            alinked[1] = _getters;
            AnnotationMap annotationmap = _mergeAnnotations(0, alinked);
            _fields = _fields.withValue(((AnnotatedField)_fields.value).withAnnotations(annotationmap));
            return;
        }
        if(true) goto _L6; else goto _L5
_L5:
    }

    public void removeIgnored()
    {
        _fields = _removeIgnored(_fields);
        _getters = _removeIgnored(_getters);
        _setters = _removeIgnored(_setters);
        _ctorParameters = _removeIgnored(_ctorParameters);
    }

    public void removeNonVisible()
    {
        removeNonVisible(false);
    }

    public void removeNonVisible(boolean flag)
    {
        _getters = _removeNonVisible(_getters);
        _ctorParameters = _removeNonVisible(_ctorParameters);
        if(flag || _getters == null)
        {
            _fields = _removeNonVisible(_fields);
            _setters = _removeNonVisible(_setters);
        }
    }

    public String toString()
    {
        StringBuilder stringbuilder = new StringBuilder();
        stringbuilder.append("[Property '").append(_name).append("'; ctors: ").append(_ctorParameters).append(", field(s): ").append(_fields).append(", getter(s): ").append(_getters).append(", setter(s): ").append(_setters);
        stringbuilder.append("]");
        return stringbuilder.toString();
    }

    public void trimByVisibility()
    {
        _fields = _trimByVisibility(_fields);
        _getters = _trimByVisibility(_getters);
        _setters = _trimByVisibility(_setters);
        _ctorParameters = _trimByVisibility(_ctorParameters);
    }

    public volatile BeanPropertyDefinition withName(PropertyName propertyname)
    {
        return withName(propertyname);
    }

    public volatile BeanPropertyDefinition withName(String s)
    {
        return withName(s);
    }

    public POJOPropertyBuilder withName(PropertyName propertyname)
    {
        return new POJOPropertyBuilder(this, propertyname);
    }

    public POJOPropertyBuilder withName(String s)
    {
        return withSimpleName(s);
    }

    public volatile BeanPropertyDefinition withSimpleName(String s)
    {
        return withSimpleName(s);
    }

    public POJOPropertyBuilder withSimpleName(String s)
    {
        PropertyName propertyname = _name.withSimpleName(s);
        if(propertyname == _name)
            return this;
        else
            return new POJOPropertyBuilder(this, propertyname);
    }

    protected final AnnotationIntrospector _annotationIntrospector;
    protected Linked _ctorParameters;
    protected Linked _fields;
    protected final boolean _forSerialization;
    protected Linked _getters;
    protected final PropertyName _internalName;
    protected final PropertyName _name;
    protected Linked _setters;
}
