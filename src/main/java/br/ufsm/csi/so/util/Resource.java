package br.ufsm.csi.so.util;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import lombok.SneakyThrows;

// pegar arquivos da pasta resource
// salva os bytes e o conteúdo como string
public class Resource {
    public byte[] bytes;
    private String content;

    @SneakyThrows
    public Resource(InputStream bytes) {
        this.bytes = bytes.readAllBytes();
        this.content = new String(this.bytes, StandardCharsets.UTF_8);
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

    public String getContent() {
        return this.content;
    }
}