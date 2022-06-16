package br.ufsm.csi.so.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

import br.ufsm.csi.so.util.HTMLUtil;
import br.ufsm.csi.so.util.Resource;

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
        String file = "404";

        if (path.equals("/home"))
            file = "home.html";
        if (path.equals("/reservar"))
            file = "reservar.html";
        if (path.equals("/confirmar") && method.equals("POST")) {
            file = "confirmar.html";
        }

        Resource resource = HTMLUtil.getResource(file);
        String code = resource.code;
        String status = resource.status;
        String html = resource.html;

        out.write(String.format("HTTP/1.1 %s %s\nContent-Type: text/html;charset=UTF-8\n\n", code, status).getBytes());
        out.write(html.getBytes());

        out.flush();

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
