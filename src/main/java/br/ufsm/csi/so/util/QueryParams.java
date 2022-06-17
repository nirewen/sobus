package br.ufsm.csi.so.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class QueryParams {
    public final String directory;
    public final Map<String, String> query;

    public QueryParams(String uri) {
        String directory = uri.substring(1);
        String[] strings = uri.split("&");

        Map<String, String> query = new HashMap<>();

        if (uri.contains("?")) {
            for (int i = 0; i < strings.length; i++) {
                String kvPair = strings[i];

                if (i == 0) {
                    String[] firstPair = kvPair.split("\\?");

                    directory = firstPair[0].substring(1);
                    kvPair = firstPair[1];
                }

                String[] pair = kvPair.split("=", 2);

                if (pair.length == 2) {
                    query.put(pair[0], pair[1]);
                } else {
                    query.put(pair[0], null);
                }
            }
        }

        this.directory = directory;
        this.query = query;
    }

    public String toString() {
        if (this.query.size() == 0)
            return "";

        StringBuilder sb = new StringBuilder()
                .append("{ ");

        Iterator<Map.Entry<String, String>> it = this.query.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry<String, String> entry = it.next();

            String key = entry.getKey();
            String value = entry.getValue();

            sb.append(key).append(": ").append(value);

            if (it.hasNext())
                sb.append(",");

            sb.append(" ");
        }

        sb.append("}");

        return sb.toString();
    }
}
