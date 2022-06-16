package br.ufsm.csi.so.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Resource {
    public String code;
    public String status;
    public String html;

    public Resource(String code, InputStream html) {
        try {
            this.code = code;
            this.html = new String(html.readAllBytes(), StandardCharsets.UTF_8);

            this.status = "OK";

            if (this.code.equals("404"))
                this.status = "Not Found";
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}