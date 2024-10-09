package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "reserva_detalle", schema = "public")
public class ReservaDetalle implements Serializable {
    @Id
    @Column(name = "id_reserva_detalle", nullable = false)
    private Long idReservaDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva")
    private Reserva idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asiento")
    private Asiento idAsiento;

//    @OneToMany(mappedBy = "factura_detalle_sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//    private List<FacturaDetalleSala> facturaDetalleSalaList;

    @Size(max = 155)
    @Column(name = "estado", length = 155)
    private String estado;

    public ReservaDetalle() {
    }

    public ReservaDetalle(Long idReservaDetalle, Reserva idReserva, Asiento idAsiento, String estado) {
        this.idReservaDetalle = idReservaDetalle;
        this.idReserva = idReserva;
        this.idAsiento = idAsiento;
        this.estado = estado;
    }

    public Long getIdReservaDetalle() {
        return idReservaDetalle;
    }

    public void setIdReservaDetalle(Long idReservaDetalle) {
        this.idReservaDetalle = idReservaDetalle;
    }

    public Reserva getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Reserva idReserva) {
        this.idReserva = idReserva;
    }

    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

//    public List<FacturaDetalleSala> getFacturaDetalleSalaList() {
//        return facturaDetalleSalaList;
//    }
//
//    public void setFacturaDetalleSalaList(List<FacturaDetalleSala> facturaDetalleSalaList) {
//        this.facturaDetalleSalaList = facturaDetalleSalaList;
//    }
}