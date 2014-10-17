// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.fragments;

import android.app.ActivityManager;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.*;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import butterknife.ButterKnife;
import com.roadtrippers.adapters.CommentsAdapter;
import com.roadtrippers.api.Roadtrippers;
import com.roadtrippers.api.models.Comment;
import com.roadtrippers.api.models.Poi;
import com.roadtrippers.api.requests.AddCommentRequest;
import com.roadtrippers.events.CommentAdded;
import com.roadtrippers.fragments.base.BaseListFragment;
import com.roadtrippers.util.Log;
import com.roadtrippers.util.Serializer;
import com.roadtrippers.widget.ProgressTypefaceButton;
import com.squareup.otto.Bus;
import dagger.Lazy;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;
import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

// Referenced classes of package com.roadtrippers.fragments:
//            SettingsFragment

public class RateAndReviewFragment extends BaseListFragment
{
    class HeaderHolder
    {

        void post()
        {
            if(ActivityManager.isUserAMonkey())
                return;
            inputMethodManager.hideSoftInputFromWindow(editReview.getWindowToken(), 0);
            if(editReview.length() > 0)
            {
                postReview.setLoading(true);
                ((Roadtrippers)roadtrippersLazy.get()).addComment(getPoi().id, new AddCommentRequest(SettingsFragment.trim(editReview))).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).map(new Func1() {

                    public Comment call(Comment comment)
                    {
                        Poi poi = getPoi();
                        poi.comments = Comment.add(poi.comments, comment);
                        getArguments().putString("poi", ((Serializer)serializerLazy.get()).serialize(poi));
                        return comment;
                    }

                    public volatile Object call(Object obj)
                    {
                        return call((Comment)obj);
                    }

                    final HeaderHolder this$1;

            
            {
                this$1 = HeaderHolder.this;
                super();
            }
                }
).subscribe(new Observer() {

                    public void onCompleted()
                    {
                    }

                    public void onError(Throwable throwable)
                    {
                        Log.e(throwable);
                        if(postReview != null)
                            postReview.setLoading(false);
                        if(getView() != null)
                        {
                            Crouton.cancelAllCroutons();
                            Crouton.showText(getActivity(), "Failed to add the comment, please try again", Style.ALERT, (ViewGroup)getView());
                        }
                    }

                    public void onNext(Comment comment)
                    {
                        if(postReview != null && editReview != null)
                        {
                            editReview.setText("");
                            postReview.setLoading(false);
                        }
                        ((Bus)busLazy.get()).post(new CommentAdded(comment));
                    }

                    public volatile void onNext(Object obj)
                    {
                        onNext((Comment)obj);
                    }

                    final HeaderHolder this$1;

            
            {
                this$1 = HeaderHolder.this;
                super();
            }
                }
);
                return;
            } else
            {
                editReview.setError("This field is required");
                editReview.requestFocus();
                return;
            }
        }

        EditText editReview;
        ProgressTypefaceButton postReview;
        final RateAndReviewFragment this$0;

        HeaderHolder(View view)
        {
            this$0 = RateAndReviewFragment.this;
            super();
            ButterKnife.inject(this, view);
        }
    }


    public RateAndReviewFragment()
    {
    }

    public static RateAndReviewFragment newInstance(String s)
    {
        RateAndReviewFragment rateandreviewfragment = new RateAndReviewFragment();
        Bundle bundle = new Bundle();
        bundle.putString("poi", s);
        rateandreviewfragment.setArguments(bundle);
        return rateandreviewfragment;
    }

    public volatile ListAdapter getListAdapter()
    {
        return getListAdapter();
    }

    public CommentsAdapter getListAdapter()
    {
        return (CommentsAdapter)super.getListAdapter();
    }

    Poi getPoi()
    {
        return (Poi)((Serializer)serializerLazy.get()).deserialize(getArguments().getString("poi"), com/roadtrippers/api/models/Poi);
    }

    public View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        View view = super.onCreateView(layoutinflater, viewgroup, bundle);
        view.setBackgroundResource(0x7f080034);
        return view;
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        ButterKnife.reset(headerHolder);
    }

    public void onEvent(CommentAdded commentadded)
    {
        getListAdapter().add(commentadded.comment);
        getListView().smoothScrollToPosition(getListAdapter().getCount());
    }

    public void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        getListView().setDivider(null);
        View view1 = LayoutInflater.from(getActivity()).inflate(0x7f030059, getListView(), false);
        headerHolder = new HeaderHolder(view1);
        getListView().addHeaderView(view1);
        getListView().setOnTouchListener(new android.view.View.OnTouchListener() {

            public boolean onTouch(View view2, MotionEvent motionevent)
            {
                return gestureDetector.onTouchEvent(motionevent);
            }

            GestureDetector gestureDetector;
            final RateAndReviewFragment this$0;

            
            {
                this$0 = RateAndReviewFragment.this;
                super();
                gestureDetector = new GestureDetector(getActivity(), new _cls1());
            }
        }
);
        setListAdapter(new CommentsAdapter(getActivity(), getPoi().comments));
    }

    Lazy busLazy;
    HeaderHolder headerHolder;
    InputMethodManager inputMethodManager;
    Lazy roadtrippersLazy;
    Lazy serializerLazy;

    // Unreferenced inner class com/roadtrippers/fragments/RateAndReviewFragment$1$1

/* anonymous class */
    class _cls1 extends android.view.GestureDetector.SimpleOnGestureListener
    {

        public boolean onFling(MotionEvent motionevent, MotionEvent motionevent1, float f, float f1)
        {
            if(Math.abs(motionevent.getY() - motionevent1.getY()) > 250F)
                return false;
            if(motionevent.getX() - motionevent1.getX() > 120F && Math.abs(f) > 100F || motionevent1.getX() - motionevent.getX() <= 120F || Math.abs(f) <= 100F)
                break MISSING_BLOCK_LABEL_87;
            getFragmentManager().popBackStack();
            return false;
            Exception exception;
            exception;
            return false;
        }

        private static final int SWIPE_MAX_OFF_PATH = 250;
        private static final int SWIPE_MIN_DISTANCE = 120;
        private static final int SWIPE_THRESHOLD_VELOCITY = 100;
        final _cls1 this$1;

            
            {
                this$1 = _cls1.this;
                super();
            }
    }

}
