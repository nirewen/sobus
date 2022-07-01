package br.ufsm.csi.so.controller;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Seat;
import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.server.Request;
import br.ufsm.csi.so.server.Response;
import br.ufsm.csi.so.util.Element;
import br.ufsm.csi.so.util.Resource;
import lombok.SneakyThrows;

public class HomeController extends Controller {
    public HomeController() {
        super("home.html");
    }

    @Override
    @SneakyThrows
    public void onGET(Request req, Response res) {
        Resource resource = Resource.from(this.resource);

        String content = resource.getContent();

        StringBuilder sb = new StringBuilder();

        for (Seat seat : App.seats.values()) {
            Element element = new Element("a")
                    .setClass("seat")
                    .addClass("occupied", seat.isTaken())
                    .addProp("data-name", seat.getName())
                    .addProp("data-date", seat.getDate())
                    .addProp("data-hour", seat.getHour())
                    .content(seat.getId());

            if (!seat.isTaken())
                element.addProp("href", "/reservar?id=" + seat.getId());

            sb.append(element);
        }

        content = content.replace("<!-- SEATS -->", sb.toString());

        // TODO: Usar header para enviar a mensagem de sucesso
        if (req.query.params.has("success")) {
            Element element = new Element("div")
                    .setClass("message success")
                    .content("Assento reservado com sucesso!");

            content = content.replace("<!-- MESSAGE -->", element.toString());
        } else if (req.query.params.has("failure")) {
            Element element = new Element("div")
                    .setClass("message failure")
                    .content("Ocorreu um erro ao reservar o assento");

            content = content.replace("<!-- MESSAGE -->", element.toString());
        }

        res.send(content);
    }
}
