package br.ufsm.csi.so.controller;

import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.Header;
import lombok.SneakyThrows;

public class RedirectController extends Controller {
    public RedirectController() {
        super("");
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    @SneakyThrows
    public void onGET(Socket socket) {
        OutputStream out = socket.getOutputStream();

        Header header = new Header(302)
                .addHeader("Location: /home");

        out.write(header.build().getBytes());
    }
}
