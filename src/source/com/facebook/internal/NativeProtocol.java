// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.internal;

import android.content.*;
import android.content.pm.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import com.facebook.*;
import java.util.*;

// Referenced classes of package com.facebook.internal:
//            Utility

public final class NativeProtocol
{
    private static class KatanaAppInfo extends NativeAppInfo
    {

        protected String getPackage()
        {
            return "com.facebook.katana";
        }

        protected String getSignature()
        {
            return "30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2";
        }

        static final String KATANA_PACKAGE = "com.facebook.katana";
        static final String KATANA_SIGNATURE = "30820268308201d102044a9c4610300d06092a864886f70d0101040500307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e3020170d3039303833313231353231365a180f32303530303932353231353231365a307a310b3009060355040613025553310b3009060355040813024341311230100603550407130950616c6f20416c746f31183016060355040a130f46616365626f6f6b204d6f62696c653111300f060355040b130846616365626f6f6b311d301b0603550403131446616365626f6f6b20436f72706f726174696f6e30819f300d06092a864886f70d010101050003818d0030818902818100c207d51df8eb8c97d93ba0c8c1002c928fab00dc1b42fca5e66e99cc3023ed2d214d822bc59e8e35ddcf5f44c7ae8ade50d7e0c434f500e6c131f4a2834f987fc46406115de2018ebbb0d5a3c261bd97581ccfef76afc7135a6d59e8855ecd7eacc8f8737e794c60a761c536b72b11fac8e603f5da1a2d54aa103b8a13c0dbc10203010001300d06092a864886f70d0101040500038181005ee9be8bcbb250648d3b741290a82a1c9dc2e76a0af2f2228f1d9f9c4007529c446a70175c5a900d5141812866db46be6559e2141616483998211f4a673149fb2232a10d247663b26a9031e15f84bc1c74d141ff98a02d76f85b2c8ab2571b6469b232d8e768a7f7ca04f7abe4a775615916c07940656b58717457b42bd928a2";

        private KatanaAppInfo()
        {
        }

    }

    private static abstract class NativeAppInfo
    {

        protected abstract String getPackage();

        protected abstract String getSignature();

        public boolean validateSignature(Context context, String s)
        {
            String s1;
            int i;
            s1 = Build.BRAND;
            i = context.getApplicationInfo().flags;
            if(!s1.startsWith("generic") || (i & 2) == 0) goto _L2; else goto _L1
_L1:
            return true;
_L2:
            PackageInfo packageinfo;
            Signature asignature[];
            int j;
            int k;
            try
            {
                packageinfo = context.getPackageManager().getPackageInfo(s, 64);
            }
            catch(android.content.pm.PackageManager.NameNotFoundException namenotfoundexception)
            {
                return false;
            }
            asignature = packageinfo.signatures;
            j = asignature.length;
            k = 0;
label0:
            do
            {
label1:
                {
                    if(k >= j)
                        break label1;
                    if(asignature[k].toCharsString().equals(getSignature()))
                        break label0;
                    k++;
                }
            } while(true);
            if(true) goto _L1; else goto _L3
_L3:
            return false;
        }

        private NativeAppInfo()
        {
        }

    }

    private static class WakizashiAppInfo extends NativeAppInfo
    {

        protected String getPackage()
        {
            return "com.facebook.wakizashi";
        }

        protected String getSignature()
        {
            return null;
        }

        public boolean validateSignature(Context context, String s)
        {
            return true;
        }

        static final String WAKIZASHI_PACKAGE = "com.facebook.wakizashi";

        private WakizashiAppInfo()
        {
        }
    }


    public NativeProtocol()
    {
    }

    private static Map buildActionToAppInfoMap()
    {
        HashMap hashmap = new HashMap();
        hashmap.put("com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG", facebookAppInfoList);
        hashmap.put("com.facebook.platform.action.request.FEED_DIALOG", facebookAppInfoList);
        hashmap.put("com.facebook.platform.action.request.LOGIN_DIALOG", facebookAppInfoList);
        return hashmap;
    }

    private static List buildFacebookAppList()
    {
        ArrayList arraylist = new ArrayList();
        arraylist.add(FACEBOOK_APP_INFO);
        return arraylist;
    }

