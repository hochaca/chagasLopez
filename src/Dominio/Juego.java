/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dominio;

import java.io.Serializable;
import java.util.*;

public class Juego implements Serializable{

    
    //################ NUEVOS METODOS EN SISTEMA #########################
    
    public ArrayList<Jugador> ListarJugadores(){
        ArrayList<Jugador> aux = this.getListaJugadores();
        Collections.sort(aux);
        return aux;
    }
    
    //################################################33
    
    private ArrayList<Jugador> listaJugadores;
    private ArrayList<Partida> listaPartidas;

    public ArrayList<Jugador> getListaJugadores() {
        return listaJugadores;
    }

    public ArrayList<Partida> getListaPartidas() {
        return listaPartidas;
    }

    public void setListaJugadores(ArrayList<Jugador> listaJugadores) {
        this.listaJugadores = listaJugadores;
    }

    public void setListaPartidas(ArrayList<Partida> listaPartidas) {
        this.listaPartidas = listaPartidas;
    }

    //Constructor
    public Juego() {
        listaPartidas = new ArrayList<>();
        listaJugadores = new ArrayList<>();
    }

    public void agregarJugador(Jugador jugador) {
        this.listaJugadores.add(jugador);
    }

    public ArrayList<Jugador> listarJugadores() {
        return this.listaJugadores;
    }

    public ArrayList<Partida> listarPartidas() {
        return this.listaPartidas;
    }

    public void agregarPartida(Partida partida) {
        this.listaPartidas.add(partida);
    }

    public Boolean esPanelActivo(Partida partida) {

        return partida.isPanelActivo();
    }
    //Agrega letra al tablero.
    public void AgregarLetraATablero(Partida partida, int fila, int columna, Boolean esJugadorBlanco) {
        char[][] matriz = partida.getTablero().getMatriz();
        if (esJugadorBlanco) {
            matriz[fila][columna] = 'B';
        } else {
            matriz[fila][columna] = 'N';
        }
    }
    //Restar fichas a jugador.
    public void RestarFichas(Partida partida, Boolean esJugadorBlanco) {
        partida.RestarFichas(esJugadorBlanco);
    }
    //Devuelve las fichas de un jugador.
    public int FichasJugador(Partida partida, Boolean esJugadorBlanco) {
        if (esJugadorBlanco) {
            return partida.getFichasJBlanco();
        } else {
            return partida.getFichasJNegro();
        }
    }
    //Asigna fichas a jugadores.
    public void AsignarFichasAJugadores(Partida partida, int cantFichas) {
        partida.setFichasJBlanco(cantFichas);
        partida.setFichasJNegro(cantFichas);
    }
    //Agrega una letra a la matriz.
    public void AgregarLetraAPanel(Partida partida, int fila, int columna, Boolean esJugadorBlanco) {
        char[][] panel = partida.getPanel().getMatriz();
        int posPanelFila = partida.getPanel().getPosFilaInicial();
        int posPanelColumna = partida.getPanel().getPosColumnaInicial();

        if (esJugadorBlanco) {
            panel[fila - posPanelFila][columna - posPanelColumna] = 'B';
        } else {
            panel[fila - posPanelFila][columna - posPanelColumna] = 'N';
        }
    }
    //Verifica si termino la partida, si los dos se quedaron sin fichas.
    public Boolean TerminoPartida(Partida partida) {
        Boolean result = false;

        if (partida.getFichasJBlanco() == 0 && partida.getFichasJNegro() == 0) {
            result = true;
        }
        return result;
    }
    //Mueve el panel.
    public void MoverPanel(Partida partida, int fila, int col) {

        partida.getPanel().setPosFilaInicial(partida.getPanel().getPosFilaInicial() + fila);
        partida.getPanel().setPosColumnaInicial(partida.getPanel().getPosColumnaInicial() + col);

    }
    //Devuelve el jugador que gano, osea el que tiene mas fichas visibles.
    public int JugadorGanador(char[][] matriz) {
        int result = 0;
        int cantB = 0;
        int cantN = 0;

        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {

                if (matriz[i][j] == 'B') {
                    cantB += 1;
                } else if (matriz[i][j] == 'N') {
                    cantN += 1;
                }

            }
        }

        if (cantB == cantN) {
            //Empate
            result = 0;
        } else if (cantB > cantN) {
            //Ganador B
            result = 1;
        } else {
            //Ganador N
            result = -1;
        }

        return result;
    }
    //Suma partidas ganadas y jugadas a los jugadores, cuando abandona o termina la partida.
    public void SumarPartidasJugadasYGanadas(Partida partida, Boolean esJugadorBlanco, Boolean empate) {
        if (!empate) {
            if (esJugadorBlanco) {
                //Sumo al jugador blanco ganadas
                partida.getJugadorBlanco().setPartidasGanadas(partida.getJugadorBlanco().getPartidasGanadas() + 1);
            } else {
                //Sumo al jugador negro ganadas
                partida.getJugadorNegro().setPartidasGanadas(partida.getJugadorNegro().getPartidasGanadas() + 1);
            }
        }
        //Se le suman partidas jugadas a los dos.
        partida.getJugadorBlanco().setPartidasJugadas(partida.getJugadorBlanco().getPartidasJugadas() + 1);
        partida.getJugadorNegro().setPartidasJugadas(partida.getJugadorNegro().getPartidasJugadas() + 1);
    }

    //Agrega ficha al panel
    public void AgregarOModificarLetraAMatriz(Partida partida, int fila, int columna, Boolean esJugadorBlanco) {
        //Si esta el panel activo, y la posicion en el panel, lo agrega a este
        if (this.esPanelActivo(partida) && this.posicionEnPanel(partida, fila, columna)) {
            this.AgregarLetraAPanel(partida, fila, columna, esJugadorBlanco);
        } else {
            this.AgregarLetraATablero(partida, fila, columna, esJugadorBlanco);
        }
    }
    //Obtiene el valor de una celda del panel.
    public char ObtenerValorCeldaPanel(Partida partida, int fila, int columna) {
        char[][] panel = partida.getPanel().getMatriz();
        int posPanelFila = partida.getPanel().getPosFilaInicial();
        int posPanelColumna = partida.getPanel().getPosColumnaInicial();

        return panel[fila - posPanelFila][columna - posPanelColumna];
    }
    //Modifica las letras donde se cumplio alguna simetria, por la letra del jugador que esta jugando.
    public void CambiarLetrasDeSimetria(Partida partida, char[][] simetrias, Boolean esJugaroBlanco) {

        for (int i = 0; i < simetrias.length; i++) {
            for (int j = 0; j < simetrias.length; j++) {

                if (simetrias[i][j] == '*') {
                    this.AgregarOModificarLetraAMatriz(partida, i, j, esJugaroBlanco);
                }

            }
        }
    }
    //devuelve si la posicion ingresada pertenece al panel.
    public Boolean posicionEnPanel(Partida partida, int posFila, int posCol) {

        Boolean pertenece = false;
        char matrizPanel[][] = partida.getPanel().getMatriz();
        int posFilaPanel = partida.getPanel().getPosFilaInicial();
        int posColPanel = partida.getPanel().getPosColumnaInicial();

        for (int i = 0; i < matrizPanel.length; i++) {
            for (int j = 0; j < matrizPanel[i].length; j++) {
                if (i + posFilaPanel == posFila && j + posColPanel == posCol) {
                    pertenece = true;
                }
            }
        }

        return pertenece;
    }
    //Pone como activo al panel.
    public void AsignarPanel(Partida partida) {
        partida.setPanelActivo(Boolean.TRUE);
    }

}
