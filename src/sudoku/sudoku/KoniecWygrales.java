package sudoku.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class KoniecWygrales extends Activity {
	private Button newGameBut, saveBut,rankBut, menuBut, exitBut;
	private TextView solveTimeTextView;
	private long time;
	
	@Override
	protected void onResume() {
		super.onResume();
		newGameBut.setOnClickListener(newGameListener);
		menuBut.setOnClickListener(menuListener);
		exitBut.setOnClickListener(exitListener);
		saveBut.setOnClickListener(saveListener);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_koniec_wygrales);
		time = getIntent().getExtras().getLong("time");
		solveTimeTextView = (TextView) findViewById(R.id.time);
		solveTimeTextView.setText(intToWynikCzasowy(time));
		newGameBut = (Button) findViewById(R.id.zacznijOdNowa);
		menuBut = (Button) findViewById(R.id.menu);
		exitBut = (Button) findViewById(R.id.exit);
		saveBut = (Button) findViewById(R.id.zapiszWynik);
		
	}
	public String intToWynikCzasowy(long czas) {
		String wynik="";
		int minuty=getMinuty(czas),sekundy=getSekundy(czas);
		if (minuty==0) {
			return wynik += String.valueOf(sekundy) + " " + getSlowoSekunda(sekundy);
		} else {
			String czescMinut = String.valueOf(minuty) + " " + getSlowoMinuta(minuty);
			String czescSekund = String.valueOf(sekundy) + " " + getSlowoSekunda(sekundy);
			return wynik += czescMinut + " " + czescSekund;
		}
	}
	public String getSlowoSekunda(int sekunda) {
		if (sekunda == 0) {
			return getString(R.string.sekund);
		} else if (sekunda == 1) {
			return getString(R.string.sekunda);
		} else if ((sekunda >= 2) && (sekunda <= 4)) {
			return getString(R.string.sekundy);
		} else {
			return getString(R.string.sekund);
		}
	}
	public String getSlowoMinuta(int minuta) {
		if (minuta == 0) {
			return getString(R.string.minut);
		} else if (minuta == 1) {
			return getString(R.string.minuta);
		} else if ((minuta >= 2) && (minuta <= 4)) {
			return getString(R.string.minuty);
		} else {
			return getString(R.string.minut);
		}
	}
	public int getMinuty(long czas) {
		int pom = (int) czas/-1000;
		return pom/60;
	}
	public int getSekundy(long czas) {
		return ((int) czas/-1000)-(60*getMinuty(czas));
	}
	OnClickListener newGameListener = new OnClickListener () {
		@Override
		public void onClick(View v) {
			reakcjaZacznijOdNowa();
		}
	};
	public void reakcjaZacznijOdNowa() {
		Intent i = new Intent(KoniecWygrales.this, NowaGra.class);
		finish();
		startActivity(i);
	}
	OnClickListener menuListener = new OnClickListener () {
		@Override
		public void onClick(View v) {
			reakcjaMenu();
		}
	};
	public void reakcjaMenu() {
		Intent i = new Intent(KoniecWygrales.this, MainActivity.class);
		finish();
		startActivity(i);
	}
	OnClickListener exitListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			finish();
			
		}
	};
	OnClickListener saveListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			reakcjaDlaZapiszWynik();
		}
	};
	public void reakcjaDlaZapiszWynik() {
		Intent i = new Intent(KoniecWygrales.this, ZapiszWynik.class);
        i.putExtra("time", time);
        finish();
		startActivity(i);
	}
}