    private static Uri buildPlatformProviderVersionURI(NativeAppInfo nativeappinfo)
    {
        return Uri.parse((new StringBuilder()).append("content://").append(nativeappinfo.getPackage()).append(".provider.PlatformProvider/versions").toString());
    }

    public static Intent createLoginDialog20121101Intent(Context context, String s, ArrayList arraylist, String s1)
    {
        Intent intent = findActivityIntent(context, "com.facebook.platform.PLATFORM_ACTIVITY", "com.facebook.platform.action.request.LOGIN_DIALOG");
        if(intent == null)
        {
            return null;
        } else
        {
            intent.putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0x133060d).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", "com.facebook.platform.action.request.LOGIN_DIALOG").putExtra("com.facebook.platform.extra.APPLICATION_ID", s).putStringArrayListExtra("com.facebook.platform.extra.PERMISSIONS", ensureDefaultPermissions(arraylist)).putExtra("com.facebook.platform.protocol.CALL_ID", generateCallId()).putExtra("com.facebook.platform.extra.WRITE_PRIVACY", ensureDefaultAudience(s1));
            return intent;
        }
    }

    public static Intent createPlatformActivityIntent(Context context, String s, int i, Bundle bundle)
    {
        Intent intent = findActivityIntent(context, "com.facebook.platform.PLATFORM_ACTIVITY", s);
        if(intent == null)
        {
            return null;
        } else
        {
            intent.putExtras(bundle).putExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", i).putExtra("com.facebook.platform.protocol.PROTOCOL_ACTION", s);
            return intent;
        }
    }

    public static Intent createPlatformServiceIntent(Context context)
    {
        for(Iterator iterator = facebookAppInfoList.iterator(); iterator.hasNext();)
        {
            NativeAppInfo nativeappinfo = (NativeAppInfo)iterator.next();
            Intent intent = validateServiceIntent(context, (new Intent("com.facebook.platform.PLATFORM_SERVICE")).setPackage(nativeappinfo.getPackage()).addCategory("android.intent.category.DEFAULT"), nativeappinfo);
            if(intent != null)
                return intent;
        }

        return null;
    }

    public static Intent createProxyAuthIntent(Context context, String s, List list, String s1)
    {
        Intent intent = (new Intent()).setClassName(FACEBOOK_APP_INFO.getPackage(), "com.facebook.katana.ProxyAuth").putExtra("client_id", s);
        if(!Utility.isNullOrEmpty(list))
            intent.putExtra("scope", TextUtils.join(",", list));
        if(!Utility.isNullOrEmpty(s1))
            intent.putExtra("e2e", s1);
        return validateActivityIntent(context, intent, FACEBOOK_APP_INFO);
    }

    public static Intent createTokenRefreshIntent(Context context)
    {
        return validateServiceIntent(context, (new Intent()).setClassName(FACEBOOK_APP_INFO.getPackage(), "com.facebook.katana.platform.TokenRefreshService"), FACEBOOK_APP_INFO);
    }

    private static String ensureDefaultAudience(String s)
    {
        if(Utility.isNullOrEmpty(s))
            s = "SELF";
        return s;
    }

    private static ArrayList ensureDefaultPermissions(ArrayList arraylist)
    {
        if(!Utility.isNullOrEmpty(arraylist)) goto _L2; else goto _L1
_L1:
        ArrayList arraylist1 = new ArrayList();
_L6:
        arraylist1.add("basic_info");
        arraylist = arraylist1;
_L4:
        return arraylist;
_L2:
        Iterator iterator = arraylist.iterator();
label0:
        do
        {
label1:
            {
                if(!iterator.hasNext())
                    break label1;
                String s = (String)iterator.next();
                if(Session.isPublishPermission(s))
                    continue;
                if("basic_info".equals(s))
                    return arraylist;
            }
        } while(true);
        if(true) goto _L4; else goto _L3
_L3:
        arraylist1 = new ArrayList(arraylist);
        if(true) goto _L6; else goto _L5
_L5:
    }

    private static Intent findActivityIntent(Context context, String s, String s1)
    {
        List list = (List)actionToAppInfoMap.get(s1);
        if(list != null) goto _L2; else goto _L1
_L1:
        Intent intent = null;
_L4:
        return intent;
_L2:
        intent = null;
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                continue; /* Loop/switch isn't completed */
            NativeAppInfo nativeappinfo = (NativeAppInfo)iterator.next();
            intent = validateActivityIntent(context, (new Intent()).setAction(s).setPackage(nativeappinfo.getPackage()).addCategory("android.intent.category.DEFAULT"), nativeappinfo);
        } while(intent == null);
        break; /* Loop/switch isn't completed */
        if(true) goto _L4; else goto _L3
