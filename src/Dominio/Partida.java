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
public class Partida implements Serializable{
    private Tablero tablero;
    private Panel panel;
    private boolean panelActivo;
    private Jugador jugadorBlanco;
    private Jugador jugadorNegro;
    private int fichasJBlanco;
    private int fichasJNegro;
    private int fichas;

    public void RestarFichas(Boolean esJugadorBlanco) {
        if (esJugadorBlanco) {
            int fichasBlanco = this.getFichasJBlanco();
            if (fichasBlanco > 0) {
                this.setFichasJBlanco(fichasBlanco - 1);
            }
        } else {
            int fichasNegro = this.getFichasJNegro();
            if (fichasNegro > 0) {
                this.setFichasJNegro(fichasNegro - 1);
            }
        }
    }

    public boolean isPanelActivo() {
        return this.panelActivo;
    }

    public void setPanelActivo(Boolean panelActivo) {
        this.panelActivo = panelActivo;
    }
    
    public boolean getPanelActivo(){
        return this.panelActivo;
    }
    public void setPanelActivo(){
        this.panelActivo=true;
    }

    public Partida() {

    }

    public void setJugadorBlanco(Jugador jugadorBlanco) {
        this.jugadorBlanco = jugadorBlanco;
    }

    public void setJugadorNegro(Jugador jugadorNegro) {
        this.jugadorNegro = jugadorNegro;
    }

    public void setTablero(Tablero tablero) {
        this.tablero = tablero;
    }

    public void setPanel(Panel panel) {
        this.panel = panel;
    }

    public void setFichas(int fichas) {
        this.fichas = fichas;
    }

    public Jugador getJugadorBlanco() {
        return this.jugadorBlanco;
    }

    public Jugador getJugadorNegro() {
        return this.jugadorNegro;
    }

    public Tablero getTablero() {
        return this.tablero;
    }

    public Panel getPanel() {
        return this.panel;
    }

    public int getFichas() {
        return this.fichas;
    }

    public void setFichasJBlanco(int fichasJBlanco) {
        this.fichasJBlanco = fichasJBlanco;
    }

    public void setFichasJNegro(int fichasJNegro) {
        this.fichasJNegro = fichasJNegro;
    }

    public int getFichasJBlanco() {
        return this.fichasJBlanco;
    }

    public int getFichasJNegro() {
        return this.fichasJNegro;
    }

    public Partida(Jugador jugadorBlanco, Jugador jugadorNegro, int fichas, Tablero tablero, Panel panel) {
        this.setFichas(fichas);
        this.setFichasJBlanco(fichas / 2);
        this.setFichasJNegro(fichas / 2);
        this.setJugadorBlanco(jugadorBlanco);
        this.setJugadorNegro(jugadorNegro);
        this.setPanel(panel);
        this.setTablero(tablero);
    }
}
