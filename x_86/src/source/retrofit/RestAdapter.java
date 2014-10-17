// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package retrofit;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.*;
import retrofit.client.Client;
import retrofit.client.Header;
import retrofit.client.Request;
import retrofit.client.Response;
import retrofit.converter.ConversionException;
import retrofit.converter.Converter;
import retrofit.mime.MimeUtil;
import retrofit.mime.TypedByteArray;
import retrofit.mime.TypedInput;
import retrofit.mime.TypedOutput;

// Referenced classes of package retrofit:
//            RestMethodInfo, Utils, ErrorHandler, Profiler, 
//            RequestInterceptor, RxSupport, Endpoint, Platform, 
//            Endpoints, Server, RetrofitError, RequestBuilder, 
//            ResponseWrapper, ExceptionCatchingTypedInput, RequestInterceptorTape, Callback, 
//            CallbackRunnable

public class RestAdapter
{
    public static class Builder
    {

        private void ensureSaneDefaults()
        {
            if(converter == null)
                converter = Platform.get().defaultConverter();
            if(clientProvider == null)
                clientProvider = Platform.get().defaultClient();
            if(httpExecutor == null)
                httpExecutor = Platform.get().defaultHttpExecutor();
            if(callbackExecutor == null)
                callbackExecutor = Platform.get().defaultCallbackExecutor();
            if(errorHandler == null)
                errorHandler = ErrorHandler.DEFAULT;
            if(log == null)
                log = Platform.get().defaultLog();
            if(requestInterceptor == null)
                requestInterceptor = RequestInterceptor.NONE;
        }

        public RestAdapter build()
        {
            if(endpoint == null)
            {
                throw new IllegalArgumentException("Endpoint may not be null.");
            } else
            {
                ensureSaneDefaults();
                return new RestAdapter(endpoint, clientProvider, httpExecutor, callbackExecutor, requestInterceptor, converter, profiler, errorHandler, log, logLevel);
            }
        }

        public Builder setClient(retrofit.client.Client.Provider provider)
        {
            if(provider == null)
            {
                throw new NullPointerException("Client provider may not be null.");
            } else
            {
                clientProvider = provider;
                return this;
            }
        }

        public Builder setClient(Client client)
        {
            if(client == null)
                throw new NullPointerException("Client may not be null.");
            else
                return setClient(client. new retrofit.client.Client.Provider() {

                    public Client get()
                    {
                        return client;
                    }

                    final Builder this$0;
                    final Client val$client;

            
            {
                this$0 = final_builder;
                client = Client.this;
                super();
            }
                }
);
        }

        public Builder setConverter(Converter converter1)
        {
            if(converter1 == null)
            {
                throw new NullPointerException("Converter may not be null.");
            } else
            {
                converter = converter1;
                return this;
            }
        }

        public Builder setEndpoint(String s)
        {
            if(s == null || s.trim().length() == 0)
            {
                throw new NullPointerException("Endpoint may not be blank.");
            } else
            {
                endpoint = Endpoints.newFixedEndpoint(s);
                return this;
            }
        }

        public Builder setEndpoint(Endpoint endpoint1)
        {
            if(endpoint1 == null)
            {
                throw new NullPointerException("Endpoint may not be null.");
            } else
            {
                endpoint = endpoint1;
                return this;
            }
        }

        public Builder setErrorHandler(ErrorHandler errorhandler)
        {
            if(errorhandler == null)
            {
                throw new NullPointerException("Error handler may not be null.");
            } else
            {
                errorHandler = errorhandler;
                return this;
            }
        }

        public Builder setExecutors(Executor executor, Executor executor1)
        {
            if(executor == null)
                throw new NullPointerException("HTTP executor may not be null.");
            if(executor1 == null)
                executor1 = new Utils.SynchronousExecutor();
            httpExecutor = executor;
            callbackExecutor = executor1;
            return this;
        }

        public Builder setLog(Log log1)
        {
            if(log1 == null)
            {
                throw new NullPointerException("Log may not be null.");
            } else
            {
                log = log1;
                return this;
            }
        }

        public Builder setLogLevel(LogLevel loglevel)
        {
            if(loglevel == null)
            {
                throw new NullPointerException("Log level may not be null.");
            } else
            {
                logLevel = loglevel;
                return this;
            }
        }

