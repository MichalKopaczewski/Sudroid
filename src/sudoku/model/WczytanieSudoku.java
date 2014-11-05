package sudoku.model;

import android.content.Context;

import java.util.Random;

import sudoku.sudoku.MainActivity;
import sudoku.sudoku.R;

public class WczytanieSudoku {

	private int gameBoard[][];
	private final int GENERATE_BOARD[][];
	private Context appContext;
	private final int COUNT_MIXED =50;
	private final int ILOSC_POL=7;

	public WczytanieSudoku () {
		appContext = MainActivity.ma.getApplicationContext();
		setEdytowanaPlansza();
		mieszajPlansze();
		randomizeNullFields();
		GENERATE_BOARD = new int[9][9];
		setGENERATE_BOARD_fromGameBoard();
		
	}
	public void randomizeNullFields() {
		for (int i = 0; i < 9; i ++) {
			int k = new Random().nextInt(2) + ILOSC_POL;
			for (int j = 0; j < k; j ++) {
				gameBoard[i][new Random().nextInt(9)] = 0;
			}
		}
	}
	public void setGENERATE_BOARD_fromGameBoard() {
		for (int i = 0 ; i < 9; i ++) {
			for (int j = 0 ; j < 9; j ++) {
				GENERATE_BOARD[i][j] = gameBoard[i][j];
			}
		}
	}
	public void setKomorka (int x, int y, int a) {
		gameBoard[x][y]=a;
	}
	public int[][] getGENERATE_BOARD() {
		return GENERATE_BOARD;
	}
	public int[][] getGameBoard(){
		return gameBoard;
	}
	public void setEdytowanaPlansza () {
		int numerPlanszy = losujWartosc();
		gameBoard = losujPlansze(numerPlanszy);
	}
	public boolean sprawdzSudoku ( int[][] tab) {
		long suma,suma2;
        for (int i = 0; i < 9; i ++) {
        	suma=1;
            suma2=1;
            for (int j = 0; j < 9; j ++) {
                suma *= gameBoard[i][j];
                suma2 *= gameBoard[j][i];
            }
            if ((suma != 362880) || (suma2 != 362880)) {
                return false;
            }
        }
        int[] pom = new int[9];
        for (int i = 0; i < 9; i ++) {
            pom[i] = 1;
        }
        for (int i = 0; i < 3; i ++) {
            for (int j = 0; j < 3; j ++) {
                pom[0] *= gameBoard[i][j];
                pom[1] *= gameBoard[i][j+3];
                pom[2] *= gameBoard[i][j+6];
                pom[3] *= gameBoard[i+3][j];
                pom[4] *= gameBoard[i+3][j+3];
                pom[5] *= gameBoard[i+3][j+6];
                pom[6] *= gameBoard[i+6][j];
                pom[7] *= gameBoard[i+6][j+3];
                pom[8] *= gameBoard[i+6][j+6];
            }
        }
        for (int i=0; i<9; i++) {
            if (pom[i]!=362880) {
                return false;
            }
        }
        return true;
	}
	public int[][] konwersjaTablicy (int tab[]) {
		int tab2[][] = new int [9][9];
		int k = 0;
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j ++) {
				tab2[i][j] = tab[k];
				k++;
			}
		}
		return tab2;
	}
	
	public int losujWartosc () {
		int a = new Random().nextInt(10);
		return a;
	}
	
	public int[][] losujPlansze (int wartosc) {
		int planszaZ_XML[];
		switch (wartosc) {
			case 0:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza0);
				break;
			case 1:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza1);
				break;
			case 2:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza2);
				break;
			case 3:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza3);
				break;
			case 4:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza4);
				break;
			case 5:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza5);
				break;
			case 6:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza6);
				break;
			case 7:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza7);
				break;
			case 8:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza8);
				break;
			default:
				planszaZ_XML = appContext.getResources().getIntArray(R.array.plansza9);
				break;
		}
		
		return konwersjaTablicy(planszaZ_XML);
	}
	
	public void mieszajPlansze() {
		mieszaj1x9();
		mieszaj3x9();
	}
	
	public void mieszaj1x9 () {
		for (int i = 0; i < COUNT_MIXED; i ++) {
			for (int j = 0; j <= 6; j += 3 ) {
				int a = new Random().nextInt(3) + j, b = new Random().nextInt(3) + j;
				int tA[] = getWiersz(a, true);
				int tB[] = getWiersz(b, true);
				setWierszInTable(a, tB, true);
				setWierszInTable(b, tA, true);
				
				a = new Random().nextInt(3) + j; b = new Random().nextInt(3) + j;
				tA = getWiersz(a, false);
				tB = getWiersz(b, false);
				setWierszInTable(a, tB, false);
				setWierszInTable(b, tA, false);
			}
		}
	}
	
	public void mieszaj3x9 () {
		for (int i = 0; i < COUNT_MIXED; i ++) {
			int a = new Random().nextInt(3); 
			int	b = new Random().nextInt(3);
			int tA[][] = new int[3][9];
			int tB[][] = new int[3][9];
			tA = get3x9(a, true);
			tB = get3x9(b, true);
			set3x9(tA, b, true);
			set3x9(tB, a, true);
			a = new Random().nextInt(3); 
			b = new Random().nextInt(3);
			tA = get3x9(a, false);
			tB = get3x9(b, false);
			set3x9(tA, b, false);
			set3x9(tB, a, false);
		}
	}
	
	public void set3x9 (int[][] tab, int numerCzesci, boolean czyPoz) {
		if (czyPoz == true) {
			for (int i = 0 + (numerCzesci * 3); i < 3 + (numerCzesci * 3); i ++) {
				for (int j = 0; j < 9; j ++) {
					gameBoard[i][j] = tab[i - (numerCzesci * 3)][j];
				}
			}
		} else {
			for (int i = 0 + (numerCzesci * 3); i < 3 + (numerCzesci * 3); i ++) {
				for (int j = 0; j < 9; j ++) {
					gameBoard[j][i] = tab[i - (numerCzesci * 3)][j];
				}
			}
		}
	}
	
	public int[][] get3x9 (int numerCzesci, boolean czyPoz) {
		int[][] tab = new int[3][9];
		if (czyPoz == true) {
			for (int i = 0 + (numerCzesci * 3); i < 3 + (numerCzesci * 3); i ++) {
				for (int j = 0; j < 9; j++) {
					tab[i - (numerCzesci * 3)][j] = gameBoard[i][j];
				}
			}
		} else {
			for (int i = 0 + (numerCzesci * 3); i < 3 + (numerCzesci * 3); i ++) {
				for (int j = 0; j < 9; j ++) {
					tab[i - (numerCzesci * 3)][j] = gameBoard[j][i];
				}
			}
		}
		return tab;
	}
	
	public int[] getWiersz(int a, boolean czyPoz) {
		int[] tab = new int[9];
		if ( czyPoz == true ) {
			for (int i = 0; i < 9; i ++) {
				tab[i] = gameBoard[a][i];
			}
		} else {
			for (int i = 0; i < 9; i ++) {
				tab[i] = gameBoard[i][a];
			}
		}
		return tab;
	}
	
	public void setWierszInTable( int a, int tab[], boolean czyPoz) {
		if ( czyPoz == true ) {
			for (int i = 0; i < 9; i ++) {
				gameBoard[a][i] = tab[i];
			}
		} else {
			for (int i = 0; i < 9; i ++) {
				gameBoard[i][a] = tab[i];
			}
		}
	}
	
}
