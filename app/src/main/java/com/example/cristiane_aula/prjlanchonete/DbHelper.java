package com.example.cristiane_aula.prjlanchonete;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DbHelper extends SQLiteOpenHelper {

	private static final String DATABASE_NAME = "DBLanche"; 
    public static final String TABLE_NAME = "LANCHE";
	
    private final String DATABASE_CREATE = "CREATE TABLE "
            + TABLE_NAME
            + " (id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT ,"
            + "lanche TEXT NOT NULL, "
            + "bebida TEXT NOT NULL, "
            + "catchup TEXT NOT NULL, "
            + "mostarda TEXT NOT NULL, "
            + "picles TEXT NOT NULL, "
            + "alface TEXT NOT NULL, "
            + "gelo TEXT NOT NULL);";
	
	
	/*private static DbHelper instance;
	
	public static DbHelper getInstance(Context context)
	{
		if (instance==null)
			instance=new DbHelper(context);
		return instance;
	}*/
	
	/*
	 *O contexto da aplica��o (application context) � o reposit�rio central para a 
	 *funcionalidade de todas as  aplica��es de n�vel superior no Android. 
	 *Usa-se este contexto quando queremos aceder a configura��es e recursos compartilhados 
	 *entre as v�rias janelas (activities) da aplica��o.
	 */
	public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
}
	
	@Override 
    public void onCreate(SQLiteDatabase db) { 
          db.execSQL(DATABASE_CREATE); 
    }

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) { 
	        /*  Log.w(DbHelper.class.getName(), "Upgrading database from version " + oldVersion + 
	        		  "	to " + newVersion + ", which will destroy all old data"); 
	          db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); 
	onCreate(db);*/ 
	}
	
	
}
