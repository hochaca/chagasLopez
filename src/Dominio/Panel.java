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
public class Panel implements Serializable{
    private char[][] Matriz;
    private int posFilaInicial;
    private int posColumnaInicial;

    public int getPosFilaInicial() {
        return posFilaInicial;
    }

    public int getPosColumnaInicial() {
        return posColumnaInicial;
    }

    public void setPosFilaInicial(int posFilaInicial) {
        this.posFilaInicial = posFilaInicial;
    }

    public void setPosColumnaInicial(int posColumnaInicial) {
        this.posColumnaInicial = posColumnaInicial;
    }

    public void setMatriz(char matriz[][]) {
        this.Matriz = matriz;
    }

    public char[][] getMatriz() {
        return this.Matriz;
    }

    public Panel(int tamanio) {
        char[][] matriz = new char[tamanio][tamanio];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matriz[i][j] = 32;
            }
        }
        this.setMatriz(matriz);

    }
}
