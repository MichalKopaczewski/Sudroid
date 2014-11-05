package sudoku.model;

public class Rekord {
    private long time =0;
    private String name;
    public void setName(String name) {
        this.name = name;
    }
    public void setTime(long time) {
        this.time = time;
    }
    public String getName () {
        return name;
    }
    public long getTime() {
        return time;
    }
}
