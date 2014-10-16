// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.cfg.HandlerInstantiator;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.fasterxml.jackson.databind.util.ClassUtil;
import java.lang.reflect.Modifier;
import java.util.*;

// Referenced classes of package com.fasterxml.jackson.databind.introspect:
//            AnnotatedClass, AnnotatedConstructor, POJOPropertyBuilder, AnnotatedMethod, 
//            AnnotatedField, VisibilityChecker, AnnotatedMember, ObjectIdInfo

public class POJOPropertiesCollector
{

    protected POJOPropertiesCollector(MapperConfig mapperconfig, boolean flag, JavaType javatype, AnnotatedClass annotatedclass, String s)
    {
        _creatorProperties = null;
        _anyGetters = null;
        _anySetters = null;
        _jsonValueGetters = null;
        _config = mapperconfig;
        _forSerialization = flag;
        _type = javatype;
        _classDef = annotatedclass;
        if(s == null)
            s = "set";
        _mutatorPrefix = s;
        boolean flag1 = mapperconfig.isAnnotationProcessingEnabled();
        AnnotationIntrospector annotationintrospector = null;
        if(flag1)
            annotationintrospector = _config.getAnnotationIntrospector();
        _annotationIntrospector = annotationintrospector;
        if(_annotationIntrospector == null)
        {
            _visibilityChecker = _config.getDefaultVisibilityChecker();
            return;
        } else
        {
            _visibilityChecker = _annotationIntrospector.findAutoDetectVisibility(annotatedclass, _config.getDefaultVisibilityChecker());
            return;
        }
    }

    private void _addIgnored(String s)
    {
        if(!_forSerialization)
        {
            if(_ignoredPropertyNames == null)
                _ignoredPropertyNames = new HashSet();
            _ignoredPropertyNames.add(s);
        }
    }

    private PropertyNamingStrategy _findNamingStrategy()
    {
        Object obj;
        if(_annotationIntrospector == null)
            obj = null;
        else
            obj = _annotationIntrospector.findNamingStrategy(_classDef);
        if(obj == null)
            return _config.getPropertyNamingStrategy();
        if(obj instanceof PropertyNamingStrategy)
            return (PropertyNamingStrategy)obj;
        if(!(obj instanceof Class))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned PropertyNamingStrategy definition of type ").append(obj.getClass().getName()).append("; expected type PropertyNamingStrategy or Class<PropertyNamingStrategy> instead").toString());
        Class class1 = (Class)obj;
        if(!com/fasterxml/jackson/databind/PropertyNamingStrategy.isAssignableFrom(class1))
            throw new IllegalStateException((new StringBuilder()).append("AnnotationIntrospector returned Class ").append(class1.getName()).append("; expected Class<PropertyNamingStrategy>").toString());
        HandlerInstantiator handlerinstantiator = _config.getHandlerInstantiator();
        if(handlerinstantiator != null)
        {
            PropertyNamingStrategy propertynamingstrategy = handlerinstantiator.namingStrategyInstance(_config, _classDef, class1);
            if(propertynamingstrategy != null)
                return propertynamingstrategy;
        }
        return (PropertyNamingStrategy)ClassUtil.createInstance(class1, _config.canOverrideAccessModifiers());
    }

