package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.util.List;

@Stateless
@LocalBean
public class TipoReservaBean extends AbstractDataPersistence<TipoReserva> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public TipoReservaBean() {
        super(TipoReserva.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }


    /**
     * Solucion 2 para el frm
     * @return
     */

    public List<TipoReserva> obtenerTodos() {
        return em.createQuery("SELECT t FROM TipoReserva t", TipoReserva.class).getResultList();
    }

    /*
    public List<TipoReserva> findAllUnique() {
        TypedQuery<TipoReserva> query = getEntityManager().createQuery(
                "SELECT DISTINCT t FROM TipoReserva t", TipoReserva.class);
        return query.getResultList();
    }*/

}