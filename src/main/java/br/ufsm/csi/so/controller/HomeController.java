package br.ufsm.csi.so.controller;

import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Seat;
import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.Header;
import br.ufsm.csi.so.util.Resource;
import br.ufsm.csi.so.util.QueryParams.Query;
import lombok.SneakyThrows;

public class HomeController extends Controller {
    private Query query;

    public HomeController(Query query) {
        super("home.html");

        this.query = query;
    }

    @Override
    @SneakyThrows
    public void onGET(Socket socket) {
        OutputStream out = socket.getOutputStream();

        Resource resource = Resource.from(this.resource);

        out.write(new Header(200).build().getBytes());

        String content = resource.getContent();

        StringBuilder sb = new StringBuilder();

        for (Seat seat : App.seats.values()) {
            StringBuilder element = new StringBuilder();

            element.append("<a");
            element.append(" class=\"seat").append(seat.isTaken() ? " occupied" : "").append("\"");

            if (!seat.isTaken())
                element.append(" href=\"/reservar?id=").append(seat.getId()).append("\"");

            element.append(">");
            element.append(seat.getId());
            element.append("</a>\n");

            sb.append(element);
        }

        content = content.replace("<!-- SEATS -->", sb.toString());

        // TODO: Usar header para enviar a mensagem de sucesso
        if (this.query.has("success")) {
            StringBuilder element = new StringBuilder("<div class=\"message success\">");

            element.append("Assento reservado com sucesso!");
            element.append("</div>");

            content = content.replace("<!-- MESSAGE -->", element.toString());
        } else if (this.query.has("failure")) {
            StringBuilder element = new StringBuilder("<div class=\"message failure\">");

            element.append("Ocorreu um erro ao reservar o assento");
            element.append("</div>");

            content = content.replace("<!-- MESSAGE -->", element.toString());
        }

        out.write(content.getBytes());
    }
}
