/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Dominio;

import java.util.*;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import static java.lang.System.out;
import java.util.*;

public class Juego implements Serializable {

    
    //################ NUEVOS METODOS EN SISTEMA #########################
    
    public ArrayList<Jugador> ListarJugadores(){
        ArrayList<Jugador> aux = this.getListaJugadores();
        Collections.sort(aux);
        return aux;
    }
    
    public Jugador BuscarJugadorPorAlias(String alias){
    Jugador encontrado = null;
    for(int i=0; i<this.ListarJugadores().size(); i++){
        if(this.ListarJugadores().get(i).getAlias().equals(alias)){
            encontrado=this.listaJugadores.get(i);
        }    
    }
    return encontrado;
    }
    
    public void FichaLimpieza(Partida partida, int fila, int columna, Boolean esJugadorBlanco)
    {
        for (int i = 0; i < partida.getTablero().getMatriz().length; i++) {
            
            //BORRO LAS FILAS
            //Si esta el panel activo, y la posicion en el panel, lo borra en este
            if (Juego.esPanelActivo(partida) && Juego.posicionEnPanel(partida, fila, i)) {
                Juego.BorrarLetraAPanel(partida, fila, i);
            } else {
                Juego.BorrarLetraATablero(partida, fila, i);
            }
            
            //BORRO LAS COLUMNAS
            //Si esta el panel activo, y la posicion en el panel, lo borra en este
            if (Juego.esPanelActivo(partida) && Juego.posicionEnPanel(partida, i, columna)) {
                Juego.BorrarLetraAPanel(partida, i, columna);
            } else {
                Juego.BorrarLetraATablero(partida, i, columna);
            }
        }
    }
    
    //################################################33
    
    //Agrega ficha al panel
    public static void AgregarOModificarLetraAMatriz(Partida partida, int fila, int columna, Boolean esJugadorBlanco) {
        //Si esta el panel activo, y la posicion en el panel, lo agrega a este
        if (Juego.esPanelActivo(partida) && Juego.posicionEnPanel(partida, fila, columna)) {
            Juego.AgregarLetraAPanel(partida, fila, columna, esJugadorBlanco);
        } else {
            Juego.AgregarLetraATablero(partida, fila, columna, esJugadorBlanco);
        }
    }
    
    //Borrar una letra al Panel.
    public static void BorrarLetraAPanel(Partida partida, int fila, int columna) {
        char[][] panel = partida.getPanel().getMatriz();
        int posPanelFila = partida.getPanel().getPosFilaInicial();
        int posPanelColumna = partida.getPanel().getPosColumnaInicial();

        panel[fila - posPanelFila][columna - posPanelColumna] = ' ';
    }
    
    //Borrar una letra al Tablero.
    public static void BorrarLetraATablero(Partida partida, int fila, int columna) {
        char[][] matriz = partida.getTablero().getMatriz();
        matriz[fila][columna] = ' ';
    }
    
    //Agrega una letra a la matriz.
    public static void AgregarLetraAPanel(Partida partida, int fila, int columna, Boolean esJugadorBlanco) {
        char[][] panel = partida.getPanel().getMatriz();
        int posPanelFila = partida.getPanel().getPosFilaInicial();
        int posPanelColumna = partida.getPanel().getPosColumnaInicial();

        if (esJugadorBlanco) {
            panel[fila - posPanelFila][columna - posPanelColumna] = 'B';
        } else {
            panel[fila - posPanelFila][columna - posPanelColumna] = 'N';
        }
    }
    
    //Agrega letra al tablero.
    public static void AgregarLetraATablero(Partida partida, int fila, int columna, Boolean esJugadorBlanco) {
        char[][] matriz = partida.getTablero().getMatriz();
        if (esJugadorBlanco) {
            matriz[fila][columna] = 'B';
        } else {
            matriz[fila][columna] = 'N';
        }
    }
    
    //Verifica que en esa posicion, no exista una ficha.
    public static Boolean posicionHabilitadaParaFicha(char matriz[][], int fila, int columna) {
        Boolean jugadaOk = false;
        if (matriz[fila - 65][columna - 49] != 32) {
            jugadaOk = false;
        } else {
            jugadaOk = true;
        }
        return jugadaOk;
    }
    
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

