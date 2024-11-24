package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "reserva_detalle", schema = "public")
@NamedQueries({
    /**⬇️ Necesita ser invocada
     * orden de retorno
     * (Long idReservaDetalle, Reserva idReserva, Asiento idAsiento, String estado)*/
    @NamedQuery(name = "ReservaDetalle.findAll", query = "SELECT r FROM ReservaDetalle r"),

    /**⬇️ Necesita idReservaDetalle(PK)[Long] de la tabla (ReservaDetalle)
     * orden de retorno
     * (Long idReservaDetalle, Reserva idReserva, Asiento idAsiento, String estado)*/
    @NamedQuery(name = "ReservaDetalle.findByIdReservaDetalle", query = "SELECT r FROM ReservaDetalle r WHERE r.idReservaDetalle = :idReservaDetalle"),

    /**⬇️ Necesita estado(columna)[String] de la tabla (ReservaDetalle)
     * orden de retorno
     * (Long idReservaDetalle, Reserva idReserva, Asiento idAsiento, String estado)*/
    @NamedQuery(name = "ReservaDetalle.findByEstado", query = "SELECT r FROM ReservaDetalle r WHERE r.estado = :estado")})

public class ReservaDetalle implements Serializable {
    @Id
    @Column(name = "id_reserva_detalle", nullable = false)
    private Long idReservaDetalle;

    /** *Relacion: ReservaDetalle/Reserva
     * Programacion (fk)(M) <-> (1)(id) Sala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva")
    private Reserva idReserva;

    /** *Relacion: ReservaDetalle/Asiento
     * Programacion (fk)(M) <-> (1)(id) Sala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asiento")
    private Asiento idAsiento;


    @Size(max = 155)
    @Column(name = "estado", length = 155)
    private String estado;

    //REservaDetalle
    public ReservaDetalle() {
    }

    public ReservaDetalle(Long idReservaDetalle) {
        this.idReservaDetalle = idReservaDetalle;
    }

    public ReservaDetalle(Long idReservaDetalle, Reserva idReserva, Asiento idAsiento, String estado) {
        this.idReservaDetalle = idReservaDetalle;
        this.idReserva = idReserva;
        this.idAsiento = idAsiento;
        this.estado = estado;
    }

    //ReservaDetalle
    public Reserva getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Reserva idReserva) {
        this.idReserva = idReserva;
    }

    //Asiento
    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }


    public Long getIdReservaDetalle() {
        return idReservaDetalle;
    }

    public void setIdReservaDetalle(Long idReservaDetalle) {
        this.idReservaDetalle = idReservaDetalle;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}