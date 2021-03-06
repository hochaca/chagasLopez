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
public class Jugador implements Comparable<Jugador>, Serializable{
    
    private String Nombre;
    private int Edad;
    private String Alias;
    private int PartidasJugadas;
    private int PartidasGanadas;

    public Jugador() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setEdad(int Edad) {
        this.Edad = Edad;
    }

    public void setAlias(String Alias) {
        this.Alias = Alias;
    }

    public void setPartidasJugadas(int PartidasJugadas) {
        this.PartidasJugadas = PartidasJugadas;
    }

    public void setPartidasGanadas(int PartidasGanadas) {
        this.PartidasGanadas = PartidasGanadas;
    }
    
    public String getNombre() {
        return Nombre;
    }

    public int getEdad() {
        return Edad;
    }

    public String getAlias() {
        return Alias;
    }

    public int getPartidasJugadas() {
        return PartidasJugadas;
    }

    public int getPartidasGanadas() {
        return PartidasGanadas;
    }
    
    public void sumarPerdidaJugadas() {
        int aumentar = this.PartidasGanadas;
      aumentar++;
        this.setPartidasGanadas(aumentar);
    }

    public void sumarJugadasGanadas() {
        int aumentar = this.PartidasGanadas;
        aumentar++;
        this.setPartidasGanadas(aumentar);
    }
    
    public Jugador(String nombre, String alias, int edad) {
        this.setAlias(alias);
        this.setNombre(nombre);
        this.setEdad(edad);
        this.setPartidasGanadas(0);
        this.setPartidasJugadas(0);
    }

    @Override
    public boolean equals(Object o) {
        return this.getAlias().equals(((Jugador) o).getAlias());
    }

    @Override
    public String toString() {
        return ("Nombre: " + this.getNombre()+ " Alias: " + this.getAlias()  +" Edad: " + this.getEdad()+ " Jugadas: " + this.getPartidasJugadas()+ " Ganadas: " + this.getPartidasGanadas());
    }

    @Override
    public int compareTo(Jugador t) {
        int orden = this.getNombre().compareTo(t.getNombre());
        return orden;
    }
    
    
}
