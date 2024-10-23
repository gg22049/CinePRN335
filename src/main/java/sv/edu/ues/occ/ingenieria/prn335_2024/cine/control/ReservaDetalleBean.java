package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

public class ReservaDetalleBean extends AbstractDataPersistence<ReservaDetalleBean> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ReservaDetalleBean() {
        super(sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ReservaDetalleBean.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}