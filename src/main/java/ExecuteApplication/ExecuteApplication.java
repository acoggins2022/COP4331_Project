/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ExecuteApplication;

import GUI.LoginWindow;
import javax.swing.SwingUtilities;

/**
 *
 * @author cashhollister
 */
public class ExecuteApplication {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginWindow());
    }
}