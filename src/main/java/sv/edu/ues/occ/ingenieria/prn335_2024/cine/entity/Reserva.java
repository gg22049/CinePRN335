package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "reserva", schema = "public")
public class Reserva implements Serializable {
    @Id
    @Column(name = "id_reserva", nullable = false)
    private Long idReserva;

    /** *Relacion: Reserva/Programacion
     * henry(hp19021):
     * Programacion (fk)(M) <-> (1)(id) Sala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programacion")
    private Programacion idProgramacion;

    /** *Relacion: Reserva/TipoReserva
     * henry(hp19021):
     * Programacion (fk)(M) <-> (1)(id) Sala
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_reserva")
    private TipoReserva idTipoReserva;

    /** *Relacion: Reserva/RerservaDetalle
     * henry(hp19021)
     * Reserva (id)(M) <-> (fk)(1) RerservaDetalle
     */
    @OneToMany(mappedBy = "idReserva", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ReservaDetalle> ReservaDetalleList;

    @Column(name = "fecha_reserva")
    private OffsetDateTime fechaReserva;

    @Size(max = 155)
    @Column(name = "estado", length = 155)
    private String estado;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    //Reserva
    public Reserva() {
    }

    public Reserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Reserva(Long idReserva, Programacion idProgramacion, TipoReserva idTipoReserva, OffsetDateTime fechaReserva, String estado, String observaciones) {
        this.idReserva = idReserva;
        this.idProgramacion = idProgramacion;
        this.idTipoReserva = idTipoReserva;
        this.fechaReserva = fechaReserva;
        this.estado = estado;
        this.observaciones = observaciones;
    }

    //Reserva
    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    //Programacion
    public Programacion getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Programacion idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    //TipoReserva
    public TipoReserva getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(TipoReserva idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    //ReservaDetalle
    public List<ReservaDetalle> getReservaDetalleList() {
        return ReservaDetalleList;
    }

    public void setReservaDetalleList(List<ReservaDetalle> reservaDetalleList) {
        ReservaDetalleList = reservaDetalleList;
    }

    public OffsetDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(OffsetDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}