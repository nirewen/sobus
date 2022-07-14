package br.ufsm.csi.so.util;

import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import lombok.SneakyThrows;

public class QueryParams {
    public final String directory;
    public final Params params;

    public class Params extends HashMap<String, String> {
        public Params() {
            super();
        }

        public boolean has(String key) {
            return this.containsKey(key);
        }
    }

    @SneakyThrows
    public QueryParams(String uri) {
        String directory = uri;
        String[] strings = uri.split("&");

        Params query = new Params();

        if (uri.contains("?")) {
            for (int i = 0; i < strings.length; i++) {
                String kvPair = strings[i];

                if (i == 0) {
                    String[] firstPair = kvPair.split("\\?");

                    directory = firstPair[0];
                    kvPair = firstPair[1];
                }

                String[] pair = kvPair.split("=", 2);

                if (pair.length == 2 && !pair[1].isEmpty()) {
                    query.put(pair[0], URLDecoder.decode(pair[1], "UTF-8"));
                } else {
                    query.put(pair[0], null);
                }
            }
        }

        this.directory = directory;
        this.params = query;
    }

    public String toString() {
        if (this.params.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder()
                .append(Chalk.of("{ ").yellow());

        Iterator<Map.Entry<String, String>> it = this.params.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();

            String key = entry.getKey();
            String value = entry.getValue();

            sb.append(Chalk.of(key).yellow()).append(Chalk.of(": ").orange()).append(Chalk.of(value).darkCyan());

            if (it.hasNext())
                sb.append(",");

            sb.append(" ");
        }

        sb.append(Chalk.of("}").yellow());

        return sb.toString();
    }
}
