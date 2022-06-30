package br.ufsm.csi.so.controller;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.server.Request;
import br.ufsm.csi.so.server.Response;
import br.ufsm.csi.so.util.Resource;
import lombok.SneakyThrows;

public class ImageController extends Controller {
    public ImageController(String resource) {
        super(resource);
    }

    @Override
    @SneakyThrows
    public void onGET(Request req, Response res) {
        Resource resource = Resource.from(this.resource);

        res.mime("image/png").send(resource.bytes);
    }
}
