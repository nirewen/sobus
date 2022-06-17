package br.ufsm.csi.so;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import br.ufsm.csi.so.data.Reserva;
import br.ufsm.csi.so.server.Server;

public class App {
    public static List<Reserva> reservas = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        for (int i = 1; i <= 20; i++) {
            reservas.add(new Reserva(i));
        }

        Server server = new Server();

        server.listen(8080);
    }
}
