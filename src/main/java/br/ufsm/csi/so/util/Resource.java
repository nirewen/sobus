package br.ufsm.csi.so.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import lombok.SneakyThrows;

public class Resource {
    public byte[] bytes;
    private String html;

    @SneakyThrows
    public Resource(InputStream bytes) {
        this.bytes = bytes.readAllBytes();
        this.html = new String(this.bytes, StandardCharsets.UTF_8);
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