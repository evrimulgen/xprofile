// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package de.keyboardsurfer.android.widget.crouton;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Handler;
import android.os.Message;
import android.view.*;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.Animation;
import android.widget.FrameLayout;
import java.util.*;
import java.util.concurrent.LinkedBlockingQueue;

// Referenced classes of package de.keyboardsurfer.android.widget.crouton:
//            Crouton, Configuration, LifecycleCallback

final class Manager extends Handler
{
    private static final class Messages
    {

        public static final int ADD_CROUTON_TO_VIEW = 0xc20074dd;
        public static final int DISPLAY_CROUTON = 0xc2007;
        public static final int REMOVE_CROUTON = 0xc2007de1;

        private Messages()
        {
        }
    }


    private Manager()
    {
    }

    private void addCroutonToView(final Crouton crouton)
    {
        if(!crouton.isShowing()) goto _L2; else goto _L1
_L1:
        return;
_L2:
        final View croutonView;
        Object obj;
        croutonView = crouton.getView();
        if(croutonView.getParent() == null)
        {
            obj = croutonView.getLayoutParams();
            if(obj == null)
                obj = new android.view.ViewGroup.MarginLayoutParams(-1, -2);
            if(crouton.getViewGroup() == null)
                break; /* Loop/switch isn't completed */
            ViewTreeObserver viewtreeobserver;
            if(crouton.getViewGroup() instanceof FrameLayout)
                crouton.getViewGroup().addView(croutonView, ((android.view.ViewGroup.LayoutParams) (obj)));
            else
                crouton.getViewGroup().addView(croutonView, 0, ((android.view.ViewGroup.LayoutParams) (obj)));
        }
_L5:
        croutonView.requestLayout();
        viewtreeobserver = croutonView.getViewTreeObserver();
        if(viewtreeobserver != null)
        {
            viewtreeobserver.addOnGlobalLayoutListener(new android.view.ViewTreeObserver.OnGlobalLayoutListener() {

                public void onGlobalLayout()
                {
                    if(android.os.Build.VERSION.SDK_INT < 16)
                        croutonView.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    else
                        croutonView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                    croutonView.startAnimation(crouton.getInAnimation());
                    Manager.announceForAccessibilityCompat(crouton.getActivity(), crouton.getText());
                    if(-1 != crouton.getConfiguration().durationInMilliseconds)
                        sendMessageDelayed(crouton, 0xc2007de1, (long)crouton.getConfiguration().durationInMilliseconds + crouton.getInAnimation().getDuration());
                }

                final Manager this$0;
                final Crouton val$crouton;
                final View val$croutonView;

            
            {
                this$0 = Manager.this;
                croutonView = view;
                crouton = crouton1;
                super();
            }
            }
);
            return;
        }
        if(true) goto _L1; else goto _L3
_L3:
        Activity activity = crouton.getActivity();
        if(activity == null || activity.isFinishing()) goto _L1; else goto _L4
_L4:
        handleTranslucentActionBar((android.view.ViewGroup.MarginLayoutParams)obj, activity);
        activity.addContentView(croutonView, ((android.view.ViewGroup.LayoutParams) (obj)));
          goto _L5
    }

    public static void announceForAccessibilityCompat(Context context, CharSequence charsequence)
    {
        AccessibilityManager accessibilitymanager;
label0:
        {
            if(android.os.Build.VERSION.SDK_INT >= 4)
            {
                accessibilitymanager = null;
                if(context != null)
                    accessibilitymanager = (AccessibilityManager)context.getSystemService("accessibility");
                if(accessibilitymanager != null && accessibilitymanager.isEnabled())
                    break label0;
            }
            return;
        }
        char c;
        AccessibilityEvent accessibilityevent;
        if(android.os.Build.VERSION.SDK_INT < 16)
            c = '\b';
        else
            c = '\u4000';
        accessibilityevent = AccessibilityEvent.obtain(c);
        accessibilityevent.getText().add(charsequence);
        accessibilityevent.setClassName(de/keyboardsurfer/android/widget/crouton/Manager.getName());
        accessibilityevent.setPackageName(context.getPackageName());
        accessibilitymanager.sendAccessibilityEvent(accessibilityevent);
    }

    private long calculateCroutonDuration(Crouton crouton)
    {
        return (long)crouton.getConfiguration().durationInMilliseconds + crouton.getInAnimation().getDuration() + crouton.getOutAnimation().getDuration();
    }

    private void displayCrouton()
    {
        if(!croutonQueue.isEmpty())
        {
            Crouton crouton = (Crouton)croutonQueue.peek();
            if(crouton.getActivity() == null)
                croutonQueue.poll();
            if(!crouton.isShowing())
            {
                sendMessage(crouton, 0xc20074dd);
                if(crouton.getLifecycleCallback() != null)
                {
                    crouton.getLifecycleCallback().onDisplayed();
                    return;
                }
            } else
            {
                sendMessageDelayed(crouton, 0xc2007, calculateCroutonDuration(crouton));
                return;
            }
        }
    }

