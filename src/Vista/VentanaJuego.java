/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Vista;

import Dominio.Juego;
import Dominio.Partida;
import Dominio.Tablero;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Santilo
 */
public class VentanaJuego extends javax.swing.JFrame {

    private Boolean esJugadorBlanco = true;
    private JButton[][] botones;
    private Juego elJuego;
    /**
     * Creates new form VentanaJuego
     */
    public VentanaJuego(Juego juego, Partida partida) {
        initComponents(); 
        elJuego = juego;
        int largoTablero = partida.getTablero().getMatriz().length;
        
        // crear botones y agregarlos al panel 
        panelJuego.setLayout(new GridLayout(largoTablero, largoTablero));
        botones = new JButton[largoTablero + 2][largoTablero + 2];
        for (int i = 1; i <= largoTablero; i++) {
            for (int j = 1; j <= largoTablero; j++) {
                JButton jButton = new JButton();
                //
                jButton.setText("   ");
                jButton.setBackground(Color.white);
                jButton.addActionListener(new ListenerBoton(i, j));
                panelJuego.add(jButton);
                botones[i][j] = jButton;
            }
        }
        jPanelNumeros.setLayout(new GridLayout(1, largoTablero));
        for (int i = 0; i < largoTablero; i++) {
            JButton jButton = new JButton();
            String texto = String.valueOf((i+1));
            jButton.setText(" "+texto);;
            jButton.setEnabled(false);
            jButton.setBackground(Color.lightGray);
            jPanelNumeros.add(jButton);
        }
        JPanelLetras.setLayout(new GridLayout(largoTablero, 1));
        
        for (int i = 0; i < largoTablero; i++) {
            char textoAux = (char)(i + 65);
            String texto = textoAux + "";
            JButton jButton = new JButton();
            jButton.setEnabled(false);
            jButton.setText(texto);
            jButton.setBackground(Color.lightGray);
            JPanelLetras.add(jButton);
        }
        
    }

    private class ListenerBoton implements ActionListener {

        private int x;
        private int y;

        public ListenerBoton(int i, int j) {
            // en el constructor se almacena la fila y columna que se presionó 
            x = i;
            y = j;
        }

        public void actionPerformed(ActionEvent e) {
            // cuando se presiona un botón, se ejecutará este método 
            clickBoton(x, y);
        }
    }

    private void clickBoton(int fila, int columna) {
        
        
        //Valido el tipo de jugada
        TipoDeJugada(fila, columna);
       
        char[][] tabSimetria = new char[this.botones.length - 2][this.botones[0].length - 2];
        
        TomarFichas(tabSimetria);
        // Método a completar!. 
        // En fila y columna se reciben las coordenas donde presionó el usuario, relativas al comienzo de la grilla 
        // fila 1 y columna 1 corresponden a la posición de arriba a la izquierda. 
        // Debe indicarse cómo responder al click de ese botón. 
    }
    
    private void TipoDeJugada(int fila, int columna)
    {
        
        if (jrbPonerFicha.isSelected()) {
            
             //Accedo al boton actual.
            if (esJugadorBlanco) {
                this.botones[fila][columna].setBackground(Color.red);
                this.botones[fila][columna].setText("B");
                this.botones[fila][columna].setEnabled(false);
            }else{
                this.botones[fila][columna].setBackground(Color.blue);
                this.botones[fila][columna].setText("N");
                this.botones[fila][columna].setEnabled(false);
                
            }
            esJugadorBlanco = !esJugadorBlanco;

            
        }else if (jrbPonerPanel.isSelected()) {
            
        }else if (jrbMoverPanel.isSelected()) {
            
        }else if (jrbJugarFichaEspecial.isSelected()) {
            
        }
    
    }
    
    private void ImprimirMatriz(char[][] matrizImprimir, Partida partida)
    {
        for (int i = 1; i < this.botones.length - 1; i++) {
            for (int j = 1; j < this.botones[0].length - 1; j++) {
                
                if (elJuego.esPanelActivo(partida) && elJuego.posicionEnPanel(partida, i-1, j-1)
                        && partida.getPanel() != null && partida.getTablero() != null) {
                    this.botones[i-1][j-1].setText(String.valueOf(elJuego.ObtenerValorCeldaPanel(partida, i-1, j-1)));
                    //Le asigno borde a la celda para saber que esta el Panel.
                    Border thickBorder = new LineBorder(Color.BLACK, 2);
                    this.botones[i-1][j-1].setBorder(thickBorder);
                    
                }
                else
                {
                    this.botones[i-1][j-1].setText(String.valueOf(matrizImprimir[i-1][j-1]));
                }
                
            }
        }
    }
    
    private void TomarFichas(char[][] tabSimetria)
    {
        for (int i = 1; i < this.botones.length - 1; i++) {
            for (int j = 1; j < this.botones[0].length - 1; j++) {
                char arr[] = this.botones[i][j].getText().toCharArray();
                tabSimetria[i-1][j-1] = arr[0];
            }
        }
    }
    
    //Verifica que en esa posicion, no exista una ficha.
    public static Boolean posicionHabilitadaParaFicha(char matriz[][], int fila, int columna) {
        return Juego.posicionHabilitadaParaFicha(matriz, fila, columna);
    }
    
