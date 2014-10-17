// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.facebook.widget;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.*;
import android.view.animation.AlphaAnimation;
import android.widget.*;
import com.facebook.*;
import com.facebook.internal.SessionTracker;
import com.facebook.model.GraphObject;
import com.newrelic.agent.android.api.v2.TraceFieldInterface;
import com.newrelic.agent.android.background.ApplicationStateMonitor;
import com.newrelic.agent.android.tracing.TraceMachine;
import java.util.*;

// Referenced classes of package com.facebook.widget:
//            GraphObjectAdapter, SimpleGraphObjectCursor, GraphObjectPagingLoader

public abstract class PickerFragment extends Fragment
    implements TraceFieldInterface
{
    public static interface GraphObjectFilter
    {

        public abstract boolean includeItem(Object obj);
    }

    abstract class LoadingStrategy
    {

        public void attach(GraphObjectAdapter graphobjectadapter)
        {
            loader = (GraphObjectPagingLoader)getLoaderManager().initLoader(0, null, new android.support.v4.app.LoaderManager.LoaderCallbacks() {

                public Loader onCreateLoader(int i, Bundle bundle)
                {
                    return LoadingStrategy.this.onCreateLoader();
                }

                public void onLoadFinished(Loader loader, SimpleGraphObjectCursor simplegraphobjectcursor)
                {
                    if(loader != LoadingStrategy.this.loader)
                    {
                        throw new FacebookException("Received callback for unknown loader.");
                    } else
                    {
                        LoadingStrategy.this.onLoadFinished((GraphObjectPagingLoader)loader, simplegraphobjectcursor);
                        return;
                    }
                }

                public volatile void onLoadFinished(Loader loader, Object obj)
                {
                    onLoadFinished(loader, (SimpleGraphObjectCursor)obj);
                }

                public void onLoaderReset(Loader loader)
                {
                    if(loader != LoadingStrategy.this.loader)
                    {
                        throw new FacebookException("Received callback for unknown loader.");
                    } else
                    {
                        onLoadReset((GraphObjectPagingLoader)loader);
                        return;
                    }
                }

                final LoadingStrategy this$1;

            
            {
                this$1 = LoadingStrategy.this;
                super();
            }
            }
);
            loader.setOnErrorListener(new GraphObjectPagingLoader.OnErrorListener() {

                public void onError(FacebookException facebookexception, GraphObjectPagingLoader graphobjectpagingloader)
                {
                    hideActivityCircle();
                    if(onErrorListener != null)
                        onErrorListener.onError(_fld0, facebookexception);
                }

                final LoadingStrategy this$1;

            
            {
                this$1 = LoadingStrategy.this;
                super();
            }
            }
);
            adapter = graphobjectadapter;
            adapter.changeCursor(loader.getCursor());
            adapter.setOnErrorListener(new GraphObjectAdapter.OnErrorListener() {

                public void onError(GraphObjectAdapter graphobjectadapter, FacebookException facebookexception)
                {
                    if(onErrorListener != null)
                        onErrorListener.onError(_fld0, facebookexception);
                }

                final LoadingStrategy this$1;

            
            {
                this$1 = LoadingStrategy.this;
                super();
            }
            }
);
        }

        public void clearResults()
        {
            if(loader != null)
                loader.clearResults();
        }

        public void detach()
        {
            adapter.setDataNeededListener(null);
            adapter.setOnErrorListener(null);
            loader.setOnErrorListener(null);
            loader = null;
            adapter = null;
        }

        public boolean isDataPresentOrLoading()
        {
            return !adapter.isEmpty() || loader.isLoading();
        }

        protected GraphObjectPagingLoader onCreateLoader()
        {
            return new GraphObjectPagingLoader(getActivity(), graphObjectClass);
        }

        protected void onLoadFinished(GraphObjectPagingLoader graphobjectpagingloader, SimpleGraphObjectCursor simplegraphobjectcursor)
        {
            updateAdapter(simplegraphobjectcursor);
        }

        protected void onLoadReset(GraphObjectPagingLoader graphobjectpagingloader)
        {
            adapter.changeCursor(null);
        }

        protected void onStartLoading(GraphObjectPagingLoader graphobjectpagingloader, Request request)
        {
            displayActivityCircle();
        }

        public void startLoading(Request request)
        {
            if(loader != null)
            {
                loader.startLoading(request, true);
                onStartLoading(loader, request);
            }
        }

        protected static final int CACHED_RESULT_REFRESH_DELAY = 2000;
        protected GraphObjectAdapter adapter;
        protected GraphObjectPagingLoader loader;
        final PickerFragment this$0;

        LoadingStrategy()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }

    class MultiSelectionStrategy extends SelectionStrategy
    {

        public void clear()
        {
            selectedIds.clear();
        }

        public Collection getSelectedIds()
        {
            return selectedIds;
        }

        boolean isEmpty()
        {
            return selectedIds.isEmpty();
        }

        boolean isSelected(String s)
        {
            return s != null && selectedIds.contains(s);
        }

        void readSelectionFromBundle(Bundle bundle, String s)
        {
            if(bundle != null)
            {
                String s1 = bundle.getString(s);
                if(s1 != null)
                {
                    String as[] = TextUtils.split(s1, ",");
                    selectedIds.clear();
                    Collections.addAll(selectedIds, as);
                }
            }
        }

        void saveSelectionToBundle(Bundle bundle, String s)
        {
            if(!selectedIds.isEmpty())
                bundle.putString(s, TextUtils.join(",", selectedIds));
        }

        boolean shouldShowCheckBoxIfUnselected()
        {
            return true;
        }

        void toggleSelection(String s)
        {
label0:
            {
                if(s != null)
                {
                    if(!selectedIds.contains(s))
                        break label0;
                    selectedIds.remove(s);
                }
                return;
            }
            selectedIds.add(s);
        }

        private Set selectedIds;
        final PickerFragment this$0;

        MultiSelectionStrategy()
        {
            this$0 = PickerFragment.this;
            super();
            selectedIds = new HashSet();
        }
    }

    public static interface OnDataChangedListener
    {

        public abstract void onDataChanged(PickerFragment pickerfragment);
    }

    public static interface OnDoneButtonClickedListener
    {

        public abstract void onDoneButtonClicked(PickerFragment pickerfragment);
    }

    public static interface OnErrorListener
    {

        public abstract void onError(PickerFragment pickerfragment, FacebookException facebookexception);
    }

    public static interface OnSelectionChangedListener
    {

        public abstract void onSelectionChanged(PickerFragment pickerfragment);
    }

    abstract class PickerFragmentAdapter extends GraphObjectAdapter
    {

        boolean isGraphObjectSelected(String s)
        {
            return selectionStrategy.isSelected(s);
        }

        void updateCheckboxState(CheckBox checkbox, boolean flag)
        {
            checkbox.setChecked(flag);
            int i;
            if(flag || selectionStrategy.shouldShowCheckBoxIfUnselected())
                i = 0;
            else
                i = 8;
            checkbox.setVisibility(i);
        }

        final PickerFragment this$0;

        public PickerFragmentAdapter(Context context)
        {
            this$0 = PickerFragment.this;
            super(context);
        }
    }

    abstract class SelectionStrategy
    {

        abstract void clear();

        abstract Collection getSelectedIds();

        abstract boolean isEmpty();

        abstract boolean isSelected(String s);

        abstract void readSelectionFromBundle(Bundle bundle, String s);

        abstract void saveSelectionToBundle(Bundle bundle, String s);

        abstract boolean shouldShowCheckBoxIfUnselected();

        abstract void toggleSelection(String s);

        final PickerFragment this$0;

        SelectionStrategy()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }

    class SingleSelectionStrategy extends SelectionStrategy
    {

        public void clear()
        {
            selectedId = null;
        }

        public Collection getSelectedIds()
        {
            String as[] = new String[1];
            as[0] = selectedId;
            return Arrays.asList(as);
        }

        boolean isEmpty()
        {
            return selectedId == null;
        }

        boolean isSelected(String s)
        {
            return selectedId != null && s != null && selectedId.equals(s);
        }

        void readSelectionFromBundle(Bundle bundle, String s)
        {
            if(bundle != null)
                selectedId = bundle.getString(s);
        }

        void saveSelectionToBundle(Bundle bundle, String s)
        {
            if(!TextUtils.isEmpty(selectedId))
                bundle.putString(s, selectedId);
        }

        boolean shouldShowCheckBoxIfUnselected()
        {
            return false;
        }

        void toggleSelection(String s)
        {
            if(selectedId != null && selectedId.equals(s))
            {
                selectedId = null;
                return;
            } else
            {
                selectedId = s;
                return;
            }
        }

        private String selectedId;
        final PickerFragment this$0;

        SingleSelectionStrategy()
        {
            this$0 = PickerFragment.this;
            super();
        }
    }


    PickerFragment(Class class1, int i, Bundle bundle)
    {
        showPictures = true;
        showTitleBar = true;
        extraFields = new HashSet();
        onScrollListener = new android.widget.AbsListView.OnScrollListener() {

            public void onScroll(AbsListView abslistview, int j, int k, int l)
            {
                reprioritizeDownloads();
            }

            public void onScrollStateChanged(AbsListView abslistview, int j)
            {
            }

            final PickerFragment this$0;

            
            {
                this$0 = PickerFragment.this;
                super();
            }
        }
;
        graphObjectClass = class1;
        layout = i;
        setPickerFragmentSettingsFromBundle(bundle);
    }

    private void clearResults()
    {
        if(adapter != null)
        {
            boolean flag;
            boolean flag1;
            if(!selectionStrategy.isEmpty())
                flag = true;
            else
                flag = false;
            if(!adapter.isEmpty())
                flag1 = true;
            else
                flag1 = false;
            loadingStrategy.clearResults();
            selectionStrategy.clear();
            adapter.notifyDataSetChanged();
            if(flag1 && onDataChangedListener != null)
                onDataChangedListener.onDataChanged(this);
            if(flag && onSelectionChangedListener != null)
                onSelectionChangedListener.onSelectionChanged(this);
        }
    }

    private void inflateTitleBar(ViewGroup viewgroup)
    {
        ViewStub viewstub = (ViewStub)viewgroup.findViewById(com.facebook.android.R.id.com_facebook_picker_title_bar_stub);
        if(viewstub != null)
        {
            View view = viewstub.inflate();
            android.widget.RelativeLayout.LayoutParams layoutparams = new android.widget.RelativeLayout.LayoutParams(-1, -1);
            layoutparams.addRule(3, com.facebook.android.R.id.com_facebook_picker_title_bar);
            listView.setLayoutParams(layoutparams);
            if(titleBarBackground != null)
                view.setBackgroundDrawable(titleBarBackground);
            doneButton = (Button)viewgroup.findViewById(com.facebook.android.R.id.com_facebook_picker_done_button);
            if(doneButton != null)
            {
                doneButton.setOnClickListener(new android.view.View.OnClickListener() {

                    public void onClick(View view1)
                    {
                        logAppEvents(true);
                        appEventsLogged = true;
                        if(onDoneButtonClickedListener != null)
                            onDoneButtonClickedListener.onDoneButtonClicked(PickerFragment.this);
                    }

                    final PickerFragment this$0;

            
            {
                this$0 = PickerFragment.this;
                super();
            }
                }
);
                if(getDoneButtonText() != null)
                    doneButton.setText(getDoneButtonText());
                if(doneButtonBackground != null)
                    doneButton.setBackgroundDrawable(doneButtonBackground);
            }
            titleTextView = (TextView)viewgroup.findViewById(com.facebook.android.R.id.com_facebook_picker_title);
            if(titleTextView != null && getTitleText() != null)
                titleTextView.setText(getTitleText());
        }
    }

    private void loadDataSkippingRoundTripIfCached()
    {
        clearResults();
        Request request = getRequestForLoadData(getSession());
        if(request != null)
        {
            onLoadingData();
            loadingStrategy.startLoading(request);
        }
    }

    private void onListItemClick(ListView listview, View view, int i)
    {
        GraphObject graphobject = (GraphObject)listview.getItemAtPosition(i);
        String s = adapter.getIdOfGraphObject(graphobject);
        selectionStrategy.toggleSelection(s);
        adapter.notifyDataSetChanged();
        if(onSelectionChangedListener != null)
            onSelectionChangedListener.onSelectionChanged(this);
    }

    private void reprioritizeDownloads()
    {
        int i = listView.getLastVisiblePosition();
        if(i >= 0)
        {
            int j = listView.getFirstVisiblePosition();
            adapter.prioritizeViewRange(j, i, 5);
        }
    }

    private static void setAlpha(View view, float f)
    {
        AlphaAnimation alphaanimation = new AlphaAnimation(f, f);
        alphaanimation.setDuration(0L);
        alphaanimation.setFillAfter(true);
        view.startAnimation(alphaanimation);
    }

    private void setPickerFragmentSettingsFromBundle(Bundle bundle)
    {
        if(bundle != null)
        {
            showPictures = bundle.getBoolean("com.facebook.widget.PickerFragment.ShowPictures", showPictures);
            String s = bundle.getString("com.facebook.widget.PickerFragment.ExtraFields");
            if(s != null)
                setExtraFields(Arrays.asList(s.split(",")));
            showTitleBar = bundle.getBoolean("com.facebook.widget.PickerFragment.ShowTitleBar", showTitleBar);
            String s1 = bundle.getString("com.facebook.widget.PickerFragment.TitleText");
            if(s1 != null)
            {
                titleText = s1;
                if(titleTextView != null)
                    titleTextView.setText(titleText);
            }
            String s2 = bundle.getString("com.facebook.widget.PickerFragment.DoneButtonText");
            if(s2 != null)
            {
                doneButtonText = s2;
                if(doneButton != null)
                    doneButton.setText(doneButtonText);
            }
        }
    }

    abstract PickerFragmentAdapter createAdapter();

    abstract LoadingStrategy createLoadingStrategy();

    abstract SelectionStrategy createSelectionStrategy();

    void displayActivityCircle()
    {
        if(activityCircle != null)
        {
            layoutActivityCircle();
            activityCircle.setVisibility(0);
        }
    }

    boolean filterIncludesItem(GraphObject graphobject)
    {
        if(filter != null)
            return filter.includeItem(graphobject);
        else
            return true;
    }

    String getDefaultDoneButtonText()
    {
        return getString(com.facebook.android.R.string.com_facebook_picker_done_button_text);
    }

    String getDefaultTitleText()
    {
        return null;
    }

    public String getDoneButtonText()
    {
        if(doneButtonText == null)
            doneButtonText = getDefaultDoneButtonText();
        return doneButtonText;
    }

    public Set getExtraFields()
    {
        return new HashSet(extraFields);
    }

    public GraphObjectFilter getFilter()
    {
        return filter;
    }

    public OnDataChangedListener getOnDataChangedListener()
    {
        return onDataChangedListener;
    }

    public OnDoneButtonClickedListener getOnDoneButtonClickedListener()
    {
        return onDoneButtonClickedListener;
    }

    public OnErrorListener getOnErrorListener()
    {
        return onErrorListener;
    }

    public OnSelectionChangedListener getOnSelectionChangedListener()
    {
        return onSelectionChangedListener;
    }

    abstract Request getRequestForLoadData(Session session);

    List getSelectedGraphObjects()
    {
        return adapter.getGraphObjectsById(selectionStrategy.getSelectedIds());
    }

    public Session getSession()
    {
        return sessionTracker.getSession();
    }

    public boolean getShowPictures()
    {
        return showPictures;
    }

    public boolean getShowTitleBar()
    {
        return showTitleBar;
    }

    public String getTitleText()
    {
        if(titleText == null)
            titleText = getDefaultTitleText();
        return titleText;
    }

    void hideActivityCircle()
    {
        if(activityCircle != null)
        {
            activityCircle.clearAnimation();
            activityCircle.setVisibility(4);
        }
    }

    void layoutActivityCircle()
    {
        float f;
        if(!adapter.isEmpty())
            f = 0.25F;
        else
            f = 1.0F;
        setAlpha(activityCircle, f);
    }

    public void loadData(boolean flag)
    {
        if(!flag && loadingStrategy.isDataPresentOrLoading())
        {
            return;
        } else
        {
            loadDataSkippingRoundTripIfCached();
            return;
        }
    }

    void logAppEvents(boolean flag)
    {
    }

    public void onActivityCreated(Bundle bundle)
    {
label0:
        {
            super.onActivityCreated(bundle);
            sessionTracker = new SessionTracker(getActivity(), new com.facebook.Session.StatusCallback() {

                public void call(Session session, SessionState sessionstate, Exception exception)
                {
                    if(!session.isOpened())
                        clearResults();
                }

                final PickerFragment this$0;

            
            {
                this$0 = PickerFragment.this;
                super();
            }
            }
);
            setSettingsFromBundle(bundle);
            loadingStrategy = createLoadingStrategy();
            loadingStrategy.attach(adapter);
            selectionStrategy = createSelectionStrategy();
            selectionStrategy.readSelectionFromBundle(bundle, "com.facebook.android.PickerFragment.Selection");
            if(showTitleBar)
                inflateTitleBar((ViewGroup)getView());
            if(activityCircle != null && bundle != null)
            {
                if(!bundle.getBoolean("com.facebook.android.PickerFragment.ActivityCircleShown", false))
                    break label0;
                displayActivityCircle();
            }
            return;
        }
        hideActivityCircle();
    }

    public void onCreate(Bundle bundle)
    {
        TraceMachine.startTracing("PickerFragment");
        TraceMachine.enterMethod(_nr_trace, "PickerFragment#onCreate", null);
_L1:
        super.onCreate(bundle);
        adapter = createAdapter();
        adapter.setFilter(new GraphObjectAdapter.Filter() {

            public boolean includeItem(GraphObject graphobject)
            {
                return filterIncludesItem(graphobject);
            }

            public volatile boolean includeItem(Object obj)
            {
                return includeItem((GraphObject)obj);
            }

            final PickerFragment this$0;

            
            {
                this$0 = PickerFragment.this;
                super();
            }
        }
);
        TraceMachine.exitMethod();
        return;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "PickerFragment#onCreate", null);
          goto _L1
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        TraceMachine.enterMethod(_nr_trace, "PickerFragment#onCreateView", null);
_L1:
        ViewGroup viewgroup1 = (ViewGroup)layoutinflater.inflate(layout, viewgroup, false);
        listView = (ListView)viewgroup1.findViewById(com.facebook.android.R.id.com_facebook_picker_list_view);
        listView.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener() {

            public void onItemClick(AdapterView adapterview, View view, int i, long l)
            {
                onListItemClick((ListView)adapterview, view, i);
            }

            final PickerFragment this$0;

            
            {
                this$0 = PickerFragment.this;
                super();
            }
        }
);
        listView.setOnLongClickListener(new android.view.View.OnLongClickListener() {

            public boolean onLongClick(View view)
            {
                return false;
            }

            final PickerFragment this$0;

            
            {
                this$0 = PickerFragment.this;
                super();
            }
        }
);
        listView.setOnScrollListener(onScrollListener);
        activityCircle = (ProgressBar)viewgroup1.findViewById(com.facebook.android.R.id.com_facebook_picker_activity_circle);
        setupViews(viewgroup1);
        listView.setAdapter(adapter);
        TraceMachine.exitMethod();
        return viewgroup1;
        NoSuchFieldError nosuchfielderror;
        nosuchfielderror;
        TraceMachine.enterMethod(null, "PickerFragment#onCreateView", null);
          goto _L1
    }

    public void onDetach()
    {
        super.onDetach();
        listView.setOnScrollListener(null);
        listView.setAdapter(null);
        loadingStrategy.detach();
        sessionTracker.stopTracking();
    }

    public void onInflate(Activity activity, AttributeSet attributeset, Bundle bundle)
    {
        super.onInflate(activity, attributeset, bundle);
        TypedArray typedarray = activity.obtainStyledAttributes(attributeset, com.facebook.android.R.styleable.com_facebook_picker_fragment);
        setShowPictures(typedarray.getBoolean(0, showPictures));
        String s = typedarray.getString(1);
        if(s != null)
            setExtraFields(Arrays.asList(s.split(",")));
        showTitleBar = typedarray.getBoolean(2, showTitleBar);
        titleText = typedarray.getString(3);
        doneButtonText = typedarray.getString(4);
        titleBarBackground = typedarray.getDrawable(5);
        doneButtonBackground = typedarray.getDrawable(6);
        typedarray.recycle();
    }

    void onLoadingData()
    {
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        saveSettingsToBundle(bundle);
        selectionStrategy.saveSelectionToBundle(bundle, "com.facebook.android.PickerFragment.Selection");
        if(activityCircle != null)
        {
            boolean flag;
            if(activityCircle.getVisibility() == 0)
                flag = true;
            else
                flag = false;
            bundle.putBoolean("com.facebook.android.PickerFragment.ActivityCircleShown", flag);
        }
    }

    protected void onStart()
    {
        super.onStart();
        ApplicationStateMonitor.getInstance().activityStarted();
    }

    public void onStop()
    {
        ApplicationStateMonitor.getInstance().activityStopped();
        if(!appEventsLogged)
            logAppEvents(false);
        super.onStop();
    }

    void saveSettingsToBundle(Bundle bundle)
    {
        bundle.putBoolean("com.facebook.widget.PickerFragment.ShowPictures", showPictures);
        if(!extraFields.isEmpty())
            bundle.putString("com.facebook.widget.PickerFragment.ExtraFields", TextUtils.join(",", extraFields));
        bundle.putBoolean("com.facebook.widget.PickerFragment.ShowTitleBar", showTitleBar);
        bundle.putString("com.facebook.widget.PickerFragment.TitleText", titleText);
        bundle.putString("com.facebook.widget.PickerFragment.DoneButtonText", doneButtonText);
    }

    public void setArguments(Bundle bundle)
    {
        super.setArguments(bundle);
        setSettingsFromBundle(bundle);
    }

    public void setDoneButtonText(String s)
    {
        doneButtonText = s;
    }

    public void setExtraFields(Collection collection)
    {
        extraFields = new HashSet();
        if(collection != null)
            extraFields.addAll(collection);
    }

    public void setFilter(GraphObjectFilter graphobjectfilter)
    {
        filter = graphobjectfilter;
    }

    public void setOnDataChangedListener(OnDataChangedListener ondatachangedlistener)
    {
        onDataChangedListener = ondatachangedlistener;
    }

    public void setOnDoneButtonClickedListener(OnDoneButtonClickedListener ondonebuttonclickedlistener)
    {
        onDoneButtonClickedListener = ondonebuttonclickedlistener;
    }

    public void setOnErrorListener(OnErrorListener onerrorlistener)
    {
        onErrorListener = onerrorlistener;
    }

    public void setOnSelectionChangedListener(OnSelectionChangedListener onselectionchangedlistener)
    {
        onSelectionChangedListener = onselectionchangedlistener;
    }

    void setSelectedGraphObjects(List list)
    {
        Iterator iterator = list.iterator();
        do
        {
            if(!iterator.hasNext())
                break;
            String s = (String)iterator.next();
            if(!selectionStrategy.isSelected(s))
                selectionStrategy.toggleSelection(s);
        } while(true);
    }

    void setSelectionStrategy(SelectionStrategy selectionstrategy)
    {
        if(selectionstrategy != selectionStrategy)
        {
            selectionStrategy = selectionstrategy;
            if(adapter != null)
                adapter.notifyDataSetChanged();
        }
    }

    public void setSession(Session session)
    {
        sessionTracker.setSession(session);
    }

    public void setSettingsFromBundle(Bundle bundle)
    {
        setPickerFragmentSettingsFromBundle(bundle);
    }

    public void setShowPictures(boolean flag)
    {
        showPictures = flag;
    }

    public void setShowTitleBar(boolean flag)
    {
        showTitleBar = flag;
    }

    public void setTitleText(String s)
    {
        titleText = s;
    }

    void setupViews(ViewGroup viewgroup)
    {
    }

    void updateAdapter(SimpleGraphObjectCursor simplegraphobjectcursor)
    {
        if(adapter != null)
        {
            View view = listView.getChildAt(1);
            int i = listView.getFirstVisiblePosition();
            if(i > 0)
                i++;
            GraphObjectAdapter.SectionAndItem sectionanditem = adapter.getSectionAndItem(i);
            int j;
            boolean flag;
            if(view != null && sectionanditem.getType() != GraphObjectAdapter.SectionAndItem.Type.ACTIVITY_CIRCLE)
                j = view.getTop();
            else
                j = 0;
            flag = adapter.changeCursor(simplegraphobjectcursor);
            if(view != null && sectionanditem != null)
            {
                int k = adapter.getPosition(sectionanditem.sectionKey, sectionanditem.graphObject);
                if(k != -1)
                    listView.setSelectionFromTop(k, j);
            }
            if(flag && onDataChangedListener != null)
                onDataChangedListener.onDataChanged(this);
        }
    }

    private static final String ACTIVITY_CIRCLE_SHOW_KEY = "com.facebook.android.PickerFragment.ActivityCircleShown";
    public static final String DONE_BUTTON_TEXT_BUNDLE_KEY = "com.facebook.widget.PickerFragment.DoneButtonText";
    public static final String EXTRA_FIELDS_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ExtraFields";
    private static final int PROFILE_PICTURE_PREFETCH_BUFFER = 5;
    private static final String SELECTION_BUNDLE_KEY = "com.facebook.android.PickerFragment.Selection";
    public static final String SHOW_PICTURES_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ShowPictures";
    public static final String SHOW_TITLE_BAR_BUNDLE_KEY = "com.facebook.widget.PickerFragment.ShowTitleBar";
    public static final String TITLE_TEXT_BUNDLE_KEY = "com.facebook.widget.PickerFragment.TitleText";
    private ProgressBar activityCircle;
    GraphObjectAdapter adapter;
    private boolean appEventsLogged;
    private Button doneButton;
    private Drawable doneButtonBackground;
    private String doneButtonText;
    HashSet extraFields;
    private GraphObjectFilter filter;
    private final Class graphObjectClass;
    private final int layout;
    private ListView listView;
    private LoadingStrategy loadingStrategy;
    private OnDataChangedListener onDataChangedListener;
    private OnDoneButtonClickedListener onDoneButtonClickedListener;
    private OnErrorListener onErrorListener;
    private android.widget.AbsListView.OnScrollListener onScrollListener;
    private OnSelectionChangedListener onSelectionChangedListener;
    private SelectionStrategy selectionStrategy;
    private SessionTracker sessionTracker;
    private boolean showPictures;
    private boolean showTitleBar;
    private Drawable titleBarBackground;
    private String titleText;
    private TextView titleTextView;




/*
    static boolean access$202(PickerFragment pickerfragment, boolean flag)
    {
        pickerfragment.appEventsLogged = flag;
        return flag;
    }

*/





}
