package br.ufsm.csi.so.util;

import java.util.ArrayList;
import java.util.List;

public class Header {
    private int code = 200;
    private String status;
    private String mimeType = "text/html";
    private List<String> headers = new ArrayList<>();

    public Header() {
        this.setStatus(this.code);
    }

    // define o nome do status
    private void setStatus(int code) {
        this.status = switch (this.code) {
            case 200 -> "OK";
            case 302 -> "Found";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            default -> "Unknown";
        };
    }

    public Header mime(String mimeType) {
        this.mimeType = mimeType;

        return this;
    }

    public Header status(int code) {
        this.code = code;

        this.setStatus(code);

        return this;
    }

    // adicionar headers extra
    public Header addHeader(String header) {
        this.headers.add(header);

        return this;
    }

    public String build() {
        // inicializa o StringBuilder
        StringBuilder sb = new StringBuilder("HTTP/1.1");

        // adicionar o código e o status no header
        sb.append(String.format(" %d %s\n", this.code, this.status));
        // adicionar o mime type
        sb.append(String.format("Content-Type: %s; charset=UTF-8", this.mimeType));

        for (String header : this.headers)
            sb.append("\n").append(header);

        sb.append("\n\n");

        return sb.toString();
    }
}
