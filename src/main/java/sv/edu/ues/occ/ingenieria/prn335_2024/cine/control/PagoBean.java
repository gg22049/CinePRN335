package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pago;
import java.io.Serializable;

public class PagoBean extends AbstractDataPersist<Pago> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public PagoBean() {
        super(Pago.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