    public static Boolean esPanelActivo(Partida partida) {

        return partida.isPanelActivo();
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

    //Obtiene el valor de una celda del panel.
    public char ObtenerValorCeldaPanel(Partida partida, int fila, int columna) {
        char[][] panel = partida.getPanel().getMatriz();
        int posPanelFila = partida.getPanel().getPosFilaInicial();
        int posPanelColumna = partida.getPanel().getPosColumnaInicial();

        return panel[fila - posPanelFila][columna - posPanelColumna];
    }
    //Modifica las letras donde se cumplio alguna simetria, por la letra del jugador que esta jugando.
    public static void CambiarLetrasDeSimetria(Partida partida, char[][] simetrias, Boolean esJugaroBlanco) {

        for (int i = 0; i < simetrias.length; i++) {
            for (int j = 0; j < simetrias.length; j++) {

                if (simetrias[i][j] == '*') {
                    Juego.AgregarOModificarLetraAMatriz(partida, i, j, esJugaroBlanco);
                }

            }
        }
    }
    //devuelve si la posicion ingresada pertenece al panel.
    public static Boolean posicionEnPanel(Partida partida, int posFila, int posCol) {

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
    
    //Devuelve si la posicion es valida
    public static boolean esPosicionValida(char[][] matriz, int fila, int columna) {
        boolean result = true;

        if (fila < 0 || fila >= matriz.length
                || columna < 0 || columna >= matriz[0].length) {
            result = false;
        }

        return result;
    }
    
    //Metodo para verificar si dos celdas son iguales y distindas de vacio.
    public static Boolean posNoVaciaYCeldasIguales(char[][] matriz, int filaUNO, int columnaUNO, int filaDOS, int columnaDOS) {
        char celdaUNO = matriz[filaUNO][columnaUNO];
        char celdaDOS = matriz[filaDOS][columnaDOS];
        Boolean iguales = false;
        //32 es vacio
        if (celdaUNO != 32 && celdaDOS != 32) {
            if (celdaUNO == celdaDOS) {
                iguales = true;
            }
        }
        return iguales;
    }
    
    //Llena la tabla de simetrias con * para cada jugada.
    public static char[][] LlenarTablaSimetrias(char[][] tabSimetrias, int filaI, int columnaI, int filaF, int columnaF) {
        char tabSimetria[][] = tabSimetrias;
        for (int k = filaI; k <= filaF; k++) {
            for (int l = columnaI; l <= columnaF; l++) {
                tabSimetria[k][l] = '*';
            }
        }
        return tabSimetria;
    }
    
    //Verifica las simetrias de Dos
    public static Boolean SimetriaDos(Tablero tablero, int fila, int columna, Boolean haySimetria, char[][] tabSimetrias) {
        char tabSimetria[][] = tabSimetrias;
        char matriz[][] = tablero.getMatriz();

        Boolean esSimetrico = haySimetria;
        //Recorro la matriz desde -1-1 a +1+1, y verifico las posiciones por si hay simetria.
        //Si las simetrias se cumplen lleno la tabla de simetrias y cambio la bandera para
        //avisar que al menos existe una simetria.
        for (int i = fila - 1; i <= fila + 1; i++) {
            for (int j = columna - 1; j <= columna + 1; j++) {
                //Esquian Superior Izquierda
                if (i == fila - 1 && j == columna - 1) {
                    if (Juego.esPosicionValida(matriz, fila - 1, columna - 1)) {
                        if (posNoVaciaYCeldasIguales(matriz, fila - 1, columna - 1, fila, columna - 1)
                                && posNoVaciaYCeldasIguales(matriz, fila - 1, columna, fila, columna)
                                || posNoVaciaYCeldasIguales(matriz, fila - 1, columna - 1, fila - 1, columna)
                                && posNoVaciaYCeldasIguales(matriz, fila, columna - 1, fila, columna)) {

                            tabSimetria = Juego.LlenarTablaSimetrias(tabSimetria, fila - 1, columna - 1, fila, columna);
                            esSimetrico = true;
                        }
                    }
                }
                //Esquian Superior Derecha
                if (i == fila - 1 && j == columna + 1) {
                    if (esPosicionValida(tablero.getMatriz(), fila - 1, columna + 1)) {
                        if (posNoVaciaYCeldasIguales(matriz, fila - 1, columna + 1, fila - 1, columna)
                                && posNoVaciaYCeldasIguales(matriz, fila, columna + 1, fila, columna)
                                || posNoVaciaYCeldasIguales(matriz, fila - 1, columna + 1, fila, columna + 1)
                                && posNoVaciaYCeldasIguales(matriz, fila - 1, columna, fila, columna)) {

                            tabSimetria = LlenarTablaSimetrias(tabSimetria, fila - 1, columna, fila, columna + 1);
                            esSimetrico = true;
                        }
                    }
                }
                //Esquian Inferior Izquierda
                if (i == fila + 1 && j == columna - 1) {
                    if (esPosicionValida(matriz, fila + 1, columna - 1)) {
                        if (posNoVaciaYCeldasIguales(matriz, fila + 1, columna - 1, fila + 1, columna)
                                && posNoVaciaYCeldasIguales(matriz, fila, columna - 1, fila, columna)
                                || posNoVaciaYCeldasIguales(matriz, fila + 1, columna - 1, fila, columna - 1)
                                && posNoVaciaYCeldasIguales(matriz, fila + 1, columna, fila, columna)) {

                            tabSimetria = LlenarTablaSimetrias(tabSimetria, fila, columna - 1, fila + 1, columna);
                            esSimetrico = true;
                        }
                    }
                }
                //Esquian Inferior Derecha
                if (i == fila + 1 && j == columna + 1) {
                    if (esPosicionValida(matriz, fila + 1, columna + 1)) {
                        if (posNoVaciaYCeldasIguales(matriz, fila, columna, fila, columna + 1)
                                && posNoVaciaYCeldasIguales(matriz, fila + 1, columna, fila + 1, columna + 1)
                                || posNoVaciaYCeldasIguales(matriz, fila, columna, fila + 1, columna)
                                && posNoVaciaYCeldasIguales(matriz, fila, columna + 1, fila + 1, columna + 1)) {

                            tabSimetria = LlenarTablaSimetrias(tabSimetria, fila, columna, fila + 1, columna + 1);
                            esSimetrico = true;
                        }
                    }
                }
            }
        }

        return esSimetrico;

    }

    //Verifica las simetrias de Tres
    public static Boolean SimetriaTres(Tablero tablero, int fila, int columna, Boolean haySimetria, char[][] tabSimetrias) {
        char tabSimetria[][] = tabSimetrias;
        char matriz[][] = tablero.getMatriz();

        Boolean esSimetrico = haySimetria;
        int count = 0;
        int auxCount = 0;

        //Guardo el valor donde voy a arrancar a recorrer para la simetria vertical.
        int inicioHorizontal = 0;
        if (esPosicionValida(matriz, fila - 1, columna - 2)) {
            //Si es Posición arranca desde el indice columna -2
            inicioHorizontal = columna - 2;
        } else {
            if (esPosicionValida(tablero.getMatriz(), fila - 1, columna - 1)) {
                //Si es Posición arranca desde el indice columna -1
                inicioHorizontal = columna - 1;
            } else {
                //Si es Posición arranca desde el indice columna
                inicioHorizontal = columna;
            }
        }

        int colFin = 0; //Columna en la cual termina la simetria.
        int colInicio = 0; //Columna en la cual inicia la simetria.

        for (int j = inicioHorizontal; j <= columna + 2; j++) {

            count = auxCount;
            //Por cada posicion valido que se cumpla simetria.
            //Si se cumple sumo 1 al count.
            //Si no se cumple mase, valido si el count es 3, ya se cumplio una antes y lleno la matriz de simetrias.
            if (esPosicionValida(tablero.getMatriz(), fila - 1, j) && esPosicionValida(tablero.getMatriz(), fila + 1, j)) {
                if (posNoVaciaYCeldasIguales(matriz, fila - 1, j, fila + 1, j)) {
                    if (colInicio == 0) {
                        colInicio = j;
                    }
                    auxCount += 1;
                    count += 1;
                } else {
                    auxCount = 0;
                    //Si el count es 3 o mayor, se guarda, si no vuelve a 0.
                    count = count >= 3 ? count : 0;
                    //Si el count es 3 o mayor, queda guardad la columna inicial, si no vuelve a 0.
                    colInicio = count >= 3 ? colInicio : 0;
                    colFin = j - 1;
                }
            }
            if (j == columna + 2) {
                if (auxCount != 0 && count >= 3) {
                    colFin = columna + 2;
                }
                auxCount = 0;
            }
            //Si ya no hay mas igualdades y por lo menos se registraron 3 antes
            if (auxCount == 0 && count >= 3) {
                tabSimetria = LlenarTablaSimetrias(tabSimetria, fila - 1, colInicio, fila + 1, colFin);
                esSimetrico = true;
                count = 0;
            }
        }
        //Inicializo a 0 para verificar ahora la Simetria Vertical
        count = 0;
        auxCount = 0;

        //Idem para las simetrias con eje horizontal.
        //Simetria Vertical
        int inicioVertical = 0;
        if (esPosicionValida(tablero.getMatriz(), fila - 2, columna - 1)) {
            //Si es Posición arranca desde el indice fila -2
            inicioVertical = fila - 2;
        } else {
            if (esPosicionValida(tablero.getMatriz(), fila - 1, columna - 1)) {
                //Si es Posición arranca desde el indice fila -1
                inicioVertical = fila - 1;
            } else {
                //Si es Posición arranca desde el indice fila
                inicioVertical = fila;
            }
        }

        //Simetria Vertical
        if (esPosicionValida(tablero.getMatriz(), inicioVertical, columna - 1)
                && esPosicionValida(tablero.getMatriz(), inicioVertical, columna + 1)) {

            int filaFin = 0; //Columna en la cual termina la simetria.

            for (int j = inicioVertical; j <= fila + 2; j++) {

                count = auxCount;

                if (esPosicionValida(tablero.getMatriz(), j, columna - 1)) {
                    if (posNoVaciaYCeldasIguales(matriz, j, columna - 1, j, columna + 1)) {
                        auxCount += 1;
                        count += 1;
                    } else {
                        auxCount = 0;
                        //Si el count es 3 o mayor, se guarda, si no vuelve a 0.
                        count = count >= 3 ? count : 0;
                        filaFin = j - 1;
                    }
                }
                if (j == fila + 2) {
                    if (auxCount != 0 && count >= 3) {
                        filaFin = fila + 2;
                    }
                    auxCount = 0;
                }
                //Si ya no hay mas igualdades y por lo menos se registraron 3 antes
                if (auxCount == 0 && count >= 3) {
                    tabSimetria = LlenarTablaSimetrias(tabSimetria, inicioVertical, columna - 1, filaFin, columna + 1);
                    esSimetrico = true;
                    count = 0;
                }
            }
        }

        return esSimetrico;
    }

    //Verifica las simetrias de Cuatro
    public static Boolean SimetriaCuatro(Tablero tablero, int fila, int columna, Boolean haySimetria, char[][] tabSimetrias) {
        char tabSimetria[][] = tabSimetrias;
        char matriz[][] = tablero.getMatriz();

        Boolean esSimetrico = haySimetria;

        int inicioFila = 0;
        int inicioCol = 0;

        //Verificar donde arranco a recorrer.
        for (int i = fila; i >= fila - 3; i--) {
            for (int j = columna; j >= columna - 3; j--) {
                if (esPosicionValida(tablero.getMatriz(), i, j)) {
                    inicioFila = i;
                    inicioCol = j;
                }
            }
        }
        
        //Idem a la simetria de tres, en esta caso valido que se cumpla la simetria cuatro.
        //se valida para l l+3 y despues l l+1
        for (int i = inicioFila; i <= fila; i++) {
            for (int j = inicioCol; j <= columna; j++) {

                Boolean esSim4 = true;

                //Simetria Vertical de 4
                for (int k = i; k <= i + 3; k++) {
                    for (int l = j; l <= j + 1; l++) {
                        //Si no se va del rango de donde ingresaste.
                        if (k <= fila + 3 && l <= columna + 3) {
                            //Si es la columna 0, chequeo que sea igual a +3
                            if (l == j) {
                                if (esPosicionValida(tabSimetria, k, l + 3)) {
                                    //Si la pos es vacia o las celdas sin distindas (hago lo inverso del metodo por eso el !)
                                    if (!posNoVaciaYCeldasIguales(matriz, k, l, k, l + 3)) {
                                        esSim4 = false;
                                    }
                                } else {
                                    esSim4 = false;
                                }
                                //Si es columna != 0, chequeo con mas uno.            
                            } else if (l != j) {
                                if (esPosicionValida(tabSimetria, k, l + 1)) {
                                    //Si la pos es vacia o las celdas sin distindas (hago lo inverso del metodo por eso el !)
                                    if (!posNoVaciaYCeldasIguales(matriz, k, l + 1, k, l)) {
                                        esSim4 = false;
                                    }
                                }
                            } else {
                                esSim4 = false;
                            }
                        }
                    }
                }
                //si nunca cambio a falso, siempre es igual, entonces escribe matriz.    
                if (esSim4) {
                    tabSimetria = LlenarTablaSimetrias(tabSimetria, i, j, i + 3, j + 3);
                    esSimetrico = true;
                }

                //Inicio devuelta en true para la simetria Vertical de 4
                esSim4 = true;

                //Simetria Horizontal de 4
                for (int k = i; k <= i + 1; k++) {
                    for (int l = j; l <= j + 3; l++) {
                        //Si no se va del rango de donde ingresaste.
                        if (k <= fila + 3 && l <= columna + 3) {
                            //Si es la fila 0, chequeo con la fila +3
                            if (k == i) {
                                //Si es posicion valida
                                if (esPosicionValida(tabSimetria, k + 3, l)) {
                                    //Si la pos es vacia o las celdas sin distindas (hago lo inverso del metodo por eso el !)
                                    if (!posNoVaciaYCeldasIguales(matriz, k, l, k + 3, l)) {
                                        esSim4 = false;
                                    }
                                } else {
                                    esSim4 = false;
                                }
                                //Si es fila distinta de 0, chequeo con la +1    
                            } else if (k != i) {
                                if (i != k) {
                                    if (esPosicionValida(tabSimetria, k + 1, l)) {
                                        //Si la pos es vacia o las celdas sin distindas (hago lo inverso del metodo por eso el !)
                                        if (!posNoVaciaYCeldasIguales(matriz, k, l, k + 1, l)) {
                                            esSim4 = false;
                                        }
                                    } else {
                                        esSim4 = false;
                                    }
                                }
                            }
                        } else {
                            esSim4 = false;
                        }
                    }
                }
                //si nunca cambio a falso, siempre es igual, entonces escribe matriz.
                if (esSim4) {
                    tabSimetria = LlenarTablaSimetrias(tabSimetria, i, j, i + 3, j + 3);
                    esSimetrico = true;
                }

            }
        }
        return esSimetrico;
    }
    
    //Metodos para ver si una matriz tiene B y N.
    public static boolean HayBsyNs(char[][] simetrias, char[][] matriz) {
        boolean hayB = false;
        boolean hayN = false;

        for (int i = 0; i < simetrias.length; i++) {
            for (int j = 0; j < simetrias[0].length; j++) {
                if (simetrias[i][j] == '*') {
                    if (matriz[i][j] == 'B') {
                        hayB = true;
                    }
                    if (matriz[i][j] == 'N') {
                        hayN = true;
                    }
                }
            }
        }
        return hayB && hayN;
    }
    
    //Junta el panel y la matriz para luego mandarla a la simetria.
    public static char[][] JuntaMatrizYPanel(Partida partida) {
        char[][] panel = partida.getPanel().getMatriz();
        char[][] tablero = partida.getTablero().getMatriz();
        char[][] result = new char[tablero.length][tablero.length];
        int filaPanel = partida.getPanel().getPosFilaInicial();
        int colPanel = partida.getPanel().getPosColumnaInicial();
        //Lleno la matriz aux con los valores del tablero.
        for (int i = 0; i < tablero.length; i++) {
            for (int j = 0; j < tablero[0].length; j++) {
                result[i][j] = tablero[i][j];
            }
        }
        //Si el panel activo, cargo las posiciones que corresponda con los valores del panel.
        if (Juego.esPanelActivo(partida)) {
            for (int i = 0; i < panel.length; i++) {
                for (int j = 0; j < panel[i].length; j++) {
                    result[i + filaPanel][j + colPanel] = panel[i][j];
                }
            }
        }
        return result;
    }


}
