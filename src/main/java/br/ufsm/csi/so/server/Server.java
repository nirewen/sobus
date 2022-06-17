package br.ufsm.csi.so.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

public class Server {
    public Semaphore mutex;
    public ServerSocket server;

    public Server() {
        this.mutex = new Semaphore(1);
    }

    public void listen(int port) throws IOException {
        this.server = new ServerSocket(port);

        System.out.println("Ouvindo na porta: " + port);

        while (true) {
            Socket socket = this.server.accept();
            Request request = new Request(this, socket);
            Thread thread = new Thread(request);

            thread.setName("Request");

            thread.start();
        }
    }
}