    protected void _addCreators()
    {
        AnnotationIntrospector annotationintrospector = _annotationIntrospector;
        if(annotationintrospector != null)
        {
            for(Iterator iterator = _classDef.getConstructors().iterator(); iterator.hasNext();)
            {
                AnnotatedConstructor annotatedconstructor = (AnnotatedConstructor)iterator.next();
                if(_creatorProperties == null)
                    _creatorProperties = new LinkedList();
                int k = annotatedconstructor.getParameterCount();
                int l = 0;
                while(l < k) 
                {
                    AnnotatedParameter annotatedparameter1 = annotatedconstructor.getParameter(l);
                    PropertyName propertyname1 = annotationintrospector.findNameForDeserialization(annotatedparameter1);
                    String s1;
                    if(propertyname1 == null)
                        s1 = null;
                    else
                        s1 = propertyname1.getSimpleName();
                    if(s1 != null)
                    {
                        POJOPropertyBuilder pojopropertybuilder1 = _property(s1);
                        pojopropertybuilder1.addCtor(annotatedparameter1, s1, true, false);
                        _creatorProperties.add(pojopropertybuilder1);
                    }
                    l++;
                }
            }

            Iterator iterator1 = _classDef.getStaticMethods().iterator();
            while(iterator1.hasNext()) 
            {
                AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator1.next();
                if(_creatorProperties == null)
                    _creatorProperties = new LinkedList();
                int i = annotatedmethod.getParameterCount();
                int j = 0;
                while(j < i) 
                {
                    AnnotatedParameter annotatedparameter = annotatedmethod.getParameter(j);
                    PropertyName propertyname = annotationintrospector.findNameForDeserialization(annotatedparameter);
                    String s;
                    if(propertyname == null)
                        s = null;
                    else
                        s = propertyname.getSimpleName();
                    if(s != null)
                    {
                        POJOPropertyBuilder pojopropertybuilder = _property(s);
                        pojopropertybuilder.addCtor(annotatedparameter, s, true, false);
                        _creatorProperties.add(pojopropertybuilder);
                    }
                    j++;
                }
            }
        }
    }

    protected void _addFields()
    {
        AnnotationIntrospector annotationintrospector = _annotationIntrospector;
        boolean flag;
        Iterator iterator;
        if(!_forSerialization && !_config.isEnabled(MapperFeature.ALLOW_FINAL_FIELDS_AS_MUTATORS))
            flag = true;
        else
            flag = false;
        iterator = _classDef.fields().iterator();
        do
            if(iterator.hasNext())
            {
                AnnotatedField annotatedfield = (AnnotatedField)iterator.next();
                String s = annotatedfield.getName();
                String s1;
                String s2;
                boolean flag1;
                boolean flag2;
                boolean flag3;
                if(annotationintrospector == null)
                    s1 = null;
                else
                if(_forSerialization)
                {
                    PropertyName propertyname1 = annotationintrospector.findNameForSerialization(annotatedfield);
                    if(propertyname1 == null)
                        s1 = null;
                    else
                        s1 = propertyname1.getSimpleName();
                } else
                {
                    PropertyName propertyname = annotationintrospector.findNameForDeserialization(annotatedfield);
                    if(propertyname == null)
                        s1 = null;
                    else
                        s1 = propertyname.getSimpleName();
                }
                if("".equals(s1))
                    s2 = s;
                else
                    s2 = s1;
                if(s2 != null)
                    flag1 = true;
                else
                    flag1 = false;
                if(!flag1)
                    flag2 = _visibilityChecker.isFieldVisible(annotatedfield);
                else
                    flag2 = flag1;
                if(annotationintrospector != null && annotationintrospector.hasIgnoreMarker(annotatedfield))
                    flag3 = true;
                else
                    flag3 = false;
                if(!flag || s2 != null || flag3 || !Modifier.isFinal(annotatedfield.getModifiers()))
                    _property(s).addField(annotatedfield, s2, flag2, flag3);
            } else
            {
                return;
            }
        while(true);
    }

