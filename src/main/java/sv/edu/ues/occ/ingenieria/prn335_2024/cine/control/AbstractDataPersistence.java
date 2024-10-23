package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractDataPersistence<T> {

    public abstract EntityManager getEntityManager();

    protected final Class<T> tipoDato;

    public AbstractDataPersistence(Class<T> tipoDato){
        this.tipoDato = tipoDato;
    }

    public String imprimirCarnet(){ return "GG22049";}

    public void create(final T entity) throws  IllegalStateException, IllegalArgumentException {
        EntityManager em = null;

        if(entity==null){
            throw new IllegalArgumentException("Parametro no valido");
        }
        try {
            em = getEntityManager();
            if(em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            em.persist(entity);
        }catch (Exception ex){
            throw new  IllegalStateException("Error al acceder al repositorio",ex);
        }

    }


    public T findById(final Object id) throws IllegalArgumentException, IllegalStateException{
        EntityManager em = null;

        if(id==null){
            throw new IllegalArgumentException("Parametro no valido");
        }
        try {
            em = getEntityManager();
            if(em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
        }catch (Exception ex){
            throw new  IllegalStateException("Error al acceder al repositorio",ex);
        }
        return em.find(tipoDato, id);
    }

    public void delete(final T entity) throws IllegalArgumentException, IllegalStateException{
        EntityManager em = null;

        if(entity==null){
            throw new IllegalArgumentException("Parametro no valido");
        }
        try {
            em = getEntityManager();
            if(em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            em.remove(entity);
        }catch (Exception ex){
            throw new  IllegalStateException("Error al acceder al repositorio",ex);
        }
    }

    public T update(final T entity) throws IllegalArgumentException, IllegalStateException{
        EntityManager em = null;

        if(entity==null){
            throw new IllegalArgumentException("Parametro no valido");
        }
        try {
            em = getEntityManager();
            if(em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            return em.merge(entity);
        }catch (Exception ex){
            throw new  IllegalStateException("Error al acceder al repositorio",ex);
        }
    }

    public List<T> findRange(int first, int pageSize) throws IllegalArgumentException, IllegalStateException{
        if(first < 0 || pageSize <= 0) {
            throw new IllegalArgumentException("Parametros no validos");
        }
        EntityManager em = null;
        try{
            em = getEntityManager();
            if (em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery cq = cb.createQuery(tipoDato);
            Root<T> raiz = cq.from(tipoDato);
            cq.select(raiz);
            TypedQuery q = em.createQuery(cq);

            q.setFirstResult(first);
            q.setMaxResults(pageSize);
            return q.getResultList();
        }catch(Exception ex){
            throw new IllegalStateException("Error al acceder al repositorio");
        }
    }


    public int count() throws IllegalStateException{
        EntityManager em = null;
        try{
            em = getEntityManager();
            if (em == null){
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Long> cq = cb.createQuery(Long.class);
            Root<T> raiz = cq.from(tipoDato);
            cq.select(cb.count(raiz));
            TypedQuery q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }catch(Exception ex){
            throw new IllegalStateException("Error al acceder al repositorio",ex);
        }
    }
}