_L3:
        return intent;
    }

    private static String generateCallId()
    {
        return UUID.randomUUID().toString();
    }

    public static Exception getErrorFromResult(Intent intent)
    {
        if(!isErrorResult(intent))
            return null;
        String s = intent.getStringExtra("com.facebook.platform.status.ERROR_TYPE");
        String s1 = intent.getStringExtra("com.facebook.platform.status.ERROR_DESCRIPTION");
        if(s.equalsIgnoreCase("UserCanceled"))
            return new FacebookOperationCanceledException(s1);
        else
            return new FacebookException(s1);
    }

    public static int getLatestAvailableProtocolVersionForAction(Context context, String s, int i)
    {
        return getLatestAvailableProtocolVersionForAppInfoList(context, (List)actionToAppInfoMap.get(s), i);
    }

    private static int getLatestAvailableProtocolVersionForAppInfo(Context context, NativeAppInfo nativeappinfo, int i)
    {
        ContentResolver contentresolver = context.getContentResolver();
        String as[] = {
            "version"
        };
        Cursor cursor = contentresolver.query(buildPlatformProviderVersionURI(nativeappinfo), as, null, null, null);
        if(cursor == null)
            return -1;
        HashSet hashset = new HashSet();
        for(; cursor.moveToNext(); hashset.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("version")))));
        for(Iterator iterator = KNOWN_PROTOCOL_VERSIONS.iterator(); iterator.hasNext();)
        {
            Integer integer = (Integer)iterator.next();
            if(integer.intValue() < i)
                return -1;
            if(hashset.contains(integer))
                return integer.intValue();
        }

        return -1;
    }

    private static int getLatestAvailableProtocolVersionForAppInfoList(Context context, List list, int i)
    {
        if(list == null)
            return -1;
        for(Iterator iterator = list.iterator(); iterator.hasNext();)
        {
            int j = getLatestAvailableProtocolVersionForAppInfo(context, (NativeAppInfo)iterator.next(), i);
            if(j != -1)
                return j;
        }

        return -1;
    }

    public static int getLatestAvailableProtocolVersionForService(Context context, int i)
    {
        return getLatestAvailableProtocolVersionForAppInfoList(context, facebookAppInfoList, i);
    }

    public static boolean isErrorResult(Intent intent)
    {
        return intent.hasExtra("com.facebook.platform.status.ERROR_TYPE");
    }

    public static boolean isServiceDisabledResult20121101(Intent intent)
    {
        int i = intent.getIntExtra("com.facebook.platform.protocol.PROTOCOL_VERSION", 0);
        String s = intent.getStringExtra("com.facebook.platform.status.ERROR_TYPE");
        boolean flag = false;
        if(0x133060d == i)
        {
            boolean flag1 = "ServiceDisabled".equals(s);
            flag = false;
            if(flag1)
                flag = true;
        }
        return flag;
    }

    static Intent validateActivityIntent(Context context, Intent intent, NativeAppInfo nativeappinfo)
    {
        if(intent == null)
        {
            intent = null;
        } else
        {
            ResolveInfo resolveinfo = context.getPackageManager().resolveActivity(intent, 0);
            if(resolveinfo == null)
                return null;
            if(!nativeappinfo.validateSignature(context, resolveinfo.activityInfo.packageName))
                return null;
        }
        return intent;
    }

    static Intent validateServiceIntent(Context context, Intent intent, NativeAppInfo nativeappinfo)
    {
        if(intent == null)
        {
            intent = null;
        } else
        {
            ResolveInfo resolveinfo = context.getPackageManager().resolveService(intent, 0);
            if(resolveinfo == null)
                return null;
            if(!nativeappinfo.validateSignature(context, resolveinfo.serviceInfo.packageName))
                return null;
        }
        return intent;
    }

    public static final String ACTION_FEED_DIALOG = "com.facebook.platform.action.request.FEED_DIALOG";
    public static final String ACTION_FEED_DIALOG_REPLY = "com.facebook.platform.action.reply.FEED_DIALOG";
    public static final String ACTION_LOGIN_DIALOG = "com.facebook.platform.action.request.LOGIN_DIALOG";
    static final String ACTION_LOGIN_DIALOG_REPLY = "com.facebook.platform.action.reply.LOGIN_DIALOG";
    public static final String ACTION_OGACTIONPUBLISH_DIALOG = "com.facebook.platform.action.request.OGACTIONPUBLISH_DIALOG";
    public static final String ACTION_OGACTIONPUBLISH_DIALOG_REPLY = "com.facebook.platform.action.reply.OGACTIONPUBLISH_DIALOG";
    public static final String AUDIENCE_EVERYONE = "EVERYONE";
    public static final String AUDIENCE_FRIENDS = "ALL_FRIENDS";
    public static final String AUDIENCE_ME = "SELF";
    private static final String BASIC_INFO = "basic_info";
    private static final String CONTENT_SCHEME = "content://";
    public static final int DIALOG_REQUEST_CODE = 64207;
    public static final String ERROR_APPLICATION_ERROR = "ApplicationError";
    public static final String ERROR_NETWORK_ERROR = "NetworkError";
    public static final String ERROR_PERMISSION_DENIED = "PermissionDenied";
    public static final String ERROR_PROTOCOL_ERROR = "ProtocolError";
    public static final String ERROR_SERVICE_DISABLED = "ServiceDisabled";
    public static final String ERROR_UNKNOWN_ERROR = "UnknownError";
    public static final String ERROR_USER_CANCELED = "UserCanceled";
    public static final String EXTRA_ACCESS_TOKEN = "com.facebook.platform.extra.ACCESS_TOKEN";
    public static final String EXTRA_ACTION = "com.facebook.platform.extra.ACTION";
    public static final String EXTRA_ACTION_TYPE = "com.facebook.platform.extra.ACTION_TYPE";
    public static final String EXTRA_APPLICATION_ID = "com.facebook.platform.extra.APPLICATION_ID";
    public static final String EXTRA_APPLICATION_NAME = "com.facebook.platform.extra.APPLICATION_NAME";
    public static final String EXTRA_DATA_FAILURES_FATAL = "com.facebook.platform.extra.DATA_FAILURES_FATAL";
    public static final String EXTRA_DESCRIPTION = "com.facebook.platform.extra.DESCRIPTION";
    public static final String EXTRA_EXPIRES_SECONDS_SINCE_EPOCH = "com.facebook.platform.extra.EXPIRES_SECONDS_SINCE_EPOCH";
    public static final String EXTRA_FRIEND_TAGS = "com.facebook.platform.extra.FRIENDS";
    public static final String EXTRA_GET_INSTALL_DATA_PACKAGE = "com.facebook.platform.extra.INSTALLDATA_PACKAGE";
    public static final String EXTRA_IMAGE = "com.facebook.platform.extra.IMAGE";
    public static final String EXTRA_LINK = "com.facebook.platform.extra.LINK";
    public static final String EXTRA_PERMISSIONS = "com.facebook.platform.extra.PERMISSIONS";
    public static final String EXTRA_PHOTOS = "com.facebook.platform.extra.PHOTOS";
    public static final String EXTRA_PLACE_TAG = "com.facebook.platform.extra.PLACE";
    public static final String EXTRA_PREVIEW_PROPERTY_NAME = "com.facebook.platform.extra.PREVIEW_PROPERTY_NAME";
    public static final String EXTRA_PROTOCOL_ACTION = "com.facebook.platform.protocol.PROTOCOL_ACTION";
    public static final String EXTRA_PROTOCOL_CALL_ID = "com.facebook.platform.protocol.CALL_ID";
    public static final String EXTRA_PROTOCOL_VERSION = "com.facebook.platform.protocol.PROTOCOL_VERSION";
    static final String EXTRA_PROTOCOL_VERSIONS = "com.facebook.platform.extra.PROTOCOL_VERSIONS";
    public static final String EXTRA_REF = "com.facebook.platform.extra.REF";
    public static final String EXTRA_SUBTITLE = "com.facebook.platform.extra.SUBTITLE";
    public static final String EXTRA_TITLE = "com.facebook.platform.extra.TITLE";
    public static final String EXTRA_WRITE_PRIVACY = "com.facebook.platform.extra.WRITE_PRIVACY";
    private static final NativeAppInfo FACEBOOK_APP_INFO = new KatanaAppInfo();
    private static final String FACEBOOK_PROXY_AUTH_ACTIVITY = "com.facebook.katana.ProxyAuth";
    public static final String FACEBOOK_PROXY_AUTH_APP_ID_KEY = "client_id";
    public static final String FACEBOOK_PROXY_AUTH_E2E_KEY = "e2e";
    public static final String FACEBOOK_PROXY_AUTH_PERMISSIONS_KEY = "scope";
    private static final String FACEBOOK_TOKEN_REFRESH_ACTIVITY = "com.facebook.katana.platform.TokenRefreshService";
    public static final String IMAGE_URL_KEY = "url";
    public static final String IMAGE_USER_GENERATED_KEY = "user_generated";
    static final String INTENT_ACTION_PLATFORM_ACTIVITY = "com.facebook.platform.PLATFORM_ACTIVITY";
    static final String INTENT_ACTION_PLATFORM_SERVICE = "com.facebook.platform.PLATFORM_SERVICE";
    private static final List KNOWN_PROTOCOL_VERSIONS;
    public static final int MESSAGE_GET_ACCESS_TOKEN_REPLY = 0x10001;
    public static final int MESSAGE_GET_ACCESS_TOKEN_REQUEST = 0x10000;
    public static final int MESSAGE_GET_INSTALL_DATA_REPLY = 0x10005;
    public static final int MESSAGE_GET_INSTALL_DATA_REQUEST = 0x10004;
    static final int MESSAGE_GET_PROTOCOL_VERSIONS_REPLY = 0x10003;
    static final int MESSAGE_GET_PROTOCOL_VERSIONS_REQUEST = 0x10002;
    public static final int NO_PROTOCOL_AVAILABLE = -1;
    public static final String OPEN_GRAPH_CREATE_OBJECT_KEY = "fbsdk:create_object";
    private static final String PLATFORM_ASYNC_APPCALL_ACTION = "com.facebook.platform.AppCallResultBroadcast";
    private static final String PLATFORM_PROVIDER_VERSIONS = ".provider.PlatformProvider/versions";
    private static final String PLATFORM_PROVIDER_VERSION_COLUMN = "version";
    public static final int PROTOCOL_VERSION_20121101 = 0x133060d;
    public static final int PROTOCOL_VERSION_20130502 = 0x1332ac6;
    public static final int PROTOCOL_VERSION_20130618 = 0x1332b3a;
    public static final int PROTOCOL_VERSION_20131107 = 0x1332d23;
    public static final int PROTOCOL_VERSION_20140204 = 0x13350ac;
    public static final String STATUS_ERROR_CODE = "com.facebook.platform.status.ERROR_CODE";
    public static final String STATUS_ERROR_DESCRIPTION = "com.facebook.platform.status.ERROR_DESCRIPTION";
    public static final String STATUS_ERROR_JSON = "com.facebook.platform.status.ERROR_JSON";
    public static final String STATUS_ERROR_SUBCODE = "com.facebook.platform.status.ERROR_SUBCODE";
    public static final String STATUS_ERROR_TYPE = "com.facebook.platform.status.ERROR_TYPE";
    private static Map actionToAppInfoMap = buildActionToAppInfoMap();
    private static List facebookAppInfoList = buildFacebookAppList();

    static 
    {
        Integer ainteger[] = new Integer[5];
        ainteger[0] = Integer.valueOf(0x13350ac);
        ainteger[1] = Integer.valueOf(0x1332d23);
        ainteger[2] = Integer.valueOf(0x1332b3a);
        ainteger[3] = Integer.valueOf(0x1332ac6);
        ainteger[4] = Integer.valueOf(0x133060d);
        KNOWN_PROTOCOL_VERSIONS = Arrays.asList(ainteger);
    }
}
