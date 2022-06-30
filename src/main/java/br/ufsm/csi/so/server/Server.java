package br.ufsm.csi.so.server;

import java.net.ServerSocket;
import java.net.Socket;

import br.ufsm.csi.so.util.Terminal;
import lombok.SneakyThrows;

public class Server {
    public ServerSocket server;

    @SneakyThrows
    public void listen(int port) {
        this.server = new ServerSocket(port);

        Terminal.printPort(port);

        while (true) {
            Socket socket = this.server.accept();

            Connection connection = new Connection(socket);
            Thread thread = new Thread(connection);

            thread.setName("Connection");

            thread.start();
        }
    }
}
