package br.ufsm.csi.so.controller;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Seat;
import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.server.Request;
import br.ufsm.csi.so.server.Response;
import br.ufsm.csi.so.util.Element;
import br.ufsm.csi.so.util.Resource;
import br.ufsm.csi.so.util.QueryParams.Params;
import lombok.SneakyThrows;

public class HomeController extends Controller {
    private Params query;

    public HomeController(Params query) {
        super("home.html");

        this.query = query;
    }

    @Override
    @SneakyThrows
    public void onGET(Request req, Response res) {
        Resource resource = Resource.from(this.resource);

        String content = resource.getContent();

        StringBuilder sb = new StringBuilder();

        // gerar um anchor para cada assento
        for (Seat seat : App.seats.values()) {
            Element element = new Element("a")
                    .setClass("seat")
                    .addClass("occupied", seat.isTaken())
                    .addProp("data-name", seat.getName())
                    .addProp("data-date", seat.getDate())
                    .addProp("data-hour", seat.getHour())
                    .content(seat.getId());

            // adicionar href se não está ocupado
            if (!seat.isTaken())
                element.addProp("href", "/reservar?id=" + seat.getId());

            sb.append(element);
        }

        // substituir o conteúdo da div.bus no html
        content = content.replace("<!-- SEATS -->", sb.toString());

        // TODO: Usar header para enviar a mensagem de sucesso
        if (this.query.has("success")) {
            // gerar elemento de mensagem
            Element element = new Element("div")
                    .setClass("message success")
                    .content("Assento reservado com sucesso!");

            // substiuir no html
            content = content.replace("<!-- MESSAGE -->", element.toString());
        } else if (this.query.has("failure")) {
            // gerar elemento de mensagem de erro
            Element element = new Element("div")
                    .setClass("message failure")
                    .content("Ocorreu um erro ao reservar o assento");

            // substiuir no html
            content = content.replace("<!-- MESSAGE -->", element.toString());
        }

        res.send(content);
    }
}
