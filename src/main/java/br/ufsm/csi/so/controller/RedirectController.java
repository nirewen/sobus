package br.ufsm.csi.so.controller;

import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.server.Request;
import br.ufsm.csi.so.server.Response;
import lombok.SneakyThrows;

public class RedirectController extends Controller {
    private String redirectTo;

    public RedirectController(String redirectTo) {
        super("");

        this.redirectTo = redirectTo;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    @SneakyThrows
    public void onGET(Request req, Response res) {
        res.redirect(this.redirectTo);
    }
}
