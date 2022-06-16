package br.ufsm.csi.so.util;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Controller {
    public final String resource;

    public Controller(String resource) {
        this.resource = resource;
    }

    public void onGET(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        out.write(RequestUtil.getHeader(405).getBytes());

        out.write("Erro: Rota não implementa método GET".getBytes());
    }

    public void onPOST(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        out.write(RequestUtil.getHeader(405).getBytes());

        out.write("Erro: Rota não implementa método POST".getBytes());
    }
}
