package br.ufsm.csi.so.server;

import java.net.Socket;

import br.ufsm.csi.so.util.QueryParams;

public class Request {
    public final Socket socket;
    public final String directory;
    public final QueryParams query;

    // armazena os parâmetros vindos da URL
    public Request(Socket socket, String path) {
        // cria um map de parametros passados na URL
        // nome -> valor
        QueryParams qs = new QueryParams(path);

        this.socket = socket;
        this.query = qs;
        this.directory = qs.directory;
    }
}
