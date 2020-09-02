package com.example.swe_project;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.text.TextUtils;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

public class VolleySingleton{
    public static final String TAG = "VolleyTag";

    public RequestQueue mRequestQueue;

    private static VolleySingleton sInstance;
    private ImageLoader mImageLoader;


    private VolleySingleton (Context context){
        mRequestQueue= Volley.newRequestQueue(context.getApplicationContext());
        mImageLoader= new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            private final LruCache<String,Bitmap> cache= new LruCache<String, Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url,bitmap);
            }
        });
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

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    public void cancelPendingRequests(Object tag){
        if(mRequestQueue!=null){
            mRequestQueue.cancelAll(tag);
        }
    }
}
