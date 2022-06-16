package br.ufsm.csi.so.util;

import java.io.InputStream;

public class HTMLUtil {
    public static Resource getResource(String resource) {
        InputStream is = HTMLUtil.class
                .getClassLoader()
                .getResourceAsStream(resource);
        String code = "200";

        if (is == null) {
            is = HTMLUtil.class
                    .getClassLoader()
                    .getResourceAsStream("404.html");
            code = "404";
        }

        return new Resource(code, is);
    }
}
