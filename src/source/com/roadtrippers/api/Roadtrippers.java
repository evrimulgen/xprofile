// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

package com.roadtrippers.api;

import com.roadtrippers.api.models.Bucket;
import com.roadtrippers.api.models.BucketListAddRequest;
import com.roadtrippers.api.models.CreateUserResponse;
import com.roadtrippers.api.models.LoginResponse;
import com.roadtrippers.api.models.RTAutocompleteResponse;
import com.roadtrippers.api.models.User;
import com.roadtrippers.api.requests.AddCommentRequest;
import com.roadtrippers.api.requests.AddGalleryImageRequest;
import com.roadtrippers.api.requests.AddPlaceRequest;
import com.roadtrippers.api.requests.CreatePoiRequest;
import com.roadtrippers.api.requests.CreateUserRequest;
import com.roadtrippers.api.requests.FacebookLoginRequest;
import com.roadtrippers.api.requests.LoginRequest;
import com.roadtrippers.api.requests.SearchRequest;
import com.roadtrippers.api.requests.TripDescriptionRequest;
import com.roadtrippers.api.requests.TripRequest;
import com.roadtrippers.api.requests.UpdateUserRequest;
import retrofit.client.Response;
import rx.Observable;

public interface Roadtrippers
{

    public abstract Observable addComment(String s, AddCommentRequest addcommentrequest);

    public abstract Response addImageToPlace(String s, AddGalleryImageRequest addgalleryimagerequest);

    public abstract Response addPlaceToBucket(int i, AddPlaceRequest addplacerequest);

    public abstract Response addPlaceToBucketList(int i, int j, BucketListAddRequest bucketlistaddrequest);

    public abstract RTAutocompleteResponse autocomplete(String s);

    public abstract Observable createBucketList(int i, Bucket bucket);

    public abstract Observable createNewPoi(CreatePoiRequest createpoirequest);

    public abstract Observable createNewTrip(TripRequest triprequest);

    public abstract Response createUser(CreateUserRequest createuserrequest);

    public abstract Observable currentUser();

    public abstract Response deleteBucket(int i, int j);

    public abstract Response deletePlaceFromBucket(int i, int j, int k);

    public abstract Observable deleteTrip(String s);

    public abstract CreateUserResponse facebookLogin(FacebookLoginRequest facebookloginrequest);

    public abstract Observable getBucketDetails(int i, int j);

    public abstract Observable getBuckets();

    public abstract Observable getBuckets(int i);

    public abstract Observable getCategories();

    public abstract Observable getGallery(String s);

    public abstract Observable getPoi(String s);

    public abstract Observable getTrip(String s);

    public abstract Observable getTripFullDetails(String s);

    public abstract Observable getTrips();

    public abstract LoginResponse login(LoginRequest loginrequest);

    public abstract Response removePlaceFromBucket(int i, String s);

    public abstract Observable resolveGoogle(double d, double d1, String s, String s1, String s2);

    public abstract com.roadtrippers.api.models.Places.Response search(SearchRequest searchrequest);

    public abstract com.roadtrippers.api.models.Places.Response search(String s);

    public abstract Observable updateBucket(int i, int j, Bucket bucket);

    public abstract Observable updateTrip(String s, TripDescriptionRequest tripdescriptionrequest);

    public abstract Observable updateTrip(String s, TripRequest triprequest);

    public abstract User updateUser(UpdateUserRequest updateuserrequest);

    public static final String SERVER = "https://roadtrippers.com";
    public static final String STAGE_SERVER = "https://stage.roadtrippers.com";
}
