/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package com.booklending.cs3301;

/**
 *
 * @author Dalitso Sakala
 */
import java.awt.Dimension;
import javax.swing.UIManager;
public class Mavenproject1 {

    public static void main(String[] args) {
        try{
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          
       MainWindow window=new MainWindow();
       window.setMaximumSize(new Dimension(400,400));
       window.setResizable(false);
       window.setVisible(true);
        }catch(Exception x){
        }
    }
}
