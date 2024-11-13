package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.AsientoCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
@LocalBean
public class AsientoCaracteristicaBean extends AbstractDataPersistence<AsientoCaracteristica> implements Serializable {

    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public AsientoCaracteristicaBean() {
        super(AsientoCaracteristica.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    public List<AsientoCaracteristica> caracteristicaSelected(final Long idAsiento, int first, int max ){
        try{
            TypedQuery<AsientoCaracteristica> q = em.createNamedQuery("AsientoCaracteristica.ListBySelected", AsientoCaracteristica.class);
            q.setParameter("idAsiento", idAsiento);
            q.setFirstResult(first);
            q.setMaxResults(max);
            return q.getResultList();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return List.of();
    }

    public int countAsiento(final Long idAsiento){
        try{
            TypedQuery<Long> q = em.createNamedQuery("AsientoCaracteristica.cantidadPaginador", Long.class);
            q.setParameter("idAsiento", idAsiento);
            return q.getSingleResult().intValue();
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE,e.getMessage(),e);
        }
        return 0;
    }

}