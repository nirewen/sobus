package br.ufsm.csi.so.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

// gera elementos HTML
public class Element {
    private String tag;
    private List<String> classes = new ArrayList<>();
    private Map<String, String> props = new HashMap<>();
    private String content = "";

    public Element(String tag) {
        this.tag = tag;
    }

    public Element setClass(String classes) {
        String[] list = classes.split(" ");

        for (String s : list)
            this.classes.add(s);

        return this;
    }

    public Element addClass(String name, boolean bool) {
        if (bool)
            this.classes.add(name);

        return this;
    }

    public Element addProp(String name, String value) {
        if (!name.equals("class"))
            this.props.put(name, value);

        return this;
    }

    public Element content(String content) {
        this.content = content;

        return this;
    }

    public Element content(int content) {
        this.content = Integer.toString(content);

        return this;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();

        sb.append("<").append(this.tag);

        if (this.classes.size() > 0) {
            sb.append(" class=\"");

            sb.append(String.join(" ", this.classes));

            sb.append("\"");
        }

        for (Entry<String, String> prop : this.props.entrySet()) {
            String name = prop.getKey();
            String value = prop.getValue();

            sb.append(" ")
                    .append(name)
                    .append("=")
                    .append("\"")
                    .append(value)
                    .append("\"");
        }

        sb.append(">");

        sb.append(this.content);

        sb.append("</").append(this.tag).append(">");

        return sb.toString();
    }
}
