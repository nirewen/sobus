package br.ufsm.csi.so.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Seat;
import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.Header;
import br.ufsm.csi.so.util.Resource;
import br.ufsm.csi.so.util.QueryParams.Query;

public class HomeController extends Controller {
    private Query query;

    public HomeController(Query query) {
        super("home.html");

        this.query = query;
    }

    @Override
    public void onGET(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        Resource resource = Resource.from(this.resource);

        out.write(new Header(200).build().getBytes());

        String html = resource.getHTML();

        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Integer, Seat> entry : App.seats.entrySet()) {
            Seat r = entry.getValue();

            StringBuilder element = new StringBuilder();

            element.append("<a");
            element.append(" class=\"seat").append(r.isTaken() ? " occupied" : "").append("\"");

            if (!r.isTaken())
                element.append(" href=\"/reservar?id=").append(r.getId()).append("\"");

            element.append(">");
            element.append(r.getId());
            element.append("</a>\n");

            sb.append(element);
        }

        html = html.replace("<!-- SEATS -->", sb.toString());

        // TODO: Usar header para enviar a mensagem de sucesso
        if (this.query.has("success")) {
            StringBuilder element = new StringBuilder("<div class=\"message\">");

            element.append("Assento reservado com sucesso!");
            element.append("</div>");

            html = html.replace("<!-- MESSAGE -->", element.toString());
        }

        out.write(html.getBytes());
    }
}
