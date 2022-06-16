package br.ufsm.csi.so.util;

public class RequestUtil {
    public static String getHeader(int code) {
        return RequestUtil.getHeader(code, "text/html");
    }

    public static String getHeader(int code, String mimeType) {
        String status = switch (code) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            default -> "Unknown";
        };

        return String.format("HTTP/1.1 %d %s\nContent-Type: %s; charset=UTF-8\n\n", code, status, mimeType);
    }
}
