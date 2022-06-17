package br.ufsm.csi.so.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

import br.ufsm.csi.so.controller.CSSController;
import br.ufsm.csi.so.controller.HomeController;
import br.ufsm.csi.so.controller.ImageController;
import br.ufsm.csi.so.controller.ReservarController;
import br.ufsm.csi.so.controller._404Controller;
import br.ufsm.csi.so.util.QueryParams;

public class Request implements Runnable {
    private Server server;
    private Socket socket;

    public Request(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    public void execute() throws IOException {
        InputStream in = this.socket.getInputStream();
        OutputStream out = this.socket.getOutputStream();

        Scanner scanner = new Scanner(in);

        // erro chato: fica dando NoSuchElement, isso corrige
        if (!scanner.hasNext()) {
            scanner.close();

            return;
        }

        String method = scanner.next();
        String path = scanner.next();

        QueryParams qs = new QueryParams(path);
        String directory = qs.directory;
        Map<String, String> query = qs.query;

        System.out.println(method + " " + directory + " " + qs);

        Controller controller = new Controller(directory);

        // controlador de CSS
        if (directory.startsWith("/css/"))
            controller = new CSSController(directory);
        // controlador de imagem
        if (directory.startsWith("/img/"))
            controller = new ImageController(directory);

        if (directory.equals("/home") || directory.equals("/"))
            controller = new HomeController(this.server);

        if (directory.equals("/reservar"))
            controller = new ReservarController();

        // caso não exista a página
        if (!controller.isValid())
            controller = new _404Controller();

        if (method.equals("GET"))
            controller.onGET(socket);

        if (method.equals("POST"))
            controller.onPOST(socket);

        in.close();
        out.close();

        scanner.close();
        this.socket.close();
    }

    @Override
    public void run() {
        try {
            this.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
