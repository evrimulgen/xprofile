// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.fasterxml.jackson.databind.introspect;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import java.io.Serializable;
import java.lang.reflect.*;

// Referenced classes of package com.fasterxml.jackson.databind.introspect:
//            AnnotatedMember, AnnotatedField, AnnotatedMethod

public interface VisibilityChecker
{
    public static class Std
        implements VisibilityChecker, Serializable
    {

        public static Std defaultInstance()
        {
            return DEFAULT;
        }

        public boolean isCreatorVisible(AnnotatedMember annotatedmember)
        {
            return isCreatorVisible(annotatedmember.getMember());
        }

        public boolean isCreatorVisible(Member member)
        {
            return _creatorMinLevel.isVisible(member);
        }

        public boolean isFieldVisible(AnnotatedField annotatedfield)
        {
            return isFieldVisible(annotatedfield.getAnnotated());
        }

        public boolean isFieldVisible(Field field)
        {
            return _fieldMinLevel.isVisible(field);
        }

        public boolean isGetterVisible(AnnotatedMethod annotatedmethod)
        {
            return isGetterVisible(annotatedmethod.getAnnotated());
        }

        public boolean isGetterVisible(Method method)
        {
            return _getterMinLevel.isVisible(method);
        }

        public boolean isIsGetterVisible(AnnotatedMethod annotatedmethod)
        {
            return isIsGetterVisible(annotatedmethod.getAnnotated());
        }

        public boolean isIsGetterVisible(Method method)
        {
            return _isGetterMinLevel.isVisible(method);
        }

        public boolean isSetterVisible(AnnotatedMethod annotatedmethod)
        {
            return isSetterVisible(annotatedmethod.getAnnotated());
        }

        public boolean isSetterVisible(Method method)
        {
            return _setterMinLevel.isVisible(method);
        }

        public String toString()
        {
            return (new StringBuilder("[Visibility:")).append(" getter: ").append(_getterMinLevel).append(", isGetter: ").append(_isGetterMinLevel).append(", setter: ").append(_setterMinLevel).append(", creator: ").append(_creatorMinLevel).append(", field: ").append(_fieldMinLevel).append("]").toString();
        }

        public Std with(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            if(visibility == com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.DEFAULT)
                return DEFAULT;
            else
                return new Std(visibility);
        }

        public Std with(JsonAutoDetect jsonautodetect)
        {
            if(jsonautodetect != null)
                this = withGetterVisibility(jsonautodetect.getterVisibility()).withIsGetterVisibility(jsonautodetect.isGetterVisibility()).withSetterVisibility(jsonautodetect.setterVisibility()).withCreatorVisibility(jsonautodetect.creatorVisibility()).withFieldVisibility(jsonautodetect.fieldVisibility());
            return this;
        }

        public volatile VisibilityChecker with(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            return with(visibility);
        }

        public volatile VisibilityChecker with(JsonAutoDetect jsonautodetect)
        {
            return with(jsonautodetect);
        }

        public Std withCreatorVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility1;
            if(visibility == com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.DEFAULT)
                visibility1 = DEFAULT._creatorMinLevel;
            else
                visibility1 = visibility;
            if(_creatorMinLevel == visibility1)
                return this;
            else
                return new Std(_getterMinLevel, _isGetterMinLevel, _setterMinLevel, visibility1, _fieldMinLevel);
        }

