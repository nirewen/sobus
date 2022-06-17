package br.ufsm.csi.so.data;

public class Seat {
    private int id;
    private String name;
    private String date;
    private String hour;
    private boolean taken;

    public Seat(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public String getName() {
        return this.name;
    }

    public String getDate() {
        return this.date;
    }

    public String getHour() {
        return this.hour;
    }

    public boolean isTaken() {
        return this.taken;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
