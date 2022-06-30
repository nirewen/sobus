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

    // troca o tipo de arquivo respondido
    public Response mime(String mime) {
        // por padrão é text/html
        this.header.mime(mime);

        return this;
    }

    // troca o código da resposta
    public Response status(int code) {
        this.header.status(code);

        return this;
    }

    public Response redirect(String location) {
        // troca o código pra 302 (Found)
        this.header.status(302);
        // indicar o Location novo
        this.header.addHeader("Location: " + location);
        // isso informa o browser que o recurso foi encontrado
        // e o local do novo recurso

        return this;
    }

    @SneakyThrows
    // envia os bytes do conteúdo para o OutputStream
    public Response send(String content) {
        this.send(content.getBytes());

        return this;
    }

    @SneakyThrows
    public Response send(byte[] content) {
        this.out.write(this.header.build().getBytes());

        this.out.write(content);

        return this;
    }

    @SneakyThrows
    public Response send() {
        this.out.write(this.header.build().getBytes());

        return this;
    }
}
