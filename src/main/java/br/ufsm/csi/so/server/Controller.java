package br.ufsm.csi.so.server;

import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.util.Header;
import lombok.SneakyThrows;

public class Controller {
    public final String resource;

    public Controller(String resource) {
        if (resource.startsWith("/"))
            resource = resource.substring(1);

        this.resource = resource;
    }

    public boolean isValid() {
        return this.getClass().getClassLoader().getResource(this.resource) != null;
    }

    @SneakyThrows
    public void onGET(Socket socket) {
        OutputStream out = socket.getOutputStream();

        out.write(new Header(405).build().getBytes());

        out.write("Erro: Rota não implementa método GET".getBytes());
    }

    @SneakyThrows
    public void onPOST(Socket socket) {
        OutputStream out = socket.getOutputStream();

        out.write(new Header(405).build().getBytes());

        out.write("Erro: Rota não implementa método POST".getBytes());
    }
}