    //Verifica las simetrias, se llama a los metodos que verifican simetrias de 2, 3 y 4.
    public static void VerificarSimetrias(int dim, int fila, int columna, Partida partida, Boolean esJugadorBlanco) {
        char tabSimetriaAux[][] = new char[dim][dim];

        Boolean esSimetrico = false;

        char[][] tableroAux = Juego.JuntaMatrizYPanel(partida);
        Tablero tab = new Tablero();
        tab.setMatriz(tableroAux);
        //Llama a los tres metodos para ver las simetrias, devuelve un booleano si hay simetrias
        //y en el tabSimetria una matriz con *
        esSimetrico = Juego.SimetriaDos(tab, fila, columna, esSimetrico, tabSimetriaAux);
        esSimetrico = Juego.SimetriaTres(tab, fila, columna, esSimetrico, tabSimetriaAux);
        esSimetrico = Juego.SimetriaCuatro(tab, fila, columna, esSimetrico, tabSimetriaAux);

        boolean mostrarSim = true;

        if (esSimetrico) {

            //verifico que la simetria tenga B y N.
            mostrarSim = Juego.HayBsyNs(tabSimetriaAux, tableroAux);

            if (mostrarSim) {
                //Cambia las letras de la simetria
                Juego.CambiarLetrasDeSimetria(partida, tabSimetriaAux, esJugadorBlanco);
                System.out.println("Tabla de simetria");
                System.out.println();
                //Muestra la tabla de simetrias con *
                //ImprimirMatriz(juego, tabSimetria, partida, true, false);
            } else {
                System.out.println("Tabla de simetria");
                System.out.println();
                //Muestra la tabla de simetrias vacia
                //ImprimirMatriz(juego, tabSimetria, partida, true, true);
            }

        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        buttonGroup1 = new javax.swing.ButtonGroup();
        jButton1 = new javax.swing.JButton();
        panelJuego = new javax.swing.JPanel();
        lblMensaje = new javax.swing.JLabel();
        jPanelNumeros = new javax.swing.JPanel();
        JPanelLetras = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jrbPonerFicha = new javax.swing.JRadioButton();
        jrbPonerPanel = new javax.swing.JRadioButton();
        jrbMoverPanel = new javax.swing.JRadioButton();
        jrbJugarFichaEspecial = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jButton1.setText("Abandonar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelJuegoLayout = new javax.swing.GroupLayout(panelJuego);
        panelJuego.setLayout(panelJuegoLayout);
        panelJuegoLayout.setHorizontalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        panelJuegoLayout.setVerticalGroup(
            panelJuegoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        lblMensaje.setText("jLabel1");

        javax.swing.GroupLayout jPanelNumerosLayout = new javax.swing.GroupLayout(jPanelNumeros);
        jPanelNumeros.setLayout(jPanelNumerosLayout);
        jPanelNumerosLayout.setHorizontalGroup(
            jPanelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanelNumerosLayout.setVerticalGroup(
            jPanelNumerosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 30, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout JPanelLetrasLayout = new javax.swing.GroupLayout(JPanelLetras);
        JPanelLetras.setLayout(JPanelLetrasLayout);
        JPanelLetrasLayout.setHorizontalGroup(
            JPanelLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 28, Short.MAX_VALUE)
        );
        JPanelLetrasLayout.setVerticalGroup(
            JPanelLetrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );

        jLabel1.setFont(new java.awt.Font("Dialog", 0, 14)); // NOI18N
        jLabel1.setText("Tipo de Jugada");

        buttonGroup1.add(jrbPonerFicha);
        jrbPonerFicha.setSelected(true);
        jrbPonerFicha.setText("Poner Ficha");

        buttonGroup1.add(jrbPonerPanel);
        jrbPonerPanel.setText("Poner Panel");

        buttonGroup1.add(jrbMoverPanel);
        jrbMoverPanel.setText("Mover Panel");

        buttonGroup1.add(jrbJugarFichaEspecial);
        jrbJugarFichaEspecial.setText("Jugar Ficha Especial");
        jrbJugarFichaEspecial.setToolTipText("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblMensaje)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 579, Short.MAX_VALUE)
                        .addComponent(jButton1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanelNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(JPanelLetras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jrbPonerFicha)
                            .addComponent(jrbPonerPanel)
                            .addComponent(jrbMoverPanel)
                            .addComponent(jrbJugarFichaEspecial))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanelNumeros, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(JPanelLetras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(48, 48, 48)
                                .addComponent(panelJuego, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(28, 28, 28)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGap(13, 13, 13)
                                .addComponent(jrbPonerFicha)
                                .addGap(18, 18, 18)
                                .addComponent(jrbPonerPanel)
                                .addGap(18, 18, 18)
                                .addComponent(jrbMoverPanel)
                                .addGap(18, 18, 18)
                                .addComponent(jrbJugarFichaEspecial)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 119, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addComponent(lblMensaje))))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:\
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanelLetras;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanelNumeros;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton jrbJugarFichaEspecial;
    private javax.swing.JRadioButton jrbMoverPanel;
    private javax.swing.JRadioButton jrbPonerFicha;
    private javax.swing.JRadioButton jrbPonerPanel;
    private javax.swing.JLabel lblMensaje;
    private javax.swing.JPanel panelJuego;
    // End of variables declaration//GEN-END:variables
}
