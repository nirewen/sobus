package br.ufsm.csi.so.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Seat;
import br.ufsm.csi.so.server.Controller;
import br.ufsm.csi.so.util.Header;
import br.ufsm.csi.so.util.QueryParams.Query;

public class ConfirmController extends Controller {
    private Query query;

    public ConfirmController(Query query) {
        super("");

        this.query = query;
    }

    @Override
    public void onGET(Socket socket) throws IOException {
        OutputStream out = socket.getOutputStream();

        int id = Integer.parseInt(this.query.get("id"));
        String name = this.query.get("name");
        String[] date = this.query.get("date").split("T");

        Seat seat = App.seats.get(id);

        // id válido & data válida & assento vago
        if (seat != null && date.length == 2 && !seat.isTaken()) {
            seat.setName(name);
            seat.setDate(date[0]);
            seat.setHour(date[1]);
            seat.setTaken(true);
        }

        Header header = new Header(302)
                .addHeader("Location: /home?success=true");

        out.write(header.build().getBytes());
    }
}
