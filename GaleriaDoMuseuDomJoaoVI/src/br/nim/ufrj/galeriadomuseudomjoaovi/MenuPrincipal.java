package br.nim.ufrj.galeriadomuseudomjoaovi;

import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MenuPrincipal extends Activity {

	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.d("nim","Menu principal entra");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_menu_principal);
		
		// Make sure we're running on Honeycomb or higher to use ActionBar APIs
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            // Show the Up button in the action bar.
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
		Log.d("nim","Menu principal sai");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}

	public void gotoGaleria(View view){
		Intent intent = new Intent(this, GaleriaMenu.class);
		startActivity(intent);
	}
	
	public void gotoPasseioPanoramico(View view){
		Intent intent = new Intent(this, PasseioPanoramico.class);
		startActivity(intent);
	}
	
	public void gotoComoChegar(View view){
		Intent intent = new Intent(this, ComoChegar.class);
		startActivity(intent);
	}
	
	public void gotoContatos(View view){
		Intent intent = new Intent(this, Contatos.class);
		startActivity(intent);
	}

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
        case android.R.id.home:
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
