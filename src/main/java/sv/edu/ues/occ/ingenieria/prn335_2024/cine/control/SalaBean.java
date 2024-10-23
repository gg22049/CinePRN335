package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;

import java.io.Serializable;

public class SalaBean extends AbstractDataPersistence<Sala> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public SalaBean() {
        super(Sala.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}