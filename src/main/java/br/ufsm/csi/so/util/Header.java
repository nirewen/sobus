package br.ufsm.csi.so.util;

import java.util.ArrayList;
import java.util.List;

public class Header {
    private int code;
    private String mimeType = "text/html";
    private List<String> headers = new ArrayList<>();

    public Header(int code) {
        this.code = code;
    }

    public Header mime(String mimeType) {
        this.mimeType = mimeType;

        return this;
    }

    public Header addHeader(String header) {
        this.headers.add(header);

        return this;
    }

    public String build() {
        String status = switch (this.code) {
            case 200 -> "OK";
            case 302 -> "Found";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            default -> "Unknown";
        };

        StringBuilder sb = new StringBuilder("HTTP/1.1");

        sb.append(String.format(" %d %s\n", this.code, status));
        sb.append(String.format("Content-Type: %s; charset=UTF-8", this.mimeType));

        for (String header : this.headers)
            sb.append("\n").append(header);

        sb.append("\n\n");

        return sb.toString();
    }
}
