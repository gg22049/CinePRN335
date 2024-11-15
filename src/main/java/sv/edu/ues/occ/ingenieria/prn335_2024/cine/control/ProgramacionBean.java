package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Programacion;

import java.io.Serializable;
import java.time.LocalDate;
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
     * Obtener las programaciones por fecha
     * @return
     */
    /*public List<Programacion> obtenerProgramacionesDelDia(LocalDate diaSeleccionado) {
    */
    /*WHERE FUNCTION('DATE', prn.desde) = "2024-09-23"*/
    public List<Programacion> obtenerProgramacionesDelDia() {
        return getEntityManager()
                .createQuery("SELECT prn FROM Programacion prn JOIN prn.idPelicula p JOIN prn.idSala s WHERE prn.desde >= '2024-09-23T00:00:00' AND prn.desde < '2024-09-24T00:00:00'", Programacion.class).getResultList();
    }
    /*
    return getEntityManager().createQuery("SELECT prn FROM Programacion prn JOIN prn.idPelicula p JOIN prn.idSala s WHERE prn.desde >= '2024-10-23T00:00:00' AND prn.desde < '2024-10-24T00:00:00'", Programacion.class).getResultList();
    SELECT p.nombre,
    s.nombre,
    s.idSucursal.nombre,
    prn.desde,
    prn.hasta
    FROM Programacion prn
    JOIN prn.idPelicula p
    JOIN prn.idSala s
    WHERE prn.desde >= '2024-10-23T00:00:00'
    AND prn.desde < '2024-10-24T00:00:00'*/

    /*
        return getEntityManager()
                .createQuery("SELECT prn FROM Programacion prn WHERE FUNCTION('DATE', prn.desde) = :diaSeleccionado", Programacion.class)
                .setParameter("diaSeleccionado", diaSeleccionado)
                .getResultList();
                */

}
