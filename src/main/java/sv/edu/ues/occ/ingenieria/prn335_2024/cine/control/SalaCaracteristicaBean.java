package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.SalaCaracteristica;

import java.io.Serializable;

public class SalaCaracteristicaBean extends AbstractDataPersistence<SalaCaracteristica> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public SalaCaracteristicaBean() {
        super(SalaCaracteristica.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}