package fr.tse.fise2.info4;

import fr.tse.fise2.info4.Front.Interface;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        /**
         * Launch the application.
         */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Interface frame = new Interface();

                    // Récupérer la taille de l'écran
                    GraphicsDevice gd = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
                    int screenWidth = gd.getDisplayMode().getWidth();
                    int screenHeight = gd.getDisplayMode().getHeight();

                    // Définir la taille de la JFrame à la taille de l'écran
                    frame.setSize(screenWidth, screenHeight);

                    // Mettre la JFrame en plein écran
                    frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
