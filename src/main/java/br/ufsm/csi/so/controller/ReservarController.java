package br.ufsm.csi.so.controller;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Seat;
import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.server.Request;
import br.ufsm.csi.so.server.Response;
import br.ufsm.csi.so.util.Resource;
import lombok.SneakyThrows;

public class ReservarController extends Controller {
    public ReservarController() {
        super("reservar.html");
    }

    @Override
    @SneakyThrows
    public void onGET(Request req, Response res) {
        String id = req.query.params.get("id");
        Resource resource = Resource.from(this.resource);

        Seat seat = App.seats.get(Integer.parseInt(id));

        if (seat.isTaken()) {
            res.redirect("/home?failure=true").send();

            return;
        }

        String content = resource.getContent();

        content = content.replaceAll("<!-- SEAT -->", id);

        res.send(content);
    }
}
