package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Asiento;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
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
     * Querya para traer los asiento según programación
     * @param idProgramacionSeleccionada
     * @return
     */
    public List<Asiento> obtenerAsientosDisponibles(Long idProgramacionSeleccionada){
        return getEntityManager()
        .createQuery("SELECT a FROM Asiento a WHERE a.activo=true AND NOT EXISTS (SELECT rd FROM ReservaDetalle rd JOIN rd.idReserva r JOIN r.idProgramacion prn WHERE prn.idProgramacion=:idProgramacionSeleccionada AND a=rd.idAsiento) ORDER BY a.idAsiento ASC ", Asiento.class)
                .setParameter("idProgramacionSeleccionada", idProgramacionSeleccionada)
                .getResultList();
    }

}