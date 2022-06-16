package br.ufsm.csi.so.util;

public class RequestUtil {
    public static String getHeader(int code) {
        String status = switch (code) {
            case 200 -> "OK";
            case 404 -> "Not Found";
            case 405 -> "Method Not Allowed";
            default -> "Unknown";
        };

        return String.format("HTTP/1.1 %s %s\nContent-Type: text/html; charset=UTF-8\n\n", code, status);
    }
}
