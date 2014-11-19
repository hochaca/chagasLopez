/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package persistencia;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/**
 *
 * @author Santilo
 */
public class ClaseLectura {
    
    private String linea;
    private BufferedReader buffer;
    //Constructor
    public ClaseLectura(String archivo){
        
        try{
            buffer = new BufferedReader(new FileReader(archivo));
        }catch (Exception e) {

        }
    }
    //Pregunta si hay mas lineas
    public Boolean HayMasLineas(){
        try{
            linea = buffer.readLine();
            return linea != null;
        }catch(Exception e){
            return false;
        }
    }
    //Devuelve la linea
    public String linea (){
        return linea;
    }
    
    public void cerrer(){
        try{
            buffer.close();
        }catch(Exception e){
        }
    }
    
}
