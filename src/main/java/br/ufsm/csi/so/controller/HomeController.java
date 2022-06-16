package br.ufsm.csi.so.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.server.Server;
import br.ufsm.csi.so.util.Controller;
import br.ufsm.csi.so.util.RequestUtil;
import br.ufsm.csi.so.util.Resource;

public class HomeController extends Controller {
    Server server;

    public HomeController(Server server) {
        super("home.html");

        this.server = server;
    }

    @Override
    public void onGET(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        Resource resource = Resource.from(this.resource);

        out.write(RequestUtil.getHeader(200).getBytes());

        out.write(resource.getHTML().getBytes());
    }
}
