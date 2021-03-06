/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Dominio.Juego;
import Dominio.Jugador;
import persistencia.ClaseLectura;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;

/**
 *
 * @author horaciochagas
 */
public class VentanaJugadorDesdeArchivo extends javax.swing.JFrame {

    /**
     * Creates new form JugadorDesdeArchivo
     */
    private Juego miJuego;
   
private File archivo=null;
       
    public VentanaJugadorDesdeArchivo(Juego juego) {
        miJuego = juego;
        initComponents();
     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jDialog1 = new javax.swing.JDialog();
        jFileChooser1 = new javax.swing.JFileChooser();
        jDialog2 = new javax.swing.JDialog();
        jLabel1 = new javax.swing.JLabel();
        botonExplorar = new javax.swing.JButton();
        botonRegistrar = new javax.swing.JButton();
        botonVolver = new javax.swing.JButton();
        rutaArchivo = new javax.swing.JFormattedTextField();

        javax.swing.GroupLayout jDialog1Layout = new javax.swing.GroupLayout(jDialog1.getContentPane());
        jDialog1.getContentPane().setLayout(jDialog1Layout);
        jDialog1Layout.setHorizontalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog1Layout.setVerticalGroup(
            jDialog1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jDialog2Layout = new javax.swing.GroupLayout(jDialog2.getContentPane());
        jDialog2.getContentPane().setLayout(jDialog2Layout);
        jDialog2Layout.setHorizontalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jDialog2Layout.setVerticalGroup(
            jDialog2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setForeground(new java.awt.Color(204, 204, 255));

        jLabel1.setText("Seleccione el archivo con los jugadores");

        botonExplorar.setText("Explorar");
        botonExplorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonExplorarActionPerformed(evt);
            }
        });

        botonRegistrar.setText("Registrar");
        botonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonRegistrarActionPerformed(evt);
            }
        });

        botonVolver.setText("Volver");
        botonVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonVolver)
                        .addGap(30, 30, 30)
                        .addComponent(botonRegistrar))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(rutaArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(botonExplorar)))
                .addGap(11, 11, 11))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rutaArchivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonExplorar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonVolver)
                    .addComponent(botonRegistrar))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonExplorarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonExplorarActionPerformed
        // TODO add your handling code here:

        JFileChooser selector = new JFileChooser();
        selector.showOpenDialog(null);
        archivo = selector.getSelectedFile();
        String nombreArchivo = archivo.getAbsolutePath();
        this.rutaArchivo.setText(nombreArchivo);

    }//GEN-LAST:event_botonExplorarActionPerformed

    private void botonVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVolverActionPerformed
        // TODO add your handling code here:
        this.dispose();
    }//GEN-LAST:event_botonVolverActionPerformed

    private void botonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonRegistrarActionPerformed
        int jugadoresPrevios = miJuego.listarJugadores().size();
        // TODO add your handling code here:
        //this.agregarJugadores(null);
        this.agregarJugadores(archivo);
        int jugadoresAgregados = miJuego.listarJugadores().size() - jugadoresPrevios;
        VentanaMensajes mensaje = new VentanaMensajes();
        mensaje.MostrarMensaje("Se agregaron " + jugadoresAgregados + " jugadores");
        mensaje.setAlwaysOnTop(true);
        mensaje.setVisible(true);
      
        this.dispose();
    }//GEN-LAST:event_botonRegistrarActionPerformed

    public void agregarJugadores(File archivo) {
        String nombreArchivo = archivo.getAbsolutePath();
        //this.labelAlias.setText(nombreArchivo);
        String nombre;
        String alias;
        int edad = 0;
        String[] datosJugador = null;
        Jugador jugador;
        boolean yaEsta;
        //Jugador jugador = new Jugador();
        ClaseLectura arch = new ClaseLectura(nombreArchivo);
        // String[] datosJugadores;

        while (arch.HayMasLineas()) {
            try {
                datosJugador = arch.linea().split("#");
                nombre = datosJugador[0].toUpperCase();
                alias = datosJugador[1].toUpperCase();

//            this.labelNombre.setText("Nombre: " + nombre);
//            this.labelAlias.setText("Alias: " + alias);
                // this.labelEdad.setText("Edad: " + datosJugador[2]);
                try {
                    edad = Integer.parseInt(datosJugador[2]);
                } catch (Exception e) {
                    edad = 13;
                }
                try {

                    yaEsta = false;
                    for (int i = 0; i < miJuego.getListaJugadores().size(); i++) {
                        Jugador aux = miJuego.getListaJugadores().get(i);
                        if (aux.getAlias().equals(alias)) {
                            aux.setEdad(edad);
                            aux.setNombre(nombre);
                            yaEsta = true;
                        }

                    }
                    if (!yaEsta) {
                        miJuego.agregarJugador(crearJugador(nombre, alias, edad));
                    }

                } catch (Exception e) {

                }
            } catch (Exception e) {
            }
        }

        //this.labelEdad.setText();
    }

    public static Jugador crearJugador(String nombre, String alias, int edad) {
        Jugador unJugador = new Jugador(nombre, alias, edad);
        // unJugador.setPartidasGanadas(ganadas);
        return unJugador;
    }

    /**
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaJugadorDesdeArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaJugadorDesdeArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaJugadorDesdeArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaJugadorDesdeArchivo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                // new VentanaJugadorDesdeArchivo().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonExplorar;
    private javax.swing.JButton botonRegistrar;
    private javax.swing.JButton botonVolver;
    private javax.swing.JDialog jDialog1;
    private javax.swing.JDialog jDialog2;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JFormattedTextField rutaArchivo;
    // End of variables declaration//GEN-END:variables
}
