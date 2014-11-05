package sudoku.model;

import android.content.Context;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import sudoku.sudoku.MainActivity;

/* TO DO
    1. Sprawdzic czy plik sie utworzyl poprawnie, jesli nie to cos tam zrobic
 */

public class Rekords {
    private final String FILE_TIME = "Czas.txt";
    private final String FILE_NAME = "Nazwa.txt";
    private final String FOLDER_NAME = "Nazwa";
    private final String FOLDER_ZAPISU_RANKINGU = "Ranking";
    private final int MAX_REKORDS = 19;
    private int nr=0;
    private Rekord users[];
    private File fileName, fileTime,dir;
    private long time;
    private Scanner scanFromFile;
    public Rekords () {
        users = new Rekord[100];
        for (int i=0; i<100; i++) {
            users[i] = new Rekord();
        }
        createFiles();
        getInfoFromName();
        getInfoFromTime();
        sortUsers();
    }
    public Rekords (long czas, String nick) {
        users = new Rekord[100];
        for (int i=0; i<100; i++) {
            users[i] = new Rekord();
        }
        time = czas * -1;
        createFiles();
        getInfoFromName();
        getInfoFromTime();
        users[nr].setName(nick);
        users[nr].setTime(time);
        setDataToNameFile();
        setDataToTimeFile();
        getInfoFromName();
        getInfoFromTime();
    }
    public String[] setRekordsToList () {
        String table[] = new String[nr];
        for (int i = 0; i < nr; i ++){
            String s;
            if (getMinutes(users[i].getTime())<10) {
                s = "0" + String.valueOf(getMinutes(users[i].getTime())) + "m ";
            } else {
                s = String.valueOf(getMinutes(users[i].getTime())) + "m ";
            }
            if (getSeconds(users[i].getTime())<10) {

                s+= "0" + String.valueOf(getSeconds(users[i].getTime())) + "s";
            } else {
                s+= String.valueOf(getSeconds(users[i].getTime())) + "s";
            }
            table[i] = s + "   ";
            if (users[i].getName().equals("null") || users[i].getName().equals("")) {
                table[i] += "No name";
            } else {
                table[i] += users[i].getName();
            }
        }
        return table;
    }

    public void sortUsers() {
        boolean pom = true;
        while(pom) {
            pom = false;
            for (int i=0; i < nr-1; i++) {
                if (users[i].getTime()>users[i+1].getTime()) {
                    pom=true;
                    Rekord pom1 = users[i];
                    users[i] = users[i+1];
                    users[i+1] = pom1;
                }
            }
        }
    }
    public int getMinutes(long time) {
        int pom = (int) time/1000;
        return pom/60;
    }
    public int getSeconds(long time) {
        return ((int) time/1000)-(60* getMinutes(time));
    }
    public void setDataToNameFile() {
        try {
            PrintWriter printToFileName = new PrintWriter(fileName);
            for (int i=0; i <= nr; i ++) {
                printToFileName.println(users[i].getName());
            }
            printToFileName.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void setDataToTimeFile() {
        try {
            PrintWriter printToFileTime = new PrintWriter(fileTime);
            for (int i=0; i <= nr; i ++) {
                printToFileTime.println(String.valueOf(users[i].getTime()));
            }
            printToFileTime.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void getInfoFromName() {
        try {
            scanFromFile = new Scanner(fileName);
            int i = 0;
            while(scanFromFile.hasNextLine()) {
                users[i].setName(scanFromFile.nextLine());
                i++;
            }
            nr=i;
            scanFromFile.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void getInfoFromTime() {
        try {
            scanFromFile = new Scanner(fileTime);
            int i = 0;
            while(scanFromFile.hasNextLine()) {
                String line = scanFromFile.nextLine();
                long a = Long.parseLong(line);
                users[i].setTime(a);
                i++;
            }
            scanFromFile.close();
            nr=i;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public boolean createFiles() {
        Context contextOfApp = MainActivity.ma.getApplication();
        dir = new File(contextOfApp.getExternalFilesDir(FOLDER_ZAPISU_RANKINGU), FOLDER_NAME);
        boolean isBothCreated = true;
        if (confirmDir(dir)) {
            fileName = new File(dir, FILE_NAME);
            if (fileName.exists()==false) {
                try {
                    fileName.createNewFile();
                } catch (IOException e) {
                    isBothCreated = false;
                    e.printStackTrace();
                }
            }
            fileTime = new File(dir, FILE_TIME);
            if (fileTime.exists()==false) {
                try {
                    fileTime.createNewFile();
                } catch (IOException e) {
                    isBothCreated = false;
                    e.printStackTrace();
                }
            }
        }
        if (isBothCreated) {
            return true;
        }
        return false;
    }
    public boolean confirmDir(File dir) {
        if (dir.isDirectory()) {
            return true;
        }
        if (dir.exists()) {
            return false;
        }
        return dir.mkdirs();
    }
}
