package br.ufsm.csi.so.server;

// controlador abstrato
public class Controller {
    public final String resource;

    public Controller(String resource) {
        if (resource.startsWith("/"))
            resource = resource.substring(1);

        this.resource = resource;
    }

    // verificar se o recurso é válido
    public boolean isValid() {
        return this.getClass().getClassLoader().getResource(this.resource) != null;
    }

    // gerenciar request do método GET
    public void onGET(Request req, Response res) {
        res.status(405).send("Erro: Rota não implementa método GET");
    }

    // gerenciar request do método POST
    public void onPOST(Request req, Response res) {
        res.status(405).send("Erro: Rota não implementa método POST");
    }
}