        public volatile VisibilityChecker withCreatorVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            return withCreatorVisibility(visibility);
        }

        public Std withFieldVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility1;
            if(visibility == com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.DEFAULT)
                visibility1 = DEFAULT._fieldMinLevel;
            else
                visibility1 = visibility;
            if(_fieldMinLevel == visibility1)
                return this;
            else
                return new Std(_getterMinLevel, _isGetterMinLevel, _setterMinLevel, _creatorMinLevel, visibility1);
        }

        public volatile VisibilityChecker withFieldVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            return withFieldVisibility(visibility);
        }

        public Std withGetterVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility1;
            if(visibility == com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.DEFAULT)
                visibility1 = DEFAULT._getterMinLevel;
            else
                visibility1 = visibility;
            if(_getterMinLevel == visibility1)
                return this;
            else
                return new Std(visibility1, _isGetterMinLevel, _setterMinLevel, _creatorMinLevel, _fieldMinLevel);
        }

        public volatile VisibilityChecker withGetterVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            return withGetterVisibility(visibility);
        }

        public Std withIsGetterVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility1;
            if(visibility == com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.DEFAULT)
                visibility1 = DEFAULT._isGetterMinLevel;
            else
                visibility1 = visibility;
            if(_isGetterMinLevel == visibility1)
                return this;
            else
                return new Std(_getterMinLevel, visibility1, _setterMinLevel, _creatorMinLevel, _fieldMinLevel);
        }

        public volatile VisibilityChecker withIsGetterVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            return withIsGetterVisibility(visibility);
        }

        public Std withSetterVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility1;
            if(visibility == com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.DEFAULT)
                visibility1 = DEFAULT._setterMinLevel;
            else
                visibility1 = visibility;
            if(_setterMinLevel == visibility1)
                return this;
            else
                return new Std(_getterMinLevel, _isGetterMinLevel, visibility1, _creatorMinLevel, _fieldMinLevel);
        }

        public volatile VisibilityChecker withSetterVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            return withSetterVisibility(visibility);
        }

        public Std withVisibility(PropertyAccessor propertyaccessor, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            static class _cls1
            {

                static final int $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor[];

                static 
                {
                    $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor = new int[PropertyAccessor.values().length];
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor[PropertyAccessor.GETTER.ordinal()] = 1;
                    }
                    catch(NoSuchFieldError nosuchfielderror) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor[PropertyAccessor.SETTER.ordinal()] = 2;
                    }
                    catch(NoSuchFieldError nosuchfielderror1) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor[PropertyAccessor.CREATOR.ordinal()] = 3;
                    }
                    catch(NoSuchFieldError nosuchfielderror2) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor[PropertyAccessor.FIELD.ordinal()] = 4;
                    }
                    catch(NoSuchFieldError nosuchfielderror3) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor[PropertyAccessor.IS_GETTER.ordinal()] = 5;
                    }
                    catch(NoSuchFieldError nosuchfielderror4) { }
                    try
                    {
                        $SwitchMap$com$fasterxml$jackson$annotation$PropertyAccessor[PropertyAccessor.ALL.ordinal()] = 6;
                    }
                    catch(NoSuchFieldError nosuchfielderror5)
                    {
                        return;
                    }
                }
            }

            switch(_cls1..SwitchMap.com.fasterxml.jackson.annotation.PropertyAccessor[propertyaccessor.ordinal()])
            {
            default:
                return this;

            case 1: // '\001'
                return withGetterVisibility(visibility);

            case 2: // '\002'
                return withSetterVisibility(visibility);

            case 3: // '\003'
                return withCreatorVisibility(visibility);

            case 4: // '\004'
                return withFieldVisibility(visibility);

            case 5: // '\005'
                return withIsGetterVisibility(visibility);

            case 6: // '\006'
                return with(visibility);
            }
        }

        public volatile VisibilityChecker withVisibility(PropertyAccessor propertyaccessor, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            return withVisibility(propertyaccessor, visibility);
        }

        protected static final Std DEFAULT = new Std((JsonAutoDetect)com/fasterxml/jackson/databind/introspect/VisibilityChecker$Std.getAnnotation(com/fasterxml/jackson/annotation/JsonAutoDetect));
        private static final long serialVersionUID = 0x9dd451be3cb754bdL;
        protected final com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility _creatorMinLevel;
        protected final com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility _fieldMinLevel;
        protected final com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility _getterMinLevel;
        protected final com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility _isGetterMinLevel;
        protected final com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility _setterMinLevel;


        public Std(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility)
        {
            if(visibility == com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility.DEFAULT)
            {
                _getterMinLevel = DEFAULT._getterMinLevel;
                _isGetterMinLevel = DEFAULT._isGetterMinLevel;
                _setterMinLevel = DEFAULT._setterMinLevel;
                _creatorMinLevel = DEFAULT._creatorMinLevel;
                _fieldMinLevel = DEFAULT._fieldMinLevel;
                return;
            } else
            {
                _getterMinLevel = visibility;
                _isGetterMinLevel = visibility;
                _setterMinLevel = visibility;
                _creatorMinLevel = visibility;
                _fieldMinLevel = visibility;
                return;
            }
        }

        public Std(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility1, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility2, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility3, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility4)
        {
            _getterMinLevel = visibility;
            _isGetterMinLevel = visibility1;
            _setterMinLevel = visibility2;
            _creatorMinLevel = visibility3;
            _fieldMinLevel = visibility4;
        }

        public Std(JsonAutoDetect jsonautodetect)
        {
            _getterMinLevel = jsonautodetect.getterVisibility();
            _isGetterMinLevel = jsonautodetect.isGetterVisibility();
            _setterMinLevel = jsonautodetect.setterVisibility();
            _creatorMinLevel = jsonautodetect.creatorVisibility();
            _fieldMinLevel = jsonautodetect.fieldVisibility();
        }
    }


    public abstract boolean isCreatorVisible(AnnotatedMember annotatedmember);

    public abstract boolean isCreatorVisible(Member member);

    public abstract boolean isFieldVisible(AnnotatedField annotatedfield);

    public abstract boolean isFieldVisible(Field field);

    public abstract boolean isGetterVisible(AnnotatedMethod annotatedmethod);

    public abstract boolean isGetterVisible(Method method);

    public abstract boolean isIsGetterVisible(AnnotatedMethod annotatedmethod);

    public abstract boolean isIsGetterVisible(Method method);

    public abstract boolean isSetterVisible(AnnotatedMethod annotatedmethod);

    public abstract boolean isSetterVisible(Method method);

    public abstract VisibilityChecker with(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker with(JsonAutoDetect jsonautodetect);

    public abstract VisibilityChecker withCreatorVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker withFieldVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker withGetterVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker withIsGetterVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker withSetterVisibility(com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility);

    public abstract VisibilityChecker withVisibility(PropertyAccessor propertyaccessor, com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility visibility);
}
