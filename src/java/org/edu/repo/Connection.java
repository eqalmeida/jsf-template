package org.edu.repo;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class Connection {

    private static EntityManager manager = null;

    public static EntityManager getManager() {

        if (manager == null) {
                manager = Persistence.createEntityManagerFactory("lojaPU").createEntityManager();
        }

        return manager;
    }
}
