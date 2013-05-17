package org.edu.repo;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Connection {
    
    private static EntityManager manager;
    
    static {
        try{
            manager = Persistence.createEntityManagerFactory("lojaPU").createEntityManager();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    public static EntityManager getManager(){
        return manager;
    }
    
}
