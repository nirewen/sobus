package br.ufsm.csi.so.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.RequestUtil;
import br.ufsm.csi.so.util.Resource;

public class ReservarController extends Controller {
    private String seat;

    public ReservarController(String seat) {
        super("reservar.html");

        this.seat = seat;
    }

    @Override
    public void onGET(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        Resource resource = Resource.from(this.resource);

        out.write(RequestUtil.getHeader(200).getBytes());

        String html = resource.getHTML();

        html = html.replaceAll("<!-- SEAT -->", this.seat);

        out.write(html.getBytes());
    }
}
