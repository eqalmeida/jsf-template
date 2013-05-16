package org.edu.repo;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import org.edu.model.GenericEntity;

public class GenericRepo<T extends GenericEntity> {

    private EntityManager em;
    private Class<T> type;

    public GenericRepo(EntityManager em, Class<T> type) {
        this.em = em;
        this.type = type;
    }

    public void gravar(T obj) {

        EntityTransaction transaction = em.getTransaction();

        try {
            transaction.begin();

            if (obj.isNew()) {
                em.persist(obj);
            } else {
                em.merge(obj);
            }

            transaction.commit();
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
    
    public List<T> listaTodos(){
        
        
        String sql = "SELECT x FROM "+type.getName()+" x";
        return em.createQuery(sql).getResultList();
    }
    
    public T busca(String campo, String valor){
        String sql = "SELECT x FROM "+type.getName()+ " WHERE x."+campo+" = '"+valor+"'";
        T obj = (T) em.createQuery(sql).getSingleResult();
        return obj;
    }

    public void excluir(T obj) {

        //
        // Obtem a transação
        //
        EntityTransaction transaction = em.getTransaction();

        try {

            //
            // Busca a entidade no banco.
            //
            obj = em.getReference(type, obj.getId());

            //
            // Verifica se é válido
            //
            if (obj != null) {
                transaction.begin();
                em.remove(obj);
                transaction.commit();
            }
            
        } finally {
            if (transaction.isActive()) {
                transaction.rollback();
            }
        }
    }
}
