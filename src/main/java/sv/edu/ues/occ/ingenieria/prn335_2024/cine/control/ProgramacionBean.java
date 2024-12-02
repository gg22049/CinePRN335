package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Stateless
@LocalBean
public class ProgramacionBean extends AbstractDataPersistence<Programacion> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    EntityManager em;

    public ProgramacionBean() {
        super(Programacion.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * Esto sirve para la pantalla de reserva NO TOCAR!!!
     * @param fechaRecibida Obtener las programaciones por fecha
     * @return Lista de programaciones que inicia ese día
     */
    public List<Programacion> obtenerProgramacionesDelDia(OffsetDateTime fechaRecibida) {
        //Estamos dando por hecho que recibimos un día con zona horaria 00:00:00+00
        return getEntityManager()
                .createQuery("SELECT prn FROM Programacion prn JOIN prn.idPelicula p JOIN prn.idSala s WHERE prn.desde >= :fechaInicio AND prn.hasta <= :fechaFinal", Programacion.class)
                .setParameter("fechaInicio", fechaRecibida)
                .setParameter("fechaFinal", fechaRecibida.plusDays(1))
                .getResultList();
    }
}
