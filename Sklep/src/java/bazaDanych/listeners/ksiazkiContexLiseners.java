/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazaDanych.listeners;

//import java.util.Date;
import bazaDanych.menagerBD;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author Patryk
 */
public class ksiazkiContexLiseners implements ServletContextListener{
    
    public void contextInitialized(ServletContextEvent sce){
        menagerBD.getMenager().closeEntityMenagerFatory();
    }
    
    public void contextDestroyed(ServletContextEvent sce) {
         menagerBD.getMenager().closeEntityMenagerFatory();
    }
    
    
}
