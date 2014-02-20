package br.ufrj.dcc.felipesfaria.TempoViagem;

import java.util.Calendar;
import java.util.Date;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends Activity {

	RadioGroup rdGrp_Caminho;
	RadioButton rdBtn_TnlStaBarbara,
				rdBtn_TnlReboucas,
				rdBtn_Leopoldina,
				rdBtn_StoCristo;
	Button btn_Confirmar;
	Calendar c;
	String str_Date;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		rdGrp_Caminho = (RadioGroup) findViewById(R.id.Caminho);
		btn_Confirmar = (Button) findViewById(R.id.Confirmar);
		c = Calendar.getInstance();
		TextView tv = (TextView) findViewById(R.id.textView1);
		tv.setText(c.getTime().toString());
				
		rdBtn_TnlReboucas = new RadioButton(this);
		rdBtn_TnlReboucas.setText("Tunel Rebouças");
		rdBtn_TnlReboucas.setOnClickListener(lstnr_Caminho);
		rdBtn_TnlReboucas.setId(0);
		
		rdBtn_TnlStaBarbara = new RadioButton(this);
		rdBtn_TnlStaBarbara.setText("Tunel Sta. Barbara");
		rdBtn_TnlStaBarbara.setOnClickListener(lstnr_Caminho);
		rdBtn_TnlStaBarbara.setId(1);
		
		rdBtn_Leopoldina = new RadioButton(this);		
		rdBtn_Leopoldina.setText("Leopoldina");
		rdBtn_Leopoldina.setOnClickListener(lstnr_Caminho);
		rdBtn_Leopoldina.setId(2);
		
		rdBtn_StoCristo = new RadioButton(this);
		rdBtn_StoCristo.setText("Sto Cristo");
		rdBtn_StoCristo.setOnClickListener(lstnr_Caminho);
		rdBtn_StoCristo.setId(3);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void onOrigemDestinoClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();

		// Check which radio button was clicked
		switch(view.getId()) {
		case R.id.laranjeirasFundao:
			if (checked)
				if(rdBtn_Leopoldina.isShown()){
					rdGrp_Caminho.removeView(rdBtn_Leopoldina);
					rdGrp_Caminho.removeView(rdBtn_StoCristo);
				}
					
				rdGrp_Caminho.addView(rdBtn_TnlStaBarbara);
				rdGrp_Caminho.addView(rdBtn_TnlReboucas);
				break;
		case R.id.fundaoLaranjeiras:
			if (checked)
				if(rdBtn_TnlStaBarbara.isShown()){
					rdGrp_Caminho.removeView(rdBtn_TnlReboucas);
					rdGrp_Caminho.removeView(rdBtn_TnlStaBarbara);
				}
				rdGrp_Caminho.addView(rdBtn_Leopoldina);
				rdGrp_Caminho.addView(rdBtn_StoCristo);
			break;
		}
	}
	
	private OnClickListener lstnr_Caminho = new OnClickListener() {

		@Override
		public void onClick(View view) {
			// Is the button now checked?
			boolean checked = ((RadioButton) view).isChecked();

			// Check which radio button was clicked
			switch(view.getId()) {
			case 0:
				if(checked){
					Log.d("nim","Rebouças");
				}
				break;
			case 1:
				if(checked){
					Log.d("nim","Sta Barbara");
				}
				break;
			case 2:
				if(checked){
					Log.d("nim","Leopoldina");
				}
				break;
			case 3:
				if(checked){
					Log.d("nim","StoCristo");
				}
				break;
			default:
				Log.d("nim","Nenhuma opereção valida");
				break;
			}
			btn_Confirmar.setVisibility(0);
		}
	};
	
	public void onConfirmarClicked(View v){
		Log.d("nim","Confimar clicked");
	}

}
