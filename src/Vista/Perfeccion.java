/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Dominio.Juego;

/**
 *
 * @author Santilo
 */
public class Perfeccion {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Juego juego = new Juego();
        Inicio inicio = new Inicio(juego);
        inicio.setVisible(true);
        
    }
    
}
