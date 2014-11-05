//TODO LIST
/*
 * 1. Ekran zakonczenia gry
 * 2. Ranking wed�ug czas�w
 * 3. Wczytanie zapisanie planszy
 * 4. Dopracowanie algorytmow generowania sudoku pod wzgledem poziomu trudno�ci
 * 5. Ustawienie layout�w
 * 6. �adniejsze po�o�enie widok�w w layoutcie
 */


package sudoku.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextView;

import sudoku.model.WczytanieSudoku;

public class NowaGra extends Activity {
	private WczytanieSudoku sudokuBoardModel;
	private int lastTouchedX = 0, lastTouchedY = 0;
	private TextView boardFields[][];
	private Button steeringPanel[];
	private Button menuBut, startPauseBut,checkBut;
	private Chronometer timer;
	private boolean isTimerActivated = false;
	private long time = 0;
	
	
	@Override
	protected void onResume() {
		super.onResume();
		setActionForMenu();
		checkBut.setOnClickListener(checkListener);
		startPauseBut.setOnClickListener(startPauseListener);
	}	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_nowa_gra);
		menuBut = (Button) findViewById(R.id.przyciskMenu);
		startPauseBut = (Button) findViewById(R.id.startPauza);
		checkBut = (Button) findViewById(R.id.sprawdzanie);
        timer = (Chronometer) findViewById(R.id.chronometer1);
		boardFields = new TextView[9][9];
		steeringPanel = new Button[10];
		sudokuBoardModel = new WczytanieSudoku();
		viewToBoardFields();
		setPanelPRzyciskowWpisania();
		fillBoard();
		
	}
	public void turnOffActionForBoardFields() {
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j ++) {
				boardFields[i][j].setOnClickListener(null);
			}
		}
	}
	public void turnOnActionForBoardFields() {
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j ++) {
				boardFields[i][j].setOnClickListener(boardFieldsListener);
			}
		}
	}
	public void turnOffActionForPanel() {
		for (int i = 0; i < 10; i ++) {
			steeringPanel[i].setOnClickListener(null);
		}
	}
	public void turnOnActionForPanel() {
		for (int i = 0; i < 10; i ++) {
			steeringPanel[i].setOnClickListener(steeringPanelListener);
		}
	}
	public void exitToMenu() {
		Intent i = new Intent(this, MainActivity.class);
		startActivity(i);
		finish();
	}
	public void setColorField(int x, int y) {
		
		boardFields[lastTouchedX][lastTouchedY].setBackgroundColor(0x00000000);
		boardFields[x][y].setBackgroundColor(Color.parseColor("#5AAF00"));
		sameFieldsSameColor(x, y, Color.RED);
		lastTouchedX = x;
		lastTouchedY = y;
	}
	public void sameFieldsSameColor(int x, int y, int color) {
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j ++) {
				if (sudokuBoardModel.getGameBoard()[i][j] == sudokuBoardModel.getGameBoard()[x][y]) {
					boardFields[i][j].setTextColor(color);
				} else {
					boardFields[i][j].setTextColor(Color.BLACK);
				}
				
			}
		}
	}
	public void fillBoard() {
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j ++) {
				if (sudokuBoardModel.getGameBoard()[i][j] == 0) {
					boardFields[i][j].setText(null);
					boardFields[i][j].setTypeface(Typeface.DEFAULT);
					boardFields[i][j].setClickable(true);
				} else {
					boardFields[i][j].setText(String.valueOf(sudokuBoardModel.getGameBoard()[i][j]));
					boardFields[i][j].setTypeface(Typeface.DEFAULT, Typeface.BOLD);
					boardFields[i][j].setClickable(false);
					
				}
			}
		}
	}
	public void setPanelPRzyciskowWpisania() {
		steeringPanel[0] = (Button) findViewById(R.id.Button00);
		steeringPanel[1] = (Button) findViewById(R.id.Button01);
		steeringPanel[2] = (Button) findViewById(R.id.Button02);
		steeringPanel[3] = (Button) findViewById(R.id.Button03);
		steeringPanel[4] = (Button) findViewById(R.id.Button04);
		steeringPanel[5] = (Button) findViewById(R.id.Button05);
		steeringPanel[6] = (Button) findViewById(R.id.Button06);
		steeringPanel[7] = (Button) findViewById(R.id.Button07);
		steeringPanel[8] = (Button) findViewById(R.id.Button08);
		steeringPanel[9] = (Button) findViewById(R.id.Button09);

	}
	public void viewToBoardFields() {
		boardFields[0][0] = (TextView) findViewById(R.id.TextView00);
		boardFields[0][1] = (TextView) findViewById(R.id.TextView01);
		boardFields[0][2] = (TextView) findViewById(R.id.TextView02);
		boardFields[0][3] = (TextView) findViewById(R.id.TextView03);
		boardFields[0][4] = (TextView) findViewById(R.id.TextView04);
		boardFields[0][5] = (TextView) findViewById(R.id.TextView05);
		boardFields[0][6] = (TextView) findViewById(R.id.TextView06);
		boardFields[0][7] = (TextView) findViewById(R.id.TextView07);
		boardFields[0][8] = (TextView) findViewById(R.id.TextView08);

		boardFields[1][0] = (TextView) findViewById(R.id.TextView10);
		boardFields[1][1] = (TextView) findViewById(R.id.TextView11);
		boardFields[1][2] = (TextView) findViewById(R.id.TextView12);
		boardFields[1][3] = (TextView) findViewById(R.id.TextView13);
		boardFields[1][4] = (TextView) findViewById(R.id.TextView14);
		boardFields[1][5] = (TextView) findViewById(R.id.TextView15);
		boardFields[1][6] = (TextView) findViewById(R.id.TextView16);
		boardFields[1][7] = (TextView) findViewById(R.id.TextView17);
		boardFields[1][8] = (TextView) findViewById(R.id.TextView18);

		boardFields[2][0] = (TextView) findViewById(R.id.TextView20);
		boardFields[2][1] = (TextView) findViewById(R.id.TextView21);
		boardFields[2][2] = (TextView) findViewById(R.id.TextView22);
		boardFields[2][3] = (TextView) findViewById(R.id.TextView23);
		boardFields[2][4] = (TextView) findViewById(R.id.TextView24);
		boardFields[2][5] = (TextView) findViewById(R.id.TextView25);
		boardFields[2][6] = (TextView) findViewById(R.id.TextView26);
		boardFields[2][7] = (TextView) findViewById(R.id.TextView27);
		boardFields[2][8] = (TextView) findViewById(R.id.TextView28);

		boardFields[3][0] = (TextView) findViewById(R.id.TextView30);
		boardFields[3][1] = (TextView) findViewById(R.id.TextView31);
		boardFields[3][2] = (TextView) findViewById(R.id.TextView32);
		boardFields[3][3] = (TextView) findViewById(R.id.TextView33);
		boardFields[3][4] = (TextView) findViewById(R.id.TextView34);
		boardFields[3][5] = (TextView) findViewById(R.id.TextView35);
		boardFields[3][6] = (TextView) findViewById(R.id.TextView36);
		boardFields[3][7] = (TextView) findViewById(R.id.TextView37);
		boardFields[3][8] = (TextView) findViewById(R.id.TextView38);

		boardFields[4][0] = (TextView) findViewById(R.id.TextView40);
		boardFields[4][1] = (TextView) findViewById(R.id.TextView41);
		boardFields[4][2] = (TextView) findViewById(R.id.TextView42);
		boardFields[4][3] = (TextView) findViewById(R.id.TextView43);
		boardFields[4][4] = (TextView) findViewById(R.id.TextView44);
		boardFields[4][5] = (TextView) findViewById(R.id.TextView45);
		boardFields[4][6] = (TextView) findViewById(R.id.TextView46);
		boardFields[4][7] = (TextView) findViewById(R.id.TextView47);
		boardFields[4][8] = (TextView) findViewById(R.id.TextView48);

		boardFields[5][0] = (TextView) findViewById(R.id.TextView50);
		boardFields[5][1] = (TextView) findViewById(R.id.TextView51);
		boardFields[5][2] = (TextView) findViewById(R.id.TextView52);
		boardFields[5][3] = (TextView) findViewById(R.id.TextView53);
		boardFields[5][4] = (TextView) findViewById(R.id.TextView54);
		boardFields[5][5] = (TextView) findViewById(R.id.TextView55);
		boardFields[5][6] = (TextView) findViewById(R.id.TextView56);
		boardFields[5][7] = (TextView) findViewById(R.id.TextView57);
		boardFields[5][8] = (TextView) findViewById(R.id.TextView58);

		boardFields[6][0] = (TextView) findViewById(R.id.TextView60);
		boardFields[6][1] = (TextView) findViewById(R.id.TextView61);
		boardFields[6][2] = (TextView) findViewById(R.id.TextView62);
		boardFields[6][3] = (TextView) findViewById(R.id.TextView63);
		boardFields[6][4] = (TextView) findViewById(R.id.TextView64);
		boardFields[6][5] = (TextView) findViewById(R.id.TextView65);
		boardFields[6][6] = (TextView) findViewById(R.id.TextView66);
		boardFields[6][7] = (TextView) findViewById(R.id.TextView67);
		boardFields[6][8] = (TextView) findViewById(R.id.TextView68);

		boardFields[7][0] = (TextView) findViewById(R.id.TextView70);
		boardFields[7][1] = (TextView) findViewById(R.id.TextView71);
		boardFields[7][2] = (TextView) findViewById(R.id.TextView72);
		boardFields[7][3] = (TextView) findViewById(R.id.TextView73);
		boardFields[7][4] = (TextView) findViewById(R.id.TextView74);
		boardFields[7][5] = (TextView) findViewById(R.id.TextView75);
		boardFields[7][6] = (TextView) findViewById(R.id.TextView76);
		boardFields[7][7] = (TextView) findViewById(R.id.TextView77);
		boardFields[7][8] = (TextView) findViewById(R.id.TextView78);

		boardFields[8][0] = (TextView) findViewById(R.id.TextView80);
		boardFields[8][1] = (TextView) findViewById(R.id.TextView81);
		boardFields[8][2] = (TextView) findViewById(R.id.TextView82);
		boardFields[8][3] = (TextView) findViewById(R.id.TextView83);
		boardFields[8][4] = (TextView) findViewById(R.id.TextView84);
		boardFields[8][5] = (TextView) findViewById(R.id.TextView85);
		boardFields[8][6] = (TextView) findViewById(R.id.TextView86);
		boardFields[8][7] = (TextView) findViewById(R.id.TextView87);
		boardFields[8][8] = (TextView) findViewById(R.id.TextView88);


	}
	public void stopTimer() {
		startPauseBut.setText("Start");
		turnOffActionForBoardFields();
		turnOffActionForPanel();
		time = timer.getBase() - SystemClock.elapsedRealtime();
		timer.stop();
		isTimerActivated =false;
	}
	public void startTimer() {
		startPauseBut.setText("Stop");
		turnOnActionForBoardFields();
		turnOnActionForPanel();
		timer.setBase(time + SystemClock.elapsedRealtime());
		timer.start();
		isTimerActivated =true;
	}
	public void ifWon() {
		Intent i = new Intent(NowaGra.this, KoniecWygrales.class);
		i.putExtra("time", time);
		finish();
		startActivity(i);
		
	}
	public void setActionForMenu() {
		menuBut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                exitToMenu();
            }
        });
	}
	OnClickListener startPauseListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			if (isTimerActivated) {
				stopTimer();
				
			} else {
				startTimer();
			}
		}
	};
	OnClickListener steeringPanelListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
            int source = v.getId();
			switch (source) {
			case R.id.Button00:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText(null);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 0);
				}
				break;
			case R.id.Button01:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText("1");
					boardFields[lastTouchedX][lastTouchedY].setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 1);
				}
				break;
			case R.id.Button02:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText("2");
					boardFields[lastTouchedX][lastTouchedY].setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 2);
				}
				break;
			case R.id.Button03:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText("3");
					boardFields[lastTouchedX][lastTouchedY].setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 3);
				}
				break;
			case R.id.Button04:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText("4");
					boardFields[lastTouchedX][lastTouchedY].setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 4);
				}
				break;
			case R.id.Button05:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText("5");
					boardFields[lastTouchedX][lastTouchedY].setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 5);
				}
				break;
			case R.id.Button06:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText("6");
					boardFields[lastTouchedX][lastTouchedY].setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 6);
				}
				break;
			case R.id.Button07:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText("7");
					boardFields[lastTouchedX][lastTouchedY].setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 7);
				}
				break;
			case R.id.Button08:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText("8");
					boardFields[lastTouchedX][lastTouchedY].setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 8);
				}
				break;
			case R.id.Button09:
				if (!boardFields[lastTouchedX][lastTouchedY].getTypeface().isBold()) {
					boardFields[lastTouchedX][lastTouchedY].setText("9");
					boardFields[lastTouchedX][lastTouchedY].setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
					sudokuBoardModel.setKomorka(lastTouchedX, lastTouchedY, 9);
				}
				break;
			}
			sameFieldsSameColor(lastTouchedX, lastTouchedY, Color.RED);
		}
	};
	
	OnClickListener checkListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			turnOffActionForBoardFields();
			if (isTimerActivated) {
				time = timer.getBase() - SystemClock.elapsedRealtime();
				timer.stop();
			}
			if (sudokuBoardModel.sprawdzSudoku(sudokuBoardModel.getGameBoard())) {
				checkBut.setBackgroundColor(Color.GREEN);
				ifWon();
			} else {
				checkBut.setBackgroundColor(Color.RED);
				ifWon();
			}
			
		}
	};
	OnClickListener boardFieldsListener = new OnClickListener () {
		@Override
		public void onClick(View v) {
			 int source = v.getId();
			
			switch (source) {
			case R.id.TextView00:
				setColorField(0, 0);
				break;
			case R.id.TextView01:
				setColorField(0, 1);
				break;
			case R.id.TextView02:
				setColorField(0, 2);
				break;
			case R.id.TextView03:
				setColorField(0, 3);
				break;
			case R.id.TextView04:
				setColorField(0, 4);
				break;
			case R.id.TextView05:
				setColorField(0, 5);
				break;
			case R.id.TextView06:
				setColorField(0, 6);
				break;
			case R.id.TextView07:
				setColorField(0, 7);
				break;
			case R.id.TextView08:
				setColorField(0, 8);
				break;
			case R.id.TextView10:
				setColorField(1, 0);
				break;
			case R.id.TextView11:
				setColorField(1, 1);
				break;
			case R.id.TextView12:
				setColorField(1, 2);
				break;
			case R.id.TextView13:
				setColorField(1, 3);
				break;
			case R.id.TextView14:
				setColorField(1, 4);
				break;
			case R.id.TextView15:
				setColorField(1, 5);
				break;
			case R.id.TextView16:
				setColorField(1, 6);
				break;
			case R.id.TextView17:
				setColorField(1, 7);
				break;
			case R.id.TextView18:
				setColorField(1, 8);
				break;
			case R.id.TextView20:
				setColorField(2, 0);
				break;
			case R.id.TextView21:
				setColorField(2, 1);
				break;
			case R.id.TextView22:
				setColorField(2, 2);
				break;
			case R.id.TextView23:
				setColorField(2, 3);
				break;
			case R.id.TextView24:
				setColorField(2, 4);
				break;
			case R.id.TextView25:
				setColorField(2, 5);
				break;
			case R.id.TextView26:
				setColorField(2, 6);
				break;
			case R.id.TextView27:
				setColorField(2, 7);
				break;
			case R.id.TextView28:
				setColorField(2, 8);
				break;
			case R.id.TextView30:
				setColorField(3, 0);
				break;
			case R.id.TextView31:
				setColorField(3, 1);
				break;
			case R.id.TextView32:
				setColorField(3, 2);
				break;
			case R.id.TextView33:
				setColorField(3, 3);
				break;
			case R.id.TextView34:
				setColorField(3, 4);
				break;
			case R.id.TextView35:
				setColorField(3, 5);
				break;
			case R.id.TextView36:
				setColorField(3, 6);
				break;
			case R.id.TextView37:
				setColorField(3, 7);
				break;
			case R.id.TextView38:
				setColorField(3, 8);
				break;
			case R.id.TextView40:
				setColorField(4, 0);
				break;
			case R.id.TextView41:
				setColorField(4, 1);
				break;
			case R.id.TextView42:
				setColorField(4, 2);
				break;
			case R.id.TextView43:
				setColorField(4, 3);
				break;
			case R.id.TextView44:
				setColorField(4, 4);
				break;
			case R.id.TextView45:
				setColorField(4, 5);
				break;
			case R.id.TextView46:
				setColorField(4, 6);
				break;
			case R.id.TextView47:
				setColorField(4, 7);
				break;
			case R.id.TextView48:
				setColorField(4, 8);
				break;
			case R.id.TextView50:
				setColorField(5, 0);
				break;
			case R.id.TextView51:
				setColorField(5, 1);
				break;
			case R.id.TextView52:
				setColorField(5, 2);
				break;
			case R.id.TextView53:
				setColorField(5, 3);
				break;
			case R.id.TextView54:
				setColorField(5, 4);
				break;
			case R.id.TextView55:
				setColorField(5, 5);
				break;
			case R.id.TextView56:
				setColorField(5, 6);
				break;
			case R.id.TextView57:
				setColorField(5, 7);
				break;
			case R.id.TextView58:
				setColorField(5, 8);
				break;
			case R.id.TextView60:
				setColorField(6, 0);
				break;
			case R.id.TextView61:
				setColorField(6, 1);
				break;
			case R.id.TextView62:
				setColorField(6, 2);
				break;
			case R.id.TextView63:
				setColorField(6, 3);
				break;
			case R.id.TextView64:
				setColorField(6, 4);
				break;
			case R.id.TextView65:
				setColorField(6, 5);
				break;
			case R.id.TextView66:
				setColorField(6, 6);
				break;
			case R.id.TextView67:
				setColorField(6, 7);
				break;
			case R.id.TextView68:
				setColorField(6, 8);
				break;
			case R.id.TextView70:
				setColorField(7, 0);
				break;
			case R.id.TextView71:
				setColorField(7, 1);
				break;
			case R.id.TextView72:
				setColorField(7, 2);
				break;
			case R.id.TextView73:
				setColorField(7, 3);
				break;
			case R.id.TextView74:
				setColorField(7, 4);
				break;
			case R.id.TextView75:
				setColorField(7, 5);
				break;
			case R.id.TextView76:
				setColorField(7, 6);
				break;
			case R.id.TextView77:
				setColorField(7, 7);
				break;
			case R.id.TextView78:
				setColorField(7, 8);
				break;
			case R.id.TextView80:
				setColorField(8, 0);
				break;
			case R.id.TextView81:
				setColorField(8, 1);
				break;
			case R.id.TextView82:
				setColorField(8, 2);
				break;
			case R.id.TextView83:
				setColorField(8, 3);
				break;
			case R.id.TextView84:
				setColorField(8, 4);
				break;
			case R.id.TextView85:
				setColorField(8, 5);
				break;
			case R.id.TextView86:
				setColorField(8, 6);
				break;
			case R.id.TextView87:
				setColorField(8, 7);
				break;
			case R.id.TextView88:
				setColorField(8, 8);
				break;
			}
		}
	};
	
}
