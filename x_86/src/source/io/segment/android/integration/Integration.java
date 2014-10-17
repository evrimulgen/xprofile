// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package io.segment.android.integration;

import android.content.Context;
import io.segment.android.Logger;
import io.segment.android.errors.InvalidSettingsException;
import io.segment.android.models.EasyJSONObject;
import io.segment.android.utils.AndroidUtils;

// Referenced classes of package io.segment.android.integration:
//            IIntegration, IntegrationState

public abstract class Integration
    implements IIntegration
{

    public Integration()
    {
        state = IntegrationState.NOT_INITIALIZED;
        hasPermission = true;
    }

    private boolean changeState(IntegrationState integrationstate, IntegrationState aintegrationstate[])
    {
        int i;
        int j;
        if(state == integrationstate)
            return true;
        i = aintegrationstate.length;
        j = 0;
_L2:
        boolean flag = false;
        if(j < i)
        {
label0:
            {
                IntegrationState integrationstate1 = aintegrationstate[j];
                if(state != integrationstate1)
                    break label0;
                flag = true;
            }
        }
        if(flag)
        {
            state = integrationstate;
            return true;
        } else
        {
            Logger.w((new StringBuilder("Provider ")).append(getKey()).append(" cant be ").append(integrationstate).append(" because its in state ").append(state).append(".").toString());
            return false;
        }
        j++;
        if(true) goto _L2; else goto _L1
_L1:
    }

    public boolean checkPermission(Context context)
    {
        String as[] = getRequiredPermissions();
        int i = as.length;
        int j = 0;
        do
        {
            if(j >= i)
                return true;
            String s = as[j];
            if(!AndroidUtils.permissionGranted(context, s))
            {
                Object aobj[] = new Object[2];
                aobj[0] = getKey();
                aobj[1] = s;
                Logger.w(String.format("Provider %s requires permission %s but its not granted.", aobj));
                IntegrationState integrationstate = IntegrationState.INVALID;
                IntegrationState aintegrationstate[] = new IntegrationState[2];
                aintegrationstate[0] = IntegrationState.NOT_INITIALIZED;
                aintegrationstate[1] = IntegrationState.INITIALIZED;
                changeState(integrationstate, aintegrationstate);
                return false;
            }
            j++;
        } while(true);
    }

    public boolean disable()
    {
        IntegrationState integrationstate = IntegrationState.DISABLED;
        IntegrationState aintegrationstate[] = new IntegrationState[4];
        aintegrationstate[0] = IntegrationState.INITIALIZED;
        aintegrationstate[1] = IntegrationState.DISABLED;
        aintegrationstate[2] = IntegrationState.ENABLED;
        aintegrationstate[3] = IntegrationState.READY;
        return changeState(integrationstate, aintegrationstate);
    }

    public boolean enable()
    {
        IntegrationState integrationstate = IntegrationState.ENABLED;
        IntegrationState aintegrationstate[] = new IntegrationState[3];
        aintegrationstate[0] = IntegrationState.INITIALIZED;
        aintegrationstate[1] = IntegrationState.DISABLED;
        aintegrationstate[2] = IntegrationState.ENABLED;
        return changeState(integrationstate, aintegrationstate);
    }

    public abstract String getKey();

    public abstract String[] getRequiredPermissions();

    public EasyJSONObject getSettings()
    {
        return settings;
    }

    public IntegrationState getState()
    {
        return state;
    }

    public void initialize(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException
    {
        if(settings != null && !EasyJSONObject.equals(settings, easyjsonobject))
        {
            Object aobj[] = new Object[3];
            aobj[0] = getKey();
            aobj[1] = settings;
            aobj[2] = easyjsonobject;
            Logger.w(String.format("Provider %s settings changed. %s => %s", aobj));
        }
        try
        {
            validate(easyjsonobject);
            settings = easyjsonobject;
            IntegrationState integrationstate1 = IntegrationState.INITIALIZED;
            IntegrationState aintegrationstate1[] = new IntegrationState[2];
            aintegrationstate1[0] = IntegrationState.NOT_INITIALIZED;
            aintegrationstate1[1] = IntegrationState.INVALID;
            changeState(integrationstate1, aintegrationstate1);
            return;
        }
        catch(InvalidSettingsException invalidsettingsexception)
        {
            IntegrationState integrationstate = IntegrationState.INVALID;
            IntegrationState aintegrationstate[] = new IntegrationState[1];
            aintegrationstate[0] = IntegrationState.NOT_INITIALIZED;
            changeState(integrationstate, aintegrationstate);
            throw invalidsettingsexception;
        }
    }

    public boolean ready()
    {
        IntegrationState integrationstate = IntegrationState.READY;
        IntegrationState aintegrationstate[] = new IntegrationState[1];
        aintegrationstate[0] = IntegrationState.ENABLED;
        return changeState(integrationstate, aintegrationstate);
    }

    public void reset()
    {
        settings = null;
        state = IntegrationState.NOT_INITIALIZED;
    }

    public abstract void validate(EasyJSONObject easyjsonobject)
        throws InvalidSettingsException;

    protected boolean hasPermission;
    private EasyJSONObject settings;
    private IntegrationState state;
}
