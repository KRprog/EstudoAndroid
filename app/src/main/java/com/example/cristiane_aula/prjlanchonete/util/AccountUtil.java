package com.example.cristiane_aula.prjlanchonete.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.cristiane_aula.prjlanchonete.conf.Constant;
import com.example.cristiane_aula.prjlanchonete.models.Credentials;
import com.example.cristiane_aula.prjlanchonete.net.WebRequest;

/**
 * Created by RAFAEL on 10/03/2017.
 */

public class AccountUtil {

    public static void Login (final Context context, Credentials user) {
        try {
            JSONObject data = new JSONObject();
            data.put("user",user.getUser());
            data.put("password",user.getPassword());

            final ProgressDialog dialog = new ProgressDialog(context);
            dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            dialog.setMessage("Realizando login...");
            dialog.setIndeterminate(true);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();

            WebRequest.getInstance(context).postJSON(Constant.API_ATHENTICATE,data,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        dialog.cancel();
                        try {
                            if(response.getBoolean("success") && response.has("token")){
                                PreferencesUtil.getInstance(context).setToken(response.getString("token"));
                                //Send to MenuActivity
                                Toast.makeText(context,response.getString("token"),Toast.LENGTH_LONG).show();
                            }else{
                                Toast.makeText(context,response.getString("message"),Toast.LENGTH_LONG).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        dialog.cancel();
                        Toast.makeText(context,error.getMessage(),Toast.LENGTH_LONG).show();
                    }
                });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void Login (final Context context, Credentials user,final Func<Void,JSONObject> success, final Func<Void,VolleyError> error) {
        try {
            JSONObject data = new JSONObject();
            data.put("user",user.getUser());
            data.put("password",user.getPassword());

            WebRequest.getInstance(context).postJSON(Constant.API_ATHENTICATE, data,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {
                                success.call(response);
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
                    });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
