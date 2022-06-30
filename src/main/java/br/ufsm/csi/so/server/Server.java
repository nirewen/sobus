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

        // printar a porta que está rodando o servidor
        Terminal.printPort(port);

        while (true) {
            // aceitar o socket
            Socket socket = this.server.accept();

            // criar uma nova conexão - Connection é Runnable
            Connection connection = new Connection(socket);
            Thread thread = new Thread(connection);

            thread.setName("Connection");

            thread.start();
        }
    }
}
