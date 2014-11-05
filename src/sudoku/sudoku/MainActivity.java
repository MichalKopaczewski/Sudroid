package sudoku.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {
	public static MainActivity ma;
    private Button newGameBut,authorBut,exitBut,rankBut;
    @Override
    protected void onResume() {
        super.onResume();
        exitBut.setOnClickListener(exitListener);
        authorBut.setOnClickListener(authorListener);
        newGameBut.setOnClickListener(newGameListener);
        rankBut.setOnClickListener(rankListener);

    }

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ma = this;
		newGameBut = (Button) findViewById(R.id.przyciskNowaGra);
		authorBut = (Button) findViewById(R.id.przyciskAutor);
		exitBut = (Button) findViewById(R.id.przyciskWyjscie);
		rankBut = (Button) findViewById(R.id.ranking);

	}
    OnClickListener rankListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            openRanking();
        }
    };
    OnClickListener exitListener = new OnClickListener () {
        @Override
        public void onClick(View v) {
            finish();
        }

    };
    OnClickListener authorListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            showToast(R.string.autor);
        }
    };
    OnClickListener newGameListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            openNewGame();
        }
    };
    public void showToast(int refToString) {
        Toast.makeText(getApplicationContext(), refToString, Toast.LENGTH_SHORT).show();
    }
	public void openNewGame() {
		Intent i = new Intent(this, NowaGra.class);
		startActivity(i);
		finish();
	}
    public void openRanking() {
        Intent i = new Intent(this, Ranking.class);
        startActivity(i);
        finish();
    }
}
