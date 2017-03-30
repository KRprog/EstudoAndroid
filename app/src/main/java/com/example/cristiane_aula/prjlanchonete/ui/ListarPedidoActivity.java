package com.example.cristiane_aula.prjlanchonete.ui;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

import com.example.cristiane_aula.prjlanchonete.db.DBAdapter;
import com.example.cristiane_aula.prjlanchonete.R;

import java.util.ArrayList;

public class ListarPedidoActivity extends AppCompatActivity {
    GridView grid1;
    private DBAdapter datasource;
    Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid);

        grid1=(GridView) findViewById(R.id.gridPedido);
        carregaGrid();

    }

    public void carregaGrid()
    {

        try
        {
            ArrayList<String> x = new ArrayList<String>();

            // abre banco
            datasource = DBAdapter.getInstance1(this);
            cursor = datasource.retornaPedidos();
            mostraCXTexto("-->"+cursor.getCount(),"Teste");

            cursor.moveToFirst();
            // x.add(cursor.getString(0));
            // x.add(cursor.getString(1));
            // x.add(cursor.getString(2));
            do{

                x.add(cursor.getString(0));
                x.add(cursor.getString(1));
                x.add(cursor.getString(2));
            } while (cursor.moveToNext());
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, x);


            grid1.setAdapter(adapter);

            datasource.fecharConexao();
        }catch (Exception e) {
            mostraCXTexto("ListarPedidoActivity View DB. Mensagem: " +e.getMessage(), " Erro");

        }
    }


    private void mostraCXTexto(String msg, String titulo) {
        AlertDialog.Builder builder = new AlertDialog.Builder(ListarPedidoActivity.this);
        builder.setMessage(msg);
        builder.setNegativeButton("OK", null);
          AlertDialog dialog = builder.create();
        builder.setTitle(titulo);
        dialog.show();
    }
}
