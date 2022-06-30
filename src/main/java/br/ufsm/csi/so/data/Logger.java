package br.ufsm.csi.so.data;

import java.io.File;
import java.io.FileWriter;
import java.net.Socket;

import br.ufsm.csi.so.util.Terminal;
import lombok.SneakyThrows;

public class Logger {
    private String logString = "";

    private File file = new File("reservas.log");

    private Socket socket;
    private Seat seat;

    @SneakyThrows
    public Logger() {
        // Criar o arquivo de log - se já criado, retorna false
        if (file.createNewFile()) {
            Terminal.printLogFile(file.getName());
        }
    }

    // cria um novo log
    public void log(Socket socket, Seat seat) {
        this.socket = socket;
        this.seat = seat;

        Thread produz = new Thread(new ProduzLog());
        Thread armazena = new Thread(new ArmazenaLog());

        produz.setName("Produzir log");
        armazena.setName("Armazenar log");

        produz.start();
        armazena.start();
    }

    private class ProduzLog implements Runnable {
        @Override
        public void run() {
            synchronized (logString) {
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
            }
        }
    }

    private class ArmazenaLog implements Runnable {
        @Override
        @SneakyThrows
        public void run() {
            synchronized (logString) {
                // criar um novo escritor de arquivo
                FileWriter writer = new FileWriter(file.getName(), true);

                // escreve no arquivo de log
                writer.write(logString);
                // fechar a abertura do arquivo
                writer.close();
            }
        }
    }
}
