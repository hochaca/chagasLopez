/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Dominio.Juego;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("salida"));
        out.writeObject(juego);
        out.close();
        
       
        ObjectInputStream in =new ObjectInputStream(new FileInputStream("salida"));
        try {
            juego=(Juego)in.readObject();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Perfeccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
