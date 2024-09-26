package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

public class TipoAsientoBean extends AbstractDataPersist<sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoAsientoBean> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public TipoAsientoBean() {
        super(sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoAsientoBean.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}