package br.ufsm.csi.so.server;

import java.io.OutputStream;

import br.ufsm.csi.so.util.Header;
import lombok.SneakyThrows;

public class Response {
    private Header header = new Header();
    private OutputStream out;

    @SneakyThrows
    public Response(OutputStream out) {
        this.out = out;
    }

    public Response mime(String mime) {
        this.header.mime(mime);

        return this;
    }

    public Response status(int code) {
        this.header.status(code);

        return this;
    }

    public void redirect(String location) {
        this.header.status(302);
        this.header.addHeader("Location: " + location);

        this.send();
    }

    @SneakyThrows
    public void send(String content) {
        this.send(content.getBytes());
    }

    @SneakyThrows
    public void send(byte[] content) {
        this.out.write(this.header.build().getBytes());

        this.out.write(content);
    }

    @SneakyThrows
    public void send() {
        this.out.write(this.header.build().getBytes());
    }
}
