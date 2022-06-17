package br.ufsm.csi.so.controller;

import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.Header;
import br.ufsm.csi.so.util.Resource;
import lombok.SneakyThrows;

public class _404Controller extends Controller {
    public _404Controller() {
        super("_404.html");
    }

    @Override
    @SneakyThrows
    public void onGET(Socket socket) {
        OutputStream out = socket.getOutputStream();

        Resource resource = Resource.from(this.resource);

        out.write(new Header(404).build().getBytes());

        out.write(resource.getContent().getBytes());
    }

    @Override
    @SneakyThrows
    public void onPOST(Socket socket) {
        OutputStream out = socket.getOutputStream();

        Resource resource = Resource.from(this.resource);

        out.write(new Header(404).build().getBytes());

        out.write(resource.getContent().getBytes());
    }
}
