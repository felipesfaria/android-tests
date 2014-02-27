package br.nim.ufrj.galeriadomuseudomjoaovi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {//Remove title bar
		Log.d("nim","App Start!");
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);

		//Remove notification bar
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void gotoMenuPrincipal(View view){
		Intent intent = new Intent(this, MenuPrincipal.class);
		startActivity(intent);
	}
}
