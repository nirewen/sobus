package br.ufsm.csi.so.controller;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.server.Request;
import br.ufsm.csi.so.server.Response;
import br.ufsm.csi.so.util.Resource;
import lombok.SneakyThrows;

// para casos de request de css
public class CSSController extends Controller {
    public CSSController(String resource) {
        super(resource);
    }

    @SneakyThrows
    public void onGET(Request req, Response res) {
        Resource resource = Resource.from(this.resource);

        res.mime("text/css").send(resource.getContent());
    }
}
