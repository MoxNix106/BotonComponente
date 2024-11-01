/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package componenteboton;

/**
 *
 * @author calib
 */
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.stream.Stream;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Captura extends JPanel implements ActionListener {
    private static final String DIRECTORIO_CAPTURAS = "capturas"; // Carpeta para almacenar las capturas
    private static final int LIMITE_CAPTURAS = 10; // Máximo número de capturas
    private final JButton botonCaptura; // Botón de captura

    public Captura() {
        // Crea el directorio de capturas si no existe
        File directorio = new File(DIRECTORIO_CAPTURAS);
        if (!directorio.exists() && directorio.mkdir()) {
            System.out.println("Directorio 'capturas' creado.");
        }
        // Configuración del botón de captura
        botonCaptura = new JButton("Capturar Ventana");
        botonCaptura.addActionListener(this); // Escucha de eventos en el botón
        this.add(botonCaptura); // Agregar el botón al panel
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        // Ejecuta la captura de pantalla cuando se presiona el botón
        if (evento.getSource() == botonCaptura) {
            capturarVentana(); // Captura la ventana actual
            verificarLimiteCapturas(); // Verifica y elimina capturas si se excede el límite
        }
    }

    private void capturarVentana() {
        try {
            // Obtener la ventana del JFrame que contiene el botón
            JFrame marco = (JFrame) SwingUtilities.getWindowAncestor(this);

            // Define el área de captura usando la posición y tamaño del JFrame
            Rectangle areaCaptura = new Rectangle(marco.getLocationOnScreen(), marco.getSize());

            // Usa Robot para capturar la pantalla en el área definida
            BufferedImage imagen = new Robot().createScreenCapture(areaCaptura);

            // Crea un nombre único para el archivo usando el tiempo actual
            String nombreArchivo = "captura_" + System.currentTimeMillis() + ".png";
            File archivoSalida = new File(DIRECTORIO_CAPTURAS, nombreArchivo);

            // Guarda la imagen en formato PNG
            ImageIO.write(imagen, "png", archivoSalida);
            System.out.println("Captura guardada en: " + archivoSalida.getAbsolutePath());
        } catch (AWTException | IOException e) {
            e.printStackTrace(); // Muestra cualquier error en la captura o guardado
        }
    }

    private void verificarLimiteCapturas() {
        try (Stream<Path> archivos = Files.list(Paths.get(DIRECTORIO_CAPTURAS))) {
            long cantidad = archivos.count(); // Cuenta los archivos en el directorio
            if (cantidad > LIMITE_CAPTURAS) {
                // Elimina archivos más antiguos si excede el límite
                Files.list(Paths.get(DIRECTORIO_CAPTURAS))
                    .filter(Files::isRegularFile)
                    .sorted(Comparator.comparingLong(this::obtenerTiempoCreacionArchivo))
                    .limit(cantidad - LIMITE_CAPTURAS)
                    .forEach(this::eliminarArchivo);
            }
        } catch (IOException e) {
            e.printStackTrace(); // Muestra errores en la verificación
        }
    }

    private long obtenerTiempoCreacionArchivo(Path ruta) {
        try {
            // Obtiene la fecha de creación del archivo
            return Files.readAttributes(ruta, BasicFileAttributes.class).creationTime().toMillis();
        } catch (IOException e) {
            e.printStackTrace();
            return Long.MAX_VALUE; // Evita eliminar si no se puede obtener la fecha
        }
    }

    private void eliminarArchivo(Path ruta) {
        try {
            Files.delete(ruta); // Elimina el archivo especificado
            System.out.println("Archivo eliminado: " + ruta);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Configuración del JFrame principal
        JFrame ventanaPrincipal = new JFrame("Captura de Ventana");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(300, 100);
        ventanaPrincipal.setLocationRelativeTo(null); // Centra la ventana

        // Agregar panel de captura al JFrame
        Captura panelCaptura = new Captura();
        ventanaPrincipal.add(panelCaptura);

        // Mostrar la ventana
        ventanaPrincipal.setVisible(true);
    }
}