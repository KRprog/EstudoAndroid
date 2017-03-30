package com.example.cristiane_aula.prjlanchonete.db;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.cristiane_aula.prjlanchonete.models.Lanchonete;


public class DBAdapter {
	private SQLiteDatabase database=null;	
	private String[] allColumns = { "id","lanche", "bebida", "catchup",
			"mostarda", "alface", "picles", "gelo"};
	
	 private static DBAdapter instance = new DBAdapter();
	    //cria a conexão com o banco de dados caso não exista

	    public static DBAdapter getInstance(Context ctx) {
	        if (instance.database == null || !instance.database.isOpen()) {
	            instance.database = new DbHelper(ctx).getWritableDatabase();
	     
	        }
	        return instance;
	    }

	    public static DBAdapter getInstance1(Context ctx) {
	        if (instance.database == null || !instance.database.isOpen()) {
	            instance.database = new DbHelper(ctx).getReadableDatabase();
	     
	        }
	        return instance;
	    }

	public DBAdapter() {
		// TODO Auto-generated constructor stub
	}
	
	
	public long createLanche(Lanchonete lanche ) {
        
		long id_dados = -1;
        database.beginTransaction();
        try {
            ContentValues cv = new ContentValues();
            cv.put(allColumns[1], lanche.getLanche());
            cv.put(allColumns[2], lanche.getBebida());
            cv.put(allColumns[3], lanche.getCatchup());
            cv.put(allColumns[4], lanche.getMostarda());
            cv.put(allColumns[5], lanche.getAlface());
            cv.put(allColumns[6], lanche.getPicles());
            cv.put(allColumns[7], lanche.getGelo());
            
            id_dados = database.insert(DbHelper.TABLE_NAME, null, cv);
            if (id_dados != -1) {
                database.setTransactionSuccessful();
              
            }
        } finally {
            database.endTransaction();
        }

        return id_dados;
		       
       
    }
	
	public Cursor retornaPedidos()
	{
	  Cursor cursor = database.query(DbHelper.TABLE_NAME, allColumns, null,null, null, null,"lanche"); 
	  cursor.moveToFirst(); 
      return cursor; 
	}
	
	private Lanchonete cursorToLanchonete(Cursor cursor) { 
        Lanchonete lanche = new 
             Lanchonete(cursor.getString(0),cursor.getString(1),cursor.getString(2), cursor.getString(3),
             cursor.getString(4), cursor.getString(5), cursor.getString(6));
        return lanche; 
    }
	
	public void fecharConexao() {
        if(database != null && database.isOpen())
            database.close(); 
    }

}
