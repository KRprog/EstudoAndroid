package com.example.cristiane_aula.prjlanchonete.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;

import org.json.JSONException;
import org.json.JSONObject;

import com.example.cristiane_aula.prjlanchonete.R;
import com.example.cristiane_aula.prjlanchonete.models.Credentials;
import com.example.cristiane_aula.prjlanchonete.util.Func;
import com.example.cristiane_aula.prjlanchonete.util.AccountUtil;
import com.example.cristiane_aula.prjlanchonete.util.PreferencesUtil;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
    }

    public void Entrar2(View view) {
        String username = ((EditText)findViewById(R.id.edtEmail)).getText().toString();
        String password = ((EditText)findViewById(R.id.edtPassword)).getText().toString();

        AccountUtil.Login(this,new Credentials(username,password));
    }

    public void Entrar(View view){

        String username = ((EditText)findViewById(R.id.edtEmail)).getText().toString();
        String password = ((EditText)findViewById(R.id.edtPassword)).getText().toString();

        final Context mCtx = this;

        final ProgressDialog dialog = new ProgressDialog(mCtx);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setMessage("Realizando login...");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        AccountUtil.Login(mCtx,new Credentials(username,password),
            new Func<Void,JSONObject>() {
                @Override
                public Void call(JSONObject response) throws Exception {
                    dialog.cancel();
                    try {
                        if(response.getBoolean("success") && response.has("token")){
                            PreferencesUtil.getInstance(mCtx.getApplicationContext()).setToken(response.getString("token"));
                            Toast.makeText(mCtx.getApplicationContext(),"Login realizado com sucesso!",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(mCtx.getApplicationContext(), PedidoActivity.class));
                            finish();
                        }else{
                            Toast.makeText(mCtx.getApplicationContext(),response.getString("message"),Toast.LENGTH_LONG).show();
                        }
                    } catch (JSONException e) {
                        Toast.makeText(mCtx.getApplicationContext(),"Ocorreu um erro ao tentar logar!",Toast.LENGTH_LONG).show();
                        e.printStackTrace();
                    }
                    return null;
                }
            },
            new Func<Void,VolleyError>(){
                @Override
                public Void call(VolleyError error) throws Exception {
                    dialog.cancel();
                    Toast.makeText(mCtx,error.getMessage(),Toast.LENGTH_LONG).show();
                    return null;
                }
            }
        );


    }

}
