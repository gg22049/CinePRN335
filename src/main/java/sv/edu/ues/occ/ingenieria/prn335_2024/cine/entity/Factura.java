package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "factura", schema = "public")
public class Factura implements Serializable {
    @Id
    @Column(name = "id_factura", nullable = false)
    private Long idFactura;

    @OneToMany(mappedBy = "factura_detalle_sala", fetch = FetchType.LAZY)
    private List<FacturaDetalleSala> facturaDetalleSalas;

    @OneToMany(mappedBy = "factura_detalle_producto", fetch = FetchType.LAZY)
    private List<FacturaDetalleProducto> facturaDetalleProductos;

    @OneToMany(mappedBy = "pago", fetch = FetchType.LAZY)
    private List<Pago> pagos;

    @Size(max = 255)
    @Column(name = "cliente")
    private String cliente;

    @Size(max = 155)
    @Column(name = "dui", length = 155)
    private String dui;

    @Column(name = "fecha")
    private OffsetDateTime fecha;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    public Factura() {
    }

    public Factura(Long idFactura, String cliente, String dui, OffsetDateTime fecha, String comentarios) {
        this.idFactura = idFactura;
        this.cliente = cliente;
        this.dui = dui;
        this.fecha = fecha;
        this.comentarios = comentarios;
    }

    public Long getIdFactura() {
        return idFactura;
    }

    public void setIdFactura(Long idFactura) {
        this.idFactura = idFactura;
    }

    public String getCliente() {
        return cliente;
    }

    public void setCliente(String cliente) {
        this.cliente = cliente;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public OffsetDateTime getFecha() {
        return fecha;
    }

    public void setFecha(OffsetDateTime fecha) {
        this.fecha = fecha;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public List<FacturaDetalleSala> getFacturaDetalleSalas() {
        return facturaDetalleSalas;
    }

    public List<FacturaDetalleProducto> getFacturaDetalleProductos() {
        return facturaDetalleProductos;
    }

    public List<Pago> getPagos() {
        return pagos;
    }
}