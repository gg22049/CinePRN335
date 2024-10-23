package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PagoDetalle;

import java.io.Serializable;

public class PagoDetalleBean extends AbstractDataPersistence<PagoDetalle> implements Serializable {
        @PersistenceContext(unitName = "CinePU")
        EntityManager em;

        public PagoDetalleBean() {
            super(PagoDetalle.class);
        }

        @Override
        public EntityManager getEntityManager() {
            return em;
        }
}