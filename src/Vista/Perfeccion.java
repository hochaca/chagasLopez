/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Dominio.Juego;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Santilo
 */
public class Perfeccion {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {

        Juego juego = new Juego();
        Inicio inicio = new Inicio(juego);
        inicio.setVisible(true);

        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("juegoArchivo"))) {
            out.writeObject(juego);
        }

    }

}
