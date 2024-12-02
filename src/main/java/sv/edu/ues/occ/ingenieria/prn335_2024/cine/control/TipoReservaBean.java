package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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
     * Devolvemos todas las tiporeserva, falta filtrar activas
     * @return
     */
    public List<TipoReserva> obtenerTodos() {
        return em.createQuery("SELECT t FROM TipoReserva t", TipoReserva.class).getResultList();
    }

}