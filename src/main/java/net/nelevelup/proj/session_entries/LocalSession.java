package net.nelevelup.proj.session_entries;

public class LocalSession {

    private int filmId;

    private int costId;

    private int hallId;

    public LocalSession(){

    }

    public LocalSession(int filmId, int costId, int hallId) {
        this.filmId = filmId;
        this.costId = costId;
        this.hallId = hallId;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getCostId() {
        return costId;
    }

    public void setCostId(int costId) {
        this.costId = costId;
    }

    public int getHallId() {
        return hallId;
    }

    public void setHallId(int hallId) {
        this.hallId = hallId;
    }
}
