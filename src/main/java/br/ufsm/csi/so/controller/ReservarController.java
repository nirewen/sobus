package br.ufsm.csi.so.controller;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.server.Request;
import br.ufsm.csi.so.server.Response;
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
    public void onGET(Request req, Response res) {
        Resource resource = Resource.from(this.resource);

        String content = resource.getContent();

        content = content.replaceAll("<!-- SEAT -->", this.seat);

        res.send(content);
    }
}
