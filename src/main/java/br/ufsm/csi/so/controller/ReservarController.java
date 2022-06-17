package br.ufsm.csi.so.controller;

import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.Header;
import br.ufsm.csi.so.util.Resource;
import lombok.SneakyThrows;

public class ReservarController extends Controller {
    private String seat;

    public ReservarController(String seat) {
        super("reservar.html");

        this.seat = seat;
    }

    @Override
    @SneakyThrows
    public void onGET(Socket socket) {
        OutputStream out = socket.getOutputStream();

        Resource resource = Resource.from(this.resource);

        out.write(new Header(200).build().getBytes());

        String html = resource.getHTML();

        html = html.replaceAll("<!-- SEAT -->", this.seat);

        out.write(html.getBytes());
    }
}
