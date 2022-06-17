package br.ufsm.csi.so.server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import lombok.SneakyThrows;

public class Server {
    public Semaphore mutex;
    public ServerSocket server;

    public Server() {
        this.mutex = new Semaphore(1);
    }

    @SneakyThrows
    public void listen(int port) {
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
