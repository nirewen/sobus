package br.ufsm.csi.so.server;

public class Controller {
    public final String resource;

    public Controller(String resource) {
        if (resource.startsWith("/"))
            resource = resource.substring(1);

        this.resource = resource;
    }

    public boolean isValid() {
        return this.getClass().getClassLoader().getResource(this.resource) != null;
    }

    public void onGET(Request req, Response res) {
        res.status(405).send("Erro: Rota não implementa método GET");
    }

    public void onPOST(Request req, Response res) {
        res.status(405).send("Erro: Rota não implementa método POST");
    }
}
