package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

public class ReservaBean extends AbstractDataPersistence<ReservaBean> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ReservaBean() {
        super(sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ReservaBean.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
