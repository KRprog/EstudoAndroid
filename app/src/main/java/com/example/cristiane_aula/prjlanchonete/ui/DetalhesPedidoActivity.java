package com.example.cristiane_aula.prjlanchonete.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cristiane_aula.prjlanchonete.db.DBAdapter;
import com.example.cristiane_aula.prjlanchonete.R;
import com.example.cristiane_aula.prjlanchonete.models.Lanchonete;


public class DetalhesPedidoActivity extends AppCompatActivity {
    private DBAdapter datasource;
    private Lanchonete pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra);

        datasource = DBAdapter.getInstance(this);
        Intent intent = getIntent();
        pedido = (Lanchonete) intent.getSerializableExtra("Objeto");

        TextView label1 = (TextView) findViewById(R.id.etPedido);
        label1.setText(pedido.retornaDados());
    }

    public void alert(AppCompatActivity context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public void grava(View view)    {
        // insere na base de dados

        long id_dados=datasource.createLanche(pedido);
        alert(this, "Dados pessoais salvos com sucesso! "+id_dados);
        //datasource.fecharConexao();

    }

}
