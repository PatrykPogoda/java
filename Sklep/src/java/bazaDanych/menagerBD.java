/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bazaDanych;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Patryk
 */
public class menagerBD {
    
    private static menagerBD instancja;
    private EntityManagerFactory emf;
    private menagerBD(){}
    
    public synchronized static menagerBD getMenager(){
        if(instancja == null)
            return new menagerBD();
       return instancja;
    }
    
    public EntityManagerFactory creaEntityManagerFactory(){
        if(emf == null)
            emf = Persistence.createEntityManagerFactory("Ksiegarnia");
       return emf;
    }
    
    public EntityManager creaEntityManager(){
        return this.creaEntityManagerFactory().createEntityManager();
    }
    
    public void closeEntityMenagerFatory(){
        if(emf != null)
            emf.close();
    }
    
}
