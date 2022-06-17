package br.ufsm.csi.so.server;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.util.Header;

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

    public void onGET(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        out.write(new Header(405).build().getBytes());

        out.write("Erro: Rota não implementa método GET".getBytes());
    }

    public void onPOST(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        out.write(new Header(405).build().getBytes());

        out.write("Erro: Rota não implementa método POST".getBytes());
    }
}
