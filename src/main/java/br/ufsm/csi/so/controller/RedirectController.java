package br.ufsm.csi.so.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.Header;

public class RedirectController extends Controller {
    public RedirectController() {
        super("");
    }

    @Override
    public void onGET(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        Header header = new Header(302)
                .addHeader("Location: /home");

        out.write(header.build().getBytes());
    }
}