        public Builder setProfiler(Profiler profiler1)
        {
            if(profiler1 == null)
            {
                throw new NullPointerException("Profiler may not be null.");
            } else
            {
                profiler = profiler1;
                return this;
            }
        }

        public Builder setRequestInterceptor(RequestInterceptor requestinterceptor)
        {
            if(requestinterceptor == null)
            {
                throw new NullPointerException("Request interceptor may not be null.");
            } else
            {
                requestInterceptor = requestinterceptor;
                return this;
            }
        }

        public Builder setServer(String s)
        {
            return setEndpoint(s);
        }

        public Builder setServer(Server server1)
        {
            return setEndpoint(server1);
        }

        private Executor callbackExecutor;
        private retrofit.client.Client.Provider clientProvider;
        private Converter converter;
        private Endpoint endpoint;
        private ErrorHandler errorHandler;
        private Executor httpExecutor;
        private Log log;
        private LogLevel logLevel;
        private Profiler profiler;
        private RequestInterceptor requestInterceptor;

        public Builder()
        {
            logLevel = LogLevel.NONE;
        }
    }

    public static interface Log
    {

        public abstract void log(String s);

        public static final Log NONE = new Log() {

            public void log(String s)
            {
            }

        }
;

    }

    public static final class LogLevel extends Enum
    {

        public static LogLevel valueOf(String s)
        {
            return (LogLevel)Enum.valueOf(retrofit/RestAdapter$LogLevel, s);
        }

        public static LogLevel[] values()
        {
            return (LogLevel[])$VALUES.clone();
        }

        public boolean log()
        {
            return this != NONE;
        }

        private static final LogLevel $VALUES[];
        public static final LogLevel BASIC;
        public static final LogLevel FULL;
        public static final LogLevel HEADERS;
        public static final LogLevel NONE;

        static 
        {
            NONE = new LogLevel("NONE", 0);
            BASIC = new LogLevel("BASIC", 1);
            HEADERS = new LogLevel("HEADERS", 2);
            FULL = new LogLevel("FULL", 3);
            LogLevel aloglevel[] = new LogLevel[4];
            aloglevel[0] = NONE;
            aloglevel[1] = BASIC;
            aloglevel[2] = HEADERS;
            aloglevel[3] = FULL;
            $VALUES = aloglevel;
        }

        private LogLevel(String s, int i)
        {
            super(s, i);
        }
    }

