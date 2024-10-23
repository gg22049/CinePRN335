package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.FacturaDetalleSala;

import java.io.Serializable;

public class FacturaDetalleSalaBean extends AbstractDataPersistence<FacturaDetalleSala> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public FacturaDetalleSalaBean() {
        super(FacturaDetalleSala.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
