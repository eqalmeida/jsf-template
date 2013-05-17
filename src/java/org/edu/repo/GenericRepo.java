package org.edu.repo;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import org.edu.model.EntityFacade;

public class GenericRepo<T extends EntityFacade> {

    private EntityManager em;
    private Class<T> type;

    public GenericRepo(Class<T> type) {
        this.em = Connection.getManager();
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
    
    public T buscaPorId(Object Id){
        T obj = em.find(type, Id);
        return obj;
    }
    
    private Query tudoQuery(){
        String sql = "SELECT x FROM "+type.getSimpleName()+" x";
        return em.createQuery(sql);
    }
    
    public List<T> listaTodos(){
        return tudoQuery().getResultList();
    }
    
    public List<T> listaTodos(int pageSize, int first){
        
        Query query = tudoQuery();
        query.setMaxResults(pageSize);
        query.setFirstResult(first);
        return query.getResultList();
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
