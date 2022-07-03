package br.ufsm.csi.so.util;

import br.ufsm.csi.so.data.Seat;

public class Terminal {
    public static void printRequest(String method, String directory, QueryParams query) {
        StringBuilder sb = new StringBuilder();

        sb.append(Chalk.of(" " + method + " ").white().bgDarkBlue()).append(" ");
        sb.append(Chalk.of(directory).green()).append(" ");
        sb.append(query);

        System.out.println(sb.toString());
    }

    public static void printLogFile(String file) {
        StringBuilder sb = new StringBuilder();

        sb.append(Chalk.of(" LOG ").white().bgDarkMagenta()).append(" ");
        sb.append(Chalk.of("Arquivo de log criado").lightGray()).append(" ");
        sb.append(Chalk.of(file).blue().underline());

        System.out.println(sb.toString());
    }

    public static void printLog(Seat seat) {
        StringBuilder sb = new StringBuilder();

        sb.append(Chalk.of(" LOG ").white().bgDarkMagenta()).append(" ");
        sb.append(Chalk.of(Integer.toString(seat.getId())).darkCyan().bold()).append(" ");
        sb.append(Chalk.of(seat.getName()).blue());

        System.out.println(sb.toString());
    }

    public static void printPort(int port) {
        StringBuilder sb = new StringBuilder();

        sb.append(Chalk.of(" APP ").white().bgDarkRed()).append(" ");
        sb.append(Chalk.of("Servidor rodando em").lightGray()).append(" ");
        sb.append(Chalk.of("http://localhost:" + port).darkCyan().underline());

        System.out.println(sb.toString());
    }
}
