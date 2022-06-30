package br.ufsm.csi.so.controller;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.server.Request;
import br.ufsm.csi.so.server.Response;
import br.ufsm.csi.so.util.Resource;
import lombok.SneakyThrows;

// para requests de páginas inexistentes
public class _404Controller extends Controller {
    public _404Controller() {
        super("_404.html");
    }

    @Override
    public void onGET(Request req, Response res) {
        Resource resource = Resource.from(this.resource);

        res.status(404).send(resource.getContent());
    }

    @Override
    @SneakyThrows
    public void onPOST(Request req, Response res) {
        Resource resource = Resource.from(this.resource);

        res.status(404).send(resource.getContent());
    }
}
