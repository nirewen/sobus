package br.ufsm.csi.so.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

import br.ufsm.csi.so.App;
import br.ufsm.csi.so.data.Reserva;
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

        Reserva reserva = App.reservas.get(id);

        // id válido & data válida & assento vago
        if (reserva != null && date.length == 2 && !reserva.isTaken()) {
            reserva.setName(name);
            reserva.setDate(date[0]);
            reserva.setHour(date[1]);
            reserva.setTaken(true);
        }

        Header header = new Header(302)
                .addHeader("Location: /home?success=true");

        out.write(header.build().getBytes());
    }
}
