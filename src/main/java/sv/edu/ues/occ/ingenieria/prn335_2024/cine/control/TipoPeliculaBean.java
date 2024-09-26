package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula;

import java.io.Serializable;
public class TipoPeliculaBean extends AbstractDataPersist<TipoPelicula> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public TipoPeliculaBean() {
        super(TipoPelicula.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}