package com.example.partyfavors2.utilidades;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class copiarArchivo {
    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }
}
