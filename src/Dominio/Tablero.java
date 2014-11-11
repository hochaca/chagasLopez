/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dominio;

import java.io.Serializable;

/**
 *
 * @author Santilo
 */
public class Tablero implements Serializable{
    private char matriz[][];

    public Tablero()
    {}
    
    public void setMatriz(char matriz[][]) {
        this.matriz = matriz;
    }

    public Tablero(int tamanio) {
        char[][] matrizAux = new char[tamanio][tamanio];
        for (int i = 0; i < matrizAux.length; i++) {
            for (int j = 0; j < matrizAux[i].length; j++) {
                matrizAux[i][j]=32;
                //matriz[i][j] = 122;
            }
        }
        this.setMatriz(matrizAux);

    }

    public char[][] getMatriz() {
        return this.matriz;
    }
}
