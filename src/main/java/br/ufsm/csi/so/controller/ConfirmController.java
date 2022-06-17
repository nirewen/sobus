package br.ufsm.csi.so.controller;

import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Seat;
import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.Header;
import br.ufsm.csi.so.util.QueryParams.Query;
import lombok.SneakyThrows;

public class ConfirmController extends Controller {
    private Query query;
    private Semaphore mutex;

    public ConfirmController(Semaphore mutex, Query query) {
        super("");

        this.query = query;
        this.mutex = mutex;

    }

    @Override
    @SneakyThrows
    public void onGET(Socket socket) {
        OutputStream out = socket.getOutputStream();

        int id = Integer.parseInt(this.query.get("id"));
        String name = this.query.get("name");
        String[] date = this.query.get("date").split("T");

        Seat seat = App.seats.get(id);

        Header header = new Header(302);

        // id válido & data válida & assento vago
        if (seat != null && date.length == 2 && !seat.isTaken()) {
            this.mutex.acquire();

            seat.setName(name);
            seat.setDate(date[0]);
            seat.setHour(date[1]);
            seat.setTaken(true);

            header.addHeader("Location: /home?success=true");

            this.mutex.release();
        } else {
            header.addHeader("Location: /home?failure=true");
        }

        out.write(header.build().getBytes());

    }
}
