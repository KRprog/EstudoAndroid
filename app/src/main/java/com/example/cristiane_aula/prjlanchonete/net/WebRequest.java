package com.example.cristiane_aula.prjlanchonete.net;


import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.util.LruCache;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.JsonRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import com.example.cristiane_aula.prjlanchonete.util.PreferencesUtil;

/**
 * Created by RAFAEL on 11/03/2017.
 */
public class WebRequest {

    private static WebRequest mInstance;
    private RequestQueue mRequestQueue;
    private ImageLoader mImageLoader;
    private Context mCtx;

    private WebRequest(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();

        mImageLoader = new ImageLoader(mRequestQueue,
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>
                            cache = new LruCache<String, Bitmap>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        return cache.get(url);
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });
    }

    public static synchronized WebRequest getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new WebRequest(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }

    private Map<String,String> Headers(){
        Map<String, String>  params = new HashMap<String, String>();
        params.put("x-access-token", PreferencesUtil.getInstance(mCtx).getToken());
        Log.d("token","Realizando requisição");
        Log.d("token", PreferencesUtil.getInstance(mCtx).getToken());
        return params;
    }

    private void requestString(int method, String url,Response.Listener<String> success, Response.ErrorListener error) {
        StringRequest request = new StringRequest(method, url,success,error){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
               return Headers();
            }
        };
        this.addToRequestQueue(request);
    }

    public void getString(String url,String data,Response.Listener<String> success, Response.ErrorListener error) {
        requestString(Request.Method.GET,url,success,error);
    }

    public void postString(String url,String data,Response.Listener<String> success, Response.ErrorListener error) {
        requestString(Request.Method.POST,url,success,error);
    }

    public void requestJSONObject(int method, String url, JSONObject data, Response.Listener<JSONObject> success, Response.ErrorListener error) {
        JsonObjectRequest request = new JsonObjectRequest(method,url,data,success,error){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return Headers();
            }
        };
        this.addToRequestQueue(request);
    }

    public void getJSON(String url, JSONObject data,Response.Listener<JSONObject> success, Response.ErrorListener error) {
        requestJSONObject(Request.Method.GET,url,data,success,error);
    }

    public void postJSON(String url,JSONObject data,Response.Listener<JSONObject> success, Response.ErrorListener error) {
        requestJSONObject(Request.Method.POST,url,data,success,error);
    }

}