    static Manager getInstance()
    {
        de/keyboardsurfer/android/widget/crouton/Manager;
        JVM INSTR monitorenter ;
        Manager manager;
        if(INSTANCE == null)
            INSTANCE = new Manager();
        manager = INSTANCE;
        de/keyboardsurfer/android/widget/crouton/Manager;
        JVM INSTR monitorexit ;
        return manager;
        Exception exception;
        exception;
        throw exception;
    }

    private void handleTranslucentActionBar(android.view.ViewGroup.MarginLayoutParams marginlayoutparams, Activity activity)
    {
        if(android.os.Build.VERSION.SDK_INT >= 19 && (0x4000000 & activity.getWindow().getAttributes().flags) == 0x4000000)
        {
            View view = activity.findViewById(Resources.getSystem().getIdentifier("action_bar_container", "id", "android"));
            if(view != null)
                marginlayoutparams.topMargin = view.getBottom();
        }
    }

    private void removeAllMessages()
    {
        removeMessages(0xc20074dd);
        removeMessages(0xc2007);
        removeMessages(0xc2007de1);
    }

    private void removeAllMessagesForCrouton(Crouton crouton)
    {
        removeMessages(0xc20074dd, crouton);
        removeMessages(0xc2007, crouton);
        removeMessages(0xc2007de1, crouton);
    }

    private void removeCroutonFromViewParent(Crouton crouton)
    {
        if(crouton.isShowing())
        {
            ViewGroup viewgroup = (ViewGroup)crouton.getView().getParent();
            if(viewgroup != null)
                viewgroup.removeView(crouton.getView());
        }
    }

    private void sendMessage(Crouton crouton, int i)
    {
        Message message = obtainMessage(i);
        message.obj = crouton;
        sendMessage(message);
    }

    private void sendMessageDelayed(Crouton crouton, int i, long l)
    {
        Message message = obtainMessage(i);
        message.obj = crouton;
        sendMessageDelayed(message, l);
    }

    void add(Crouton crouton)
    {
        croutonQueue.add(crouton);
        displayCrouton();
    }

    void clearCroutonQueue()
    {
        removeAllMessages();
        for(Iterator iterator = croutonQueue.iterator(); iterator.hasNext(); removeCroutonFromViewParent((Crouton)iterator.next()));
        croutonQueue.clear();
    }

    void clearCroutonsForActivity(Activity activity)
    {
        Iterator iterator = croutonQueue.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Crouton crouton = (Crouton)iterator.next();
            if(crouton.getActivity() != null && crouton.getActivity().equals(activity))
            {
                removeCroutonFromViewParent(crouton);
                removeAllMessagesForCrouton(crouton);
                iterator.remove();
            }
        } while(true);
    }

    public void handleMessage(Message message)
    {
        Crouton crouton = (Crouton)message.obj;
        if(crouton != null)
        {
            switch(message.what)
            {
            default:
                super.handleMessage(message);
                return;

            case 794631: 
                displayCrouton();
                return;

            case -1040157475: 
                addCroutonToView(crouton);
                return;

            case -1040155167: 
                removeCrouton(crouton);
                break;
            }
            if(crouton.getLifecycleCallback() != null)
            {
                crouton.getLifecycleCallback().onRemoved();
                return;
            }
        }
    }

    protected void removeCrouton(Crouton crouton)
    {
        View view = crouton.getView();
        ViewGroup viewgroup = (ViewGroup)view.getParent();
        if(viewgroup != null)
        {
            view.startAnimation(crouton.getOutAnimation());
            Crouton crouton1 = (Crouton)croutonQueue.poll();
            viewgroup.removeView(view);
            if(crouton1 != null)
            {
                crouton1.detachActivity();
                crouton1.detachViewGroup();
                if(crouton1.getLifecycleCallback() != null)
                    crouton1.getLifecycleCallback().onRemoved();
                crouton1.detachLifecycleCallback();
            }
            sendMessageDelayed(crouton, 0xc2007, crouton.getOutAnimation().getDuration());
        }
    }

    void removeCroutonImmediately(Crouton crouton)
    {
        if(crouton.getActivity() != null && crouton.getView() != null && crouton.getView().getParent() != null)
        {
            ((ViewGroup)crouton.getView().getParent()).removeView(crouton.getView());
            removeAllMessagesForCrouton(crouton);
        }
        Iterator iterator = croutonQueue.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            Crouton crouton1 = (Crouton)iterator.next();
            if(!crouton1.equals(crouton) || crouton1.getActivity() == null)
                continue;
            removeCroutonFromViewParent(crouton);
            removeAllMessagesForCrouton(crouton1);
            iterator.remove();
            break;
        } while(true);
    }

    public String toString()
    {
        return (new StringBuilder()).append("Manager{croutonQueue=").append(croutonQueue).append('}').toString();
    }

    private static Manager INSTANCE;
    private final Queue croutonQueue = new LinkedBlockingQueue();

}
