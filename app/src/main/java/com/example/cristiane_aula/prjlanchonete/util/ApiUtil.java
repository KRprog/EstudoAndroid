package com.example.cristiane_aula.prjlanchonete.util;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.Arrays;
import java.util.List;

import com.example.cristiane_aula.prjlanchonete.conf.Constant;
import com.example.cristiane_aula.prjlanchonete.model.Credentials;
import com.example.cristiane_aula.prjlanchonete.net.WebRequest;

/**
 * Created by RAFAEL on 12/03/2017.
 */

public class ApiUtil {

    private final Context mCtx;
    private final Gson gson;
    public ApiUtil(Context context) {

        mCtx =  context;
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("M/d/yy hh:mm a");
        gson = gsonBuilder.create();
    }

    public void getAllUsers(final Func<Void,List<Credentials>> success, final Func<Void,VolleyError> error){

        WebRequest.getInstance(mCtx).getJSON(Constant.API_URL + "/api/users",null,
                new Response.Listener<JSONObject>(){
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            List<Credentials> users = Arrays.asList(gson.fromJson(response.getJSONArray("data").toString(), Credentials[].class));
                            success.call(users);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError response) {
                        try {
                            error.call(response);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
    }
}
