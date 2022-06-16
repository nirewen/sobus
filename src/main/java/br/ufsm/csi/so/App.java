package br.ufsm.csi.so;

import java.io.IOException;

import br.ufsm.csi.so.server.Server;

public class App {
    public static void main(String[] args) throws IOException {
        Server server = new Server();

        server.listen(8080);
    }
}
