// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.*;
import android.support.v4.app.Fragment;
import com.facebook.*;
import com.facebook.internal.*;
import com.facebook.model.*;
import com.newrelic.agent.android.instrumentation.JSONObjectInstrumentation;
import java.io.File;
import java.util.*;
import org.json.*;

public class FacebookDialog
{
    static abstract class Builder
    {

        protected Builder addImageAttachment(String s, Bitmap bitmap)
        {
            imageAttachments.put(s, bitmap);
            return this;
        }

        protected Builder addImageAttachment(String s, File file)
        {
            imageAttachmentFiles.put(s, file);
            return this;
        }

        protected List addImageAttachmentFiles(Collection collection)
        {
            ArrayList arraylist = new ArrayList();
            String s;
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); arraylist.add(NativeAppCallContentProvider.getAttachmentUrl(applicationId, appCall.getCallId(), s)))
            {
                File file = (File)iterator.next();
                s = UUID.randomUUID().toString();
                addImageAttachment(s, file);
            }

            return arraylist;
        }

        protected List addImageAttachments(Collection collection)
        {
            ArrayList arraylist = new ArrayList();
            String s;
            for(Iterator iterator = collection.iterator(); iterator.hasNext(); arraylist.add(NativeAppCallContentProvider.getAttachmentUrl(applicationId, appCall.getCallId(), s)))
            {
                Bitmap bitmap = (Bitmap)iterator.next();
                s = UUID.randomUUID().toString();
                addImageAttachment(s, bitmap);
            }

            return arraylist;
        }

        public FacebookDialog build()
        {
            validate();
            Bundle bundle = new Bundle();
            putExtra(bundle, "com.facebook.platform.extra.APPLICATION_ID", applicationId);
            putExtra(bundle, "com.facebook.platform.extra.APPLICATION_NAME", applicationName);
            Bundle bundle1 = setBundleExtras(bundle);
            String s = FacebookDialog.getActionForFeatures(getDialogFeatures());
            int i = FacebookDialog.getProtocolVersionForNativeDialog(activity, s, FacebookDialog.getMinVersionForFeatures(getDialogFeatures()));
            Intent intent = NativeProtocol.createPlatformActivityIntent(activity, s, i, bundle1);
            if(intent == null)
            {
                throw new FacebookException("Unable to create Intent; this likely means the Facebook app is not installed.");
            } else
            {
                appCall.setRequestIntent(intent);
                return new FacebookDialog(activity, fragment, appCall, getOnPresentCallback());
            }
        }

        public boolean canPresent()
        {
            return FacebookDialog.handleCanPresent(activity, getDialogFeatures());
        }

        abstract EnumSet getDialogFeatures();

        List getImageAttachmentNames()
        {
            return new ArrayList(imageAttachments.keySet());
        }

        OnPresentCallback getOnPresentCallback()
        {
            return new OnPresentCallback() {

                public void onPresent(Context context)
                    throws Exception
                {
                    if(imageAttachments != null && imageAttachments.size() > 0)
                        FacebookDialog.getAttachmentStore().addAttachmentsForCall(context, appCall.getCallId(), imageAttachments);
                    if(imageAttachmentFiles != null && imageAttachmentFiles.size() > 0)
                        FacebookDialog.getAttachmentStore().addAttachmentFilesForCall(context, appCall.getCallId(), imageAttachmentFiles);
                }

                final Builder this$0;

            
            {
                this$0 = Builder.this;
                super();
            }
            }
;
        }

        void putExtra(Bundle bundle, String s, String s1)
        {
            if(s1 != null)
                bundle.putString(s, s1);
        }

        public Builder setApplicationName(String s)
        {
            applicationName = s;
            return this;
        }

        abstract Bundle setBundleExtras(Bundle bundle);

        public Builder setFragment(Fragment fragment1)
        {
            fragment = fragment1;
            return this;
        }

        public Builder setRequestCode(int i)
        {
            appCall.setRequestCode(i);
            return this;
        }

        void validate()
        {
        }

        protected final Activity activity;
        protected final PendingCall appCall = new PendingCall(64207);
        protected final String applicationId;
        protected String applicationName;
        protected Fragment fragment;
        protected HashMap imageAttachmentFiles;
        protected HashMap imageAttachments;

        Builder(Activity activity1)
        {
            imageAttachments = new HashMap();
            imageAttachmentFiles = new HashMap();
            Validate.notNull(activity1, "activity");
            activity = activity1;
            applicationId = Utility.getMetadataApplicationId(activity1);
        }
    }

    public static interface Callback
    {

        public abstract void onComplete(PendingCall pendingcall, Bundle bundle);

        public abstract void onError(PendingCall pendingcall, Exception exception, Bundle bundle);
    }

    private static interface DialogFeature
    {

        public abstract String getAction();

        public abstract int getMinVersion();
    }

    static interface OnPresentCallback
    {

        public abstract void onPresent(Context context)
            throws Exception;
    }

    public static class OpenGraphActionDialogBuilder extends OpenGraphDialogBuilderBase
    {

        public volatile FacebookDialog build()
        {
            return super.build();
        }

        public volatile boolean canPresent()
        {
            return super.canPresent();
        }

        EnumSet getDialogFeatures()
        {
            return EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG);
        }

        public OpenGraphActionDialogBuilder(Activity activity1, OpenGraphAction opengraphaction, String s)
        {
            super(activity1, opengraphaction, s);
        }

        public OpenGraphActionDialogBuilder(Activity activity1, OpenGraphAction opengraphaction, String s, String s1)
        {
            super(activity1, opengraphaction, s, s1);
        }
    }

    public static final class OpenGraphActionDialogFeature extends Enum
        implements DialogFeature
    {

        public static OpenGraphActionDialogFeature valueOf(String s)
        {
            return (OpenGraphActionDialogFeature)Enum.valueOf(com/facebook/widget/FacebookDialog$OpenGraphActionDialogFeature, s);
        }

        public static OpenGraphActionDialogFeature[] values()
        {
            return (OpenGraphActionDialogFeature[])$VALUES.clone();
        }

        public String getAction()
        {
            return "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
        }

        public int getMinVersion()
        {
            return minVersion;
        }

        private static final OpenGraphActionDialogFeature $VALUES[];
        public static final OpenGraphActionDialogFeature OG_ACTION_DIALOG;
        private int minVersion;

        static 
        {
            OG_ACTION_DIALOG = new OpenGraphActionDialogFeature("OG_ACTION_DIALOG", 0, 0x1332b3a);
            OpenGraphActionDialogFeature aopengraphactiondialogfeature[] = new OpenGraphActionDialogFeature[1];
            aopengraphactiondialogfeature[0] = OG_ACTION_DIALOG;
            $VALUES = aopengraphactiondialogfeature;
        }

        private OpenGraphActionDialogFeature(String s, int i, int j)
        {
            super(s, i);
            minVersion = j;
        }
    }

    private static abstract class OpenGraphDialogBuilderBase extends Builder
    {

        private JSONObject flattenChildrenOfGraphObject(JSONObject jsonobject)
        {
            JVM INSTR new #93  <Class JSONObject>;
            if(jsonobject instanceof JSONObject) goto _L2; else goto _L1
_L1:
            String s1 = jsonobject.toString();
_L4:
            JSONException jsonexception;
            JSONObject jsonobject1;
            jsonobject1 = JSONObjectInstrumentation.init(s1);
            Iterator iterator = jsonobject1.keys();
            do
            {
                if(!iterator.hasNext())
                    break;
                String s2 = (String)iterator.next();
                if(!s2.equalsIgnoreCase("image"))
                    jsonobject1.put(s2, flattenObject(jsonobject1.get(s2)));
            } while(true);
            break; /* Loop/switch isn't completed */
_L2:
            String s;
            try
            {
                s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
            }
            // Misplaced declaration of an exception variable
            catch(JSONException jsonexception)
            {
                throw new FacebookException(jsonexception);
            }
            s1 = s;
            if(true) goto _L4; else goto _L3
_L3:
            return jsonobject1;
        }

        private Object flattenObject(Object obj)
            throws JSONException
        {
            if(obj != null) goto _L2; else goto _L1
_L1:
            obj = null;
_L4:
            return obj;
_L2:
            JSONObject jsonobject;
            if(!(obj instanceof JSONObject))
                continue; /* Loop/switch isn't completed */
            jsonobject = (JSONObject)obj;
            if(jsonobject.optBoolean("fbsdk:create_object")) goto _L4; else goto _L3
_L3:
            if(jsonobject.has("id"))
                return jsonobject.getString("id");
            if(!jsonobject.has("url")) goto _L4; else goto _L5
_L5:
            return jsonobject.getString("url");
            if(!(obj instanceof JSONArray)) goto _L4; else goto _L6
_L6:
            JSONArray jsonarray = (JSONArray)obj;
            JSONArray jsonarray1 = new JSONArray();
            int i = jsonarray.length();
            for(int j = 0; j < i; j++)
                jsonarray1.put(flattenObject(jsonarray.get(j)));

            return jsonarray1;
        }

        private void updateActionAttachmentUrls(List list, boolean flag)
        {
            Object obj;
            Iterator iterator;
            obj = action.getImage();
            if(obj == null)
                obj = new ArrayList(list.size());
            iterator = list.iterator();
_L3:
            if(!iterator.hasNext()) goto _L2; else goto _L1
_L1:
            String s = (String)iterator.next();
            JSONObject jsonobject = new JSONObject();
            try
            {
                jsonobject.put("url", s);
            }
            catch(JSONException jsonexception)
            {
                throw new FacebookException("Unable to attach images", jsonexception);
            }
            if(!flag)
                continue; /* Loop/switch isn't completed */
            jsonobject.put("user_generated", true);
            ((List) (obj)).add(jsonobject);
              goto _L3
_L2:
            action.setImage(((List) (obj)));
            return;
        }

        Bundle setBundleExtras(Bundle bundle)
        {
            putExtra(bundle, "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME", previewPropertyName);
            putExtra(bundle, "com.facebook.platform.extra.ACTION_TYPE", actionType);
            bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", dataErrorsFatal);
            JSONObject jsonobject = flattenChildrenOfGraphObject(action.getInnerJSONObject());
            String s;
            if(!(jsonobject instanceof JSONObject))
                s = jsonobject.toString();
            else
                s = JSONObjectInstrumentation.toString((JSONObject)jsonobject);
            putExtra(bundle, "com.facebook.platform.extra.ACTION", s);
            return bundle;
        }

        public OpenGraphDialogBuilderBase setDataErrorsFatal(boolean flag)
        {
            dataErrorsFatal = flag;
            return this;
        }

        public OpenGraphDialogBuilderBase setImageAttachmentFilesForAction(List list)
        {
            return setImageAttachmentFilesForAction(list, false);
        }

        public OpenGraphDialogBuilderBase setImageAttachmentFilesForAction(List list, boolean flag)
        {
            Validate.containsNoNulls(list, "bitmapFiles");
            if(action == null)
            {
                throw new FacebookException("Can not set attachments prior to setting action.");
            } else
            {
                updateActionAttachmentUrls(addImageAttachmentFiles(list), flag);
                return this;
            }
        }

        public OpenGraphDialogBuilderBase setImageAttachmentFilesForObject(String s, List list)
        {
            return setImageAttachmentFilesForObject(s, list, false);
        }

        public OpenGraphDialogBuilderBase setImageAttachmentFilesForObject(String s, List list, boolean flag)
        {
            Validate.notNull(s, "objectProperty");
            Validate.containsNoNulls(list, "bitmapFiles");
            if(action == null)
            {
                throw new FacebookException("Can not set attachments prior to setting action.");
            } else
            {
                updateObjectAttachmentUrls(s, addImageAttachmentFiles(list), flag);
                return this;
            }
        }

        public OpenGraphDialogBuilderBase setImageAttachmentsForAction(List list)
        {
            return setImageAttachmentsForAction(list, false);
        }

        public OpenGraphDialogBuilderBase setImageAttachmentsForAction(List list, boolean flag)
        {
            Validate.containsNoNulls(list, "bitmaps");
            if(action == null)
            {
                throw new FacebookException("Can not set attachments prior to setting action.");
            } else
            {
                updateActionAttachmentUrls(addImageAttachments(list), flag);
                return this;
            }
        }

        public OpenGraphDialogBuilderBase setImageAttachmentsForObject(String s, List list)
        {
            return setImageAttachmentsForObject(s, list, false);
        }

        public OpenGraphDialogBuilderBase setImageAttachmentsForObject(String s, List list, boolean flag)
        {
            Validate.notNull(s, "objectProperty");
            Validate.containsNoNulls(list, "bitmaps");
            if(action == null)
            {
                throw new FacebookException("Can not set attachments prior to setting action.");
            } else
            {
                updateObjectAttachmentUrls(s, addImageAttachments(list), flag);
                return this;
            }
        }

        void updateObjectAttachmentUrls(String s, List list, boolean flag)
        {
            OpenGraphObject opengraphobject;
            try
            {
                opengraphobject = (OpenGraphObject)action.getPropertyAs(s, com/facebook/model/OpenGraphObject);
            }
            catch(FacebookGraphObjectException facebookgraphobjectexception)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("Property '").append(s).append("' is not a graph object").toString());
            }
            if(opengraphobject != null)
                break MISSING_BLOCK_LABEL_93;
            throw new IllegalArgumentException((new StringBuilder()).append("Action does not contain a property '").append(s).append("'").toString());
            if(!opengraphobject.getCreateObject())
                throw new IllegalArgumentException((new StringBuilder()).append("The Open Graph object in '").append(s).append("' is not marked for creation").toString());
            GraphObjectList graphobjectlist = opengraphobject.getImage();
            if(graphobjectlist == null)
                graphobjectlist = com.facebook.model.GraphObject.Factory.createList(com/facebook/model/GraphObject);
            GraphObject graphobject;
            for(Iterator iterator = list.iterator(); iterator.hasNext(); graphobjectlist.add(graphobject))
            {
                String s1 = (String)iterator.next();
                graphobject = com.facebook.model.GraphObject.Factory.create();
                graphobject.setProperty("url", s1);
                if(flag)
                    graphobject.setProperty("user_generated", Boolean.valueOf(true));
            }

            opengraphobject.setImage(graphobjectlist);
            return;
        }

        private OpenGraphAction action;
        private String actionType;
        private boolean dataErrorsFatal;
        private String previewPropertyName;

        public OpenGraphDialogBuilderBase(Activity activity1, OpenGraphAction opengraphaction, String s)
        {
            super(activity1);
            Validate.notNull(opengraphaction, "action");
            Validate.notNullOrEmpty(opengraphaction.getType(), "action.getType()");
            Validate.notNullOrEmpty(s, "previewPropertyName");
            if(opengraphaction.getProperty(s) == null)
            {
                throw new IllegalArgumentException((new StringBuilder()).append("A property named \"").append(s).append("\" was not found on the action.  The name of ").append("the preview property must match the name of an action property.").toString());
            } else
            {
                action = opengraphaction;
                actionType = opengraphaction.getType();
                previewPropertyName = s;
                return;
            }
        }

        public OpenGraphDialogBuilderBase(Activity activity1, OpenGraphAction opengraphaction, String s, String s1)
        {
            super(activity1);
            Validate.notNull(opengraphaction, "action");
            Validate.notNullOrEmpty(s, "actionType");
            Validate.notNullOrEmpty(s1, "previewPropertyName");
            if(opengraphaction.getProperty(s1) == null)
                throw new IllegalArgumentException((new StringBuilder()).append("A property named \"").append(s1).append("\" was not found on the action.  The name of ").append("the preview property must match the name of an action property.").toString());
            String s2 = opengraphaction.getType();
            if(!Utility.isNullOrEmpty(s2) && !s2.equals(s))
            {
                throw new IllegalArgumentException("'actionType' must match the type of 'action' if it is specified. Consider using OpenGraphDialogBuilderBase(Activity activity, OpenGraphAction action, String previewPropertyName) instead.");
            } else
            {
                action = opengraphaction;
                actionType = s;
                previewPropertyName = s1;
                return;
            }
        }
    }

    public static class PendingCall
        implements Parcelable
    {

        private void setRequestCode(int i)
        {
            requestCode = i;
        }

        private void setRequestIntent(Intent intent)
        {
            requestIntent = intent;
            requestIntent.putExtra("com.facebook.platform.protocol.CALL_ID", callId.toString());
        }

        public int describeContents()
        {
            return 0;
        }

        public UUID getCallId()
        {
            return callId;
        }

        public int getRequestCode()
        {
            return requestCode;
        }

        public Intent getRequestIntent()
        {
            return requestIntent;
        }

        public void writeToParcel(Parcel parcel, int i)
        {
            parcel.writeString(callId.toString());
            parcel.writeParcelable(requestIntent, 0);
            parcel.writeInt(requestCode);
        }

        public static final android.os.Parcelable.Creator CREATOR = new android.os.Parcelable.Creator() {

            public PendingCall createFromParcel(Parcel parcel)
            {
                return new PendingCall(parcel);
            }

            public volatile Object createFromParcel(Parcel parcel)
            {
                return createFromParcel(parcel);
            }

            public PendingCall[] newArray(int i)
            {
                return new PendingCall[i];
            }

            public volatile Object[] newArray(int i)
            {
                return newArray(i);
            }

        }
;
        private UUID callId;
        private int requestCode;
        private Intent requestIntent;




        public PendingCall(int i)
        {
            callId = UUID.randomUUID();
            requestCode = i;
        }

        private PendingCall(Parcel parcel)
        {
            callId = UUID.fromString(parcel.readString());
            requestIntent = (Intent)parcel.readParcelable(null);
            requestCode = parcel.readInt();
        }

    }

    private static abstract class PhotoDialogBuilderBase extends Builder
    {

        public PhotoDialogBuilderBase addPhotoFiles(Collection collection)
        {
            imageAttachmentUrls.addAll(addImageAttachmentFiles(collection));
            return this;
        }

        public PhotoDialogBuilderBase addPhotos(Collection collection)
        {
            imageAttachmentUrls.addAll(addImageAttachments(collection));
            return this;
        }

        Bundle setBundleExtras(Bundle bundle)
        {
            putExtra(bundle, "com.facebook.platform.extra.APPLICATION_ID", applicationId);
            putExtra(bundle, "com.facebook.platform.extra.APPLICATION_NAME", applicationName);
            putExtra(bundle, "com.facebook.platform.extra.PLACE", place);
            bundle.putStringArrayList("com.facebook.platform.extra.PHOTOS", imageAttachmentUrls);
            if(!Utility.isNullOrEmpty(friends))
                bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", friends);
            return bundle;
        }

        public PhotoDialogBuilderBase setFriends(List list)
        {
            friends = new ArrayList(list);
            return this;
        }

        public PhotoDialogBuilderBase setPlace(String s)
        {
            place = s;
            return this;
        }

        void validate()
        {
            super.validate();
            if(imageAttachmentUrls.isEmpty())
                throw new FacebookException("Must specify at least one photo.");
            else
                return;
        }

        private ArrayList friends;
        private ArrayList imageAttachmentUrls;
        private String place;

        public PhotoDialogBuilderBase(Activity activity1)
        {
            super(activity1);
            imageAttachmentUrls = new ArrayList();
        }
    }

    public static class PhotoShareDialogBuilder extends PhotoDialogBuilderBase
    {

        public volatile FacebookDialog build()
        {
            return super.build();
        }

        public volatile boolean canPresent()
        {
            return super.canPresent();
        }

        EnumSet getDialogFeatures()
        {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG, ShareDialogFeature.PHOTOS);
        }

        public PhotoShareDialogBuilder(Activity activity1)
        {
            super(activity1);
        }
    }

    public static class ShareDialogBuilder extends ShareDialogBuilderBase
    {

        public volatile FacebookDialog build()
        {
            return super.build();
        }

        public volatile boolean canPresent()
        {
            return super.canPresent();
        }

        EnumSet getDialogFeatures()
        {
            return EnumSet.of(ShareDialogFeature.SHARE_DIALOG);
        }

        public ShareDialogBuilder(Activity activity1)
        {
            super(activity1);
        }
    }

    private static abstract class ShareDialogBuilderBase extends Builder
    {

        Bundle setBundleExtras(Bundle bundle)
        {
            putExtra(bundle, "com.facebook.platform.extra.APPLICATION_ID", applicationId);
            putExtra(bundle, "com.facebook.platform.extra.APPLICATION_NAME", applicationName);
            putExtra(bundle, "com.facebook.platform.extra.TITLE", name);
            putExtra(bundle, "com.facebook.platform.extra.SUBTITLE", caption);
            putExtra(bundle, "com.facebook.platform.extra.DESCRIPTION", description);
            putExtra(bundle, "com.facebook.platform.extra.LINK", link);
            putExtra(bundle, "com.facebook.platform.extra.IMAGE", picture);
            putExtra(bundle, "com.facebook.platform.extra.PLACE", place);
            putExtra(bundle, "com.facebook.platform.extra.TITLE", name);
            putExtra(bundle, "com.facebook.platform.extra.REF", ref);
            bundle.putBoolean("com.facebook.platform.extra.DATA_FAILURES_FATAL", dataErrorsFatal);
            if(!Utility.isNullOrEmpty(friends))
                bundle.putStringArrayList("com.facebook.platform.extra.FRIENDS", friends);
            return bundle;
        }

        public ShareDialogBuilderBase setCaption(String s)
        {
            caption = s;
            return this;
        }

        public ShareDialogBuilderBase setDataErrorsFatal(boolean flag)
        {
            dataErrorsFatal = flag;
            return this;
        }

        public ShareDialogBuilderBase setDescription(String s)
        {
            description = s;
            return this;
        }

        public ShareDialogBuilderBase setFriends(List list)
        {
            friends = new ArrayList(list);
            return this;
        }

        public ShareDialogBuilderBase setLink(String s)
        {
            link = s;
            return this;
        }

        public ShareDialogBuilderBase setName(String s)
        {
            name = s;
            return this;
        }

        public ShareDialogBuilderBase setPicture(String s)
        {
            picture = s;
            return this;
        }

        public ShareDialogBuilderBase setPlace(String s)
        {
            place = s;
            return this;
        }

        public ShareDialogBuilderBase setRef(String s)
        {
            ref = s;
            return this;
        }

        private String caption;
        private boolean dataErrorsFatal;
        private String description;
        private ArrayList friends;
        protected String link;
        private String name;
        private String picture;
        private String place;
        private String ref;

        public ShareDialogBuilderBase(Activity activity1)
        {
            super(activity1);
        }
    }

    public static final class ShareDialogFeature extends Enum
        implements DialogFeature
    {

        public static ShareDialogFeature valueOf(String s)
        {
            return (ShareDialogFeature)Enum.valueOf(com/facebook/widget/FacebookDialog$ShareDialogFeature, s);
        }

        public static ShareDialogFeature[] values()
        {
            return (ShareDialogFeature[])$VALUES.clone();
        }

        public String getAction()
        {
            return "com.facebook.platform.action.request.FEED_DIALOG";
        }

        public int getMinVersion()
        {
            return minVersion;
        }

        private static final ShareDialogFeature $VALUES[];
        public static final ShareDialogFeature PHOTOS;
        public static final ShareDialogFeature SHARE_DIALOG;
        private int minVersion;

        static 
        {
            SHARE_DIALOG = new ShareDialogFeature("SHARE_DIALOG", 0, 0x1332b3a);
            PHOTOS = new ShareDialogFeature("PHOTOS", 1, 0x13350ac);
            ShareDialogFeature asharedialogfeature[] = new ShareDialogFeature[2];
            asharedialogfeature[0] = SHARE_DIALOG;
            asharedialogfeature[1] = PHOTOS;
            $VALUES = asharedialogfeature;
        }

        private ShareDialogFeature(String s, int i, int j)
        {
            super(s, i);
            minVersion = j;
        }
    }


    private FacebookDialog(Activity activity1, Fragment fragment1, PendingCall pendingcall, OnPresentCallback onpresentcallback)
    {
        activity = activity1;
        fragment = fragment1;
        appCall = pendingcall;
        onPresentCallback = onpresentcallback;
    }


    public static transient boolean canPresentOpenGraphActionDialog(Context context, OpenGraphActionDialogFeature aopengraphactiondialogfeature[])
    {
        return handleCanPresent(context, EnumSet.of(OpenGraphActionDialogFeature.OG_ACTION_DIALOG, aopengraphactiondialogfeature));
    }

    public static transient boolean canPresentShareDialog(Context context, ShareDialogFeature asharedialogfeature[])
    {
        return handleCanPresent(context, EnumSet.of(ShareDialogFeature.SHARE_DIALOG, asharedialogfeature));
    }

    private static String getActionForFeatures(Iterable iterable)
    {
        Iterator iterator = iterable.iterator();
        boolean flag = iterator.hasNext();
        String s = null;
        if(flag)
            s = ((DialogFeature)iterator.next()).getAction();
        return s;
    }

    private static NativeAppCallAttachmentStore getAttachmentStore()
    {
        if(attachmentStore == null)
            attachmentStore = new NativeAppCallAttachmentStore();
        return attachmentStore;
    }

    private static int getMinVersionForFeatures(Iterable iterable)
    {
        int i = 0x80000000;
        for(Iterator iterator = iterable.iterator(); iterator.hasNext();)
            i = Math.max(i, ((DialogFeature)iterator.next()).getMinVersion());

        return i;
    }

    public static String getNativeDialogCompletionGesture(Bundle bundle)
    {
        return bundle.getString("com.facebook.platform.extra.COMPLETION_GESTURE");
    }

    public static boolean getNativeDialogDidComplete(Bundle bundle)
    {
        return bundle.getBoolean("com.facebook.platform.extra.DID_COMPLETE", false);
    }

    public static String getNativeDialogPostId(Bundle bundle)
    {
        return bundle.getString("com.facebook.platform.extra.POST_ID");
    }

    private static int getProtocolVersionForNativeDialog(Context context, String s, int i)
    {
        return NativeProtocol.getLatestAvailableProtocolVersionForAction(context, s, i);
    }

    public static boolean handleActivityResult(Context context, PendingCall pendingcall, int i, Intent intent, Callback callback)
    {
        if(i != pendingcall.getRequestCode())
            return false;
        if(attachmentStore != null)
            attachmentStore.cleanupAttachmentsForCall(context, pendingcall.getCallId());
        if(callback != null)
            if(NativeProtocol.isErrorResult(intent))
                callback.onError(pendingcall, NativeProtocol.getErrorFromResult(intent), intent.getExtras());
            else
                callback.onComplete(pendingcall, intent.getExtras());
        return true;
    }

    private static boolean handleCanPresent(Context context, Iterable iterable)
    {
        return getProtocolVersionForNativeDialog(context, getActionForFeatures(iterable), getMinVersionForFeatures(iterable)) != -1;
    }

    public PendingCall present()
    {
        if(onPresentCallback != null)
            try
            {
                onPresentCallback.onPresent(activity);
            }
            catch(Exception exception)
            {
                throw new FacebookException(exception);
            }
        if(fragment != null)
            fragment.startActivityForResult(appCall.getRequestIntent(), appCall.getRequestCode());
        else
            activity.startActivityForResult(appCall.getRequestIntent(), appCall.getRequestCode());
        return appCall;
    }

    public static final String COMPLETION_GESTURE_CANCEL = "cancel";
    private static final String EXTRA_DIALOG_COMPLETE_KEY = "com.facebook.platform.extra.DID_COMPLETE";
    private static final String EXTRA_DIALOG_COMPLETION_GESTURE_KEY = "com.facebook.platform.extra.COMPLETION_GESTURE";
    private static final String EXTRA_DIALOG_COMPLETION_ID_KEY = "com.facebook.platform.extra.POST_ID";
    private static NativeAppCallAttachmentStore attachmentStore;
    private Activity activity;
    private PendingCall appCall;
    private Fragment fragment;
    private OnPresentCallback onPresentCallback;





}
