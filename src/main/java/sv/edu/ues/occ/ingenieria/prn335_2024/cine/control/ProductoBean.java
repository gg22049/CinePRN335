package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.io.Serializable;

public class ProductoBean extends AbstractDataPersistence<ProductoBean> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ProductoBean() {
        super(sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.ProductoBean.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}