package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

public abstract class AbstractDataPersist <T> {
    public abstract EntityManager getEntityManager();
    Class tipoDato;
    public AbstractDataPersist (Class tipoDato){ this.tipoDato = tipoDato; }
    private class tipoDato {}

    public void create(T entity) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        if (entity == null) {
            throw new IllegalArgumentException("Parametro no valido");
        }
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            em.persist(entity);
        }catch (Exception ex){
            throw new IllegalStateException("Error al acceder al repositorio", ex);
        }
    }

    public T findById(final Object id) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        if (id == null) {
            throw new IllegalArgumentException("Parametro no valido");
        }
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error al acceder al repositorio");
            }
        }catch (Exception ex){
            throw new IllegalStateException("Error al acceder al repositorio", ex);
        }
        return (T) em.find(tipoDato.class,id);
    }

    public List<T> findByRange(int first, int max) throws IllegalArgumentException, IllegalStateException {
        EntityManager em = null;
        if (max <= 0 || max<=first) {
            throw new IllegalArgumentException("Parametro no valido");
        }
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<T> q = cb.createQuery(tipoDato);
            Root<T> raiz = q.from(tipoDato);
            CriteriaQuery cq = q.select(raiz);
            TypedQuery query = em.createQuery(cq);
            query.setFirstResult(first);
            query.setMaxResults(max);
            return query.getResultList();
        }catch (Exception ex){
            throw new IllegalStateException("Error al acceder al repositorio", ex);
        }
    }

    public void update(T entity) throws IllegalArgumentException, IllegalStateException {
        if (entity == null) {
            throw new IllegalArgumentException("Parametro no valido");
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            em.merge(entity);
        } catch (Exception ex) {
            throw new IllegalStateException("Error al actualizar la entidad", ex);
        }
    }

    public void delete(final Object id) throws IllegalArgumentException, IllegalStateException {
        if (id == null) {
            throw new IllegalArgumentException("Parametro no valido");
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            if (em == null) {
                throw new IllegalStateException("Error al acceder al repositorio");
            }
            em.remove(findById(id));
        } catch (Exception ex) {
            throw new IllegalStateException("Error al actualizar la entidad", ex);
        }
    }

}