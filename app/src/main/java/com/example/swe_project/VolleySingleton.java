package com.example.swe_project;

import android.app.Application;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.Volley;

public class VolleySingleton{
    public static final String TAG = "VolleyTag";

    public RequestQueue mRequestQueue;

    private static VolleySingleton sInstance;

    private VolleySingleton (Context context){
        mRequestQueue= Volley.newRequestQueue(context.getApplicationContext());
    }
    public static synchronized VolleySingleton getInstance(Context context){
        if(sInstance==null){
            sInstance=new VolleySingleton(context);
        }
        return sInstance;
    }

    public RequestQueue getRequestQueue(){
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag){
        req.setTag(TextUtils.isEmpty(tag)?TAG:tag);
        VolleyLog.d("Adding req to queue: %s",req.getUrl());
        getRequestQueue().add(req);
    }
    public <T> void addToRequestQueue(Request<T> req){
        req.setTag(TAG);
        VolleyLog.d("Adding req to queue: %s",req.getUrl());
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue!=null){
            mRequestQueue.cancelAll(tag);
        }
    }
}
