package br.ufrj.eba.nim.SQLiteTest;

import java.io.IOException;  
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;  
import android.database.Cursor;  
import android.os.Bundle;  
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;  
import android.widget.SimpleCursorAdapter;  

public class GaleriaMenu extends Activity {  
	/** Called when the activity is first created. */  
	DBHelper dbhelper;  
	Boolean[] ativos = new Boolean[] {true, false, false, false};
	final int ARTISTAS = 0, TIPOS=1, OBRAS=2, FAVORITOS=3;
	int estado;
	@Override  
	public void onCreate(Bundle savedInstanceState) {  
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.main);
		// if you use simplecursoradapter then you should have _id as one of column name and its values should be integer in your db.  
		// so "_id", "columnName1", "columnName2" are column names from your db.
		estado=ARTISTAS;
		listarArtistas();
	}  


	void listarArtistas(){  
		String[] from = new String[] {"nome"};  
		int[] to = new int[] { R.id.TextView1};
		List<String> arr_Artistas;

		dbhelper = new DBHelper(this);  
		try {  
			dbhelper.createDataBase();  
		} catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  

		Cursor c = dbhelper.getArtistas();
		c.moveToFirst();
		arr_Artistas = new ArrayList<String>();
		arr_Artistas.add("Todos");
		int i=1;
		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			// The Cursor is now set to the right position
			arr_Artistas.add(c.getString(1));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr_Artistas);

		ListView list = (ListView) findViewById(R.id.ListView1);  
		list.setAdapter(adapter);
	}

	void listarTipos(){  
		String[] from = new String[] {"nome"};  
		int[] to = new int[] { R.id.TextView1};  

		dbhelper = new DBHelper(this);  
		try {  
			dbhelper.createDataBase();  
		} catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  

		Cursor c = dbhelper.getTipos("todos");

		SimpleCursorAdapter adapter = new SimpleCursorAdapter(  
				getApplicationContext(), R.layout.list, c, from, to);  

		ListView list = (ListView) findViewById(R.id.ListView1);  

		list.setAdapter(adapter);
	}

	public void btn_artistas_onClick(View v){
		Log.d("nim","Botao artistas clickado");

		if(estado!=ARTISTAS){
			estado=ARTISTAS;
			listarArtistas();
		}
	}

	public void btn_tecnicas_onClick(View v){
		Log.d("nim","Botao tecnicas clickado");

		if(estado!=TIPOS){
			estado=TIPOS;
			listarTipos();
		}
	}

	public void btn_obras_onClick(View v){
		Log.d("nim","Botao obras clickado");

		if(estado!=OBRAS){
			estado=OBRAS;
			//listarObras();
		}
	}

	public void btn_favoritos_onClick(View v){
		Log.d("nim","Botao favoritos clickado");

		if(estado!=FAVORITOS){
			estado=FAVORITOS;
			//listarFavoritos();
		}
	}
}  