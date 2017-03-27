package com.example.cristiane_aula.prjlanchonete;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.*;

public class MainActivity extends AppCompatActivity {
    private static final String[] LANCHES = new String[]{"X-Tudo", "X-Salada", "Hot-Dog",  "Misto Quente"};
    private static final String[] BEBIDAS = new String[]{"Coca-Cola", "Sprit", "Guaraná",  "Suco de Laranja"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner combo = (Spinner) findViewById(R.id.spinnerLanche);
        ArrayAdapter<String> adp = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, LANCHES);
        adp.setDropDownViewResource(android.R.layout.simple_spinner_item);
        combo.setAdapter(adp);

        Spinner combo2 = (Spinner) findViewById(R.id.spinnerBebida);
        ArrayAdapter<String> adp1 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, BEBIDAS);
        adp1.setDropDownViewResource(android.R.layout.simple_spinner_item);
        combo2.setAdapter(adp1);
    }

    public void gera(View view)    {


        Spinner cbLanche = (Spinner) findViewById(R.id.spinnerLanche);
        Spinner cbBebida = (Spinner) findViewById(R.id.spinnerBebida);

        //label1.setText("oi");
        //pega nome pela posição

        //imprime um Toast na tela com o nome que foi selecionado
        //Toast.makeText(MainActivity.this, "Nome Selecionado: " + nome, Toast.LENGTH_LONG).show();
        CheckBox CkCatchup = (CheckBox) findViewById(R.id.CbCatchup);

        CheckBox CkMostarda = (CheckBox) findViewById(R.id.CbMostarda);
        CheckBox CkAlface = (CheckBox) findViewById(R.id.CbAlface);
        CheckBox CkPicles = (CheckBox) findViewById(R.id.CbPicles);
        RadioButton RbGelada = (RadioButton) findViewById(R.id.RadioGelada);

        Lanchonete pedido = new Lanchonete(cbLanche.getSelectedItem().toString(),
                cbBebida.getSelectedItem().toString(),
                CkCatchup.isChecked()?"S":"N", CkMostarda.isChecked()?"S":"N",
                CkAlface.isChecked()?"S":"N",CkPicles.isChecked()?"S":"N", RbGelada.isChecked()?"S":"N");


        Intent intent = new Intent(this, Mostra.class);
        Intent objeto = intent.putExtra("Objeto", pedido);
        startActivity(intent);
    }


    public void consulta(View view) {
        Intent intent = new Intent(this, Grid.class);
        //intent.putExtra("Objeto",pedido);
        startActivity(intent);
    }
}
