// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.newrelic.agent.android.instrumentation.httpclient;

import com.newrelic.agent.android.instrumentation.TransactionState;
import com.newrelic.agent.android.instrumentation.TransactionStateUtil;
import java.io.IOException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;

public final class ResponseHandlerImpl
    implements ResponseHandler
{

    private ResponseHandlerImpl(ResponseHandler responsehandler, TransactionState transactionstate)
    {
        impl = responsehandler;
        transactionState = transactionstate;
    }

    public static ResponseHandler wrap(ResponseHandler responsehandler, TransactionState transactionstate)
    {
        return new ResponseHandlerImpl(responsehandler, transactionstate);
    }

    public Object handleResponse(HttpResponse httpresponse)
        throws ClientProtocolException, IOException
    {
        TransactionStateUtil.inspectAndInstrument(transactionState, httpresponse);
        return impl.handleResponse(httpresponse);
    }

    private final ResponseHandler impl;
    private final TransactionState transactionState;
}