    private class RestHandler
        implements InvocationHandler
    {

        private Object invokeRequest(RequestInterceptor requestinterceptor, RestMethodInfo restmethodinfo, Object aobj[])
        {
            String s;
            String s1;
            restmethodinfo.init();
            s = server.getUrl();
            s1 = s;
            Request request;
            Profiler profiler1;
            RequestBuilder requestbuilder = new RequestBuilder(s, restmethodinfo, converter);
            requestbuilder.setArguments(aobj);
            requestinterceptor.intercept(requestbuilder);
            request = requestbuilder.build();
            s1 = request.getUrl();
            if(!restmethodinfo.isSynchronous)
            {
                Thread thread = Thread.currentThread();
                StringBuilder stringbuilder = (new StringBuilder()).append("Retrofit-");
                int j = s.length();
                thread.setName(stringbuilder.append(s1.substring(j)).toString());
            }
            if(logLevel.log())
                request = logAndReplaceRequest("HTTP", request);
            profiler1 = profiler;
            Object obj;
            obj = null;
            if(profiler1 == null)
                break MISSING_BLOCK_LABEL_179;
            obj = profiler.beforeCall();
            Response response;
            int i;
            java.lang.reflect.Type type;
            long l = System.nanoTime();
            response = clientProvider.get().execute(request);
            long l1 = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - l);
            i = response.getStatus();
            if(profiler != null)
            {
                Profiler.RequestInformation requestinformation = RestAdapter.getRequestInfo(s, restmethodinfo, request);
                profiler.afterCall(requestinformation, l1, i, obj);
            }
            if(logLevel.log())
                response = logAndReplaceResponse(s1, response, l1);
            type = restmethodinfo.responseObjectType;
            if(i < 200 || i >= 300)
                break MISSING_BLOCK_LABEL_634;
            if(!type.equals(retrofit/client/Response)) goto _L2; else goto _L1
_L1:
            Response response3;
            boolean flag1;
            response3 = Utils.readBodyToBytesIfNecessary(response);
            flag1 = restmethodinfo.isSynchronous;
            if(!flag1) goto _L4; else goto _L3
_L3:
            Object obj1;
            if(!restmethodinfo.isSynchronous)
                Thread.currentThread().setName("Retrofit-Idle");
            obj1 = response3;
_L6:
            return obj1;
_L4:
            obj1 = new ResponseWrapper(response3, response3);
            if(restmethodinfo.isSynchronous) goto _L6; else goto _L5
_L5:
            Thread.currentThread().setName("Retrofit-Idle");
            return obj1;
_L2:
            TypedInput typedinput = response.getBody();
            if(typedinput != null)
                break MISSING_BLOCK_LABEL_439;
            obj1 = new ResponseWrapper(response, null);
            if(restmethodinfo.isSynchronous) goto _L6; else goto _L7
_L7:
            Thread.currentThread().setName("Retrofit-Idle");
            return obj1;
            ExceptionCatchingTypedInput exceptioncatchingtypedinput = new ExceptionCatchingTypedInput(typedinput);
            boolean flag;
            obj1 = converter.fromBody(exceptioncatchingtypedinput, type);
            flag = restmethodinfo.isSynchronous;
            if(!flag)
                break MISSING_BLOCK_LABEL_497;
            if(restmethodinfo.isSynchronous) goto _L6; else goto _L8
_L8:
            Thread.currentThread().setName("Retrofit-Idle");
            return obj1;
            ResponseWrapper responsewrapper = new ResponseWrapper(response, obj1);
            if(!restmethodinfo.isSynchronous)
                Thread.currentThread().setName("Retrofit-Idle");
            return responsewrapper;
            ConversionException conversionexception;
            conversionexception;
            if(exceptioncatchingtypedinput.threwException())
                throw exceptioncatchingtypedinput.getThrownException();
            break MISSING_BLOCK_LABEL_569;
            RetrofitError retrofiterror;
            retrofiterror;
            throw retrofiterror;
            Exception exception;
            exception;
            if(!restmethodinfo.isSynchronous)
                Thread.currentThread().setName("Retrofit-Idle");
            throw exception;
            Response response2 = Utils.replaceResponseBody(response, null);
            Converter converter2 = converter;
            throw RetrofitError.conversionError(s1, response2, converter2, type, conversionexception);
            IOException ioexception;
            ioexception;
            if(logLevel.log())
                logException(ioexception, s1);
            throw RetrofitError.networkError(s1, ioexception);
            Response response1 = Utils.readBodyToBytesIfNecessary(response);
            Converter converter1 = converter;
            throw RetrofitError.httpError(s1, response1, converter1, type);
            Throwable throwable;
            throwable;
            if(logLevel.log())
                logException(throwable, s1);
            throw RetrofitError.unexpectedError(s1, throwable);
        }

        public Object invoke(Object obj, Method method, Object aobj[])
            throws Throwable
        {
            final RestMethodInfo methodInfo;
            final RequestInterceptorTape interceptorTape;
label0:
            {
label1:
                {
                    if(method.getDeclaringClass() == java/lang/Object)
                        return method.invoke(this, aobj);
                    methodInfo = RestAdapter.getMethodInfo(methodDetailsCache, method);
                    if(methodInfo.isSynchronous)
                    {
                        Object obj1;
                        try
                        {
                            obj1 = invokeRequest(requestInterceptor, methodInfo, aobj);
                        }
                        catch(RetrofitError retrofiterror)
                        {
                            Throwable throwable = errorHandler.handleError(retrofiterror);
                            if(throwable == null)
                                throw new IllegalStateException("Error handler returned null for wrapped exception.", retrofiterror);
                            else
                                throw throwable;
                        }
                        return obj1;
                    }
                    if(httpExecutor == null || callbackExecutor == null)
                        throw new IllegalStateException("Asynchronous invocation requires calling setExecutors.");
                    interceptorTape = new RequestInterceptorTape();
                    requestInterceptor.intercept(interceptorTape);
                    if(!methodInfo.isObservable)
                        break label0;
                    if(rxSupport == null)
                    {
                        if(!Platform.HAS_RX_JAVA)
                            break label1;
                        rxSupport = new RxSupport(httpExecutor, errorHandler);
                    }
                    return rxSupport.createRequestObservable(((_cls1) (aobj)). new Callable() {

                        public volatile Object call()
                            throws Exception
                        {
                            return call();
                        }

                        public ResponseWrapper call()
                            throws Exception
                        {
                            return (ResponseWrapper)invokeRequest(interceptorTape, methodInfo, args);
                        }

                        final RestHandler this$1;
                        final Object val$args[];
                        final RequestInterceptorTape val$interceptorTape;
                        final RestMethodInfo val$methodInfo;

            
            {
                this$1 = final_resthandler;
                interceptorTape = requestinterceptortape;
                methodInfo = restmethodinfo;
                args = _5B_Ljava.lang.Object_3B_.this;
                super();
            }
                    }
);
                }
                throw new IllegalStateException("Observable method found but no RxJava on classpath");
            }
            final Callback final_callback = (Callback)aobj[-1 + aobj.length];
            httpExecutor.execute(errorHandler. new CallbackRunnable(interceptorTape, methodInfo, aobj) {

                public ResponseWrapper obtainResponse()
                {
                    return (ResponseWrapper)invokeRequest(interceptorTape, methodInfo, args);
                }

                final RestHandler this$1;
                final Object val$args[];
                final RequestInterceptorTape val$interceptorTape;
                final RestMethodInfo val$methodInfo;

            
            {
                this$1 = final_resthandler;
                interceptorTape = requestinterceptortape;
                methodInfo = restmethodinfo;
                args = aobj;
                super(final_callback, final_executor, ErrorHandler.this);
            }
            }
);
            return null;
        }