    protected void _addGetterMethod(AnnotatedMethod annotatedmethod, AnnotationIntrospector annotationintrospector)
    {
        if(annotationintrospector == null) goto _L2; else goto _L1
_L1:
        if(!annotationintrospector.hasAnyGetterAnnotation(annotatedmethod)) goto _L4; else goto _L3
_L3:
        if(_anyGetters == null)
            _anyGetters = new LinkedList();
        _anyGetters.add(annotatedmethod);
_L8:
        return;
_L4:
        if(annotationintrospector.hasAsValueAnnotation(annotatedmethod))
        {
            if(_jsonValueGetters == null)
                _jsonValueGetters = new LinkedList();
            _jsonValueGetters.add(annotatedmethod);
            return;
        }
_L2:
        String s;
        boolean flag;
        String s2;
        String s3;
        PropertyName propertyname;
        String s4;
        boolean flag3;
        if(annotationintrospector == null)
            propertyname = null;
        else
            propertyname = annotationintrospector.findNameForSerialization(annotatedmethod);
        s = null;
        if(propertyname != null)
            s = propertyname.getSimpleName();
        if(s != null)
            break MISSING_BLOCK_LABEL_209;
        s3 = BeanUtil.okNameForRegularGetter(annotatedmethod, annotatedmethod.getName());
        if(s3 != null) goto _L6; else goto _L5
_L5:
        s4 = BeanUtil.okNameForIsGetter(annotatedmethod, annotatedmethod.getName());
        if(s4 == null) goto _L8; else goto _L7
_L7:
        flag3 = _visibilityChecker.isIsGetterVisible(annotatedmethod);
        s2 = s4;
        flag = flag3;
_L9:
        String s1;
        boolean flag1;
        boolean flag2;
        if(annotationintrospector == null)
            flag1 = false;
        else
            flag1 = annotationintrospector.hasIgnoreMarker(annotatedmethod);
        _property(s2).addGetter(annotatedmethod, s, flag, flag1);
        return;
_L6:
        flag2 = _visibilityChecker.isGetterVisible(annotatedmethod);
        s2 = s3;
        flag = flag2;
          goto _L9
        s1 = BeanUtil.okNameForGetter(annotatedmethod);
        if(s1 == null)
            s1 = annotatedmethod.getName();
        if(s.length() == 0)
            s = s1;
        flag = true;
        s2 = s1;
          goto _L9
    }

    protected void _addInjectables()
    {
        AnnotationIntrospector annotationintrospector = _annotationIntrospector;
        if(annotationintrospector != null)
        {
            AnnotatedField annotatedfield;
            for(Iterator iterator = _classDef.fields().iterator(); iterator.hasNext(); _doAddInjectable(annotationintrospector.findInjectableValueId(annotatedfield), annotatedfield))
                annotatedfield = (AnnotatedField)iterator.next();

            Iterator iterator1 = _classDef.memberMethods().iterator();
            while(iterator1.hasNext()) 
            {
                AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator1.next();
                if(annotatedmethod.getParameterCount() == 1)
                    _doAddInjectable(annotationintrospector.findInjectableValueId(annotatedmethod), annotatedmethod);
            }
        }
    }

