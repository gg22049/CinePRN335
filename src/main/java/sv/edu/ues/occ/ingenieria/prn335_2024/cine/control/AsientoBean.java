package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class AsientoBean extends AbstractDataPersistence<Asiento> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public AsientoBean() {
        super(Asiento.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public List<Asiento> caracteristicaSelected(final Long idSala, int first, int max ){
        try{
            TypedQuery<Asiento> q = em.createNamedQuery("Asiento.ListBySelected", Asiento.class);
            q.setParameter("idSala", idSala);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return List.of();
    }

    public int countAsiento(final Long idSala){
        try{
            TypedQuery<Long> q = em.createNamedQuery("Asiento.cantidadPaginador", Long.class);
            q.setParameter("idSala", idSala);
            return q.getSingleResult().intValue();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return 0;
    }

    /**
     * Querys para traer los asiento disponibles según programación
     * @param
     * @return
     */
    public List<Long> obtenerAsientosDisponibles(Integer idSalaSeleccionada){
        List<Long> asientosID = getEntityManager()
        .createQuery("SELECT a.idAsiento\n" +
                "FROM Sala s \n" +
                "LEFT JOIN s.AsientoList a \n" +
                "LEFT JOIN a.ReservaDetalleList rd \n" +
                "WHERE s.idSala = :idSala\n" +
                "AND rd.idAsiento IS NULL\n" +
                "ORDER BY a.idAsiento ASC", Long.class)
                .setParameter("idSala", idSalaSeleccionada)
                .getResultList();
        return new ArrayList<>(asientosID);
    }

}