        private final Map methodDetailsCache;
        final RestAdapter this$0;


        RestHandler(Map map)
        {
            this$0 = RestAdapter.this;
            super();
            methodDetailsCache = map;
        }
    }


    private RestAdapter(Endpoint endpoint, retrofit.client.Client.Provider provider, Executor executor, Executor executor1, RequestInterceptor requestinterceptor, Converter converter1, Profiler profiler1, 
            ErrorHandler errorhandler, Log log1, LogLevel loglevel)
    {
        serviceMethodInfoCache = new LinkedHashMap();
        server = endpoint;
        clientProvider = provider;
        httpExecutor = executor;
        callbackExecutor = executor1;
        requestInterceptor = requestinterceptor;
        converter = converter1;
        profiler = profiler1;
        errorHandler = errorhandler;
        log = log1;
        logLevel = loglevel;
    }


    static RestMethodInfo getMethodInfo(Map map, Method method)
    {
        map;
        JVM INSTR monitorenter ;
        RestMethodInfo restmethodinfo = (RestMethodInfo)map.get(method);
        if(restmethodinfo != null)
            break MISSING_BLOCK_LABEL_35;
        restmethodinfo = new RestMethodInfo(method);
        map.put(method, restmethodinfo);
        map;
        JVM INSTR monitorexit ;
        return restmethodinfo;
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private static Profiler.RequestInformation getRequestInfo(String s, RestMethodInfo restmethodinfo, Request request)
    {
        long l = 0L;
        TypedOutput typedoutput = request.getBody();
        String s1 = null;
        if(typedoutput != null)
        {
            l = typedoutput.length();
            s1 = typedoutput.mimeType();
        }
        return new Profiler.RequestInformation(restmethodinfo.requestMethod, s, restmethodinfo.requestUrl, l, s1);
    }

    private Response logAndReplaceResponse(String s, Response response, long l)
        throws IOException
    {
        Log log1 = log;
        Object aobj[] = new Object[3];
        aobj[0] = Integer.valueOf(response.getStatus());
        aobj[1] = s;
        aobj[2] = Long.valueOf(l);
        log1.log(String.format("<--- HTTP %s %s (%sms)", aobj));
        if(logLevel.ordinal() >= LogLevel.HEADERS.ordinal())
        {
            Header header;
            for(Iterator iterator = response.getHeaders().iterator(); iterator.hasNext(); log.log(header.toString()))
                header = (Header)iterator.next();

            long l1 = 0L;
            TypedInput typedinput = response.getBody();
            if(typedinput != null)
            {
                l1 = typedinput.length();
                if(logLevel.ordinal() >= LogLevel.FULL.ordinal())
                {
                    if(!response.getHeaders().isEmpty())
                        log.log("");
                    if(!(typedinput instanceof TypedByteArray))
                    {
                        response = Utils.readBodyToBytesIfNecessary(response);
                        typedinput = response.getBody();
                    }
                    byte abyte0[] = ((TypedByteArray)typedinput).getBytes();
                    l1 = abyte0.length;
                    String s1 = MimeUtil.parseCharset(typedinput.mimeType());
                    log.log(new String(abyte0, s1));
                }
            }
            Log log2 = log;
            Object aobj1[] = new Object[1];
            aobj1[0] = Long.valueOf(l1);
            log2.log(String.format("<--- END HTTP (%s-byte body)", aobj1));
        }
        return response;
    }

    public Object create(Class class1)
    {
        Utils.validateServiceClass(class1);
        return Proxy.newProxyInstance(class1.getClassLoader(), new Class[] {
            class1
        }, new RestHandler(getMethodInfoCache(class1)));
    }

    public LogLevel getLogLevel()
    {
        return logLevel;
    }

    Map getMethodInfoCache(Class class1)
    {
        Map map = serviceMethodInfoCache;
        map;
        JVM INSTR monitorenter ;
        Object obj = (Map)serviceMethodInfoCache.get(class1);
        if(obj != null)
            break MISSING_BLOCK_LABEL_49;
        obj = new LinkedHashMap();
        serviceMethodInfoCache.put(class1, obj);
        map;
        JVM INSTR monitorexit ;
        return ((Map) (obj));
        Exception exception;
        exception;
        map;
        JVM INSTR monitorexit ;
        throw exception;
    }

    Request logAndReplaceRequest(String s, Request request)
        throws IOException
    {
        Log log1 = log;
        Object aobj[] = new Object[3];
        aobj[0] = s;
        aobj[1] = request.getMethod();
        aobj[2] = request.getUrl();
        log1.log(String.format("---> %s %s %s", aobj));
        if(logLevel.ordinal() >= LogLevel.HEADERS.ordinal())
        {
            Header header;
            for(Iterator iterator = request.getHeaders().iterator(); iterator.hasNext(); log.log(header.toString()))
                header = (Header)iterator.next();

            long l = 0L;
            TypedOutput typedoutput = request.getBody();
            if(typedoutput != null)
            {
                l = typedoutput.length();
                if(logLevel.ordinal() >= LogLevel.FULL.ordinal())
                {
                    if(!request.getHeaders().isEmpty())
                        log.log("");
                    if(!(typedoutput instanceof TypedByteArray))
                    {
                        request = Utils.readBodyToBytesIfNecessary(request);
                        typedoutput = request.getBody();
                    }
                    byte abyte0[] = ((TypedByteArray)typedoutput).getBytes();
                    l = abyte0.length;
                    String s1 = MimeUtil.parseCharset(typedoutput.mimeType());
                    log.log(new String(abyte0, s1));
                }
            }
            Log log2 = log;
            Object aobj1[] = new Object[2];
            aobj1[0] = s;
            aobj1[1] = Long.valueOf(l);
            log2.log(String.format("---> END %s (%s-byte body)", aobj1));
        }
        return request;
    }

    void logException(Throwable throwable, String s)
    {
        log.log(String.format("---- ERROR %s", new Object[] {
            s
        }));
        StringWriter stringwriter = new StringWriter();
        throwable.printStackTrace(new PrintWriter(stringwriter));
        log.log(stringwriter.toString());
        log.log("---- END ERROR");
    }

    public void setLogLevel(LogLevel loglevel)
    {
        if(logLevel == null)
        {
            throw new NullPointerException("Log level may not be null.");
        } else
        {
            logLevel = loglevel;
            return;
        }
    }

    static final String IDLE_THREAD_NAME = "Retrofit-Idle";
    static final String THREAD_PREFIX = "Retrofit-";
    final Executor callbackExecutor;
    private final retrofit.client.Client.Provider clientProvider;
    final Converter converter;
    final ErrorHandler errorHandler;
    final Executor httpExecutor;
    final Log log;
    volatile LogLevel logLevel;
    private final Profiler profiler;
    final RequestInterceptor requestInterceptor;
    private RxSupport rxSupport;
    final Endpoint server;
    private final Map serviceMethodInfoCache;



/*
    static RxSupport access$002(RestAdapter restadapter, RxSupport rxsupport)
    {
        restadapter.rxSupport = rxsupport;
        return rxsupport;
    }

*/




}