    protected void _addMethods()
    {
        AnnotationIntrospector annotationintrospector = _annotationIntrospector;
        Iterator iterator = _classDef.memberMethods().iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            AnnotatedMethod annotatedmethod = (AnnotatedMethod)iterator.next();
            int i = annotatedmethod.getParameterCount();
            if(i == 0)
                _addGetterMethod(annotatedmethod, annotationintrospector);
            else
            if(i == 1)
                _addSetterMethod(annotatedmethod, annotationintrospector);
            else
            if(i == 2 && annotationintrospector != null && annotationintrospector.hasAnySetterAnnotation(annotatedmethod))
            {
                if(_anySetters == null)
                    _anySetters = new LinkedList();
                _anySetters.add(annotatedmethod);
            }
        } while(true);
    }

    protected void _addSetterMethod(AnnotatedMethod annotatedmethod, AnnotationIntrospector annotationintrospector)
    {
        PropertyName propertyname;
        String s;
        if(annotationintrospector == null)
            propertyname = null;
        else
            propertyname = annotationintrospector.findNameForDeserialization(annotatedmethod);
        s = null;
        if(propertyname != null)
            s = propertyname.getSimpleName();
        boolean flag;
        String s2;
        boolean flag1;
        if(s == null)
        {
            String s3 = BeanUtil.okNameForMutator(annotatedmethod, _mutatorPrefix);
            if(s3 == null)
                return;
            boolean flag2 = _visibilityChecker.isSetterVisible(annotatedmethod);
            s2 = s3;
            flag = flag2;
        } else
        {
            String s1 = BeanUtil.okNameForMutator(annotatedmethod, _mutatorPrefix);
            if(s1 == null)
                s1 = annotatedmethod.getName();
            if(s.length() == 0)
                s = s1;
            flag = true;
            s2 = s1;
        }
        if(annotationintrospector == null)
            flag1 = false;
        else
            flag1 = annotationintrospector.hasIgnoreMarker(annotatedmethod);
        _property(s2).addSetter(annotatedmethod, s, flag, flag1);
    }

    protected void _doAddInjectable(Object obj, AnnotatedMember annotatedmember)
    {
        if(obj != null)
        {
            if(_injectables == null)
                _injectables = new LinkedHashMap();
            if((AnnotatedMember)_injectables.put(obj, annotatedmember) != null)
            {
                String s = obj.getClass().getName();
                throw new IllegalArgumentException((new StringBuilder()).append("Duplicate injectable value with id '").append(String.valueOf(obj)).append("' (of type ").append(s).append(")").toString());
            }
        }
    }

    protected POJOPropertyBuilder _property(String s)
    {
        POJOPropertyBuilder pojopropertybuilder = (POJOPropertyBuilder)_properties.get(s);
        if(pojopropertybuilder == null)
        {
            pojopropertybuilder = new POJOPropertyBuilder(new PropertyName(s), _annotationIntrospector, _forSerialization);
            _properties.put(s, pojopropertybuilder);
        }
        return pojopropertybuilder;
    }

    protected void _removeUnwantedProperties()
    {
        Iterator iterator = _properties.entrySet().iterator();
        boolean flag;
        if(!_config.isEnabled(MapperFeature.INFER_PROPERTY_MUTATORS))
            flag = true;
        else
            flag = false;
        do
        {
            if(!iterator.hasNext())
                break;
            POJOPropertyBuilder pojopropertybuilder = (POJOPropertyBuilder)((java.util.Map.Entry)iterator.next()).getValue();
            if(!pojopropertybuilder.anyVisible())
            {
                iterator.remove();
                continue;
            }
            if(pojopropertybuilder.anyIgnorals())
            {
                if(!pojopropertybuilder.isExplicitlyIncluded())
                {
                    iterator.remove();
                    _addIgnored(pojopropertybuilder.getName());
                    continue;
                }
                pojopropertybuilder.removeIgnored();
                if(!_forSerialization && !pojopropertybuilder.couldDeserialize())
                    _addIgnored(pojopropertybuilder.getName());
            }
            pojopropertybuilder.removeNonVisible(flag);
        } while(true);
    }

    protected void _renameProperties()
    {
        LinkedList linkedlist;
        Iterator iterator = _properties.entrySet().iterator();
        linkedlist = null;
        do
        {
            if(!iterator.hasNext())
                break;
            POJOPropertyBuilder pojopropertybuilder2 = (POJOPropertyBuilder)((java.util.Map.Entry)iterator.next()).getValue();
            String s1 = pojopropertybuilder2.findNewName();
            if(s1 != null)
            {
                if(linkedlist == null)
                    linkedlist = new LinkedList();
                linkedlist.add(pojopropertybuilder2.withSimpleName(s1));
                iterator.remove();
            }
        } while(true);
        if(linkedlist == null) goto _L2; else goto _L1
_L1:
        Iterator iterator1 = linkedlist.iterator();
_L4:
        if(iterator1.hasNext())
        {
            POJOPropertyBuilder pojopropertybuilder = (POJOPropertyBuilder)iterator1.next();
            String s = pojopropertybuilder.getName();
            POJOPropertyBuilder pojopropertybuilder1 = (POJOPropertyBuilder)_properties.get(s);
            int i;
            if(pojopropertybuilder1 == null)
                _properties.put(s, pojopropertybuilder);
            else
                pojopropertybuilder1.addAll(pojopropertybuilder);
            if(_creatorProperties == null)
                continue; /* Loop/switch isn't completed */
            i = 0;
            do
            {
                if(i >= _creatorProperties.size())
                    continue; /* Loop/switch isn't completed */
                if(((POJOPropertyBuilder)_creatorProperties.get(i)).getInternalName().equals(pojopropertybuilder.getInternalName()))
                {
                    _creatorProperties.set(i, pojopropertybuilder);
                    continue; /* Loop/switch isn't completed */
                }
                i++;
            } while(true);
        }
_L2:
        return;
        if(true) goto _L4; else goto _L3
_L3:
    }

    protected void _renameUsing(PropertyNamingStrategy propertynamingstrategy)
    {
        POJOPropertyBuilder apojopropertybuilder[] = (POJOPropertyBuilder[])_properties.values().toArray(new POJOPropertyBuilder[_properties.size()]);
        _properties.clear();
        int i = apojopropertybuilder.length;
        int j = 0;
        while(j < i) 
        {
            POJOPropertyBuilder pojopropertybuilder = apojopropertybuilder[j];
            PropertyName propertyname = pojopropertybuilder.getFullName();
            String s;
            POJOPropertyBuilder pojopropertybuilder1;
            String s2;
            POJOPropertyBuilder pojopropertybuilder2;
            if(_forSerialization)
            {
                if(pojopropertybuilder.hasGetter())
                {
                    s = propertynamingstrategy.nameForGetterMethod(_config, pojopropertybuilder.getGetter(), propertyname.getSimpleName());
                } else
                {
                    boolean flag1 = pojopropertybuilder.hasField();
                    s = null;
                    if(flag1)
                        s = propertynamingstrategy.nameForField(_config, pojopropertybuilder.getField(), propertyname.getSimpleName());
                }
            } else
            if(pojopropertybuilder.hasSetter())
                s = propertynamingstrategy.nameForSetterMethod(_config, pojopropertybuilder.getSetter(), propertyname.getSimpleName());
            else
            if(pojopropertybuilder.hasConstructorParameter())
                s = propertynamingstrategy.nameForConstructorParameter(_config, pojopropertybuilder.getConstructorParameter(), propertyname.getSimpleName());
            else
            if(pojopropertybuilder.hasField())
            {
                s = propertynamingstrategy.nameForField(_config, pojopropertybuilder.getField(), propertyname.getSimpleName());
            } else
            {
                boolean flag = pojopropertybuilder.hasGetter();
                s = null;
                if(flag)
                    s = propertynamingstrategy.nameForGetterMethod(_config, pojopropertybuilder.getGetter(), propertyname.getSimpleName());
            }
            if(s != null && !propertyname.hasSimpleName(s))
            {
                pojopropertybuilder1 = pojopropertybuilder.withSimpleName(s);
                s2 = s;
            } else
            {
                String s1 = propertyname.getSimpleName();
                pojopropertybuilder1 = pojopropertybuilder;
                s2 = s1;
            }
            pojopropertybuilder2 = (POJOPropertyBuilder)_properties.get(s2);
            if(pojopropertybuilder2 == null)
                _properties.put(s2, pojopropertybuilder1);
            else
                pojopropertybuilder2.addAll(pojopropertybuilder1);
            j++;
        }
    }

    protected void _renameWithWrappers()
    {
        Iterator iterator = _properties.entrySet().iterator();
        LinkedList linkedlist = null;
        do
        {
            if(!iterator.hasNext())
                break;
            POJOPropertyBuilder pojopropertybuilder2 = (POJOPropertyBuilder)((java.util.Map.Entry)iterator.next()).getValue();
            AnnotatedMember annotatedmember = pojopropertybuilder2.getPrimaryMember();
            if(annotatedmember != null)
            {
                PropertyName propertyname = _annotationIntrospector.findWrapperName(annotatedmember);
                if(propertyname != null && propertyname.hasSimpleName() && !propertyname.equals(pojopropertybuilder2.getFullName()))
                {
                    if(linkedlist == null)
                        linkedlist = new LinkedList();
                    linkedlist.add(pojopropertybuilder2.withName(propertyname));
                    iterator.remove();
                }
            }
        } while(true);
        if(linkedlist != null)
        {
            for(Iterator iterator1 = linkedlist.iterator(); iterator1.hasNext();)
            {
                POJOPropertyBuilder pojopropertybuilder = (POJOPropertyBuilder)iterator1.next();
                String s = pojopropertybuilder.getName();
                POJOPropertyBuilder pojopropertybuilder1 = (POJOPropertyBuilder)_properties.get(s);
                if(pojopropertybuilder1 == null)
                    _properties.put(s, pojopropertybuilder);
                else
                    pojopropertybuilder1.addAll(pojopropertybuilder);
            }

        }
    }

    protected void _sortProperties()
    {
        boolean flag;
        String as[];
        Object obj;
        LinkedHashMap linkedhashmap;
        AnnotationIntrospector annotationintrospector = _annotationIntrospector;
        Boolean boolean1;
        if(annotationintrospector == null)
            boolean1 = null;
        else
            boolean1 = annotationintrospector.findSerializationSortAlphabetically(_classDef);
        if(boolean1 == null)
            flag = _config.shouldSortPropertiesAlphabetically();
        else
            flag = boolean1.booleanValue();
        if(annotationintrospector == null)
            as = null;
        else
            as = annotationintrospector.findSerializationPropertyOrder(_classDef);
        if(!flag && _creatorProperties == null && as == null)
            return;
        int i = _properties.size();
        Iterator iterator;
        POJOPropertyBuilder pojopropertybuilder4;
        if(flag)
            obj = new TreeMap();
        else
            obj = new LinkedHashMap(i + i);
        for(iterator = _properties.values().iterator(); iterator.hasNext(); ((Map) (obj)).put(pojopropertybuilder4.getName(), pojopropertybuilder4))
            pojopropertybuilder4 = (POJOPropertyBuilder)iterator.next();

        linkedhashmap = new LinkedHashMap(i + i);
        if(as == null) goto _L2; else goto _L1
_L1:
        int j;
        int k;
        j = as.length;
        k = 0;
_L4:
        if(k >= j) goto _L2; else goto _L3
_L3:
        String s;
        POJOPropertyBuilder pojopropertybuilder2;
        String s1;
        s = as[k];
        pojopropertybuilder2 = (POJOPropertyBuilder)((Map) (obj)).get(s);
        if(pojopropertybuilder2 != null)
            break MISSING_BLOCK_LABEL_474;
        Iterator iterator3 = _properties.values().iterator();
        POJOPropertyBuilder pojopropertybuilder3;
        do
        {
            if(!iterator3.hasNext())
                break MISSING_BLOCK_LABEL_474;
            pojopropertybuilder3 = (POJOPropertyBuilder)iterator3.next();
        } while(!s.equals(pojopropertybuilder3.getInternalName()));
        s1 = pojopropertybuilder3.getName();
        pojopropertybuilder2 = pojopropertybuilder3;
_L5:
        if(pojopropertybuilder2 != null)
            linkedhashmap.put(s1, pojopropertybuilder2);
        k++;
          goto _L4
_L2:
        if(_creatorProperties != null)
        {
            Object obj1;
            Iterator iterator2;
            POJOPropertyBuilder pojopropertybuilder;
            if(flag)
            {
                TreeMap treemap = new TreeMap();
                POJOPropertyBuilder pojopropertybuilder1;
                for(Iterator iterator1 = _creatorProperties.iterator(); iterator1.hasNext(); treemap.put(pojopropertybuilder1.getName(), pojopropertybuilder1))
                    pojopropertybuilder1 = (POJOPropertyBuilder)iterator1.next();

                obj1 = treemap.values();
            } else
            {
                obj1 = _creatorProperties;
            }
            for(iterator2 = ((Collection) (obj1)).iterator(); iterator2.hasNext(); linkedhashmap.put(pojopropertybuilder.getName(), pojopropertybuilder))
                pojopropertybuilder = (POJOPropertyBuilder)iterator2.next();

        }
        linkedhashmap.putAll(((Map) (obj)));
        _properties.clear();
        _properties.putAll(linkedhashmap);
        return;
        s1 = s;
          goto _L5
    }

    public POJOPropertiesCollector collect()
    {
        _properties.clear();
        _addFields();
        _addMethods();
        _addCreators();
        _addInjectables();
        _removeUnwantedProperties();
        _renameProperties();
        PropertyNamingStrategy propertynamingstrategy = _findNamingStrategy();
        if(propertynamingstrategy != null)
            _renameUsing(propertynamingstrategy);
        for(Iterator iterator = _properties.values().iterator(); iterator.hasNext(); ((POJOPropertyBuilder)iterator.next()).trimByVisibility());
        for(Iterator iterator1 = _properties.values().iterator(); iterator1.hasNext(); ((POJOPropertyBuilder)iterator1.next()).mergeAnnotations(_forSerialization));
        if(_config.isEnabled(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME))
            _renameWithWrappers();
        _sortProperties();
        return this;
    }

    public Class findPOJOBuilderClass()
    {
        return _annotationIntrospector.findPOJOBuilder(_classDef);
    }

    public AnnotationIntrospector getAnnotationIntrospector()
    {
        return _annotationIntrospector;
    }

    public AnnotatedMember getAnyGetter()
    {
        if(_anyGetters != null)
        {
            if(_anyGetters.size() > 1)
                reportProblem((new StringBuilder()).append("Multiple 'any-getters' defined (").append(_anyGetters.get(0)).append(" vs ").append(_anyGetters.get(1)).append(")").toString());
            return (AnnotatedMember)_anyGetters.getFirst();
        } else
        {
            return null;
        }
    }

    public AnnotatedMethod getAnySetterMethod()
    {
        if(_anySetters != null)
        {
            if(_anySetters.size() > 1)
                reportProblem((new StringBuilder()).append("Multiple 'any-setters' defined (").append(_anySetters.get(0)).append(" vs ").append(_anySetters.get(1)).append(")").toString());
            return (AnnotatedMethod)_anySetters.getFirst();
        } else
        {
            return null;
        }
    }

    public AnnotatedClass getClassDef()
    {
        return _classDef;
    }

    public MapperConfig getConfig()
    {
        return _config;
    }

    public Set getIgnoredPropertyNames()
    {
        return _ignoredPropertyNames;
    }

    public Map getInjectables()
    {
        return _injectables;
    }

    public AnnotatedMethod getJsonValueMethod()
    {
        if(_jsonValueGetters != null)
        {
            if(_jsonValueGetters.size() > 1)
                reportProblem((new StringBuilder()).append("Multiple value properties defined (").append(_jsonValueGetters.get(0)).append(" vs ").append(_jsonValueGetters.get(1)).append(")").toString());
            return (AnnotatedMethod)_jsonValueGetters.get(0);
        } else
        {
            return null;
        }
    }

    public ObjectIdInfo getObjectIdInfo()
    {
        ObjectIdInfo objectidinfo;
        if(_annotationIntrospector == null)
        {
            objectidinfo = null;
        } else
        {
            objectidinfo = _annotationIntrospector.findObjectIdInfo(_classDef);
            if(objectidinfo != null)
                return _annotationIntrospector.findObjectReferenceInfo(_classDef, objectidinfo);
        }
        return objectidinfo;
    }

    public List getProperties()
    {
        return new ArrayList(_properties.values());
    }

    protected Map getPropertyMap()
    {
        return _properties;
    }

    public JavaType getType()
    {
        return _type;
    }

    protected void reportProblem(String s)
    {
        throw new IllegalArgumentException((new StringBuilder()).append("Problem with definition of ").append(_classDef).append(": ").append(s).toString());
    }

    protected final AnnotationIntrospector _annotationIntrospector;
    protected LinkedList _anyGetters;
    protected LinkedList _anySetters;
    protected final AnnotatedClass _classDef;
    protected final MapperConfig _config;
    protected LinkedList _creatorProperties;
    protected final boolean _forSerialization;
    protected HashSet _ignoredPropertyNames;
    protected LinkedHashMap _injectables;
    protected LinkedList _jsonValueGetters;
    protected final String _mutatorPrefix;
    protected final LinkedHashMap _properties = new LinkedHashMap();
    protected final JavaType _type;
    protected final VisibilityChecker _visibilityChecker;
}
