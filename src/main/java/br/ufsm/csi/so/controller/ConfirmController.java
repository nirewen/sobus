package br.ufsm.csi.so.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Seat;
import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.Header;
import br.ufsm.csi.so.util.QueryParams.Query;

public class ConfirmController extends Controller {
    private Query query;
    private Semaphore mutex;

    public ConfirmController(Semaphore mutex, Query query) throws InterruptedException {
        super("");

        this.query = query;
        this.mutex = mutex;

        this.mutex.acquire();
    }

    @Override
    public void onGET(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        int id = Integer.parseInt(this.query.get("id"));
        String name = this.query.get("name");
        String[] date = this.query.get("date").split("T");

        Seat seat = App.seats.get(id);

        Header header = new Header(302);

        // id válido & data válida & assento vago
        if (seat != null && date.length == 2 && !seat.isTaken()) {
            seat.setName(name);
            seat.setDate(date[0]);
            seat.setHour(date[1]);
            seat.setTaken(true);

            header.addHeader("Location: /home?success=true");
        } else {
            header.addHeader("Location: /home?failure=true");
        }

        out.write(header.build().getBytes());

        this.mutex.release();
    }
}
