package br.ufsm.csi.so.data;

import java.io.File;
import java.io.FileWriter;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import lombok.SneakyThrows;

public class Logger {
    private String logString = "";

    private Semaphore vazio = new Semaphore(1000);
    private Semaphore cheio = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);

    private File file = new File("reservas.log");

    private Socket socket;
    private Seat seat;

    @SneakyThrows
    public Logger() {
        if (file.createNewFile()) {
            System.out.println("Arquivo de log criado: " + file.getName());
        }
    }

    public void log(Socket socket, Seat seat) {
        this.socket = socket;
        this.seat = seat;

        Thread produz = new Thread(new ProduzLog());
        Thread armazena = new Thread(new ArmazenaLog());

        produz.start();
        armazena.start();
    }

    private class ProduzLog implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            mutex.acquire();
            String ip = socket.getInetAddress().toString();
            StringBuilder sb = new StringBuilder();

            // adicionar ip
            sb.append("[IP: ")
                    .append(ip)
                    .append("] ");

            // adicionar nome
            sb.append("[NOME: ")
                    .append(seat.getName())
                    .append("] ");

            // adicionar assento reservado
            sb.append("[ASSENTO: ")
                    .append(seat.getId())
                    .append("] ");

            // adicionar data
            sb.append("[DATA: ")
                    .append(seat.getDate())
                    .append(" ")
                    .append(seat.getHour())
                    .append("]");

            sb.append("\n");

            logString = sb.toString();

            vazio.acquire(logString.length());
            cheio.release();
            mutex.release();
        }
    }

    private class ArmazenaLog implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            mutex.acquire();
            cheio.acquire();

            vazio.release(logString.length());

            FileWriter writer = new FileWriter(file.getName(), true);

            writer.write(logString);
            writer.close();

            mutex.release();
        }
    }
}
