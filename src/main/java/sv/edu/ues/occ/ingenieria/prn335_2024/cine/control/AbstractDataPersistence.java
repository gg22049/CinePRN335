package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;

/**
 * Generalizacion de los metodos de crud
 * @param <T> parametro generico que se sustituye para utilizar la clase
 */
public abstract class AbstractDataPersistence<T> {

    /**
     * Metodo para obtener el el entity manager
     * @return Retorna un entity manager
     */
    public abstract EntityManager getEntityManager();

    protected final Class<T> tipoDato;

    public AbstractDataPersistence(Class<T> tipoDato){
        this.tipoDato = tipoDato;
    }

    /**
     * Metodo para crear nuevos registros en la base de datos
     * @param entity la entidad que se persistira en la base de datos
     * @throws IllegalStateException si se produce un error en el proceso
     * @throws IllegalArgumentException si la entidad es nula
     */
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

    /**
     * Método para traer todos los objetos
     * @return
     */
    public List<T> obtenerTodos() {
        return getEntityManager()
                .createQuery("SELECT e FROM " + tipoDato.getSimpleName() + " e", tipoDato)
                .getResultList();
    }

    /**
     * Metodo para buscar registros en la base de datos
     * @param id llave para realizar la busqueda
     * @return retorna la entidad buscada si se encuentra, sino retorna nulo
     * @throws IllegalArgumentException en caso de id nulo
     * @throws IllegalStateException en caso de error en proceso
     */
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

    /**
     * Metodo para eliminar registros de la base de datos
     * @param entity la entidad a eliminar
     * @throws IllegalArgumentException la entidad brindada es nula
     * @throws IllegalStateException error en proceso de eliminado
     */
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

    /**
     * Metodo para acualizar con campos de un registro
     * @param entity La entidad a Actualizar
     * @return Regresa la entidad actualizada
     * @throws IllegalArgumentException La entidad a actualizar es nula
     * @throws IllegalStateException Error en proceso de actualizacion
     */
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

    /**
     * Metodo para buscar listas de registros por rango
     * @param first Valor en donde comienza la lista de registros
     * @param pageSize Valor maximo de la lista
     * @return Retorna la lista en el rango estipulado
     * @throws IllegalArgumentException Rango brindado no valido
     * @throws IllegalStateException Error en el proceso de busqueda
     */
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

    /**
     * Metodo para contar la cantidad de registros de una tabla de la base de datos
     * @return int Retorna un valor entero
     * @throws IllegalStateException Error en proceso de contar los registro
     */
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