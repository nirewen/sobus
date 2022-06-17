package br.ufsm.csi.so.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Reserva;
import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.server.Server;
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

        String html = resource.getHTML();

        StringBuilder sb = new StringBuilder();

        for (Reserva r : App.reservas) {
            StringBuilder element = new StringBuilder();

            element.append("<a ");
            element.append("class=\"seat").append(r.isTaken() ? " occupied" : "").append("\" ");
            element.append("href=\"/reservar?id=").append(r.getSeat()).append("\">");
            element.append(r.getSeat());
            element.append("</a>\n");

            sb.append(element);
        }

        html = html.replace("<!-- SEATS -->", sb.toString());

        out.write(html.getBytes());
    }
}
