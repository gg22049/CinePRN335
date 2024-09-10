package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;

public abstract class AbstractDataPersist <T> {
    public abstract EntityManager getEntityManager();
    Class tipoDato;
    public AbstractDataPersist (Class tipoDato){ this.tipoDato = tipoDato; }

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

    private class tipoDato {
    }
}
