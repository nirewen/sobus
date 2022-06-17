package br.ufsm.csi.so;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import br.ufsm.csi.so.data.Reserva;
import br.ufsm.csi.so.server.Server;

public class App {
    public static Map<Integer, Reserva> reservas = new HashMap<>();

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 20; i++) {
            reservas.put(i, new Reserva(i));
        }

        Server server = new Server();

        server.listen(8080);
    }
}
