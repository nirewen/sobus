package br.ufsm.csi.so.util;

import java.util.ArrayList;
import java.util.List;

public class Chalk {
    private final String ESC = "\u001B";
    private String finalText = "";

    private String fg;
    private String bg;
    private String ef;

    public Chalk(String initialText) {
        this.finalText = initialText;
    }

    public Chalk black() {
        this.fg = "30";
        return this;
    }

    public Chalk lightGray() {
        this.fg = "37";
        return this;
    }

    public Chalk gray() {
        this.fg = "90";
        return this;
    }

    public Chalk red() {
        this.fg = "91";
        return this;
    }

    public Chalk green() {
        this.fg = "92";
        return this;
    }

    public Chalk yellow() {
        this.fg = "93";
        return this;
    }

    public Chalk blue() {
        this.fg = "94";
        return this;
    }

    public Chalk magenta() {
        this.fg = "95";
        return this;
    }

    public Chalk cyan() {
        this.fg = "96";
        return this;
    }

    public Chalk white() {
        this.fg = "97";
        return this;
    }

    public Chalk darkRed() {
        this.fg = "31";
        return this;
    }

    public Chalk darkGreen() {
        this.fg = "32";
        return this;
    }

    public Chalk orange() {
        this.fg = "33";
        return this;
    }

    public Chalk darkBlue() {
        this.fg = "34";
        return this;
    }

    public Chalk darkMagenta() {
        this.fg = "35";
        return this;
    }

    public Chalk darkCyan() {
        this.fg = "36";
        return this;
    }

    public Chalk bgBlack() {
        this.fg = "40";
        return this;
    }

    public Chalk bgLightGray() {
        this.fg = "47";
        return this;
    }

    public Chalk bgGray() {
        this.fg = "100";
        return this;
    }

    public Chalk bgRed() {
        this.fg = "101";
        return this;
    }

    public Chalk bgGreen() {
        this.fg = "102";
        return this;
    }

    public Chalk bgYellow() {
        this.fg = "103";
        return this;
    }

    public Chalk bgBlue() {
        this.fg = "104";
        return this;
    }

    public Chalk bgMagenta() {
        this.fg = "105";
        return this;
    }

    public Chalk bgCyan() {
        this.fg = "106";
        return this;
    }

    public Chalk bgWhite() {
        this.fg = "107";
        return this;
    }

    public Chalk bgDarkRed() {
        this.fg = "41";
        return this;
    }

    public Chalk bgDarkGreen() {
        this.fg = "42";
        return this;
    }

    public Chalk bgOrange() {
        this.fg = "43";
        return this;
    }

    public Chalk bgDarkBlue() {
        this.fg = "44";
        return this;
    }

    public Chalk bgDarkMagenta() {
        this.fg = "45";
        return this;
    }

    public Chalk bgDarkCyan() {
        this.fg = "46";
        return this;
    }

    public Chalk bold() {
        this.ef = "1";
        return this;
    }

    public Chalk dimmed() {
        this.ef = "2";
        return this;
    }

    public Chalk italic() {
        this.ef = "3";
        return this;
    }

    public Chalk underline() {
        this.ef = "4";
        return this;
    }

    public Chalk blinking() {
        this.ef = "5";
        return this;
    }

    public Chalk reverse() {
        this.ef = "7";
        return this;
    }

    public Chalk invisible() {
        this.ef = "8";
        return this;
    }

    public Chalk strikethrough() {
        this.ef = "9";
        return this;
    }

    private String getColorANSI(String color) {
        return ESC + "[" + color + "m";
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        List<String> modifiers = new ArrayList<>();

        if (ef != null)
            modifiers.add(ef);
        if (fg != null)
            modifiers.add(fg);
        if (bg != null)
            modifiers.add(bg);

        if (!modifiers.isEmpty())
            sb.append(this.getColorANSI(String.join(";", modifiers)));

        sb.append(finalText);

        sb.append(this.getColorANSI("0"));

        return sb.toString();
    }

    public static Chalk of(String text) {
        return new Chalk(text);
    }
}
