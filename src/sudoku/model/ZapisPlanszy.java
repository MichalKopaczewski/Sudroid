package sudoku.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import sudoku.sudoku.MainActivity;
import android.content.Context;
import android.os.Environment;
import android.util.Log;

public class ZapisPlanszy {
	private int plansza[][],planszaOryginalna[][];
	private final String FOLDER_ZAPISU_DANYCH = "Files";
	private final String FOLDER_ZAPISU_PLANSZY = "Plansza";
	private final String FOLDER_ZAPISU_RANKINGU = "Ranking";
	private File dir,plik;
	private boolean isSaved;
	private FileInputStream streamIn;
	private FileOutputStream streamOut;
	
	
	public ZapisPlanszy(int tPlansza[][],int tOryginal[][], long czas1) {
		plansza = tPlansza;
		
		createDirAndFile("1_plansza");
		saveDataToFile(tPlansza, tOryginal, czas1);
	}
	public void createDirAndFile (String nameFile) {
		dir = new File(MainActivity.ma.getApplication().getExternalFilesDir(FOLDER_ZAPISU_DANYCH), FOLDER_ZAPISU_PLANSZY);
		if (!dir.mkdirs()) {
			Log.d("Tablica0", "Directory is missing");
		}
		plik = new File(dir, nameFile);
	}
	public void saveDataToFile(int tPlansza[][],int tOryginal[][], long czas1) {
		String data = intDataToStringData(tPlansza, tOryginal, czas1);
		try {
			streamOut = new FileOutputStream(plik);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			streamOut.write(data.getBytes());
			streamOut.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String intDataToStringData (int[][] tPlansza, int[][] tOryginal, long czas) {
		String data = "";
		data = intTableToString(tPlansza) + " " + intTableToString(tOryginal) + " " + String.valueOf(czas);
		return data;
	}
	public String intTableToString(int[][] table) {
		String data = "";
		for (int i = 0; i < 9; i ++) {
			for (int j = 0; j < 9; j ++) {
				data+=String.valueOf(table[i][j]);
			}
		}
		return data;
	}
	public boolean isExternalStorageWritable() {
		String status = Environment.getExternalStorageState();
		if (Environment.MEDIA_MOUNTED.equals(status)) {
			return true;
		}
		return false;
	}
	public boolean getIsSaved () {
		return isSaved;
	}
}
