package br.nim.ufrj.galeriadomuseudomjoaovi;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

public class Contatos extends Activity {

	private int counter=0;
	ImageView imagem_contato;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contatos);
		imagem_contato = (ImageView) findViewById(R.id.contatos_imagem);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.contatos, menu);
		return true;
	}
	
	public void proximo_contato(View view){
		Log.d("NIM", "proximo_contato apertado");
		if (counter<3)
			counter++;
		atualizar();
	}
	
	public void anterior_contato(View view){
		Log.d("NIM", "anterior_contato apertado");
		if (counter>0)
			counter--;
		atualizar();
	}
	
	private void atualizar(){
		switch (counter)
		{
			case 0:
				imagem_contato.setImageResource(R.drawable.contatos);
				this.setTitle(R.string.title_activity_contatos);
				Log.d("NIM", "Counter ="+counter);
				break;
			case 1:
				imagem_contato.setImageResource(R.drawable.equipe);
				this.setTitle(R.string.title_activity_equipe);
				Log.d("NIM", "Counter ="+counter);
				break;
			case 2:
				imagem_contato.setImageResource(R.drawable.copyright);
				this.setTitle(R.string.title_activity_copyright);
				Log.d("NIM", "Counter ="+counter);
				break;
			case 3:
				imagem_contato.setImageResource(R.drawable.agradecimentos);
				this.setTitle(R.string.title_activity_agradecimentos);
				Log.d("NIM", "Counter ="+counter);
				break;
				
		}
	}

}

