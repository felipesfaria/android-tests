package br.ufrj.eba.nim.SQLiteTest;

import java.io.IOException;  
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;  
import android.content.ClipData.Item;
import android.database.Cursor;  
import android.os.Bundle;  
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;  
import android.widget.SimpleCursorAdapter;  

public class GaleriaMenu extends Activity {  
	/** Called when the activity is first created. */  
	DBHelper dbhelper;  
	Boolean[] ativos = new Boolean[] {true, false, false, false};
	final int ARTISTAS = 0, TIPOS=1, OBRAS=2, FAVORITOS=3;
	int estado;
	String str_ArtistaSelecionado,str_TipoSelecionado;

	ListView list;
	@Override  
	public void onCreate(Bundle savedInstanceState) { 
		Log.d("Nim","SQLite Test: START!"); 
		super.onCreate(savedInstanceState);  
		setContentView(R.layout.main);


		dbhelper = new DBHelper(this,getString(R.string.todos));  
		try {  
			dbhelper.createDataBase();  
		} catch (IOException e) {  
			// TODO Auto-generated catch block  
			e.printStackTrace();  
		}  

		estado=ARTISTAS;

		str_ArtistaSelecionado=getString(R.string.todos);
		str_TipoSelecionado=getString(R.string.todos);

		list = (ListView) findViewById(R.id.ListView1);
		list.setOnItemClickListener(listOnClickListener);
		list.setChoiceMode(1);

		listarArtistas();
	}

	private OnItemClickListener listOnClickListener = new OnItemClickListener() {

		@Override
		public void onItemClick(AdapterView<?> adapter, View view, int position, long arg) {
			// TODO Auto-generated method stub
			Object listItem = list.getItemAtPosition(position);

			Log.d("Nim","Nome: "+listItem.toString());
			switch(estado){
			case ARTISTAS:
				estado=TIPOS;
				if(position==0){
					str_ArtistaSelecionado=getString(R.string.todos);
				}else {
					str_ArtistaSelecionado=listItem.toString();
				}
				listarTipos(str_ArtistaSelecionado);
				break;
			case TIPOS:
				estado=OBRAS;
				if(position==0){
					str_TipoSelecionado=getString(R.string.todos);
				}else {
					str_TipoSelecionado=listItem.toString();
				}
				listarObras(str_ArtistaSelecionado,str_TipoSelecionado);
				break;
			case OBRAS:
				if(listItem.toString().equals(getString(R.string.nenhum)))
					break;
				break;
			default:

			}

		}
	};

	@SuppressLint("NewApi")
	void listarArtistas(){
		List<String> arr_ListItems;


		Cursor c = dbhelper.getArtistas();
		c.moveToFirst();
		arr_ListItems = new ArrayList<String>();
		arr_ListItems.add(getString(R.string.todos));

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			arr_ListItems.add(c.getString(1));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,arr_ListItems);
		
		list.setAdapter(adapter);
		
		int selected_position=adapter.getPosition(str_ArtistaSelecionado);
		list.setSelection(selected_position);
		list.setItemChecked(selected_position, true);
	}

	void listarTipos(String artista){
		List<String> arr_ListItems;

		Cursor c = dbhelper.getTipos(artista);
		c.moveToFirst();
		arr_ListItems = new ArrayList<String>();
		arr_ListItems.add(getString(R.string.todos));

		for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
			arr_ListItems.add(c.getString(1));
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_activated_1,arr_ListItems);
		Log.d("nim","Selected position="+adapter.getPosition(str_TipoSelecionado));

		list.setAdapter(adapter);
		
		int selected_position=adapter.getPosition(str_TipoSelecionado);
		list.setSelection(selected_position);
		list.setItemChecked(selected_position, true);
	}

	void listarObras(String artista, String tipo){  
		List<String> arr_ListItems;
		arr_ListItems = new ArrayList<String>(); 

		Cursor c = dbhelper.getObras(artista,tipo);
		Log.d("nim","N Obras = "+c.getCount());
		if(c.getCount()==0){
			arr_ListItems.add(getString(R.string.nenhum));
		}else{
			c.moveToFirst();
			arr_ListItems.add(getString(R.string.todos));

			for(c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
				arr_ListItems.add(c.getString(1));
			}
		}

		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,arr_ListItems);


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
			listarTipos(str_ArtistaSelecionado);
		}
	}

	public void btn_obras_onClick(View v){
		Log.d("nim","Botao obras clickado");

		if(estado!=OBRAS){
			estado=OBRAS;
			listarObras(str_ArtistaSelecionado,str_TipoSelecionado);
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