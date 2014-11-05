 package sudoku.sudoku;

 import android.app.Activity;
 import android.content.Intent;
 import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import sudoku.model.Rekords;

import static android.view.View.OnClickListener;

 public class ZapiszWynik extends Activity {
    private Button saveButton;
    private EditText nick;
    private long czas1;
    private Button menuBut;
    protected void onResume () {
        super.onResume();
        menuBut.setOnClickListener(menuListener);
        saveButton.setOnClickListener(listenerSaveButton);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        czas1 = getIntent().getExtras().getLong("czas1");
        setContentView(R.layout.activity_zapisz_wynik);
        saveButton = (Button) findViewById(R.id.saveName);
        nick = (EditText) findViewById(R.id.editText1);
        menuBut = (Button) findViewById(R.id.przyciskMenu);
    }
    OnClickListener listenerSaveButton = new OnClickListener() {
        @Override
        public void onClick(View v) {
            new Rekords(czas1, nick.getText().toString());
            Toast.makeText(getApplicationContext(),"Rank saved",Toast.LENGTH_LONG).show();
            InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
            imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY,0);
            menu();
        }
    };
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
