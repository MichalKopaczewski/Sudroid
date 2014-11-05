package sudoku.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Arrays;

import sudoku.model.Rekords;


public class Ranking extends Activity {
    private ListView listView;
    private ArrayAdapter<String> adapter;
    private String[] rekords;
    private Rekords cos;
    private Button exitBut,newGameBut,menuBut;

    @Override
    protected void onResume() {
        super.onResume();
        exitBut.setOnClickListener(exitListener);
        newGameBut.setOnClickListener(newGameListener);
        menuBut.setOnClickListener(menuListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        listView = (ListView) findViewById(R.id.listView);
        exitBut = (Button) findViewById(R.id.przyciskWyjscie);
        newGameBut = (Button) findViewById(R.id.przyciskNowaGra);
        menuBut = (Button) findViewById(R.id.przyciskMenu);
        cos = new Rekords();
        rekords = cos.setRekordsToList();
        ArrayList<String> rekordsList = new ArrayList<String>();
        rekordsList.addAll(Arrays.asList(rekords));
        adapter = new ArrayAdapter<String>(this, R.layout.name_list_view,rekordsList);
        listView.setAdapter(adapter);
    }
    OnClickListener exitListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            exit();
        }
    };
    public void exit() {
        finish();
    }
    OnClickListener newGameListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            newGame();
        }
    };
    public void newGame() {
        Intent i = new Intent(this, NowaGra.class);
        startActivity(i);
        finish();
    }
    OnClickListener menuListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            menu();
        }
    };
    public void menu() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }

}
