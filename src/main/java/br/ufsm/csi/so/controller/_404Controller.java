package br.ufsm.csi.so.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.RequestUtil;
import br.ufsm.csi.so.util.Resource;

public class _404Controller extends Controller {
    public _404Controller() {
        super("_404.html");
    }

    @Override
    public void onGET(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        Resource resource = Resource.from(this.resource);

        out.write(RequestUtil.getHeader(404).getBytes());

        out.write(resource.getHTML().getBytes());
    }

    @Override
    public void onPOST(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        Resource resource = Resource.from(this.resource);

        out.write(RequestUtil.getHeader(404).getBytes());

        out.write(resource.getHTML().getBytes());
    }
}
