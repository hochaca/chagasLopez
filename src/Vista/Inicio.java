/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Dominio.Juego;
import java.io.File;
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
public class Inicio extends javax.swing.JFrame {

    //Sistema para acceder al dominio
    Juego elJuego;
    private File salida = null;
    private ObjectOutputStream out = null;
    private boolean salidaExiste;
    private VentanaMensajes mensaje = new VentanaMensajes();

    /**
     * Creates new form Inicio
     */
    public Inicio(Juego juego) {
        initComponents();

//MOVER EL GUARDADO DE LA SALIDA A LA OPCION DE SALIDA
        salida = new File("salida");
        salidaExiste = salida.exists();

        if (salidaExiste) {

            ObjectInputStream in = null;
            try {
                in = new ObjectInputStream(new FileInputStream("salida"));
                try {
                    elJuego = (Juego) in.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(Perfeccion.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    in.close();
                } catch (IOException ex) {
                    Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else {
            elJuego = juego;
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

        jMenuBar2 = new javax.swing.JMenuBar();
        jMenu4 = new javax.swing.JMenu();
        jMenu5 = new javax.swing.JMenu();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        MenuJugadores = new javax.swing.JMenu();
        listarJugadores = new javax.swing.JMenuItem();
        NuevoJugador = new javax.swing.JMenuItem();
        cargarJugadorArchivo = new javax.swing.JMenuItem();
        menuSalida = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();

        jMenu4.setText("File");
        jMenuBar2.add(jMenu4);

        jMenu5.setText("Edit");
        jMenuBar2.add(jMenu5);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        jMenu2.setText("Partida");

        jMenuItem1.setText("Nueva Partida");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem1);

        jMenuBar1.add(jMenu2);

        MenuJugadores.setText("Jugadores");

        listarJugadores.setText("Listar Jugadores");
        listarJugadores.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarJugadoresActionPerformed(evt);
            }
        });
        MenuJugadores.add(listarJugadores);

        NuevoJugador.setText("Nuevo Jugador");
        NuevoJugador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoJugadorActionPerformed(evt);
            }
        });
        MenuJugadores.add(NuevoJugador);

        cargarJugadorArchivo.setText("Cargar desde archivo");
        cargarJugadorArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cargarJugadorArchivoActionPerformed(evt);
            }
        });
        MenuJugadores.add(cargarJugadorArchivo);

        jMenuBar1.add(MenuJugadores);

        menuSalida.setText("Salir");

        jMenuItem4.setText("Salir");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        menuSalida.add(jMenuItem4);

        jMenuBar1.add(menuSalida);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 279, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
//        VentanaJuego ventanaJuego =  new VentanaJuego(elJuego);
//        ventanaJuego.setVisible(true);
//        
        if (elJuego.getListaJugadores().size() < 2) {
            VentanaMensajes ventanaMensajes = new VentanaMensajes();
            ventanaMensajes.MostrarMensaje("Por favor registre al menos 2 jugadores");
            ventanaMensajes.setAlwaysOnTop(true);
            ventanaMensajes.setVisible(true);
        } else {
            VentanaPartidaNueva ventanaPartida = new VentanaPartidaNueva(elJuego);
            ventanaPartida.setVisible(true);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void listarJugadoresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listarJugadoresActionPerformed
        // TODO add your handling code here:
        VentanaListaJugadores ventanaListaJugadores = new VentanaListaJugadores(elJuego);
        ventanaListaJugadores.setVisible(true);
    }//GEN-LAST:event_listarJugadoresActionPerformed

    private void NuevoJugadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoJugadorActionPerformed
        // TODO add your handling code here:
        VentanaAltaJugador ventanaAltaJugador = new VentanaAltaJugador(elJuego);
        ventanaAltaJugador.setVisible(true);
    }//GEN-LAST:event_NuevoJugadorActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        // TODO add your handling code here:
        if (!salidaExiste) {
            try {
                out = new ObjectOutputStream(new FileOutputStream("salida"));
                out.writeObject(elJuego);
                out.flush();
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.exit(0);
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void cargarJugadorArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cargarJugadorArchivoActionPerformed
        // TODO add your handling code here:
        VentanaJugadorDesdeArchivo jugadorArchivo = new VentanaJugadorDesdeArchivo(elJuego);
        jugadorArchivo.setVisible(true);
    }//GEN-LAST:event_cargarJugadorArchivoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuJugadores;
    private javax.swing.JMenuItem NuevoJugador;
    private javax.swing.JMenuItem cargarJugadorArchivo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuBar jMenuBar2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem listarJugadores;
    private javax.swing.JMenu menuSalida;
    // End of variables declaration//GEN-END:variables
}
