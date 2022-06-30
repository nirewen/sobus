package br.ufsm.csi.so;

import java.util.HashMap;
import java.util.Map;

import br.ufsm.csi.so.data.Logger;
import br.ufsm.csi.so.data.Seat;
import br.ufsm.csi.so.server.Server;

public class App {
    public static Map<Integer, Seat> seats = new HashMap<>();
    public static Logger logger = new Logger();

    public static void main(String[] args) {
        for (int i = 1; i <= 24; i++) {
            seats.put(i, new Seat(i));
        }

        Server server = new Server();

        server.listen(8080);
    }
}
