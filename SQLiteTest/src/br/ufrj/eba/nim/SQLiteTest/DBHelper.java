package br.ufrj.eba.nim.SQLiteTest;

import java.io.File;  
import java.io.FileOutputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.io.OutputStream;  
import android.content.Context;  
import android.database.Cursor;  
import android.database.sqlite.SQLiteDatabase;  
import android.database.sqlite.SQLiteOpenHelper;  
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {  

	private static String DB_NAME = "database";  
	private SQLiteDatabase db;  
	private final Context context;  
	private String DB_PATH;  

	public DBHelper(Context context) {  
		super(context, DB_NAME, null, 1);  
		this.context = context;  
		DB_PATH = "/data/data/" + context.getPackageName() + "/" + "databases/";  
	}  

	public void createDataBase() throws IOException {  

		boolean dbExist = checkDataBase();  
		if (dbExist) {  

		} else {  
			this.getReadableDatabase();  
			try {  
				copyDataBase();  
			} catch (IOException e) {  
				throw new Error("Error copying database");  
			}  
		}  
	}  

	private boolean checkDataBase() {  
		File dbFile = new File(DB_PATH + DB_NAME);  
		return dbFile.exists();  
	}  

	private void copyDataBase() throws IOException {  

		InputStream myInput = context.getAssets().open(DB_NAME);  
		String outFileName = DB_PATH + DB_NAME;  
		OutputStream myOutput = new FileOutputStream(outFileName);  
		byte[] buffer = new byte[1024];  
		int length;  
		while ((length = myInput.read(buffer)) > 0) {  
			myOutput.write(buffer, 0, length);  
		}  

		// Close the streams  
		myOutput.flush();  
		myOutput.close();  
		myInput.close();  

	}  

	public Cursor getArtistas() {  
		String myPath = DB_PATH + DB_NAME;  
		db = SQLiteDatabase.openDatabase(myPath, null,  
				SQLiteDatabase.OPEN_READONLY);  
		Cursor c = db.rawQuery("SELECT * FROM artistas ORDER BY nome ASC", null);  
		// Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
		return c;  
	}  

	public Cursor getTipos(String artista) {  
		String myPath = DB_PATH + DB_NAME;  
		String query = new String();
		db = SQLiteDatabase.openDatabase(myPath, null,  
				SQLiteDatabase.OPEN_READONLY);
		query="SELECT DISTINCT tn ._id, tn.nome FROM tipos_nomes as tn, artistas as a, galeria as g WHERE tn._id=g.id_tipo";
		if(artista!="todos"){
			query+=" AND g.id_artista=a._id AND a.nome='"+artista+"'";
		}
		query+=" ORDER BY tn.nome ASC";
		Log.d("nim", query);
		Cursor c = db.rawQuery(query, null);  
		// Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
		return c;  
	}  

	public Cursor getObras(String artista, String tipo) {  
		String myPath = DB_PATH + DB_NAME;  
		String query = new String();
		db = SQLiteDatabase.openDatabase(myPath, null,  
				SQLiteDatabase.OPEN_READONLY);
		
		query="SELECT g._id, g.obra FROM galeria as g";
		if(artista!="todos"&&tipo!="todos"){
			
			query+=", tipos_nomes as tn, artistas as a";
			query+=" WHERE g.id_artista=a._id";
			query+=" AND a.nome='"+artista+"'";
			query+=" AND g.id_tipo=tn._id";
			query+=" AND tn.nome='"+tipo+"'";
			
		}else if(artista!="todos"){
			
			query+=", artistas as a";
			query+=" WHERE g.id_artista=a._id";
			query+=" AND a.nome='"+artista+"'";
			
		}else if(tipo!="todos"){
			query+=", tipos_nomes as tn";
			query+=" WHERE g.id_tipo=tn._id";
			query+=" AND tn.nome='"+tipo+"'";
			
		}
		query+=" ORDER BY g.obra ASC";
		Log.d("nim", query);
		Cursor c = db.rawQuery(query, null);  
		// Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
		return c;  
	}  

	public Cursor getTipos_Nomes() {  
		String myPath = DB_PATH + DB_NAME;  
		db = SQLiteDatabase.openDatabase(myPath, null,  
				SQLiteDatabase.OPEN_READONLY);  
		Cursor c = db.rawQuery("SELECT * FROM tipos_nomes", null);  
		// Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
		return c;  
	}  

	public Cursor getGaleria() {  
		String myPath = DB_PATH + DB_NAME;  
		db = SQLiteDatabase.openDatabase(myPath, null,  
				SQLiteDatabase.OPEN_READONLY);  
		Cursor c = db.rawQuery("SELECT * FROM galeria", null);  
		// Note: Master is the one table in External db. Here we trying to access the records of table from external db.  
		return c;  
	}  

	@Override  
	public void onCreate(SQLiteDatabase arg0) {  
		// TODO Auto-generated method stub  
	}  

	@Override  
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {  
		// TODO Auto-generated method stub  
	}  
}  