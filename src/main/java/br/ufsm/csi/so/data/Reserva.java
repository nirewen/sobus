package br.ufsm.csi.so.data;

public class Reserva {
    private int seat;
    private String name;
    private String date;
    private String hour;
    private boolean taken;

    public Reserva(int seat) {
        this.seat = seat;
    }

    public int getSeat() {
        return this.seat;
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
