/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistencia;

import java.io.*;

/**
 *
 * @author Santilo
 */
public class ClaseGrabacion {

    private BufferedWriter buffer;

    public ClaseGrabacion(String archivo) {
        try {
            buffer = new BufferedWriter(new FileWriter(archivo));
        } catch (Exception e) {

        }
    }

    public void grabarLinea(String linea) {
        try {
            buffer.write(linea);
            buffer.newLine();
        } catch (Exception e) {

        }
    }

    public void cerrar() {
        try {
            buffer.flush();
            buffer.close();
        } catch (Exception e) {

        }
    }
}
