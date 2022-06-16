package br.ufsm.csi.so.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class Resource {
    private String html;

    public Resource(InputStream html) {
        try {
            this.html = new String(html.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static Resource from(String resource) {
        InputStream is = Resource.class
                .getClassLoader()
                .getResourceAsStream(resource);

        if (is == null) {
            return null;
        }

        return new Resource(is);
    }

    public String getHTML() {
        return this.html;
    }
}