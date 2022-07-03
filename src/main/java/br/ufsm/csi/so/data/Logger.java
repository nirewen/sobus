package br.ufsm.csi.so.data;

import java.io.File;
import java.io.FileWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import br.ufsm.csi.so.util.Terminal;
import lombok.SneakyThrows;

public class Logger {
    private File file = new File("reservas.log");
    private List<String> queue = new ArrayList<>();

    @SneakyThrows
    public Logger() {
        if (file.createNewFile()) {
            Terminal.printLogFile(file.getName());
        }

        Thread consome = new Thread(new ConsomeLog());
        consome.setName("Consumir log");

        consome.start();
    }

    @SneakyThrows
    public void log(Socket socket, Seat seat) {
        synchronized (queue) {
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

            queue.add(sb.toString());
            queue.notify();

            Terminal.printLog(seat);
        }
    }

    private class ConsomeLog implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            while (true) {
                String entry;

                synchronized (queue) {
                    if (queue.isEmpty()) {
                        queue.wait();
                    }

                    entry = queue.remove(0);

                    FileWriter writer = new FileWriter(file.getName(), true);

                    writer.write(entry);
                    writer.close();
                }
            }
        }
    }
}
